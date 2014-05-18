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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.techytax.domain.BusinessCalendarEvent;
import org.techytax.domain.Project;
import org.zkoss.calendar.api.CalendarEvent;
import org.zkoss.calendar.api.RenderContext;
import org.zkoss.calendar.impl.SimpleCalendarModel;
 
public class BusinessCalendarModel extends SimpleCalendarModel {
    private static final long serialVersionUID = 1L;
     
    private String filterText = "";
 
    private Project filterProject = null;
    
    public BusinessCalendarModel(List<BusinessCalendarEvent> calendarEvents) {
        super(calendarEvents);
    }
 
    public void setFilterText(String filterText) {
        this.filterText = filterText;
    }
    
    public void setFilterProject(Project filterProject) {
    	this.filterProject = filterProject;
    }
 
    @Override
    public List<CalendarEvent> get(Date beginDate, Date endDate, RenderContext rc) {
        List<CalendarEvent> list = new LinkedList<CalendarEvent>();
        long begin = beginDate.getTime();
        long end = endDate.getTime();
                 
        for (Iterator<?> itr = _list.iterator(); itr.hasNext();) {
            Object obj = itr.next();
            BusinessCalendarEvent ce = obj instanceof BusinessCalendarEvent ? (BusinessCalendarEvent)obj : null;
             
            if(ce == null) break;
             
            long b = ce.getBeginDate().getTime();
            long e = ce.getEndDate().getTime();
            if (e >= begin && b < end && ce.getContent().toLowerCase().contains(filterText.toLowerCase()))
            	if (filterProject == null || ce.getProject() == filterProject) {
            		if (filterProject != null) {
            			ce.setContent(ce.getContent() + " (" + ce.getUnitsOfWork() + ")");
            		}
            		list.add(ce);
            	}
        }
        return list;
    }
 
}
