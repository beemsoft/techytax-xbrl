/**
 * Copyright 2015 Hans Beemsterboer
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
package org.techytax.zk.vat;

import static org.techytax.domain.CostConstants.EXPENSE_OTHER_ACCOUNT_IGNORE;
import static org.techytax.domain.CostConstants.UNDETERMINED;
import static org.techytax.log.AuditType.IMPORT_TRANSACTIONS;
import static org.techytax.log.AuditType.UPLOAD_TRANSACTIONS;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.techytax.domain.Cost;
import org.techytax.domain.User;
import org.techytax.importing.helper.TransactionReader;
import org.techytax.importing.helper.TransactionReaderFactory;
import org.techytax.jpa.dao.CostDao;
import org.techytax.log.AuditLogger;
import org.techytax.security.AuthenticationException;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Window;

public class VatViewCtrl extends SelectorComposer<Window> {

	private static final long serialVersionUID = -6147927083401382065L;

	private User user = UserCredentialManager.getUser();

	private CostDao costDao;

	@Wire
	private Grid costGrid;
	@Wire
	private Tab matchTab;
	@Wire
	private Tab controleTab;

	private Media media = null;
	private BufferedReader reader = null;

	@Wire
	private Button reloadBtn;
	@Wire
	private Button importBtn;
	
	private AuditLogger auditLogger;
	
	private TransactionReaderFactory transactionReaderFactory;
	
	@Override
	public ComponentInfo doBeforeCompose(Page page, Component parent, ComponentInfo compInfo) {
		ComponentInfo componentInfo = super.doBeforeCompose(page, parent, compInfo);
		if (user == null) {
			Executions.sendRedirect("login.zul");
		}
		return componentInfo;
	}

	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		costDao = (CostDao) SpringUtil.getBean("costDao");
		auditLogger = (AuditLogger) SpringUtil.getBean("auditLogger");
		transactionReaderFactory = (TransactionReaderFactory) SpringUtil.getBean("transactionReaderFactory");
	}

	@Listen("onUpload=#uploadBtn")
	public void upload(UploadEvent event) throws WrongValueException, AuthenticationException, NoSuchAlgorithmException, IOException {
		auditLogger.log(UPLOAD_TRANSACTIONS, user);
		try {
			media = event.getMedia();
			List<Cost> result = readTransactions();
			ListModelList<Cost> costModel = new ListModelList<>(result);
			costGrid.setModel(costModel);
			matchTab.setSelected(true);
			reloadBtn.setDisabled(false);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private boolean listContainsLongDescriptions(List<Cost> result) {
		for (Cost cost : result) {
			if (cost.getDescription().length() > 200) {
				return true;
			}
		}
		return false;
	}

	private boolean listContainsUnmatchedTransactions(List<Cost> result) {
		for (Cost cost : result) {
			if (cost.getCostType() == null || cost.getCostType().equals(UNDETERMINED)) {
				return true;
			}
		}
		return false;
	}

	private List<Cost> filterUnmatchedTransactions(List<Cost> result) {
		List<Cost> filteredResult = new ArrayList<>();
		for (Cost cost : result) {
			if (cost.getCostType() == null || cost.getCostType().equals(UNDETERMINED)) {
				filteredResult.add(cost);
			}
		}
		return filteredResult;
	}

	private List<Cost> filterLongDescriptions(List<Cost> result) {
		List<Cost> filteredResult = new ArrayList<>();
		for (Cost cost : result) {
			if (cost.getDescription().length() > 200) {
				filteredResult.add(cost);
			}
		}
		return filteredResult;
	}

	private List<Cost> readTransactions() throws IOException, Exception {
		String firstLine = getFirstLine();
		InputStream is = new ByteArrayInputStream(media.getStringData().getBytes()); 
		Reader reader2 = new InputStreamReader(is);
		reader = new BufferedReader(reader2);
//		reader = new BufferedReader(media.getReaderData());
		TransactionReader importTransactions = transactionReaderFactory.getTransactionReader(firstLine);
		importTransactions.reset();
		List<Cost> result = importTransactions.readFile(reader);
		boolean unmatchedTransactions = listContainsUnmatchedTransactions(result);
		boolean longDescriptions = listContainsLongDescriptions(result);
		if (!unmatchedTransactions && !longDescriptions) {
			importBtn.setDisabled(false);
		} else if (unmatchedTransactions) {
			Messagebox.show("Er zijn nog onbepaalde transacties. Voeg tekstfragmenten toe, waarmee transacties herkend kunnen worden voor een bepaalde kostensoort.", null,
					new Messagebox.Button[] { Messagebox.Button.OK }, Messagebox.EXCLAMATION, null);
			return filterUnmatchedTransactions(result);
		} else if (longDescriptions) {
			Messagebox.show("Er zijn nog lange omschrijvingen. Maak deze wat korter om ze in te kunnen lezen.", null, new Messagebox.Button[] { Messagebox.Button.OK }, Messagebox.EXCLAMATION, null);
			return filterLongDescriptions(result);
		}
		return result;
	}

	private String getFirstLine() throws IOException {
		InputStream is = new ByteArrayInputStream(media.getStringData().getBytes()); 
		Reader reader2 = new InputStreamReader(is);
		reader = new BufferedReader(reader2);
//		reader = new BufferedReader(media.getReaderData());
		String firstLine = reader.readLine();
		return firstLine;
	}

	@Listen("onClick=#reloadBtn")
	public void reload(Event event) {
		try {
			List<Cost> result = readTransactions();
			ListModelList<Cost> costModel = new ListModelList<>(result);
			costGrid.setModel(costModel);
			matchTab.setSelected(true);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@Listen("onClick=#importBtn")
	public void importTransactions(Event event) throws Exception {
		auditLogger.log(IMPORT_TRANSACTIONS, user);
		ListModel<Cost> result = costGrid.getModel();
		if (result != null) {
			Cost kost = null;

			for (int i = 0; i < result.getSize(); i++) {
				kost = (Cost) result.getElementAt(i);
				if (!kost.getCostType().equals(EXPENSE_OTHER_ACCOUNT_IGNORE)) {
					kost.setUser(user);
					costDao.persistEntity(kost);
				}
			}
		}
		clearMatchListAfterImporting();
	}

	private void clearMatchListAfterImporting() {
		ListModelList<Cost> costModel = new ListModelList<>();
		if (costGrid != null) {
			costGrid.setModel(costModel);
		}
	}

	@Listen("onClick=#controleTab")
	public void displayVatOverview(Event event) throws Exception {
		controleTab.setSelected(true);
	}

}
