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
package org.techytax.zk.cost;

import java.io.Serializable;
import java.util.Comparator;

import org.techytax.domain.Cost;

public class CostComparator implements Comparator<Object>, Serializable {

	private static final long serialVersionUID = -2127053833562854322L;

	private boolean asc = true;
	private int type = 0;

	public CostComparator(boolean asc, int type) {
		this.asc = asc;
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int compare(Object o1, Object o2) {
		Cost cost1 = (Cost) o1;
		Cost cost2 = (Cost) o2;
		switch (type) {
		case 1: // Compare Date
			return cost1.getDate().compareTo(cost2.getDate()) * (asc ? 1 : -1);
		case 2: // Compare Amount
			return cost1.getAmount().compareTo(cost2.getAmount()) * (asc ? 1 : -1);
		case 3: // Compare VAT
			return cost1.getVat().compareTo(cost2.getVat()) * (asc ? 1 : -1);
		case 4: // Compare Description
			return cost1.getDescription().compareTo(cost2.getDescription()) * (asc ? 1 : -1);
		case 5: // Compare Type
			return cost1.getKostenSoortOmschrijving().compareTo(cost2.getKostenSoortOmschrijving()) * (asc ? 1 : -1);
		default:
			return 0;
		}
	}

}
