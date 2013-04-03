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
package org.techytax.zk.account;

import java.util.List;

import org.techytax.dao.AccountDao;
import org.techytax.domain.Account;
import org.techytax.domain.AccountBalance;
import org.techytax.domain.AccountType;
import org.techytax.domain.KeyId;
import org.techytax.domain.User;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModelList;

public class AccountVM {

	ListModelList<Account> accounts;
	ListModelList<AccountType> accountTypes;
	ListModelList<AccountBalance> accountBalances;	
	Account selected;
	AccountType selectedAccountType;
	AccountBalance selectedAccountBalance;
	AccountDao accountDao = new AccountDao();
	User user = UserCredentialManager.getUser();

	public ListModelList<Account> getAccounts() throws Exception {
		if (accounts == null) {
			if (user != null) {
				KeyId key = new KeyId();
				key.setUserId(user.getId());
				List<Account> accounts2 = accountDao.getAccounts(key);
				accounts = new ListModelList<Account>(accounts2);
			}
		}
		return accounts;
	}
	
	public ListModelList<AccountBalance> getAccountBalances() throws Exception {
		if (user != null && selected != null) {
			KeyId key = new KeyId();
			key.setUserId(user.getId());
			key.setId(selected.getId());
			List<AccountBalance> accountBalances2 = accountDao.getAccountBalances(key);
			accountBalances = new ListModelList<AccountBalance>(accountBalances2);
		}
		return accountBalances;
	}	
	
	public ListModelList<AccountType> getAccountTypes() throws Exception {
		if (accountTypes == null) {
			AccountType[] accountTypeValues = AccountType.values();
			accountTypes = new ListModelList<AccountType>(accountTypeValues);
			selectedAccountType = accountTypes.get(0); 			
		}
		return accountTypes;
	}	

	public Account getSelected() {
		return selected;
	}
	
	@NotifyChange({"selected", "selectedAccountType", "accountBalances", "selectedAccountBalance"})
	public void setSelected(Account selected) {
		this.selected = selected;
		setSelectedAccountType(selected.getType());
		setSelectedAccountBalance(null);
	}
	
	public AccountType getSelectedAccountType() {
		return selectedAccountType;
	}
	
	public void setSelectedAccountType(AccountType selected) {
		this.selectedAccountType = selected;
	}
	
	public AccountBalance getSelectedAccountBalance() {
		return selectedAccountBalance;
	}
	
	@NotifyChange({"selectedAccountBalance"})
	public void setSelectedAccountBalance(AccountBalance selected) {
		this.selectedAccountBalance = selected;
	}	

	//action command
	
	@NotifyChange({"selected","accounts"})
	@Command
	public void newAccount() throws Exception{
		Account account = new Account();
		getAccounts().add(account);
		selected = account;//select the new one
	}
	
	@NotifyChange("selected")
	@Command
	public void saveAccount() throws Exception{
		if (user != null) {
			selected.setUserId(user.getId());
			selected.setType(selectedAccountType);
			KeyId key = new KeyId();
			key.setId(selected.getId());
			key.setUserId(user.getId());
			Account account = accountDao.getAccount(key);
			if (account == null) {
				accountDao.insertAccount(selected);
			} else {
				accountDao.updateAccount(selected);
			}
		}
	}
	
	@NotifyChange({"selectedAccountBalance","accountBalances"})
	@Command
	public void newAccountBalance() throws Exception{
		AccountBalance accountBalance = new AccountBalance();
		accountBalances.add(accountBalance);
		selectedAccountBalance = accountBalance;
	}
	
	@NotifyChange("selectedAccountBalance")
	@Command
	public void saveAccountBalance() throws Exception{
		if (user != null) {
			selectedAccountBalance.setUserId(user.getId());
			selectedAccountBalance.setAccountId(selected.getId());
			KeyId key = new KeyId();
			key.setId(selectedAccountBalance.getId());
			key.setUserId(user.getId());
			AccountBalance accountBalance = accountDao.getAccountBalance(key);
			if (accountBalance == null) {
				accountDao.insertAccountBalance(selectedAccountBalance);
			} else {
				accountDao.updateAccountBalance(selectedAccountBalance);
			}
		}
	}	
	
}
