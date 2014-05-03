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
package org.techytax.chart;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Locale;

import org.jfree.chart.JFreeChart;
import org.techytax.domain.BookValue;
import org.techytax.domain.FiscalOverview;
import org.techytax.helper.Translator;

@Deprecated
public final class ChartFactory {

	public static final int IMAGE_WIDTH = 704;
	public static final int IMAGE_HEIGHT = 396;

	/**
	 * Private constructor for Factory
	 */
	private ChartFactory() {
	};

	public static BufferedImage createBookValueGraph(long userId, Locale locale) throws Exception {
		List<BookValue> bookValues = null;
		String title = Translator.translateKey("menu.bookvalues", locale);
		String domainAxisLabel = Translator.translateKey("label.year", locale);
		String rangeAxisLabel = Translator.translateKey("label.saldo", locale);		
		return createBarChart(bookValues, title, domainAxisLabel, rangeAxisLabel, locale);
	}

	private static BufferedImage createBarChart(List<BookValue> bookValues, String title, String domainAxisLabel, String rangeAxisLabel, Locale locale) {
		JFreeChart chart = JFreeChartHelper.createBarChart(bookValues, title, domainAxisLabel, rangeAxisLabel, locale);
		return chart.createBufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB, null);
	}

	public static BufferedImage createProfitAndLossPieChart(FiscalOverview overview, Locale locale) {
		JFreeChart chart = JFreeChartHelper.createPieChartForProfitAndLoss(overview, locale);
		return chart.createBufferedImage(640, 230, BufferedImage.TYPE_INT_RGB, null);
	}
	
	public static BufferedImage createActivaPieChart(FiscalOverview overview, Locale locale) {
		String title = Translator.translateKey("overview.fiscal.activa", locale);
		JFreeChart chart = JFreeChartHelper.createPieChartForActiva(overview, title);
		return chart.createBufferedImage(850, 300, BufferedImage.TYPE_INT_RGB, null);
	}
	
	public static BufferedImage createPassivaPieChart(FiscalOverview overview, Locale locale) {
		String title = Translator.translateKey("overview.fiscal.passiva", locale);
		JFreeChart chart = JFreeChartHelper.createPieChartForPassiva(overview, title);
		return chart.createBufferedImage(850, 300, BufferedImage.TYPE_INT_RGB, null);
	}	

}
