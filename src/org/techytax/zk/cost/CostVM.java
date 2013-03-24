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
import org.techytax.dao.KostensoortDao;
import org.techytax.domain.Cost;
import org.techytax.domain.Kostensoort;
import org.techytax.domain.Periode;
import org.techytax.domain.User;
import org.techytax.helper.AmountHelper;
import org.techytax.util.DateHelper;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.util.resource.Labels;
import org.zkoss.zul.ListModelList;

public class CostVM {

	protected User user = UserCredentialManager.getUser();
	
	protected ListModelList<Cost> costs;
	
	ListModelList<Kostensoort> costTypes;
	
	protected Cost selected;
	
	Kostensoort selectedCostType;

	public ListModelList<Cost> getCosts() throws Exception {
		if (costs == null) {
			BoekDao boekDao = new BoekDao();
			if (user != null) {
				Periode vatPeriod = DateHelper.getLatestVatPeriod();
				List<Cost> vatCosts = boekDao.getVatCostsWithPrivateMoney(DateHelper.getDate(vatPeriod.getBeginDatum()), DateHelper.getDate(vatPeriod.getEindDatum()), Long.toString(user.getId()));
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
			List<Kostensoort> vatCostTypes = kostensoortDao.getCostTypesForVatCostsWithPrivateMoney();
			costTypes = new ListModelList<Kostensoort>(vatCostTypes);
			for (Kostensoort costType : costTypes) {
				costType.setOmschrijving(Labels.getLabel(costType.getOmschrijving()));
			}
			selectedCostType = costTypes.get(0); 			
		}
		return costTypes;
	}	

	public Cost getSelected() {
		return selected;
	}
	
	private Kostensoort select(long costTypeId) {
		for (Kostensoort costType: costTypes) {
			if (costType.getKostenSoortId() == costTypeId) {
				return costType;
			}
		}
		return null;
	}

	@NotifyChange({"selected", "selectedCostType"})
	public void setSelected(Cost selected) {
		this.selected = selected;
		setSelectedCostType(select(selected.getCostTypeId()));
	}
	
	public Kostensoort getSelectedCostType() {
		return selectedCostType;
	}
	
	public void setSelectedCostType(Kostensoort selected) {
		this.selectedCostType = selected;
	}	

	//action command
	
	@NotifyChange({"selected","costs"})
	@Command
	public void newCost() throws Exception{
		Cost cost = new Cost();
		cost.setDate(new Date());
		getCosts().add(cost);
		selected = cost;//select the new one
	}
	
	@NotifyChange("selected")
	@Command
	public void saveCost() throws Exception{
		BoekDao boekDao = new BoekDao();
		if (user != null) {
			selected.setUserId(user.getId());
			selected.setCostTypeId(selectedCostType.getKostenSoortId());
			selected.setKostenSoortOmschrijving(selectedCostType.getOmschrijving());
			Cost cost = boekDao.getKost(Long.toString(selected.getId()), user.getId());
			if (cost == null) {
				boekDao.insertKost(selected);
			} else {
				boekDao.updateKost(selected);
			}
		}
	}
	
	
	@NotifyChange({"selected","costs"})
	@Command
	public void deleteCost() throws Exception{
		BoekDao boekDao = new BoekDao();
		if (user != null) {
			selected.setUserId(user.getId());
			boekDao.deleteCost(selected);
			getCosts().remove(selected);
			selected = null;			
		}
	}
	
	@NotifyChange("selected")
	@Command
	public void highVat() throws Exception{
		AmountHelper.applyHighVat(selected);
	}

	@NotifyChange("selected")
	@Command
	public void lowVat() throws Exception{
		BigDecimal amount = selected.getAmount();
		if (amount != null) {
			BigDecimal bd = new BigDecimal(amount.doubleValue()/1.06d);
			bd = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
			selected.setAmount(bd);
			selected.setVat(amount.subtract(bd));			
		}
	}	

	public Validator getPriceValidator(){
		return new AbstractValidator(){
			public void validate(ValidationContext ctx) {
				Double price = (Double)ctx.getProperty().getValue();
				if(price==null || price<0){
					addInvalidMessage(ctx, "must be equal to or larger than 0");
				}
			}
		};
	}
	
}
