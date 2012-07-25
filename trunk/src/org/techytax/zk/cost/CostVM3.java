package org.techytax.zk.cost;

import org.zkoss.bind.annotation.Command;
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
		deleteMessage = "Do you want to delete "+selected.getDescription()+" ?";
	}
	
	@NotifyChange("deleteMessage")
	@Command
	public void cancelDelete(){
		deleteMessage = null;
	}
	
}
