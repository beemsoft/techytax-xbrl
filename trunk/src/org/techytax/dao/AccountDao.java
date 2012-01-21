/**
 * Copyright 2011 Hans Beemsterboer
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

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.techytax.domain.Account;
import org.techytax.domain.AccountBalance;
import org.techytax.domain.AccountType;
import org.techytax.domain.KeyId;

public class AccountDao extends BaseDao {
	
	private void decrypt(Account account) {
		String name = account.getName();
		if (StringUtils.isNotEmpty(name)) {
			account.setName(textEncryptor.decrypt(name));
		}
		String number = account.getNumber();
		if (StringUtils.isNotEmpty(number)) {
			account.setNumber(textEncryptor.decrypt(number));
		}
		String description = account.getDescription();
		if (StringUtils.isNotEmpty(description)) {
			account.setDescription(textEncryptor.decrypt(description));
		}
	}
	
	private void encrypt(Account account) {
		account.setName(textEncryptor.encrypt(account.getName()));
		account.setNumber(textEncryptor.encrypt(account.getNumber()));
		account.setDescription(textEncryptor.encrypt(account.getDescription()));		
	}
	
	private void encrypt(AccountBalance accountBalance) {
		BigDecimal balance = accountBalance.getBalance();
		if (balance != null && balance.doubleValue() != 0) {
			accountBalance.setBalance(decimalEncryptor.encrypt(balance));
		}
	}	
	
	private void decrypt(AccountBalance accountBalance) {
		BigDecimal balance = accountBalance.getBalance();
		if (balance != null && balance.doubleValue() != 0) {
			accountBalance.setBalance(decimalEncryptor.decrypt(balance));
		}
	}	
	
	public void insertAccount(Account account) throws Exception {
		encrypt(account);
		sqlMap.insert("insertAccount", account);
	}	

	@SuppressWarnings("unchecked")
	public List<Account> getAccounts(KeyId key) throws Exception {
		List<Account> accounts = sqlMap.queryForList("getAccounts", key);
		for (Account account : accounts) {
			decrypt(account);
		}
		return accounts;
	}
	
	public AccountType getAccountType(String accountNumber, long userId) throws Exception {
		KeyId key = new KeyId();
		key.setUserId(userId);
		List<Account> accounts = getAccounts(key);
		for (Account account: accounts) {
			if (account.getNumber().equals(accountNumber)) {
				return account.getType();
			}
		}
		return null;
	}
	
	public Account getBusinessAccount(long userId) throws Exception {
		KeyId key = new KeyId();
		key.setUserId(userId);
		List<Account> accounts = getAccounts(key);
		for (Account account: accounts) {
			if (account.getType() == AccountType.BUSINESS) {
				return account;
			}
		}
		return null;
	}	
	
	@SuppressWarnings("unchecked")
	public List<Account> getAllAccounts() throws Exception {
		return sqlMap.queryForList("getAllAccounts", null);
	}	

	public void updateAccount(Account account) throws Exception {
		encrypt(account);
		sqlMap.insert("updateAccount", account);
	}
	
	public Account getAccount(KeyId key) throws Exception {
		Account account = (Account) sqlMap.queryForObject("getAccount", key);
		decrypt(account);
		return account;
	}
	
	@SuppressWarnings("unchecked")
	public List<AccountBalance> getAccountBalance(KeyId key) throws Exception {
		List<AccountBalance> balances = sqlMap.queryForList("getAccountBalance", key);
		for (AccountBalance balance : balances) {
			decrypt(balance);
		}
		return balances;
	}

	public void insertAccountBalance(AccountBalance accountBalance)
			throws Exception {
		accountBalance.setBalance(accountBalance.getBalance().setScale(2));
		encrypt(accountBalance);
		sqlMap.insert("insertAccountBalance", accountBalance);
	}
	
	@SuppressWarnings("unchecked")
	public List<AccountBalance> getAllAccountBalances() throws Exception {
		return sqlMap.queryForList("getAllAccountBalances", null);
	}	
	
	public void updateAccountBalance(AccountBalance accountBalance) throws Exception {
		encrypt(accountBalance);
		sqlMap.insert("updateAccountBalance", accountBalance);
	}	

}
