/**
 * Copyright 2014 Hans Beemsterboer
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
package org.techytax.jpa.dao;

import java.util.List;

import javax.persistence.Query;

import org.techytax.domain.SplitMatch;
import org.zkoss.zkplus.jpa.JpaUtil;

public class SplitMatchDao {

	public void insertOrUpdate(SplitMatch splitMatch) {
		List<SplitMatch> result = getMatch(splitMatch);
		GenericDao<SplitMatch> genericDao = new GenericDao<SplitMatch>(SplitMatch.class, null);
		if (result.size() == 0) {
			genericDao.persistEntity(splitMatch);
		} else {
			SplitMatch persistedSplitMatch = result.get(0);
			persistedSplitMatch.setPercentage(splitMatch.getPercentage());
			genericDao.persistEntity(persistedSplitMatch);
		}
	}
	
	public SplitMatch getSplitMatch(long costMatchId) {
		SplitMatch splitMatch = new SplitMatch();
		splitMatch.setKostmatchId(costMatchId);
		List<SplitMatch> result = getMatch(splitMatch);
		if (result.size() == 1) {
			return result.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	private List<SplitMatch> getMatch(SplitMatch splitMatch) {
		Query query = JpaUtil.getEntityManager().createQuery("SELECT sm FROM SplitMatch sm WHERE sm.kostmatchId= :costMatchId");
		query.setParameter("costMatchId", splitMatch.getKostmatchId());
		List<SplitMatch> result = query.getResultList();
		return result;
	}
	
	public void delete(long costMatchId) {
		SplitMatch splitMatch = new SplitMatch();
		splitMatch.setKostmatchId(costMatchId);
		List<SplitMatch> result = getMatch(splitMatch);
		if (result.size() == 1) {
			GenericDao<SplitMatch> genericDao = new GenericDao<SplitMatch>(SplitMatch.class, null);
			genericDao.deleteEntity(result.get(0));
		}
	}
}
