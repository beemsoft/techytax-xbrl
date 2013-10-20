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
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.techytax.domain.FiscalOverview;
import org.techytax.domain.User;

@SuppressWarnings("serial")
public class ChartGeneratorServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

		BufferedImage image = null;

		try {
			User user = (User) httpServletRequest.getSession().getAttribute("user");

			String chartType = httpServletRequest.getParameter("chartType");
//			Locale locale = (Locale)httpServletRequest.getSession().getAttribute(Globals.LOCALE_KEY);
			Locale locale = null;
			if (chartType.equals("bookValues")) {
				image = ChartFactory.createBookValueGraph(user.getId(), locale);
			} else if (chartType.equals("profitAndLoss")) {
				FiscalOverview overview = (FiscalOverview) httpServletRequest.getSession().getAttribute("overview");
				image = ChartFactory.createProfitAndLossPieChart(overview, locale);
			} else if (chartType.equals("activa")) {
				FiscalOverview overview = (FiscalOverview) httpServletRequest.getSession().getAttribute("overview");
				image = ChartFactory.createActivaPieChart(overview, locale);
			} else if (chartType.equals("passiva")) {
				FiscalOverview overview = (FiscalOverview) httpServletRequest.getSession().getAttribute("overview");
				image = ChartFactory.createPassivaPieChart(overview, locale);
			} else {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (image == null) {

		}

		// Write BufferedImage to response output stream.
		httpServletResponse.setContentType("image/png");

		// The following line is needed for IE:
		httpServletResponse.setHeader("Content-disposition", "inline;filename=chart.png");

		OutputStream outputStream = httpServletResponse.getOutputStream();
		ImageIO.write(image, "png", outputStream);
		outputStream.close();

	}

}
