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

public class Liquiditeit {

	BigDecimal overigeKosten;

	BigDecimal priveBalans;

	BigDecimal rekeningBalans;

	BigDecimal spaarBalans;

	public BigDecimal getOverigeKosten() {
		return overigeKosten;
	}

	public BigDecimal getPriveBalans() {
		return priveBalans;
	}

	public BigDecimal getRekeningBalans() {
		return rekeningBalans;
	}

	public BigDecimal getSpaarBalans() {
		return spaarBalans;
	}

	public void setOverigeKosten(BigDecimal overigeKosten) {
		this.overigeKosten = overigeKosten;
	}

	public void setPriveBalans(BigDecimal priveBalans) {
		this.priveBalans = priveBalans;
	}

	public void setRekeningBalans(BigDecimal rekeningBalans) {
		this.rekeningBalans = rekeningBalans;
	}

	public void setSpaarBalans(BigDecimal spaarBalans) {
		this.spaarBalans = spaarBalans;
	}

}
