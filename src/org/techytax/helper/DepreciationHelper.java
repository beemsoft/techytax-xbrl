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

import static org.techytax.domain.CostConstants.DEPRECIATION_CAR;
import static org.techytax.domain.CostConstants.DEPRECIATION_MACHINE;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.techytax.dao.BookValueDao;
import org.techytax.dao.FiscalDao;
import org.techytax.domain.Activum;
import org.techytax.domain.BalanceType;
import org.techytax.domain.BookValue;
import org.techytax.domain.Cost;
import org.techytax.domain.RemainingValue;
import org.techytax.domain.User;
import org.techytax.jpa.dao.GenericDao;
import org.techytax.zk.login.UserCredentialManager;

public class DepreciationHelper {
	
	private User user = UserCredentialManager.getUser();
	private GenericDao<BookValue> bookValueGenericDao = new GenericDao<BookValue>(BookValue.class, user);

	public BigDecimal getYearlyDepreciation(int nofYears, BigDecimal initialNetAmount, BigInteger remainingValue) {
		BigDecimal yearlyDepreciation = (initialNetAmount.subtract(new BigDecimal(remainingValue))).divide(new BigDecimal(nofYears), 2, RoundingMode.HALF_UP);
		yearlyDepreciation = yearlyDepreciation.setScale(0, BigDecimal.ROUND_UP);
		return yearlyDepreciation;
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
		FiscalDao fiscaalDao = new FiscalDao();
		Integer activumId = fiscaalDao.insertActivum(activum);
		// Add remaining value
		RemainingValue remainingValue = new RemainingValue();
		remainingValue.setUserId(userId);
		remainingValue.setActivaId(activumId);
		remainingValue.setRestwaarde(restWaarde);
		boekwaardeDao.insertRemainingValue(remainingValue);

		// Add or update boekwaarde
		BookValue activumValue = boekwaardeDao.getBookValue(activum.getBalanceType(), bookYear - 1);
		if (activumValue != null) {
			activumValue.setSaldo(activumValue.getSaldo().subtract(yearlyDepreciation.toBigInteger()));
			bookValueGenericDao.merge(activumValue);
		} else {
			activumValue = new BookValue();
			activumValue.setJaar(bookYear);
			activumValue.setBalanceType(activum.getBalanceType());
			activumValue.setUser(user);
			BigDecimal initialNetAmount = cost.getAmount().setScale(0, BigDecimal.ROUND_UP);
			activumValue.setSaldo(initialNetAmount.toBigInteger().subtract(yearlyDepreciation.toBigInteger()));
			bookValueGenericDao.persistEntity(activumValue);
		}
	}

	public List<Cost> getDepreciations(Cost cost, boolean isCar, int nofYears, BigDecimal yearlyDepreciation) throws Exception {
		List<Cost> depreciations = new ArrayList<Cost>();
		Calendar cal = new GregorianCalendar();
		cal.setTime(cost.getDate());
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		for (int i = 0; i < nofYears; i++) {
			Cost depreciation = new Cost();
			depreciation.setVat(new BigDecimal(0));
			if (isCar) {
				depreciation.setCostType(DEPRECIATION_CAR);
			} else {
				depreciation.setCostType(DEPRECIATION_MACHINE);
			}
			depreciation.setDate(cal.getTime());
			depreciation.setAmount(yearlyDepreciation.setScale(2));
			depreciation.setDescription("Afschrijving " + (i + 1) + ", " + cost.getDescription());
			depreciations.add(depreciation);
			cal.add(Calendar.YEAR, 1);
		}
		return depreciations;
	}

}
