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

import org.techytax.dao.BookValueDao;
import org.techytax.domain.Activum;
import org.techytax.domain.BalanceType;
import org.techytax.domain.BookValue;
import org.techytax.domain.Cost;
import org.techytax.domain.RemainingValue;
import org.techytax.domain.User;
import org.techytax.jpa.dao.GenericDao;
import org.techytax.util.DateHelper;
import org.techytax.zk.login.UserCredentialManager;

public class DepreciationHelper {

	private User user = UserCredentialManager.getUser();
	private GenericDao<BookValue> bookValueGenericDao = new GenericDao<BookValue>(BookValue.class, user);
	private GenericDao<Activum> activumGenericDao = new GenericDao<Activum>(Activum.class, user);

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

	public void putOnBalance(Cost cost, boolean isCar, long userId, BigInteger restWaarde, BigDecimal yearlyDepreciation, int bookYear) throws Exception {
		Activum activum = new Activum();
		activum.setUser(user);
		activum.setCost(cost);
		BookValueDao boekwaardeDao = new BookValueDao();
		if (!isCar) {
			activum.setBalanceType(BalanceType.MACHINERY);
		} else {
			activum.setBalanceType(BalanceType.CAR);
		}
		RemainingValue remainingValue = new RemainingValue();
		remainingValue.setActivum(activum);
		remainingValue.setRestwaarde(restWaarde);
		activumGenericDao.persistEntity(activum);

		BookValue previousBookValue = boekwaardeDao.getBookValue(activum.getBalanceType(), bookYear - 1);
		if (previousBookValue != null) {
			previousBookValue.setSaldo(previousBookValue.getSaldo().subtract(yearlyDepreciation.toBigInteger()));
		} else {
			BigDecimal initialNetAmount = cost.getAmount().setScale(0, BigDecimal.ROUND_UP);
			BookValue newBookValue = new BookValue(0, activum.getBalanceType(), bookYear, initialNetAmount.toBigInteger().subtract(yearlyDepreciation.toBigInteger()));
			newBookValue.setUser(user);
			bookValueGenericDao.persistEntity(newBookValue);
		}
	}

}
