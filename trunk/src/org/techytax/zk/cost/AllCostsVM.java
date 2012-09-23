package org.techytax.zk.cost;

import java.util.Date;
import java.util.List;

import org.techytax.dao.BoekDao;
import org.techytax.dao.KostensoortDao;
import org.techytax.domain.Cost;
import org.techytax.domain.Kostensoort;
import org.techytax.domain.Periode;
import org.techytax.domain.User;
import org.techytax.util.DateHelper;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zul.ListModelList;

public class AllCostsVM extends CostVM3 {
	
	private Periode periode = DateHelper.getLatestVatPeriod();
	
	@NotifyChange("periode")	
	public ListModelList<Cost> getCosts() throws Exception {
		BoekDao boekDao = new BoekDao();
		User user = UserCredentialManager.getUser();
		if (user != null) {
			List<Cost> vatCosts = boekDao.getKostLijst(DateHelper.getDate(periode.getBeginDatum()), DateHelper.getDate(periode.getEindDatum()), "alles", Long.toString(user.getId()));
			for (Cost cost : vatCosts) {
				cost.setKostenSoortOmschrijving(Labels.getLabel(cost.getKostenSoortOmschrijving()));
			}				
			costs = new ListModelList<Cost>(vatCosts);
		}
		return costs;
	}
	
	@NotifyChange("periode")	
	public ListModelList<Cost> getBusinessCosts() throws Exception {
		BoekDao boekDao = new BoekDao();
		User user = UserCredentialManager.getUser();
		if (user != null) {
			List<Cost> vatCosts = boekDao.getKostLijst(DateHelper.getDate(periode.getBeginDatum()), DateHelper.getDate(periode.getEindDatum()), "rekeningBalans", Long.toString(user.getId()));
			for (Cost cost : vatCosts) {
				cost.setKostenSoortOmschrijving(Labels.getLabel(cost.getKostenSoortOmschrijving()));
			}				
			costs = new ListModelList<Cost>(vatCosts);
		}
		return costs;
	}	
	
	public ListModelList<Kostensoort> getCostTypes() throws Exception {
		if (costTypes == null) {
			KostensoortDao kostensoortDao = new KostensoortDao();
			List<Kostensoort> vatCostTypes = kostensoortDao.getKostensoortLijst();
			costTypes = new ListModelList<Kostensoort>(vatCostTypes);
			for (Kostensoort costType : costTypes) {
				costType.setOmschrijving(Labels.getLabel(costType.getOmschrijving()));
			}
			selectedCostType = costTypes.get(0); 			
		}
		return costTypes;
	}

	@NotifyChange("costs")	
	public void setBeginDate(Date beginDate) {
		periode.setBeginDatum(beginDate);
	}
	
	public Date getBeginDate() {
		return periode.getBeginDatum();
	}
	
	@NotifyChange("costs")	
	public void setEndDate(Date endDate) {
		periode.setEindDatum(endDate);
	}
	
	public Date getEndDate() {
		return periode.getEindDatum();
	}	
}
