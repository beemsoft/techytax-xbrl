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
import static org.techytax.domain.CostConstants.BUSINESS_TRAVEL_CREDIT_CARD;
import static org.techytax.domain.CostConstants.EXPENSE_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.EXPENSE_OTHER_ACCOUNT_IGNORE;
import static org.techytax.domain.CostConstants.INVESTMENT_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.INVOICE_PAID;
import static org.techytax.domain.CostConstants.INVOICE_SENT;
import static org.techytax.domain.CostConstants.UITGAVE_CREDIT_CARD;
import static org.techytax.domain.CostConstants.UITGAVE_DEZE_REKENING_FOUTIEF;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.techytax.cache.CostTypeCache;
import org.techytax.domain.Activum;
import org.techytax.domain.BalanceType;
import org.techytax.domain.Cost;
import org.techytax.domain.CostType;
import org.techytax.util.DateHelper;
import org.zkoss.zkplus.jpa.JpaUtil;

public class CostDao extends BaseDao<Cost> {

	public CostDao(Class<Cost> persistentClass) {
		super(persistentClass);
	}

	public void insertSplitCost(Cost originalCost, Cost splitCost) throws Exception {
		splitCost.setDate(originalCost.getDate());
		CostType costType = CostTypeCache.getCostType(originalCost.getCostTypeId());
		if (costType.isBalansMeetellen()) {
			splitCost.setCostType(UITGAVE_DEZE_REKENING_FOUTIEF);
		} else {
			splitCost.setCostType(EXPENSE_OTHER_ACCOUNT_IGNORE);
		}
		splitCost.roundValues();
		persistEntity(splitCost);
	}

	public List<Cost> getCostsInPeriod(Date beginDatum, Date eindDatum) {
		TypedQuery<Cost> query = JpaUtil.getEntityManager().createQuery("SELECT c FROM org.techytax.domain.Cost c WHERE c.user = :user AND c.date >= :beginDate AND c.date <= :endDate order by c.date asc", Cost.class);
		query.setParameter("user", user);
		query.setParameter("beginDate", beginDatum, TemporalType.DATE);
		query.setParameter("endDate", eindDatum, TemporalType.DATE);
		List<Cost> costs = query.getResultList();
		return costs;
	}

	public List<Cost> getVatCostsInPeriod(Date beginDatum, Date eindDatum) {
		List<Cost> costs = getCostsInPeriod(beginDatum, eindDatum);
		List<Cost> filteredCosts = new ArrayList<>();
		for (Cost cost : costs) {
			if (cost.getCostType().isVatDeclarable()) {
				filteredCosts.add(cost);
			}
		}
		return filteredCosts;
	}

	public List<Cost> getCostsOnBusinessAccountInPeriod(Date beginDatum, Date eindDatum) {
		List<Cost> costs = getCostsInPeriod(beginDatum, eindDatum);
		List<Cost> filteredCosts = new ArrayList<>();
		for (Cost cost : costs) {
			if (cost.getCostType().isBalansMeetellen()) {
				filteredCosts.add(cost);
			}
		}
		return filteredCosts;
	}

	public List<Activum> getNewActiva(BalanceType balanceType, Date beginDate, Date endDate) {
		TypedQuery<Activum> query = JpaUtil
				.getEntityManager()
				.createQuery(
						"SELECT act FROM org.techytax.domain.Activum act WHERE act.balanceType = :balanceType AND act.cost.date >= :beginDate AND act.cost.date <= :endDate AND (act.startDate = null OR act.startDate <= :startDate) AND act.endDate = null AND act.user = :user",
						Activum.class);
		query.setParameter("user", user);
		query.setParameter("beginDate", beginDate, TemporalType.DATE);
		query.setParameter("endDate", endDate, TemporalType.DATE);
		query.setParameter("startDate", DateHelper.getLastDayOfFiscalYear(), TemporalType.DATE);
		query.setParameter("balanceType", balanceType);
		List<Activum> result = query.getResultList();
		return result;
	}

	public BigDecimal getInvoiceBalance(Date beginDatum, Date eindDatum) throws Exception {
		List<Cost> costs = getCostsInPeriod(beginDatum, eindDatum);
		BigDecimal invoiceBalance = BigDecimal.ZERO;
		for (Cost cost : costs) {
			if (cost.getCostType().equals(INVOICE_SENT)) {
				invoiceBalance = invoiceBalance.add(cost.getAmount()).add(cost.getVat());
			} else if (cost.getCostType().equals(INVOICE_PAID)) {
				invoiceBalance = invoiceBalance.subtract(cost.getAmount()).subtract(cost.getVat());
			}
		}
		return invoiceBalance;
	}

	public List<Cost> getInvoices(Date beginDatum, Date eindDatum) throws Exception {
		List<Cost> costs = getCostsInPeriod(beginDatum, eindDatum);
		List<Cost> filteredCosts = new ArrayList<>();
		for (Cost cost : costs) {
			if (cost.getCostType().equals(INVOICE_SENT) || cost.getCostType().equals(INVOICE_PAID)) {
				filteredCosts.add(cost);
			}
		}
		return filteredCosts;
	}

	public BigDecimal getVatDebtFromPreviousYear(Date beginDate, Date endDate) {
		List<Cost> costs = getCostsInPeriod(beginDate, endDate);
		BigDecimal vatBalance = BigDecimal.ZERO;
		for (Cost cost : costs) {
			if (cost.getCostType().equals(INVOICE_SENT)) {
				vatBalance = vatBalance.add(cost.getVat());
			}
		}
		return vatBalance;
	}

	public List<Cost> getVatCostsWithPrivateMoney(Date beginDate, Date endDate) throws Exception {
		List<CostType> costTypes = Arrays.asList(EXPENSE_OTHER_ACCOUNT, BUSINESS_CAR_OTHER_ACCOUNT, BUSINESS_TRAVEL_CREDIT_CARD, INVESTMENT_OTHER_ACCOUNT, UITGAVE_CREDIT_CARD);
		TypedQuery<Cost> query = JpaUtil
				.getEntityManager()
				.createQuery(
						"SELECT c FROM org.techytax.domain.Cost c WHERE c.date >= :beginDate AND c.date <= :endDate AND c.costType IN :costTypes AND c.user = :user",
						Cost.class);
		query.setParameter("user", user);
		query.setParameter("beginDate", beginDate, TemporalType.DATE);
		query.setParameter("endDate", endDate, TemporalType.DATE);
		query.setParameter("costTypes", costTypes);
		List<Cost> result = query.getResultList();
		return result;
	}

}
