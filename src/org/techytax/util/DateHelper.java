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
package org.techytax.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.net.time.TimeTCPClient;
import org.techytax.domain.Periode;
import org.techytax.domain.VatPeriodType;

public class DateHelper {

	private static String datePattern = "yyyy-MM-dd";
	private static String timePattern = "yyyy-MM-dd HH:mm:ss";
	private static String timePattern2 = "yyyyMMddHHmm";
	private static String datePatternForTravelChipCard = "dd-MM-yyyy";
	private static String datePatternForInvoice = "dd-MM-yyyy";
	private static String datePatternForIng = "dd-MM-yyyy";

	public static Date stringToDate(String date_str) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(datePattern);
		try {
			return format.parse(date_str);
		} catch (ParseException e) {
			throw new Exception("errors.date.invalid");
		}
	}

	public static Date stringToDateForIng(String date_str) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(datePatternForIng);
		try {
			return format.parse(date_str);
		} catch (ParseException e) {
			throw new Exception("errors.date.invalid");
		}
	}

	public static Date stringToDateForTravelChipCard(String date_str) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(datePatternForTravelChipCard);
		try {
			return format.parse(date_str);
		} catch (ParseException e) {
			throw new Exception("errors.date.invalid");
		}
	}

	public static String getDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(datePattern);
		String dateString = format.format(date);
		return dateString;
	}

	public static String getTime(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(timePattern);
		String timeString = format.format(date);
		return timeString;
	}

	public static String getTimeStamp(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(timePattern2);
		String timeString = format.format(date);
		return timeString;
	}

	public static XMLGregorianCalendar getDate(String date_str) throws Exception {
		XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar();
		Date date = stringToDate(date_str);
		calendar.setDay(getDay(date));
		calendar.setMonth(getMonth(date) + 1);
		calendar.setYear(getYear(date));
		return calendar;
	}

	public static XMLGregorianCalendar getDateForXml(Date date) {
		XMLGregorianCalendar calendar = null;
		try {
			calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar();
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		calendar.setDay(getDay(date));
		calendar.setMonth(getMonth(date) + 1);
		calendar.setYear(getYear(date));
		calendar.setHour(0);
		calendar.setMinute(0);
		calendar.setSecond(0);
		return calendar;
	}

	public static int getDay(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static int getMonth(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}

	public static Periode getPeriodPreviousYear() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, -1);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date beginDatum = cal.getTime();
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		Date eindDatum = cal.getTime();
		Periode periode = new Periode();
		periode.setBeginDatum(beginDatum);
		periode.setEindDatum(eindDatum);
		return periode;
	}

	public static Periode getLastVatPeriodPreviousYear() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, -1);
		cal.set(Calendar.MONTH, Calendar.OCTOBER);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date beginDatum = cal.getTime();
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		Date eindDatum = cal.getTime();
		Periode periode = new Periode();
		periode.setBeginDatum(beginDatum);
		periode.setEindDatum(eindDatum);
		return periode;
	}

	public static Periode getLatestVatPeriod(VatPeriodType vatPeriodType) {
		Periode period = null;
		switch (vatPeriodType) {
		case PER_QUARTER:
			period = getLatestQuarterPeriod();
			break;
		case PER_YEAR:
			period = getPeriodPreviousYear();
			break;
		}

		return period;
	}

	private static Periode getLatestQuarterPeriod() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());

		int month = cal.get(Calendar.MONTH);
		if (month == 0) {
			cal.add(Calendar.YEAR, -1);
		}
		int quarter = getQuarter(month);
		int lastMonth = quarter * 3;
		int firstMonth = quarter * 3 - 3;
		cal.set(Calendar.MONTH, firstMonth);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date beginDatum = cal.getTime();
		cal.set(Calendar.MONTH, lastMonth);
		cal.set(Calendar.DAY_OF_MONTH, 0);
		Date eindDatum = cal.getTime();
		Periode periode = new Periode();
		periode.setBeginDatum(beginDatum);
		periode.setEindDatum(eindDatum);
		return periode;
	}

	public static Date getLastDayOfFirstMonthOfNextQuarter(Date date) {
		int month = getMonth(date);
		int year = getYear(date);
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, year);
		Date lastDay = null;
		switch (month) {
		case 0:
		case 1:
		case 2:
		case 3:
			cal.set(Calendar.MONTH, Calendar.APRIL);
			cal.set(Calendar.DAY_OF_MONTH, 30);
			lastDay = cal.getTime();
			break;
		case 4:
		case 5:
		case 6:
			cal.set(Calendar.MONTH, Calendar.JULY);
			cal.set(Calendar.DAY_OF_MONTH, 31);
			lastDay = cal.getTime();
			break;
		case 7:
		case 8:
		case 9:
			cal.set(Calendar.MONTH, Calendar.OCTOBER);
			cal.set(Calendar.DAY_OF_MONTH, 31);
			lastDay = cal.getTime();
			break;
		case 10:
		case 11:
			cal.add(Calendar.YEAR, 1);
			cal.set(Calendar.MONTH, Calendar.JANUARY);
			cal.set(Calendar.DAY_OF_MONTH, 31);
			lastDay = cal.getTime();
			break;
		default:
			break;
		}
		return lastDay;
	}

	public static Periode getLatestVatPeriodTillToday() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		int month = cal.get(Calendar.MONTH);
		if (month == 0) {
			cal.add(Calendar.YEAR, -1);
		}
		int quarter = getQuarter(month);
		int firstMonth = quarter * 3 - 3;
		cal.set(Calendar.MONTH, firstMonth);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date beginDatum = cal.getTime();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date eindDatum = cal.getTime();
		Periode periode = new Periode();
		periode.setBeginDatum(beginDatum);
		periode.setEindDatum(eindDatum);
		return periode;
	}

	private static int getQuarter(int month) {
		int quarter = 1;
		switch (month) {
		case 1:
		case 2:
		case 3:
			quarter = 1;
			break;
		case 4:
		case 5:
		case 6:
			quarter = 2;
			break;
		case 7:
		case 8:
		case 9:
			quarter = 3;
			break;
		case 10:
		case 11:
		case 0:
			quarter = 4;
			break;
		default:
			break;
		}
		return quarter;
	}

	public static int getCurrentYear() {
		return getYear(new Date());
	}

	public static int getYear(Date date) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		int jaar = cal.get(Calendar.YEAR);
		return jaar;
	}
	
	public static int getFiscalYear() {
		return getYear(new Date()) - 1;
	}
	
	public static Date getLastDayOfFiscalYear() {
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, -1);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		return cal.getTime();
	}

	public static boolean hasOneDayDifference(Date date1, String date2) throws Exception {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date1);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String date = getDate(cal.getTime());
		if (date.equals(date2)) {
			return true;
		}
		cal.add(Calendar.DAY_OF_MONTH, -1);
		date = getDate(cal.getTime());
		if (date.equals(date2)) {
			return true;
		}
		cal.add(Calendar.DAY_OF_MONTH, -1);
		date = getDate(cal.getTime());
		if (date.equals(date2)) {
			return true;
		}
		return false;
	}

	public static Periode getPeriodTillDate(Date balanceDate) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(balanceDate);
		Date eindDatum = cal.getTime();
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date beginDatum = cal.getTime();
		Periode periode = new Periode();
		periode.setBeginDatum(beginDatum);
		periode.setEindDatum(eindDatum);
		return periode;
	}

	public static Date getNTPDate() {
		List<String> hosts = Arrays.asList("ntp.xs4all.nl");

		for (String host : hosts) {
			TimeTCPClient client = new TimeTCPClient();
			client.setDefaultTimeout(5000);
			try {
				client.connect(host);
				Date ntpDate = client.getDate();
				client.disconnect();
				if (ntpDate != null) {
					return ntpDate;
				}
			} catch (java.net.SocketException exp) {
				exp.printStackTrace();
			} catch (java.io.IOException exp) {
				exp.printStackTrace();
			}
		}
		return null;
	}

	public static String getInvoiceDateString(Date date) {
		SimpleDateFormat df = null;
		String returnValue = "";
		if (date != null) {
			df = new SimpleDateFormat(datePatternForInvoice);
			returnValue = df.format(date);
		}
		return returnValue;
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

	public static List<Integer> getLatestSevenYears() {
		List<Integer> yearList = new ArrayList<Integer>();
		int yearInt = getCurrentYear();
		Integer year = new Integer(yearInt);
		for (int i = 0; i < 7; i++) {
			yearList.add(year);
			yearInt--;
			year = new Integer(yearInt);
		}
		return yearList;
	}
	
	public static boolean isTimeForUsingLatestYearPeriod() {
		Date currentDate = new Date();
		Calendar cal = new GregorianCalendar();
		cal.setTime(currentDate);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		Date beginDate = cal.getTime();
		cal.set(Calendar.MONTH, Calendar.APRIL);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date endDate = cal.getTime();
		if (currentDate.after(beginDate) && currentDate.before(endDate)) {
			return true;
		}
		return false;
	}
	
	public static boolean isBefore2014(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.YEAR, 2014);
		Date fromDate = cal.getTime();
		if (fromDate.after(date)) {
			return true;
		}
		return false;
	}	

	public static void main(String[] args) {
		System.out.println(getLatestVatPeriod(VatPeriodType.PER_QUARTER).getBeginDatum());
		System.out.println(getLatestVatPeriod(VatPeriodType.PER_QUARTER).getEindDatum());
		System.out.println(getLatestVatPeriod(VatPeriodType.PER_YEAR).getBeginDatum());
		System.out.println(getLatestVatPeriod(VatPeriodType.PER_YEAR).getEindDatum());
		System.out.println(getDateForXml(new Date()));
		System.out.println(getNTPDate());
	}

}
