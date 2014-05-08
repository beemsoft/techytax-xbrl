/**
 * Copyright 2013 Hans Beemsterboer
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
package org.techytax.business.jpa.entities;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
import org.techytax.domain.UserEntity;
import org.techytax.domain.UserObject;

@Entity
@Table(name = "customer")
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

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

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmailInvoice() {
		return emailInvoice;
	}

	public void setEmailInvoice(String emailInvoice) {
		this.emailInvoice = emailInvoice;
	}

	public BigInteger getCommerceNr() {
		return commerceNr;
	}

	public void setCommerceNr(BigInteger commerceNr) {
		this.commerceNr = commerceNr;
	}

	public BigInteger getNumber() {
		return number;
	}

	public void setNumber(BigInteger number) {
		this.number = number;
	}

	public String getNumberExtension() {
		return numberExtension;
	}

	public void setNumberExtension(String numberExtension) {
		this.numberExtension = numberExtension;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
}
