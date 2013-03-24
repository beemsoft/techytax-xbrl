/**
 * Copyright 2013 Hans Beemsterboer
 * 
 * This file is part of the TechyTax program.
 *
 * TechyTax is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * TechyTax is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TechyTax; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.techytax.helper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.techytax.dao.AccountDao;
import org.techytax.dao.KostensoortDao;
import org.techytax.dao.KostmatchDao;
import org.techytax.dao.SettlementDao;
import org.techytax.dao.VatMatchDao;
import org.techytax.domain.AccountType;
import org.techytax.domain.Cost;
import org.techytax.domain.CostConstants;
import org.techytax.domain.Kostensoort;
import org.techytax.domain.Kostmatch;
import org.techytax.domain.VatMatch;
import org.techytax.domain.VatType;
import org.techytax.util.DateHelper;

import com.Ostermiller.util.CSVParser;

public class RekeningFileAbnAmroHelper {

	private static CSVParser parser = null;

	private static Vector<String[]> regels = null;

	private static List<Kostensoort> kostensoortList = null;

	private RekeningFileAbnAmroHelper() {
	}

	public static AccountType getAccountType(String fileName, long userId) throws Exception {
		int index = fileName.indexOf("_");
		String accountNumber = fileName.substring(0, index);
		AccountDao accountDao = new AccountDao();
		return accountDao.getAccountType(accountNumber, userId);
	}

	public static List<Cost> readFileForAbnAmroBank(BufferedReader in, List<Kostensoort> kostensoortList2, String userId) throws NumberFormatException, Exception {
		SettlementDao settlementDao = new SettlementDao();

		kostensoortList = kostensoortList2;
		List<Cost> kostLijst = new ArrayList<Cost>();
		try {
			parser = new CSVParser(in);
			parser.changeDelimiter('\t');

			verwerkRecords();

			Vector<String[]> data = getRegels();
			Cost cost = null;
			for (int regelNummer = 1; regelNummer <= data.size(); regelNummer++) {
				String[] regel = (String[]) data.get(regelNummer - 1);
				for (int i=0; i< regel.length; i++) {
					System.out.print(regel[i]+" ");
				}
				cost = processLine(regel, regelNummer, userId);
				if (cost.getCostTypeId() != CostConstants.SETTLEMENT) {
					kostLijst.add(cost);
				} else {
					// Administrative split
					long percentage = settlementDao.getPercentage(Long.parseLong(userId));
					Cost splitCost = new Cost();
					splitCost.setAmount(cost.getAmount());
					splitCost.setVat(cost.getVat());
					splitCost.setCostTypeId(CostConstants.UITGAVE_DEZE_REKENING_FOUTIEF);
					splitCost.setDate(cost.getDate());
					splitCost.setDescription(cost.getDescription());
					splitCost.setKostenSoortOmschrijving(getKostOmschrijving(splitCost.getCostTypeId()));
					CostSplitter.applyPercentage(splitCost, (int)(100-percentage));
					CostSplitter.applyPercentage(cost, (int)percentage);
					kostLijst.add(cost);
					kostLijst.add(splitCost);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return kostLijst;
	}

	private static void verwerkRecords() throws IOException {
		int regelNummer = 0;

		String[] regel = parser.getLine();
		regels = new Vector<String[]>();
		while (regel != null) {

			++regelNummer;
			regels.add(regel);

			regel = parser.getLine();
		}
	}

	private static Cost processLine(String[] line, int lineNumber, String userId) {
		Cost kost = new Cost();
		try {
			String datum = line[2];
			kost.setDate(DateHelper.stringToDate(datum.substring(0, 4) + "-" + datum.substring(4, 6) + "-" + datum.substring(6, 8)));
			BigDecimal bedrag = new BigDecimal(line[6].replace(',', '.'));
			if (bedrag.compareTo(new BigDecimal("0")) == -1) {
				kost.setIncoming(false);
			} else {
				kost.setIncoming(true);
			}
			bedrag = bedrag.abs();
			kost.setAmount(bedrag);
			String omschrijving = line[7];

			if (omschrijving.trim().equals("")) {
				kost.setCostTypeId(CostConstants.UNDETERMINED);
				kost.setKostenSoortOmschrijving(getKostOmschrijving(kost.getCostTypeId()));
				kost.setVat(new BigDecimal("0"));
			} else {
				kost.setDescription(omschrijving);
				if (omschrijving.contains("BELASTINGDIENST APELDOORN")) {
					DutchTaxCodeHelper.convertTaxCode(kost);
				}
				kost = matchKost(kost, userId);
			}
			return kost;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static Kostmatch findCostMatch(String omschrijving, String userId) throws Exception {
		KostmatchDao kostmatchDao = new KostmatchDao();
		List<Kostmatch> kostmatchList = kostmatchDao.getCostMatchPrivateList(userId);
		Iterator<Kostmatch> iterator = kostmatchList.iterator();
		while (iterator.hasNext()) {
			Kostmatch kostmatch = iterator.next();
			if (omschrijving.toUpperCase().contains(kostmatch.getMatchText().toUpperCase())) {
				return kostmatch;
			}
		}
		kostmatchList = kostmatchDao.getKostmatchLijst();
		iterator = kostmatchList.iterator();
		while (iterator.hasNext()) {
			Kostmatch kostmatch = iterator.next();
			if (omschrijving.toUpperCase().contains(kostmatch.getMatchText().toUpperCase())) {
				return kostmatch;
			}
		}
		return null;
	}

	private static Cost matchKost(Cost kost, String userId) throws Exception {
		long kostensoortId = CostConstants.UNDETERMINED;
		kost.setVat(new BigDecimal("0"));
		Kostmatch costMatch = findCostMatch(kost.getDescription(), userId);
		if (costMatch != null) {
			kostensoortId = costMatch.getKostenSoortId();
			KostensoortDao kostensoortDao = new KostensoortDao();
			Kostensoort costType = kostensoortDao.getKostensoort(Long.toString(kostensoortId));
			if (costType.isBtwVerrekenbaar()) {
				VatMatchDao vatMatchDao = new VatMatchDao();
				VatMatch vatMatch = vatMatchDao.getVatMatch(Long.toString(costMatch.getId()));
				if (vatMatch == null) {
					vatMatch = vatMatchDao.getVatMatchPrivate(Long.toString(costMatch.getId()));
				}
				if (vatMatch != null) {
					if (vatMatch.getVatType() == VatType.HIGH) {
						CostSplitter.splitPercentagFromAmount(kost, (int)(100*VatType.HIGH.getValue(kost.getDate())));
					}
					if (vatMatch.getVatType() == VatType.LOW) {
						CostSplitter.splitPercentagFromAmount(kost, 6);
					}
				}
			}
		}
		kost.setCostTypeId(kostensoortId);
		kost.setKostenSoortOmschrijving(getKostOmschrijving(kostensoortId));
		return kost;
	}

	private static String getKostOmschrijving(long kostensoortId) {
		Iterator<Kostensoort> iter = kostensoortList.iterator();
		while (iter.hasNext()) {
			Kostensoort kostensoort = iter.next();
			if (kostensoort.getKostenSoortId() == kostensoortId) {
				return kostensoort.getOmschrijving();
			}
		}
		return "costtype.none";
	}

	private static Vector<String[]> getRegels() {
		return regels;
	}
	
	public static void main(String[] args) throws NumberFormatException, Exception {
		FileInputStream fis = new FileInputStream("test.bat");
		KostensoortDao dao = new KostensoortDao();
		List<Kostensoort> kostensoortLijst = dao.getCostTypesForAccount();

		List<Cost> result = RekeningFileAbnAmroHelper.readFileForAbnAmroBank(new BufferedReader(new InputStreamReader(fis)), kostensoortLijst, "1");
	}

}
