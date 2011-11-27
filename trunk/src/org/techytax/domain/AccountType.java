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
package org.techytax.domain;

public enum AccountType {
	PRIVATE("account.type.private"), BUSINESS("account.type.business"), SAVINGS("account.type.savings"), MORTGAGE_LOAN("account.type.mortgage.loan"), MORTGAGE_SAVINGS("account.type.mortgage.savings");

	private String key;

	private AccountType(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public static AccountType getInstance(int type) {
		AccountType accountType;
		switch (type) {
		case 0:
			accountType = PRIVATE;
			break;
		case 1:
			accountType = BUSINESS;
			break;
		case 2:
			accountType = SAVINGS;
			break;
		case 3:
			accountType = MORTGAGE_LOAN;
			break;
		case 4:
			accountType = MORTGAGE_SAVINGS;
			break;	
		default:
			accountType = null;
			break;
		}
		return accountType;
	}
}
