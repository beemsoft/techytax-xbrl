/* OrderVM.java

	Purpose:
		
	Description:
		
	History:
		2011/10/31 Created by Dennis Chen

Copyright (C) 2011 Potix Corporation. All Rights Reserved.
 */
package org.techytax.zk.cost;

import org.techytax.dao.BoekDao;
import org.techytax.domain.Cost;
import org.techytax.domain.User;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zul.ListModelList;

public class CostVM {

	ListModelList<Cost> costs;
	
	//the selected order
	Cost selected;

	public ListModelList<Cost> getCosts() {
		if (costs == null) {
			//init the list
			costs = new ListModelList<Cost>();
		}
		return costs;
	}

	public Cost getSelected() {
		return selected;
	}

	public void setSelected(Cost selected) {
		this.selected = selected;
	}

	//action command
	
	@NotifyChange({"selected","costs"})
	@Command
	public void newCost(){
		Cost cost = new Cost();
		getCosts().add(cost);
		selected = cost;//select the new one
	}
	
	@NotifyChange("selected")
	@Command
	public void saveCost() throws Exception{
		BoekDao boekDao = new BoekDao();
		User user = UserCredentialManager.getUser();
		if (user != null) {
			selected.setUserId(user.getId());
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
		User user = UserCredentialManager.getUser();
		if (user != null) {
			selected.setUserId(user.getId());
			boekDao.deleteCost(selected);
			getCosts().remove(selected);
			selected = null;			
		}
	}

	//validators for prompt
	public Validator getPriceValidator(){
		return new AbstractValidator(){
			public void validate(ValidationContext ctx) {
				Double price = (Double)ctx.getProperty().getValue();
				if(price==null || price<=0){
					addInvalidMessage(ctx, "must large than 0");
				}
			}
		};
	}
	
	public Validator getQuantityValidator(){
		return new AbstractValidator(){
			public void validate(ValidationContext ctx) {
				Integer quantity = (Integer)ctx.getProperty().getValue();
				if(quantity==null || quantity<=0){
					addInvalidMessage(ctx, "must large than 0");
				}
			}
		};
	}
}
