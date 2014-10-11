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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

import lombok.Data;

import org.techytax.report.domain.BalanceReport;
import org.techytax.report.helper.FiscalReportHelper;

@Data
public class FiscalOverview {

	private Map<BalanceType,FiscalBalance> activaMap;
	private Map<BalanceType,FiscalBalance> passivaMap;
	
	private BigInteger afschrijvingAuto = BigInteger.ZERO;

	@Deprecated
	private BigInteger afschrijvingAutoCorrectie = BigInteger.ZERO;

	private BigInteger afschrijvingOverig = BigInteger.ZERO;

	// Will be removed in 2017
	@Deprecated
	private BigInteger afschrijvingOverigCorrectie = BigInteger.ZERO;

	private BigInteger afschrijvingTotaal = BigInteger.ZERO;
	private BigInteger bijtellingAuto = BigInteger.ZERO;
	private BigInteger bookTotalBegin = BigInteger.ZERO;
	private BigInteger bookTotalEnd = BigInteger.ZERO;
	private BigInteger enterpriseCapital = BigInteger.ZERO;
	private BigInteger interestFromBusinessSavings = BigInteger.ZERO;
	private BigInteger investmentDeduction = BigInteger.ZERO;
	private int jaar;
	private BigInteger kostenAuto = BigInteger.ZERO;
	private BigInteger kostenAutoAftrekbaar = BigInteger.ZERO;
	private BigInteger kostenOverig = BigInteger.ZERO;
	private BigInteger kostenOverigTransport = BigInteger.ZERO;
	private BigInteger nettoOmzet = BigInteger.ZERO;
	private PrivateWithdrawal onttrekking;
	private BigInteger oudedagsReserveMaximaal = BigInteger.ZERO;

	private PrepaidTax prepaidTax;
	private BigInteger privateDeposit = BigInteger.ZERO;
	private BigInteger profit = BigInteger.ZERO;
	private BigInteger repurchase = BigInteger.ZERO;
	private BigInteger settlementCosts = BigInteger.ZERO;
	private BigInteger settlementDepreciation = BigInteger.ZERO;
	private BigDecimal turnOverUnpaid = BigDecimal.ZERO;

	public BalanceReport getActivaReport() {
		return FiscalReportHelper.getActivaReport(activaMap);
	}

	public BigInteger getCarAndTransportCosts() {
		return kostenAutoAftrekbaar.subtract(kostenOverigTransport);
	}

	public BigInteger getOtherCostsTotal() {
		return getCarAndTransportCosts().subtract(kostenOverig.add(settlementCosts));
	}

	public BalanceReport getPassivaReport() {
		return FiscalReportHelper.getPassivaReport(passivaMap);
	}

	public BigInteger getWinst() {
		return profit;
	}

}
