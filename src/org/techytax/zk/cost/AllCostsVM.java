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
package org.techytax.zk.cost;

import static org.techytax.domain.CostConstants.EXPENSE_CURRENT_ACCOUNT;
import static org.techytax.domain.CostConstants.EXPENSE_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.INVESTMENT;
import static org.techytax.domain.CostConstants.INVESTMENT_MINIMUM_AMOUNT;
import static org.techytax.domain.CostConstants.INVESTMENT_OTHER_ACCOUNT;
import static org.techytax.helper.DutchAuditFileHelper.sendAuditFile;
import static org.techytax.log.AuditType.DEPRECIATE_COST;
import static org.techytax.log.AuditType.ENTER_COST;
import static org.techytax.log.AuditType.SPLIT_COST;
import static org.techytax.log.AuditType.UPDATE_COST;
import static org.techytax.util.DateHelper.getLatestVatPeriod;
import static org.techytax.util.DateHelper.getPeriodPreviousYear;
import static org.techytax.util.DateHelper.isTimeForUsingLatestYearPeriod;
import static org.zkoss.zk.ui.Executions.sendRedirect;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.techytax.cache.CostTypeCache;
import org.techytax.domain.Cost;
import org.techytax.domain.CostType;
import org.techytax.domain.Periode;
import org.techytax.domain.VatPeriodType;
import org.techytax.helper.DepreciationHelper;
import org.techytax.log.AuditLogger;
import org.techytax.util.DateHelper;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

public class AllCostsVM extends CostVM3 {

	private Periode periode;
	private List<Cost> unhandledCosts = new ArrayList<>();
	private List<Cost> filteredCosts = new ArrayList<>();
	private boolean showUnhandledInvestments = false;
	private boolean filterCosts = false;
	private String searchString;

	public AllCostsVM() throws Exception {
		super();
		if (user != null) {
			if (isTimeForUsingLatestYearPeriod()) {
				periode = getPeriodPreviousYear();
			} else {
				periode = getLatestVatPeriod(user.getVatPeriodType());
			}
			getCosts();
		} else {
			periode = getLatestVatPeriod(VatPeriodType.PER_QUARTER);
			sendRedirect("login.zul");
		}
	}

	@Command
	public void audit() {
		sendAuditFile(user, periode);
	}

	public ListModelList<Cost> getCosts() throws Exception {
		if (user != null) {
			costCache.setBeginDatum(periode.getBeginDatum());
			costCache.setEindDatum(periode.getEindDatum());
			List<Cost> allCosts = costCache.getCosts();
			unhandledCosts = new ArrayList<>();
			filteredCosts = new ArrayList<>();
			for (Cost cost : allCosts) {
				if (filterCosts && StringUtils.isNotEmpty(searchString)) {
					if (cost.getDescription().toLowerCase().contains(searchString.toLowerCase())) {
						filteredCosts.add(cost);
					}
				} else {
					filterUnhandledCosts(cost);
				}
			}
			if (!showUnhandledInvestments && !filterCosts) {
				costs = new ListModelList<Cost>(allCosts);
			} else if (showUnhandledInvestments) {
				costs = new ListModelList<Cost>(unhandledCosts);
				showUnhandledInvestments = false;
			} else if (filterCosts) {
				costs = new ListModelList<Cost>(filteredCosts);
				filterCosts = false;
			}
		} else {
			Executions.sendRedirect("login.zul");
		}
		return costs;
	}

	private void filterUnhandledCosts(Cost cost) {
		if (cost.getAmount().compareTo(new BigDecimal(INVESTMENT_MINIMUM_AMOUNT)) == 1) {
			if (!cost.getCostType().equals(INVESTMENT) && cost.getCostType() != INVESTMENT_OTHER_ACCOUNT) {
				if (cost.getCostType().equals(EXPENSE_CURRENT_ACCOUNT) || cost.getCostType().equals(EXPENSE_OTHER_ACCOUNT)) {
					unhandledCosts.add(cost);
				}
			}
		}
	}

	@NotifyChange("costs")
	@Command
	public void showUnhandledInvestments() throws Exception {
		showUnhandledInvestments = true;
	}

