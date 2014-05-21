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

import static org.techytax.dao.QueryParameter.with;
import static org.techytax.domain.CostConstants.BUSINESS_CAR_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.BUSINESS_FOOD_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.BUSINESS_TRAVEL_CREDIT_CARD;
import static org.techytax.domain.CostConstants.EXPENSE_CREDIT_CARD;
import static org.techytax.domain.CostConstants.EXPENSE_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.EXPENSE_OTHER_ACCOUNT_IGNORE;
import static org.techytax.domain.CostConstants.INVESTMENT_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.TRAVEL_WITH_PUBLIC_TRANSPORT_OTHER_ACCOUNT;

import java.util.Arrays;
import java.util.List;

import org.techytax.domain.CostType;

public class CostTypeDao extends BaseDao<CostType> {

	public CostTypeDao(Class<CostType> persistentClass) {
		super(persistentClass);
	}

	public List<CostType> getCostTypesForTransactionMatching() throws Exception {
		List<CostType> costTypes = Arrays.asList(EXPENSE_OTHER_ACCOUNT_IGNORE, TRAVEL_WITH_PUBLIC_TRANSPORT_OTHER_ACCOUNT);
		return findByNamedQuery(CostType.FOR_MATCHING, with("costTypes", costTypes).parameters());
	}

	public List<CostType> getCostTypesForVatCostsWithPrivateMoney() throws Exception {
		List<CostType> costTypes = Arrays.asList(EXPENSE_OTHER_ACCOUNT, BUSINESS_CAR_OTHER_ACCOUNT, BUSINESS_FOOD_OTHER_ACCOUNT, BUSINESS_TRAVEL_CREDIT_CARD, INVESTMENT_OTHER_ACCOUNT,
				EXPENSE_CREDIT_CARD);
		return findByNamedQuery(CostType.FOR_TYPES, with("costTypes", costTypes).parameters());
	}

}
