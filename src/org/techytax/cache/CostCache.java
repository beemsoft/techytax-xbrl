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

import static java.math.BigDecimal.ZERO;
import static org.techytax.domain.CostConstants.ADVERTORIAL;
import static org.techytax.domain.CostConstants.ADVERTORIAL_NO_VAT;
import static org.techytax.domain.CostConstants.BUSINESS_CAR;
import static org.techytax.domain.CostConstants.BUSINESS_CAR_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.BUSINESS_FOOD;
import static org.techytax.domain.CostConstants.BUSINESS_FOOD_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.EXPENSE_CURRENT_ACCOUNT;
import static org.techytax.domain.CostConstants.EXPENSE_CURRENT_ACCOUNT_IGNORE;
import static org.techytax.domain.CostConstants.EXPENSE_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.INCOME_CURRENT_ACCOUNT_IGNORE;
import static org.techytax.domain.CostConstants.INCOME_TAX;
import static org.techytax.domain.CostConstants.INCOME_TAX_PAID_BACK;
import static org.techytax.domain.CostConstants.INTEREST;
import static org.techytax.domain.CostConstants.INVESTMENT;
import static org.techytax.domain.CostConstants.INVESTMENT_MINIMUM_AMOUNT;
import static org.techytax.domain.CostConstants.INVESTMENT_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.REPURCHASES;
import static org.techytax.domain.CostConstants.ROAD_TAX;
import static org.techytax.domain.CostConstants.SETTLEMENT;
import static org.techytax.domain.CostConstants.SETTLEMENT_INTEREST;
import static org.techytax.domain.CostConstants.TRAVEL_WITH_PUBLIC_TRANSPORT;
import static org.techytax.domain.CostConstants.TRAVEL_WITH_PUBLIC_TRANSPORT_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.UNDETERMINED;
import static org.techytax.domain.CostConstants.VAT;
import static org.techytax.domain.CostConstants.VAT_CORRECTION_CAR_DEPRECIATION;
import static org.techytax.domain.CostConstants.VAT_PAID_BACK_ON_OTHER_ACCOUNT;

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
import org.techytax.domain.FiscalPeriod;
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
		CostDao costDao = new CostDao(Cost.class);
		costs = costDao.getCostsInPeriod(new FiscalPeriod(beginDatum, eindDatum));
	}

	public void invalidate() {
		costs = null;
	}

	public List<DeductableCostGroup> getDeductableCosts() throws Exception {
		List<DeductableCostGroup> deductableCostList = new ArrayList<>();
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
		BigDecimal totalDeductableCost = ZERO;
		List<DeductableCostGroup> groupedDeducatableCostList = new ArrayList<>();
		for (DeductableCostGroup deductableCost : deductableCostList) {
			if (deductableCost.getKostenSoort() != latestCostType) {
				if (groupedCost != null) {
					groupedCost.setAftrekbaarBedrag(totalDeductableCost);
					groupedCost.setKostenSoort(latestCostType);
					groupedDeducatableCostList.add(groupedCost);
				}
				latestCostType = deductableCost.getKostenSoort();
				groupedCost = new DeductableCostGroup();
				totalDeductableCost = ZERO;
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
		List<Cost> filteredCostList = new ArrayList<>();
		for (Cost cost : costs) {
			CostType costType = CostTypeCache.getCostType(cost.getCostTypeId());
			if (costType.isBalansMeetellen()) {
				filteredCostList.add(cost);
			}
		}
		return filteredCostList;
	}

	public List<Cost> getInvestments() throws Exception {
		List<Cost> filteredCosts = new ArrayList<>();
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
		List<Cost> filteredCosts = new ArrayList<>();
		List<CostType> costTypes = Arrays.asList(VAT, INCOME_TAX, INCOME_TAX_PAID_BACK, ROAD_TAX, VAT_PAID_BACK_ON_OTHER_ACCOUNT);
		for (Cost cost : costs) {
			if (costTypes.contains(cost.getCostType())) {
				filteredCosts.add(cost);
			}
		}
		return filteredCosts;
	}

	public List<Cost> getCostListCurrentAccount() throws Exception {
		List<Cost> filteredCosts = new ArrayList<>();
		List<CostType> costTypes = Arrays.asList(EXPENSE_CURRENT_ACCOUNT, EXPENSE_CURRENT_ACCOUNT_IGNORE, TRAVEL_WITH_PUBLIC_TRANSPORT, BUSINESS_FOOD, BUSINESS_CAR, INVESTMENT, ADVERTORIAL,
				ADVERTORIAL_NO_VAT, ROAD_TAX, SETTLEMENT, SETTLEMENT_INTEREST);
		for (Cost cost : costs) {
			if (costTypes.contains(cost.getCostType())) {
				filteredCosts.add(cost);
			}
		}
		return filteredCosts;
	}

	public BigDecimal getCostsWithPrivateMoney() throws Exception {
		BigDecimal costsWithPrivateMoney = ZERO;
		List<CostType> costTypes = Arrays.asList(EXPENSE_OTHER_ACCOUNT, TRAVEL_WITH_PUBLIC_TRANSPORT_OTHER_ACCOUNT, BUSINESS_CAR_OTHER_ACCOUNT,
				BUSINESS_FOOD_OTHER_ACCOUNT, INVESTMENT_OTHER_ACCOUNT);
		for (Cost cost : costs) {
			CostType costType = cost.getCostType();
			if (costTypes.contains(costType)) {
				costsWithPrivateMoney = costsWithPrivateMoney.add(cost.getAmount()).add(cost.getVat());
			}
		}
		return costsWithPrivateMoney;
	}

	public BigDecimal getInterest() throws Exception {
		BigDecimal interest = ZERO;
		for (Cost cost : costs) {
			if (cost.getCostType().equals(INTEREST)) {
				interest = interest.add(cost.getAmount());
			}
		}
		return interest;
	}

	@Deprecated
	public List<Cost> getVatCorrectionDepreciation() throws Exception {
		List<Cost> filteredCosts = new ArrayList<>();
		for (Cost cost : costs) {
			if (cost.getCostType().equals(VAT_CORRECTION_CAR_DEPRECIATION)) {
				filteredCosts.add(cost);
			}
		}
		return filteredCosts;
	}

	public PrepaidTax getPrepaidTax() throws Exception {
		PrepaidTax prepaidTax = new PrepaidTax();
		List<Cost> filteredCostList = new ArrayList<>();
		for (Cost cost : costs) {
			if (cost.getCostType().equals(INCOME_TAX)) {
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
		BigDecimal repurchases = ZERO;
		for (Cost cost : costs) {
			if (cost.getCostType().equals(REPURCHASES)) {
				repurchases = repurchases.add(cost.getAmount());
			}
		}
		return repurchases;
	}
	
	public BigDecimal getCostCurrentAccountIgnore() throws Exception {
		BigDecimal totalCost = ZERO;
		for (Cost cost : costs) {
			if (cost.getCostType().equals(INCOME_CURRENT_ACCOUNT_IGNORE)) {
				totalCost = totalCost.add(cost.getAmount()).add(cost.getVat());
			}
		}
		return totalCost;
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
