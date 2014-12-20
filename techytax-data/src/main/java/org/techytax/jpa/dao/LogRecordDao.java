package org.techytax.jpa.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.techytax.domain.FiscalPeriod;
import org.techytax.domain.User;
import org.techytax.jpa.entities.LogRecord;
import org.techytax.util.DateHelper;
import org.techytax.zk.login.UserCredentialManager;

@Component
public class LogRecordDao extends GenericDao<LogRecord> {

	public Date getVatDeclarationTimeForLatestVatPeriod()
			throws IllegalAccessException {
		User user = UserCredentialManager.getUser();
		if (user == null) {
			throw new IllegalAccessException();
		}
		FiscalPeriod latestVatPeriod = DateHelper.getLatestVatPeriod(user
				.getVatPeriodType());
		FiscalPeriod latestVatPeriodTillToday = DateHelper
				.getLatestVatPeriodTillToday();
		List<LogRecord> result = findByNamedQueryForPeriod(
				LogRecord.GET_VAT_DECLARATION_TIME,
				new FiscalPeriod(latestVatPeriod.getEndDate(),
						latestVatPeriodTillToday.getEndDate()));
		if (!result.isEmpty()) {
			return result.get(0).getTimeStamp();
		} else {
			return null;
		}
	}
}
