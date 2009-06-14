/**
 * Copyright 2009 Hans Beemsterboer
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

public class PriveOnttrekking {
	private int balansSaldo; // Current Asset
	private int gat;		// Extra private extraction
	private int opnameSaldo;

	private int teruggave;

	private int totalCost;

	private int totaleOnttrekking;

	private int voorlopigeAanslag;
	public int getBalansSaldo() {
		return balansSaldo;
	}
	public int getGat() {
		return gat;
	}
	public int getOpnameSaldo() {
		return opnameSaldo;
	}
	public int getTeruggave() {
		return teruggave;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public int getTotaleOnttrekking() {
		return totaleOnttrekking;
	}

	public int getVoorlopigeAanslag() {
		return voorlopigeAanslag;
	}

	public void setBalansSaldo(int balansSaldo) {
		this.balansSaldo = balansSaldo;
	}

	public void setGat(int gat) {
		this.gat = gat;
	}

	public void setOpnameSaldo(int opnameSaldo) {
		this.opnameSaldo = opnameSaldo;
	}

	public void setTeruggave(int teruggave) {
		this.teruggave = teruggave;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public void setTotaleOnttrekking(int totaleOnttrekking) {
		this.totaleOnttrekking = totaleOnttrekking;
	}

	public void setVoorlopigeAanslag(int voorlopigeAanslag) {
		this.voorlopigeAanslag = voorlopigeAanslag;
	}

}
