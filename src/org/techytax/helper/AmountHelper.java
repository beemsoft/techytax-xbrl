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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.techytax.domain.Cost;
import org.techytax.domain.VatType;

public class AmountHelper {

	public static void applyHighVat(Cost cost) throws Exception {
		applyVat(cost, VatType.HIGH);
	}

	public static void applyLowVat(Cost cost) throws Exception {
		applyVat(cost, VatType.LOW);
	}

	private static void applyVat(Cost cost, VatType vatType) throws Exception {
		BigDecimal amount = cost.getAmount();
		if (amount != null) {
			BigDecimal bd = new BigDecimal(amount.doubleValue()/(1 + vatType.getValue(cost.getDate())));
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
	
	public static BigInteger roundDownToInteger(BigDecimal amount) {
		if (amount != null) {
			return amount.setScale(0,RoundingMode.DOWN).toBigInteger();
		} else {
			return null;
		}
	}	
	
	public static String formatDecimal(BigDecimal b) {
		if (b == null) {
			return null;
		}
		Locale loc = new Locale("nl", "NL", "EURO");
		NumberFormat n = NumberFormat.getCurrencyInstance(loc);
		double doublePayment = b.doubleValue();
		String s = n.format(doublePayment);
		return s;
	}	
	
	public static String formatDecimal(BigInteger b) {

		Locale loc = new Locale("nl", "NL", "EURO");
		NumberFormat n = NumberFormat.getCurrencyInstance(loc);
		double doublePayment = b.doubleValue();
		n.setMaximumFractionDigits(0);
		String s = n.format(doublePayment);
		return s;
	}
	
	public static String formatWithEuroSymbol(BigInteger amount) {
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.GERMAN);
		otherSymbols.setDecimalSeparator(',');
		otherSymbols.setGroupingSeparator('.');
		DecimalFormat df = new DecimalFormat("€ ###,###,###,##0", otherSymbols);
		return df.format(amount.doubleValue());
	}
	
	public static String formatWithEuroSymbol(BigDecimal amount) {
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.GERMAN);
		otherSymbols.setDecimalSeparator(',');
		otherSymbols.setGroupingSeparator('.');
		DecimalFormat df = new DecimalFormat("€ ###,###,###,##0.00", otherSymbols);
		return df.format(amount.doubleValue());
	}	
	
	public static String format(int amount) {
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.GERMAN);
		otherSymbols.setDecimalSeparator(',');
		otherSymbols.setGroupingSeparator('.');
		DecimalFormat df = new DecimalFormat("###,###,###,##0", otherSymbols);
		return df.format(amount);
	}
	
	public static String format(BigInteger amount) {
		if (amount == null) {
			return null;
		}
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.GERMAN);
		otherSymbols.setDecimalSeparator(',');
		otherSymbols.setGroupingSeparator('.');
		DecimalFormat df = new DecimalFormat("###,###,###,##0", otherSymbols);
		return df.format(amount);
	}
	
	public static BigInteger parse(String amount) throws ParseException {
		if (StringUtils.isEmpty(amount)) {
			return null;
		}
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.GERMAN);
		otherSymbols.setDecimalSeparator(',');
		otherSymbols.setGroupingSeparator('.');
		DecimalFormat df = new DecimalFormat("###,###,###,##0", otherSymbols);
		return BigInteger.valueOf(df.parse(amount).intValue());
	}
	
	public static void main(String[] args) {
		System.out.println(roundToInteger(new BigDecimal("1.55")));
		System.out.println(roundToInteger(new BigDecimal("1.45")));
		System.out.println(format(1000));
	}
}
