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
import java.util.List;

import org.techytax.dao.FiscalDao;
import org.techytax.domain.Activum;
import org.techytax.domain.BalanceType;
import org.techytax.domain.Cost;
import org.techytax.domain.User;
import org.techytax.zk.login.UserCredentialManager;

public class InvestmentDeductionHelper {
	
	private User user = UserCredentialManager.getUser();

	public BigInteger getInvestmentDeduction(List<Cost> costList) throws Exception {
		BigInteger totalInvestmentDeduction = BigInteger.ZERO;
		FiscalDao fiscaalDao = new FiscalDao();
		for (Cost cost : costList) {
			Activum activum = new Activum();
			activum.setUserId(user.getId());
			activum.setCostId(cost.getId());
			activum = fiscaalDao.getActivumByCostId(activum);
			if (activum != null && activum.getBalanceType() == BalanceType.MACHINERY) {
				totalInvestmentDeduction = totalInvestmentDeduction.add(calculateInvestmentDeduction(cost.getAmount()));
			}
		}
		return totalInvestmentDeduction;
	}

	private static BigInteger calculateInvestmentDeduction(BigDecimal totalInvestment) {
		BigInteger totalInvestmentRounded = totalInvestment.setScale(0, BigDecimal.ROUND_UP).toBigInteger();
		if (totalInvestmentRounded.compareTo(new BigInteger("2300")) == -1 || totalInvestmentRounded.compareTo(new BigInteger("306931")) == 1) {
			return new BigInteger("0");
		}
		if (totalInvestmentRounded.compareTo(new BigInteger("2301")) == 1 && totalInvestmentRounded.compareTo(new BigInteger("55248")) == -1) {
			return new BigDecimal(totalInvestmentRounded).multiply(new BigDecimal(".28")).setScale(0, BigDecimal.ROUND_UP).toBigInteger();
		}
		if (totalInvestmentRounded.compareTo(new BigInteger("55249")) == 1 && totalInvestmentRounded.compareTo(new BigInteger("102311")) == -1) {
			return new BigInteger("15470");
		}
		if (totalInvestmentRounded.compareTo(new BigInteger("102312")) == 1 && totalInvestmentRounded.compareTo(new BigInteger("306931")) == -1) {
			return new BigInteger("15470").subtract(new BigDecimal(totalInvestmentRounded.subtract(new BigInteger("102311"))).multiply(new BigDecimal(".0756")).setScale(0, BigDecimal.ROUND_UP)
					.toBigInteger());
		}
		return null;
	}

}
