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

import org.apache.commons.lang.StringUtils;
import org.techytax.domain.KeyId;
import org.techytax.domain.Kostmatch;

public class KostmatchDao extends BaseDao {
	
	private void decrypt(Kostmatch costMatch) {
		String matchText = costMatch.getMatchText();
		if (StringUtils.isNotEmpty(matchText)) {
			costMatch.setMatchText(textEncryptor.decrypt(matchText));
		}
	}
	
	private void encrypt(Kostmatch costMatch) {
		costMatch.setMatchText(textEncryptor.encrypt(costMatch.getMatchText()));
	}
	
	public void insertKostmatch(Kostmatch kostmatch) throws Exception {
		try {
			sqlMap.insert("insertKostmatch", kostmatch);
		} catch (SQLException ex) {
			throw ex;
		}
	}
	
	public void insertCostMatchPrivate(Kostmatch costMatch) throws Exception {
		try {
			encrypt(costMatch);
			sqlMap.insert("insertCostMatchPrivate", costMatch);
		} catch (SQLException ex) {
			throw ex;
		}
	}	

	@SuppressWarnings("unchecked")
	public List<Kostmatch> getKostmatchLijstForId(String kostenSoortId)
			throws Exception {
		try {
			return sqlMap.queryForList("getKostmatchLijstForId", kostenSoortId);
		} catch (SQLException ex) {
			throw ex;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Kostmatch> getCostMatchPrivateListForId(KeyId key)
			throws Exception {
		try {
			List<Kostmatch> costMatches = sqlMap.queryForList("getCostMatchPrivateListForId", key);
			for (Kostmatch costMatch : costMatches) {
				decrypt(costMatch);
			}
			return costMatches;			
		} catch (SQLException ex) {
			throw ex;
		}
	}	

	@SuppressWarnings("unchecked")
	public List<Kostmatch> getKostmatchLijst() throws Exception {
		try {
			return sqlMap.queryForList("getKostmatchLijst", null);
		} catch (SQLException ex) {
			throw ex;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Kostmatch> getCostMatchPrivateList(String userId) throws Exception {
		try {
			List<Kostmatch> costMatches = sqlMap.queryForList("getCostMatchPrivateList", userId);
			for (Kostmatch costMatch : costMatches) {
				decrypt(costMatch);
			}
			return costMatches;
		} catch (SQLException ex) {
			throw ex;
		}
	}	

	public void updateKostmatch(Kostmatch kostmatch) throws Exception {
		try {
			sqlMap.insert("updateKostmatch", kostmatch);
		} catch (SQLException ex) {
			throw ex;
		}
	}
	
	public void updateCostMatchPrivate(Kostmatch costMatch) throws Exception {
		try {
			encrypt(costMatch);
			sqlMap.insert("updateCostMatchPrivate", costMatch);
		} catch (SQLException ex) {
			throw ex;
		}
	}	

	public Kostmatch getKostmatch(String id) throws Exception {
		try {
			return (Kostmatch) sqlMap.queryForObject("getKostmatch", id);
		} catch (SQLException ex) {
			throw ex;
		}
	}
	
	public Kostmatch getCostMatchPrivate(KeyId key) throws Exception {
		try {
			Kostmatch costMatch = (Kostmatch) sqlMap.queryForObject("getCostMatchPrivate", key);
			decrypt(costMatch);
			return costMatch;
		} catch (SQLException ex) {
			throw ex;
		}
	}	

}
