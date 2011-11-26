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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.techytax.domain.Kost;

public class InvestmentDeductionHelper {

	public static BigInteger getInvestmentDeduction(List<Kost> costList) {
		BigDecimal totalInvestment = new BigDecimal("0");
		for (Kost cost : costList) {
			totalInvestment = totalInvestment.add(cost.getBedrag());
		}
		return calculateInvestmentDeduction(totalInvestment);
	}

	private static BigInteger calculateInvestmentDeduction(BigDecimal totalInvestment) {
		BigInteger totalInvestmentRounded = totalInvestment.setScale(0, BigDecimal.ROUND_UP).toBigInteger();
		if (totalInvestmentRounded.compareTo(new BigInteger("2200")) == -1 || totalInvestmentRounded.compareTo(new BigInteger("301800")) == 1) {
			return new BigInteger("0");
		}
		if (totalInvestmentRounded.compareTo(new BigInteger("2201")) == 1 && totalInvestmentRounded.compareTo(new BigInteger("54324")) == -1) {
			return new BigDecimal(totalInvestmentRounded).multiply(new BigDecimal(".28")).setScale(0, BigDecimal.ROUND_UP).toBigInteger();
		}
		if (totalInvestmentRounded.compareTo(new BigInteger("54325")) == 1 && totalInvestmentRounded.compareTo(new BigInteger("100600")) == -1) {
			return new BigInteger("15211");
		}
		if (totalInvestmentRounded.compareTo(new BigInteger("100601")) == 1 && totalInvestmentRounded.compareTo(new BigInteger("301800")) == -1) {
			return new BigInteger("15211").subtract(new BigDecimal(totalInvestmentRounded.subtract(new BigInteger("100600"))).multiply(new BigDecimal(".0756")).setScale(0, BigDecimal.ROUND_UP)
					.toBigInteger());
		}
		return null;
	}

}
