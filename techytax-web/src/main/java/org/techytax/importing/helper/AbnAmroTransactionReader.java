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

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.springframework.stereotype.Component;
import org.techytax.domain.Cost;
import org.techytax.domain.CostConstants;
import org.techytax.domain.CostMatchParent;
import org.techytax.domain.Kostmatch;
import org.techytax.helper.DutchTaxCodeHelper;
import org.techytax.util.DateHelper;

import com.Ostermiller.util.CSVParser;

@Component
public class AbnAmroTransactionReader extends BaseTransactionReader {

	private static CSVParser parser = null;

	public List<Cost> readFile(BufferedReader in) throws NumberFormatException, Exception {
		try {
			parser = new CSVParser(in);
			parser.changeDelimiter('\t');

			verwerkRecords();

			Vector<String[]> data = getRegels();
			for (int regelNummer = 1; regelNummer <= data.size(); regelNummer++) {
				String[] regel = (String[]) data.get(regelNummer - 1);
				for (int i=0; i< regel.length; i++) {
					System.out.print(regel[i]+" ");
				}
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
		regels = new Vector<String[]>();
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
			String datum = line[2];
			kost.setDate(DateHelper.stringToDate(datum.substring(0, 4) + "-" + datum.substring(4, 6) + "-" + datum.substring(6, 8)));
			BigDecimal bedrag = new BigDecimal(line[6].replace(',', '.'));
			bedrag = bedrag.abs();
			kost.setAmount(bedrag);
			String omschrijving = line[7];

			if (omschrijving.trim().equals("")) {
				kost.setCostType(CostConstants.UNDETERMINED);
				kost.setVat(BigDecimal.ZERO);
			} else {
				omschrijving = omschrijving.replace(" Kenmerk: ", "");
				omschrijving = omschrijving.replace(" BETALINGSKENM. ", "");
				omschrijving = omschrijving.replace(" Omschrijving: ", "");
				omschrijving = omschrijving.replace("BIC: RABONL2U", "");
				omschrijving = omschrijving.replace("BIC: INGBNL2A", "");
				omschrijving = omschrijving.replace("SEPA Overboeking", "");
				omschrijving = omschrijving.replace("IBAN: ", "");
				omschrijving = omschrijving.replace("SEPA Incasso algemeen doorlopend Incassant: ", "");
				omschrijving = omschrijving.replace("SEPA Incasso algemeen doorlopend", "");
				omschrijving = omschrijving.replace("VANWEGE ONVOLDOENDE BESTEDINGSRUIMTE", "");
				omschrijving = omschrijving.replace("zie my.vodafone.nl", "");
				omschrijving = omschrijving.replace("zie my.vodafone.n l", "");

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
		kostLijst = new ArrayList<Cost>();
		kostmatchList = new ArrayList<Kostmatch>();
	}

}
