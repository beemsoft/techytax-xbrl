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

public class Boekwaarde {

	private long balansId;
	private long id = 0;
	private int jaar;
	private int saldo;
	private long userId;

	public long getBalansId() {
		return balansId;
	}

	public long getId() {
		return id;
	}

	public int getJaar() {
		return jaar;
	}

	public int getSaldo() {
		return saldo;
	}

	public long getUserId() {
		return userId;
	}

	public void setBalansId(long balansId) {
		this.balansId = balansId;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setJaar(int jaar) {
		this.jaar = jaar;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
