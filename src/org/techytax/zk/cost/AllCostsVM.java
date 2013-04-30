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
package org.techytax.zk.cost;

import java.util.Date;
import java.util.List;

import org.techytax.dao.BoekDao;
import org.techytax.dao.KostensoortDao;
import org.techytax.domain.Cost;
import org.techytax.domain.Kostensoort;
import org.techytax.domain.Periode;
import org.techytax.helper.DutchAuditFileHelper;
import org.techytax.log.AuditLogger;
import org.techytax.log.AuditType;
import org.techytax.mail.MailHelper;
import org.techytax.util.DateHelper;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zul.ListModelList;

public class AllCostsVM extends CostVM3 {

	private Periode periode = DateHelper.getLatestVatPeriod();
	private BoekDao boekDao = new BoekDao();

	@Command
	public void audit() {
		AuditLogger.log(AuditType.SEND_AUDIT_FILE, user);		
		try {
			List<Cost> allCosts = boekDao.getKostLijst(DateHelper.getDate(periode.getBeginDatum()), DateHelper.getDate(periode.getEindDatum()), "audit", Long.toString(user.getId()));
			String message = DutchAuditFileHelper.createAuditFile(allCosts, user);
			MailHelper.sendAuditReport(message, user.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ListModelList<Cost> getCosts() throws Exception {
		if (user != null) {
			List<Cost> vatCosts = boekDao.getKostLijst(DateHelper.getDate(periode.getBeginDatum()), DateHelper.getDate(periode.getEindDatum()), "alles", Long.toString(user.getId()));
			for (Cost cost : vatCosts) {
				cost.setKostenSoortOmschrijving(Labels.getLabel(cost.getKostenSoortOmschrijving()));
			}
			costs = new ListModelList<Cost>(vatCosts);
		}
		return costs;
	}

	public ListModelList<Cost> getBusinessCosts() throws Exception {
		if (user != null && costs == null) {
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
