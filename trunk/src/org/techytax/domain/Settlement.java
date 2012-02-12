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
package org.techytax.domain;

import java.math.BigInteger;


public class Settlement extends Activum {

	private String address;
	private String description;
	private String startDate;
	private int nofSquareMetersBusiness;
	private int nofSquareMetersTotal;
	private BigInteger wozValue;
	private BigInteger terrainValue; // Waarde van de grond, die bij het pand hoort
	private int nofYearsForDepreciation;
	private BigInteger eigenWoningForfaitBusiness;
	private BigInteger eigenWoningForfaitPrivate;	
	private BigInteger fictiefRendement;
	
	public int getNofSquareMetersBusiness() {
		return nofSquareMetersBusiness;
	}
	public void setNofSquareMetersBusiness(int nofSquareMetersBusiness) {
		this.nofSquareMetersBusiness = nofSquareMetersBusiness;
	}
	public int getNofSquareMetersTotal() {
		return nofSquareMetersTotal;
	}
	public void setNofSquareMetersTotal(int nofSquareMetersTotal) {
		this.nofSquareMetersTotal = nofSquareMetersTotal;
	}
	public BigInteger getWozValue() {
		return wozValue;
	}
	public void setWozValue(BigInteger wozValue) {
		this.wozValue = wozValue;
	}
	public int getNofYearsForDepreciation() {
		return nofYearsForDepreciation;
	}
	public void setNofYearsForDepreciation(int nofYearsForDepreciation) {
		this.nofYearsForDepreciation = nofYearsForDepreciation;
	}
	public BigInteger getTerrainValue() {
		return terrainValue;
	}
	public void setTerrainValue(BigInteger terrainValue) {
		this.terrainValue = terrainValue;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public BigInteger getFictiefRendement() {
		return fictiefRendement;
	}
	public void setFictiefRendement(BigInteger fictiefRendement) {
		this.fictiefRendement = fictiefRendement;
	}
	public BigInteger getEigenWoningForfaitBusiness() {
		return eigenWoningForfaitBusiness;
	}
	public void setEigenWoningForfaitBusiness(BigInteger eigenWoningForfaitBusiness) {
		this.eigenWoningForfaitBusiness = eigenWoningForfaitBusiness;
	}
	public BigInteger getEigenWoningForfaitPrivate() {
		return eigenWoningForfaitPrivate;
	}
	public void setEigenWoningForfaitPrivate(BigInteger eigenWoningForfaitPrivate) {
		this.eigenWoningForfaitPrivate = eigenWoningForfaitPrivate;
	}
	public String getdescription() {
		return description;
	}
	public void setdescription(String description) {
		this.description = description;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

}
