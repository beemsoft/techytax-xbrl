package org.techytax.zk.vat;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import org.techytax.dao.BoekDao;
import org.techytax.dao.KostensoortDao;
import org.techytax.domain.Balans;
import org.techytax.domain.Cost;
import org.techytax.domain.Kostensoort;
import org.techytax.domain.Periode;
import org.techytax.domain.User;
import org.techytax.helper.BalanceCalculator;
import org.techytax.helper.RekeningFileAbnAmroHelper;
import org.techytax.helper.RekeningFileHelper;
import org.techytax.security.AuthenticationException;
import org.techytax.util.DateHelper;
import org.techytax.zk.login.UserCredentialManager;
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
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Window;

public class VatViewCtrl extends SelectorComposer<Window> {

	private static final long serialVersionUID = -6147927083401382065L;

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

	private Media media = null;
	
	@Listen("onClick=#logoffBtn")
	public void logoff(Event event) {
		UserCredentialManager.setUser(null);
		Executions.sendRedirect("login.zul"); 
	}
	
	@Listen("onClick=#costsBtn")
	public void allCosts(Event event) {
		Executions.sendRedirect("costs.zul"); 
	}	

	@Listen("onUpload=#uploadBtn")
	public void upload(UploadEvent event) throws WrongValueException, AuthenticationException, NoSuchAlgorithmException, IOException {
		System.out.println(" Testing upload: " + event.getMedia().getName());
		User user = UserCredentialManager.getUser();

		try {

			KostensoortDao dao = new KostensoortDao();
			List<Kostensoort> kostensoortLijst = dao.getCostTypesForAccount();

			media = event.getMedia();
			BufferedReader reader = new BufferedReader(media.getReaderData());
			String firstLine = reader.readLine();
			reader = new BufferedReader(media.getReaderData());
			List<Cost> result = null;
			if (firstLine.startsWith("\"Datum\"")) {
				result = RekeningFileHelper.readFileForIngBank(reader, kostensoortLijst, Long.toString(user.getId()));
			} else {
				result = RekeningFileAbnAmroHelper.readFileForAbnAmroBank(reader, kostensoortLijst, Long.toString(user.getId()));
			}

			for (Cost cost : result) {
				cost.setKostenSoortOmschrijving(Labels.getLabel(cost.getKostenSoortOmschrijving()));
			}
			for (Kostensoort costType : kostensoortLijst) {
				costType.setOmschrijving(Labels.getLabel(costType.getOmschrijving()));
			}
			ListModelList<Cost> costModel = new ListModelList<Cost>(result);
			costGrid.setModel(costModel);
			matchTab.setSelected(true);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@Listen("onClick=#reloadBtn")
	public void reload(Event event) {
		User user = UserCredentialManager.getUser();

		try {

			KostensoortDao dao = new KostensoortDao();
			List<Kostensoort> kostensoortLijst = dao.getCostTypesForAccount();

			BufferedReader reader = new BufferedReader(media.getReaderData());
			String firstLine = reader.readLine();
			reader = new BufferedReader(media.getReaderData());
			List<Cost> result = null;
			if (firstLine.startsWith("\"Datum\"")) {
				result = RekeningFileHelper.readFileForIngBank(reader, kostensoortLijst, Long.toString(user.getId()));
			} else {
				result = RekeningFileAbnAmroHelper.readFileForAbnAmroBank(reader, kostensoortLijst, Long.toString(user.getId()));
			}

			for (Cost cost : result) {
				cost.setKostenSoortOmschrijving(Labels.getLabel(cost.getKostenSoortOmschrijving()));
			}
			for (Kostensoort costType : kostensoortLijst) {
				costType.setOmschrijving(Labels.getLabel(costType.getOmschrijving()));
			}
			ListModelList<Cost> costModel = new ListModelList<Cost>(result);
			costGrid.setModel(costModel);
			matchTab.setSelected(true);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private static String format(BigDecimal amount) {
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.GERMAN);
		otherSymbols.setDecimalSeparator(',');
		otherSymbols.setGroupingSeparator('.');
		DecimalFormat df = new DecimalFormat("€ ###,###,###,##0.00", otherSymbols);
		return df.format(amount.doubleValue());
	}

	@Listen("onClick=#importBtn")
	public void importTransactions(Event event) throws Exception {
		User user = UserCredentialManager.getUser();
		ListModel<Cost> result = costGrid.getModel();
		BoekDao boekDao = new BoekDao();
		if (result != null) {
			Cost kost = null;

			for (int i = 0; i < result.getSize(); i++) {
				kost = (Cost) result.getElementAt(i);
				kost.setId(0);
				kost.setUserId(user.getId());
				boekDao.insertKost(kost);
			}
		}
		createVatOverview();
	}
	
	@Listen("onClick=#controleTab")
	public void displayVatOverview(Event event) throws Exception {
		createVatOverview();
	}	

	private void createVatOverview() throws Exception {
		BoekDao boekDao = new BoekDao();
		User user = UserCredentialManager.getUser();
		Periode vatPeriod = DateHelper.getLatestVatPeriod();
		List<Cost> vatCosts = boekDao.getKostLijst(DateHelper.getDate(vatPeriod.getBeginDatum()), DateHelper.getDate(vatPeriod.getEindDatum()), "btwBalans", Long.toString(user.getId()));
		for (Cost cost : vatCosts) {
			cost.setKostenSoortOmschrijving(Labels.getLabel(cost.getKostenSoortOmschrijving()));
		}
		ListModelList<Cost> costModel = new ListModelList<Cost>(vatCosts);
		vatGrid.setModel(costModel);
		Balans balans = BalanceCalculator.calculateBtwBalance(vatCosts, false);
		vatOut.setValue(format(balans.getTotaleKosten()));
		vatIn.setValue(format(balans.getTotaleBaten()));
		vatBalance.setValue(format(balans.getTotaleBaten().subtract(balans.getTotaleKosten()).add(balans.getCorrection())));
		turnoverGross.setValue(format(balans.getBrutoOmzet()));
		turnoverNet.setValue(format(balans.getNettoOmzet()));
		controleTab.setSelected(true);
		costModel = new ListModelList<Cost>();
		costGrid.setModel(costModel);
	}

	@Override
	public ComponentInfo doBeforeCompose(Page page, Component parent, ComponentInfo compInfo) {
		ComponentInfo componentInfo = super.doBeforeCompose(page, parent, compInfo);
		User user = UserCredentialManager.getUser();
		if (user == null) {
			Executions.sendRedirect("login.zul");
		}
		return componentInfo;
	}

}
