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

public class Balans {

	BigDecimal totaleKosten;

	BigDecimal totaleBaten;

	BigDecimal brutoOmzet;

	BigDecimal nettoOmzet;

	public BigDecimal getNettoOmzet() {
		return nettoOmzet;
	}

	public void setNettoOmzet(BigDecimal nettoOmzet) {
		this.nettoOmzet = nettoOmzet;
	}

	public BigDecimal getBrutoOmzet() {
		return brutoOmzet;
	}

	public void setBrutoOmzet(BigDecimal brutoOmzet) {
		this.brutoOmzet = brutoOmzet;
	}

	public BigDecimal getTotaleBaten() {
		return totaleBaten;
	}

	public void setTotaleBaten(BigDecimal totaleBaten) {
		this.totaleBaten = totaleBaten;
	}

	public BigDecimal getTotaleKosten() {
		return totaleKosten;
	}

	public void setTotaleKosten(BigDecimal totaleKosten) {
		this.totaleKosten = totaleKosten;
	}

}
