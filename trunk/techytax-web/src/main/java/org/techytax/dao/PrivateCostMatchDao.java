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

import static org.techytax.jpa.dao.QueryParameter.with;

import java.util.List;

import org.springframework.stereotype.Component;
import org.techytax.domain.CostType;
import org.techytax.domain.PrivateCostMatch;
import org.techytax.jpa.dao.GenericDao;
import org.techytax.zk.login.UserCredentialManager;

@Component
public class PrivateCostMatchDao extends GenericDao<PrivateCostMatch> {

	public List<PrivateCostMatch> getCostMatchPrivateListForCostType(CostType costType) {
		return findByNamedQuery(PrivateCostMatch.FOR_TYPE, with("costType", costType).and("user", UserCredentialManager.getUser()).parameters());
	}

	public PrivateCostMatch getCostMatchPrivate(PrivateCostMatch costMatch) throws Exception {
		if (costMatch.getId() == null) {
			return null;
		}
		PrivateCostMatch result = (PrivateCostMatch) getEntity(costMatch, costMatch.getId());
		return result;
	}

}
