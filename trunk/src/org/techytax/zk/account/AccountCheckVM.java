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
package org.techytax.zk.account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.techytax.cache.CostCache;
import org.techytax.dao.AccountDao;
import org.techytax.dao.CostDao;
import org.techytax.domain.Account;
import org.techytax.domain.AccountBalance;
import org.techytax.domain.Balans;
import org.techytax.domain.Cost;
import org.techytax.domain.Liquiditeit;
import org.techytax.domain.Periode;
import org.techytax.domain.User;
import org.techytax.helper.BalanceCalculator;
import org.techytax.util.DateHelper;
import org.techytax.zk.cost.CostVM3;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

public class AccountCheckVM extends CostVM3 {

	private AccountDao accountDao = new AccountDao();
	private CostDao costDao = new CostDao();
	private User user = UserCredentialManager.getUser();
	private BigDecimal businessAccountBalance;

	protected Periode periode = DateHelper.getLatestVatPeriod();
	private CostCache costCache = new CostCache();

	protected List<Cost> costList;
	private AccountCheckData accountCheckData = new AccountCheckData();

	public ListModelList<Cost> getCosts() throws Exception {
		User user = UserCredentialManager.getUser();
		if (user != null) {
			costCache.setBeginDatum(DateHelper.getDate(periode.getBeginDatum()));
			costCache.setEindDatum(DateHelper.getDate(periode.getEindDatum()));
			costCache.getCosts();
			costList = costCache.getBusinessAccountCosts();
			costs = new ListModelList<Cost>(costList);
			getAccountCheck();
		} else {
			Executions.sendRedirect("login.zul");	
		}
		return costs;
	}

	public void getAccountCheck() throws Exception {
		User user = UserCredentialManager.getUser();
		if (user != null) {
			BigDecimal actualBalance = BalanceCalculator.getActualAccountBalance(DateHelper.getDate(periode.getBeginDatum()),
					DateHelper.getDate(periode.getEindDatum()), user.getId());
			Liquiditeit liquiditeit = BalanceCalculator.calculateAccountBalance(costList);
			List<Cost> result2 = costDao.getKostLijst(DateHelper.getDate(periode.getBeginDatum()), DateHelper.getDate(periode.getEindDatum()),
					"btwBalans", Long.toString(user.getId()));
			Balans balans = BalanceCalculator.calculateBtwBalance(result2, true);
			BigDecimal totalPaidInvoices = BalanceCalculator.calculateTotalPaidInvoices(costList);
			BigDecimal brutoOmzet = balans.getBrutoOmzet().add(totalPaidInvoices);
			List<Cost> result3 = costDao.getKostLijst(DateHelper.getDate(periode.getBeginDatum()), DateHelper.getDate(periode.getEindDatum()), "tax",
					Long.toString(user.getId()));
			BigDecimal taxBalance = BalanceCalculator.calculateTaxBalance(result3).getTotaleKosten();
			List<Cost> result4 = costDao.getCostListCurrentAccount(DateHelper.getDate(periode.getBeginDatum()),
					DateHelper.getDate(periode.getEindDatum()), Long.toString(user.getId()));
			BigDecimal costBalance = BalanceCalculator.calculateCostBalanceCurrentAccount(result4, true).getTotaleKosten();
			BigDecimal interest = costCache.getInterest();
			BigDecimal costIgnoreBalance = costDao.getCostsCurrentAccountIgnore(DateHelper.getDate(periode.getBeginDatum()),
					DateHelper.getDate(periode.getEindDatum()), Long.toString(user.getId()));
			accountCheckData.setCostIgnoreBalance(costIgnoreBalance);
			BigDecimal doubleCheck = balans.getBrutoOmzet().add(totalPaidInvoices).subtract(taxBalance).subtract(costBalance)
					.subtract(liquiditeit.getSpaarBalans().subtract(liquiditeit.getPriveBalans()).subtract(interest)).add(costIgnoreBalance);

			accountCheckData.setAccountBalance(liquiditeit.getRekeningBalans());
			accountCheckData.setCostBalance(costBalance);
			accountCheckData.setGrossIncome(brutoOmzet);
			accountCheckData.setInterest(interest);
			accountCheckData.setPaidInvoices(totalPaidInvoices);
			accountCheckData.setPrivateWithdrawalBalance(liquiditeit.getPriveBalans());
			accountCheckData.setSavingBalance(liquiditeit.getSpaarBalans());
			accountCheckData.setTaxBalance(taxBalance);
			accountCheckData.setDoubleCheck(doubleCheck);
			accountCheckData.setActualBalance(actualBalance);
		} else {
			Executions.sendRedirect("login.zul");
		}
	}

	@NotifyChange({ "selected" })
	public void setSelected(Cost selected) {
		this.selected = selected;
	}

	@Command
	public void onDoubleClicked() {
		Map<String, Object> arguments = new HashMap<String, Object>();
		arguments.put("cost", selected);
		String template = "edit-cost.zul";
		Window window = (Window) Executions.createComponents(template, null, arguments);
		window.doModal();
	}

	@GlobalCommand
	@NotifyChange({ "costs" })
	public void refreshvalues(@BindingParam("returncost") Cost cost, @BindingParam("splitcost") Cost splitCost) throws Exception {
		Cost originalCost = costDao.getKost(Long.toString(cost.getId()), user.getId());
		if (!cost.equals(originalCost)) {
			cost.setUserId(user.getId());
			costDao.updateKost(cost);

			if (splitCost != null) {
				splitCost.setUserId(user.getId());
				costDao.insertSplitCost(cost, splitCost);
			}
		}
	}

	@NotifyChange({ "costs", "accountCheck", "accountCheckData" })
	public void setBeginDate(Date beginDate) {
		periode.setBeginDatum(beginDate);
	}

	public Date getBeginDate() {
		return periode.getBeginDatum();
	}

	@NotifyChange({ "costs", "accountCheck", "accountCheckData" })
	public void setEndDate(Date endDate) {
		periode.setEindDatum(endDate);
	}

	public Date getEndDate() {
		return periode.getEindDatum();
	}

	public Periode getPeriode() {
		return periode;
	}

	public List<Cost> getCostList() {
		return costList;
	}

	// action command

	@NotifyChange({ "accountCheck", "accountCheckData" })
	@Command
	public void saveAccountBalance() throws Exception {
		if (user != null) {
			AccountBalance accountBalance = new AccountBalance();
			accountBalance.setUserId(user.getId());
			accountBalance.setBalance(businessAccountBalance);
			accountBalance.setDatum(periode.getEindDatum());
			Account businessAccount = accountDao.getBusinessAccount(user.getId());
			accountBalance.setAccountId(businessAccount.getId());
			accountDao.insertAccountBalance(accountBalance);
			getCosts();
		}
	}

	public BigDecimal getBusinessAccountBalance() {
		return businessAccountBalance;
	}

	public void setBusinessAccountBalance(BigDecimal businessAccountBalance) {
		this.businessAccountBalance = businessAccountBalance;
	}

	public AccountCheckData getAccountCheckData() {
		return accountCheckData;
	}

	public void setAccountCheckData(AccountCheckData accountCheckData) {
		this.accountCheckData = accountCheckData;
	}

}
