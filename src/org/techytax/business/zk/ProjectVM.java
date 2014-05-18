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

import org.techytax.domain.Project;
import org.techytax.domain.User;
import org.techytax.domain.UserEntity;
import org.techytax.jpa.dao.GenericDao;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

public class ProjectVM {

	private User user = UserCredentialManager.getUser();
	private GenericDao<Project> projectDao = new GenericDao<>(Project.class);

	protected ListModelList<Project> projects;

	protected Project selected;

	private String deleteMessage;

	public String getDeleteMessage() {
		return deleteMessage;
	}

	@NotifyChange({ "selected", "projects", "deleteMessage" })
	@Command
	public void deleteProject() throws Exception {
		deleteMessage = null;
		projectDao.deleteEntity(selected);
		selected = null;
	}

	@NotifyChange("deleteMessage")
	@Command
	public void confirmDelete() {
		deleteMessage = "Do you want to delete " + selected.getCode() + " ?";
	}

	@NotifyChange("deleteMessage")
	@Command
	public void cancelDelete() {
		deleteMessage = null;
	}

	public ListModelList<Project> getProjects() throws Exception {
		try {
			projects = new ListModelList<>(projectDao.findAll(user));
		} catch (IllegalAccessException e) {
			Executions.sendRedirect("login.zul");
		}
		return projects;
	}

	@Command
	public void newProject() {
		Project newProject = new Project();
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("project", newProject);
		String template = "edit-project.zul";
		Window window = (Window) Executions.createComponents(template, null, arguments);
		window.doModal();
	}

	@Command
	public void onDoubleClicked() {
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("project", selected);
		String template = "edit-project.zul";
		Window window = (Window) Executions.createComponents(template, null, arguments);
		window.doModal();
	}

	@GlobalCommand
	@NotifyChange({ "projects", "selected" })
	public void refreshvalues(@BindingParam("project") Project project) throws Exception {
		project.setUser(new UserEntity(user));
		projectDao.merge(project);
	}

	public Project getSelected() {
		return selected;
	}

	public void setSelected(Project selected) {
		this.selected = selected;
	}

	public void setProjects(ListModelList<Project> projects) {
		this.projects = projects;
	}
}
