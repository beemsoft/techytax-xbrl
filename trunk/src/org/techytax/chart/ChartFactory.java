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

import org.jfree.chart.JFreeChart;
import org.techytax.dao.BoekwaardeDao;
import org.techytax.domain.Boekwaarde;
import org.techytax.domain.KeyId;

public final class ChartFactory {

	public static final int IMAGE_WIDTH = 704;
	public static final int IMAGE_HEIGHT = 396;

	/**
	 * Private constructor for Factory
	 */
	private ChartFactory() {
	};

	public static BufferedImage createBookValueGraph(long userId) throws Exception {
		BoekwaardeDao dao = new BoekwaardeDao();
		KeyId key = new KeyId();
		key.setUserId(userId);
		List<Boekwaarde> bookValues = dao.getBookValuesForChart(key);
		return createChart(bookValues);
	}

	private static BufferedImage createChart(List<Boekwaarde> bookValues) {
		JFreeChart chart = JFreeChartHelper.createChart(bookValues);
		return chart.createBufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB, null);
	}
	
}
