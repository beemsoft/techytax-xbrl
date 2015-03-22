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

import static java.math.BigInteger.ZERO;

@Data
public class FiscalOverview {

	private Map<BalanceType,FiscalBalance> activaMap;
	private Map<BalanceType,FiscalBalance> passivaMap;
	
	private BigInteger afschrijvingAuto = ZERO;

	@Deprecated
	private BigInteger afschrijvingAutoCorrectie = ZERO;

	private BigInteger afschrijvingOverig = ZERO;

	// Will be removed in 2017
	@Deprecated
	private BigInteger afschrijvingOverigCorrectie = ZERO;

	private BigInteger afschrijvingTotaal = ZERO;
	private BigInteger bijtellingAuto = ZERO;
	private BigInteger bookTotalBegin = ZERO;
	private BigInteger bookTotalEnd = ZERO;
	private BigInteger enterpriseCapital = ZERO;
    private BigInteger enterpriseCapitalPreviousYear = ZERO;
	private BigInteger interestFromBusinessSavings = ZERO;
	private BigInteger investmentDeduction = ZERO;
	private int jaar;
	private BigInteger kostenAuto = ZERO;
	private BigInteger kostenAutoAftrekbaar = ZERO;
	private BigInteger kostenOverig = ZERO;
	private BigInteger kostenOverigTransport = ZERO;
	private BigInteger nettoOmzet = ZERO;
	private PrivateWithdrawal onttrekking;
	private BigInteger oudedagsReserveMaximaal = ZERO;

	private PrepaidTax prepaidTax;
	private BigInteger privateDeposit = ZERO;
	private BigInteger profit = ZERO;
	private BigInteger repurchase = ZERO;
	private BigInteger settlementCosts = ZERO;
	private BigInteger settlementDepreciation = ZERO;
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
