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

public class Balance {

	BigDecimal brutoOmzet;

	BigDecimal correction;

	BigDecimal nettoOmzet;

	BigDecimal totaleBaten;
	
	BigDecimal totaleKosten;

	public BigDecimal getBrutoOmzet() {
		return brutoOmzet;
	}

	public BigDecimal getCorrection() {
		return correction;
	}

	public BigDecimal getNettoOmzet() {
		return nettoOmzet;
	}

	public BigDecimal getTotaleBaten() {
		return totaleBaten;
	}

	public BigDecimal getTotaleKosten() {
		return totaleKosten;
	}

	public void setBrutoOmzet(BigDecimal brutoOmzet) {
		this.brutoOmzet = brutoOmzet;
	}

	public void setCorrection(BigDecimal correction) {
		this.correction = correction;
	}

	public void setNettoOmzet(BigDecimal nettoOmzet) {
		this.nettoOmzet = nettoOmzet;
	}

	public void setTotaleBaten(BigDecimal totaleBaten) {
		this.totaleBaten = totaleBaten;
	}

	public void setTotaleKosten(BigDecimal totaleKosten) {
		this.totaleKosten = totaleKosten;
	}

}
