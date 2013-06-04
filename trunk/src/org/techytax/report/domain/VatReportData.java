/**
 * Copyright 2013 Hans Beemsterboer
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
package org.techytax.report.domain;

import org.techytax.domain.VatDeclarationData;

public class VatReportData {

	private VatDeclarationData vatDeclarationData;

	private VatJournals vatJournalsIn;

	private VatJournals vatJournalsOut;

	public VatDeclarationData getVatDeclarationData() {
		return vatDeclarationData;
	}

	public void setVatDeclarationData(VatDeclarationData vatDeclarationData) {
		this.vatDeclarationData = vatDeclarationData;
	}

	public VatJournals getVatJournalsIn() {
		return vatJournalsIn;
	}

	public void setVatJournalsIn(VatJournals vatJournalsIn) {
		this.vatJournalsIn = vatJournalsIn;
	}

	public VatJournals getVatJournalsOut() {
		return vatJournalsOut;
	}

	public void setVatJournalsOut(VatJournals vatJournalsOut) {
		this.vatJournalsOut = vatJournalsOut;
	}

}
