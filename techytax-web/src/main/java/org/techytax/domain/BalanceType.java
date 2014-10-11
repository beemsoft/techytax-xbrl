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
package org.techytax.domain;

import org.zkoss.util.resource.Labels;

public enum BalanceType {
	NONE("balance.type.none", null), MACHINERY("balance.type.machines", true), CAR("balance.type.car", true), CURRENT_ASSETS(
			"balance.type.liquid.assets", true), NON_CURRENT_ASSETS(
			"balance.type.equity", false), PENSION("balance.type.pension", false), STOCK(
			"balance.type.stock", true), OFFICE("balance.type.settlement", true), VAT_TO_BE_PAID(
			"balance.type.debt.sales.tax", false), INVOICES_TO_BE_PAID(
			"balance.type.loans.customers", true);

	private String key;
	
	private Boolean isActivum;

	private BalanceType(String key, Boolean isActivum) {
		this.key = key;
		this.isActivum = isActivum;
	}

	public String getKey() {
		return Labels.getLabel(key);
	}
	
	public Boolean isActivum() {
		return isActivum;
	}

	public static BalanceType getInstance(int type) {
		BalanceType balanceType;
		switch (type) {
		case 1:
			balanceType = MACHINERY;
			break;
		case 2:
			balanceType = CAR;
			break;
		case 3:
			balanceType = CURRENT_ASSETS;
			break;
		case 4:
			balanceType = NON_CURRENT_ASSETS;
			break;
		case 5:
			balanceType = PENSION;
			break;
		case 6:
			balanceType = STOCK;
			break;
		case 7:
			balanceType = OFFICE;
			break;
		case 8:
			balanceType = VAT_TO_BE_PAID;
			break;
		case 9:
			balanceType = INVOICES_TO_BE_PAID;
			break;
		default:
			balanceType = null;
			break;
		}
		return balanceType;
	}
}
