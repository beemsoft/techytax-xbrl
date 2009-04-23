/**
 * Copyright 2009 Hans Beemsterboer
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

public class Activa {
	
	public static final int MACHINERY = 1;
	public static final int CAR = 2;
	public static final int CURRENT_ASSETS = 3;	

	private String omschrijving;
	private BigDecimal aanschafKosten;
	private int boekjaar;
	private BigDecimal saldo;
	private BigDecimal restwaarde;

	public BigDecimal getAanschafKosten() {
		return aanschafKosten;
	}

	public void setAanschafKosten(BigDecimal aanschafKosten) {
		this.aanschafKosten = aanschafKosten;
	}

	public int getBoekjaar() {
		return boekjaar;
	}

	public void setBoekjaar(int boekjaar) {
		this.boekjaar = boekjaar;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}

	public BigDecimal getRestwaarde() {
		return restwaarde;
	}

	public void setRestwaarde(BigDecimal restwaarde) {
		this.restwaarde = restwaarde;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

}
