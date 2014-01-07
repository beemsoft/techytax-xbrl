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
package org.techytax.helper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.techytax.dao.CostDao;
import org.techytax.dao.BookValueDao;
import org.techytax.dao.FiscalDao;
import org.techytax.domain.Activum;
import org.techytax.domain.BalanceType;
import org.techytax.domain.BookValue;
import org.techytax.domain.Cost;
import org.techytax.domain.CostConstants;
import org.techytax.domain.RemainingValue;
import org.techytax.util.DateHelper;
import org.zkoss.util.resource.Labels;

public class DepreciationHelper {

	private CostDao costDao = new CostDao();

	public void splitCost(Cost cost, boolean isCar, int nofYears, long userId, int remainingValuePercentage) throws Exception {
		BigDecimal initialNetAmount = cost.getAmount();
		BigInteger remainingValue = null;
		if (remainingValuePercentage == 0) {
			remainingValue = new BigInteger("0");
		} else {
			remainingValue = initialNetAmount.divide(new BigDecimal(remainingValuePercentage)).toBigInteger();
		}
		BigDecimal yearlyDepreciation = getYearlyDepreciation(nofYears, initialNetAmount, remainingValue);

		List<Cost> depreciations = getDepreciations(cost, isCar, nofYears, yearlyDepreciation);
		storeDepreciations(depreciations, userId);
		putOnBalance(cost, isCar, userId, remainingValue, yearlyDepreciation, DateHelper.getYear(cost.getDate()));
	}

	public BigDecimal getYearlyDepreciation(int nofYears, BigDecimal initialNetAmount, BigInteger remainingValue) {
		BigDecimal yearlyDepreciation = (initialNetAmount.subtract(new BigDecimal(remainingValue))).divide(new BigDecimal(nofYears), 2,
				RoundingMode.HALF_UP);
		yearlyDepreciation = yearlyDepreciation.setScale(0, BigDecimal.ROUND_UP);
		return yearlyDepreciation;
	}

	private void putOnBalance(Cost cost, boolean isCar, long userId, BigInteger restWaarde, BigDecimal yearlyDepreciation, int bookYear)
			throws Exception {
		Activum activum = new Activum();
		activum.setUserId(userId);
		activum.setCostId(cost.getId());
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
		BookValue activumValue = new BookValue();
		activumValue.setJaar(bookYear);
		activumValue.setBalanceType(activum.getBalanceType());
		activumValue.setUserId(userId);
		activumValue = boekwaardeDao.getPreviousBookValue(activumValue);
		if (activumValue != null) {
			activumValue.setSaldo(activumValue.getSaldo().subtract(yearlyDepreciation.toBigInteger()));
			boekwaardeDao.updateBookValue(activumValue);
		} else {
			activumValue = new BookValue();
			activumValue.setJaar(bookYear);
			activumValue.setBalanceType(activum.getBalanceType());
			activumValue.setUserId(userId);
			BigDecimal initialNetAmount = cost.getAmount().setScale(0, BigDecimal.ROUND_UP);
			activumValue.setSaldo(initialNetAmount.toBigInteger().subtract(yearlyDepreciation.toBigInteger()));
			boekwaardeDao.insertBookValue(activumValue);
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
				depreciation.setCostTypeId(CostConstants.DEPRECIATION_CAR);
				depreciation.setKostenSoortOmschrijving(Labels.getLabel("costtype.business.car.depreciation"));
			} else {
				depreciation.setCostTypeId(CostConstants.DEPRECIATION_MACHINE);
				depreciation.setKostenSoortOmschrijving(Labels.getLabel("costtype.depreciation"));
			}
			depreciation.setDate(cal.getTime());
			depreciation.setAmount(yearlyDepreciation.setScale(2));
			depreciation.setDescription("Afschrijving " + (i + 1) + ", " + cost.getDescription());
			depreciations.add(depreciation);
			cal.add(Calendar.YEAR, 1);
		}
		return depreciations;
	}

	public void storeDepreciations(List<Cost> depreciations, long userId) throws Exception {
		for (Cost depreciation : depreciations) {
			depreciation.setUserId(userId);
			costDao.insertKost(depreciation);
		}
	}

}
