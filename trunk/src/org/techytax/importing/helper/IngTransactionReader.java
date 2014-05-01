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

import static org.techytax.domain.CostConstants.UNDETERMINED;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Vector;

import org.techytax.domain.Cost;
import org.techytax.domain.Kostmatch;
import org.techytax.helper.DutchTaxCodeHelper;
import org.techytax.util.DateHelper;

import com.Ostermiller.util.CSVParser;
import com.Ostermiller.util.LabeledCSVParser;

public class IngTransactionReader extends BaseTransactionReader {

	private static LabeledCSVParser parser = null;
	
	public List<Cost> readFile(BufferedReader in, String userId) throws NumberFormatException, Exception {
		try {
			parser = new LabeledCSVParser(new CSVParser(in));
			System.out.println(parser.getLabels()[0]);
			verwerkRecords();

			Vector<String[]> data = getRegels();
			for (int regelNummer = 1; regelNummer <= data.size(); regelNummer++) {
				String[] regel = (String[]) data.get(regelNummer - 1);
				processLine(regel, regelNummer, userId);
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

	private void processLine(String[] line, int lineNumber, String userId) {
		Cost kost = new Cost();
		Kostmatch costMatch = null;
		try {
			String datum = line[0];
			if (datum.contains("-")) {
				kost.setDate(DateHelper.stringToDateForIng(datum));
			} else {
				kost.setDate(DateHelper.stringToDate(datum.substring(0, 4) + "-" + datum.substring(4, 6) + "-" + datum.substring(6, 8)));
			}
			BigDecimal bedrag = new BigDecimal(line[6].replace(',', '.'));
			kost.setAmount(bedrag);
			String omschrijving = line[1] + " " + line[8];
			if (omschrijving.trim().equals("")) {
				kost.setCostType(UNDETERMINED);
				kost.setVat(BigDecimal.ZERO);
			} else {
				omschrijving = omschrijving.replace("SEPA Incasso, eersteIBAN:", "");
				omschrijving = omschrijving.replace("SEPA Incasso, eerste IBAN:", "");
				omschrijving = omschrijving.replace("SEPA Incasso, doorlopendIBAN:", "");
				omschrijving = omschrijving.replace("SEPA Incasso, doorlopend IBAN:", "");
				int index = omschrijving.indexOf("Mandaat:");
				if (index > 0) {
					omschrijving = omschrijving.substring(0, index);
				}
				kost.setDescription(omschrijving);
				if (omschrijving.contains("BELASTINGDIENST")) {
					DutchTaxCodeHelper.convertTaxCode(kost);
				}
				costMatch = matchKost(kost, userId);
			}
			addCostOrHandleAdminstrativeSplitting(userId, kost, costMatch);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws NumberFormatException, Exception {
		FileInputStream fis = new FileInputStream("test.bat");
		BaseTransactionReader rekeningFileHelper = new IngTransactionReader();
		List<Cost> result = rekeningFileHelper.readFile(new BufferedReader(new InputStreamReader(fis)),  "1");
	}

}
