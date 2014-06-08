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

import org.techytax.report.domain.BalanceReport;
import org.techytax.report.helper.FiscalReportHelper;

public class FiscalOverview {

	private Map<BalanceType,FiscalBalance> activaMap;
	private Map<BalanceType,FiscalBalance> passivaMap;
	
	private BigInteger afschrijvingAuto = BigInteger.ZERO;

	@Deprecated
	private BigInteger afschrijvingAutoCorrectie = BigInteger.ZERO;

	private BigInteger afschrijvingOverig = BigInteger.ZERO;

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

	public BigInteger getAfschrijvingAuto() {
		return afschrijvingAuto;
	}

	@Deprecated
	public BigInteger getAfschrijvingAutoCorrectie() {
		return afschrijvingAutoCorrectie;
	}

	public BigInteger getAfschrijvingOverig() {
		return afschrijvingOverig;
	}

	@Deprecated
	// Will be removed in 2017
	public BigInteger getAfschrijvingOverigCorrectie() {
		return afschrijvingOverigCorrectie;
	}

	public BigInteger getAfschrijvingTotaal() {
		return afschrijvingTotaal;
	}

	public BigInteger getBijtellingAuto() {
		return bijtellingAuto;
	}

	public BigInteger getBookTotalBegin() {
		return bookTotalBegin;
	}

	public BigInteger getBookTotalEnd() {
		return bookTotalEnd;
	}

	public BigInteger getCarAndTransportCosts() {
		return kostenAutoAftrekbaar.subtract(kostenOverigTransport);
	}

	public BigInteger getEnterpriseCapital() {
		return enterpriseCapital;
	}

	public BigInteger getInterestFromBusinessSavings() {
		return interestFromBusinessSavings;
	}

	public BigInteger getInvestmentDeduction() {
		return investmentDeduction;
	}

	public int getJaar() {
		return jaar;
	}

	public BigInteger getKostenAuto() {
		return kostenAuto;
	}

	public BigInteger getKostenAutoAftrekbaar() {
		return kostenAutoAftrekbaar;
	}

	public BigInteger getKostenOverig() {
		return kostenOverig;
	}

	public BigInteger getKostenOverigTransport() {
		return kostenOverigTransport;
	}

	public BigInteger getNettoOmzet() {
		return nettoOmzet;
	}

	public PrivateWithdrawal getOnttrekking() {
		return onttrekking;
	}

	public BigInteger getOtherCostsTotal() {
		return getCarAndTransportCosts().subtract(kostenOverig.add(settlementCosts));
	}

	public BigInteger getOudedagsReserveMaximaal() {
		return oudedagsReserveMaximaal;
	}

	public BalanceReport getPassivaReport() {
		return FiscalReportHelper.getPassivaReport(passivaMap);
	}

	public PrepaidTax getPrepaidTax() {
		return prepaidTax;
	}

	public BigInteger getPrivateDeposit() {
		return privateDeposit;
	}

	public BigInteger getProfit() {
		return profit;
	}

	public BigInteger getRepurchase() {
		return repurchase;
	}

	public BigInteger getSettlementCosts() {
		return settlementCosts;
	}

	public BigInteger getSettlementDepreciation() {
		return settlementDepreciation;
	}

	public BigDecimal getTurnOverUnpaid() {
		return turnOverUnpaid;
	}

	public BigInteger getWinst() {
		return profit;
	}

	public void setAfschrijvingAuto(BigInteger afschrijvingAuto) {
		this.afschrijvingAuto = afschrijvingAuto;
	}

	@Deprecated
	public void setAfschrijvingAutoCorrectie(BigInteger afschrijvingAutoCorrectie) {
		this.afschrijvingAutoCorrectie = afschrijvingAutoCorrectie;
	}

	public void setAfschrijvingOverig(BigInteger afschrijvingOverig) {
		this.afschrijvingOverig = afschrijvingOverig;
	}

	@Deprecated
	public void setAfschrijvingOverigCorrectie(BigInteger afschrijvingOverigCorrectie) {
		this.afschrijvingOverigCorrectie = afschrijvingOverigCorrectie;
	}

	public void setAfschrijvingTotaal(BigInteger afschrijvingTotaal) {
		this.afschrijvingTotaal = afschrijvingTotaal;
	}

	public void setBijtellingAuto(BigInteger bijtellingAuto) {
		this.bijtellingAuto = bijtellingAuto;
	}

	public void setBookTotalBegin(BigInteger bookTotalBegin) {
		this.bookTotalBegin = bookTotalBegin;
	}

	public void setBookTotalEnd(BigInteger bookTotalEnd) {
		this.bookTotalEnd = bookTotalEnd;
	}

	public void setEnterpriseCapital(BigInteger enterpriseCapital) {
		this.enterpriseCapital = enterpriseCapital;
	}

	public void setInterestFromBusinessSavings(BigInteger interestFromBusinessSavings) {
		this.interestFromBusinessSavings = interestFromBusinessSavings;
	}

	public void setInvestmentDeduction(BigInteger investmentDeduction) {
		this.investmentDeduction = investmentDeduction;
	}

	public void setJaar(int jaar) {
		this.jaar = jaar;
	}

	public void setKostenAuto(BigInteger kostenAuto) {
		this.kostenAuto = kostenAuto;
	}

	public void setKostenAutoAftrekbaar(BigInteger kostenAutoAftrekbaar) {
		this.kostenAutoAftrekbaar = kostenAutoAftrekbaar;
	}

	public void setKostenOverig(BigInteger kostenOverig) {
		this.kostenOverig = kostenOverig;
	}

	public void setKostenOverigTransport(BigInteger kostenOverigTransport) {
		this.kostenOverigTransport = kostenOverigTransport;
	}

	public void setNettoOmzet(BigInteger nettoOmzet) {
		this.nettoOmzet = nettoOmzet;
	}

	public void setOnttrekking(PrivateWithdrawal onttrekking) {
		this.onttrekking = onttrekking;
	}

	public void setOudedagsReserveMaximaal(BigInteger oudedagsReserveMaximaal) {
		this.oudedagsReserveMaximaal = oudedagsReserveMaximaal;
	}

	public void setPrepaidTax(PrepaidTax prepaidTax) {
		this.prepaidTax = prepaidTax;
	}

	public void setPrivateDeposit(BigInteger privateDeposit) {
		this.privateDeposit = privateDeposit;
	}

	public void setProfit(BigInteger winst) {
		this.profit = winst;
	}

	public void setRepurchase(BigInteger repurchase) {
		this.repurchase = repurchase;
	}

	public void setSettlementCosts(BigInteger settlementCosts) {
		this.settlementCosts = settlementCosts;
	}

	public void setSettlementDepreciation(BigInteger settlementDepreciation) {
		this.settlementDepreciation = settlementDepreciation;
	}

	public void setTurnOverUnpaid(BigDecimal turnOverUnpaid) {
		this.turnOverUnpaid = turnOverUnpaid;
	}

	public void setActivaMap(Map<BalanceType, FiscalBalance> activaMap) {
		this.activaMap = activaMap;
	}

	public Map<BalanceType, FiscalBalance> getPassivaMap() {
		return passivaMap;
	}

	public void setPassivaMap(Map<BalanceType, FiscalBalance> passivaMap) {
		this.passivaMap = passivaMap;
	}

	public Map<BalanceType, FiscalBalance> getActivaMap() {
		return activaMap;
	}

}
