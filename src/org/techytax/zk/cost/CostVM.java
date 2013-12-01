/* OrderVM.java

	Purpose:
		
	Description:
		
	History:
		2011/10/31 Created by Dennis Chen

Copyright (C) 2011 Potix Corporation. All Rights Reserved.
 */
package org.techytax.zk.cost;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.techytax.dao.BoekDao;
import org.techytax.dao.CostTypeDao;
import org.techytax.domain.Cost;
import org.techytax.domain.CostType;
import org.techytax.domain.Periode;
import org.techytax.domain.User;
import org.techytax.helper.AmountHelper;
import org.techytax.log.AuditLogger;
import org.techytax.log.AuditType;
import org.techytax.util.DateHelper;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModelList;

public class CostVM {

	protected User user = UserCredentialManager.getUser();

	protected ListModelList<Cost> costs;

	ListModelList<CostType> costTypes;

	protected Cost selected;

	CostType selectedCostType;
	
	public ListModelList<Cost> getCosts() throws Exception {
		if (user == null) {
			Executions.sendRedirect("login.zul");
		} else if (costs == null) {
			BoekDao boekDao = new BoekDao();
				Periode vatPeriod = DateHelper.getLatestVatPeriod();
				List<Cost> vatCosts = boekDao.getVatCostsWithPrivateMoney(DateHelper.getDate(vatPeriod.getBeginDatum()),
						DateHelper.getDate(vatPeriod.getEindDatum()), Long.toString(user.getId()));
				for (Cost cost : vatCosts) {
					cost.setKostenSoortOmschrijving(Labels.getLabel(cost.getKostenSoortOmschrijving()));
				}
				costs = new ListModelList<Cost>(vatCosts);
		}
		return costs;
	}

	public ListModelList<CostType> getCostTypes() throws Exception {
		if (costTypes == null) {
			CostTypeDao kostensoortDao = new CostTypeDao();
			List<CostType> vatCostTypes = kostensoortDao.getCostTypesForVatCostsWithPrivateMoney();
			costTypes = new ListModelList<CostType>(vatCostTypes);
			for (CostType costType : costTypes) {
				costType.setOmschrijving(Labels.getLabel(costType.getOmschrijving()));
			}
			selectedCostType = costTypes.get(0);
		}
		return costTypes;
	}

	public Cost getSelected() {
		return selected;
	}

	@NotifyChange({ "selected", "costs" })
	@Command
	public void newCost() throws Exception {
		AuditLogger.log(AuditType.ENTER_COST, user);
		Cost cost = new Cost();
		cost.setDate(new Date());
		getCosts().add(cost);
		selected = cost;// select the new one
	}

	@NotifyChange("selected")
	@Command
	public void saveCost() throws Exception {
		BoekDao boekDao = new BoekDao();
		if (user != null) {
			selected.setUserId(user.getId());
			selected.setCostTypeId(selectedCostType.getKostenSoortId());
			selected.setKostenSoortOmschrijving(selectedCostType.getOmschrijving());
			Cost cost = boekDao.getKost(Long.toString(selected.getId()), user.getId());
			if (cost == null) {
				AuditLogger.log(AuditType.ENTER_COST, user);
				boekDao.insertKost(selected);
			} else {
				AuditLogger.log(AuditType.UPDATE_COST, user);
				boekDao.updateKost(selected);
			}
		}
	}

	@NotifyChange({ "selected" })
	public void setSelected(Cost selected) {
		this.selected = selected;
	}

	public CostType getSelectedCostType() {
		return selectedCostType;
	}

	public void setSelectedCostType(CostType selected) {
		this.selectedCostType = selected;
	}

	// action command

	@NotifyChange({ "selected", "costs" })
	@Command
	public void deleteCost() throws Exception {
		BoekDao boekDao = new BoekDao();
		if (user != null) {
			AuditLogger.log(AuditType.DELETE_COST, user);
			selected.setUserId(user.getId());
			boekDao.deleteCost(selected);
			getCosts().remove(selected);
			selected = null;
		}
	}

	@NotifyChange("selected")
	@Command
	public void highVat() throws Exception {
		AmountHelper.applyHighVat(selected);
	}

	@NotifyChange("selected")
	@Command
	public void lowVat() throws Exception {
		BigDecimal amount = selected.getAmount();
		if (amount != null) {
			BigDecimal bd = new BigDecimal(amount.doubleValue() / 1.06d);
			bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
			selected.setAmount(bd);
			selected.setVat(amount.subtract(bd));
		}
	}

	public Validator getPriceValidator() {
		return new AbstractValidator() {
			public void validate(ValidationContext ctx) {
				Double price = (Double) ctx.getProperty().getValue();
				if (price == null || price < 0) {
					addInvalidMessage(ctx, "must be equal to or larger than 0");
				}
			}
		};
	}

}
