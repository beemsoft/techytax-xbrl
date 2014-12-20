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
package org.techytax.zk.activa;

import static org.techytax.log.AuditType.ENTER_BOOKVALUE;
import static org.techytax.log.AuditType.UPDATE_ACTIVUM;
import static org.techytax.log.AuditType.UPDATE_BOOKVALUE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.techytax.dao.ActivumDao;
import org.techytax.dao.BookValueDao;
import org.techytax.domain.Activum;
import org.techytax.domain.BalanceType;
import org.techytax.domain.BookValue;
import org.techytax.domain.BookValueHistory;
import org.techytax.domain.User;
import org.techytax.log.AuditLogger;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.ClickEvent;
import org.zkoss.zul.Window;

public class ActivaVM {

	private List<BookValueHistory> bookValueHistories = new ArrayList<>();
	private Activum selected;
	private BookValueDao bookValueDao;
	private ActivumDao activumDao;
	
	private AuditLogger auditLogger;
	
	@Init
	public void init() {
		bookValueDao = (BookValueDao) SpringUtil.getBean("bookValueDao");
		activumDao = (ActivumDao) SpringUtil.getBean("activumDao");
		auditLogger = (AuditLogger) SpringUtil.getBean("auditLogger");
	}

	public ListModelList<BookValueHistory> getBookValueHistories() throws Exception {
		if (UserCredentialManager.getUser() != null) {
			bookValueHistories = new ArrayList<>();
			List<BookValue> bookValues = bookValueDao.getBookValuesHistory();
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
					bookValuesForBalanceType = new ArrayList<>();

					int bookYear = bookValue.getJaar();
					int year = firstYear;
					while (year > bookYear) {
						BookValue emptyBookValue = new BookValue();
						emptyBookValue.setJaar(year);
						emptyBookValue.setBalanceType(bookValue.getBalanceType());
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
		return new ListModelList<>(bookValueHistories);
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
		if (UserCredentialManager.getUser() != null) {
			Activum activum = (Activum) activumDao.getEntity(selected, selected.getId());
			if (activum == null) {
				activumDao.persistEntity(selected);
			} else {
				activumDao.merge(selected);
			}
		}
	}

	@Command
	public void addNew() {
		BookValue newBookValue = new BookValue();
		Map<String, Object> arguments = new HashMap<>();
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

	private void insertOrUpdate(BookValue bookValue) throws Exception {
		User user = UserCredentialManager.getUser();
		BookValue originalBookValue = null;
		if (!checkFiscalPensionLimit(bookValue)) {
			return;
		}
		if (bookValue.getId() > 0) {
			originalBookValue = (BookValue) bookValueDao.getEntity(bookValue, Long.valueOf(bookValue.getId()));
		}
		if (originalBookValue == null && bookValue.getSaldo() != null) {
			auditLogger.log(ENTER_BOOKVALUE, user);
			bookValue.setUser(user);
			bookValueDao.persistEntity(bookValue);
		} else if (!bookValue.equals(originalBookValue) && bookValue.getSaldo() != null) {
			auditLogger.log(UPDATE_BOOKVALUE, user);
			bookValue.setUser(user);
			bookValueDao.merge(bookValue);
		} else if (bookValue.getSaldo() == null) {
			bookValueDao.deleteEntity(bookValue);
		}
	}

	private boolean checkFiscalPensionLimit(BookValue bookValue) throws Exception {
		if (bookValue.getBalanceType() == BalanceType.PENSION) {
			BookValue nonCurrentAssets = bookValueDao.getBookValue(BalanceType.NON_CURRENT_ASSETS, bookValue.getJaar());
			if (bookValue.getSaldo() != null && bookValue.getSaldo().compareTo(nonCurrentAssets.getSaldo()) > 0) {
				Messagebox.show("FOR mag niet hoger worden dan eigen vermogen.", null, new Messagebox.Button[] { Messagebox.Button.OK }, Messagebox.EXCLAMATION,
						new org.zkoss.zk.ui.event.EventListener<ClickEvent>() {
							public void onEvent(ClickEvent e) {
								switch (e.getButton()) {
								case OK:
								default:
								}
							}
						});
				return false;
			}
		}
		return true;
	}

	public ListModelList<Activum> getActiva() throws Exception {
		if (UserCredentialManager.getUser() != null) {
			List<Activum> activaList = activumDao.getAllActiva();
			return new ListModelList<>(activaList);
		} else {
			Executions.sendRedirect("login.zul");
		}
		return null;
	}

	@Command
	public void onDoubleClicked() {
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("activum", selected);
		String template = "edit-activum.zul";
		Window window = (Window) Executions.createComponents(template, null, arguments);
		window.doModal();
	}

	@GlobalCommand
	@NotifyChange("activa")
	public void refreshvalues(@BindingParam("returnactivum") Activum activum) throws Exception {
		auditLogger.log(UPDATE_ACTIVUM, UserCredentialManager.getUser());
		activumDao.merge(activum);
	}

	@GlobalCommand
	@NotifyChange("bookValueHistories")
	public void refreshvalues2(@BindingParam("returnbookvalue") BookValue bookValue) throws Exception {
		insertOrUpdate(bookValue);
	}

}
