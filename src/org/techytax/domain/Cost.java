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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.hibernate.annotations.Type;

@Entity(name = "org.techytax.domain.Cost")
@Table(name = "kosten")
public class Cost extends UserObject implements Serializable {

	private static final long serialVersionUID = 6493376166158299239L;

	@Column(name = "bedrag")
	@Type(type = "encryptedBigDecimal")
	private BigDecimal amount;

	@Column(name = "btw")
	@Type(type = "encryptedBigDecimal")
	private BigDecimal vat = BigDecimal.ZERO;

	@Column(name = "datum")
	private Date date;

	@ManyToOne
	@JoinColumn(name="kostensoort_id")
	private CostType costType;

	@Column(name = "omschrijving")
	@Type(type = "encryptedString")
	private String description;

	public BigDecimal getAmount() {
		return amount;
	}

	public BigDecimal getVat() {
		return vat;
	}

	public Date getDate() {
		return date;
	}

	public long getCostTypeId() {
		return costType.getId();
	}

	public String getDescription() {
		return description;
	}

	public boolean isIncoming() {
		return costType.isBijschrijving();
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

	public void setCostType(CostType costType) {
		this.costType = costType;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void roundValues() {
		if (amount != null) {
			amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		if (vat != null) {
			vat = vat.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Cost)) {
			return false;
		}
		Cost other = (Cost) obj;
		EqualsBuilder equalsBuilder = new EqualsBuilder();
		equalsBuilder.append(id, other.id);
		equalsBuilder.append(description, other.description);
		equalsBuilder.append(date, other.date);
		equalsBuilder.append(amount, other.amount);
		equalsBuilder.append(vat, other.vat);
		equalsBuilder.append(costType, other.costType);
		return equalsBuilder.isEquals();
	}

	public CostType getCostType() {
		return costType;
	}

	public String getStyle() {
		if (costType.isBijschrijving()) {
			return "z-listitem-positive-amount";
		} else {
			return "z-listitem-negative-amount";
		}
	}
}
