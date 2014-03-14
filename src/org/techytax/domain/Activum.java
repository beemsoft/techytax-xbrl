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
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jasypt.hibernate4.type.EncryptedBigIntegerType;
import org.jasypt.hibernate4.type.EncryptedStringType;

@TypeDefs({
	@TypeDef(name = "encryptedString", typeClass = EncryptedStringType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "strongHibernateStringEncryptor") }),
	@TypeDef(name = "encryptedInteger", typeClass = EncryptedBigIntegerType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "integerEncryptor") }) })
@Entity(name = "org.techytax.domain.Activum")
@Table(name = "activa")
public class Activum extends Passivum {

	@Id
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	private BigDecimal aanschafKosten;
	private BigDecimal bedrag;
	private BigDecimal btw;
	private BigInteger restwaarde;

	private Date endDate;

	@OneToOne
	@JoinColumn(name = "costId")
	private Cost cost;

	public BigDecimal getAanschafKosten() {
		return aanschafKosten;
	}

	public BigDecimal getBedrag() {
		return bedrag;
	}

	public BigDecimal getBtw() {
		return btw;
	}

	public BigInteger getRestwaarde() {
		return restwaarde;
	}

	public void setAanschafKosten(BigDecimal aanschafKosten) {
		this.aanschafKosten = aanschafKosten;
	}

	public void setBedrag(BigDecimal bedrag) {
		this.bedrag = bedrag;
	}

	public void setBtw(BigDecimal btw) {
		this.btw = btw;
	}

	public void setRestwaarde(BigInteger restwaarde) {
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

}
