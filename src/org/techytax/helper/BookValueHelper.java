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
package org.techytax.helper;

import org.techytax.dao.BookValueDao;
import org.techytax.domain.BalanceType;
import org.techytax.domain.BookValue;
import org.techytax.domain.KeyYear;
import org.techytax.domain.User;
import org.techytax.zk.login.UserCredentialManager;

public class BookValueHelper {

	private User user = UserCredentialManager.getUser();
	private BookValueDao boekwaardeDao = new BookValueDao();
	
	public BookValue getCurrentBookValue(BalanceType balanceType, KeyYear keyYear) throws Exception {
		BookValue bookValue = new BookValue();
		bookValue.setJaar(keyYear.getYear());
		bookValue.setBalanceType(balanceType);
		bookValue.setUser(user);
		bookValue = boekwaardeDao.getBookValue(balanceType, keyYear.getYear());
		return bookValue;
	}	
	
}
