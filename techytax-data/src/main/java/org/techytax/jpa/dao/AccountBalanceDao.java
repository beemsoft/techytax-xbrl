package org.techytax.jpa.dao;

import static org.techytax.jpa.dao.QueryParameter.with;

import java.util.List;

import org.springframework.stereotype.Component;
import org.techytax.domain.Account;
import org.techytax.domain.AccountBalance;

//@Component
public class AccountBalanceDao extends GenericDao<AccountBalance> {

	public List<AccountBalance> getAccountBalances(Account account) {
		return findByNamedQuery(AccountBalance.BY_ACCOUNT, with("account", account).parameters());
	}
	
}
