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

public enum BalanceType {
	NONE, MACHINERY, CAR, CURRENT_ASSETS, NON_CURRENT_ASSETS, PENSION, STOCK, OFFICE, VAT_TO_BE_PAID, INVOICES_TO_BE_PAID;
	
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
			balanceType = NONE;
			break;
		}
		return balanceType;
	}	
}
