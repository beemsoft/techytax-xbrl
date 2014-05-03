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
package org.techytax.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.techytax.domain.CostType;
import org.techytax.domain.Kostmatch;
import org.techytax.domain.PrivateCostMatch;
import org.techytax.domain.User;
import org.techytax.jpa.dao.GenericDao;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.zkplus.jpa.JpaUtil;

public class KostmatchDao extends BaseDao {
	
	private User user = UserCredentialManager.getUser();
	
	private GenericDao<PrivateCostMatch> genericPrivateCostMatchDao = new GenericDao<>(PrivateCostMatch.class);

	public void insertCostMatchPrivate(PrivateCostMatch costMatch) throws Exception {
		costMatch.setUser(user);
		genericPrivateCostMatchDao.persistEntity(costMatch);
	}

	public void deleteCostMatchPrivate(PrivateCostMatch costMatch) throws Exception {
		genericPrivateCostMatchDao.deleteEntity(costMatch);
	}

	public List<Kostmatch> getKostmatchLijstForCostType(CostType costType) throws Exception {
		TypedQuery<Kostmatch> query = JpaUtil.getEntityManager().createQuery("SELECT cm FROM org.techytax.domain.Kostmatch cm WHERE cm.costType = :costType", Kostmatch.class);
		query.setParameter("costType", costType);
		List<Kostmatch> costMatches = query.getResultList();
		return costMatches;
	}

	public List<PrivateCostMatch> getCostMatchPrivateListForCostType(CostType costType) throws Exception {
		TypedQuery<PrivateCostMatch> query = JpaUtil.getEntityManager().createQuery("SELECT cm FROM org.techytax.domain.PrivateCostMatch cm WHERE cm.user = :user AND cm.costType = :costType", PrivateCostMatch.class);
		query.setParameter("costType", costType);
		query.setParameter("user", user);
		List<PrivateCostMatch> costMatches = query.getResultList();
		return costMatches;
	}

	public List<Kostmatch> getKostmatchLijst() throws Exception {
		TypedQuery<Kostmatch> query = JpaUtil.getEntityManager().createQuery("SELECT cm FROM org.techytax.domain.Kostmatch cm", Kostmatch.class);
		List<Kostmatch> costMatches = query.getResultList();
		return costMatches;
	}

	public List<PrivateCostMatch> getCostMatchPrivateList() throws Exception {
		TypedQuery<PrivateCostMatch> query = JpaUtil.getEntityManager().createQuery("SELECT cm FROM org.techytax.domain.PrivateCostMatch cm WHERE cm.user = :user", PrivateCostMatch.class);
		query.setParameter("user", user);
		List<PrivateCostMatch> result = query.getResultList();
		return result ;
	}

	public void updateCostMatchPrivate(PrivateCostMatch costMatch) throws Exception {
		genericPrivateCostMatchDao.merge(costMatch);
	}

	public PrivateCostMatch getCostMatchPrivate(PrivateCostMatch costMatch) throws Exception {
		PrivateCostMatch result = (PrivateCostMatch) genericPrivateCostMatchDao.getEntity(costMatch, costMatch.getId());
		return result;
	}

}
