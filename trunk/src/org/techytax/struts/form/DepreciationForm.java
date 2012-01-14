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
package org.techytax.struts.form;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

public class DepreciationForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private long id = 0;
	private int nofYears = 2;
	private boolean car;
	private String endDate;
	private BigInteger remainingValue;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNofYears() {
		return nofYears;
	}

	public void setNofYears(int nofYears) {
		this.nofYears = nofYears;
	}

	public boolean isCar() {
		return car;
	}

	public void setCar(boolean car) {
		this.car = car;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public BigInteger getRemainingValue() {
		return remainingValue;
	}

	public void setRemainingValue(BigInteger remainingValue) {
		this.remainingValue = remainingValue;
	}

}