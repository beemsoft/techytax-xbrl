/**
 * Copyright 2014 Hans Beemsterboer
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
import java.util.Date;
import java.util.List;

import org.techytax.cache.CostCache;
import org.techytax.dao.CostDao;
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

	protected ListModelList<CostType> costTypes;

	protected Cost selected;

	protected CostType selectedCostType;
	
	protected CostDao costDao = new CostDao();
	
	protected CostCache costCache = new CostCache();
	
	public ListModelList<Cost> getCosts() throws Exception {
		if (user == null) {
			Executions.sendRedirect("login.zul");
		} else if (costs == null) {
				Periode vatPeriod = DateHelper.getLatestVatPeriod(user.getVatPeriodType());
				List<Cost> vatCosts = costDao.getVatCostsWithPrivateMoney(DateHelper.getDate(vatPeriod.getBeginDatum()),
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
		if (user != null) {
			selected.setUserId(user.getId());
			selected.setCostTypeId(selectedCostType.getKostenSoortId());
			selected.setKostenSoortOmschrijving(selectedCostType.getOmschrijving());
			Cost cost = costDao.getKost(Long.toString(selected.getId()), user.getId());
			if (cost == null) {
				AuditLogger.log(AuditType.ENTER_COST, user);
				costDao.insertKost(selected);
			} else {
				AuditLogger.log(AuditType.UPDATE_COST, user);
				costDao.updateKost(selected);
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
		if (user != null) {
			AuditLogger.log(AuditType.DELETE_COST, user);
			selected.setUserId(user.getId());
			costDao.deleteCost(selected);
			getCosts().remove(selected);
			selected = null;
			costCache.invalidate();
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
