/**
 * Copyright 2015 Hans Beemsterboer
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
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.techytax.domain.BusinessCalendarEvent;
import org.techytax.jpa.dao.GenericDao;
import org.techytax.zk.login.UserCredentialManager;

@Component
public class BusinessCalendarDao extends GenericDao<BusinessCalendarEvent> {

	@Transactional
	public void insertBusinessCalendarEvent(BusinessCalendarEvent event) throws Exception {
		event.roundValues();
		event.setUser(UserCredentialManager.getUser());
		persistEntity(event);
	}

	public List<BusinessCalendarEvent> getEvents() throws Exception {
		TypedQuery<BusinessCalendarEvent> query = getEntityManager().createQuery("SELECT e FROM BusinessCalendarEvent e WHERE e.user = :user",
				BusinessCalendarEvent.class);
		query.setParameter("user", UserCredentialManager.getUser());
		List<BusinessCalendarEvent> result = query.getResultList();
		return result;
	}

	@Transactional
	public void updateEvent(BusinessCalendarEvent event) throws Exception {
		event.roundValues();
		merge(event);
	}

}
