/**
 * Copyright 2011 Hans Beemsterboer
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

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.techytax.domain.Aftrekpost;
import org.techytax.domain.KeyId;
import org.techytax.domain.Kost;
import org.techytax.domain.KostConstanten;

public class BoekDao extends BaseDao {
	
	private void encrypt(Kost cost) {
		cost.setBedrag(decimalEncryptor.encrypt(cost.getBedrag()));	
		cost.setBtw(decimalEncryptor.encrypt(cost.getBtw()));
		cost.setOmschrijving(textEncryptor.encrypt(cost.getOmschrijving()));
	}
	
	private void decrypt(Kost cost) {
		cost.setBedrag(decimalEncryptor.decrypt(cost.getBedrag()));	
		cost.setBtw(decimalEncryptor.decrypt(cost.getBtw()));
		cost.setOmschrijving(textEncryptor.decrypt(cost.getOmschrijving()));
	}	
	
	private void decrypt(Aftrekpost deductableCost) {
		deductableCost.setAftrekbaarBedrag(decimalEncryptor.decrypt(deductableCost.getAftrekbaarBedrag()));
	}		
	
	public void insertKost(Kost kost) throws Exception {
		try {
			encrypt(kost);
			sqlMap.insert("insertKost", kost);
		} catch (SQLException ex) {
			throw ex;
		}
	}

	public void insertPrivateExpense(Kost kost) throws Exception {
		try {
			sqlMap.insert("insertPrivateExpense", kost);
		} catch (SQLException ex) {
			throw ex;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Kost> getKostLijst(String userId) throws Exception {
		try {
			List<Kost> costs = sqlMap.queryForList("getKostLijst", userId);
			for (Kost cost: costs) {
				decrypt(cost);
			}
			return costs;
		} catch (SQLException ex) {
			throw ex;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Kost> getPrivateExpenses() throws Exception {
		try {
			return sqlMap.queryForList("getPrivateExpenses", null);
		} catch (SQLException ex) {
			throw ex;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Kost> getKostLijst(String beginDatum, String eindDatum, String balansSoort, String userId) throws Exception {
		Map<String, String> map = createMap(beginDatum, eindDatum, userId);
		List<Kost> costs = null;
 		try {
			if (balansSoort.equals("alles")) {
				costs = sqlMap.queryForList("getCompleteKostLijst", map);
			} else if (balansSoort.equals("btwBalans")) {
				costs = sqlMap.queryForList("getBtwBalansLijst", map);
			} else if (balansSoort.equals("rekeningBalans")) {
				costs = sqlMap.queryForList("getRekeningBalansLijst", map);
			} else if (balansSoort.equals("kostenBalans")) {
				costs = sqlMap.queryForList("getKostenBalansLijst", map);
			} else if (balansSoort.equals("reiskostenBalans")) {
				costs = sqlMap.queryForList("getReisKostenBalansLijst", map);
			} else if (balansSoort.equals("investeringen")) {
				map.put("investmentMinimum", Integer.toString(KostConstanten.INVESTMENT_MINIMUM_AMOUNT));
				costs = sqlMap.queryForList("getInvesteringenLijst", map);
			} else if (balansSoort.equals("afschrijvingen")) {
				costs = sqlMap.queryForList("getAfschrijvingenLijst", map);
			} else if (balansSoort.equals("private")) {
				return sqlMap.queryForList("getPrivateExpenses", map);
			} else if (balansSoort.equals("tax")) {
				costs = sqlMap.queryForList("getTaxList", map);
			} else if (balansSoort.equals("audit")) {
				costs = sqlMap.queryForList("getAuditList", map);
			}
			for (Kost cost: costs) {
				decrypt(cost);
			}
			return costs;
		} catch (SQLException ex) {
			throw ex;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Aftrekpost> getDeductableCosts(String beginDatum, String eindDatum, String userId) throws Exception {
		Map<String, String> map = createMap(beginDatum, eindDatum, userId);
		try {
			List<Aftrekpost> deductableCostList = sqlMap.queryForList("getDeductableCostList", map);
			Collections.sort(deductableCostList);
			long latestCostType = 0;
			Aftrekpost groupedCost = null;
			BigDecimal totalDeductableCost = new BigDecimal("0");
			List<Aftrekpost> groupedDeducatableCostList = new ArrayList<Aftrekpost>();
			for (Aftrekpost deductableCost : deductableCostList) {
				decrypt(deductableCost);
				if (deductableCost.getKostenSoortId() != latestCostType) {
					if (groupedCost != null) {
						groupedCost.setAftrekbaarBedrag(totalDeductableCost);
						groupedCost.setKostenSoortId(latestCostType);
						groupedDeducatableCostList.add(groupedCost);
					}
					latestCostType = deductableCost.getKostenSoortId();
					groupedCost = new Aftrekpost();
					totalDeductableCost = new BigDecimal("0");
				}
				totalDeductableCost = totalDeductableCost.add(deductableCost.getAftrekbaarBedrag());
			}
			if (groupedCost != null) {
				groupedCost.setAftrekbaarBedrag(totalDeductableCost);
				groupedCost.setKostenSoortId(latestCostType);
				groupedDeducatableCostList.add(groupedCost);
			}
			return groupedDeducatableCostList;
		} catch (SQLException ex) {
			throw ex;
		}
	}

	@Deprecated
	public Integer getBelastingKosten(String beginDatum, String eindDatum, String userId) throws Exception {
		Map<String, String> map = createMap(beginDatum, eindDatum, userId);
		try {
			return (Integer) sqlMap.queryForObject("getBelastingKosten", map);
		} catch (SQLException ex) {
			throw ex;
		}
	}

	@Deprecated
	public Integer getBelastingTeruggave(String beginDatum, String eindDatum, String userId) throws Exception {
		Map<String, String> map = createMap(beginDatum, eindDatum, userId);
		try {
			return (Integer) sqlMap.queryForObject("getBelastingTeruggave", map);
		} catch (SQLException ex) {
			throw ex;
		}
	}

	public void updateKost(Kost kost) throws Exception {
		try {
			encrypt(kost);
			sqlMap.insert("updateKost", kost);
		} catch (SQLException ex) {
			throw ex;
		}
	}

	public Kost getKost(String id, long userId) throws Exception {
		KeyId key = new KeyId();
		key.setId(Long.parseLong(id));
		key.setUserId(userId);
		try {
			Kost cost = (Kost) sqlMap.queryForObject("getKost", key);
			decrypt(cost);
			return cost;
		} catch (SQLException ex) {
			throw ex;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Kost> getVatCorrectionDepreciation(String beginDatum, String eindDatum, String userId) throws Exception {
		Map<String, String> map = createMap(beginDatum, eindDatum, userId);
		try {
			List<Kost> costs = sqlMap.queryForList("getVatCorrectionDepreciation", map);
			for (Kost cost: costs) {
				decrypt(cost);
			}
			return costs;
		} catch (SQLException ex) {
			throw ex;
		}
	}

	@Deprecated
	public Integer getVatCorrectionPrivate(String beginDatum, String eindDatum, String userId) throws Exception {
		Map<String, String> map = createMap(beginDatum, eindDatum, userId);
		try {
			return (Integer) sqlMap.queryForObject("getVatCorrectionPrivate", map);
		} catch (SQLException ex) {
			throw ex;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Kost> getTaxList(String beginDatum, String eindDatum, String userId) throws Exception {
		Map<String, String> map = createMap(beginDatum, eindDatum, userId);
		try {
			List<Kost> costs = sqlMap.queryForList("getTaxList", map);
			for (Kost cost: costs) {
				decrypt(cost);
			}
			return costs;
		} catch (SQLException ex) {
			throw ex;
		}
	}	
	
	@SuppressWarnings("unchecked")
	public List<Kost> getAuditList(String beginDatum, String eindDatum, String userId) throws Exception {
		Map<String, String> map = createMap(beginDatum, eindDatum, userId);
		try {
			List<Kost> costs = sqlMap.queryForList("getAuditList", map);
			for (Kost cost: costs) {
				decrypt(cost);
			}
			return costs;
		} catch (SQLException ex) {
			throw ex;
		}
	}	
	
	@SuppressWarnings("unchecked")
	public List<Kost> getAllCosts() throws Exception {
		try {
			return sqlMap.queryForList("getAllCosts", null);
		} catch (SQLException ex) {
			throw ex;
		}
	}	

}
