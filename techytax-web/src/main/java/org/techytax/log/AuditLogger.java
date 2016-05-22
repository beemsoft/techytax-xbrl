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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.techytax.domain.User;
import org.techytax.domain.UserEntity;
import org.techytax.jpa.dao.LogRecordDao;
import org.techytax.jpa.entities.LogRecord;

import javax.transaction.Transactional;
import java.util.Date;

@Component
public class AuditLogger {
	
	@Autowired
	private LogRecordDao logDao;

	@Transactional
	public void log(AuditType auditType, User user) {
		LogRecord logRecord = new LogRecord();
		UserEntity userEntity = new UserEntity(user);
		logRecord.setUser(userEntity);
		logRecord.setAuditType(auditType);
		logRecord.setTimeStamp(new Date());
		logDao.persistEntity(logRecord);
	}

	public Date getVatDeclarationTimeForLatestVatPeriod() throws IllegalAccessException {
		return logDao.getVatDeclarationTimeForLatestVatPeriod();
	}

}
