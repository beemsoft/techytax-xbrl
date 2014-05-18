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

import org.techytax.domain.Customer;
import org.techytax.domain.Project;
import org.techytax.domain.User;
import org.techytax.jpa.dao.GenericDao;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

public class EditProjectVM {
	@Wire("#resultWin")
	private Window win;

	private Project project;

	private User user = UserCredentialManager.getUser();

	private Customer selectedCustomer;

	private ListModelList<Customer> customers;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("project") Project project) {
		Selectors.wireComponents(view, this, false);
		this.project = project;
	}

	public ListModelList<Customer> getCustomers() throws IllegalAccessException {
		try {
			GenericDao<Customer> customerDao = new GenericDao<>(Customer.class);
			customers = new ListModelList<>(customerDao.findAll(user));
			for (Customer customer: customers) {
				if (customer.getId() == project.getCustomer().getId()) {
					selectedCustomer = customer;
				}
			}			
		} catch (IllegalAccessException e) {
			Executions.sendRedirect("login.zul");
		}
		return customers;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void save() {
		Map args = new HashMap();
		project.setCustomer(selectedCustomer);
		args.put("project", this.project);
		BindUtils.postGlobalCommand("queueName", null, "refreshvalues", args);
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}

}
