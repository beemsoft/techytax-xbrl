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

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jasypt.hibernate4.type.EncryptedBigDecimalType;
import org.jasypt.hibernate4.type.EncryptedStringType;

@TypeDefs({ @TypeDef(name = "encryptedString", typeClass = EncryptedStringType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "strongHibernateStringEncryptor") }),
	@TypeDef(name = "encryptedBigDecimal", typeClass = EncryptedBigDecimalType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "bigDecimalEncryptor"), @Parameter(name = "decimalScale", value = "2") })})
@Entity(name = "org.techytax.domain.Activum")
@Table(name = "activa")
public class Activum extends Passivum {

	@Id
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@OneToOne(mappedBy = "activum", cascade=CascadeType.ALL)
	private RemainingValue restwaarde;

	@Column(name = "einddatum")
	private Date endDate;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "kost_id")
	private Cost cost;

	public RemainingValue getRestwaarde() {
		return restwaarde;
	}

	public void setRestwaarde(RemainingValue restwaarde) {
		this.restwaarde = restwaarde;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = new UserEntity(user);
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}
	
	// For iBatis.
	public long getUserId() {
		return user.getId();
	}	
	
	public long getCostId() {
		return cost.getId();
	}

	public BigDecimal getAanschafKosten() {
		if (cost != null) {
			return cost.getAmount().add(cost.getVat());
		} else {
			return null;
		}
	}

}
