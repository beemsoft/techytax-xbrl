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
 
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import org.techytax.business.jpa.entities.Project;
import org.techytax.dao.BusinessCalendarDao;
import org.techytax.domain.User;
import org.techytax.jpa.dao.GenericDao;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.calendar.Calendars;
import org.zkoss.calendar.event.CalendarsEvent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkmax.ui.select.annotation.Subscribe;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
 
 
public class CalendarController extends SelectorComposer<Component> {
 
    private static final long serialVersionUID = 1L;
    
    @Wire
    private Window win;
    
    @Wire
    private Calendars calendars;
    @Wire
    private Textbox filter;
     
    private BusinessCalendarModel calendarModel;
     
    //the in editing calendar ui event
    private CalendarsEvent calendarsEvent = null;
    
	private User user = UserCredentialManager.getUser();
	
	private GenericDao<Project> projectDao = new GenericDao<Project>(Project.class, user);
	
	private BusinessCalendarDao businessCalendarDao = new BusinessCalendarDao();
	
	private ListModel<Project> projectsModel = new ListModelList<Project>(projectDao.findAll());
	
	@Wire
    private Listbox projectListbox;
	
	private Project selectedProject;
	
	@Wire
	private Button invoiceButton;
	
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
    
    	List<BusinessCalendarEvent> calendarEvents = businessCalendarDao.getEvents(user.getId().toString());
        calendarModel = new BusinessCalendarModel(calendarEvents);
        calendars.setModel(this.calendarModel);
        invoiceButton.setDisabled(true);
        invoiceButton.setTooltiptext("Selecteer 1 project om een factuur te maken");
    }
     
    //control the calendar position
    @Listen("onClick = #today")
    public void gotoToday(){
        TimeZone timeZone = calendars.getDefaultTimeZone();
        calendars.setCurrentDate(Calendar.getInstance(timeZone).getTime());
    }
    @Listen("onClick = #next")
    public void gotoNext(){
        calendars.nextPage();
    }
    @Listen("onClick = #prev")
    public void gotoPrev(){
        calendars.previousPage();
    }
     
    //control page display
    @Listen("onClick = #pageDay")
    public void changeToDay(){
        calendars.setMold("default");
        calendars.setDays(1);
    }
    @Listen("onClick = #pageWeek")
    public void changeToWeek(){
        calendars.setMold("default");
        calendars.setDays(7);
    }
    
    @Listen("onClick = #pageMonth")
    public void changeToMonth(){
        calendars.setMold("month");
        if (selectedProject != null) {
        	invoiceButton.setDisabled(false);
        }
    }
     
    //control the filter
    @Listen("onClick = #applyFilter")
    public void applyFilter(){
        calendarModel.setFilterText(filter.getValue());
        calendars.setModel(calendarModel);
    }
    
    @Listen("onClick = #resetFilter")
    public void resetFilter(){
        filter.setText("");
        calendarModel.setFilterText("");
        calendarModel.setFilterProject(null);
        selectedProject = null;
        projectListbox.setSelectedItem(null);
        calendars.setModel(calendarModel);
        invoiceButton.setDisabled(true);
    }
 
    //listen to the calendar-create and edit of a event data
    @Listen("onEventCreate = #calendars; onEventEdit = #calendars")
    public void createEvent(CalendarsEvent event) {
        calendarsEvent = event;
         
        //to display a shadow when editing
        calendarsEvent.stopClearGhost();
         
        BusinessCalendarEvent data = (BusinessCalendarEvent)event.getCalendarEvent();
         
        if(data == null) {
            data = new BusinessCalendarEvent();
            data.setHeaderColor("#3366ff");
            data.setContentColor("#6699ff");
            data.setBeginDate(event.getBeginDate());
            data.setEndDate(event.getEndDate());
        } else {
            data = (BusinessCalendarEvent) event.getCalendarEvent();
        }
        //notify the editor
        QueueUtil.lookupQueue().publish(
        		
                new QueueMessage(QueueMessage.Type.EDIT,data));
    }
     
    //listen to the calendar-update of event data, usually send when user drag the event data
    @Listen("onEventUpdate = #calendars")
    public void updateEvent(CalendarsEvent event) throws Exception {
        BusinessCalendarEvent data = (BusinessCalendarEvent) event.getCalendarEvent();
        data.setBeginDate(event.getBeginDate());
        data.setEndDate(event.getEndDate());
        calendarModel.update(data);
        data.setUserId(user.getId());
        businessCalendarDao.updateEvent(data);
    }
     
    //listen to queue message from other controller
    @Subscribe(value = QueueUtil.QUEUE_NAME)
    public void handleQueueMessage(QueueMessage message) throws Exception {
    	BusinessCalendarEvent calendarEvent = (BusinessCalendarEvent)message.getData();
        calendarEvent.setUserId(user.getId());
        switch(message.getType()){
        case DELETE:
            calendarModel.remove((BusinessCalendarEvent)message.getData());
            businessCalendarDao.deleteEvent(calendarEvent);
            //clear the shadow of the event after editing
            calendarsEvent.clearGhost();
            calendarsEvent = null;
            break;
        case OK:
            if (calendarModel.indexOf(calendarEvent) >= 0) {
                calendarModel.update(calendarEvent);
                businessCalendarDao.updateEvent(calendarEvent);
            } else {
                calendarModel.add(calendarEvent);
                businessCalendarDao.insertBusinessCalendarEvent(calendarEvent);
            }
        case CANCEL:
            //clear the shadow of the event after editing
            calendarsEvent.clearGhost();
            calendarsEvent = null;
            break;
        }
    }

    public ListModel<Project> getProjectsModel() {
    	return projectsModel;
    }
    
    @Listen("onSelect = #projectListbox")
    public void changeProduct() {
        Set<Project> selectedProjects = ((ListModelList<Project>)projectsModel).getSelection();
        selectedProject = selectedProjects.iterator().next();
        calendarModel.setFilterProject(selectedProject);
        calendars.setModel(calendarModel);
        
        if (calendars.getMold().equals("month")) {
        	invoiceButton.setDisabled(false);
        }
    }    
    
    @Listen("onClick = #invoiceButton")
    public void createInvoice(){
    	System.out.println("Maak factuur van: " + calendars.getBeginDate() + " tot " + calendars.getEndDate() + " en: " + calendars.getCurrentDate());
    }    

}