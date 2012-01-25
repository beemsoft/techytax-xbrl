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
import org.techytax.report.domain.ActivaReport;
import org.techytax.report.domain.ReportActivum;

public class FiscalReportHelper {

	public static ActivaReport getActivaReport(List<Activum> activa) {
		ActivaReport report = new ActivaReport();
		Activum[] activaArray2 = new Activum[activa.size()];
		Activum[] activaArray = activa.toArray(activaArray2);
		List<ReportActivum> reportActiva = new ArrayList<ReportActivum>();
		BigInteger totalBegin  = new BigInteger("0");
		BigInteger totalEnd = new BigInteger("0");
		String description = null;
		int i = 0;
		do {

			ReportActivum reportActivum = new ReportActivum();
			Activum activumBegin = activaArray[i];
			Activum activumEnd = null;

			while (i < activaArray.length && (description == null || description.equals(activumBegin.getOmschrijving()))) {
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

				if (i < activaArray.length) {
					activumEnd = activaArray[i + 1];
					reportActivum.setBookValueEnd(activumEnd.getSaldo());
					i += 2;
					if (i < activaArray.length) {
						activumBegin = activaArray[i];
					}
				}
			}
			totalBegin = totalBegin.add(reportActivum.getBookValueBegin());
			totalEnd = totalEnd.add(reportActivum.getBookValueEnd());
			reportActiva.add(reportActivum);
			description = null;

		} while (i < activaArray.length);
		report.setActiva(reportActiva);
		report.setTotalBeginValue(totalBegin);
		report.setTotalEndValue(totalEnd);
		return report;
	}

	public static void main(String[] args) {
		FiscalDao fiscalDao = new FiscalDao();
		KeyYear key = new KeyYear();
		key.setYear(2011);
		key.setUserId(1);
		try {
			List<Activum> activa = fiscalDao.getActivaLijst(key);
			ActivaReport report = getActivaReport(activa);
			System.out.println("Test");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
