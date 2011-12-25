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
import java.math.BigInteger;

public class Activum {

	private long id;
	private BigDecimal aanschafKosten;
	private BigDecimal bedrag;
	private int boekjaar;
	private BigDecimal btw;
	private String omschrijving;
	private BigInteger restwaarde;
	private BigInteger saldo;
	
	private long userId;
	private BalanceType balanceType;
	private String endDate;
	private long costId;

	public BigDecimal getAanschafKosten() {
		return aanschafKosten;
	}

	public BigDecimal getBedrag() {
		return bedrag;
	}

	public int getBoekjaar() {
		return boekjaar;
	}

	public BigDecimal getBtw() {
		return btw;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public BigInteger getRestwaarde() {
		return restwaarde;
	}

	public BigInteger getSaldo() {
		return saldo;
	}

	public void setAanschafKosten(BigDecimal aanschafKosten) {
		this.aanschafKosten = aanschafKosten;
	}

	public void setBedrag(BigDecimal bedrag) {
		this.bedrag = bedrag;
	}

	public void setBoekjaar(int boekjaar) {
		this.boekjaar = boekjaar;
	}

	public void setBtw(BigDecimal btw) {
		this.btw = btw;
	}

	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}

	public void setRestwaarde(BigInteger restwaarde) {
		this.restwaarde = restwaarde;
	}

	public void setSaldo(BigInteger saldo) {
		this.saldo = saldo;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public BalanceType getBalanceType() {
		return balanceType;
	}

	public void setBalanceType(BalanceType balanceType) {
		this.balanceType = balanceType;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public long getCostId() {
		return costId;
	}

	public void setCostId(long costId) {
		this.costId = costId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
