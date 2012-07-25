package org.techytax.zk.cost;

import java.util.List;

import org.techytax.dao.BoekDao;
import org.techytax.dao.KostensoortDao;
import org.techytax.domain.Cost;
import org.techytax.domain.Kostensoort;
import org.techytax.domain.Periode;
import org.techytax.domain.User;
import org.techytax.util.DateHelper;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.util.resource.Labels;
import org.zkoss.zul.ListModelList;

public class CostVM4 extends CostVM3 {
	
//	List<Cost> vatCosts = boekDao.getKostLijst(DateHelper.getDate(vatPeriod.getBeginDatum()), DateHelper.getDate(vatPeriod.getEindDatum()), "alles", Long.toString(user.getId()));

	public ListModelList<Cost> getCosts() throws Exception {
		if (costs == null) {
			BoekDao boekDao = new BoekDao();
			User user = UserCredentialManager.getUser();
			if (user != null) {
				Periode vatPeriod = DateHelper.getLatestVatPeriod();
				List<Cost> vatCosts = boekDao.getKostLijst(DateHelper.getDate(vatPeriod.getBeginDatum()), DateHelper.getDate(vatPeriod.getEindDatum()), "alles", Long.toString(user.getId()));
				for (Cost cost : vatCosts) {
					cost.setKostenSoortOmschrijving(Labels.getLabel(cost.getKostenSoortOmschrijving()));
				}				
				costs = new ListModelList<Cost>(vatCosts);
			}
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
}
