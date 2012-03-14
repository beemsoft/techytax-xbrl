/**
 * Copyright 2012 Hans Beemsterboer
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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.techytax.dao.FiscalDao;
import org.techytax.domain.Activum;
import org.techytax.domain.KeyYear;
import org.techytax.domain.Passivum;
import org.techytax.report.domain.BalanceReport;
import org.techytax.report.domain.ReportBalance;

public class FiscalReportHelper {

	public static BalanceReport getActivaReport(List<Activum> activa) {
		BalanceReport report = new BalanceReport();
		Activum[] activaArray2 = new Activum[activa.size()];
		Activum[] activaArray = activa.toArray(activaArray2);
		List<ReportBalance> reportActiva = new ArrayList<ReportBalance>();
		BigInteger totalBegin  = new BigInteger("0");
		BigInteger totalEnd = new BigInteger("0");
		String description = null;
		int i = 0;
		do {

			ReportBalance reportActivum = new ReportBalance();
			Activum activumBegin = activaArray[i];
			Activum activumEnd = null;

			while (i < activaArray.length && (description == null || description.equals(activumBegin.getOmschrijving()))) {
				activumBegin = activaArray[i];
				description = activumBegin.getOmschrijving();
				reportActivum.setOmschrijving(description);
				if (activumBegin.getAanschafKosten() != null) {

					if (reportActivum.getAanschafKosten() == null) {
						reportActivum.setAanschafKosten(activumBegin.getAanschafKosten());
					} else {
						reportActivum.setAanschafKosten(reportActivum.getAanschafKosten().add(activumBegin.getAanschafKosten()));
					}
				}
				if (activumBegin.getRestwaarde() != null) {

					if (reportActivum.getRestwaarde() == null) {
						reportActivum.setRestwaarde(activumBegin.getRestwaarde());
					} else {
						reportActivum.setRestwaarde(reportActivum.getRestwaarde().add(activumBegin.getRestwaarde()));
					}
				}
				reportActivum.setBookValueBegin(activumBegin.getSaldo());
				if (i+1 < activaArray.length) {
					activumEnd = activaArray[i+1];
				}
				if (i + 1 < activaArray.length && activumEnd.getOmschrijving().equals(description)) {
					reportActivum.setBookValueEnd(activumEnd.getSaldo());
					i += 2;
					if (i < activaArray.length) {
						activumBegin = activaArray[i];
					}
				} else {
					activumEnd = activumBegin;
					reportActivum.setBookValueBegin(new BigInteger("0"));
					reportActivum.setBookValueEnd(activumEnd.getSaldo());
					i++;
					break;
				}
			}
			totalBegin = totalBegin.add(reportActivum.getBookValueBegin());
			if (reportActivum.getBookValueEnd() != null) {
				totalEnd = totalEnd.add(reportActivum.getBookValueEnd());
			}
			reportActiva.add(reportActivum);
			description = null;

		} while (i < activaArray.length);
		report.setActiva(reportActiva);
		report.setTotalBeginValue(totalBegin);
		report.setTotalEndValue(totalEnd);
		return report;
	}
	
	public static BalanceReport getPassivaReport(List<Passivum> passiva) {
		BalanceReport report = new BalanceReport();
		Passivum[] passivaArray2 = new Passivum[passiva.size()];
		Passivum[] passivaArray = passiva.toArray(passivaArray2);
		List<ReportBalance> reportBalance = new ArrayList<ReportBalance>();
		BigInteger totalBegin  = new BigInteger("0");
		BigInteger totalEnd = new BigInteger("0");
		int i = 0;
		do {

			ReportBalance reportPassivum = new ReportBalance();
			Passivum passivumBegin = passivaArray[i];
			reportPassivum.setBookValueBegin(passivumBegin.getSaldo());
			reportPassivum.setOmschrijving(passivumBegin.getOmschrijving());
			Passivum passivumEnd = null;
			if (i+1 < passivaArray.length) {
				passivumEnd = passivaArray[i+1];
			}
			if (i+1 < passivaArray.length && passivumEnd.getOmschrijving().equals(passivumBegin.getOmschrijving())) {
				reportPassivum.setBookValueEnd(passivumEnd.getSaldo());
			} else {
				passivumEnd = passivumBegin;
				reportPassivum.setBookValueBegin(new BigInteger("0"));
				reportPassivum.setBookValueEnd(passivumEnd.getSaldo());
			}
			

			totalBegin = totalBegin.add(reportPassivum.getBookValueBegin());
			if (reportPassivum.getBookValueEnd() != null) {
				totalEnd = totalEnd.add(reportPassivum.getBookValueEnd());
			}
			reportBalance.add(reportPassivum);
			if (passivumEnd.getOmschrijving().equals(passivumBegin.getOmschrijving())) {
				i+=1;
			} else {
				i+=2;
			}
		} while (i < passivaArray.length);
		report.setActiva(reportBalance);
		report.setTotalBeginValue(totalBegin);
		report.setTotalEndValue(totalEnd);
		return report;
	}	

	public static void main(String[] args) {
		FiscalDao fiscalDao = new FiscalDao();
		KeyYear key = new KeyYear();
		key.setYear(2011);
		key.setUserId(14);
		try {
//			List<Activum> activa = fiscalDao.getActivaLijst(key);
//			BalanceReport report = getActivaReport(activa);
			List<Passivum> passiva = fiscalDao.getPassivaLijst(key);
			BalanceReport report = getPassivaReport(passiva);
			System.out.println("Test");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
