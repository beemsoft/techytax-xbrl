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
package org.techytax.dao;

import java.util.List;

import org.techytax.domain.CostType;

public class CostTypeDao extends BaseDao {

	@SuppressWarnings("unchecked")
	public List<CostType> getKostensoortLijst() throws Exception {
		return sqlMap.queryForList("getKostensoortLijst", null);
	}
	
	@SuppressWarnings("unchecked")
	public List<CostType> getCostTypesForAccount() throws Exception {
		return sqlMap.queryForList("getCostTypesForAccount", null);
	}	

	@SuppressWarnings("unchecked")
	public List<CostType> getCostTypesForVatCostsWithPrivateMoney() throws Exception {
		return sqlMap.queryForList("getCostTypesForVatCostsWithPrivateMoney", null);
	}	

}
