package org.techytax.zk.xbrl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.techytax.digipoort.DigipoortService;
import org.techytax.digipoort.DigipoortServiceImpl;
import org.techytax.digipoort.DigipoortServiceStub;
import org.techytax.digipoort.XbrlHelper;
import org.techytax.domain.Periode;
import org.techytax.domain.User;
import org.techytax.domain.VatDeclarationData;
import org.techytax.props.PropsFactory;
import org.techytax.util.DateHelper;
import org.techytax.ws.ArrayOfBerichtsoortResultaat;
import org.techytax.ws.ArrayOfProcesResultaat;
import org.techytax.ws.ArrayOfStatusResultaat;
import org.techytax.ws.GetBerichtsoortenResponse;
import org.techytax.ws.GetNieuweStatussenProcesResponse;
import org.techytax.ws.GetNieuweStatussenResponse;
import org.techytax.ws.GetProcessenResponse;
import org.techytax.ws.GetStatussenProcesResponse;
import org.techytax.ws.ProcesResultaat;
import org.techytax.ws.StatusResultaat;
import org.techytax.wus.status.StatusinformatieServiceFault;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Popup;

public class XbrlVM implements Serializable {

	private static final long serialVersionUID = -4600263940170016764L;
	
	protected User user = UserCredentialManager.getUser();
	private Periode periode = DateHelper.getLatestVatPeriodTillToday();
	private DigipoortService digipoortService;
	private List<String> berichtsoorten;
	private List<ProcesResultaat> processen;
	private List<StatusResultaat> nieuweStatussen;
	private List<StatusResultaat> nieuweStatussenProces;
	private List<StatusResultaat> statussenProces;
	private boolean isTestEnvironment = false;
	
	private StatusResultaat selected;
	
	public XbrlVM() throws IOException {
		String digipoort = PropsFactory.getProperty("digipoort");
		if (digipoort.equals("prod")) {
			isTestEnvironment = false;
		} else {
			isTestEnvironment = true;
		}
		if (digipoort.equals("stub")) {
			digipoortService = new DigipoortServiceStub();
		} else {
			digipoortService = new DigipoortServiceImpl();
		}
	}
	
	@Wire
	Popup msgPopup;
	@Wire
	Label msg;

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Command
	public void popupMessage(@BindingParam("target") Component targetComponent, @BindingParam("content") String content) {
		if (StringUtils.isNotEmpty(content)) {
			msg.setPre(true);
			msg.setValue(content);
			msgPopup.open(targetComponent);
		}
	}   
	
	public void setStatussenProces(List<StatusResultaat> statussenProces) {
		this.statussenProces = statussenProces;
	}

	private String fiscalNumber;
	private String kenmerk;
	private ProcesResultaat selectedProces;

	public ProcesResultaat getSelectedProces() {
		return selectedProces;
	}

	@NotifyChange({ "selectedProces", "statussenProces" })
	public void setSelectedProces(ProcesResultaat selectedProces) {
		this.selectedProces = selectedProces;
	}

	public String getKenmerk() {
		return kenmerk;
	}

	public void setKenmerk(String kenmerk) {
		this.kenmerk = kenmerk;
	}

	@Command
	@NotifyChange("berichtsoorten")
	public void berichtsoort() throws FileNotFoundException, IOException, GeneralSecurityException {
		try {
			VatDeclarationData vatDeclarationData = new VatDeclarationData();
			setFiscalNumber(vatDeclarationData);
			GetBerichtsoortenResponse response = digipoortService.getBerichtsoorten(vatDeclarationData);
			ArrayOfBerichtsoortResultaat resultaat = response.getGetBerichtsoortenReturn();
			berichtsoorten = resultaat.getBerichtsoort();
		} catch (StatusinformatieServiceFault e) {
			e.printStackTrace();
			Messagebox.show(e.getFaultInfo().getFoutbeschrijving(), null, 0, Messagebox.ERROR);
		}
	}

	private void setFiscalNumber(VatDeclarationData vatDeclarationData) {
		if (!isTestEnvironment) {
			String fiscalNumber = user.getFiscalNumber();
			if (StringUtils.isEmpty(fiscalNumber)) {
				throw new RuntimeException("U heeft nog geen fiscaal nummer opgegeven");
			}
			vatDeclarationData.setFiscalNumber(user.getFiscalNumber());
		} else {
			vatDeclarationData.setFiscalNumber(XbrlHelper.getTestFiscalNumber());
		}
	}

