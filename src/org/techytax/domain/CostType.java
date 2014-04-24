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

import static org.techytax.domain.CostConstants.SETTLEMENT;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.zkoss.util.resource.Labels;

@Entity(name = "org.techytax.domain.CostType")
@Table(name = "kostensoort")
public class CostType {
	
	@Id
	@Column(name = "id")
	private long kostenSoortId = 0;

	private String omschrijving;

	private boolean bijschrijving;

	private boolean btwVerrekenbaar;

	private boolean balansMeetellen;

	private boolean aftrekbaar;

	private boolean investering;
	
	public CostType() {
		// default constructor required by JPA
	}
	
	public CostType(long id) {
		this.kostenSoortId = id;
	}

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
		return this.equals(SETTLEMENT);
	}

	public void setBijschrijving(boolean bijschrijving) {
		this.bijschrijving = bijschrijving;
	}

	public boolean isVatDeclarable() {
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
		if (omschrijving != null) {
			return Labels.getLabel(omschrijving);
		} else {
			return "Onbekend";
		}
	}

	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof CostType)) {
			return false;
		}
		CostType other = (CostType) object;
		if (this.getKostenSoortId() != other.getKostenSoortId()) {
			return false;
		}
		return true;
	}
}
