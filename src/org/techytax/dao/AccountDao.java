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

import java.util.List;

import javax.persistence.TypedQuery;

import org.techytax.domain.Account;
import org.techytax.domain.AccountBalance;
import org.techytax.domain.AccountType;
import org.techytax.domain.User;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.zkplus.jpa.JpaUtil;

public class AccountDao extends BaseDao {
	
	private User user = UserCredentialManager.getUser();
	
	public List<Account> getAccounts() throws Exception {
		TypedQuery<Account> query = JpaUtil.getEntityManager().createQuery("SELECT a FROM org.techytax.domain.Account a WHERE a.user = :user", Account.class);
		query.setParameter("user", user);
		List<Account> accounts = query.getResultList();
		return accounts;
	}
	
	public AccountType getAccountType(String accountNumber) throws Exception {
		List<Account> accounts = getAccounts();
		for (Account account: accounts) {
			if (account.getNumber().equals(accountNumber)) {
				return account.getType();
			}
		}
		return null;
	}
	
	public Account getBusinessAccount() throws Exception {
		List<Account> accounts = getAccounts();
		for (Account account: accounts) {
			if (account.getType() == AccountType.BUSINESS) {
				return account;
			}
		}
		return null;
	}	
	
	public List<AccountBalance> getAccountBalances(Account account) throws Exception {
		TypedQuery<AccountBalance> query = JpaUtil.getEntityManager().createQuery("SELECT ab FROM org.techytax.domain.AccountBalance ab WHERE ab.account = :account AND ab.user = :user", AccountBalance.class);
		query.setParameter("user", user);
		query.setParameter("account", account);
		List<AccountBalance> accountBalances = query.getResultList();
		return accountBalances;
	}
	
}
