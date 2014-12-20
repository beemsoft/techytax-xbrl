package org.techytax.business.zk.calendar;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.techytax.dao.BusinessCalendarDao;
import org.techytax.domain.BusinessCalendarEvent;

@Service
public class CalendarService {
	
	@Autowired
	private BusinessCalendarDao businessCalendarDao;

	public void add(BusinessCalendarEvent calendarEvent) throws Exception {
		businessCalendarDao.insertBusinessCalendarEvent(calendarEvent);
	}

	public List<BusinessCalendarEvent> getEvents() throws Exception {
		return businessCalendarDao.getEvents();
	}

	@Transactional
	public void delete(BusinessCalendarEvent calendarEvent) {
		businessCalendarDao.deleteEntity(calendarEvent);
	}

}
