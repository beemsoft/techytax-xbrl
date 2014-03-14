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

import org.techytax.domain.BalanceType;
import org.techytax.domain.BookValue;
import org.techytax.domain.KeyId;
import org.techytax.domain.RemainingValue;
import org.techytax.domain.User;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.zkplus.jpa.JpaUtil;

public class BookValueDao extends BaseDao {

	private final User user = UserCredentialManager.getUser();

	private void encrypt(RemainingValue restwaarde) {
		restwaarde.setRestwaarde(intEncryptor.encrypt(restwaarde.getRestwaarde()));
	}

	private void decrypt(BookValue boekwaarde) {
		if (boekwaarde != null) {
			boekwaarde.setSaldo(intEncryptor.decrypt(boekwaarde.getSaldo()));
		}
	}
	
	private void decrypt(RemainingValue remainingValue) {
		if (remainingValue != null) {
			remainingValue.setRestwaarde(intEncryptor.decrypt(remainingValue.getRestwaarde()));
		}
	}	

	@Deprecated
	@SuppressWarnings("unchecked")
	public List<BookValue> getAllBoekwaardes() throws Exception {
		return sqlMap.queryForList("getAllBoekwaardes", null);
	}

	@Deprecated
	@SuppressWarnings("unchecked")
	public List<RemainingValue> getAllRestwaardes() throws Exception {
		return sqlMap.queryForList("getAllRestwaardes", null);
	}

	@Deprecated
	public void updateRestwaarde(RemainingValue restwaarde) throws Exception {
		encrypt(restwaarde);
		sqlMap.insert("updateRestwaarde", restwaarde);
	}
	
	@Deprecated
	public void updateRemainingValueByActivumId(RemainingValue restwaarde) throws Exception {
		encrypt(restwaarde);
		sqlMap.insert("updateRemainingValueByActivumId", restwaarde);
	}	
	
	@Deprecated
	public void insertRemainingValue(RemainingValue restwaarde) throws Exception {
		encrypt(restwaarde);
		sqlMap.insert("insertRestwaarde", restwaarde);
	}
	
	@SuppressWarnings("unchecked")
	public List<BookValue> getBookValuesHistory() throws Exception {
		Query query = JpaUtil.getEntityManager().createQuery("SELECT bv FROM org.techytax.domain.BookValue bv WHERE bv.user = :user order by bv.balanceType asc, bv.jaar desc");
		query.setParameter("user", user);
		List<BookValue> result = query.getResultList();
		return result;
	}	
	
	public BookValue getBookValue(BalanceType balanceType, int year) throws Exception {
		Query query = JpaUtil.getEntityManager().createQuery("SELECT bv FROM org.techytax.domain.BookValue bv WHERE bv.jaar = :year and bv.user = :user and bv.balanceType = :balanceType");
		query.setParameter("year", year);		
		query.setParameter("user", user);
		query.setParameter("balanceType", balanceType);
		BookValue result = null;
		try {
			result = (BookValue) query.getSingleResult();
		} catch (NoResultException e) {
			// ok
		}
		return result;
	}
	
	@Deprecated
	public BookValue getBookValueForActivum(BookValue bookValue) throws Exception {
		Query query = JpaUtil.getEntityManager().createQuery("SELECT bv FROM org.techytax.domain.BookValue bv WHERE bv.jaar = :year and bv.user = :user and bv.balanceType = :balanceType");
		query.setParameter("year", bookValue.getJaar());		
		query.setParameter("user", user);
		query.setParameter("balanceType", bookValue.getBalanceType());
		BookValue result = (BookValue) query.getSingleResult();
		return result;
	}	
	
	@Deprecated
	@SuppressWarnings("unchecked")
	public List<BookValue> getBookValuesForChart(KeyId key) throws Exception {
		List<BookValue> bookValues = sqlMap.queryForList("getBookValuesForChart", key);
		for (BookValue boekwaarde: bookValues) {
			decrypt(boekwaarde);
		}
		return bookValues;
	}	
	
	@Deprecated
	@SuppressWarnings("unchecked")
	public List<RemainingValue> getRemainingValueForMachines(KeyId key) throws Exception {
		List<RemainingValue> remainingValues = sqlMap.queryForList("getRemainingValueForMachines", key);
		for (RemainingValue remainingValue: remainingValues) {
			decrypt(remainingValue);
		}
		return remainingValues;
	}
	
}
