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
package org.techytax.zk.activa;

import java.util.List;

import org.techytax.dao.AccountDao;
import org.techytax.dao.BoekDao;
import org.techytax.dao.BookValueDao;
import org.techytax.dao.FiscalDao;
import org.techytax.domain.Account;
import org.techytax.domain.AccountBalance;
import org.techytax.domain.AccountType;
import org.techytax.domain.Activum;
import org.techytax.domain.KeyId;
import org.techytax.domain.KeyYear;
import org.techytax.domain.User;
import org.techytax.helper.ActivaHelper;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.Locales;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.ListModelList;

public class ActivaVM {

	ListModelList<Activum> activa;
	Activum selected;
	AccountType selectedAccountType;
	AccountBalance selectedAccountBalance;
	AccountDao accountDao = new AccountDao();
	private static FiscalDao fiscalDao = new FiscalDao();
	private static BookValueDao bookValueDao = new BookValueDao();
	private static BoekDao boekDao = new BoekDao();	
	private User user = UserCredentialManager.getUser();

	public ListModelList<Activum> getActiva() throws Exception {
		if (activa  == null) {
			if (user != null) {
				KeyYear key = new KeyYear(user.getId(), 2012);
				List<Activum> activa2 = ActivaHelper.getAndTranslate(Locales.getCurrent(), key);
				activa = new ListModelList<Activum>(activa2);
			}
		}
		return activa;
	}
	
	public Activum getSelected() {
		return selected;
	}
	
	@NotifyChange({"selected", "selectedAccountType", "accountBalances", "selectedAccountBalance"})
	public void setSelected(Activum selected) {
		this.selected = selected;
	}
	
	@NotifyChange({"selectedAccountBalance"})
	public void setSelectedAccountBalance(AccountBalance selected) {
		this.selectedAccountBalance = selected;
	}	

	//action command
	
	@NotifyChange({"selected","activa"})
	@Command
	public void newActivum() throws Exception{
		Activum activum = new Activum();
		getActiva().add(activum);
		selected = activum;//select the new oneAccount
	}
	
	@NotifyChange("selected")
	@Command
	public void saveActivum() throws Exception{
		if (user != null) {
			selected.setUserId(user.getId());
			KeyId key = new KeyId();
			key.setId(selected.getId());
			key.setUserId(user.getId());
			Activum activum = fiscalDao.getActivum(key);
			if (activum == null) {
				fiscalDao.insertActivum(selected);
			} else {
				fiscalDao.updateActivum(selected);
			}
		}
	}
	
}
