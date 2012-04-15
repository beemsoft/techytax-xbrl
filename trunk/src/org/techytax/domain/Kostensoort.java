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

public class Kostensoort {
	private long kostenSoortId = 0;

	private String omschrijving;

	private boolean bijschrijving;

	private boolean btwVerrekenbaar;

	private boolean balansMeetellen;

	private boolean aftrekbaar;

	private boolean investering;

	public boolean isInvestering() {
		return investering;
	}

	public void setInvestering(boolean investering) {
		this.investering = investering;
	}

	public boolean isAftrekbaar() {
		return aftrekbaar;
	}

	public void setAftrekbaar(boolean aftrekbaar) {
		this.aftrekbaar = aftrekbaar;
	}

	public boolean isBalansMeetellen() {
		return balansMeetellen;
	}

	public void setBalansMeetellen(boolean balansMeetellen) {
		this.balansMeetellen = balansMeetellen;
	}

	public boolean isBijschrijving() {
		return bijschrijving;
	}
	
	public boolean isForSettlement() {
		return kostenSoortId == KostConstanten.SETTLEMENT;
	}

	public void setBijschrijving(boolean bijschrijving) {
		this.bijschrijving = bijschrijving;
	}

	public boolean isBtwVerrekenbaar() {
		return btwVerrekenbaar;
	}

	public void setBtwVerrekenbaar(boolean btwVerrekenbaar) {
		this.btwVerrekenbaar = btwVerrekenbaar;
	}

	public long getKostenSoortId() {
		return kostenSoortId;
	}

	public void setKostenSoortId(long kostenSoortId) {
		this.kostenSoortId = kostenSoortId;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}
}
