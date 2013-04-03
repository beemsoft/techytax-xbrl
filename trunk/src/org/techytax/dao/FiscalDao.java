/**
 * Copyright 2013 Hans Beemsterboer
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

import org.techytax.domain.Activum;
import org.techytax.domain.KeyId;
import org.techytax.domain.KeyYear;
import org.techytax.domain.Passivum;

public class FiscalDao extends BaseDao {

	private void decrypt(Activum activum) {
		activum.setSaldo(intEncryptor.decrypt(activum.getSaldo()));
		activum.setRestwaarde(intEncryptor.decrypt(activum.getRestwaarde()));
		activum.setBedrag(decimalEncryptor.decrypt(activum.getBedrag()));
		if (activum.getBtw() != null && activum.getBtw().doubleValue() != 0) {
			activum.setBtw(decimalEncryptor.decrypt(activum.getBtw()));
		}
		if (activum.getAanschafKosten() != null && activum.getAanschafKosten().doubleValue() != 0) {
			activum.setAanschafKosten(decimalEncryptor.decrypt(activum.getAanschafKosten()));
		}
	}
	
	private void decrypt(Passivum passivum) {
		passivum.setSaldo(intEncryptor.decrypt(passivum.getSaldo()));
	}

	@SuppressWarnings("unchecked")
	public List<Activum> getActivaLijst(KeyYear key) throws Exception {
		List<Activum> activumList = sqlMap.queryForList("getActivaLijst", key);
		for (Activum activum : activumList) {
			decrypt(activum);
			if (activum.getBedrag() != null) {
				activum.setAanschafKosten(activum.getBedrag().add(activum.getBtw()));
			}
		}
		return activumList;
	}

	@SuppressWarnings("unchecked")
	public List<Passivum> getPassivaLijst(KeyYear key) throws Exception {
		List<Passivum> passivumList = sqlMap.queryForList("getPassivaLijst", key);
		for (Passivum passivum : passivumList) {
			decrypt(passivum);
		}
		return passivumList;
	}
	
	public Integer insertActivum(Activum activum) throws Exception {
		return (Integer)sqlMap.insert("insertActivum", activum);
	}
	
	public void updateActivum(Activum activum) throws Exception {
		sqlMap.insert("updateActivum", activum);
	}	
	
	public Activum getActivumByCostId(Activum activum) throws Exception {
		activum = (Activum) sqlMap.queryForObject("getActivumByCostId", activum);
		if (activum != null) {
			decrypt(activum);
		}
		return activum;
	}
	
	public Activum getActivum(KeyId key) throws Exception {
		Activum activum = (Activum) sqlMap.queryForObject("getActivum", key);
		if (activum != null) {
			decrypt(activum);
		}
		return activum;
	}

}
