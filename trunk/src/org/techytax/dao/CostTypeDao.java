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

import static org.techytax.domain.CostConstants.BUSINESS_CAR_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.BUSINESS_FOOD_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.BUSINESS_TRAVEL_CREDIT_CARD;
import static org.techytax.domain.CostConstants.EXPENSE_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.EXPENSE_OTHER_ACCOUNT_IGNORE;
import static org.techytax.domain.CostConstants.INVESTMENT_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.TRAVEL_WITH_PUBLIC_TRANSPORT_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.UITGAVE_CREDIT_CARD;

import java.util.Arrays;
import java.util.List;

import javax.persistence.TypedQuery;

import org.techytax.domain.CostType;
import org.zkoss.zkplus.jpa.JpaUtil;

public class CostTypeDao extends BaseDao<CostType> {

	public CostTypeDao(Class<CostType> persistentClass) {
		super(persistentClass);
	}

	public List<CostType> getCostTypes() throws Exception {
		TypedQuery<CostType> query = JpaUtil.getEntityManager().createQuery("SELECT ct FROM org.techytax.domain.CostType ct", CostType.class);
		List<CostType> result = query.getResultList();
		return result;
	}

	public List<CostType> getCostTypesForAccount() throws Exception {
		List<CostType> costTypes = Arrays.asList(EXPENSE_OTHER_ACCOUNT_IGNORE, TRAVEL_WITH_PUBLIC_TRANSPORT_OTHER_ACCOUNT);
		TypedQuery<CostType> query = JpaUtil.getEntityManager().createQuery("SELECT ct FROM org.techytax.domain.CostType ct WHERE ct.balansMeetellen = true OR ct IN :costTypes", CostType.class);
		query.setParameter("costTypes", costTypes);
		List<CostType> result = query.getResultList();
		return result;
	}

	public List<CostType> getCostTypesForVatCostsWithPrivateMoney() throws Exception {
		List<CostType> costTypes = Arrays.asList(EXPENSE_OTHER_ACCOUNT, BUSINESS_CAR_OTHER_ACCOUNT, BUSINESS_FOOD_OTHER_ACCOUNT, BUSINESS_TRAVEL_CREDIT_CARD, INVESTMENT_OTHER_ACCOUNT,
				UITGAVE_CREDIT_CARD);
		TypedQuery<CostType> query = JpaUtil.getEntityManager().createQuery("SELECT ct FROM org.techytax.domain.CostType ct WHERE ct IN :costTypes", CostType.class);
		query.setParameter("costTypes", costTypes);
		List<CostType> result = query.getResultList();
		return result;
	}

}
