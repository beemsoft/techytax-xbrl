/* OrderVM.java

	Purpose:
		
	Description:
		
	History:
		2011/10/31 Created by Dennis Chen

Copyright (C) 2011 Potix Corporation. All Rights Reserved.
 */
package org.techytax.zk.cost;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;


/**
 * @author dennis
 * 
 */
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
