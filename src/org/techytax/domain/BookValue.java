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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity(name = "org.techytax.domain.BookValue")
@Table(name = "boekwaarde")
public class BookValue extends UserObject {
	
	@Column(name = "balans_id")
	@Enumerated(EnumType.ORDINAL)
	private BalanceType balanceType;
	
	@Column(name = "boekjaar")
	private int jaar;
	
	@Type(type = "encryptedInteger")
	private BigInteger saldo;
	
	public BookValue() {
	}
	
	public BookValue(BalanceType balanceType, int jaar, BigInteger saldo) {
		this.balanceType = balanceType;
		this.jaar = jaar;
		this.saldo = saldo;
	}

	public BalanceType getBalanceType() {
		return balanceType;
	}

	public int getJaar() {
		return jaar;
	}

	public BigInteger getSaldo() {
		return saldo;
	}

	public void setBalanceType(BalanceType balanceType) {
		this.balanceType = balanceType;
	}

	public void setJaar(int jaar) {
		this.jaar = jaar;
	}

	public void setSaldo(BigInteger saldo) {
		this.saldo = saldo;
	}

	public String getDescription() {
		return balanceType.getKey();
	}
	
}
