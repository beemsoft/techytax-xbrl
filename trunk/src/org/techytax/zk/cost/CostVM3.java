package org.techytax.zk.cost;

import static org.techytax.log.AuditType.ENTER_COST;
import static org.techytax.log.AuditType.SPLIT_COST;
import static org.techytax.log.AuditType.UPDATE_COST;

import org.techytax.domain.Cost;
import org.techytax.log.AuditLogger;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;

public class CostVM3 extends CostVM2{

	String deleteMessage;
	
	public String getDeleteMessage(){
		return deleteMessage;
	}
	
	@Override
	@NotifyChange({"selected","costs","deleteMessage"})
	@Command
	public void deleteCost() throws Exception{
		super.deleteCost();
		deleteMessage = null;
	}
	
	@NotifyChange("deleteMessage")
	@Command
	public void confirmDelete(){
		deleteMessage = "Weet u zeker dat u wilt verwijderen: "+selected.getDescription()+" ?";
	}
	
	@NotifyChange("deleteMessage")
	@Command
	public void cancelDelete(){
		deleteMessage = null;
	}
	
	@GlobalCommand
	@NotifyChange({ "costs", "selected" })
	public void refreshvalues(@BindingParam("returncost") Cost cost, @BindingParam("splitcost") Cost splitCost) throws Exception {
		Cost originalCost = (Cost) costDao.getEntity(cost, Long.valueOf(cost.getId()));
		cost.setUser(user);
		if (originalCost == null) {
			AuditLogger.log(ENTER_COST, user);
			cost.roundValues();
			costDao.persistEntity(cost);
			this.selected = cost;
		} else if (!cost.equals(originalCost)) {
			AuditLogger.log(UPDATE_COST, user);
			cost.roundValues();
			costDao.merge(cost);
		}
		if (splitCost != null) {
			AuditLogger.log(SPLIT_COST, user);
			costDao.insertSplitCost(cost, splitCost);
		}
	}	
	
}
