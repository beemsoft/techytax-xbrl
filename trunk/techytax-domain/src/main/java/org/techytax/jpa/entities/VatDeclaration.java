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
package org.techytax.jpa.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

import org.techytax.domain.UserObject;

@Entity
@Table(name = "vat_declaration")
@NamedQuery(name="VatDeclaration.findUnpaid", query="SELECT vatDeclaration FROM VatDeclaration vatDeclaration WHERE vatDeclaration.user = ? AND timeStampPaid = null")
@Getter
@Setter
public class VatDeclaration extends UserObject {

	@Column(name = "declaration_number")
	private String declarationNumber;

	@Column(name = "payment_feature")
	private String paymentFeature;

	@Column(name = "declaration_feature")
	private String declarationFeature;

	@Column(name = "timestamp_declared")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeStampDeclared;

	@Column(name = "timestamp_paid")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeStampPaid;

}
