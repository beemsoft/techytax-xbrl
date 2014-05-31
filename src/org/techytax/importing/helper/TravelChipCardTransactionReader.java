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

import static org.techytax.domain.CostConstants.TRAVEL_WITH_PUBLIC_TRANSPORT_OTHER_ACCOUNT;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.techytax.domain.Cost;
import org.techytax.domain.CostType;
import org.techytax.domain.Kostmatch;
import org.techytax.domain.PrivateCostMatch;
import org.techytax.helper.CostSplitter;
import org.techytax.util.DateHelper;

import com.Ostermiller.util.CSVParser;
import com.Ostermiller.util.LabeledCSVParser;

public class TravelChipCardTransactionReader extends BaseTransactionReader {

	private LabeledCSVParser parser = null;

	public List<Cost> readFile(BufferedReader in) throws NumberFormatException, Exception {
		try {
			parser = new LabeledCSVParser(new CSVParser(in));
			parser.changeDelimiter(';');
			System.out.println(parser.getLabels()[0]);
			verwerkRecords();

			Vector<String[]> data = getRegels();
			Cost cost = null;
			for (int regelNummer = 1; regelNummer <= data.size(); regelNummer++) {
				String[] regel = (String[]) data.get(regelNummer - 1);
				cost = processLine(regel, regelNummer);
				if (cost != null) {
					kostLijst.add(cost);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return kostLijst;
	}

	private void verwerkRecords() throws IOException {
		int regelNummer = 0;

		String[] regel = parser.getLine();
		regels = new Vector<String[]>();
		while (regel != null) {

			++regelNummer;
			regels.add(regel);

			regel = parser.getLine();
		}
	}

	protected Kostmatch findCostMatch(String omschrijving) throws Exception {
		List<PrivateCostMatch> kostmatchList = privateCostMatchDao.findAll();
		Iterator<PrivateCostMatch> iterator = kostmatchList.iterator();
		while (iterator.hasNext()) {
//			Kostmatch kostmatch = iterator.next();
//			if (omschrijving.toUpperCase().contains(kostmatch.getMatchText().toUpperCase())) {
//				return kostmatch;
//			}
		}
		return null;
	}

	protected Kostmatch matchCost(Cost kost) throws Exception {
		CostType kostensoort = TRAVEL_WITH_PUBLIC_TRANSPORT_OTHER_ACCOUNT;
		Kostmatch costMatch = findCostMatch(kost.getDescription());
		if (costMatch != null) {
			kost.setDescription(kost.getDescription());
			kost.setCostType(kostensoort);
			CostSplitter.splitPercentagFromAmount(kost, 6);
			return costMatch;
		}
		return null;
	}

	private Cost processLine(String[] line, int lineNumber) {
		Cost kost = new Cost();
		try {
			String datum = line[0];
			System.out.println("Datum: " + datum);
			kost.setDate(DateHelper.stringToDateForTravelChipCard(datum));
			BigDecimal bedrag = new BigDecimal(line[5].replace(',', '.'));
			kost.setAmount(bedrag);
			String omschrijving = "Van " + line[2] + " naar " + line[4] + " (" + line[3] + ") " + line[6] + " " + line[7] + " " + line[8];

			kost.setDescription(omschrijving);
			matchCost(kost);
			return kost;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) throws NumberFormatException, Exception {
		FileInputStream fis = new FileInputStream("test.csv");
		TravelChipCardTransactionReader helper = new TravelChipCardTransactionReader();
		List<Cost> result = helper.readFile(new BufferedReader(new InputStreamReader(fis)));
		System.out.println("Ok");
	}

}
