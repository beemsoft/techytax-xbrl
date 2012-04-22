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

public enum VatType {
	NONE("vat.none"), LOW("vat.low"), HIGH("vat.high");

	private String key;

	private VatType(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
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
}
