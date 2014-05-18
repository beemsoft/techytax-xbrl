/**
 * Copyright 2014 Hans Beemsterboer
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
package org.techytax.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.techytax.business.zk.calendar.BusinessCalendarEvent;
import org.zkoss.zkplus.jpa.JpaUtil;

public class BusinessCalendarDao extends BaseDao<BusinessCalendarEvent> {

	public BusinessCalendarDao(Class<BusinessCalendarEvent> persistentClass) {
		super(persistentClass);
	}

	public void insertBusinessCalendarEvent(BusinessCalendarEvent event) throws Exception {
		event.roundValues();
		event.setUser(user);
		persistEntity(event);
	}

	public List<BusinessCalendarEvent> getEvents() throws Exception {
		TypedQuery<BusinessCalendarEvent> query = JpaUtil.getEntityManager().createQuery("SELECT e FROM org.techytax.business.zk.calendar.BusinessCalendarEvent e WHERE e.user = :user",
				BusinessCalendarEvent.class);
		query.setParameter("user", user);
		List<BusinessCalendarEvent> result = query.getResultList();
		return result;
	}

	public void updateEvent(BusinessCalendarEvent event) throws Exception {
		event.roundValues();
		merge(event);
	}

	public void deleteEvent(BusinessCalendarEvent event) throws Exception {
		deleteEntity(event);
	}

}
