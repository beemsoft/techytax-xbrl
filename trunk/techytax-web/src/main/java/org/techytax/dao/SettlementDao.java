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
package org.techytax.dao;

import org.springframework.stereotype.Component;
import org.techytax.domain.Settlement;
import org.techytax.jpa.dao.GenericDao;

@Component
public class SettlementDao extends GenericDao<Settlement> {

	public long getPercentage() throws Exception {
		Settlement settlement = getSettlement();
		long percentage = 0;
		if (settlement != null && settlement.getNofSquareMetersPrivate() != 0) {
			percentage = Math.round((double)settlement.getNofSquareMetersBusiness()/settlement.getNofSquareMetersPrivate() * 100);
		}
		return percentage;
	}
	
	public Settlement getSettlement() throws Exception {
		return findEntityByNamedQuery(Settlement.GET);		
	}	

}
