/**
 * Copyright 2012 Hans Beemsterboer
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
package org.techytax.domain;

import java.util.Date;

import org.techytax.util.DateHelper;

public enum VatType {
	NONE("vat.none", 0), LOW("vat.low", 0.06d), HIGH("vat.high", 0.21d), HIGH_OLD("vat.high", 0.19d);

	private static final String changeDateForHighVat = "2012-10-01";
	private String key;
	private double value;

	private VatType(String key, double value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}
	
	public double getValue(Date payDate) throws Exception {
		if (this == VatType.HIGH && payDate != null && DateHelper.stringToDate(changeDateForHighVat).compareTo(payDate) > 0) {
			return VatType.HIGH_OLD.value;
		}
		return value;
	}
	
	public int getValueAsInteger(Date payDate) throws Exception {
		return (int) (100 * getValue(payDate));
	}
	
	public static VatType getInstance(String type) {
		VatType balanceType;
		switch (Integer.parseInt(type)) {
		case 0:
			balanceType = NONE;
			break;
		case 1:
			balanceType = LOW;
			break;
		case 2:
			balanceType = HIGH;
			break;
		default:
			balanceType = NONE;
			break;
		}
		return balanceType;
	}
	
	@Override
	public String toString() {
		return name();
	}
}
