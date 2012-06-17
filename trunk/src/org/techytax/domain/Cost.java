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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Cost implements Serializable {

	private static final long serialVersionUID = 6493376166158299239L;
	private BigDecimal amount;
	private BigDecimal vat = new BigDecimal("0");
	private Date date;
	private long id = 0;
	private boolean isIncoming;
	private long costTypeId;
	private String kostenSoortOmschrijving;
	private String description;
	private long userId;

	public BigDecimal getAmount() {
		return amount;
	}

	public BigDecimal getVat() {
		return vat;
	}

	public Date getDate() {
		return date;
	}

	public long getId() {
		return id;
	}

	public long getCostTypeId() {
		return costTypeId;
	}

	public String getKostenSoortOmschrijving() {
		return kostenSoortOmschrijving;
	}

	public String getDescription() {
		return description;
	}

	public long getUserId() {
		return userId;
	}

	public boolean isIncoming() {
		return isIncoming;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setIncoming(boolean isIncoming) {
		this.isIncoming = isIncoming;
	}

	public void setCostTypeId(long costTypeId) {
		this.costTypeId = costTypeId;
	}

	public void setKostenSoortOmschrijving(String kostenSoortOmschrijving) {
		this.kostenSoortOmschrijving = kostenSoortOmschrijving;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
