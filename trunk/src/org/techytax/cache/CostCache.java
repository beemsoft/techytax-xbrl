/**
 * Copyright 2013 Hans Beemsterboer
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.techytax.dao.CostDao;
import org.techytax.domain.Cost;
import org.techytax.domain.CostConstants;
import org.techytax.domain.CostType;
import org.techytax.domain.DeductableCostGroup;
import org.techytax.domain.PrepaidTax;
import org.techytax.domain.User;
import org.techytax.util.DateHelper;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.util.resource.Labels;

public class CostCache {

	private User user = UserCredentialManager.getUser();

	private List<Cost> costs = null;

	private String beginDatum;

	private String eindDatum;

	public List<Cost> getCosts() throws Exception {

		if (costs == null) {
			fillCosts();
		}
		return costs;
	}

	private void fillCosts() throws Exception {
		CostDao costDao = new CostDao();
		costs = costDao.getKostLijst(beginDatum, eindDatum, "alles", Long.toString(user.getId()));
		for (Cost cost : costs) {
			cost.setKostenSoortOmschrijving(Labels.getLabel(cost.getKostenSoortOmschrijving()));
		}
	}

	public void invalidate() {
		costs = null;
	}

	public List<DeductableCostGroup> getDeductableCosts() throws Exception {
		List<DeductableCostGroup> deductableCostList = new ArrayList<DeductableCostGroup>();
		for (Cost cost : costs) {
			CostType costType = CostTypeCache.getCostType(cost.getCostTypeId());
			if (costType.isAftrekbaar()) {
				DeductableCostGroup deductableCostGroup = new DeductableCostGroup();
				deductableCostGroup.setAftrekbaarBedrag(cost.getAmount());
				deductableCostGroup.setKostenSoortId(cost.getCostTypeId());
				deductableCostList.add(deductableCostGroup);
			}
		}
		Collections.sort(deductableCostList);
		long latestCostType = 0;
		DeductableCostGroup groupedCost = null;
		BigDecimal totalDeductableCost = new BigDecimal("0");
		List<DeductableCostGroup> groupedDeducatableCostList = new ArrayList<DeductableCostGroup>();
		for (DeductableCostGroup deductableCost : deductableCostList) {
			if (deductableCost.getKostenSoortId() != latestCostType) {
				if (groupedCost != null) {
					groupedCost.setAftrekbaarBedrag(totalDeductableCost);
					groupedCost.setKostenSoortId(latestCostType);
					groupedDeducatableCostList.add(groupedCost);
				}
				latestCostType = deductableCost.getKostenSoortId();
				groupedCost = new DeductableCostGroup();
				totalDeductableCost = new BigDecimal("0");
			}
			totalDeductableCost = totalDeductableCost.add(deductableCost.getAftrekbaarBedrag());
		}
		if (groupedCost != null) {
			groupedCost.setAftrekbaarBedrag(totalDeductableCost);
			groupedCost.setKostenSoortId(latestCostType);
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
			long id = cost.getCostTypeId();
			if (id == CostConstants.INVESTMENT) {
				if (cost.getAmount().compareTo(new BigDecimal(CostConstants.INVESTMENT_MINIMUM_AMOUNT)) == 1) {
					filteredCosts.add(cost);
				}
			}
		}
		return filteredCosts;
	}

	public BigDecimal getCostsWithPrivateMoney() throws Exception {
		BigDecimal costsWithPrivateMoney = BigDecimal.valueOf(0);
		for (Cost cost : costs) {
			long id = cost.getCostTypeId();
			if (id == CostConstants.EXPENSE_OTHER_ACCOUNT_IGNORE || id == CostConstants.EXPENSE_OTHER_ACCOUNT || id == CostConstants.TRAVEL_WITH_PUBLIC_TRANSPORT_OTHER_ACCOUNT
					|| id == CostConstants.AUTO_VAN_DE_ZAAK_ANDERE_REKENING || id == CostConstants.BUSINESS_FOOD_OTHER_ACCOUNT || id == CostConstants.BUSINESS_TRAVEL_CREDIT_CARD
					|| id == CostConstants.BUSINESS_LITERATURE_CREDIT_CARD_NO_VAT || id == CostConstants.INVESTMENT_OTHER_ACCOUNT) {

				costsWithPrivateMoney = costsWithPrivateMoney.add(cost.getAmount()).add(cost.getVat());
			}
		}
		return costsWithPrivateMoney;
	}

	public BigDecimal getInterest() throws Exception {
		BigDecimal interest = BigDecimal.valueOf(0);
		for (Cost cost : costs) {
			if (cost.getCostTypeId() == CostConstants.INTEREST) {
				interest = interest.add(cost.getAmount());
			}
		}
		return interest;
	}

	@Deprecated
	public List<Cost> getVatCorrectionDepreciation() throws Exception {
		List<Cost> filteredCosts = new ArrayList<Cost>();
		for (Cost cost : costs) {
			if (cost.getCostTypeId() == CostConstants.VAT_CORRECTION_CAR_DEPRECIATION) {
				filteredCosts.add(cost);
			}
		}
		return filteredCosts;
	}

	public PrepaidTax getPrepaidTax() throws Exception {
		PrepaidTax prepaidTax = new PrepaidTax();
		List<Cost> filteredCostList = new ArrayList<Cost>();
		for (Cost cost : costs) {
			long id = cost.getCostTypeId();
			if (id == CostConstants.INCOME_TAX) {
				filteredCostList.add(cost);
			}
		}
		int prepaidIncomeTax = 0;
		int prepaidHealthTax = 0;
		int year = DateHelper.getYear(DateHelper.stringToDate(beginDatum));
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
		BigDecimal repurchases = BigDecimal.valueOf(0);
		for (Cost cost : costs) {
			long id = cost.getCostTypeId();
			if (id == CostConstants.REPURCHASES) {
				repurchases = repurchases.add(cost.getAmount());
			}
		}
		return repurchases;
	}	

	public String getBeginDatum() {
		return beginDatum;
	}

	public void setBeginDatum(String beginDatum) {
		if (!beginDatum.equals(this.beginDatum)) {
			costs = null;
		}
		this.beginDatum = beginDatum;
	}

	public String getEindDatum() {
		return eindDatum;
	}

	public void setEindDatum(String eindDatum) {
		if (!eindDatum.equals(this.eindDatum)) {
			costs = null;
		}
		this.eindDatum = eindDatum;
	}

}
