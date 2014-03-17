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
	
	private int afschrijvingAuto;

	@Deprecated
	private int afschrijvingAutoCorrectie;

	private int afschrijvingOverig;

	@Deprecated
	private int afschrijvingOverigCorrectie;

	private int afschrijvingTotaal;
	private int bijtellingAuto;
	private int bookTotalBegin;
	private int bookTotalEnd;
	private BigInteger enterpriseCapital;
	private BigInteger interestFromBusinessSavings;
	private BigInteger investmentDeduction;
	private int jaar;
	private int kostenAuto;
	private int kostenAutoAftrekbaar;
	private int kostenOverig;
	private int kostenOverigTransport;
	private int nettoOmzet;
	private PrivatWithdrawal onttrekking;
	private int oudedagsReserveMaximaal;

	private PrepaidTax prepaidTax;
	private BigInteger privateDeposit;
	private int profit;
	private BigInteger repurchase;
	private int settlementCosts;
	private int settlementDepreciation;
	private BigDecimal turnOverUnpaid;

	public BalanceReport getActivaReport() {
		return FiscalReportHelper.getActivaReport(activaMap);
	}

	public int getAfschrijvingAuto() {
		return afschrijvingAuto;
	}

	@Deprecated
	public int getAfschrijvingAutoCorrectie() {
		return afschrijvingAutoCorrectie;
	}

	public int getAfschrijvingOverig() {
		return afschrijvingOverig;
	}

	@Deprecated
	// Will be removed in 2017
	public int getAfschrijvingOverigCorrectie() {
		return afschrijvingOverigCorrectie;
	}

	public int getAfschrijvingTotaal() {
		return afschrijvingTotaal;
	}

	public int getBijtellingAuto() {
		return bijtellingAuto;
	}

	public int getBookTotalBegin() {
		return bookTotalBegin;
	}

	public int getBookTotalEnd() {
		return bookTotalEnd;
	}

	public int getCarAndTransportCosts() {
		return kostenAutoAftrekbaar - kostenOverigTransport;
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

	public int getKostenAuto() {
		return kostenAuto;
	}

	public int getKostenAutoAftrekbaar() {
		return kostenAutoAftrekbaar;
	}

	public int getKostenOverig() {
		return kostenOverig;
	}

	public int getKostenOverigTransport() {
		return kostenOverigTransport;
	}

	public int getNettoOmzet() {
		return nettoOmzet;
	}

	public PrivatWithdrawal getOnttrekking() {
		return onttrekking;
	}

	public int getOtherCostsTotal() {
		return getCarAndTransportCosts() - kostenOverig - settlementCosts;
	}

	public int getOudedagsReserveMaximaal() {
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

	public int getProfit() {
		return profit;
	}

	public BigInteger getRepurchase() {
		return repurchase;
	}

	public int getSettlementCosts() {
		return settlementCosts;
	}

	public int getSettlementDepreciation() {
		return settlementDepreciation;
	}

	public BigDecimal getTurnOverUnpaid() {
		return turnOverUnpaid;
	}

	public int getWinst() {
		return profit;
	}

	public void setAfschrijvingAuto(int afschrijvingAuto) {
		this.afschrijvingAuto = afschrijvingAuto;
	}

	@Deprecated
	public void setAfschrijvingAutoCorrectie(int afschrijvingAutoCorrectie) {
		this.afschrijvingAutoCorrectie = afschrijvingAutoCorrectie;
	}

	public void setAfschrijvingOverig(int afschrijvingOverig) {
		this.afschrijvingOverig = afschrijvingOverig;
	}

	@Deprecated
	public void setAfschrijvingOverigCorrectie(int afschrijvingOverigCorrectie) {
		this.afschrijvingOverigCorrectie = afschrijvingOverigCorrectie;
	}

	public void setAfschrijvingTotaal(int afschrijvingTotaal) {
		this.afschrijvingTotaal = afschrijvingTotaal;
	}

	public void setBijtellingAuto(int bijtellingAuto) {
		this.bijtellingAuto = bijtellingAuto;
	}

	public void setBookTotalBegin(int bookTotalBegin) {
		this.bookTotalBegin = bookTotalBegin;
	}

	public void setBookTotalEnd(int bookTotalEnd) {
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

	public void setKostenAuto(int kostenAuto) {
		this.kostenAuto = kostenAuto;
	}

	public void setKostenAutoAftrekbaar(int kostenAutoAftrekbaar) {
		this.kostenAutoAftrekbaar = kostenAutoAftrekbaar;
	}

	public void setKostenOverig(int kostenOverig) {
		this.kostenOverig = kostenOverig;
	}

	public void setKostenOverigTransport(int kostenOverigTransport) {
		this.kostenOverigTransport = kostenOverigTransport;
	}

	public void setNettoOmzet(int nettoOmzet) {
		this.nettoOmzet = nettoOmzet;
	}

	public void setOnttrekking(PrivatWithdrawal onttrekking) {
		this.onttrekking = onttrekking;
	}

	public void setOudedagsReserveMaximaal(int oudedagsReserveMaximaal) {
		this.oudedagsReserveMaximaal = oudedagsReserveMaximaal;
	}

	public void setPrepaidTax(PrepaidTax prepaidTax) {
		this.prepaidTax = prepaidTax;
	}

	public void setPrivateDeposit(BigInteger privateDeposit) {
		this.privateDeposit = privateDeposit;
	}

	public void setProfit(int winst) {
		this.profit = winst;
	}

	public void setRepurchase(BigInteger repurchase) {
		this.repurchase = repurchase;
	}

	public void setSettlementCosts(int settlementCosts) {
		this.settlementCosts = settlementCosts;
	}

	public void setSettlementDepreciation(int settlementDepreciation) {
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
