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

import org.techytax.domain.Activum;
import org.techytax.domain.KeyYear;
import org.techytax.domain.Passivum;

public class FiscaalDao extends BaseDao {

	private void decrypt(Activum activa) {
		activa.setSaldo(intEncryptor.decrypt(activa.getSaldo()));
		activa.setRestwaarde(intEncryptor.decrypt(activa.getRestwaarde()));
		activa.setBedrag(decimalEncryptor.decrypt(activa.getBedrag()));
		activa.setBtw(decimalEncryptor.decrypt(activa.getBtw()));
		activa.setAanschafKosten(decimalEncryptor.decrypt(activa.getAanschafKosten()));
	}
	
	private void decrypt(Passivum passiva) {
		passiva.setSaldo(intEncryptor.decrypt(passiva.getSaldo()));
	}

	@SuppressWarnings("unchecked")
	public List<Activum> getActivaLijst(KeyYear key) throws Exception {
		List<Activum> activaList = sqlMap.queryForList("getActivaLijst", key);
		for (Activum activa : activaList) {
			decrypt(activa);
			if (activa.getBedrag() != null) {
				activa.setAanschafKosten(activa.getBedrag().add(activa.getBtw()));
			}
		}
		return activaList;
	}

	@SuppressWarnings("unchecked")
	public List<Passivum> getPassivaLijst(KeyYear key) throws Exception {
		List<Passivum> passivaList = sqlMap.queryForList("getPassivaLijst", key);
		for (Passivum passiva : passivaList) {
			decrypt(passiva);
		}
		return passivaList;
	}
	
	public Integer insertActivum(Activum activa) throws Exception {
		return (Integer)sqlMap.insert("insertActiva", activa);
	}	
	
	public Activum getActivumByCostId(Activum activum) throws Exception {
		activum = (Activum) sqlMap.queryForObject("getActivumByCostId", activum);
		if (activum != null) {
			decrypt(activum);
		}
		return activum;
	}	

}
