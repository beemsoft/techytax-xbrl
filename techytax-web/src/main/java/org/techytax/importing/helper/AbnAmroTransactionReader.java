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
import java.util.List;
import java.util.Vector;

import org.techytax.domain.Cost;
import org.techytax.domain.CostConstants;
import org.techytax.domain.CostMatchParent;
import org.techytax.helper.DutchTaxCodeHelper;
import org.techytax.util.DateHelper;

import com.Ostermiller.util.CSVParser;

public class AbnAmroTransactionReader extends BaseTransactionReader {

	public AbnAmroTransactionReader() throws IllegalAccessException {
		super();
	}

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

	public static void main(String[] args) throws NumberFormatException, Exception {
		FileInputStream fis = new FileInputStream("test.bat");
		AbnAmroTransactionReader rekeningFileAbnAmroHelper = new AbnAmroTransactionReader();
		List<Cost> result = rekeningFileAbnAmroHelper.readFile(new BufferedReader(new InputStreamReader(fis)));
	}

}
