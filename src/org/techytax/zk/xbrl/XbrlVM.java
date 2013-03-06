package org.techytax.zk.xbrl;

import java.util.Date;
import java.util.List;

import org.techytax.digipoort.DigipoortService;
import org.techytax.digipoort.DigipoortServiceImpl;
import org.techytax.domain.Periode;
import org.techytax.domain.User;
import org.techytax.domain.VatDeclarationData;
import org.techytax.util.DateHelper;
import org.techytax.ws.ArrayOfBerichtsoortResultaat;
import org.techytax.ws.GetBerichtsoortenResponse;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;

public class XbrlVM {

	protected User user = UserCredentialManager.getUser();
	private Periode periode = DateHelper.getLatestVatPeriod();
	private DigipoortService digipoortService = new DigipoortServiceImpl();
	private List<String> berichtsoorten;

	@Command
	@NotifyChange("berichtsoorten")
	public void berichtsoort() {
		try {
			VatDeclarationData vatDeclarationData = new VatDeclarationData();
			vatDeclarationData.setFiscalNumber(user.getFiscalNumber());
			GetBerichtsoortenResponse response = digipoortService.getBerichtsoorten(vatDeclarationData);
			ArrayOfBerichtsoortResultaat resultaat = response.getGetBerichtsoortenReturn();
			berichtsoorten = resultaat.getBerichtsoort();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
}
