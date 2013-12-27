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
package org.techytax.business.zk;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.techytax.business.jpa.entities.Customer;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

public class SelectCustomerVM {
	@Wire("#selectWin")
	private Window win;

	private Customer selectedCustomer;
	
	private ListModelList<Customer> customersModel;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("customers") List<Customer> customers) {
		Selectors.wireComponents(view, this, false);
		this.customersModel = new ListModelList<Customer>(customers);
	}
	
	@Command
	public void onDoubleClicked() {
		select();
	}	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void select() {
		Map args = new HashMap();
		args.put("selectedCustomer", this.selectedCustomer);
		BindUtils.postGlobalCommand("queueName", null, "updateCustomerWithInfo", args);
		win.detach();
	}

	@Command
	public void cancel() {
		win.detach();
	}

	@Command
	public void closeThis() {
		win.detach();
	}

	public ListModelList<Customer> getCustomers() {
		return customersModel;
	}

	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}

}
