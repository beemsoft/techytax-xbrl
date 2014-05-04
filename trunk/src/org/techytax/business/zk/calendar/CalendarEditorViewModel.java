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
package org.techytax.business.zk.calendar;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.cxf.common.util.StringUtils;
import org.techytax.business.jpa.entities.Project;
import org.techytax.domain.User;
import org.techytax.jpa.dao.GenericDao;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.ListModelList;

public class CalendarEditorViewModel {

	private BusinessCalendarEvent calendarEventData = new BusinessCalendarEvent();

	private User user = UserCredentialManager.getUser();

	private GenericDao<Project> projectDao = new GenericDao<>(Project.class);

	private Project selectedProject;

	private boolean visible = false;

	public BusinessCalendarEvent getCalendarEvent() {
		return calendarEventData;
	}

	public ListModelList<Project> getProjects() {
		try {
			List<Project> projects = projectDao.findAll(user);
			for (Project project : projects) {
				if (project.equals(calendarEventData.getProject())) {
					selectedProject = project;
				}
			}
			return new ListModelList<>(projects);
		} catch (IllegalAccessException e) {
			Executions.sendRedirect("login.zul");
		}
		return null;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Init
	public void init() {
		// subscribe a queue, listen to other controller
		QueueUtil.lookupQueue().subscribe(new QueueListener());
	}

	private void startEditing(BusinessCalendarEvent calendarEventData) {
		this.calendarEventData = calendarEventData;
		visible = true;

		// reload entire view-model data when going to edit
		BindUtils.postNotifyChange(null, null, this, "*");
	}

	public boolean isAllDay(Date beginDate, Date endDate) {
		int M_IN_DAY = 1000 * 60 * 60 * 24;
		boolean allDay = false;

		if (beginDate != null && endDate != null) {
			long between = endDate.getTime() - beginDate.getTime();
			allDay = between > M_IN_DAY;
		}
		return allDay;
	}

	public Validator getDateValidator() {
		return new AbstractValidator() {
			@Override
			public void validate(ValidationContext ctx) {
				Map<String, Property> formData = ctx.getProperties(ctx.getProperty().getValue());
				Date beginDate = (Date) formData.get("beginDate").getValue();
				Date endDate = (Date) formData.get("endDate").getValue();
				if (beginDate == null) {
					addInvalidMessage(ctx, "beginDate", "Begin date is empty");
				}
				if (endDate == null) {
					addInvalidMessage(ctx, "endDate", "End date is empty");
				}
				if (beginDate != null && endDate != null && beginDate.compareTo(endDate) >= 0) {
					addInvalidMessage(ctx, "endDate", "End date is before begin date");
				}
			}
		};
	}

	@Command
	@NotifyChange("visible")
	public void cancel() {
		QueueMessage message = new QueueMessage(QueueMessage.Type.CANCEL);
		QueueUtil.lookupQueue().publish(message);
		this.visible = false;
	}

	@Command
	@NotifyChange("visible")
	public void delete() {
		QueueMessage message = new QueueMessage(QueueMessage.Type.DELETE, calendarEventData);
		QueueUtil.lookupQueue().publish(message);
		this.visible = false;
	}

	@Command
	@NotifyChange("visible")
	public void ok() {
		if (selectedProject != null) {
			calendarEventData.setProject(selectedProject);
		}
		QueueMessage message = new QueueMessage(QueueMessage.Type.OK, calendarEventData);
		QueueUtil.lookupQueue().publish(message);
		this.visible = false;
	}

	private class QueueListener implements EventListener<QueueMessage> {
		@Override
		public void onEvent(QueueMessage message) throws Exception {
			if (QueueMessage.Type.EDIT.equals(message.getType())) {
				CalendarEditorViewModel.this.startEditing((BusinessCalendarEvent) message.getData());
			}
		}
	}

	public Project getSelectedProject() {
		return selectedProject;
	}

	@NotifyChange("calendarEvent")
	public void setSelectedProject(Project selectedProject) {
		this.selectedProject = selectedProject;
		if (StringUtils.isEmpty(calendarEventData.getTitle())) {
			calendarEventData.setContent(selectedProject.getCode());
		}
		calendarEventData.setBillable(true);
		calendarEventData.setUnitsOfWork(8);
	}
}