package org.techytax.zk.xbrl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.List;

import org.techytax.digipoort.DigipoortService;
import org.techytax.digipoort.DigipoortServiceImpl;
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
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.Messagebox;

public class XbrlVM implements Serializable {

	private static final long serialVersionUID = -4600263940170016764L;
	
	protected User user = UserCredentialManager.getUser();
	private Periode periode = DateHelper.getLatestVatPeriodTillToday();
	private DigipoortService digipoortService = new DigipoortServiceImpl();
	private List<String> berichtsoorten;
	private List<ProcesResultaat> processen;
	private List<StatusResultaat> nieuweStatussen;
	private List<StatusResultaat> nieuweStatussenProces;
	private List<StatusResultaat> statussenProces;
	private boolean isTestEnvironment = false;
	
	public XbrlVM() throws IOException {
		String digipoort = PropsFactory.getProperty("digipoort");
		if (digipoort.equals("prod")) {
			isTestEnvironment = false;
		} else {
			isTestEnvironment = true;
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
	public void berichtsoort() {
		try {
			VatDeclarationData vatDeclarationData = new VatDeclarationData();
			setFiscalNumber(vatDeclarationData);
			GetBerichtsoortenResponse response = digipoortService.getBerichtsoorten(vatDeclarationData);
			ArrayOfBerichtsoortResultaat resultaat = response.getGetBerichtsoortenReturn();
			berichtsoorten = resultaat.getBerichtsoort();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setFiscalNumber(VatDeclarationData vatDeclarationData) {
		if (!isTestEnvironment) {
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
			e.printStackTrace();
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
		}
	}

	public List<StatusResultaat> getStatussenProces() {
		if (selectedProces != null) {
			try {
				VatDeclarationData vatDeclarationData = new VatDeclarationData();
				setFiscalNumber(vatDeclarationData);
				setDates(vatDeclarationData);
				GetStatussenProcesResponse response = digipoortService.getStatussenProces(vatDeclarationData, selectedProces.getKenmerk());
				ArrayOfStatusResultaat resultaat = response.getGetStatussenProcesReturn();
				statussenProces = resultaat.getStatusResultaat();
			} catch (Exception e) {
				e.printStackTrace();
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
}
