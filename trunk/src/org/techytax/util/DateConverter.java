/**
 * Copyright 2009 Hans Beemsterboer
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
package org.techytax.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateConverter {

	private static String datePattern = "yyyy-MM-dd";
	private static String factuurDatumPattern = "dd-MM-yyyy";

	public static String getDate(Date date) {

		SimpleDateFormat df = null;
		String returnValue = "";
		if (date != null) {
			df = new SimpleDateFormat(datePattern);
			returnValue = df.format(date);
		}

		return returnValue;
	}

	public static String getFactuurDatum(Date date) {

		SimpleDateFormat df = null;
		String returnValue = "";
		if (date != null) {
			df = new SimpleDateFormat(factuurDatumPattern);
			returnValue = df.format(date);
		}

		return returnValue;
	}

	public static Date stringToDate(String date_str) throws Exception {
		SimpleDateFormat df_zos = new SimpleDateFormat(datePattern);
		try {
			return df_zos.parse(date_str);
		} catch (ParseException e) {
			throw new Exception("errors.date.invalid");
		}
	}

	public static String getMaand(Date date) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		int maand = cal.get(Calendar.MONTH);
		String maandString = null;
		switch (maand) {
		case 0:
			maandString = "januari";
			break;
		case 1:
			maandString = "februari";
			break;
		case 2:
			maandString = "maart";
			break;
		case 3:
			maandString = "april";
			break;
		case 4:
			maandString = "mei";
			break;
		case 5:
			maandString = "juni";
			break;
		case 6:
			maandString = "juli";
			break;
		case 7:
			maandString = "augustus";
			break;
		case 8:
			maandString = "september";
			break;
		case 9:
			maandString = "oktober";
			break;
		case 10:
			maandString = "november";
			break;
		case 11:
			maandString = "december";
			break;
		}
		return maandString;
	}

	public static int getJaar(Date date) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		int jaar = cal.get(Calendar.YEAR);
		return jaar;
	}

	public static int getEersteDagVanDeMaand(Date date) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.WEEK_OF_MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static int getDagVanDeMaand(Date date) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		int dag = cal.get(Calendar.DAY_OF_MONTH);
		return dag;
	}

	public static int getAantalWekenInMaand(Date date) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int eersteWeek = cal.get(Calendar.WEEK_OF_YEAR);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, -1);
		int laatsteWeek = cal.get(Calendar.WEEK_OF_YEAR);
		System.out.println("eersteWeek: " + eersteWeek);
		System.out.println("laatsteWeek: " + laatsteWeek);
		System.out.println("Aantal weken in maand: "
				+ (laatsteWeek - eersteWeek + 1));
		return laatsteWeek - eersteWeek + 1;
	}

}
