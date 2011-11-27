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
package org.techytax.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.techytax.dao.AccountDao;
import org.techytax.domain.Account;
import org.techytax.domain.AccountType;
import org.techytax.domain.User;
import org.techytax.struts.form.AccountForm;

public class UpdateAccountAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		AccountForm accountForm = (AccountForm) form;

		Account account = new Account();
		account.setId(accountForm.getId());
		account.setDateClosed(accountForm.getDateClosed());
		account.setDateOpened(accountForm.getDateOpened());
		account.setDescription(accountForm.getDescription());
		account.setName(accountForm.getName());
		account.setNumber(accountForm.getNumber());
		account.setType(AccountType.getInstance(Integer.parseInt(accountForm.getType())));
		AccountDao accountDao = new AccountDao();
		User user = (User) request.getSession().getAttribute("user");
		account.setUserId(user.getId());
		accountDao.updateAccount(account);

		return mapping.findForward("success");

	}
}