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

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public class CostMatchParent {
	
	@Id
	private long id = 0;

	@ManyToOne
	@JoinColumn(name="kostensoort_id")
	protected CostType costType;

	@OneToOne(mappedBy = "publicCostMatch", cascade = CascadeType.ALL)
	private VatMatch vatMatch;

	public long getId() {
		return id;
	}
	
	public VatMatch getVatMatch() {
		return vatMatch;
	}

	public void setVatMatch(VatMatch vatMatch) {
		this.vatMatch = vatMatch;
	}	

	public CostType getKostenSoort() {
		return costType;
	}
	
	public void setCostType(CostType costType) {
		this.costType = costType;
	}

	public void setId(long id) {
		this.id = id;
	}

}
