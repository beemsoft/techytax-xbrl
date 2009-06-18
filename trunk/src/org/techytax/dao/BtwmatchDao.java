/**
 * Copyright 2009 Hans Beemsterboer
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

import org.techytax.domain.Btwmatch;
import org.techytax.domain.Kostmatch;

public class BtwmatchDao extends BaseDao {
	public void insertBtwmatch(Kostmatch kostmatch) throws Exception {
		Btwmatch btwmatch = new Btwmatch();
		btwmatch.setKostmatchId(kostmatch.getId());
		try {
			sqlMap.insert("insertBtwmatch", btwmatch);
		} catch (SQLException ex) {
			throw ex;
		}
	}

	public void updateBtwmatch(Kostmatch kostmatch) throws Exception {
		try {
			sqlMap.insert("updateBtwmatch", kostmatch);
		} catch (SQLException ex) {
			throw ex;
		}
	}

	public Btwmatch getBtwmatch(String id) throws Exception {
		try {
			return (Btwmatch) sqlMap.queryForObject("getBtwmatch", id);
		} catch (SQLException ex) {
			throw ex;
		}
	}

}
