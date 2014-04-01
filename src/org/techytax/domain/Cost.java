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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jasypt.hibernate4.type.EncryptedBigDecimalType;
import org.jasypt.hibernate4.type.EncryptedStringType;
import org.zkoss.util.resource.Labels;

@TypeDefs({
		@TypeDef(name = "encryptedString", typeClass = EncryptedStringType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "strongHibernateStringEncryptor") }),
		@TypeDef(name = "encryptedBigDecimal", typeClass = EncryptedBigDecimalType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "bigDecimalEncryptor"),
				@Parameter(name = "decimalScale", value = "2") }) })
@Entity(name = "org.techytax.domain.Cost")
@Table(name = "kosten")
public class Cost implements Serializable {

	private static final long serialVersionUID = 6493376166158299239L;

	@Column(name = "bedrag")
	@Type(type = "encryptedBigDecimal")
	private BigDecimal amount;

	@Column(name = "btw")
	@Type(type = "encryptedBigDecimal")
	private BigDecimal vat = BigDecimal.ZERO;

	@Column(name = "datum")
	private Date date;

	@Id
	private long id = 0;

	@ManyToOne
	@JoinColumn(name = "kostensoort_id")
	private CostType costType;

	@Column(name = "omschrijving")
	@Type(type = "encryptedString")
	private String description;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

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
		return costType.getKostenSoortId();
	}

	public String getKostenSoortOmschrijving() {
		if (costType != null && costType.getOmschrijving() != null) {
			return Labels.getLabel(costType.getOmschrijving());
		} else {
			return "Onbekend";
		}
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

	public void setId(long id) {
		this.id = id;
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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = new UserEntity(user);
	}

	// For iBatis.
	public long getUserId() {
		return user.getId();
	}

	public CostType getCostType() {
		return costType;
	}

	public void setIncoming(boolean b) {
		// TODO Auto-generated method stub
	}
	
	public String getStyle() {
		if (costType.isBijschrijving()) {
			return "z-listitem-positive-amount";
		} else {
			return "z-listitem-negative-amount";
		}
	}
}
