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

	//message for confirming the deletion.
	String deleteMessage;
	
	public String getDeleteMessage(){
		return deleteMessage;
	}
	
	@Override
	@NotifyChange({"selected","costs","deleteMessage"})
	@Command
	public void deleteCost(){
		super.deleteCost();
		deleteMessage = null;
	}
	
	@NotifyChange("deleteMessage")
	@Command
	public void confirmDelete(){
		//set the message to show to user
		deleteMessage = "Do you want to delete "+selected.getId()+" ?";
	}
	
	@NotifyChange("deleteMessage")
	@Command
	public void cancelDelete(){
		//clear the message
		deleteMessage = null;
	}
	
}
