/**
 * Copyright 2012 Hans Beemsterboer
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

public class Boekwaarde {

	private BalanceType balanceType;
	private long id = 0;
	private int jaar;
	private BigInteger saldo;
	private long userId;
	private String description;

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

	public long getUserId() {
		return userId;
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

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
