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
package org.techytax.struts.form;

import org.apache.struts.action.ActionForm;

public class BalansForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String beginDatum;
	private String eindDatum;
	private String balansSoort;
	private String searchTerm;

	public String getBalansSoort() {
		return balansSoort;
	}

	public void setBalansSoort(String balansSoort) {
		this.balansSoort = balansSoort;
	}

	public String getBeginDatum() {
		return beginDatum;
	}

	public void setBeginDatum(String beginDatum) {
		this.beginDatum = beginDatum;
	}

	public String getEindDatum() {
		return eindDatum;
	}

	public void setEindDatum(String eindDatum) {
		this.eindDatum = eindDatum;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

}