	@Command
	@NotifyChange("processen")
	public void processen() throws FileNotFoundException, IOException, GeneralSecurityException {
		VatDeclarationData vatDeclarationData = new VatDeclarationData();
		setFiscalNumber(vatDeclarationData);
		GetProcessenResponse response;
		try {
			response = digipoortService.getProcessen(vatDeclarationData);
			ArrayOfProcesResultaat resultaat = response.getGetProcessenReturn();
			processen = resultaat.getProcesResultaat();
		} catch (StatusinformatieServiceFault e) {
			e.printStackTrace();
			Messagebox.show(e.getFaultInfo().getFoutbeschrijving(), null, 0, Messagebox.ERROR);
		}
	}

	@Command
	@NotifyChange("nieuweStatussen")
	public void nieuweStatussen() {
		try {
			VatDeclarationData vatDeclarationData = new VatDeclarationData();
			setFiscalNumber(vatDeclarationData);
			setDates(vatDeclarationData);
			GetNieuweStatussenResponse response = digipoortService.getNieuweStatussen(vatDeclarationData);
			ArrayOfStatusResultaat resultaat = response.getGetNieuweStatussenReturn();
			nieuweStatussen = resultaat.getStatusResultaat();
		} catch (Exception e) {
			Messagebox.show(e.getMessage(), null, 0, Messagebox.ERROR);
		}
	}

	private void setDates(VatDeclarationData vatDeclarationData) {
		vatDeclarationData.setStartDate(periode.getBeginDatum());
		vatDeclarationData.setEndDate(periode.getEindDatum());
	}

	@Command
	public void nieuweStatussenProces() {
		try {
			VatDeclarationData vatDeclarationData = new VatDeclarationData();
			setFiscalNumber(vatDeclarationData);
			setDates(vatDeclarationData);
			GetNieuweStatussenProcesResponse response = digipoortService.getNieuweStatussenProces(vatDeclarationData, selectedProces.getKenmerk());
			ArrayOfStatusResultaat resultaat = response.getGetNieuweStatussenProcesReturn();
			nieuweStatussenProces = resultaat.getStatusResultaat();
		} catch (Exception e) {
			e.printStackTrace();
			Messagebox.show(e.getMessage(), null, 0, Messagebox.ERROR);
		}
	}

	public List<StatusResultaat> getStatussenProces() throws FileNotFoundException, IOException, GeneralSecurityException {
		if (selectedProces != null) {
			try {
				VatDeclarationData vatDeclarationData = new VatDeclarationData();
				setFiscalNumber(vatDeclarationData);
				setDates(vatDeclarationData);
				GetStatussenProcesResponse response = digipoortService.getStatussenProces(vatDeclarationData, selectedProces.getKenmerk());
				ArrayOfStatusResultaat resultaat = response.getGetStatussenProcesReturn();
				statussenProces = resultaat.getStatusResultaat();
			} catch (StatusinformatieServiceFault e) {
				e.printStackTrace();
				Messagebox.show(e.getFaultInfo().getFoutbeschrijving(), null, 0, Messagebox.ERROR);
			}
		}
		return statussenProces;
	}

	public List<StatusResultaat> getNieuweStatussenProces() {
		return nieuweStatussenProces;
	}

	public void setNieuweStatussenProces(List<StatusResultaat> nieuweStatussenProces) {
		this.nieuweStatussenProces = nieuweStatussenProces;
	}

	public List<StatusResultaat> getNieuweStatussen() {
		return nieuweStatussen;
	}

	public void setNieuweStatussen(List<StatusResultaat> nieuweStatussen) {
		this.nieuweStatussen = nieuweStatussen;
	}

	public List<String> getBerichtsoorten() {
		return berichtsoorten;
	}

	public Date getBeginDate() {
		return periode.getBeginDatum();
	}

	public Date getEndDate() {
		return periode.getEindDatum();
	}

	public String getFiscalNumber() {
		return fiscalNumber;
	}

	public void setFiscalNumber(String fiscalNumber) {
		this.fiscalNumber = fiscalNumber;
	}

	public List<ProcesResultaat> getProcessen() {
		return processen;
	}

	public void setProcessen(List<ProcesResultaat> processen) {
		this.processen = processen;
	}

	public StatusResultaat getSelected() {
		return selected;
	}

	@NotifyChange("selected")
	public void setSelected(StatusResultaat selected) {
		this.selected = selected;
	}

	public boolean isTestEnvironment() {
		return isTestEnvironment;
	}
	
	public Date getSystemDate() {
		return new Date();
	}
	
	public Date getRealTimDate() {
		return DateHelper.getNTPDate();
	}

}
