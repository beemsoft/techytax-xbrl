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
package org.techytax.zk.vat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.techytax.dao.KostensoortDao;
import org.techytax.domain.Cost;
import org.techytax.domain.CostConstants;
import org.techytax.domain.Kostensoort;
import org.techytax.helper.AmountHelper;
import org.techytax.helper.DepreciationHelper;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

public class ModelWindowVM {
	@Wire("#resultWin")
	private Window win;
	private Cost cost;
	private Cost splitCost;
	private Cost originalCost;
	private ListModelList<Kostensoort> costTypes;
	private Kostensoort selectedCostType;
	private boolean investment;
	private boolean carDepreciation;
	private BigInteger deprecationRemainingValue;
	private int depreciationNofYears;
	private ListModelList<Cost> depreciationList;
	private List<Integer> depreciationYearsList;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("cost") Cost cost) {
		Selectors.wireComponents(view, this, false);
		this.cost = cost;
		if (cost.getAmount().compareTo(new BigDecimal(CostConstants.INVESTMENT_MINIMUM_AMOUNT)) >= 0
				&& cost.getCostTypeId() != CostConstants.INVESTMENT && cost.getCostTypeId() != CostConstants.INVESTMENT_OTHER_ACCOUNT) {
			investment = true;
		}
		depreciationYearsList = Arrays.asList(1, 2, 3, 4, 5);
	}

	public ListModelList<Kostensoort> getCostTypes() throws Exception {
		if (costTypes == null) {
			KostensoortDao kostensoortDao = new KostensoortDao();
			List<Kostensoort> vatCostTypes = kostensoortDao.getKostensoortLijst();
			costTypes = new ListModelList<Kostensoort>(vatCostTypes);
			for (Kostensoort costType : costTypes) {
				costType.setOmschrijving(Labels.getLabel(costType.getOmschrijving()));

				if (costType.getKostenSoortId() == cost.getCostTypeId()) {
					selectedCostType = costType;
				}
			}
		}
		return costTypes;
	}

	public Kostensoort getSelectedCostType() {
		return selectedCostType;
	}

	public void setSelectedCostType(Kostensoort selected) {
		this.selectedCostType = selected;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void save() {
		Map args = new HashMap();
		cost.setCostTypeId(selectedCostType.getKostenSoortId());
		args.put("returncost", this.cost);
		args.put("splitcost", this.splitCost);
		if (this.depreciationList != null) {
			args.put("depreciations", this.depreciationList.getInnerList());
		}
		BindUtils.postGlobalCommand("queueName", null, "refreshvalues", args);
		win.detach();
	}

	@Command
	public void cancel() {
		win.detach();
	}

	public Validator getCreationDateValidator() {
		return new AbstractValidator() {
			public void validate(ValidationContext ctx) {
				Date creation = (Date) ctx.getProperty().getValue();
				if (creation == null) {
					addInvalidMessage(ctx, "must not null");
				}
			}
		};
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

	@NotifyChange("cost")
	@Command
	public void highVat() throws Exception {
		AmountHelper.applyHighVat(cost);
	}

	@NotifyChange("cost")
	@Command
	public void lowVat() throws Exception {
		BigDecimal amount = cost.getAmount();
		if (amount != null) {
			BigDecimal bd = new BigDecimal(amount.doubleValue() / 1.06d);
			bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
			cost.setAmount(bd);
			cost.setVat(amount.subtract(bd));
		}
	}

	@NotifyChange({ "cost", "splitCost" })
	@Command
	public void split() throws Exception {
		originalCost = new Cost();
		originalCost.setDescription(cost.getDescription());
		originalCost.setAmount(cost.getAmount());
		originalCost.setVat(cost.getVat());
		Cost splitCost = new Cost();
		splitCost.setDate(cost.getDate());
		splitCost.setDescription(cost.getDescription());
		setSplitCost(splitCost);
	}

	@NotifyChange({ "cost", "splitCost" })
	@Command
	public void unSplit() throws Exception {
		splitCost = null;
		cost.setDescription(originalCost.getDescription());
		cost.setAmount(originalCost.getAmount());
		cost.setVat(originalCost.getVat());
	}

	@NotifyChange("investment")
	@Command
	public void checkInvestment() {
		if (cost.getAmount().compareTo(new BigDecimal(CostConstants.INVESTMENT_MINIMUM_AMOUNT)) >= 0) {
			investment = true;
		} else {
			investment = false;
		}
	}

	@NotifyChange("deprecationList")
	@Command
	public void deprecateCost() throws Exception {
		if (depreciationNofYears > 0 && deprecationRemainingValue != null && deprecationRemainingValue.compareTo(new BigInteger("0")) >= 0) {
			BigDecimal initialNetAmount = cost.getAmount();
			DepreciationHelper depreciationHelper = new DepreciationHelper();

			BigDecimal yearlyDepreciation = depreciationHelper.getYearlyDepreciation(depreciationNofYears, initialNetAmount,
					deprecationRemainingValue);

			List<Cost> deprecations = depreciationHelper.getDepreciations(cost, carDepreciation, depreciationNofYears, yearlyDepreciation);
			depreciationList = new ListModelList<Cost>(deprecations);
		}
	}

	@Command
	public void closeThis() {
		win.detach();
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public Cost getSplitCost() {
		return splitCost;
	}

	public void setSplitCost(Cost splitCost) {
		this.splitCost = splitCost;
	}

	public boolean isInvestment() {
		return investment;
	}

	public void setInvestment(boolean investment) {
		this.investment = investment;
	}

	public boolean isCarDepreciation() {
		return carDepreciation;
	}

	public void setCarDepreciation(boolean carDepreciation) {
		this.carDepreciation = carDepreciation;
	}

	public BigInteger getDeprecationRemainingValue() {
		return deprecationRemainingValue;
	}

	public void setDeprecationRemainingValue(BigInteger deprecationRemainingValue) {
		this.deprecationRemainingValue = deprecationRemainingValue;
	}

	public int getDepreciationNofYears() {
		return depreciationNofYears;
	}

	public void setDepreciationNofYears(int depreciationNofYears) {
		this.depreciationNofYears = depreciationNofYears;
	}

	public ListModelList<Cost> getDeprecationList() {
		return depreciationList;
	}

	public List<Integer> getDepreciationYearsList() {
		return depreciationYearsList;
	}

	public void setDepreciationYearsList(List<Integer> depreciationYearsList) {
		this.depreciationYearsList = depreciationYearsList;
	}
}
