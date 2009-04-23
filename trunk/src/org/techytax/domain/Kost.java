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

public class Kost {
	private long id = 0;
	private BigDecimal bedrag;
	private BigDecimal btw;
	private String datum;
	private String omschrijving;
	private long crediteurId;
	private long kostenSoortId;
	private String kostenSoortOmschrijving;

	public String getKostenSoortOmschrijving() {
		return kostenSoortOmschrijving;
	}

	public void setKostenSoortOmschrijving(String kostenSoortOmschrijving) {
		this.kostenSoortOmschrijving = kostenSoortOmschrijving;
	}

	public BigDecimal getBedrag() {
		return bedrag;
	}

	public void setBedrag(BigDecimal bedrag) {
		this.bedrag = bedrag;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getKostenSoortId() {
		return kostenSoortId;
	}

	public void setKostenSoortId(long kostenSoortId) {
		this.kostenSoortId = kostenSoortId;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}

	public long getCrediteurId() {
		return crediteurId;
	}

	public void setCrediteurId(long crediteurId) {
		this.crediteurId = crediteurId;
	}

	public BigDecimal getBtw() {
		return btw;
	}

	public void setBtw(BigDecimal btw) {
		this.btw = btw;
	}
}
