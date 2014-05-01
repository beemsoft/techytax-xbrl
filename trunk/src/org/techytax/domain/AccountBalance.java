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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jasypt.hibernate4.type.EncryptedBigDecimalType;
import org.jasypt.hibernate4.type.EncryptedStringType;

@TypeDefs({
		@TypeDef(name = "encryptedString", typeClass = EncryptedStringType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "strongHibernateStringEncryptor") }),
		@TypeDef(name = "encryptedBigDecimal", typeClass = EncryptedBigDecimalType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "bigDecimalEncryptor"),
				@Parameter(name = "decimalScale", value = "2") }) })
@Entity(name = "org.techytax.domain.AccountBalance")
@Table(name = "account_balance")
public class AccountBalance implements Comparable<AccountBalance> {

	@Id
	private long id = 0;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	@Type(type = "encryptedBigDecimal")
	private BigDecimal balance;

	private Date datum;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	public Account getAccount() {
		return account;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public Date getDatum() {
		return datum;
	}

	public long getId() {
		return id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = new UserEntity(user);
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int compareTo(AccountBalance o) {
		return datum.compareTo(o.getDatum());
	}
}
