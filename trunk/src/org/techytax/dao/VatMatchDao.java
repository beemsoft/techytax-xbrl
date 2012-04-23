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

import org.techytax.domain.VatMatch;
import org.techytax.domain.Kostmatch;

public class VatMatchDao extends BaseDao {
	
	public void insertVatMatch(Kostmatch kostmatch) throws Exception {
		VatMatch vatMatch = new VatMatch();
		vatMatch.setKostmatchId(kostmatch.getId());
		sqlMap.insert("insertVatMatch", vatMatch);
	}

	public void updateVatMatch(Kostmatch kostmatch) throws Exception {
		sqlMap.insert("updateVatMatch", kostmatch);
	}

	public VatMatch getVatMatch(String id) throws Exception {
		return (VatMatch) sqlMap.queryForObject("getVatMatch", id);
	}

	public void insertVatMatchPrivate(Kostmatch kostmatch) throws Exception {
		VatMatch btwmatch = new VatMatch();
		btwmatch.setKostmatchId(kostmatch.getId());
		sqlMap.insert("insertVatMatchPrivate", btwmatch);
	}

	public void deleteVatMatchPrivate(String kostMatchId) throws Exception {
		sqlMap.delete("deleteVatMatchPrivate", kostMatchId);
	}

	public void updateVatMatchPrivate(Kostmatch kostmatch) throws Exception {
		sqlMap.insert("updateVatMatchPrivate", kostmatch);
	}

	public VatMatch getVatMatchPrivate(String id) throws Exception {
		return (VatMatch) sqlMap.queryForObject("getVatMatchPrivate", id);
	}

}
