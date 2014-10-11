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
package org.techytax.log;

import java.util.Date;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.techytax.domain.FiscalPeriod;
import org.techytax.domain.User;
import org.techytax.domain.UserEntity;
import org.techytax.jpa.dao.GenericDao;
import org.techytax.jpa.entities.EntityManagerHelper;
import org.techytax.jpa.entities.LogRecord;
import org.techytax.util.DateHelper;
import org.zkoss.zkplus.jpa.JpaUtil;

public class AuditLogger {

	public static void log(AuditType auditType, User user) {
		GenericDao<LogRecord> logDao = new GenericDao<>(EntityManagerHelper.getEntityManager(), LogRecord.class);
		LogRecord logRecord = new LogRecord();
		UserEntity userEntity = new UserEntity(user);
		logRecord.setUser(userEntity);
		logRecord.setAuditType(auditType);
		logRecord.setTimeStamp(new Date());
		logDao.persistEntity(logRecord);
	}

	public static Date getVatDeclarationTimeForLatestVatPeriod(User user) throws IllegalAccessException {
		if (user == null) {
			throw new IllegalAccessException();
		}
		FiscalPeriod latestVatPeriod = DateHelper.getLatestVatPeriod(user.getVatPeriodType());
		FiscalPeriod latestVatPeriodTillToday = DateHelper.getLatestVatPeriodTillToday();
		TypedQuery<LogRecord> query = JpaUtil.getEntityManager().createQuery(
				"SELECT lr FROM LogRecord lr WHERE lr.timeStamp > :beginTime AND lr.timeStamp <= :endTime AND lr.auditType='SEND_VAT_DECLARATION' AND lr.user= :user",
				LogRecord.class);
		query.setParameter("beginTime", latestVatPeriod.getEndDate());
		query.setParameter("endTime", latestVatPeriodTillToday.getEndDate());
		query.setParameter("user", user);
		LogRecord result = null;
		try {
			result = query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
		return result.getTimeStamp();
	}

	public static void main(String[] args) throws IllegalAccessException {
		User user = new User();
		user.setId(1L);
		System.out.println(getVatDeclarationTimeForLatestVatPeriod(user));
	}
}
