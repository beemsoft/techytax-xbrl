/**
 * Copyright 2012 Hans Beemsterboer
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

import org.techytax.domain.BookValue;
import org.techytax.domain.KeyId;
import org.techytax.domain.RemainingValue;

public class BookValueDao extends BaseDao {

	private void encrypt(BookValue boekwaarde) {
		boekwaarde.setSaldo(intEncryptor.encrypt(boekwaarde.getSaldo()));
	}

	private void encrypt(RemainingValue restwaarde) {
		restwaarde.setRestwaarde(intEncryptor.encrypt(restwaarde.getRestwaarde()));
	}

	private void decrypt(BookValue boekwaarde) {
		if (boekwaarde != null) {
			boekwaarde.setSaldo(intEncryptor.decrypt(boekwaarde.getSaldo()));
		}
	}

	public void insertBoekwaarde(BookValue boekwaarde) throws Exception {
		encrypt(boekwaarde);
		sqlMap.insert("insertBoekwaarde", boekwaarde);
	}

	public BookValue getVorigeBoekwaarde(BookValue boekwaarde) throws Exception {
		BookValue vorigeBoekwaarde = (BookValue) sqlMap.queryForObject("getVorigeBoekwaarde", boekwaarde);
		decrypt(vorigeBoekwaarde);
		return vorigeBoekwaarde;
	}

	public BookValue getBoekwaardeDitJaar(BookValue boekwaarde) throws Exception {
		BookValue boekwaardeDitJaar = (BookValue) sqlMap.queryForObject("getBoekwaardeDitJaar", boekwaarde);
		decrypt(boekwaardeDitJaar);
		return boekwaardeDitJaar;
	}

	@SuppressWarnings("unchecked")
	public List<BookValue> getAllBoekwaardes() throws Exception {
		return sqlMap.queryForList("getAllBoekwaardes", null);
	}

	public void updateBookValue(BookValue boekwaarde) throws Exception {
		encrypt(boekwaarde);
		sqlMap.insert("updateBoekwaarde", boekwaarde);
	}

	@SuppressWarnings("unchecked")
	public List<RemainingValue> getAllRestwaardes() throws Exception {
		return sqlMap.queryForList("getAllRestwaardes", null);
	}

	public void updateRestwaarde(RemainingValue restwaarde) throws Exception {
		encrypt(restwaarde);
		sqlMap.insert("updateRestwaarde", restwaarde);
	}
	
	public void updateRemainingValueByActivumId(RemainingValue restwaarde) throws Exception {
		encrypt(restwaarde);
		sqlMap.insert("updateRemainingValueByActivumId", restwaarde);
	}	
	
	public void insertRemainingValue(RemainingValue restwaarde) throws Exception {
		encrypt(restwaarde);
		sqlMap.insert("insertRestwaarde", restwaarde);
	}
	
	@SuppressWarnings("unchecked")
	public List<BookValue> getBookValues(KeyId key) throws Exception {
		List<BookValue> bookValues = sqlMap.queryForList("getBookValues", key);
		for (BookValue boekwaarde: bookValues) {
			decrypt(boekwaarde);
		}
		return bookValues;
	}
	
	@SuppressWarnings("unchecked")
	public BookValue getBookValue(KeyId key) throws Exception {
		BookValue bookValue = (BookValue) sqlMap.queryForObject("getBookValue", key);
		decrypt(bookValue);
		return bookValue;
	}	
	
	@SuppressWarnings("unchecked")
	public List<BookValue> getBookValuesForChart(KeyId key) throws Exception {
		List<BookValue> bookValues = sqlMap.queryForList("getBookValuesForChart", key);
		for (BookValue boekwaarde: bookValues) {
			decrypt(boekwaarde);
		}
		return bookValues;
	}	

}
