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

import java.sql.SQLException;
import java.util.List;

import org.techytax.domain.Activa;
import org.techytax.domain.KeyYear;
import org.techytax.domain.Passiva;

public class FiscaalDao extends BaseDao {

	private void decrypt(Activa activa) {
		activa.setSaldo(intEncryptor.decrypt(activa.getSaldo()));	
		activa.setRestwaarde(intEncryptor.decrypt(activa.getRestwaarde()));
		activa.setBedrag(decimalEncryptor.decrypt(activa.getBedrag()));
		activa.setBtw(decimalEncryptor.decrypt(activa.getBtw()));
	}
	
	private void decrypt(Passiva passiva) {
		passiva.setSaldo(intEncryptor.decrypt(passiva.getSaldo()));	
	}	
	
	@SuppressWarnings("unchecked")
	public List<Activa> getActivaLijst(KeyYear key) throws Exception {
		try {
			List<Activa> activaList = sqlMap.queryForList("getActivaLijst", key);
			for (Activa activa : activaList) {
				decrypt(activa);
				if (activa.getBedrag() != null) {
					activa.setAanschafKosten(activa.getBedrag().add(activa.getBtw()));
				}
			}
			return activaList;
		} catch (SQLException ex) {
			throw ex;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Passiva> getPassivaLijst(KeyYear key) throws Exception {
		try {
			List<Passiva> passivaList = sqlMap.queryForList("getPassivaLijst", key);
			for (Passiva passiva : passivaList) {
				decrypt(passiva);
			}
			return passivaList;
		} catch (SQLException ex) {
			throw ex;
		}
	}

}
