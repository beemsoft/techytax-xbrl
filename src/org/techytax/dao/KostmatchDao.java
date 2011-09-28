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
		sqlMap.insert("insertKostmatch", kostmatch);
	}

	public void insertCostMatchPrivate(Kostmatch costMatch) throws Exception {
		encrypt(costMatch);
		sqlMap.insert("insertCostMatchPrivate", costMatch);
	}

	public void deleteCostMatchPrivate(Kostmatch costMatch) throws Exception {
		sqlMap.delete("deleteCostMatchPrivate", costMatch);
	}

	@SuppressWarnings("unchecked")
	public List<Kostmatch> getKostmatchLijstForId(String kostenSoortId) throws Exception {
		return sqlMap.queryForList("getKostmatchLijstForId", kostenSoortId);
	}

	@SuppressWarnings("unchecked")
	public List<Kostmatch> getCostMatchPrivateListForId(KeyId key) throws Exception {
		List<Kostmatch> costMatches = sqlMap.queryForList("getCostMatchPrivateListForId", key);
		for (Kostmatch costMatch : costMatches) {
			decrypt(costMatch);
		}
		return costMatches;
	}

	@SuppressWarnings("unchecked")
	public List<Kostmatch> getKostmatchLijst() throws Exception {
		return sqlMap.queryForList("getKostmatchLijst", null);
	}

	@SuppressWarnings("unchecked")
	public List<Kostmatch> getCostMatchPrivateList(String userId) throws Exception {
		List<Kostmatch> costMatches = sqlMap.queryForList("getCostMatchPrivateList", userId);
		for (Kostmatch costMatch : costMatches) {
			decrypt(costMatch);
		}
		return costMatches;
	}

	public void updateKostmatch(Kostmatch kostmatch) throws Exception {
		sqlMap.insert("updateKostmatch", kostmatch);
	}

	public void updateCostMatchPrivate(Kostmatch costMatch) throws Exception {
		encrypt(costMatch);
		sqlMap.insert("updateCostMatchPrivate", costMatch);
	}

	public Kostmatch getKostmatch(String id) throws Exception {
		return (Kostmatch) sqlMap.queryForObject("getKostmatch", id);
	}

	public Kostmatch getCostMatchPrivate(KeyId key) throws Exception {
		Kostmatch costMatch = (Kostmatch) sqlMap.queryForObject("getCostMatchPrivate", key);
		decrypt(costMatch);
		return costMatch;
	}

}
