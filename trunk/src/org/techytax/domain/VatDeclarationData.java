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
package org.techytax.domain;

import java.math.BigDecimal;
import java.util.Date;

public class VatDeclarationData {

	private String name;
	private String phoneNumber;
	private String fiscalNumber;
	private Date startDate;
	private Date endDate;
	private BigDecimal valueAddedTaxOwed;
	private BigDecimal valueAddedTaxOwedToBePaidBack;
	private BigDecimal valueAddedTaxPrivateUse;
	private BigDecimal taxedTurnoverSuppliesServicesGeneralTariff;
	private BigDecimal valueAddedTaxSuppliesServicesGeneralTariff;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFiscalNumber() {
		return fiscalNumber;
	}

	public void setFiscalNumber(String fiscalNumber) {
		this.fiscalNumber = fiscalNumber;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getValueAddedTaxOwed() {
		return valueAddedTaxOwed;
	}

	public void setValueAddedTaxOwed(BigDecimal valueAddedTaxOwed) {
		this.valueAddedTaxOwed = valueAddedTaxOwed;
	}

	public BigDecimal getValueAddedTaxOwedToBePaidBack() {
		return valueAddedTaxOwedToBePaidBack;
	}

	public void setValueAddedTaxOwedToBePaidBack(
			BigDecimal valueAddedTaxOwedToBePaidBack) {
		this.valueAddedTaxOwedToBePaidBack = valueAddedTaxOwedToBePaidBack;
	}

	public BigDecimal getValueAddedTaxPrivateUse() {
		return valueAddedTaxPrivateUse;
	}

	public void setValueAddedTaxPrivateUse(BigDecimal valueAddedTaxPrivateUse) {
		this.valueAddedTaxPrivateUse = valueAddedTaxPrivateUse;
	}

	public BigDecimal getTaxedTurnoverSuppliesServicesGeneralTariff() {
		return taxedTurnoverSuppliesServicesGeneralTariff;
	}

	public void setTaxedTurnoverSuppliesServicesGeneralTariff(
			BigDecimal taxedTurnoverSuppliesServicesGeneralTariff) {
		this.taxedTurnoverSuppliesServicesGeneralTariff = taxedTurnoverSuppliesServicesGeneralTariff;
	}

	public BigDecimal getValueAddedTaxSuppliesServicesGeneralTariff() {
		return valueAddedTaxSuppliesServicesGeneralTariff;
	}

	public void setValueAddedTaxSuppliesServicesGeneralTariff(
			BigDecimal valueAddedTaxSuppliesServicesGeneralTariff) {
		this.valueAddedTaxSuppliesServicesGeneralTariff = valueAddedTaxSuppliesServicesGeneralTariff;
	}

}
