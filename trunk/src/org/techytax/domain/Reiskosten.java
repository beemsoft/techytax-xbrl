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

public class Reiskosten {
	
	private BigDecimal ovKosten;
	
	private BigDecimal autoKostenZonderBtw;
	
	private BigDecimal autoKostenMetBtw;

	public BigDecimal getAutoKostenMetBtw() {
		return autoKostenMetBtw;
	}

	public void setAutoKostenMetBtw(BigDecimal autoKostenMetBtw) {
		this.autoKostenMetBtw = autoKostenMetBtw;
	}

	public BigDecimal getAutoKostenZonderBtw() {
		return autoKostenZonderBtw;
	}

	public void setAutoKostenZonderBtw(BigDecimal autoKostenZonderBtw) {
		this.autoKostenZonderBtw = autoKostenZonderBtw;
	}

	public BigDecimal getOvKosten() {
		return ovKosten;
	}

	public void setOvKosten(BigDecimal ovKosten) {
		this.ovKosten = ovKosten;
	}

}
