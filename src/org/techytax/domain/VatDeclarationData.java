/**
 * Copyright 2014 Hans Beemsterboer
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

import java.math.BigInteger;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

public class VatDeclarationData {

	private final User user;
	private Date startDate;
	private Date endDate;

	// Verschuldigde_omzetbelasting_2
	private BigInteger valueAddedTaxOwed = BigInteger.ZERO;

	// Totaal_te_betalen_/_terug_te_vragen_2
	private BigInteger valueAddedTaxOwedToBePaidBack = BigInteger.ZERO;

	// Omzetbelasting_over_privegebruik_1
	private BigInteger valueAddedTaxPrivateUse = BigInteger.ZERO;

	// Omzetbelasting_leveringen/diensten_algemeen_tarief moet gelijk zijn aan
	// Omzet_leveringen/diensten_belast_met_algemeen_tarief maal
	// Percentage_algemene_tarief_omzetbelasting afgerond naar beneden
	private BigInteger taxedTurnoverSuppliesServicesGeneralTariff = BigInteger.ZERO;

	// Omzetbelasting_leveringen/diensten_algemeen_tarief_2
	private BigInteger valueAddedTaxSuppliesServicesGeneralTariff = BigInteger.ZERO;

	// Voorbelasting_1
	private BigInteger valueAddedTaxOnInput = BigInteger.ZERO;

	public VatDeclarationData(User user) {
		Validate.notNull(user);
		this.user = user;
		String fiscalNumber = user.getFiscalNumber();
		if (StringUtils.isEmpty(fiscalNumber)) {
			throw new RuntimeException("U heeft nog geen fiscaal nummer opgegeven");
		}
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

	public BigInteger getValueAddedTaxOwed() {
		return valueAddedTaxOwed;
	}

	public void setValueAddedTaxOwed(BigInteger valueAddedTaxOwed) {
		this.valueAddedTaxOwed = valueAddedTaxOwed;
	}

	public BigInteger getValueAddedTaxOwedToBePaidBack() {
		return valueAddedTaxOwedToBePaidBack;
	}

	public void setValueAddedTaxOwedToBePaidBack(BigInteger valueAddedTaxOwedToBePaidBack) {
		this.valueAddedTaxOwedToBePaidBack = valueAddedTaxOwedToBePaidBack;
	}

	public BigInteger getValueAddedTaxPrivateUse() {
		return valueAddedTaxPrivateUse;
	}

	public void setValueAddedTaxPrivateUse(BigInteger valueAddedTaxPrivateUse) {
		this.valueAddedTaxPrivateUse = valueAddedTaxPrivateUse;
	}

	public BigInteger getTaxedTurnoverSuppliesServicesGeneralTariff() {
		return taxedTurnoverSuppliesServicesGeneralTariff;
	}

	public void setTaxedTurnoverSuppliesServicesGeneralTariff(BigInteger taxedTurnoverSuppliesServicesGeneralTariff) {
		this.taxedTurnoverSuppliesServicesGeneralTariff = taxedTurnoverSuppliesServicesGeneralTariff;
	}

	public BigInteger getValueAddedTaxSuppliesServicesGeneralTariff() {
		return valueAddedTaxSuppliesServicesGeneralTariff;
	}

	public void setValueAddedTaxSuppliesServicesGeneralTariff(BigInteger valueAddedTaxSuppliesServicesGeneralTariff) {
		this.valueAddedTaxSuppliesServicesGeneralTariff = valueAddedTaxSuppliesServicesGeneralTariff;
	}

	public BigInteger getValueAddedTaxOnInput() {
		return valueAddedTaxOnInput;
	}

	public void setValueAddedTaxOnInput(BigInteger valueAddedTaxOnInput) {
		this.valueAddedTaxOnInput = valueAddedTaxOnInput;
	}

	public User getUser() {
		return user;
	}

}
