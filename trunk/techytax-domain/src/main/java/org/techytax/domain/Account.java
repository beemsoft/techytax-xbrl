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

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Type;

@Entity(name = "org.techytax.domain.Account")
@Table(name = "accounts")
@Getter
@Setter
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

}
