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

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jasypt.hibernate4.type.EncryptedBigDecimalType;
import org.jasypt.hibernate4.type.EncryptedBigIntegerType;
import org.jasypt.hibernate4.type.EncryptedStringType;

@TypeDefs({
	@TypeDef(name = "encryptedString", typeClass = EncryptedStringType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "strongHibernateStringEncryptor") }),
	@TypeDef(name = "encryptedBigDecimal", typeClass = EncryptedBigDecimalType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "bigDecimalEncryptor"),
			@Parameter(name = "decimalScale", value = "2") }),
			@TypeDef(name = "encryptedInteger", typeClass = EncryptedBigIntegerType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "integerEncryptor") }),			
	@TypeDef(name = "encryptedInteger", typeClass = EncryptedBigIntegerType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "integerEncryptor") }) })
@Entity
@NamedQuery(name = Settlement.GET, query = "SELECT s FROM Settlement s WHERE s.user = :user")
@Table(name = "settlement")
@DiscriminatorValue("S")
public class Settlement extends Activum {
	
	public static final String GET = "org.techytax.domain.Settlement.GET";

	@Type(type = "encryptedString")
	private String description;
	
	private BigInteger purchasePrice;
	
	private BigDecimal startupCosts;
	
	private int nofSquareMetersBusiness;
	private int nofSquareMetersPrivate;
	
	private BigInteger wozValue;
	
	private BigInteger terrainValue;
	
//	@Type(type = "encryptedInteger")
//	private BigInteger eigenWoningForfaitBusiness;
//	
//	@Type(type = "encryptedInteger")
//	private BigInteger eigenWoningForfaitPrivate;
//	
//	@Type(type = "encryptedInteger")
//	private BigInteger fictiefRendement;

	public int getNofSquareMetersBusiness() {
		return nofSquareMetersBusiness;
	}

	public void setNofSquareMetersBusiness(int nofSquareMetersBusiness) {
		this.nofSquareMetersBusiness = nofSquareMetersBusiness;
	}

	public int getNofSquareMetersPrivate() {
		return nofSquareMetersPrivate;
	}

	public void setNofSquareMetersPrivate(int nofSquareMetersTotal) {
		this.nofSquareMetersPrivate = nofSquareMetersTotal;
	}

	public BigInteger getWozValue() {
		return wozValue;
	}

	public void setWozValue(BigInteger wozValue) {
		this.wozValue = wozValue;
	}

	public BigInteger getTerrainValue() {
		return terrainValue;
	}

	public void setTerrainValue(BigInteger terrainValue) {
		this.terrainValue = terrainValue;
	}

//	public BigInteger getFictiefRendement() {
//		return fictiefRendement;
//	}
//
//	public void setFictiefRendement(BigInteger fictiefRendement) {
//		this.fictiefRendement = fictiefRendement;
//	}
//
//	public BigInteger getEigenWoningForfaitBusiness() {
//		return eigenWoningForfaitBusiness;
//	}
//
//	public void setEigenWoningForfaitBusiness(BigInteger eigenWoningForfaitBusiness) {
//		this.eigenWoningForfaitBusiness = eigenWoningForfaitBusiness;
//	}
//
//	public BigInteger getEigenWoningForfaitPrivate() {
//		return eigenWoningForfaitPrivate;
//	}
//
//	public void setEigenWoningForfaitPrivate(BigInteger eigenWoningForfaitPrivate) {
//		this.eigenWoningForfaitPrivate = eigenWoningForfaitPrivate;
//	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getStartupCosts() {
		return startupCosts;
	}

	public void setStartupCosts(BigDecimal startupCosts) {
		this.startupCosts = startupCosts;
	}

	public BigInteger getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigInteger purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

}
