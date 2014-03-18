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
package org.techytax.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.techytax.domain.BalanceType;
import org.techytax.domain.BookValue;
import org.techytax.domain.User;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.zkplus.jpa.JpaUtil;

public class BookValueDao extends BaseDao {

	private final User user = UserCredentialManager.getUser();

	@SuppressWarnings("unchecked")
	public List<BookValue> getBookValuesHistory() throws Exception {
		Query query = JpaUtil.getEntityManager().createQuery("SELECT bv FROM org.techytax.domain.BookValue bv WHERE bv.user = :user order by bv.balanceType asc, bv.jaar desc");
		query.setParameter("user", user);
		List<BookValue> result = query.getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<BookValue> getBookValuesForYear(int year) throws Exception {
		Query query = JpaUtil.getEntityManager().createQuery("SELECT bv FROM org.techytax.domain.BookValue bv WHERE bv.user = :user AND bv.jaar = :year order by bv.balanceType asc");
		query.setParameter("user", user);
		query.setParameter("year", year);
		List<BookValue> result = query.getResultList();
		return result;
	}

	public BookValue getBookValue(BalanceType balanceType, int year) throws Exception {
		TypedQuery<BookValue> query = JpaUtil.getEntityManager().createQuery(
				"SELECT bv FROM org.techytax.domain.BookValue bv WHERE bv.jaar = :year and bv.user = :user and bv.balanceType = :balanceType", BookValue.class);
		query.setParameter("year", year);
		query.setParameter("user", user);
		query.setParameter("balanceType", balanceType);
		BookValue result = null;
		try {
			result = query.getSingleResult();
		} catch (NoResultException e) {
			// ok
		}
		return result;
	}

	@Deprecated
	public BookValue getBookValueForActivum(BookValue bookValue) throws Exception {
		TypedQuery<BookValue> query = JpaUtil.getEntityManager().createQuery(
				"SELECT bv FROM org.techytax.domain.BookValue bv WHERE bv.jaar = :year and bv.user = :user and bv.balanceType = :balanceType", BookValue.class);
		query.setParameter("year", bookValue.getJaar());
		query.setParameter("user", user);
		query.setParameter("balanceType", bookValue.getBalanceType());
		BookValue result = query.getSingleResult();
		return result;
	}

}
