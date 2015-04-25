/**
 * Copyright 2015 Hans Beemsterboer
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
import static org.techytax.domain.CostConstants.UNDETERMINED;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.springframework.stereotype.Component;
import org.techytax.domain.Cost;
import org.techytax.domain.CostConstants;
import org.techytax.domain.CostMatchParent;
import org.techytax.domain.Kostmatch;
import org.techytax.helper.CostSplitter;
import org.techytax.util.DateHelper;

import com.Ostermiller.util.CSVParser;
import com.Ostermiller.util.LabeledCSVParser;

@Component
public class TravelChipCardTransactionReader extends BaseTransactionReader {

	private LabeledCSVParser parser = null;

	public List<Cost> readFile(BufferedReader in) throws Exception {
		try {
			parser = new LabeledCSVParser(new CSVParser(in));
			parser.changeDelimiter(';');
			System.out.println(parser.getLabels()[0]);
			verwerkRecords();

			Vector<String[]> data = getRegels();
			Cost cost;
			for (int regelNummer = 1; regelNummer <= data.size(); regelNummer++) {
				String[] regel = data.get(regelNummer - 1);
				cost = processLine(regel, regelNummer);
				if (cost != null && cost.getCostType() != null && (cost.getCostType().equals(TRAVEL_WITH_PUBLIC_TRANSPORT_OTHER_ACCOUNT) || cost.getCostType().equals(UNDETERMINED))) {
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
		regels = new Vector<>();
		while (regel != null) {

			++regelNummer;
			regels.add(regel);

			regel = parser.getLine();
		}
	}

	protected CostMatchParent matchCost(Cost kost) throws Exception {
		CostMatchParent costMatch = super.findCostMatch(kost.getDescription());
		if (costMatch != null) {
			// kost.setDescription(kost.getDescription());
			kost.setCostType(costMatch.getCostType());
			CostSplitter.splitPercentagFromAmount(kost, 6);
			return costMatch;
		} else {
			kost.setCostType(CostConstants.UNDETERMINED);
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
	
	@Override
	public void reset() {
		kostLijst = new ArrayList<>();
		kostmatchList = new ArrayList<>();
	}	

	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream("test.csv");
		TravelChipCardTransactionReader helper = new TravelChipCardTransactionReader();
		List<Cost> result = helper.readFile(new BufferedReader(new InputStreamReader(fis)));
		System.out.println("Ok");
	}

}
