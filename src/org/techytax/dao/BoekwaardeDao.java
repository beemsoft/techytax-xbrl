/**
 * Copyright 2011 Hans Beemsterboer
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

import org.techytax.domain.Boekwaarde;
import org.techytax.domain.KeyId;
import org.techytax.domain.RemainingValue;

public class BoekwaardeDao extends BaseDao {

	private void encrypt(Boekwaarde boekwaarde) {
		boekwaarde.setSaldo(intEncryptor.encrypt(boekwaarde.getSaldo()));
	}

	private void encrypt(RemainingValue restwaarde) {
		restwaarde.setRestwaarde(intEncryptor.encrypt(restwaarde.getRestwaarde()));
	}

	private void decrypt(Boekwaarde boekwaarde) {
		if (boekwaarde != null) {
			boekwaarde.setSaldo(intEncryptor.decrypt(boekwaarde.getSaldo()));
		}
	}

	public void insertBoekwaarde(Boekwaarde boekwaarde) throws Exception {
		encrypt(boekwaarde);
		sqlMap.insert("insertBoekwaarde", boekwaarde);
	}

	public Boekwaarde getVorigeBoekwaarde(Boekwaarde boekwaarde) throws Exception {
		Boekwaarde vorigeBoekwaarde = (Boekwaarde) sqlMap.queryForObject("getVorigeBoekwaarde", boekwaarde);
		decrypt(vorigeBoekwaarde);
		return vorigeBoekwaarde;
	}

	public Boekwaarde getBoekwaardeDitJaar(Boekwaarde boekwaarde) throws Exception {
		Boekwaarde boekwaardeDitJaar = (Boekwaarde) sqlMap.queryForObject("getBoekwaardeDitJaar", boekwaarde);
		decrypt(boekwaardeDitJaar);
		return boekwaardeDitJaar;
	}

	@SuppressWarnings("unchecked")
	public List<Boekwaarde> getAllBoekwaardes() throws Exception {
		return sqlMap.queryForList("getAllBoekwaardes", null);
	}

	public void updateBoekwaarde(Boekwaarde boekwaarde) throws Exception {
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
	
	public void insertRemainingValue(RemainingValue restwaarde) throws Exception {
		encrypt(restwaarde);
		sqlMap.insert("insertRestwaarde", restwaarde);
	}
	
	@SuppressWarnings("unchecked")
	public List<Boekwaarde> getBookValues(KeyId key) throws Exception {
		List<Boekwaarde> bookValues = sqlMap.queryForList("getBookValues", key);
		for (Boekwaarde boekwaarde: bookValues) {
			decrypt(boekwaarde);
		}
		return bookValues;
	}
	
	@SuppressWarnings("unchecked")
	public List<Boekwaarde> getBookValuesForChart(KeyId key) throws Exception {
		List<Boekwaarde> bookValues = sqlMap.queryForList("getBookValuesForChart", key);
		for (Boekwaarde boekwaarde: bookValues) {
			decrypt(boekwaarde);
		}
		return bookValues;
	}	

}
