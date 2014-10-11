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

import org.techytax.domain.Account;
import org.techytax.domain.AccountType;

public class AccountDao extends BaseDao<Account> {
	
	public AccountDao(Class<Account> persistentClass) {
		super(persistentClass);
	}

	public AccountType getAccountType(String accountNumber) throws Exception {
		for (Account account: findAll()) {
			if (account.getNumber().equals(accountNumber)) {
				return account.getType();
			}
		}
		return null;
	}
	
	public Account getBusinessAccount() throws IllegalAccessException {
		List<Account> accounts = findAll();
		for (Account account: accounts) {
			if (account.getType() == AccountType.BUSINESS) {
				return account;
			}
		}
		return null;
	}	
	
}
