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
package org.techytax.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.techytax.business.zk.calendar.BusinessCalendarEvent;


public class BusinessCalendarDao extends BaseDao {

	private void encrypt(BusinessCalendarEvent event) {
		BigDecimal travelingByCarCostDeclaration = event.getTravelingByCarCostDeclaration();
		if (travelingByCarCostDeclaration != null && travelingByCarCostDeclaration.doubleValue() != 0) {
			event.setTravelingByCarCostDeclaration(decimalEncryptor.encrypt(travelingByCarCostDeclaration));
		}
		BigDecimal otherCostDeclaration = event.getOtherCostDeclaration();
		if (otherCostDeclaration != null && otherCostDeclaration.doubleValue() != 0) {
			event.setOtherCostDeclaration(decimalEncryptor.encrypt(otherCostDeclaration));
		}
		String title = event.getTitle();
		if (title != null && StringUtils.isNotEmpty(title.trim())) {
			event.setTitle(textEncryptor.encrypt(title));
		}
		String content = event.getContent();
		if (content != null && StringUtils.isNotEmpty(content.trim())) {
			event.setContent(textEncryptor.encrypt(content));
		}
	}

	public void decrypt(BusinessCalendarEvent event) {
		BigDecimal travelingByCarCostDeclaration = event.getTravelingByCarCostDeclaration();
		if (travelingByCarCostDeclaration != null && travelingByCarCostDeclaration.doubleValue() != 0) {
			try {
				event.setTravelingByCarCostDeclaration(decimalEncryptor.decrypt(travelingByCarCostDeclaration));
			} catch (EncryptionOperationNotPossibleException e) {
				e.printStackTrace();
				System.out.println("Could not decrypt: " + event.getTravelingByCarCostDeclaration());
			}
		}
		BigDecimal otherCostDeclaration = event.getOtherCostDeclaration();
		if (otherCostDeclaration != null && otherCostDeclaration.doubleValue() != 0) {
			try {
				event.setOtherCostDeclaration(decimalEncryptor.decrypt(otherCostDeclaration));
			} catch (EncryptionOperationNotPossibleException e) {
				e.printStackTrace();
				System.out.println("Could not decrypt: " + event.getOtherCostDeclaration());
			}
		}
		String title = event.getTitle();
		if (title != null && StringUtils.isNotEmpty(title.trim())) {
			event.setTitle(textEncryptor.decrypt(title));
		}
		String content = event.getContent();
		if (content != null && StringUtils.isNotEmpty(content.trim())) {
			event.setContent(textEncryptor.decrypt(content));
		}
	}

	public void insertBusinessCalendarEvent(BusinessCalendarEvent event) throws Exception {
		event.roundValues();
		encrypt(event);
		sqlMap.insert("insertEvent", event);
		decrypt(event);
	}

	@SuppressWarnings("unchecked")
	public List<BusinessCalendarEvent> getEvents(String userId) throws Exception {
		List<BusinessCalendarEvent> events = sqlMap.queryForList("getEvents", userId);
		for (BusinessCalendarEvent event : events) {
			decrypt(event);
		}
		return events;
	}

	public void updateEvent(BusinessCalendarEvent event) throws Exception {
		event.roundValues();
		encrypt(event);
		sqlMap.insert("updateEvent", event);
		decrypt(event);
	}
	
	public void deleteEvent(BusinessCalendarEvent event) throws Exception {
		sqlMap.delete("deleteEvent", event);
	}	

}
