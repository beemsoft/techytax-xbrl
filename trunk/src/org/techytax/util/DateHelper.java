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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.techytax.domain.Periode;

public class DateHelper {

	public static String getDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = format.format(date);
		return dateString;
	}

	public static Periode getPeriodeVorigJaar() {
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

}
