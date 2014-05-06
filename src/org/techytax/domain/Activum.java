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

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jasypt.hibernate4.type.EncryptedBigDecimalType;
import org.jasypt.hibernate4.type.EncryptedBigIntegerType;
import org.jasypt.hibernate4.type.EncryptedStringType;
import org.techytax.helper.DepreciationHelper;

@TypeDefs({
		@TypeDef(name = "encryptedString", typeClass = EncryptedStringType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "strongHibernateStringEncryptor") }),
		@TypeDef(name = "encryptedBigDecimal", typeClass = EncryptedBigDecimalType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "bigDecimalEncryptor"),
				@Parameter(name = "decimalScale", value = "2") }),
		@TypeDef(name = "encryptedInteger", typeClass = EncryptedBigIntegerType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "integerEncryptor") }) })
@Entity(name = "org.techytax.domain.Activum")
@Table(name = "activa")
@Inheritance(strategy = InheritanceType.JOINED)
public class Activum {

	@Id
	public long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	protected UserEntity user;

	@OneToOne(mappedBy = "activum", cascade = CascadeType.ALL)
	private RemainingValue restwaardeOld;

	@Type(type = "encryptedInteger")
	private BigInteger remainingValue;

	@Column(name = "einddatum")
	private Date endDate;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "kost_id")
	private Cost cost;

	@Column(name = "balans_id")
	@Enumerated(EnumType.ORDINAL)
	private BalanceType balanceType;

	private Date startDate;

	private int nofYearsForDepreciation;

	public BalanceType getBalanceType() {
		return balanceType;
	}

	public void setBalanceType(BalanceType balanceType) {
		this.balanceType = balanceType;
	}

	public String getOmschrijving() {
		return balanceType.getKey();
	}

	public BigInteger getRemainingValue() {
		return remainingValue;
	}
	
	public RemainingValue getRestwaarde() {
		return restwaardeOld;
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

	public long getCostId() {
		return cost.getId();
	}

	public int getNofYearsForDepreciation() {
		return nofYearsForDepreciation;
	}

	public void setNofYearsForDepreciation(int nofYearsForDepreciation) {
		this.nofYearsForDepreciation = nofYearsForDepreciation;
	}

	public Date getStartDate() {
		if (startDate != null) {
			return startDate;
		} else {
			return cost.getDate();
		}
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setRemainingValue(BigInteger remainingValue) {
		this.remainingValue = remainingValue;
	}
	
	public BigInteger getDepreciation() {
		DepreciationHelper depreciationHelper = new DepreciationHelper();
		return depreciationHelper.getDepreciation(this);
	}

}
