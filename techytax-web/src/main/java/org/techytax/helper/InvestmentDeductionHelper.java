/**
 * Copyright 2016 Hans Beemsterboer
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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.techytax.dao.ActivumDao;
import org.techytax.domain.Activum;
import org.techytax.domain.BalanceType;
import org.techytax.domain.Cost;
import org.techytax.zk.login.UserCredentialManager;

@Component
public class InvestmentDeductionHelper {

	@Autowired
	private ActivumDao activumDao;

	public BigInteger getInvestmentDeduction(List<Cost> costList) throws Exception {
		BigInteger totalInvestmentDeduction = BigInteger.ZERO;
		for (Cost cost : costList) {
			Activum activum = new Activum();
			activum.setUser(UserCredentialManager.getUser());
			activum.setCost(cost);
			activum = activumDao.getActivumForCost(cost);
			if (activum != null && activum.getBalanceType() == BalanceType.MACHINERY) {
				totalInvestmentDeduction = totalInvestmentDeduction.add(calculateInvestmentDeduction(cost.getAmount()));
			}
		}
		return totalInvestmentDeduction;
	}

	private BigInteger calculateInvestmentDeduction(BigDecimal totalInvestment) {
		BigInteger totalInvestmentRounded = totalInvestment.setScale(0, BigDecimal.ROUND_UP).toBigInteger();
		if (totalInvestmentRounded.compareTo(BigInteger.valueOf(2300)) == -1 || totalInvestmentRounded.compareTo(BigInteger.valueOf(309693)) == 1) {
			return BigInteger.ZERO;
		}
		if (totalInvestmentRounded.compareTo(BigInteger.valueOf(2301)) == 1 && totalInvestmentRounded.compareTo(BigInteger.valueOf(55745)) == -1) {
			return new BigDecimal(totalInvestmentRounded).multiply(BigDecimal.valueOf(.28)).setScale(0, BigDecimal.ROUND_UP).toBigInteger();
		}
		if (totalInvestmentRounded.compareTo(BigInteger.valueOf(55746)) == 1 && totalInvestmentRounded.compareTo(BigInteger.valueOf(103231)) == -1) {
			return BigInteger.valueOf(15609);
		}
		if (totalInvestmentRounded.compareTo(BigInteger.valueOf(103232)) == 1 && totalInvestmentRounded.compareTo(BigInteger.valueOf(309693)) == -1) {
			return BigInteger.valueOf(15609).subtract(new BigDecimal(totalInvestmentRounded.subtract(BigInteger.valueOf(103231))).multiply(BigDecimal.valueOf(.0756)).setScale(0, BigDecimal.ROUND_UP)
					.toBigInteger());
		}
		return null;
	}

}
