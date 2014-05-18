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

import static org.techytax.log.AuditType.DELETE_COST;
import static org.techytax.log.AuditType.ENTER_COST;
import static org.techytax.log.AuditType.UPDATE_COST;

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
import org.techytax.jpa.dao.GenericDao;
import org.techytax.log.AuditLogger;
import org.techytax.util.DateHelper;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModelList;

public class CostVM {

	protected User user = UserCredentialManager.getUser();

	protected ListModelList<Cost> costs;

	protected ListModelList<CostType> costTypes;

	protected Cost selected;

	protected CostType selectedCostType;

	protected CostDao costDao = new CostDao(Cost.class);
	
	protected CostTypeDao costTypeDao = new CostTypeDao(CostType.class);

	protected CostCache costCache = new CostCache();

	public ListModelList<Cost> getCosts() throws Exception {
		if (user == null) {
			Executions.sendRedirect("login.zul");
		} else if (costs == null) {
			Periode vatPeriod = DateHelper.getLatestVatPeriod(user.getVatPeriodType());
			List<Cost> vatCosts = costDao.getVatCostsWithPrivateMoney(vatPeriod.getBeginDatum(), vatPeriod.getEindDatum());
			costs = new ListModelList<>(vatCosts);
		}
		return costs;
	}

	public ListModelList<CostType> getCostTypes() throws Exception {
		if (costTypes == null) {
			List<CostType> vatCostTypes = costTypeDao.getCostTypesForVatCostsWithPrivateMoney();
			costTypes = new ListModelList<>(vatCostTypes);
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
		AuditLogger.log(ENTER_COST, user);
		Cost cost = new Cost();
		cost.setDate(new Date());
		getCosts().add(cost);
		selected = cost;
	}

	@NotifyChange({ "selected", "costs" })
	@Command
	public void saveCost() throws Exception {
		if (user != null) {
			selected.setUser(user);
			selected.setCostType(selectedCostType);
			Cost cost = (Cost) costDao.getEntity(selected, Long.valueOf(selected.getId()));
			selected.roundValues();
			if (cost == null) {
				AuditLogger.log(ENTER_COST, user);
				costDao.persistEntity(selected);
			} else {
				AuditLogger.log(UPDATE_COST, user);
				costDao.merge(selected);
			}
			costCache.invalidate();
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

	@NotifyChange({ "selected", "costs" })
	@Command
	public void deleteCost() throws Exception {
		if (user != null) {
			AuditLogger.log(DELETE_COST, user);
			selected.setUser(user);
			costDao.deleteEntity(selected);
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
		AmountHelper.applyLowVat(selected);
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
