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

import java.awt.Color;
import java.awt.GradientPaint;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.TableOrder;
import org.techytax.domain.BookValue;
import org.techytax.domain.FiscalOverview;
import org.techytax.report.domain.BalanceReport;
import org.techytax.report.domain.ReportBalance;

public class JFreeChartHelper {

	public static JFreeChart createBarChart(List<BookValue> bookValues) {

		CategoryDataset dataset = createDataset(bookValues);

		// create the chart...
		JFreeChart chart = ChartFactory.createBarChart("Boekwaarde overzicht", // chart
																				// title
				"Jaar", // domain axis label
				"Saldo", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips?
				false // URLs?
				);

		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

		// set the background color for the chart...
		chart.setBackgroundPaint(Color.white);

		// get a reference to the plot for further customisation...
		CategoryPlot plot = (CategoryPlot) chart.getPlot();

		// ******************************************************************
		// More than 150 demo applications are included with the JFreeChart
		// Developer Guide...for more information, see:
		//
		// > http://www.object-refinery.com/jfreechart/guide.html
		//
		// ******************************************************************

		// set the range axis to display integers only...
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		// disable bar outlines...
		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);

		// set up gradient paints for series...
		GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue, 0.0f, 0.0f, new Color(0, 0, 64));
		GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f, 0.0f, new Color(0, 64, 0));
		GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red, 0.0f, 0.0f, new Color(64, 0, 0));
		renderer.setSeriesPaint(0, gp0);
		renderer.setSeriesPaint(1, gp1);
		renderer.setSeriesPaint(2, gp2);

		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));
		// OPTIONAL CUSTOMISATION COMPLETED.

		return chart;

	}

	private static CategoryDataset createDataset(List<BookValue> bookValues) {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		int categoryYear = 0;
		String balanceType = null;
		for (BookValue bookValue : bookValues) {
			int thisYear = bookValue.getJaar();
			if (thisYear != categoryYear) {
				categoryYear = thisYear;
			}
			balanceType = bookValue.getDescription();
			dataset.addValue(bookValue.getSaldo().doubleValue(), balanceType, Integer.toString(categoryYear));
		}

		return dataset;
	}

	public static JFreeChart createPieChartForProfitAndLoss(FiscalOverview overview) {

		PieDataset dataset = createDatasetForProfitAndLoss(overview);

		// set a theme using the new shadow generator feature available in
		// 1.0.14 - for backwards compatibility it is not enabled by default
		ChartFactory.setChartTheme(new StandardChartTheme("JFree/Shadow", true));

		JFreeChart chart = ChartFactory.createPieChart("Winst en verlies", // chart
																			// title
				dataset, // data
				true, // include legend
				true, false);

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setSectionOutlinesVisible(false);
		plot.setNoDataMessage("No data available");
		return chart;

	}

	private static PieDataset createDatasetForProfitAndLoss(FiscalOverview overview) {
		DefaultPieDataset dataset = new DefaultPieDataset();
		if (overview.getWinst() >= 0) {
			dataset.setValue("Winst", overview.getWinst());
		} else {
			dataset.setValue("Verlies", Math.abs(overview.getWinst()));
		}
		dataset.setValue("Rente-opbrengsten", overview.getInterestFromBusinessSavings());
		dataset.setValue("Andere kosten", overview.getKostenOverig());
		dataset.setValue("Transport kosten", overview.getKostenOverigTransport());
		dataset.setValue("Auto kosten", Math.abs(overview.getKostenAutoAftrekbaar()));
		dataset.setValue("Afschrijving auto", overview.getAfschrijvingAuto());
		dataset.setValue("Afschrijving overig", overview.getAfschrijvingOverig());
		return dataset;
	}

	public static JFreeChart createPieChartForActiva(FiscalOverview overview) {

		CategoryDataset dataset = createDatasetForActiva(overview);

		final JFreeChart chart = ChartFactory.createMultiplePieChart("Activa", // chart
																				// title
				dataset, // dataset
				TableOrder.BY_ROW, true, // include legend
				true, false);
		final MultiplePiePlot plot = (MultiplePiePlot) chart.getPlot();
		final JFreeChart subchart = plot.getPieChart();
		final PiePlot p = (PiePlot) subchart.getPlot();

		return chart;
	}

	private static CategoryDataset createDatasetForActiva(FiscalOverview overview) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		BalanceReport report = overview.getActivaReport();
		String title1 = Integer.toString(overview.getJaar()-1);
		String title2 = Integer.toString(overview.getJaar());		
		for (ReportBalance activum : report.getBalanceList()) {
			dataset.addValue(activum.getBookValueBegin(), title1, activum.getOmschrijving());
			dataset.addValue(activum.getBookValueEnd(), title2, activum.getOmschrijving());			
		}
 		return dataset;
	}
	
	public static JFreeChart createPieChartForPassiva(FiscalOverview overview) {

		CategoryDataset dataset = createDatasetForPassiva(overview);

		final JFreeChart chart = ChartFactory.createMultiplePieChart("Passiva", // chart
																				// title
				dataset, // dataset
				TableOrder.BY_ROW, true, // include legend
				true, false);
		final MultiplePiePlot plot = (MultiplePiePlot) chart.getPlot();
		final JFreeChart subchart = plot.getPieChart();
		final PiePlot p = (PiePlot) subchart.getPlot();

		return chart;
	}

	private static CategoryDataset createDatasetForPassiva(FiscalOverview overview) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		BalanceReport report = overview.getPassivaReport();
		String title1 = Integer.toString(overview.getJaar()-1);
		String title2 = Integer.toString(overview.getJaar());		
		for (ReportBalance activum : report.getBalanceList()) {
			dataset.addValue(activum.getBookValueBegin(), title1, activum.getOmschrijving());
			dataset.addValue(activum.getBookValueEnd(), title2, activum.getOmschrijving());			
		}
 		return dataset;
	}	

}