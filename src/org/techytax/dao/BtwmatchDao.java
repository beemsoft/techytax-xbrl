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

import org.techytax.domain.Btwmatch;
import org.techytax.domain.Kostmatch;

public class BtwmatchDao extends BaseDao {
	public void insertBtwmatch(Kostmatch kostmatch) throws Exception {
		Btwmatch btwmatch = new Btwmatch();
		btwmatch.setKostmatchId(kostmatch.getId());
		sqlMap.insert("insertBtwmatch", btwmatch);
	}

	public void updateBtwmatch(Kostmatch kostmatch) throws Exception {
		sqlMap.insert("updateBtwmatch", kostmatch);
	}

	public Btwmatch getBtwmatch(String id) throws Exception {
		return (Btwmatch) sqlMap.queryForObject("getBtwmatch", id);
	}

	public void insertBtwMatchPrivate(Kostmatch kostmatch) throws Exception {
		Btwmatch btwmatch = new Btwmatch();
		btwmatch.setKostmatchId(kostmatch.getId());
		sqlMap.insert("insertBtwMatchPrivate", btwmatch);
	}

	public void deleteBtwMatchPrivate(String kostMatchId) throws Exception {
		sqlMap.delete("deleteBtwMatchPrivate", kostMatchId);
	}

	public void updateBtwMatchPrivate(Kostmatch kostmatch) throws Exception {
		sqlMap.insert("updateBtwMatchPrivate", kostmatch);
	}

	public Btwmatch getBtwMatchPrivate(String id) throws Exception {
		return (Btwmatch) sqlMap.queryForObject("getBtwMatchPrivate", id);
	}

}
