/**
 * Copyright 2013 Hans Beemsterboer
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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.techytax.dao.CostTypeDao;
import org.techytax.domain.CostConstants;
import org.techytax.domain.CostType;
import org.zkoss.util.resource.Labels;

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
		costTypeMap = new HashMap<Long, CostType>();
		CostTypeDao kostensoortDao = new CostTypeDao();
		List<CostType> costTypes = kostensoortDao.getKostensoortLijst();
		for (CostType costType : costTypes) {
			if (costType.getKostenSoortId() != CostConstants.VAT_CORRECTION_CAR_DEPRECIATION) {
				costType.setOmschrijving(Labels.getLabel(costType.getOmschrijving()));
				costTypeMap.put(costType.getKostenSoortId(), costType);
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
