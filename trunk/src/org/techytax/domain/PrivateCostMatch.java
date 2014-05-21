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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@NamedQueries({
	@NamedQuery(name = PrivateCostMatch.FOR_TYPE, query = "SELECT cm FROM PrivateCostMatch cm WHERE cm.user = :user AND cm.costType = :costType")
})
@Table(name = "kostmatch_private")
public class PrivateCostMatch extends CostMatchParent {
	
	public static final String FOR_TYPE = "org.techytax.domain.PrivateCostMatch.FOR_TYPE";

	@ManyToOne
	@JoinColumn(name = "user_id", updatable=false)
	private UserEntity user;
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = new UserEntity(user);
	}

	@OneToOne(mappedBy = "privateCostMatch", cascade = CascadeType.ALL, orphanRemoval = true)
	private VatMatchPrivate vatMatchPrivate;

	@OneToOne(mappedBy = "privateCostMatch", cascade = CascadeType.ALL, orphanRemoval = true)
	private SplitMatch splitMatch;
	
	@Column(name = "match_text")
	@Type(type = "encryptedString")	
	protected String matchText;
	
	public String getMatchText() {
		return matchText;
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

}
