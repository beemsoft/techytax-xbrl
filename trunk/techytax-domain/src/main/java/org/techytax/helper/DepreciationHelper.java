/**
 * Copyright 2014 Hans Beemsterboer
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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Date;

import org.techytax.domain.Activum;
import org.techytax.util.DateHelper;

public class DepreciationHelper {

	public BigInteger getDepreciation(Activum activum) {
		BigDecimal yearlyDepreciation = BigDecimal.ZERO;
		int fiscalYear = DateHelper.getFiscalYear();
		int years = activum.getNofYearsForDepreciation();
		if ((years > 0 && activum.getEndDate() == null && (isYearForDeprecation(activum.getStartDate(), years)))) {
			BigDecimal maximumYearlyDepreciation = activum.getCost().getAmount().subtract(BigDecimal.valueOf(activum.getRemainingValue().intValue())).divide(BigDecimal.valueOf(5), 2,
					RoundingMode.HALF_UP);			
			yearlyDepreciation = activum.getCost().getAmount().subtract(BigDecimal.valueOf(activum.getRemainingValue().intValue())).divide(BigDecimal.valueOf(years), 2,
					RoundingMode.HALF_UP);
			double proportion = 1.0d;
			if (fiscalYear == years + DateHelper.getYear(activum.getStartDate())) {
				proportion = DateHelper.getMonth(activum.getStartDate()) / 12.0d;	
			} else if (fiscalYear == DateHelper.getYear(activum.getStartDate())) {
				proportion = 1.0d - DateHelper.getMonth(activum.getStartDate()) / 12.0d;	
			} 
			yearlyDepreciation = yearlyDepreciation.multiply(BigDecimal.valueOf(proportion));
			if (yearlyDepreciation.compareTo(maximumYearlyDepreciation) == 1) {
				yearlyDepreciation = maximumYearlyDepreciation;
			}
		}
		return AmountHelper.roundToInteger(yearlyDepreciation);
	}

	private boolean isYearForDeprecation(Date startDate, int years) {
		if (DateHelper.getMonth(startDate) > 0) {
			years++;
		}
		if (DateHelper.getFiscalYear() >= DateHelper.getYear(startDate) || DateHelper.getFiscalYear() > years + DateHelper.getYear(startDate)) {
			return true;
		}
		return false;
	}

}
