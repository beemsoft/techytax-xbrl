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

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.techytax.helper.DepreciationHelper;

@Entity
@NamedQueries({
		@NamedQuery(name = Activum.NEW_ACTIVA, query = "SELECT act FROM Activum act WHERE act.balanceType = :balanceType AND act.cost.date >= :beginDate AND act.cost.date <= :endDate AND (act.startDate = null OR act.startDate <= :startDate) AND act.endDate = null AND act.user = :user"),
		@NamedQuery(name = Activum.ALL_ACTIVA, query = "SELECT act FROM org.techytax.domain.Activum act WHERE act.user = :user ORDER BY act.cost.date ASC"),
		@NamedQuery(name = Activum.GET_ACTIVUM_FOR_COST, query = "SELECT act FROM Activum act WHERE act.user = :user AND act.cost = :cost"),
		@NamedQuery(name = Activum.ACTIVE_ACTIVA, query = "SELECT act FROM org.techytax.domain.Activum act WHERE act.user = :user AND act.endDate = null ORDER BY act.cost.date ASC"),
		@NamedQuery(name = Activum.ACTIVE_ACTIVA_FOR_TYPE, query = "SELECT act FROM org.techytax.domain.Activum act WHERE act.balanceType = :balanceType AND act.user = :user AND act.endDate = null AND (act.startDate = null OR act.startDate <= :startDate) ORDER BY act.cost.date ASC") })
@Table(name = "activa")
@Inheritance(strategy = InheritanceType.JOINED)
public class Activum extends UserObject {

	public static final String NEW_ACTIVA = "org.techytax.domain.Activum.NEW_ACTIVA";
	public static final String ALL_ACTIVA = "org.techytax.domain.Activum.ALL_ACTIVA";
	public static final String GET_ACTIVUM_FOR_COST = "org.techytax.domain.Activum.GET_ACTIVUM_FOR_COST";
	public static final String ACTIVE_ACTIVA = "org.techytax.domain.Activum.ACTIVE_ACTIVA";
	public static final String ACTIVE_ACTIVA_FOR_TYPE = "org.techytax.domain.Activum.ACTIVE_ACTIVA_FOR_TYPE";

	@OneToOne(mappedBy = "activum", cascade = CascadeType.ALL)
	private RemainingValue restwaardeOld;

	@Type(type = "encryptedInteger")
	private BigInteger remainingValue;

	@Column(name = "einddatum")
	private Date endDate;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "kost_id")
	private Cost cost;

	@Column(name = "balans_id")
	@Enumerated(EnumType.ORDINAL)
	private BalanceType balanceType;

	private Date startDate;

	private int nofYearsForDepreciation;

	public BalanceType getBalanceType() {
		return balanceType;
	}

	public void setBalanceType(BalanceType balanceType) {
		this.balanceType = balanceType;
	}

	public String getOmschrijving() {
		return balanceType.getKey();
	}

	public BigInteger getRemainingValue() {
		return remainingValue;
	}

	public RemainingValue getRestwaarde() {
		return restwaardeOld;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public long getCostId() {
		return cost.getId();
	}

	public int getNofYearsForDepreciation() {
		return nofYearsForDepreciation;
	}

	public void setNofYearsForDepreciation(int nofYearsForDepreciation) {
		this.nofYearsForDepreciation = nofYearsForDepreciation;
	}

	public Date getStartDate() {
		if (startDate != null) {
			return startDate;
		} else {
			return cost.getDate();
		}
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setRemainingValue(BigInteger remainingValue) {
		this.remainingValue = remainingValue;
	}

	public BigInteger getDepreciation() {
		DepreciationHelper depreciationHelper = new DepreciationHelper();
		return depreciationHelper.getDepreciation(this);
	}

}
