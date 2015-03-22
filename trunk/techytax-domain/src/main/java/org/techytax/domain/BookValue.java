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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Type;

@Entity
@NamedQueries({
		@NamedQuery(name = BookValue.HISTORY, query = "SELECT bv FROM BookValue bv WHERE bv.user = :user order by bv.balanceType asc, bv.year desc"),
		@NamedQuery(name = BookValue.FOR_YEAR, query = "SELECT bv FROM BookValue bv WHERE bv.user = :user AND bv.year = :year order by bv.balanceType asc"),
		@NamedQuery(name = BookValue.GET, query = "SELECT bv FROM BookValue bv WHERE bv.year = :year and bv.user = :user and bv.balanceType = :balanceType"),
		@NamedQuery(name = BookValue.FOR_YEAR_AND_TYPES, query = "SELECT bv FROM BookValue bv WHERE bv.user = :user AND bv.year = :year AND bv.balanceType IN :balanceTypes ORDER BY bv.balanceType asc") })
@Table(name = "boekwaarde")
@Getter
@Setter
public class BookValue extends UserObject {

	public static final String HISTORY = "org.techytax.domain.BookValue.HISTORY";
	public static final String FOR_YEAR = "org.techytax.domain.BookValue.FOR_YEAR";
	public static final String GET = "org.techytax.domain.BookValue.GET";
	public static final String FOR_YEAR_AND_TYPES = "org.techytax.domain.BookValue.FOR_YEAR_AND_TYPES";

	@Column(name = "balans_id")
	@Enumerated(EnumType.ORDINAL)
	private BalanceType balanceType;

	@Column(name = "boekjaar")
	private int year;

	@Type(type = "encryptedInteger")
	private BigInteger saldo;

	public BookValue() {
	}

	public BookValue(BalanceType balanceType, int year, BigInteger saldo) {
		this.balanceType = balanceType;
		this.year = year;
		this.saldo = saldo;
	}

	public String getDescription() {
		return balanceType.getKey();
	}

    @Override
    public String toString() {
        return balanceType + "," + year + "," + saldo;
    }
}
