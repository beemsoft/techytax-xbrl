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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jasypt.hibernate4.type.EncryptedBigDecimalType;
import org.jasypt.hibernate4.type.EncryptedStringType;

@TypeDefs({
	@TypeDef(name = "encryptedString", typeClass = EncryptedStringType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "strongHibernateStringEncryptor") }),
	@TypeDef(name = "encryptedBigDecimal", typeClass = EncryptedBigDecimalType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "bigDecimalEncryptor"),
			@Parameter(name = "decimalScale", value = "2") }) })
@Entity(name = "org.techytax.domain.PrivateCostMatch")
@Table(name = "kostmatch_private")
public class PrivateCostMatch extends CostMatchParent {

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
	@OneToOne(mappedBy = "privateCostMatch", cascade = CascadeType.ALL)
	private VatMatchPrivate vatMatchPrivate;

	@OneToOne(mappedBy = "privateCostMatch", cascade = CascadeType.ALL)
	private SplitMatch splitMatch;
	
	@Column(name = "match_text")
	@Type(type = "encryptedString")	
	protected String matchText;
	
	public String getMatchText() {
		return matchText;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = new UserEntity(user);
	}

	public void setMatchText(String matchText) {
		this.matchText = matchText;
	}

	public VatMatchPrivate getVatMatchPrivate() {
		return vatMatchPrivate;
	}

	public void setVatMatchPrivate(VatMatchPrivate vatMatchPrivate) {
		this.vatMatchPrivate = vatMatchPrivate;
	}

	public SplitMatch getSplitMatch() {
		return splitMatch;
	}

	public void setSplitMatch(SplitMatch splitMatch) {
		this.splitMatch = splitMatch;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
