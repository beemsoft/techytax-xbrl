/**
 * Copyright 2015 Hans Beemsterboer
 * <p/>
 * This file is part of the TechyTax program.
 * <p/>
 * TechyTax is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * TechyTax is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with TechyTax; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.techytax.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Type;

@Entity
@NamedQuery(name = AccountBalance.BY_ACCOUNT, query = "SELECT ab FROM AccountBalance ab WHERE ab.account = :account AND ab.user = :user")
@Table(name = "account_balance")
@Getter
@Setter
public class AccountBalance extends UserObject implements Comparable<AccountBalance> {

    public final static String BY_ACCOUNT = "AccountBalance.BY_ACCOUNT";

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Type(type = "encryptedBigDecimal")
    @Column(precision = 50, scale = 2)
    private BigDecimal balance;

    private Date datum;

    public int compareTo(AccountBalance o) {
        return datum.compareTo(o.getDatum());
    }
}
