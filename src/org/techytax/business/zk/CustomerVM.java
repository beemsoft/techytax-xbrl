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
import java.util.Map;

import org.techytax.business.jpa.entities.Customer;
import org.techytax.domain.User;
import org.techytax.domain.UserEntity;
import org.techytax.helper.DutchAuditFileHelper;
import org.techytax.jpa.dao.GenericDao;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

public class CustomerVM {

	private User user = UserCredentialManager.getUser();
	private GenericDao<Customer> customerDao = new GenericDao<>(Customer.class);

	protected ListModelList<Customer> customers;

	protected Customer selected;

	private String deleteMessage;

	public String getDeleteMessage() {
		return deleteMessage;
	}

	@NotifyChange({ "selected", "customers", "deleteMessage" })
	@Command
	public void deleteCustomer() throws Exception {
		deleteMessage = null;
		customerDao.deleteEntity(selected);
		selected = null;
	}

	@NotifyChange("deleteMessage")
	@Command
	public void confirmDelete() {
		deleteMessage = "Do you want to delete " + selected.getDescription() + " ?";
	}

	@NotifyChange("deleteMessage")
	@Command
	public void cancelDelete() {
		deleteMessage = null;
	}

	public ListModelList<Customer> getCustomers() throws Exception {
		try {
			customers = new ListModelList<>(customerDao.findAll(user));
		} catch (IllegalAccessException e) {
			Executions.sendRedirect("login.zul");
		}
		return customers;
	}

	@Command
	public void newCustomer() {
		Customer newCustomer = new Customer();
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("customer", newCustomer);
		String template = "edit-customer.zul";
		Window window = (Window) Executions.createComponents(template, null, arguments);
		window.doModal();
	}

	@Command
	public void onDoubleClicked() {
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("customer", selected);
		String template = "edit-customer.zul";
		Window window = (Window) Executions.createComponents(template, null, arguments);
		window.doModal();
	}

	@GlobalCommand
	@NotifyChange({ "customers", "selected" })
	public void refreshvalues(@BindingParam("customer") Customer customer) throws Exception {
		customer.setUser(new UserEntity(user));
		customerDao.merge(customer);
	}
	
	@Command
	public void audit() {
		DutchAuditFileHelper.sendAuditFile(user, null);
	}	

	public Customer getSelected() {
		return selected;
	}

	public void setSelected(Customer selected) {
		this.selected = selected;
	}

	public void setCustomers(ListModelList<Customer> customers) {
		this.customers = customers;
	}
}
