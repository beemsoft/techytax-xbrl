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
package org.techytax.importing.helper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.techytax.dao.KostensoortDao;
import org.techytax.dao.SettlementDao;
import org.techytax.domain.Cost;
import org.techytax.domain.CostConstants;
import org.techytax.domain.Kostensoort;
import org.techytax.helper.CostSplitter;
import org.techytax.helper.DutchTaxCodeHelper;
import org.techytax.util.DateHelper;

import com.Ostermiller.util.CSVParser;
import com.Ostermiller.util.LabeledCSVParser;

public class IngTransactionReader extends BaseTransactionReader {

	private static LabeledCSVParser parser = null;

	public List<Cost> readFile(BufferedReader in, String userId) throws NumberFormatException, Exception {
		KostensoortDao dao = new KostensoortDao();
		List<Kostensoort> kostensoortList2 = dao.getCostTypesForAccount();
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
				if (cost.getCostTypeId() != CostConstants.SETTLEMENT && cost.getCostTypeId() != CostConstants.SETTLEMENT_DISCOUNT) {
					kostLijst.add(cost);
				} else {
					// Administrative split
					long percentage = settlementDao.getPercentage(Long.parseLong(userId));
					Cost splitCost = new Cost();
					splitCost.setAmount(cost.getAmount());
					splitCost.setVat(cost.getVat());
					if (cost.getCostTypeId() == CostConstants.SETTLEMENT) {
						splitCost.setCostTypeId(CostConstants.UITGAVE_DEZE_REKENING_FOUTIEF);
					} else {
						splitCost.setCostTypeId(CostConstants.INCOME_CURRENT_ACCOUNT_IGNORE);
					}
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

	private Cost processLine(String[] line, int lineNumber, String userId) {
		Cost kost = new Cost();
		try {
			String datum = line[0];
			kost.setDate(DateHelper.stringToDate(datum.substring(0, 4) + "-" + datum.substring(4, 6) + "-" + datum.substring(6, 8)));
			BigDecimal bedrag = new BigDecimal(line[6].replace(',', '.'));
			kost.setAmount(bedrag);
			if (line[5].equals("Af")) {
				kost.setIncoming(false);
			} else {
				kost.setIncoming(true);
			}
			String omschrijving = line[1] + " " + line[8];

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

	public static void main(String[] args) throws NumberFormatException, Exception {
		FileInputStream fis = new FileInputStream("test.bat");
		IngTransactionReader rekeningFileHelper = new IngTransactionReader();
		List<Cost> result = rekeningFileHelper.readFile(new BufferedReader(new InputStreamReader(fis)),  "1");
	}

}
