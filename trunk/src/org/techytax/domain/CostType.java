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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;
import org.zkoss.util.resource.Labels;

@Immutable
@Entity
@NamedQueries({ @NamedQuery(name = CostType.FOR_MATCHING, query = "SELECT ct FROM CostType ct WHERE ct.balansMeetellen = true OR ct IN :costTypes"),
		@NamedQuery(name = CostType.FOR_TYPES, query = "SELECT ct FROM CostType ct WHERE ct IN :costTypes") })
@Table(name = "kostensoort")
public class CostType {

	public static final String FOR_MATCHING = "org.techytax.domain.CostType.FOR_MATCHING";
	public static final String FOR_TYPES = "org.techytax.domain.CostType.FOR_TYPES";

	@Id
	private long id = 0;

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
		this.id = id;
	}

	public boolean isInvestering() {
		return investering;
	}

	public boolean isAftrekbaar() {
		return aftrekbaar;
	}

	public boolean isBalansMeetellen() {
		return balansMeetellen;
	}

	public boolean isBijschrijving() {
		return bijschrijving;
	}

	public boolean isForSettlement() {
		return this.equals(SETTLEMENT);
	}

	public boolean isVatDeclarable() {
		return btwVerrekenbaar;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOmschrijving() {
		if (omschrijving != null) {
			return Labels.getLabel(omschrijving);
		} else {
			return "Onbekend";
		}
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof CostType)) {
			return false;
		}
		CostType other = (CostType) object;
		if (this.getId() != other.getId()) {
			return false;
		}
		return true;
	}
}
