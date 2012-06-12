package org.techytax.zk.vat;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.techytax.dao.BoekDao;
import org.techytax.dao.KostensoortDao;
import org.techytax.domain.Cost;
import org.techytax.domain.Kostensoort;
import org.techytax.domain.User;
import org.techytax.helper.RekeningFileAbnAmroHelper;
import org.techytax.helper.RekeningFileHelper;
import org.techytax.security.AuthenticationException;
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
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Window;

public class VatViewCtrl extends SelectorComposer<Window> {

	private static final long serialVersionUID = -6147927083401382065L;

	@Wire
	private Grid costGrid;
	
	@Wire
	private Tab matchTab;
	
	@Wire
	private Tab controleTab;	
	
	private Media media = null;
	

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
	
	@Listen("onClick=#importBtn")
	public void importTransactions(Event event) {
		User user = UserCredentialManager.getUser();
		BoekDao boekDao = new BoekDao();
		try {
			ListModel<Cost> result = costGrid.getModel();
			Cost kost = null;
			for (int i = 0; i < result.getSize(); i++) {
				kost = (Cost) result.getElementAt(i);
				kost.setId(0);
				kost.setUserId(user.getId());
//				boekDao.insertKost(kost);
			}
			controleTab.setSelected(true);
		} catch (Exception e) {
			e.printStackTrace();

		}		
	}	
	
	@Override
	public ComponentInfo doBeforeCompose(Page page, Component parent, ComponentInfo compInfo) {
		ComponentInfo componentInfo =  super.doBeforeCompose(page, parent, compInfo);
		User user = UserCredentialManager.getUser();
		if (user == null) {
			Executions.sendRedirect("login.zul");
		}
		return componentInfo;
	}
	
}
