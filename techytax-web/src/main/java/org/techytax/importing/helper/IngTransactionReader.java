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

import static org.techytax.domain.CostConstants.UNDETERMINED;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.springframework.stereotype.Component;
import org.techytax.domain.Cost;
import org.techytax.domain.CostMatchParent;
import org.techytax.domain.Kostmatch;
import org.techytax.helper.DutchTaxCodeHelper;
import org.techytax.util.DateHelper;

import com.Ostermiller.util.CSVParser;
import com.Ostermiller.util.LabeledCSVParser;

@Component
public class IngTransactionReader extends BaseTransactionReader {

	private static LabeledCSVParser parser = null;
	
	public List<Cost> readFile(BufferedReader in) throws NumberFormatException, Exception {
		try {
			parser = new LabeledCSVParser(new CSVParser(in));
			System.out.println(parser.getLabels()[0]);
			verwerkRecords();

			Vector<String[]> data = getRegels();
			for (int regelNummer = 1; regelNummer <= data.size(); regelNummer++) {
				String[] regel = (String[]) data.get(regelNummer - 1);
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
			String datum = line[0];
			if (datum.contains("-")) {
				kost.setDate(DateHelper.stringToDateForIng(datum));
			} else {
				kost.setDate(DateHelper.stringToDate(datum.substring(0, 4) + "-" + datum.substring(4, 6) + "-" + datum.substring(6, 8)));
			}
			BigDecimal bedrag = new BigDecimal(line[6].replace(',', '.'));
			kost.setAmount(bedrag);
			String omschrijving = null;
			if (line[8].contains(line[1])) {
				omschrijving = line[8];
			} else {
				omschrijving = line[1] + " " + line[8];
			}
			if (omschrijving.trim().equals("")) {
				kost.setCostType(UNDETERMINED);
				kost.setVat(BigDecimal.ZERO);
			} else {
				omschrijving = omschrijving.replace("SEPA Incasso, eersteIBAN:", "");
				omschrijving = omschrijving.replace("SEPA Incasso, eerste IBAN:", "");
				omschrijving = omschrijving.replace("SEPA Incasso, doorlopendIBAN:", "");
				omschrijving = omschrijving.replace("SEPA Incasso, doorlopend IBAN:", "");
				omschrijving = omschrijving.replace("Europese Incasso, doorlopend IBAN:", "");
				omschrijving = omschrijving.replace("SEPA ID machtiging:", "");
				omschrijving = omschrijving.replace("ID begunstigde:", "");
				omschrijving = omschrijving.replace("TLS BV inzake", "");
				omschrijving = omschrijving.replace("Omschrijving:", "");
				omschrijving = omschrijving.replace("Omschr ijving:", "");
				omschrijving = omschrijving.replace("Omschrijv ing  Klantnummer", "");
				omschrijving = omschrijving.replace("Kenmerk:", "");
				omschrijving = omschrijving.replace("Kenmerk", "");	
				omschrijving = omschrijving.replace("Naam:", "");
				omschrijving = omschrijving.replace("MEER INFO WWW.BELASTINGDIENST.NL", "");
				omschrijving = omschrijving.replace("AEGON NEDERLAND NV", "");
				int lastIndex = omschrijving.lastIndexOf("TUSSENPERS.HYPOTH EKER UTRECHT LEIDSCHE RIJN");
				if (lastIndex > 0) {
					omschrijving = omschrijving.substring(0, lastIndex);
				}
				
				int index = omschrijving.indexOf("Mandaat:");
				if (index > 0) {
					omschrijving = omschrijving.substring(0, index);
				}
				
				index = omschrijving.indexOf("OV-Chipkaart");
				if (index > 0) {
					omschrijving = "OV-Chipkaart";
				}
				
				index = omschrijving.indexOf("BIC:");
				if (index > 0) {
					omschrijving = omschrijving.substring(0, index) + omschrijving.substring(index + 13, omschrijving.length());
				}
				
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
