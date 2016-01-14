/**
 * Copyright 2016 Hans Beemsterboer
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

import com.Ostermiller.util.CSVParser;
import com.Ostermiller.util.LabeledCSVParser;
import org.springframework.stereotype.Component;
import org.techytax.domain.Cost;
import org.techytax.domain.CostMatchParent;
import org.techytax.helper.DutchTaxCodeHelper;
import org.techytax.util.DateHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static org.techytax.domain.CostConstants.UNDETERMINED;

@Component
public class KnabTransactionReader extends BaseTransactionReader {

	private static LabeledCSVParser parser = null;
	
	public List<Cost> readFile(BufferedReader in) throws Exception {
		try {
			parser = new LabeledCSVParser(new CSVParser(in));
			parser.changeDelimiter(';');
			System.out.println(parser.getLabels()[0]);
			verwerkRecords();

			Vector<String[]> data = getRegels();
			for (int regelNummer = 2; regelNummer <= data.size(); regelNummer++) {
				String[] regel = data.get(regelNummer - 1);
				processLine(regel, regelNummer);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return kostLijst;
	}

	private static void verwerkRecords() throws IOException {
		int regelNummer = 0;

		String[] regel = parser.getLine();
		regels = new Vector<>();
		while (regel != null) {

			++regelNummer;
			regels.add(regel);

			regel = parser.getLine();
		}
	}

	private void processLine(String[] line, int lineNumber) {
		Cost kost = new Cost();
		CostMatchParent costMatch = null;
		try {
			String datum = line[1];
			kost.setDate(DateHelper.stringToDateForIng(datum));
			BigDecimal bedrag = new BigDecimal(line[4].replace(',', '.'));
			kost.setAmount(bedrag);
			String omschrijving = line[6] + " " + line[9];
			if (omschrijving.trim().equals("")) {
				kost.setCostType(UNDETERMINED);
				kost.setVat(BigDecimal.ZERO);
			} else {
				kost.setDescription(omschrijving);
				if (omschrijving.contains("BELASTINGDIENST")) {
					DutchTaxCodeHelper.convertTaxCode(kost);
				}
				costMatch = matchCost(kost);
			}
			addCostOrHandleAdminstrativeSplitting(kost, costMatch);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void reset() {
		kostLijst = new ArrayList<>();
		kostmatchList = new ArrayList<>();
	}

}
