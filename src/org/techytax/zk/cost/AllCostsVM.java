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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.techytax.dao.BoekDao;
import org.techytax.dao.KostensoortDao;
import org.techytax.domain.Cost;
import org.techytax.domain.CostConstants;
import org.techytax.domain.Kostensoort;
import org.techytax.domain.Periode;
import org.techytax.helper.DutchAuditFileHelper;
import org.techytax.log.AuditLogger;
import org.techytax.log.AuditType;
import org.techytax.mail.MailHelper;
import org.techytax.util.DateHelper;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

public class AllCostsVM extends CostVM3 {

	private Periode periode = DateHelper.getLatestVatPeriod();
	private BoekDao boekDao = new BoekDao();
	private KostensoortDao kostensoortDao = new KostensoortDao();
	private List<Cost> unhandledCosts = new ArrayList<Cost>();
	private boolean showUnhandledInvestments = false;

	@Command
	public void audit() {
		AuditLogger.log(AuditType.SEND_AUDIT_FILE, user);
		try {
			List<Cost> allCosts = boekDao.getKostLijst(DateHelper.getDate(periode.getBeginDatum()), DateHelper.getDate(periode.getEindDatum()),
					"audit", Long.toString(user.getId()));
			String message = DutchAuditFileHelper.createAuditFile(allCosts, user);
			MailHelper.sendAuditReport(message, user.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ListModelList<Cost> getCosts() throws Exception {
		if (user != null) {
			List<Cost> allCosts = boekDao.getKostLijst(DateHelper.getDate(periode.getBeginDatum()), DateHelper.getDate(periode.getEindDatum()),
					"alles", Long.toString(user.getId()));
			unhandledCosts = new ArrayList<Cost>();
			for (Cost cost : allCosts) {
				cost.setKostenSoortOmschrijving(Labels.getLabel(cost.getKostenSoortOmschrijving()));
				if (cost.getAmount().compareTo(new BigDecimal(CostConstants.INVESTMENT_MINIMUM_AMOUNT)) == 1) {
					if (cost.getCostTypeId() != CostConstants.INVESTMENT && cost.getCostTypeId() != CostConstants.INVESTMENT_OTHER_ACCOUNT) {
						if (cost.getCostTypeId() == CostConstants.EXPENSE_CURRENT_ACCOUNT
								|| cost.getCostTypeId() == CostConstants.EXPENSE_OTHER_ACCOUNT) {
							unhandledCosts.add(cost);
						}
					}
				}
			}
			if (!showUnhandledInvestments) {
				costs = new ListModelList<Cost>(allCosts);
			} else {
				costs = new ListModelList<Cost>(unhandledCosts);
			}
		}
		return costs;
	}

	@NotifyChange("costs")
	@Command
	public void showUnhandledInvestments() throws Exception {
		showUnhandledInvestments = true;
	}

	public boolean isListWithUnhandledInvestments() throws Exception {
		if (unhandledCosts != null && unhandledCosts.size() > 0) {
			return true;
		}
		return false;
	}

	public ListModelList<Cost> getBusinessCosts() throws Exception {
		if (user != null && costs == null) {
			List<Cost> vatCosts = boekDao.getKostLijst(DateHelper.getDate(periode.getBeginDatum()), DateHelper.getDate(periode.getEindDatum()),
					"rekeningBalans", Long.toString(user.getId()));
			for (Cost cost : vatCosts) {
				cost.setKostenSoortOmschrijving(Labels.getLabel(cost.getKostenSoortOmschrijving()));
			}
			costs = new ListModelList<Cost>(vatCosts);
		}
		return costs;
	}

	public ListModelList<Kostensoort> getCostTypes() throws Exception {
		if (costTypes == null) {
			List<Kostensoort> vatCostTypes = kostensoortDao.getKostensoortLijst();
			costTypes = new ListModelList<Kostensoort>(vatCostTypes);
			for (Kostensoort costType : costTypes) {
				costType.setOmschrijving(Labels.getLabel(costType.getOmschrijving()));
			}
			selectedCostType = costTypes.get(0);
		}
		return costTypes;
	}

	@NotifyChange({ "costs", "listWithUnhandledInvestments" })
	public void setBeginDate(Date beginDate) {
		periode.setBeginDatum(beginDate);
	}

	public Date getBeginDate() {
		return periode.getBeginDatum();
	}

	@NotifyChange({ "costs", "listWithUnhandledInvestments" })
	public void setEndDate(Date endDate) {
		periode.setEindDatum(endDate);
	}

	public Date getEndDate() {
		return periode.getEindDatum();
	}

	@Command
	public void newCost() {
		Cost newCost = new Cost();
		newCost.setDate(new Date());
		Map<String, Object> arguments = new HashMap<String, Object>();
		arguments.put("cost", newCost);
		String template = "edit-cost.zul";
		Window window = (Window) Executions.createComponents(template, null, arguments);
		window.doModal();
	}

	@Command
	public void onDoubleClicked() {
		Map<String, Object> arguments = new HashMap<String, Object>();
		arguments.put("cost", selected);
		String template = "edit-cost.zul";
		Window window = (Window) Executions.createComponents(template, null, arguments);
		window.doModal();
	}

	@GlobalCommand
	@NotifyChange({ "costs", "selected" })
	public void refreshvalues(@BindingParam("returncost") Cost cost, @BindingParam("splitcost") Cost splitCost,
			@BindingParam("depreciations") List<Cost> depreciations) throws Exception {
		BoekDao boekDao = new BoekDao();

		if (depreciations != null && depreciations.size() > 0) {
			AuditLogger.log(AuditType.DEPRECIATE_COST, user);
			for (Cost depreciation : depreciations) {
				depreciation.setUserId(user.getId().longValue());
				boekDao.insertKost(depreciation);
			}
			if (cost.getCostTypeId() == CostConstants.EXPENSE_CURRENT_ACCOUNT) {
				cost.setCostTypeId(CostConstants.INVESTMENT);
			} else if (cost.getCostTypeId() == CostConstants.EXPENSE_OTHER_ACCOUNT) {
				cost.setCostTypeId(CostConstants.INVESTMENT_OTHER_ACCOUNT);
			} 
		}

		Cost originalCost = boekDao.getKost(Long.toString(cost.getId()), user.getId().longValue());
		cost.setUserId(user.getId().longValue());
		if (originalCost == null) {
			AuditLogger.log(AuditType.ENTER_COST, user);
			boekDao.insertKost(cost);
			this.selected = cost;
		} else if (!cost.equals(originalCost)) {
			AuditLogger.log(AuditType.UPDATE_COST, user);
			boekDao.updateKost(cost);
		}
		if (splitCost != null) {
			AuditLogger.log(AuditType.SPLIT_COST, user);
			splitCost.setUserId(user.getId().longValue());
			boekDao.insertSplitCost(cost, splitCost);
		}
	}
}