	public boolean isListWithUnhandledInvestments() throws Exception {
		if (unhandledCosts != null && !unhandledCosts.isEmpty()) {
			return true;
		}
		return false;
	}

	public ListModelList<Cost> getBusinessCosts() throws Exception {
		if (user != null && costs == null) {
			List<Cost> vatCosts = costDao.getCostsOnBusinessAccountInPeriod(periode.getBeginDatum(), periode.getEindDatum());
			costs = new ListModelList<Cost>(vatCosts);
		}
		return costs;
	}

	public ListModelList<CostType> getCostTypes() throws Exception {
		if (costTypes == null) {
			Collection<CostType> vatCostTypes = CostTypeCache.getCostTypes();
			costTypes = new ListModelList<CostType>(vatCostTypes);
			selectedCostType = costTypes.get(0);
		}
		return costTypes;
	}

	@NotifyChange({ "costs", "listWithUnhandledInvestments" })
	public void setBeginDate(Date beginDate) {
		periode.setBeginDatum(beginDate);
	}

	public Date getBeginDate() {
		return periode.getBeginDatum();
	}

	@NotifyChange({ "costs", "listWithUnhandledInvestments" })
	public void setEndDate(Date endDate) {
		periode.setEindDatum(endDate);
	}

	public Date getEndDate() {
		return periode.getEindDatum();
	}

	@Command
	public void newCost() {
		Cost newCost = new Cost();
		newCost.setDate(new Date());
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("cost", newCost);
		String template = "edit-cost.zul";
		Window window = (Window) Executions.createComponents(template, null, arguments);
		window.doModal();
	}

	@Command
	public void onDoubleClicked() {
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("cost", selected);
		String template = "edit-cost.zul";
		Window window = (Window) Executions.createComponents(template, null, arguments);
		window.doModal();
	}

	@Command
	public void filterCosts() {
		doFilter(searchString);
	}

	@GlobalCommand
	@NotifyChange({ "costs", "selected" })
	public void refreshvalues(@BindingParam("returncost") Cost cost, @BindingParam("splitcost") Cost splitCost, @BindingParam("depreciations") List<Cost> depreciations,
			@BindingParam("isCar") boolean isCar, @BindingParam("remainingValue") BigInteger remainingValue, @BindingParam("yearlyDepreciation") BigDecimal yearlyDepreciation) throws Exception {
		if (depreciations != null && depreciations.size() > 0) {
			AuditLogger.log(DEPRECIATE_COST, user);
			for (Cost depreciation : depreciations) {
				depreciation.setUser(user);
				genericCostDao.persistEntity(depreciation);
			}
			if (cost.getCostType().equals(EXPENSE_CURRENT_ACCOUNT)) {
				cost.setCostType(INVESTMENT);
			} else if (cost.getCostType().equals(EXPENSE_OTHER_ACCOUNT)) {
				cost.setCostType(INVESTMENT_OTHER_ACCOUNT);
			}
			DepreciationHelper depreciationHelper = new DepreciationHelper();
			depreciationHelper.putOnBalance(cost, isCar, user.getId(), remainingValue, yearlyDepreciation, DateHelper.getYear(cost.getDate()));
		}

		Cost originalCost = costDao.getKost(cost);
		cost.setUser(user);
		if (originalCost == null) {
			AuditLogger.log(ENTER_COST, user);
			genericCostDao.persistEntity(cost);
			this.selected = cost;
		} else if (!cost.equals(originalCost)) {
			AuditLogger.log(UPDATE_COST, user);
			genericCostDao.merge(cost);
		}
		if (splitCost != null) {
			AuditLogger.log(SPLIT_COST, user);
			splitCost.setUser(user);
			costDao.insertSplitCost(cost, splitCost);
		}
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) throws Exception {
		this.searchString = searchString;
	}

	private void doFilter(String searchString) {
		this.searchString = searchString;
		if (searchString != null && searchString.length() > 1) {
			filterCosts = true;
			BindUtils.postNotifyChange("queueName", "desktop", this, "costs");
		} else {
			filterCosts = false;
			BindUtils.postNotifyChange("queueName", "desktop", this, "costs");
		}
	}
}
