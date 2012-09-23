package org.techytax.zk.account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.techytax.dao.AccountDao;
import org.techytax.dao.BoekDao;
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
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zul.ListModelList;

public class AccountCheckVM extends CostVM3 {

	private AccountDao accountDao = new AccountDao();
	private User user = UserCredentialManager.getUser();
	private BigDecimal businessAccountBalance;
	
	protected Periode periode = DateHelper.getLatestVatPeriod();
	
	protected BigDecimal actualBalance;
	protected List<Cost> costList;
	protected Liquiditeit liquiditeit;
	protected BigDecimal brutoOmzet;
	protected BigDecimal taxBalance;
	protected BigDecimal costBalance;
	protected BigDecimal doubleCheck;	
	
	public ListModelList<Cost> getCosts() throws Exception {
		BoekDao boekDao = new BoekDao();
		User user = UserCredentialManager.getUser();
		if (user != null) {
			costList = boekDao.getKostLijst(DateHelper.getDate(periode.getBeginDatum()), DateHelper.getDate(periode.getEindDatum()), "rekeningBalans", Long.toString(user.getId()));
			for (Cost cost : costList) {
				cost.setKostenSoortOmschrijving(Labels.getLabel(cost.getKostenSoortOmschrijving()));
			}				
			costs = new ListModelList<Cost>(costList);
			getAccountCheck();
		}
		return costs;
	}
	
	public void getAccountCheck() throws Exception {
		User user = UserCredentialManager.getUser();
		actualBalance = BalanceCalculator.getActualAccountBalance(DateHelper.getDate(periode.getBeginDatum()), DateHelper.getDate(periode.getEindDatum()), user.getId());
		liquiditeit = BalanceCalculator.calculateAccountBalance(costList);
		BoekDao boekDao = new BoekDao();
		List<Cost> result2 = boekDao.getKostLijst(DateHelper.getDate(periode.getBeginDatum()), DateHelper.getDate(periode.getEindDatum()), "btwBalans", Long.toString(user.getId()));
		Balans balans = BalanceCalculator.calculateBtwBalance(result2, true);
		BigDecimal totalPaidInvoices = BalanceCalculator.calculateTotalPaidInvoices(costList);
		brutoOmzet = balans.getBrutoOmzet().add(totalPaidInvoices);
		List<Cost> result3 = boekDao.getKostLijst(DateHelper.getDate(periode.getBeginDatum()), DateHelper.getDate(periode.getEindDatum()), "tax", Long.toString(user.getId()));
		taxBalance = BalanceCalculator.calculateTaxBalance(result3).getTotaleKosten();
		List<Cost> result4 = boekDao.getCostListCurrentAccount(DateHelper.getDate(periode.getBeginDatum()), DateHelper.getDate(periode.getEindDatum()), Long.toString(user.getId()));
		costBalance = BalanceCalculator.calculateCostBalanceCurrentAccount(result4, true).getTotaleKosten();
		BigDecimal interest = boekDao.getInterest(DateHelper.getDate(periode.getBeginDatum()), DateHelper.getDate(periode.getEindDatum()), Long.toString(user.getId()));					
		doubleCheck = balans.getBrutoOmzet().add(totalPaidInvoices).subtract(taxBalance).subtract(costBalance).subtract(
				liquiditeit.getSpaarBalans().subtract(liquiditeit.getPriveBalans()).subtract(interest));
	
	}
	
	@NotifyChange({"selected"})
	public void setSelected(Cost selected) {
		this.selected = selected;
	}	
	
	@NotifyChange({"costs", "accountCheck", "actualBalance", "liquiditeit", "brutoOmzet", "taxBalance", "costBalance", "doubleCheck"})	
	public void setBeginDate(Date beginDate) {
		periode.setBeginDatum(beginDate);
	}
	
	public Date getBeginDate() {
		return periode.getBeginDatum();
	}
	
	@NotifyChange({"costs", "accountCheck", "actualBalance", "liquiditeit", "brutoOmzet", "taxBalance", "costBalance", "doubleCheck"})	
	public void setEndDate(Date endDate) {
		periode.setEindDatum(endDate);
	}
	
	public Date getEndDate() {
		return periode.getEindDatum();
	}

	public Periode getPeriode() {
		return periode;
	}

	public BigDecimal getActualBalance() {
		return actualBalance;
	}

	public List<Cost> getCostList() {
		return costList;
	}

	public Liquiditeit getLiquiditeit() {
		return liquiditeit;
	}

	public BigDecimal getBrutoOmzet() {
		return brutoOmzet;
	}

	public BigDecimal getTaxBalance() {
		return taxBalance;
	}

	public BigDecimal getCostBalance() {
		return costBalance;
	}

	public BigDecimal getDoubleCheck() {
		return doubleCheck;
	}

	public void setActualBalance(BigDecimal actualBalance) {
		this.actualBalance = actualBalance;
	}	

	// action command

	@NotifyChange("actualBalance")	
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
			getAccountCheck();
		}
	}

	public BigDecimal getBusinessAccountBalance() {
		return businessAccountBalance;
	}

	public void setBusinessAccountBalance(BigDecimal businessAccountBalance) {
		this.businessAccountBalance = businessAccountBalance;
	}

}
