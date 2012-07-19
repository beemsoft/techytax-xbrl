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

public class Kostmatch {
	private VatType vatType = VatType.NONE;

	private long id = 0;

	private long kostenSoortId = 0;

	private String matchText;

	private long userId;

	public VatType getVatType() {
		return vatType;
	}

	public long getId() {
		return id;
	}

	public long getKostenSoortId() {
		return kostenSoortId;
	}

	public String getMatchText() {
		return matchText;
	}

	public long getUserId() {
		return userId;
	}

	public void setVatType(VatType vatType) {
		this.vatType = vatType;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setKostenSoortId(long kostenSoortId) {
		this.kostenSoortId = kostenSoortId;
	}

	public void setMatchText(String matchText) {
		this.matchText = matchText;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
