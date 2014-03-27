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

import static org.techytax.domain.BalanceType.CAR;
import static org.techytax.domain.BalanceType.CURRENT_ASSETS;
import static org.techytax.domain.BalanceType.INVOICES_TO_BE_PAID;
import static org.techytax.domain.BalanceType.MACHINERY;
import static org.techytax.domain.BalanceType.NON_CURRENT_ASSETS;
import static org.techytax.domain.BalanceType.OFFICE;
import static org.techytax.domain.BalanceType.PENSION;
import static org.techytax.domain.BalanceType.STOCK;
import static org.techytax.domain.BalanceType.VAT_TO_BE_PAID;

import java.util.Arrays;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.techytax.domain.Activum;
import org.techytax.domain.BalanceType;
import org.techytax.domain.BookValue;
import org.techytax.domain.Cost;
import org.techytax.domain.User;
import org.techytax.util.DateHelper;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.zkplus.jpa.JpaUtil;

public class FiscalDao extends BaseDao {

	private final User user = UserCredentialManager.getUser();

	public List<BookValue> getPassivaList(int year) throws Exception {
		List<BalanceType> balanceTypes = Arrays.asList(NON_CURRENT_ASSETS, PENSION, VAT_TO_BE_PAID);
		return getBookValuesForBalanceTypes(year, balanceTypes);
	}

	private List<BookValue> getBookValuesForBalanceTypes(int year, List<BalanceType> balanceTypes) {
		TypedQuery<BookValue> query = JpaUtil.getEntityManager().createQuery(
				"SELECT bv FROM org.techytax.domain.BookValue bv WHERE bv.user = :user AND bv.jaar = :year AND bv.balanceType IN :balanceTypes ORDER BY bv.balanceType asc", BookValue.class);
		query.setParameter("user", user);
		query.setParameter("year", year);
		query.setParameter("balanceTypes", balanceTypes);
		List<BookValue> result = query.getResultList();
		return result;
	}

	public List<BookValue> getActivaList(int year) throws Exception {
		List<BalanceType> balanceTypes = Arrays.asList(MACHINERY, CAR, CURRENT_ASSETS, STOCK, OFFICE, INVOICES_TO_BE_PAID);
		return getBookValuesForBalanceTypes(year, balanceTypes);
	}

	public Activum getActivumForCost(Cost cost) throws Exception {
		TypedQuery<Activum> query = JpaUtil.getEntityManager().createQuery("SELECT act FROM org.techytax.domain.Activum act WHERE act.user = :user AND act.cost = :cost", Activum.class);
		query.setParameter("user", user);
		query.setParameter("cost", cost);
		Activum result = null;
		try {
			result = query.getSingleResult();
		} catch (NoResultException e) {
			// ok
		}
		return result;
	}

	public Activum getActivum(BalanceType balanceType) throws Exception {
		TypedQuery<Activum> query = JpaUtil.getEntityManager().createQuery("SELECT act FROM org.techytax.domain.Activum act WHERE act.user = :user AND act.balanceType = :balanceType", Activum.class);
		query.setParameter("user", user);
		query.setParameter("balanceType", balanceType);
		Activum result = null;
		try {
			result = query.getSingleResult();
		} catch (NoResultException e) {
			// ok
		}
		return result;
	}

	public List<Activum> getAllActiva() throws Exception {
		TypedQuery<Activum> query = JpaUtil.getEntityManager().createQuery("SELECT act FROM org.techytax.domain.Activum act WHERE act.user = :user ORDER BY act.cost.date ASC", Activum.class);
		query.setParameter("user", user);
		List<Activum> result = query.getResultList();
		return result;
	}

	public List<Activum> getActiveActiva() throws Exception {
		TypedQuery<Activum> query = JpaUtil.getEntityManager().createQuery("SELECT act FROM org.techytax.domain.Activum act WHERE act.user = :user AND act.endDate = null ORDER BY act.cost.date ASC",
				Activum.class);
		query.setParameter("user", user);
		List<Activum> result = query.getResultList();
		return result;
	}

	public List<Activum> getActiveActiva(BalanceType balanceType) throws Exception {
		TypedQuery<Activum> query = JpaUtil.getEntityManager().createQuery(
				"SELECT act FROM org.techytax.domain.Activum act WHERE act.balanceType = :balanceType AND act.user = :user AND act.endDate = null AND (act.startDate = null OR act.startDate <= :startDate) ORDER BY act.cost.date ASC", Activum.class);
		query.setParameter("user", user);
		query.setParameter("balanceType", balanceType);
		query.setParameter("startDate", DateHelper.getLastDayOfFiscalYear());
		List<Activum> result = query.getResultList();
		return result;
	}

}
