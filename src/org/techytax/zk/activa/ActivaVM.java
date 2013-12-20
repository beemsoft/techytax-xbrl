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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.techytax.dao.BookValueDao;
import org.techytax.dao.FiscalDao;
import org.techytax.domain.Activum;
import org.techytax.domain.BalanceType;
import org.techytax.domain.BookValue;
import org.techytax.domain.BookValueHistory;
import org.techytax.domain.KeyId;
import org.techytax.domain.User;
import org.techytax.log.AuditLogger;
import org.techytax.log.AuditType;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

public class ActivaVM {

	private List<BookValueHistory> bookValueHistories = new ArrayList<BookValueHistory>();
	private Activum selected;
	private FiscalDao fiscalDao = new FiscalDao();
	private BookValueDao bookValueDao = new BookValueDao();
	private ListModelList<Activum> activa;

	private User user = UserCredentialManager.getUser();

	public ListModelList<BookValueHistory> getBookValueHistories() throws Exception {
		if (user != null) {
			bookValueHistories = new ArrayList<BookValueHistory>();
			KeyId keyId = new KeyId();
			keyId.setUserId(user.getId());
			List<BookValue> bookValues = bookValueDao.getBookValuesHistory(keyId);
			BalanceType currentBalanceType = null;

			int firstYear = 0;
			for (BookValue bookValue : bookValues) {
				BalanceType balanceType = bookValue.getBalanceType();
				if (balanceType != currentBalanceType) {
					if (firstYear < bookValue.getJaar()) {
						firstYear = bookValue.getJaar();
					}
				}
			}

			currentBalanceType = null;
			List<BookValue> bookValuesForBalanceType = null;

			for (BookValue bookValue : bookValues) {
				BalanceType balanceType = bookValue.getBalanceType();
				if (balanceType != currentBalanceType) {
					if (bookValuesForBalanceType != null) {
						BookValueHistory bookValueHistory = new BookValueHistory(bookValuesForBalanceType);
						bookValueHistories.add(bookValueHistory);
					}
					bookValuesForBalanceType = new ArrayList<BookValue>();

					int bookYear = bookValue.getJaar();
					int year = firstYear;
					while (year > bookYear) {
						BookValue emptyBookValue = new BookValue();
						emptyBookValue.setJaar(year);
						emptyBookValue.setBalanceType(bookValue.getBalanceType());
						emptyBookValue.setDescription(bookValue.getDescription());
						bookValuesForBalanceType.add(emptyBookValue);
						year--;
					}
					bookValuesForBalanceType.add(bookValue);

					currentBalanceType = balanceType;
				} else {
					bookValuesForBalanceType.add(bookValue);
				}

			}
			if (bookValuesForBalanceType != null) {
				BookValueHistory bookValueHistory = new BookValueHistory(bookValuesForBalanceType);
				bookValueHistories.add(bookValueHistory);
			}
		} else {
			Executions.sendRedirect("login.zul");
		}
		return new ListModelList<BookValueHistory>(bookValueHistories);
	}

	public Activum getSelected() {
		return selected;
	}

	@NotifyChange({ "selected", "selectedAccountType", "accountBalances", "selectedAccountBalance" })
	public void setSelected(Activum selected) {
		this.selected = selected;
	}

	@NotifyChange("selected")
	@Command
	public void saveActivum() throws Exception {
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

	@Command
	public void addNew() {
		BookValue newBookValue = new BookValue();
		Map<String, Object> arguments = new HashMap<String, Object>();
		arguments.put("bookValue", newBookValue);
		String template = "~./bookvalue/edit-bookvalue.zul";
		Window window = (Window) Executions.createComponents(template, null, arguments);
		window.doModal();
	}

	@Command
	@NotifyChange("bookValueHistories")
	public void updateModelData(@ContextParam(ContextType.TRIGGER_EVENT) ModelDataChangeEvent event) throws Exception {
		BookValue bookValue = (BookValue) event.getData();
		insertOrUpdate(bookValue);
	}

	@GlobalCommand
	@NotifyChange("bookValueHistories")
	public void refreshvalues(@BindingParam("returnbookvalue") BookValue bookValue) throws Exception {
		insertOrUpdate(bookValue);
	}

	private void insertOrUpdate(BookValue bookValue) throws Exception {
		KeyId key = new KeyId();
		key.setId(bookValue.getId());
		key.setUserId(user.getId());
		BookValue originalBookValue = bookValueDao.getBookValue(key);
		bookValue.setUserId(user.getId());
		if (originalBookValue == null && bookValue.getSaldo() != null) {
			AuditLogger.log(AuditType.ENTER_BOOKVALUE, user);
			bookValueDao.insertBookValue(bookValue);
		} else if (!bookValue.equals(originalBookValue) && bookValue.getSaldo() != null) {
			AuditLogger.log(AuditType.UPDATE_BOOKVALUE, user);
			bookValueDao.updateBookValue(bookValue);
		} else if (bookValue.getSaldo() == null) {
			bookValueDao.deleteBookValue(key);
		}
	}

	public ListModelList<Activum> getActiva() throws Exception {
		if (user != null) {
			KeyId key = new KeyId();
			key.setUserId(user.getId());
			List<Activum> activaList = fiscalDao.getAllActivaForUser(key);
			return new ListModelList<Activum>(activaList);
		} else {
			Executions.sendRedirect("login.zul");
		}
		return null;
	}

	@Command
	public void onDoubleClicked() {
		Map<String, Object> arguments = new HashMap<String, Object>();
		arguments.put("activum", selected);
		String template = "edit-activum.zul";
		Window window = (Window) Executions.createComponents(template, null, arguments);
		window.doModal();
	}

}
