/**
 * Copyright 2012 Hans Beemsterboer
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
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.techytax.dao.AccountDao;
import org.techytax.dao.KostensoortDao;
import org.techytax.dao.VatMatchDao;
import org.techytax.dao.KostmatchDao;
import org.techytax.dao.SettlementDao;
import org.techytax.domain.AccountType;
import org.techytax.domain.VatMatch;
import org.techytax.domain.Cost;
import org.techytax.domain.KostConstanten;
import org.techytax.domain.Kostensoort;
import org.techytax.domain.Kostmatch;
import org.techytax.domain.VatType;

import com.Ostermiller.util.CSVParser;
import com.Ostermiller.util.LabeledCSVParser;

public class RekeningFileHelper {

	final static char DELIMITER = ',';

	final static char QUOTE = '"';

	private String fileName;

	private static LabeledCSVParser parser = null;

	private Vector<String> labels = new Vector<String>();

	private static Vector<String[]> regels = null;

	private static List<Kostensoort> kostensoortList = null;

	private RekeningFileHelper() {
	}

	public static AccountType getAccountType(String fileName, long userId) throws Exception {
		int index = fileName.indexOf("_");
		String accountNumber = fileName.substring(0, index);
		AccountDao accountDao = new AccountDao();
		return accountDao.getAccountType(accountNumber, userId);
	}

	public static List<Cost> readFile(BufferedReader in, List<Kostensoort> kostensoortList2, String userId) throws NumberFormatException, Exception {
		SettlementDao settlementDao = new SettlementDao();

		kostensoortList = kostensoortList2;
		List<Cost> kostLijst = new ArrayList<Cost>();
		try {
			parser = new LabeledCSVParser(new CSVParser(in));
			System.out.println(parser.getLabels()[0]);
			verwerkRecords();

			Vector<String[]> data = getRegels();
			Cost cost = null;
			for (int regelNummer = 1; regelNummer <= data.size(); regelNummer++) {
				String[] regel = (String[]) data.get(regelNummer - 1);
				cost = processLine(regel, regelNummer, userId);
				if (cost.getCostTypeId() != KostConstanten.SETTLEMENT) {
					kostLijst.add(cost);
				} else {
					// Administrative split
					long percentage = settlementDao.getPercentage(Long.parseLong(userId));
					Cost splitCost = new Cost();
					splitCost.setAmount(cost.getAmount());
					splitCost.setVat(cost.getVat());
					splitCost.setCostTypeId(KostConstanten.UITGAVE_DEZE_REKENING_FOUTIEF);
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

	public static Cost processLine(String[] line, int lineNumber, String userId) {
		Cost kost = new Cost();
		try {
			String datum = line[0];
			kost.setDate(datum.substring(0, 4) + "-" + datum.substring(4, 6) + "-" + datum.substring(6, 8));
			BigDecimal bedrag = new BigDecimal(line[6].replace(',', '.'));
			kost.setAmount(bedrag);
			if (line[5].equals("Af")) {
				kost.setIncoming(false);
			} else {
				kost.setIncoming(true);
			}
			String omschrijving = line[1] + " " + line[8];

			if (omschrijving.trim().equals("")) {
				kost.setCostTypeId(KostConstanten.UNDETERMINED);
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
		long kostensoortId = KostConstanten.UNDETERMINED;
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
						CostSplitter.splitPercentagFromAmount(kost, 19);
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

	public String getFileName() {
		return fileName;
	}

	public Vector<String> getLabels() {
		return labels;
	}

	public static Vector<String[]> getRegels() {
		return regels;
	}

	public static void main(String[] args) throws Exception {
		KostensoortDao kostensoortDao = new KostensoortDao();
		kostensoortList = kostensoortDao.getKostensoortLijst();
		Cost cost = new Cost();
		cost.setAmount(new BigDecimal("100"));
		cost.setDescription("77820003 85-PFP-8 3E MND TIJDVAK 10/01/12-1");
		cost.setUserId(1);
		Cost matchedCost = matchKost(cost, "1");
		System.out.println("Test: " + matchedCost.getCostTypeId());
		System.out.println("Test: " + matchedCost.getVat());

	}

}
