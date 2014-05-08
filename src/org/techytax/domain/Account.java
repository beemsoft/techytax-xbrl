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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity(name = "org.techytax.domain.Account")
@Table(name = "accounts")
public class Account extends UserObject {

	@Column(name = "date_closed")
	private Date dateClosed;
	
	@Column(name = "date_opened")
	private Date dateOpened;
	
	@Type(type = "encryptedString")
	private String description;
	
	@Type(type = "encryptedString")
	private String name;
	
	@Type(type = "encryptedString")
	private String number;
	
	private AccountType type;

	public Date getDateClosed() {
		return dateClosed;
	}

	public Date getDateOpened() {
		return dateOpened;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

	public void setDateClosed(Date dateClosed) {
		this.dateClosed = dateClosed;
	}

	public void setDateOpened(Date dateOpened) {
		this.dateOpened = dateOpened;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}
}
