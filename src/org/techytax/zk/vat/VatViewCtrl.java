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
package org.techytax.zk.vat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.techytax.dao.BoekDao;
import org.techytax.digipoort.DigipoortService;
import org.techytax.digipoort.DigipoortServiceImpl;
import org.techytax.digipoort.XbrlHelper;
import org.techytax.domain.Balans;
import org.techytax.domain.Cost;
import org.techytax.domain.CostConstants;
import org.techytax.domain.Periode;
import org.techytax.domain.User;
import org.techytax.domain.VatDeclarationData;
import org.techytax.helper.AmountHelper;
import org.techytax.helper.BalanceCalculator;
import org.techytax.importing.helper.TransactionReader;
import org.techytax.importing.helper.TransactionReaderFactory;
import org.techytax.log.AuditLogger;
import org.techytax.log.AuditType;
import org.techytax.props.PropsFactory;
import org.techytax.security.AuthenticationException;
import org.techytax.util.DateHelper;
import org.techytax.ws.AanleverResponse;
import org.techytax.ws.AanleverServiceFault;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.GlobalCommandEvent;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
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
import org.zkoss.zkmax.ui.select.annotation.Subscribe;
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.ClickEvent;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

public class VatViewCtrl extends SelectorComposer<Window> {

	private static final long serialVersionUID = -6147927083401382065L;

	private User user = UserCredentialManager.getUser();

	private BoekDao boekDao = new BoekDao();

	@Wire
	private Grid costGrid;
	@Wire
	private Grid vatGrid;
	@Wire
	private Label vatOut;
	@Wire
	private Label vatIn;
	@Wire
	private Label vatBalance;
	@Wire
	private Label turnoverGross;
	@Wire
	private Label turnoverNet;
	@Wire
	private Tab matchTab;
	@Wire
	private Tab controleTab;
	@Wire
	private Popup sbrPopup;
	@Wire
	private Label msg;
	@Wire
	private Popup costPopup;
	@Wire
	private Toolbarbutton digipoortBtn;
	private Media media = null;
	private BufferedReader reader = null;
	private Balans balans = null;
	@Wire
	private Button reloadBtn;
	@Wire
	private Button importBtn;

