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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jasypt.hibernate4.type.EncryptedBigIntegerType;
import org.jasypt.hibernate4.type.EncryptedStringType;

@TypeDefs({
	@TypeDef(name = "encryptedString", typeClass = EncryptedStringType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "strongHibernateStringEncryptor") }),
	@TypeDef(name = "encryptedInteger", typeClass = EncryptedBigIntegerType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "integerEncryptor") }) })
@Entity(name = "org.techytax.domain.BookValue")
@Table(name = "boekwaarde")
public class BookValue {
	
	@Id
	private long id = 0;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
	@Column(name = "balans_id")
	@Enumerated(EnumType.ORDINAL)
	private BalanceType balanceType;
	
	@Column(name = "boekjaar")
	private int jaar;
	
	@Type(type = "encryptedInteger")
	private BigInteger saldo;
	
	public BookValue() {
	}
	
	public BookValue(long id, BalanceType balanceType, int jaar, BigInteger saldo) {
		this.id = id;
		this.balanceType = balanceType;
		this.jaar = jaar;
		this.saldo = saldo;
	}

	public BalanceType getBalanceType() {
		return balanceType;
	}

	public long getId() {
		return id;
	}

	public int getJaar() {
		return jaar;
	}

	public BigInteger getSaldo() {
		return saldo;
	}

	public User getUser() {
		return user;
	}

	public void setBalanceType(BalanceType balanceType) {
		this.balanceType = balanceType;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setJaar(int jaar) {
		this.jaar = jaar;
	}

	public void setSaldo(BigInteger saldo) {
		this.saldo = saldo;
	}

	public void setUser(User user) {
		this.user = new UserEntity(user);
	}

	public String getDescription() {
		return balanceType.getKey();
	}
	
}
