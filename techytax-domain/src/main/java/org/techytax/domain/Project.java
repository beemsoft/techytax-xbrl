/**
 * Copyright 2015 Hans Beemsterboer
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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "project")
@Getter
@Setter
public class Project extends UserObject {

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Type(type = "encryptedString")
	private String code;
	
	@Type(type = "encryptedString")
	@Column(name = "project_description")
	private String projectDescription;
	
	@Type(type = "encryptedString")
	@Column(name = "activity_description")
	private String activityDescription;
	
	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;
	
	@Type(type = "encryptedBigDecimal")
	@Column(precision=50, scale=2)
	private BigDecimal rate;
	
	@Enumerated(EnumType.ORDINAL)
	private VatType vatType = VatType.HIGH;

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Project)) {
			return false;
		}
		Project other = (Project)obj;
		return this.id.equals(other.id);
	}

}
