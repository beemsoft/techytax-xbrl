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
import static org.techytax.domain.CostConstants.BUSINESS_TRAVEL_CREDIT_CARD;
import static org.techytax.domain.CostConstants.EXPENSE_CREDIT_CARD;
import static org.techytax.domain.CostConstants.EXPENSE_CURRENT_ACCOUNT_IGNORE;
import static org.techytax.domain.CostConstants.EXPENSE_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.EXPENSE_OTHER_ACCOUNT_IGNORE;
import static org.techytax.domain.CostConstants.INVESTMENT_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.INVOICE_PAID;
import static org.techytax.domain.CostConstants.INVOICE_SENT;

import java.util.Arrays;
import java.util.List;

import org.techytax.domain.Cost;
import org.techytax.domain.CostType;
import org.techytax.domain.FiscalPeriod;

public class CostDao extends BaseDao<Cost> {

	public CostDao(Class<Cost> persistentClass) {
		super(persistentClass);
	}

	public void insertSplitCost(Cost originalCost, Cost splitCost) {
		splitCost.setDate(originalCost.getDate());
		splitCost.setCostType(originalCost.getCostType().isBalansMeetellen() ? EXPENSE_CURRENT_ACCOUNT_IGNORE : EXPENSE_OTHER_ACCOUNT_IGNORE);
		splitCost.roundValues();
		splitCost.setUser(user);
		persistEntity(splitCost);
	}

	public List<Cost> getCostsInPeriod(FiscalPeriod period) {
		return findByNamedQueryForPeriod(Cost.FOR_PERIOD, period);
	}

	public List<Cost> getVatCostsInPeriod(FiscalPeriod period) {
		return findByNamedQueryForPeriod(Cost.FOR_PERIOD_AND_VAT_DECLARABLE, period);
	}

	public List<Cost> getCostsOnBusinessAccountInPeriod(FiscalPeriod period) {
		return findByNamedQueryForPeriod(Cost.FOR_PERIOD_AND_ACCOUNT, period);
	}

	public List<Cost> getInvoicesSentAndPaid(FiscalPeriod period) {
		return getCosts(period, Arrays.asList(INVOICE_SENT, INVOICE_PAID));
	}

	public List<Cost> getInvoicesSent(FiscalPeriod period) {
		return getCosts(period, Arrays.asList(INVOICE_SENT));
	}

	public List<Cost> getVatCostsWithPrivateMoney(FiscalPeriod period) {
		return getCosts(period, Arrays.asList(EXPENSE_OTHER_ACCOUNT, BUSINESS_CAR_OTHER_ACCOUNT, BUSINESS_TRAVEL_CREDIT_CARD, INVESTMENT_OTHER_ACCOUNT, EXPENSE_CREDIT_CARD));
	}

	private List<Cost> getCosts(FiscalPeriod period, List<CostType> costTypes) {
		return findByNamedQuery(Cost.FOR_PERIOD_AND_TYPES, with("beginDate", period.getBeginDate()).and("endDate", period.getEndDate()).and("costTypes", costTypes).parameters());
	}
}
