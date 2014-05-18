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
package org.techytax.cache;

import static org.techytax.domain.CostConstants.VAT_CORRECTION_CAR_DEPRECIATION;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.techytax.dao.CostTypeDao;
import org.techytax.domain.CostType;

public class CostTypeCache {

	private static Map<Long, CostType> costTypeMap = null;
	
	private CostTypeCache() {
		//
	}

	public static CostType getCostType(long id) throws Exception {

		if (costTypeMap == null) {
			fill();
		}
		return costTypeMap.get(id);
	}

	private static void fill() throws Exception {
		costTypeMap = new HashMap<>();
		CostTypeDao costTypeDao = new CostTypeDao(CostType.class);
		for (CostType costType : costTypeDao.getCostTypes()) {
			if (!costType.equals(VAT_CORRECTION_CAR_DEPRECIATION)) {
				costTypeMap.put(costType.getId(), costType);
				System.out.println("Cache costtype: " + costType.getOmschrijving());
			}
		}
	}

	public static Collection<CostType> getCostTypes() throws Exception {
		if (costTypeMap == null) {
			fill();
		}
		return costTypeMap.values();
	}

}
