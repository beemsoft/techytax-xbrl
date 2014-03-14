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
package org.techytax.cache;

import static org.techytax.domain.CostConstants.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.techytax.dao.CostDao;
import org.techytax.domain.Cost;
import org.techytax.domain.CostType;
import org.techytax.domain.DeductableCostGroup;
import org.techytax.domain.PrepaidTax;
import org.techytax.util.DateHelper;

public class CostCache {

	private List<Cost> costs = null;

	private Date beginDatum;

	private Date eindDatum;

	public List<Cost> getCosts() throws Exception {

		if (costs == null) {
			fillCosts();
		}
		return costs;
	}

	private void fillCosts() throws Exception {
		CostDao costDao = new CostDao();
		costs = costDao.getKostLijst(beginDatum, eindDatum, "alles");
	}

	public void invalidate() {
		costs = null;
	}

	public List<DeductableCostGroup> getDeductableCosts() throws Exception {
		List<DeductableCostGroup> deductableCostList = new ArrayList<DeductableCostGroup>();
		for (Cost cost : costs) {
			CostType costType = cost.getCostType();
			if (costType.isAftrekbaar()) {
				DeductableCostGroup deductableCostGroup = new DeductableCostGroup();
				deductableCostGroup.setAftrekbaarBedrag(cost.getAmount());
				deductableCostGroup.setKostenSoort(cost.getCostType());
				deductableCostList.add(deductableCostGroup);
			}
		}
		Collections.sort(deductableCostList);
		CostType latestCostType = UNDETERMINED;
		DeductableCostGroup groupedCost = null;
		BigDecimal totalDeductableCost = BigDecimal.ZERO;
		List<DeductableCostGroup> groupedDeducatableCostList = new ArrayList<DeductableCostGroup>();
		for (DeductableCostGroup deductableCost : deductableCostList) {
			if (deductableCost.getKostenSoort() != latestCostType) {
				if (groupedCost != null) {
					groupedCost.setAftrekbaarBedrag(totalDeductableCost);
					groupedCost.setKostenSoort(latestCostType);
					groupedDeducatableCostList.add(groupedCost);
				}
				latestCostType = deductableCost.getKostenSoort();
				groupedCost = new DeductableCostGroup();
				totalDeductableCost = BigDecimal.ZERO;
			}
			totalDeductableCost = totalDeductableCost.add(deductableCost.getAftrekbaarBedrag());
		}
		if (groupedCost != null) {
			groupedCost.setAftrekbaarBedrag(totalDeductableCost);
			groupedCost.setKostenSoort(latestCostType);
			groupedDeducatableCostList.add(groupedCost);
		}
		return groupedDeducatableCostList;
	}

	public List<Cost> getBusinessAccountCosts() throws Exception {
		List<Cost> filteredCostList = new ArrayList<Cost>();
		for (Cost cost : costs) {
			CostType costType = CostTypeCache.getCostType(cost.getCostTypeId());
			if (costType.isBalansMeetellen()) {
				filteredCostList.add(cost);
			}
		}
		return filteredCostList;
	}

	public List<Cost> getInvestments() throws Exception {
		List<Cost> filteredCosts = new ArrayList<Cost>();
		for (Cost cost : costs) {
			CostType costType = cost.getCostType();
			if (costType.equals(INVESTMENT)) {
				if (cost.getAmount().compareTo(BigDecimal.valueOf(INVESTMENT_MINIMUM_AMOUNT)) == 1) {
					filteredCosts.add(cost);
				}
			}
		}
		return filteredCosts;
	}

	public List<Cost> getTaxCosts() throws Exception {
		List<Cost> filteredCosts = new ArrayList<Cost>();
		List<CostType> costTypes = Arrays.asList(VAT, INCOME_TAX, INCOME_TAX_PAID_BACK, ROAD_TAX, VAT_PAID_BACK_ON_OTHER_ACCOUNT);
		for (Cost cost : costs) {
			if (costTypes.contains(cost.getCostType())) {
				filteredCosts.add(cost);
			}
		}
		return filteredCosts;
	}

