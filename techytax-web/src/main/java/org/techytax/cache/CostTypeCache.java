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

import static org.techytax.domain.CostConstants.BUSINESS_LITERATURE_CREDIT_CARD_NO_VAT;
import static org.techytax.domain.CostConstants.BUSINESS_TRAVEL_CREDIT_CARD;
import static org.techytax.domain.CostConstants.DEPRECIATION_CAR;
import static org.techytax.domain.CostConstants.DEPRECIATION_MACHINE;
import static org.techytax.domain.CostConstants.DEPRECIATION_SETTLEMENT;
import static org.techytax.domain.CostConstants.VAT_CORRECTION_CAR_DEPRECIATION;
import static org.techytax.domain.CostConstants.VAT_PAID_BACK_ON_OTHER_ACCOUNT;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.techytax.dao.CostTypeDao;
import org.techytax.domain.CostType;

@Component
public class CostTypeCache {

	@Autowired
	CostTypeDao costTypeDao;

	private final List<CostType> COST_TYPES_SKIP = Arrays.asList(VAT_CORRECTION_CAR_DEPRECIATION, BUSINESS_TRAVEL_CREDIT_CARD, BUSINESS_LITERATURE_CREDIT_CARD_NO_VAT, DEPRECIATION_CAR,
			DEPRECIATION_SETTLEMENT, DEPRECIATION_MACHINE, VAT_PAID_BACK_ON_OTHER_ACCOUNT);
	private Map<Long, CostType> costTypeMap = null;

	public CostType getCostType(long id) throws Exception {

		if (costTypeMap == null) {
			fill();
		}
		return costTypeMap.get(id);
	}

	@Transactional
	private void fill() throws Exception {
		costTypeMap = new HashMap<>();
		for (CostType costType : costTypeDao.findAll()) {
			if (!COST_TYPES_SKIP.contains(costType)) {
				costTypeMap.put(costType.getId(), costType);
			}
		}
	}

	@Transactional
	public Collection<CostType> getCostTypes() throws Exception {
		if (costTypeMap == null) {
			fill();
		}
		return costTypeMap.values();
	}

}
