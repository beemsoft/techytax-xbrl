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
package org.techytax.report.helper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.techytax.domain.BalanceType;
import org.techytax.domain.FiscalBalance;
import org.techytax.report.domain.BalanceReport;
import org.techytax.report.domain.ReportBalance;

import static java.math.BigInteger.ZERO;

public class FiscalReportHelper {

	public static BalanceReport getActivaReport(Map<BalanceType, FiscalBalance> activaMap) {
		BalanceReport report = new BalanceReport();
		List<ReportBalance> reportActiva = new ArrayList<>();
		BigInteger totalBegin = ZERO;
		BigInteger totalEnd = ZERO;
		Iterator<Entry<BalanceType, FiscalBalance>> iterator = activaMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<BalanceType, FiscalBalance> entry = iterator.next();
			ReportBalance reportActivum = new ReportBalance();
			BalanceType activum = entry.getKey();
			reportActivum.setDescription(activum.getKey());
			FiscalBalance fiscalBalance = entry.getValue();
			reportActivum.setBookValueBegin(fiscalBalance.getBeginSaldo());
			reportActivum.setBookValueEnd(fiscalBalance.getEndSaldo());
			BigDecimal totalPurchaseCost = fiscalBalance.getTotalPurchaseCost();
			if (totalPurchaseCost != null) {
				if (reportActivum.getAanschafKosten() == null) {
					reportActivum.setAanschafKosten(totalPurchaseCost);
				} else {
					reportActivum.setAanschafKosten(reportActivum.getAanschafKosten().add(totalPurchaseCost));
				}
			}
			BigInteger totalRemainingValue = fiscalBalance.getTotalRemainingValue();
			if (totalRemainingValue != null) {
				reportActivum.setRestwaarde(totalRemainingValue);
			}
			if (reportActivum.getBookValueBegin() != null) {
				totalBegin = totalBegin.add(reportActivum.getBookValueBegin());
			}
			if (reportActivum.getBookValueEnd() != null) {
				totalEnd = totalEnd.add(reportActivum.getBookValueEnd());
			}
			reportActiva.add(reportActivum);
		}
		report.setBalanceList(reportActiva);
		report.setTotalBeginValue(totalBegin);
		report.setTotalEndValue(totalEnd);
		return report;
	}

	public static BalanceReport getPassivaReport(Map<BalanceType, FiscalBalance> passivaMap) {
		BalanceReport report = new BalanceReport();
		List<ReportBalance> reportBalance = new ArrayList<>();
		BigInteger totalBegin = ZERO;
		BigInteger totalEnd = ZERO;

		if (passivaMap != null) {
			Iterator<Entry<BalanceType, FiscalBalance>> iterator = passivaMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<BalanceType, FiscalBalance> entry = iterator.next();
				ReportBalance reportPassivum = new ReportBalance();
				FiscalBalance fiscalBalance = entry.getValue();
				BalanceType passivum = entry.getKey();
				reportPassivum.setDescription(passivum.getKey());
				reportPassivum.setBookValueBegin(fiscalBalance.getBeginSaldo());
				reportPassivum.setBookValueEnd(fiscalBalance.getEndSaldo());
				if (reportPassivum.getBookValueBegin() != null) {
					totalBegin = totalBegin.add(reportPassivum.getBookValueBegin());
				}
				if (reportPassivum.getBookValueEnd() != null) {
					totalEnd = totalEnd.add(reportPassivum.getBookValueEnd());
				}
				reportBalance.add(reportPassivum);
			}
			report.setBalanceList(reportBalance);
			report.setTotalBeginValue(totalBegin);
			report.setTotalEndValue(totalEnd);


		}
		return report;
	}

}