	public List<Cost> getCostListCurrentAccount() throws Exception {
		List<Cost> filteredCosts = new ArrayList<Cost>();
		List<CostType> costTypes = Arrays.asList(EXPENSE_CURRENT_ACCOUNT, UITGAVE_DEZE_REKENING_FOUTIEF, TRAVEL_WITH_PUBLIC_TRANSPORT, BUSINESS_FOOD, BUSINESS_CAR, INVESTMENT, ADVERTENTIE,
				ADVERTENTIE_ZONDER_BTW, ROAD_TAX, SETTLEMENT, SETTLEMENT_INTEREST);
		for (Cost cost : costs) {
			if (costTypes.contains(cost.getCostType())) {
				filteredCosts.add(cost);
			}
		}
		return filteredCosts;
	}

	public BigDecimal getCostsWithPrivateMoney() throws Exception {
		BigDecimal costsWithPrivateMoney = BigDecimal.ZERO;
		List<CostType> costTypes = Arrays.asList(EXPENSE_OTHER_ACCOUNT_IGNORE, EXPENSE_OTHER_ACCOUNT, TRAVEL_WITH_PUBLIC_TRANSPORT_OTHER_ACCOUNT, BUSINESS_CAR_OTHER_ACCOUNT,
				BUSINESS_FOOD_OTHER_ACCOUNT, BUSINESS_TRAVEL_CREDIT_CARD, BUSINESS_LITERATURE_CREDIT_CARD_NO_VAT, INVESTMENT_OTHER_ACCOUNT);
		for (Cost cost : costs) {
			CostType costType = cost.getCostType();
			if (costTypes.contains(costType)) {
				costsWithPrivateMoney = costsWithPrivateMoney.add(cost.getAmount()).add(cost.getVat());
			}
		}
		return costsWithPrivateMoney;
	}

	public BigDecimal getInterest() throws Exception {
		BigDecimal interest = BigDecimal.ZERO;
		for (Cost cost : costs) {
			if (cost.getCostType().equals(INTEREST)) {
				interest = interest.add(cost.getAmount());
			}
		}
		return interest;
	}

	@Deprecated
	public List<Cost> getVatCorrectionDepreciation() throws Exception {
		List<Cost> filteredCosts = new ArrayList<Cost>();
		for (Cost cost : costs) {
			if (cost.getCostType().equals(VAT_CORRECTION_CAR_DEPRECIATION)) {
				filteredCosts.add(cost);
			}
		}
		return filteredCosts;
	}

	public PrepaidTax getPrepaidTax() throws Exception {
		PrepaidTax prepaidTax = new PrepaidTax();
		List<Cost> filteredCostList = new ArrayList<Cost>();
		for (Cost cost : costs) {
			CostType costType = cost.getCostType();
			if (costType.equals(INCOME_TAX)) {
				filteredCostList.add(cost);
			}
		}
		int prepaidIncomeTax = 0;
		int prepaidHealthTax = 0;
		int year = DateHelper.getYear(beginDatum);
		for (Cost tax : filteredCostList) {
			if (tax.getDescription().contains("Inkomstenbelasting " + year)) {
				prepaidIncomeTax += tax.getAmount().intValue();
			}
			if (tax.getDescription().contains("Zorgverzekeringswet " + year)) {
				prepaidHealthTax += tax.getAmount().intValue();
			}
		}
		prepaidTax.setPrepaidHealth(prepaidHealthTax);
		prepaidTax.setPrepaidIncome(prepaidIncomeTax);
		return prepaidTax;
	}

	public BigDecimal getRepurchases() throws Exception {
		BigDecimal repurchases = BigDecimal.ZERO;
		for (Cost cost : costs) {
			CostType costType = cost.getCostType();
			if (costType.equals(REPURCHASES)) {
				repurchases = repurchases.add(cost.getAmount());
			}
		}
		return repurchases;
	}

	public Date getBeginDatum() {
		return beginDatum;
	}

	public void setBeginDatum(Date beginDatum) {
		if (!beginDatum.equals(this.beginDatum)) {
			costs = null;
		}
		this.beginDatum = beginDatum;
	}

	public Date getEindDatum() {
		return eindDatum;
	}

	public void setEindDatum(Date eindDatum) {
		if (!eindDatum.equals(this.eindDatum)) {
			costs = null;
		}
		this.eindDatum = eindDatum;
	}

}
