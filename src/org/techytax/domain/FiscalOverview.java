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
package org.techytax.domain;

import java.math.BigInteger;
import java.util.List;

public class FiscalOverview {

	private List<Activa> activa;

	private int afschrijvingAuto;
	
	private int afschrijvingAutoCorrectie;

	private int afschrijvingOverig;
	
	private int afschrijvingOverigCorrectie;
	
	private int afschrijvingTotaal;

	private int bijtellingAuto;

	private int bookTotalBegin;

	private int bookTotalEnd;

	private int jaar;

	private int kostenAuto;
	
	private int kostenAutoAftrekbaar;	

	private int kostenOverig;

	private int kostenOverigTransport;

	private int nettoOmzet;

	private PrivatWithdrawal onttrekking;
	
	private int oudedagsReserveMaximaal;

	private List<Passiva> passiva;

	private PrepaidTax prepaidTax;

	private int profit;
	
	private BigInteger investmentDeduction;
	
	public List<Activa> getActiva() {
		return activa;
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
	
	public int getOudedagsReserveMaximaal() {
		return oudedagsReserveMaximaal;
	}	

	public List<Passiva> getPassiva() {
		return passiva;
	}

	public PrepaidTax getPrepaidTax() {
		return prepaidTax;
	}

	public int getWinst() {
		return profit;
	}

	public void setActiva(List<Activa> activa) {
		this.activa = activa;
	}

	public void setAfschrijvingAuto(int afschrijvingAuto) {
		this.afschrijvingAuto = afschrijvingAuto;
	}

	public void setAfschrijvingAutoCorrectie(int afschrijvingAutoCorrectie) {
		this.afschrijvingAutoCorrectie = afschrijvingAutoCorrectie;
	}

	public void setAfschrijvingOverig(int afschrijvingOverig) {
		this.afschrijvingOverig = afschrijvingOverig;
	}

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

	public void setPassiva(List<Passiva> passiva) {
		this.passiva = passiva;
	}

	public void setPrepaidTax(PrepaidTax prepaidTax) {
		this.prepaidTax = prepaidTax;
	}

	public void setProfit(int winst) {
		this.profit = winst;
	}

	public BigInteger getInvestmentDeduction() {
		return investmentDeduction;
	}

	public void setInvestmentDeduction(BigInteger investmentDeduction) {
		this.investmentDeduction = investmentDeduction;
	}

}