	@Listen("onUpload=#uploadBtn")
	public void upload(UploadEvent event) throws WrongValueException,
			AuthenticationException, NoSuchAlgorithmException, IOException {
		AuditLogger.log(AuditType.UPLOAD_TRANSACTIONS, user);
		try {
			media = event.getMedia();
			List<Cost> result = readTransactions();

			for (Cost cost : result) {
				cost.setKostenSoortOmschrijving(Labels.getLabel(cost
						.getKostenSoortOmschrijving()));
			}

			ListModelList<Cost> costModel = new ListModelList<Cost>(result);
			costGrid.setModel(costModel);
			matchTab.setSelected(true);
			reloadBtn.setDisabled(false);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private boolean listContainsLongDescriptions(List<Cost> result) {
		for (Cost cost : result) {
			boekDao.encrypt(cost);
			if (cost.getDescription().length() > 400) {
				boekDao.decrypt(cost);
				return true;
			}
			boekDao.decrypt(cost);
		}
		return false;
	}

	private boolean listContainsUnmatchedTransactions(List<Cost> result) {
		for (Cost cost : result) {
			if (cost.getCostTypeId() == CostConstants.UNDETERMINED) {
				return true;
			}
		}
		return false;
	}

	private List<Cost> filterUnmatchedTransactions(List<Cost> result) {
		List<Cost> filteredResult = new ArrayList<Cost>();
		for (Cost cost : result) {
			if (cost.getCostTypeId() == CostConstants.UNDETERMINED) {
				filteredResult.add(cost);
			}
		}
		return filteredResult;
	}

	private List<Cost> filterLongDescriptions(List<Cost> result) {
		List<Cost> filteredResult = new ArrayList<Cost>();
		for (Cost cost : result) {
			boekDao.encrypt(cost);
			if (cost.getDescription().length() > 400) {
				boekDao.decrypt(cost);
				filteredResult.add(cost);
			}
		}
		return filteredResult;
	}

	private List<Cost> readTransactions() throws IOException, Exception {
		String firstLine = getFirstLine();
		reader = new BufferedReader(media.getReaderData());
		TransactionReader importTransactions = TransactionReaderFactory
				.getTransactionReader(firstLine);
		List<Cost> result = importTransactions.readFile(reader,
				Long.toString(user.getId()));
		boolean unmatchedTransactions = listContainsUnmatchedTransactions(result);
		boolean longDescriptions = listContainsLongDescriptions(result);
		if (!unmatchedTransactions && !longDescriptions) {
			importBtn.setDisabled(false);
		} else if (unmatchedTransactions) {
			Messagebox
					.show("Er zijn nog onbepaalde transacties. Voeg tekstfragmenten toe, waarmee transacties herkend kunnen worden voor een bepaalde kostensoort.",
							null,
							new Messagebox.Button[] { Messagebox.Button.OK },
							Messagebox.EXCLAMATION, null);
			return filterUnmatchedTransactions(result);
		} else if (longDescriptions) {
			Messagebox
					.show("Er zijn nog lange omschrijvingen. Maak deze wat korter om ze in te kunnen lezen.",
							null,
							new Messagebox.Button[] { Messagebox.Button.OK },
							Messagebox.EXCLAMATION, null);
			return filterLongDescriptions(result);
		}
		return result;
	}

	private String getFirstLine() throws IOException {
		reader = new BufferedReader(media.getReaderData());
		String firstLine = reader.readLine();
		return firstLine;
	}

	@Listen("onClick=#reloadBtn")
	public void reload(Event event) {
		try {
			List<Cost> result = readTransactions();

			for (Cost cost : result) {
				cost.setKostenSoortOmschrijving(Labels.getLabel(cost
						.getKostenSoortOmschrijving()));
			}

			ListModelList<Cost> costModel = new ListModelList<Cost>(result);
			costGrid.setModel(costModel);
			matchTab.setSelected(true);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@Listen("onClick=#importBtn")
	public void importTransactions(Event event) throws Exception {
		AuditLogger.log(AuditType.IMPORT_TRANSACTIONS, user);
		ListModel<Cost> result = costGrid.getModel();
		BoekDao boekDao = new BoekDao();
		if (result != null) {
			Cost kost = null;

			for (int i = 0; i < result.getSize(); i++) {
				kost = (Cost) result.getElementAt(i);
				if (kost.getCostTypeId() != CostConstants.EXPENSE_OTHER_ACCOUNT_IGNORE) {
					kost.setId(0);
					kost.setUserId(user.getId());
					boekDao.insertKost(kost);
				}
			}
		}
		createVatOverview();
		clearMatchListAfterImporting();
	}

	private void clearMatchListAfterImporting() {
		ListModelList<Cost> costModel = new ListModelList<Cost>();
		if (costGrid != null) {
			costGrid.setModel(costModel);
		}
	}

	@Listen("onClick=#controleTab")
	public void displayVatOverview(Event event) throws Exception {
		createVatOverview();
	}

	private void createVatOverview() throws Exception {
		BoekDao boekDao = new BoekDao();
		AuditLogger.log(AuditType.VAT_OVERVIEW, user);
		Periode vatPeriod = DateHelper.getLatestVatPeriod();
		List<Cost> vatCosts = boekDao.getKostLijst(
				DateHelper.getDate(vatPeriod.getBeginDatum()),
				DateHelper.getDate(vatPeriod.getEindDatum()), "btwBalans",
				Long.toString(user.getId()));
		for (Cost cost : vatCosts) {
			cost.setKostenSoortOmschrijving(Labels.getLabel(cost
					.getKostenSoortOmschrijving()));
		}
		ListModelList<Cost> costModel = new ListModelList<Cost>(vatCosts);
		vatGrid.setModel(costModel);
		vatGrid.setRowRenderer(new CostRowRenderer());

		balans = BalanceCalculator.calculateBtwBalance(vatCosts, false);
		VatDeclarationData vatDeclarationData = new VatDeclarationData();
		XbrlHelper.addBalanceData(vatDeclarationData, balans);
		vatOut.setValue(AmountHelper.formatWithEuroSymbol(vatDeclarationData
				.getValueAddedTaxSuppliesServicesGeneralTariff()));
		vatIn.setValue(AmountHelper.formatWithEuroSymbol(vatDeclarationData
				.getValueAddedTaxOnInput()));
		vatBalance.setValue(AmountHelper
				.formatWithEuroSymbol(vatDeclarationData
						.getValueAddedTaxOwedToBePaidBack()));
		turnoverGross.setValue(AmountHelper.formatWithEuroSymbol(balans
				.getBrutoOmzet().toBigInteger()));
		turnoverNet.setValue(AmountHelper.formatWithEuroSymbol(balans
				.getNettoOmzet().toBigInteger()));
		controleTab.setSelected(true);
	}

	@Listen("onClick=#digipoortBtn")
	public void aanleveren() throws FileNotFoundException, IOException,
			GeneralSecurityException {
		Messagebox.show("Weet u zeker dat u wilt aanleveren?", "Vraag",
				new Messagebox.Button[] { Messagebox.Button.OK,
						Messagebox.Button.CANCEL }, Messagebox.QUESTION,
				new org.zkoss.zk.ui.event.EventListener<ClickEvent>() {
					public void onEvent(ClickEvent e)
							throws FileNotFoundException, IOException,
							GeneralSecurityException {
						switch (e.getButton()) {
						case OK:
							try {
								AuditLogger.log(AuditType.SEND_VAT_DECLARATION,
										user);
								AanleverResponse aanleverResponse = doAanleveren();
								digipoortBtn.setDisabled(true);
								Messagebox.show(
										"Uw aanlevering is gelukt en heeft als kenmerk: "
												+ aanleverResponse.getKenmerk(),
										null,
										new Messagebox.Button[] { Messagebox.Button.OK },
										Messagebox.INFORMATION,
										new org.zkoss.zk.ui.event.EventListener<ClickEvent>() {
											public void onEvent(ClickEvent e) {
												switch (e.getButton()) {
												case OK:
												default:
												}
											}
										});
							} catch (AanleverServiceFault asf) {
								Messagebox.show(asf.getFaultInfo()
										.getFoutbeschrijving(), null, 0,
										Messagebox.ERROR);
							}
						case CANCEL:
						default:
						}
					}
				});
	}

	private AanleverResponse doAanleveren() throws FileNotFoundException,
			IOException, GeneralSecurityException, AanleverServiceFault {
		VatDeclarationData vatDeclarationData = createVatDeclarationData();
		String digipoortEnvironment = PropsFactory.getProperty("digipoort");
		String xbrlInstance = createXbrlInstanceForEnvironment(
				vatDeclarationData, digipoortEnvironment);
		if (!digipoortEnvironment.equals("prod")) {
			vatDeclarationData
					.setFiscalNumber(XbrlHelper.getTestFiscalNumber());
		}
		DigipoortService digipoortService = new DigipoortServiceImpl();
		return digipoortService.aanleveren(xbrlInstance,
				vatDeclarationData.getFiscalNumber());
	}

	private String createXbrlInstanceForEnvironment(
			VatDeclarationData vatDeclarationData, String digipoort) {
		String xbrlInstance = null;
		if (digipoort.equals("prod")) {
			xbrlInstance = XbrlHelper.createXbrlInstance(vatDeclarationData);
		} else {
			xbrlInstance = XbrlHelper.createTestXbrlInstance();
		}
		return xbrlInstance;
	}

	private VatDeclarationData createVatDeclarationData() {
		VatDeclarationData vatDeclarationData = new VatDeclarationData();
		vatDeclarationData.setFiscalNumber(user.getFiscalNumber());
		vatDeclarationData.setName(user.getFullName());
		vatDeclarationData.setPhoneNumber(user.getPhoneNumber());
		XbrlHelper.addBalanceData(vatDeclarationData, balans);
		Periode period = DateHelper.getLatestVatPeriod();
		vatDeclarationData.setStartDate(period.getBeginDatum());
		vatDeclarationData.setEndDate(period.getEindDatum());
		return vatDeclarationData;
	}

	@Listen("onClick=#sbrBtn")
	public void showSbrMessage() throws FileNotFoundException, IOException,
			GeneralSecurityException {
		VatDeclarationData vatDeclarationData = createVatDeclarationData();
		String digipoortEnvironment = PropsFactory.getProperty("digipoort");
		String xbrlInstance = createXbrlInstanceForEnvironment(
				vatDeclarationData, digipoortEnvironment);
		if (!digipoortEnvironment.equals("prod")) {
			vatDeclarationData
					.setFiscalNumber(XbrlHelper.getTestFiscalNumber());
		}
		msg.setValue(xbrlInstance);
		sbrPopup.open(this.getPage().getFirstRoot());
	}

	/**
	 * If the vat declaration has not yet been sent and the user has a fiscal
	 * number, then the button can be enabled.
	 */
	public boolean disableDigipoort() {
		Date declarationTime;
		try {
			declarationTime = AuditLogger
					.getVatDeclarationTimeForLatestVatPeriod(user);
			if (declarationTime != null && user.getFiscalNumber() != null) {
				return true;
			} else if (declarationTime == null
					&& user.getFiscalNumber() == null) {
				return true;
			}
		} catch (IllegalAccessException e) {
			Executions.sendRedirect("login.zul");
		}
		return false;
	}

	@Override
	public ComponentInfo doBeforeCompose(Page page, Component parent,
			ComponentInfo compInfo) {
		ComponentInfo componentInfo = super.doBeforeCompose(page, parent,
				compInfo);
		if (user == null) {
			Executions.sendRedirect("login.zul");
		}
		return componentInfo;
	}

	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		digipoortBtn.setDisabled(disableDigipoort());
	}

	@Subscribe("queueName")
	public void updateVatOverview(Event evt) throws Exception {
		if (evt instanceof GlobalCommandEvent) {
			if ("refreshvalues".equals(((GlobalCommandEvent) evt).getCommand())) {
				Map<String, Object> arguments = ((GlobalCommandEvent) evt)
						.getArgs();
				Cost updatedCost = (Cost) arguments.get("returncost");
				Cost originalCost = boekDao.getKost(
						Long.toString(updatedCost.getId()), user.getId());
				if (!updatedCost.equals(originalCost)) {
					updatedCost.setUserId(user.getId());
					boekDao.updateKost(updatedCost);

					Cost splitCost = (Cost) arguments.get("splitcost");
					if (splitCost != null) {
						splitCost.setUserId(user.getId());
						boekDao.insertSplitCost(originalCost, splitCost);
					}
					createVatOverview();
				}
			}
		}
	}
}
