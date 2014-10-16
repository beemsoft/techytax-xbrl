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

import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
public class Settlement extends Activum {
	
	public static final String GET = "Settlement.GET";

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

}
