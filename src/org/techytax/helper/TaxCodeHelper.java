/**
 * Copyright 2011 Hans Beemsterboer
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

import java.util.Calendar;
import java.util.List;

import org.techytax.dao.BoekDao;
import org.techytax.domain.Cost;
import org.techytax.domain.KostConstanten;
import org.techytax.domain.Periode;
import org.techytax.domain.PrepaidTax;
import org.techytax.util.DateHelper;

public class TaxCodeHelper {

	public static Cost convertTaxCode(Cost cost) {
		String description = cost.getDescription();
		int endIndex = description.indexOf("AC");
		if (endIndex != -1) {
			String taxCode = cost.getDescription().substring(endIndex - 16, endIndex);
			String convertedTaxCode = DutchTaxCodeConverter.convert(taxCode);
			String fullDescription = "";
			String taxType = convertedTaxCode.substring(12, 13);
			String yearIndicator = convertedTaxCode.substring(14, 15);
			String currentYear = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));

			if (taxType.equals("B")) {
				cost.setCostTypeId(KostConstanten.OMZET_BELASTING);
				yearIndicator = convertedTaxCode.substring(17, 18);
				fullDescription = "Omzetbelasting";
			} else if (taxType.equals("W")) {
				fullDescription = "Zorgverzekeringswet";
			} else if (taxType.equals("W")) {
				fullDescription = "Zorgverzekeringswet";
			} else if (taxType.equals("M")) {
				fullDescription = "Motorrijtuigenbelasting";
				cost.setCostTypeId(KostConstanten.WEGEN_BELASTING);
			} else if (taxType.equals("O")) {
				fullDescription = "Omzetbelasting teruggaaf";
			} else if (taxType.equals("H")) {
				fullDescription = "Inkomstenbelasting";
			}

			String taxYear = currentYear.substring(0, 3) + yearIndicator;
			if (Integer.parseInt(taxYear) > Integer.parseInt(currentYear)) {
				taxYear = Integer.toString((Integer.parseInt(taxYear) - 10));
			}
			cost.setDescription(description + " " + convertedTaxCode + " " + fullDescription + " " + taxYear);
		}
		return cost;

	}

	public static PrepaidTax findPrepaidTax(int year, String userId) {
		PrepaidTax prepaidTax = new PrepaidTax();
		Periode period = DateHelper.getPeriodPreviousYearThisYear(year);
		BoekDao boekDao = new BoekDao();
		try {
			List<Cost> taxList = boekDao.getTaxList(DateHelper.getDate(period.getBeginDatum()), DateHelper.getDate(period.getEindDatum()), userId);
			int prepaidIncomeTax = 0;
			int prepaidHealthTax = 0;
			for (Cost tax : taxList) {
				if (tax.getDescription().contains("Inkomstenbelasting " + year)) {
					prepaidIncomeTax += tax.getAmount().intValue();
				}
				if (tax.getDescription().contains("Zorgverzekeringswet " + year)) {
					prepaidHealthTax += tax.getAmount().intValue();
				}
			}
			prepaidTax.setPrepaidHealth(prepaidHealthTax);
			prepaidTax.setPrepaidIncome(prepaidIncomeTax);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prepaidTax;
	}

	public static void main(String[] args) {
		Cost cost = new Cost();
		cost.setDescription("AC");
		System.out.println(convertTaxCode(cost).getDescription());
	}

}
