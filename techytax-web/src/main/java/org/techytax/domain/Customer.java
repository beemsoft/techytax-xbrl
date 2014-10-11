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
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer extends UserObject {

	@Type(type = "encryptedString")
	private String name;

	@Type(type = "encryptedString")
	private String description;

	@Type(type = "encryptedInteger")
	private BigInteger commerceNr;

	@Type(type = "encryptedString")
	private String address;

	@Type(type = "encryptedInteger")
	private BigInteger number;

	@Type(type = "encryptedString")
	@Column(name = "number_extension")
	private String numberExtension;

	@Type(type = "encryptedString")
	@Column(name = "postal_code")
	private String postalCode;

	@Type(type = "encryptedString")
	private String city;

	@Type(type = "encryptedString")
	private String contact;

	@Type(type = "encryptedString")
	@Column(name = "email_invoice")
	private String emailInvoice;

	@Type(type = "encryptedString")
	private String telephone;

	@Type(type = "encryptedString")
	private String fax;

	@Type(type = "encryptedString")
	private String website;

	public String getFullAddress() {
		StringBuffer fullAddress = new StringBuffer();
		fullAddress.append(address);
		if (number != null) {
			fullAddress.append(" ");
			fullAddress.append(number);
		}
		if (numberExtension != null) {
			fullAddress.append(" ");
			fullAddress.append(numberExtension);
		}
		fullAddress.append(", ");
		fullAddress.append(postalCode);
		fullAddress.append(", ");
		fullAddress.append(city);
		return fullAddress.toString();
	}

}
