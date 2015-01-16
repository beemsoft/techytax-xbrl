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
package org.techytax.helper;

import static org.techytax.domain.CostConstants.VAT;
import static org.techytax.domain.CostConstants.ROAD_TAX;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.techytax.domain.Cost;

public class DutchTaxCodeHelper {

	public static Cost convertTaxCode(Cost cost) {
		String description = cost.getDescription();
		int endIndex = description.indexOf("IBAN:");
		if (endIndex == -1) {
			endIndex = description.indexOf("AC");
		}
		if (endIndex != -1) {
			String taxCode = cost.getDescription().substring(description.length() - 16, description.length());
			if (StringUtils.isNumeric(taxCode)) {
				String convertedTaxCode = DutchTaxCodeConverter
						.convertPaymentFeatureToHumanReadableDescription(taxCode);
				String fullDescription = "";
				String taxType = convertedTaxCode.substring(12, 13);
				String yearIndicator = convertedTaxCode.substring(14, 15);
				String currentYear = Integer.toString(Calendar.getInstance()
						.get(Calendar.YEAR));

				if (taxType.equals("B")) {
					cost.setCostType(VAT);
					yearIndicator = convertedTaxCode.substring(17, 18);
					fullDescription = "Omzetbelasting";
				} else if (taxType.equals("W")) {
					fullDescription = "Zorgverzekeringswet";
				} else if (taxType.equals("W")) {
					fullDescription = "Zorgverzekeringswet";
				} else if (taxType.equals("M")) {
					fullDescription = "Motorrijtuigenbelasting";
					cost.setCostType(ROAD_TAX);
				} else if (taxType.equals("O")) {
					fullDescription = "Omzetbelasting teruggaaf";
				} else if (taxType.equals("H")) {
					fullDescription = "Inkomstenbelasting";
				} else if (taxType.equals("F")) {
					fullDescription = "Definitieve IB aanslag";
				}

				String taxYear = currentYear.substring(0, 3) + yearIndicator;
				if (Integer.parseInt(taxYear) > Integer.parseInt(currentYear)) {
					taxYear = Integer
							.toString((Integer.parseInt(taxYear) - 10));
				}
				cost.setDescription(description + " " + convertedTaxCode + " "
						+ fullDescription + " " + taxYear);
			}
		}
		return cost;

	}

}
