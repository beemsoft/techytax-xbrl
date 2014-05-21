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
package org.techytax.zk.vat;

import static org.techytax.domain.CostConstants.EXPENSE_OTHER_ACCOUNT_IGNORE;
import static org.techytax.domain.CostConstants.UNDETERMINED;
import static org.techytax.helper.AmountHelper.formatWithEuroSymbol;
import static org.techytax.helper.AmountHelper.roundDownToInteger;
import static org.techytax.log.AuditType.IMPORT_TRANSACTIONS;
import static org.techytax.log.AuditType.SEND_VAT_DECLARATION;
import static org.techytax.log.AuditType.SPLIT_COST;
import static org.techytax.log.AuditType.UPDATE_COST;
import static org.techytax.log.AuditType.UPLOAD_TRANSACTIONS;
import static org.techytax.log.AuditType.VAT_OVERVIEW;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.techytax.dao.CostDao;
import org.techytax.digipoort.DigipoortService;
import org.techytax.digipoort.DigipoortServiceImpl;
import org.techytax.digipoort.XbrlHelper;
import org.techytax.digipoort.XbrlNtp8Helper;
import org.techytax.domain.Cost;
import org.techytax.domain.FiscalPeriod;
import org.techytax.domain.User;
import org.techytax.domain.VatBalanceWithinEu;
import org.techytax.domain.VatDeclarationData;
import org.techytax.helper.AmountHelper;
import org.techytax.helper.BalanceCalculator;
import org.techytax.importing.helper.TransactionReader;
import org.techytax.importing.helper.TransactionReaderFactory;
import org.techytax.jpa.dao.GenericDao;
import org.techytax.jpa.entities.VatDeclaration;
import org.techytax.log.AuditLogger;
import org.techytax.props.PropsFactory;
import org.techytax.security.AuthenticationException;
import org.techytax.util.DateHelper;
import org.techytax.ws.AanleverResponse;
import org.techytax.ws.AanleverServiceFault;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.GlobalCommandEvent;
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

	private CostDao costDao = new CostDao(Cost.class);

	@Wire
	private Grid costGrid;
	@Wire
	private Grid vatGrid;
	@Wire
	private Label vatOut;
	@Wire
	private Label vatIn;
	@Wire
	private Label vatForPaymentWithinEu;
	@Wire
	private Label vatBalance;
	@Wire
	private Label turnoverGross;
	@Wire
	private Label turnoverNet;
	@Wire
	private Label vatCorrection;
	@Wire
	private Label totalOfPaymentsWithinEu;
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
	private VatBalanceWithinEu vatBalanceWithinEu = null;
	@Wire
	private Button reloadBtn;
	@Wire
	private Button importBtn;

	@Listen("onUpload=#uploadBtn")
	public void upload(UploadEvent event) throws WrongValueException, AuthenticationException, NoSuchAlgorithmException, IOException {
		AuditLogger.log(UPLOAD_TRANSACTIONS, user);
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
		reader = new BufferedReader(media.getReaderData());
		TransactionReader importTransactions = TransactionReaderFactory.getTransactionReader(firstLine);
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
		reader = new BufferedReader(media.getReaderData());
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
		AuditLogger.log(IMPORT_TRANSACTIONS, user);
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
		createVatOverview();
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
		createVatOverview();
	}

	private void createVatOverview() throws Exception {
		AuditLogger.log(VAT_OVERVIEW, user);
		List<Cost> vatCosts = costDao.getVatCostsInPeriod(DateHelper.getLatestVatPeriod(user.getVatPeriodType()));
		ListModelList<Cost> costModel = new ListModelList<>(vatCosts);
		vatGrid.setModel(costModel);
		vatGrid.setRowRenderer(new CostRowRenderer());

		vatBalanceWithinEu = BalanceCalculator.calculateBtwBalance(vatCosts, false);
		VatDeclarationData vatDeclarationData = new VatDeclarationData(user);
		if (vatBalanceWithinEu.getBrutoOmzet().equals(BigDecimal.ZERO)) {
			creatYearlyVatDeclarationForSmallEnterprise(DateHelper.getLatestVatPeriod(user.getVatPeriodType()), vatDeclarationData);
		} else {
			XbrlNtp8Helper.addBalanceData(vatDeclarationData, vatBalanceWithinEu);
		}
		vatOut.setValue(formatWithEuroSymbol(vatDeclarationData.getValueAddedTaxSuppliesServicesGeneralTariff()));
		vatIn.setValue(formatWithEuroSymbol(vatDeclarationData.getValueAddedTaxOnInput()));
		vatBalance.setValue(formatWithEuroSymbol(vatDeclarationData.getValueAddedTaxOwedToBePaidBack()));
		turnoverGross.setValue(formatWithEuroSymbol(vatBalanceWithinEu.getBrutoOmzet().toBigInteger()));
		turnoverNet.setValue(formatWithEuroSymbol(roundDownToInteger(vatBalanceWithinEu.getNettoOmzet())));
		vatCorrection.setValue(formatWithEuroSymbol(vatBalanceWithinEu.getCorrection().toBigInteger()));
		vatForPaymentWithinEu.setValue(formatWithEuroSymbol(vatBalanceWithinEu.getVatOutEu()));
		totalOfPaymentsWithinEu.setValue(formatWithEuroSymbol(AmountHelper.roundToInteger(vatBalanceWithinEu.getTurnoverNetEu())));
		
		controleTab.setSelected(true);
	}

	private void creatYearlyVatDeclarationForSmallEnterprise(FiscalPeriod vatPeriod, VatDeclarationData vatDeclarationData) {
		List<Cost> balanceCosts = costDao.getCostsOnBusinessAccountInPeriod(vatPeriod);
		BigDecimal turnover = BalanceCalculator.calculateTotalPaidInvoices(balanceCosts);
		vatBalanceWithinEu.setNettoOmzet(turnover);
		vatDeclarationData.setTaxedTurnoverSuppliesServicesGeneralTariff(AmountHelper.roundToInteger(turnover));
	}

	@Listen("onClick=#digipoortBtn")
	public void aanleveren() throws FileNotFoundException, IOException, GeneralSecurityException {
		Messagebox.show("Weet u zeker dat u wilt aanleveren?", "Vraag", new Messagebox.Button[] { Messagebox.Button.OK, Messagebox.Button.CANCEL }, Messagebox.QUESTION,
				new org.zkoss.zk.ui.event.EventListener<ClickEvent>() {
					public void onEvent(ClickEvent e) throws Exception {
						switch (e.getButton()) {
						case OK:
							try {
								AuditLogger.log(SEND_VAT_DECLARATION, user);
								AanleverResponse aanleverResponse = doAanleveren();
								digipoortBtn.setDisabled(true);
								if (aanleverResponse != null) {
									Messagebox.show("Uw aanlevering is gelukt en heeft als kenmerk: " + aanleverResponse.getKenmerk(), null, new Messagebox.Button[] { Messagebox.Button.OK },
											Messagebox.INFORMATION, null);
									storeDeclarationFeature(aanleverResponse.getKenmerk());
								} else {
									Messagebox.show("Aanlevering is mislukt!", null, 0, Messagebox.ERROR);
								}
							} catch (AanleverServiceFault asf) {
								Messagebox.show(asf.getFaultInfo().getFoutbeschrijving(), null, 0, Messagebox.ERROR);
							}
						case CANCEL:
						default:
						}
					}
				});
	}

	private void storeDeclarationFeature(String declarationFeature) throws Exception {
		GenericDao<VatDeclaration> vatDeclarationDao = new GenericDao<>(VatDeclaration.class);
		VatDeclaration vatDeclaration = new VatDeclaration();
		vatDeclaration.setDeclarationFeature(declarationFeature);
		vatDeclaration.setTimeStampDeclared(new Date());
		vatDeclaration.setUser(user);
		vatDeclarationDao.persistEntity(vatDeclaration);
	}

	private AanleverResponse doAanleveren() throws Exception {
		VatDeclarationData vatDeclarationData = createVatDeclarationData();
		String digipoortEnvironment = PropsFactory.getProperty("digipoort");
		String xbrlInstance = createXbrlInstanceForEnvironment(vatDeclarationData, digipoortEnvironment);
		DigipoortService digipoortService = new DigipoortServiceImpl();
		if (digipoortEnvironment.equals("prod")) {
			return digipoortService.aanleveren(xbrlInstance, vatDeclarationData.getUser().getFiscalNumber());
		} else {
			return digipoortService.aanleveren(xbrlInstance, XbrlNtp8Helper.getTestFiscalNumber());
		}
	}

	private String createXbrlInstanceForEnvironment(VatDeclarationData vatDeclarationData, String digipoort) throws Exception {
		String xbrlInstance = null;
		if (digipoort.equals("prod")) {
			if (DateHelper.isBefore2014(vatDeclarationData.getEndDate())) {
				xbrlInstance = XbrlHelper.createXbrlInstance(vatDeclarationData);
			} else {
				xbrlInstance = XbrlNtp8Helper.createXbrlInstance(vatDeclarationData);
			}
		} else {
			xbrlInstance = XbrlNtp8Helper.createTestXbrlInstance();
		}
		return xbrlInstance;
	}

	private VatDeclarationData createVatDeclarationData() throws Exception {
		VatDeclarationData vatDeclarationData = new VatDeclarationData(user);
		if (vatBalanceWithinEu.getBrutoOmzet().equals(BigDecimal.ZERO)) {
			vatDeclarationData.setTaxedTurnoverSuppliesServicesGeneralTariff(roundDownToInteger(vatBalanceWithinEu.getNettoOmzet()));
		} else {
			XbrlNtp8Helper.addBalanceData(vatDeclarationData, vatBalanceWithinEu);
		}
		FiscalPeriod period = DateHelper.getLatestVatPeriod(user.getVatPeriodType());
		vatDeclarationData.setStartDate(period.getBeginDate());
		vatDeclarationData.setEndDate(period.getEndDate());
		return vatDeclarationData;
	}

	@Listen("onClick=#sbrBtn")
	public void showSbrMessage() throws Exception {
		VatDeclarationData vatDeclarationData = createVatDeclarationData();
		String digipoortEnvironment = PropsFactory.getProperty("digipoort");
		String xbrlInstance = createXbrlInstanceForEnvironment(vatDeclarationData, digipoortEnvironment);
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
			declarationTime = AuditLogger.getVatDeclarationTimeForLatestVatPeriod(user);
			if (declarationTime != null && user.getFiscalNumber() != null) {
				return true;
			} else if (declarationTime == null && user.getFiscalNumber() == null) {
				return true;
			}
		} catch (IllegalAccessException e) {
			Executions.sendRedirect("login.zul");
		}
		return false;
	}

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
		digipoortBtn.setDisabled(disableDigipoort());
	}

	@Subscribe("queueName")
	public void updateVatOverview(Event evt) throws Exception {
		if (evt instanceof GlobalCommandEvent) {
			if ("refreshvalues".equals(((GlobalCommandEvent) evt).getCommand())) {
				Map<String, Object> arguments = ((GlobalCommandEvent) evt).getArgs();
				Cost updatedCost = (Cost) arguments.get("returncost");
				Cost originalCost = (Cost) costDao.getEntity(updatedCost, Long.valueOf(updatedCost.getId()));
				if (!updatedCost.equals(originalCost)) {
					updatedCost.setUser(user);
					AuditLogger.log(UPDATE_COST, user);
					costDao.merge(updatedCost);

					Cost splitCost = (Cost) arguments.get("splitcost");
					if (splitCost != null) {
						AuditLogger.log(SPLIT_COST, user);
						costDao.insertSplitCost(originalCost, splitCost);
					}
					createVatOverview();
				}
			}
		}
	}
}
