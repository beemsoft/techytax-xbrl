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

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.techytax.domain.Settlement;
import org.zkoss.zkplus.jpa.JpaUtil;

public class SettlementDao extends BaseDao<Settlement> {

	public SettlementDao(Class<Settlement> persistentClass) {
		super(persistentClass);
	}

	public Settlement getSettlement() throws Exception {
		TypedQuery<Settlement> query = JpaUtil.getEntityManager().createQuery("SELECT s FROM org.techytax.domain.Settlement s WHERE s.user = :user", Settlement.class);
		query.setParameter("user", user);
		Settlement result = null;
		try {
			result = query.getSingleResult();
		} catch (NoResultException e) {
			// ok
		}
		return result;
	}	
	
	public long getPercentage() throws Exception {
		Settlement settlement = getSettlement();
		long percentage = 0;
		if (settlement != null && settlement.getNofSquareMetersPrivate() != 0) {
			percentage = Math.round((double)settlement.getNofSquareMetersBusiness()/settlement.getNofSquareMetersPrivate() * 100);
		}
		return percentage;
	}

}
