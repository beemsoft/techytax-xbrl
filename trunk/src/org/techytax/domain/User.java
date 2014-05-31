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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class User implements Serializable {

	private static final long serialVersionUID = -374265857173724138L;
	
	@Id
	private long id;

	private String role;
	
	private boolean blocked;
	
	@Column(name = "company_address")
	private String companyAddress;
	
	private String companyName;
	private String email;
	
	private String initials;
	private String prefix;
	private String surname;
	
    @Temporal(TemporalType.TIMESTAMP)
	private Date latestOnlineTime;
	private String password;
	private String username;
	private String phoneNumber;
	private String fiscalNumber;
	
	@Column(name = "kvk_nummer")
	private Long chamberOfCommerceNumber;
	
	@Enumerated(EnumType.ORDINAL)
	private VatPeriodType vatPeriodType = VatPeriodType.PER_QUARTER;
	
	private String accountNumber;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFiscalNumber() {
		return fiscalNumber;
	}

	public void setFiscalNumber(String fiscalNumber) {
		this.fiscalNumber = fiscalNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getEmail() {
		return email;
	}

	public String getFullName() {
		StringBuffer sb = new StringBuffer();
		sb.append(initials);
		if (prefix != null) {
			sb.append(" ");			
			sb.append(prefix);
		}
		sb.append(" ");		
		sb.append(surname);
		return sb.toString();
	}

	public long getId() {
		return id;
	}

	public Date getLatestOnlineTime() {
		return latestOnlineTime;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public boolean passwordMatch(String pwd) {
		return password.equals(pwd);
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLatestOnlineTime(Date latestOnlineTime) {
		this.latestOnlineTime = latestOnlineTime;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public VatPeriodType getVatPeriodType() {
		return vatPeriodType;
	}

	public void setVatPeriodType(VatPeriodType vatPeriodType) {
		this.vatPeriodType = vatPeriodType;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public Long getChamberOfCommerceNumber() {
		return chamberOfCommerceNumber;
	}

	public void setChamberOfCommerceNumber(Long chamberOfCommerceNumber) {
		this.chamberOfCommerceNumber = chamberOfCommerceNumber;
	}
	
	public boolean isAdmin() {
		return role.equals("admin");
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
}
