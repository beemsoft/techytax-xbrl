package org.techytax.zk.vat;

import static org.techytax.log.AuditType.ENTER_COST;
import static org.techytax.log.AuditType.SEND_VAT_DECLARATION;
import static org.techytax.log.AuditType.SPLIT_COST;
import static org.techytax.log.AuditType.UPDATE_COST;
import static org.techytax.log.AuditType.VAT_OVERVIEW;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.techytax.jpa.dao.CostDao;
import org.techytax.jpa.dao.VatDeclarationDao;
import org.techytax.jpa.entities.VatDeclaration;
import org.techytax.log.AuditLogger;
import org.techytax.props.PropsFactory;
import org.techytax.util.DateHelper;
import org.techytax.ws.AanleverResponse;
import org.techytax.ws.AanleverServiceFault;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.GlobalCommandEvent;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkmax.ui.select.annotation.Subscribe;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.ClickEvent;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Window;

public class VatOverviewVM {

	private User user = UserCredentialManager.getUser();

	private AuditLogger auditLogger;

	private CostDao costDao;

	private VatDeclarationDao vatDeclarationDao;

	private BalanceCalculator balanceCalculator;

	@Wire
	private Grid vatGrid;

	@Wire
	private Popup sbrPopup;

	@Wire
	private Label msg;

	private VatBalanceWithinEu vatBalanceWithinEu;

	private VatDeclarationData vatDeclarationData;

	private boolean isDigipoortDisabled;

	private ListModelList<Cost> costs;
	
	private VatDeclaration vatDeclaration;
	
	protected Cost selected;
	
	private AMedia sbr;

