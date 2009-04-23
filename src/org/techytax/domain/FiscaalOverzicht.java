/**
 * Copyright 2009 Hans Beemsterboer
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

import java.util.List;

public class FiscaalOverzicht {

	private List<Activa> activa;

	private int afschrijvingAuto;

	private int afschrijvingOverig;

	private int bijtellingAuto;

	private int jaar;

	private int kostenAuto;

	private int kostenOverig;

	private int kostenOverigTransport;

	private int nettoOmzet;

	private PriveOnttrekking onttrekking;

	private int oudedagsReserveMaximaal;

	private List<Passiva> passiva;

	private int winst;

	public List<Activa> getActiva() {
		return activa;
	}

	public int getAfschrijvingAuto() {
		return afschrijvingAuto;
	}

	public int getAfschrijvingOverig() {
		return afschrijvingOverig;
	}

	public int getBijtellingAuto() {
		return bijtellingAuto;
	}

	public int getJaar() {
		return jaar;
	}

	public int getKostenAuto() {
		return kostenAuto;
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

	public int getOudedagsReserveMaximaal() {
		return oudedagsReserveMaximaal;
	}

	public List<Passiva> getPassiva() {
		return passiva;
	}

	public int getWinst() {
		return winst;
	}

	public void setActiva(List<Activa> activa) {
		this.activa = activa;
	}

	public void setAfschrijvingAuto(int afschrijvingAuto) {
		this.afschrijvingAuto = afschrijvingAuto;
	}

	public void setAfschrijvingOverig(int afschrijvingOverig) {
		this.afschrijvingOverig = afschrijvingOverig;
	}

	public void setBijtellingAuto(int bijtellingAuto) {
		this.bijtellingAuto = bijtellingAuto;
	}

	public void setJaar(int jaar) {
		this.jaar = jaar;
	}

	public void setKostenAuto(int kostenAuto) {
		this.kostenAuto = kostenAuto;
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

	public void setOudedagsReserveMaximaal(int oudedagsReserveMaximaal) {
		this.oudedagsReserveMaximaal = oudedagsReserveMaximaal;
	}

	public void setPassiva(List<Passiva> passiva) {
		this.passiva = passiva;
	}

	public void setWinst(int winst) {
		this.winst = winst;
	}

	public PriveOnttrekking getOnttrekking() {
		return onttrekking;
	}

	public void setOnttrekking(PriveOnttrekking onttrekking) {
		this.onttrekking = onttrekking;
	}

}
