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

import java.math.BigDecimal;
import java.util.Date;

public class AccountBalance implements Comparable<AccountBalance>{
	private long accountId;
	private BigDecimal balance;
	private Date datum;
	private long id = 0;
	private long userId;

	public long getAccountId() {
		return accountId;
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

	public long getUserId() {
		return userId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
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

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int compareTo(AccountBalance o) {
		return datum.compareTo(o.getDatum());
	}
}
