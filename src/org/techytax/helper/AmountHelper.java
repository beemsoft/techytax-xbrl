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

import org.techytax.domain.Cost;
import org.techytax.domain.VatType;

public class AmountHelper {

	public static void applyHighVat(Cost cost) throws Exception {
		BigDecimal amount = cost.getAmount();
		if (amount != null) {
			BigDecimal bd = new BigDecimal(amount.doubleValue()/(1 + VatType.HIGH.getValue(cost.getDate())));
			bd = round(bd);
			cost.setAmount(bd);
			cost.setVat(amount.subtract(bd));
		}
	}
	
	public static BigDecimal round(BigDecimal amount) {
		if (amount != null) {
			return amount.setScale(2,RoundingMode.HALF_UP);
		} else {
			return null;
		}
	}
	
	public static BigInteger roundToInteger(BigDecimal amount) {
		if (amount != null) {
			return amount.setScale(0,RoundingMode.HALF_UP).toBigInteger();
		} else {
			return null;
		}
	}	
	
	public static void main(String[] args) {
		System.out.println(roundToInteger(new BigDecimal("1.55")));
		System.out.println(roundToInteger(new BigDecimal("1.45")));		
	}
}
