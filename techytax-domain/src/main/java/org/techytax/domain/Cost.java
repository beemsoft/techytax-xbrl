/**
 * Copyright 2015 Hans Beemsterboer
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

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.hibernate.annotations.Type;

@Entity
@NamedQueries({
		@NamedQuery(name = Cost.FOR_PERIOD, query = "SELECT c FROM Cost c WHERE c.user = :user AND c.date >= :beginDate AND c.date <= :endDate order by c.date asc"),
		@NamedQuery(name = Cost.FOR_PERIOD_AND_TYPES, query = "SELECT c FROM Cost c WHERE c.date >= :beginDate AND c.date <= :endDate AND c.costType IN :costTypes AND c.user = :user"),
		@NamedQuery(name = Cost.FOR_PERIOD_AND_VAT_DECLARABLE, query = "SELECT c FROM Cost c WHERE c.date >= :beginDate AND c.date <= :endDate AND c.costType.btwVerrekenbaar = true AND c.user = :user"),
		@NamedQuery(name = Cost.FOR_PERIOD_AND_ACCOUNT, query = "SELECT c FROM Cost c WHERE c.date >= :beginDate AND c.date <= :endDate AND c.costType.balansMeetellen = true AND c.user = :user") })
@Table(name = "kosten")
@Getter
@Setter
public class Cost extends UserObject implements Serializable {

	private static final long serialVersionUID = 6493376166158299239L;

	public static final String FOR_PERIOD = "Cost.FOR_PERIOD";
	public static final String FOR_PERIOD_AND_TYPES = "Cost.FOR_PERIOD_AND_TYPES";
	public static final String FOR_PERIOD_AND_VAT_DECLARABLE = "Cost.FOR_PERIOD_AND_VAT_DECLARABLE";
	public static final String FOR_PERIOD_AND_ACCOUNT = "Cost.FOR_PERIOD_AND_ACCOUNT";

	@Column(name = "bedrag", precision = 50, scale = 2)
	@Type(type = "encryptedBigDecimal")
	private BigDecimal amount;

	@Column(name = "btw", precision = 48, scale = 2)
	@Type(type = "encryptedBigDecimal")
	private BigDecimal vat = BigDecimal.ZERO;

	@Column(name = "datum")
	private Date date;

	@ManyToOne
	@JoinColumn(name = "kostensoort_id")
	private CostType costType;

	@Column(name = "omschrijving")
	@Type(type = "encryptedString")
	private String description;

	public long getCostTypeId() {
		return costType.getId();
	}

	public boolean isIncoming() {
		return costType.isBijschrijving();
	}

	public boolean isInvestment() { return costType.isInvestering(); }

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

	public String getStyle() {
		if (costType.isBijschrijving()) {
			return "z-listitem-positive-amount";
		} else {
			return "z-listitem-negative-amount";
		}
	}
}