	public AMedia getSbr() throws Exception {
		VatDeclarationData vatDeclarationData = createVatDeclarationData();
		String digipoortEnvironment = PropsFactory.getProperty("digipoort");
		String xbrlInstance = createXbrlInstanceForEnvironment(vatDeclarationData, digipoortEnvironment);
		final byte[] buf = xbrlInstance.getBytes();
		if (buf != null) {
			final InputStream mediais = new ByteArrayInputStream(buf);
			sbr = new AMedia("VatSbr.xml", "xml", "application/xml", mediais);
		}
		return sbr;
	}	

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view) throws Exception {
		auditLogger = (AuditLogger) SpringUtil.getBean("auditLogger");
		costDao = (CostDao) SpringUtil.getBean("costDao");
		balanceCalculator = (BalanceCalculator) SpringUtil.getBean("balanceCalculator");
		vatDeclarationDao = (VatDeclarationDao) SpringUtil.getBean("vatDeclarationDao");
		isDigipoortDisabled = disableDigipoort();

		createVatOverview();
	}

	public ListModelList<Cost> getCosts() {
		return costs;
	}

	public boolean isDigipoortDisabled() {
		return isDigipoortDisabled;
	}

	@NotifyChange("costs")
	private void createVatOverview() throws Exception {
		auditLogger.log(VAT_OVERVIEW, user);
		List<Cost> vatCosts = costDao.getVatCostsInPeriod(DateHelper.getLatestVatPeriod(user.getVatPeriodType()));
		costs = new ListModelList<>(vatCosts);
		vatBalanceWithinEu = balanceCalculator.calculateVatBalance(vatCosts, false);
		vatDeclarationData = new VatDeclarationData();
		if (vatBalanceWithinEu.getBrutoOmzet().equals(BigDecimal.ZERO)) {
			createYearlyVatDeclarationForSmallEnterprise(DateHelper.getLatestVatPeriod(user.getVatPeriodType()), vatDeclarationData);
		} else {
			XbrlNtp8Helper.addBalanceData(vatDeclarationData, vatBalanceWithinEu);
		}
		
		List<VatDeclaration> vatDeclarationsUnpaid = vatDeclarationDao.findByNamedQuery("VatDeclaration.findUnpaid");
		if (!vatDeclarationsUnpaid.isEmpty()) {
			vatDeclaration = vatDeclarationsUnpaid.get(0);
		}
	}

	public VatDeclaration getVatDeclaration() {
		return vatDeclaration;
	}

	public VatBalanceWithinEu getVatBalanceWithinEu() {
		return vatBalanceWithinEu;
	}

	public VatDeclarationData getVatDeclarationData() {
		return vatDeclarationData;
	}

	private void createYearlyVatDeclarationForSmallEnterprise(FiscalPeriod vatPeriod, VatDeclarationData vatDeclarationData) {
		List<Cost> balanceCosts = costDao.getCostsOnBusinessAccountInPeriod(vatPeriod);
		BigInteger turnover = AmountHelper.roundDownToInteger(balanceCalculator.calculateTotalPaidInvoices(balanceCosts));
		vatBalanceWithinEu.setNettoOmzet(turnover);
		vatDeclarationData.setTaxedTurnoverSuppliesServicesGeneralTariff(turnover);
	}

	/**
	 * If the vat declaration has not yet been sent and the user has a fiscal
	 * number, then the button can be enabled.
	 */
	public boolean disableDigipoort() {
		Date declarationTime;
		try {
			declarationTime = auditLogger.getVatDeclarationTimeForLatestVatPeriod();
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

	@NotifyChange("digipoortDisabled")
	@Command
	public void aanleveren() throws FileNotFoundException, IOException, GeneralSecurityException {
		Messagebox.show("Weet u zeker dat u wilt aanleveren?", "Vraag", new Messagebox.Button[] { Messagebox.Button.OK, Messagebox.Button.CANCEL }, Messagebox.QUESTION,
				new org.zkoss.zk.ui.event.EventListener<ClickEvent>() {
					public void onEvent(ClickEvent e) throws Exception {
						switch (e.getButton()) {
						case OK:
							try {
								auditLogger.log(SEND_VAT_DECLARATION, user);
								AanleverResponse aanleverResponse = doAanleveren();
								isDigipoortDisabled = true;
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
			return digipoortService.aanleveren(xbrlInstance, vatDeclarationData.getFiscalNumber());
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
		VatDeclarationData vatDeclarationData = new VatDeclarationData();
		if (vatBalanceWithinEu.getBrutoOmzet().equals(BigDecimal.ZERO)) {
			vatDeclarationData.setTaxedTurnoverSuppliesServicesGeneralTariff(vatBalanceWithinEu.getNettoOmzet());
		} else {
			XbrlNtp8Helper.addBalanceData(vatDeclarationData, vatBalanceWithinEu);
		}
		FiscalPeriod period = DateHelper.getLatestVatPeriod(user.getVatPeriodType());
		vatDeclarationData.setStartDate(period.getBeginDate());
		vatDeclarationData.setEndDate(period.getEndDate());
		vatDeclarationData.setFiscalNumber(user.getFiscalNumber());
		vatDeclarationData.setInitials(user.getInitials());
		vatDeclarationData.setSurname(user.getSurname());
		vatDeclarationData.setPhoneNumber(user.getPhoneNumber());
		return vatDeclarationData;
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
					auditLogger.log(UPDATE_COST, user);
					costDao.merge(updatedCost);

					Cost splitCost = (Cost) arguments.get("splitcost");
					if (splitCost != null) {
						auditLogger.log(SPLIT_COST, user);
						costDao.insertSplitCost(originalCost, splitCost);
					}
					createVatOverview();
				}
			}
		}
	}
	
	@GlobalCommand
	@NotifyChange({ "costs", "selected" })
	public void refreshvalues(@BindingParam("returncost") Cost cost, @BindingParam("splitcost") Cost splitCost) throws Exception {
		costDao = (CostDao) SpringUtil.getBean("costDao");
		auditLogger = (AuditLogger) SpringUtil.getBean("auditLogger");
		Cost originalCost = (Cost) costDao.getEntity(cost, Long.valueOf(cost.getId()));
		User user = UserCredentialManager.getUser();
		cost.setUser(user);
		if (originalCost == null) {
			auditLogger.log(ENTER_COST, user);
			cost.roundValues();
			costDao.persistEntity(cost);
			this.selected = cost;
		} else if (!cost.equals(originalCost)) {
			auditLogger.log(UPDATE_COST, user);
			cost.roundValues();
			costDao.merge(cost);
		}
		if (splitCost != null) {
			auditLogger.log(SPLIT_COST, user);
			costDao.insertSplitCost(cost, splitCost);
		}
	}	
	
	@Command
	public void onDoubleClicked() {
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("cost", selected);
		String template = "edit-cost.zul";
		Window window = (Window) Executions.createComponents(template, null, arguments);
		window.doModal();
	}
	
	public Cost getSelected() {
		return selected;
	}
	
	@NotifyChange({ "selected" })
	public void setSelected(Cost selected) {
		this.selected = selected;
	}

}
