/**
 * Copyright 2011 Hans Beemsterboer
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

public class User implements Serializable {

	private static final long serialVersionUID = -374265857173724138L;

	private boolean blocked;

	private String email;

	private Date freezeEndDate;

	private Date freezeStartDate;

	private String fullName;

	private long id;

	private Date latestOnlineTime;

	private boolean paid;

	private String password;

	private String role;

	private String username;

	public boolean isFrozen() {
		boolean frozen = false;
		Date currentDate = new Date();
		if (freezeStartDate != null && freezeStartDate.before(currentDate) && (freezeEndDate == null || freezeEndDate.after(currentDate))) {
			frozen = true;
		}
		return frozen;
	}

	public String getEmail() {
		return email;
	}

	public Date getFreezeEndDate() {
		return freezeEndDate;
	}

	public Date getFreezeStartDate() {
		return freezeStartDate;
	}

	public String getFullName() {
		return fullName;
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

	public String getRole() {
		return role;
	}

	public String getUsername() {
		return username;
	}

	public boolean hasRole(String role) {
		if (this.role != null && this.role.equals(role)) {
			return true;
		}
		return false;
	}

	public boolean isAdministrator() {
		return hasRole("admin");
	}

	public boolean isBlocked() {
		return blocked;
	}

	public boolean isGuest() {
		return hasRole("guest");
	}

	public boolean isPaid() {
		return paid;
	}

	public boolean isUser() {
		return hasRole("user");
	}

	public boolean passwordMatch(String pwd) {
		return password.equals(pwd);
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFreezeEndDate(Date freezeEndDate) {
		this.freezeEndDate = freezeEndDate;
	}

	public void setFreezeStartDate(Date freezeStartDate) {
		this.freezeStartDate = freezeStartDate;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLatestOnlineTime(Date latestOnlineTime) {
		this.latestOnlineTime = latestOnlineTime;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
