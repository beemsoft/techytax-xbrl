/**
 * Copyright 2009 Hans Beemsterboer
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

import java.sql.SQLException;
import java.util.List;

import org.techytax.domain.Account;
import org.techytax.domain.AccountBalance;
import org.techytax.util.IbatisUtil;

import com.ibatis.sqlmap.client.SqlMapClient;

public class AccountDao {
	public void insertAccount(Account account) throws Exception {
		SqlMapClient sqlMap = IbatisUtil.getSqlMapInstance();
		try {
			sqlMap.insert("insertAccount", account);
		} catch (SQLException ex) {
			throw ex;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Account> getAccounts() throws Exception {
		SqlMapClient sqlMap = IbatisUtil.getSqlMapInstance();
		try {
			return sqlMap.queryForList("getAccounts", null);
		} catch (SQLException ex) {
			throw ex;
		}
	}

	public void updateAccount(Account Account) throws Exception {
		SqlMapClient sqlMap = IbatisUtil.getSqlMapInstance();
		try {
			sqlMap.insert("updateAccount", Account);
		} catch (SQLException ex) {
			throw ex;
		}
	}

	public Account getAccount(String id) throws Exception {
		SqlMapClient sqlMap = IbatisUtil.getSqlMapInstance();
		try {
			return (Account) sqlMap.queryForObject("getAccount", id);
		} catch (SQLException ex) {
			throw ex;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<AccountBalance> getAccountBalance(String id) throws Exception {
		SqlMapClient sqlMap = IbatisUtil.getSqlMapInstance();
		try {
			return sqlMap.queryForList("getAccountBalance", id);
		} catch (SQLException ex) {
			throw ex;
		}
	}
	
	public void insertAccountBalance(AccountBalance accountBalance) throws Exception {
		SqlMapClient sqlMap = IbatisUtil.getSqlMapInstance();
		try {
			sqlMap.insert("insertAccountBalance", accountBalance);
		} catch (SQLException ex) {
			throw ex;
		}
	}	

}
