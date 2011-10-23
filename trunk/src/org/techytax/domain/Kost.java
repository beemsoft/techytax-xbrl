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
import java.math.BigDecimal;

public class Kost implements Serializable {

	private static final long serialVersionUID = 6493376166158299239L;
	private BigDecimal bedrag;
	private BigDecimal btw;
	private String datum;
	private long id = 0;
	private boolean isIncoming;
	private long kostenSoortId;
	private String kostenSoortOmschrijving;
	private String omschrijving;
	private long userId;

	public BigDecimal getBedrag() {
		return bedrag;
	}

	public BigDecimal getBtw() {
		return btw;
	}

	public String getDatum() {
		return datum;
	}

	public long getId() {
		return id;
	}

	public long getKostenSoortId() {
		return kostenSoortId;
	}

	public String getKostenSoortOmschrijving() {
		return kostenSoortOmschrijving;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public long getUserId() {
		return userId;
	}

	public boolean isIncoming() {
		return isIncoming;
	}

	public void setBedrag(BigDecimal bedrag) {
		this.bedrag = bedrag;
	}

	public void setBtw(BigDecimal btw) {
		this.btw = btw;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setIncoming(boolean isIncoming) {
		this.isIncoming = isIncoming;
	}

	public void setKostenSoortId(long kostenSoortId) {
		this.kostenSoortId = kostenSoortId;
	}

	public void setKostenSoortOmschrijving(String kostenSoortOmschrijving) {
		this.kostenSoortOmschrijving = kostenSoortOmschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
