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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.techytax.domain.Activum;
import org.techytax.domain.Cost;
import org.techytax.domain.DeductableCostGroup;
import org.techytax.domain.KeyId;
import org.techytax.domain.CostConstants;
import org.techytax.domain.Kostensoort;

public class BoekDao extends BaseDao {
	
	private KostensoortDao kostensoortDao = new KostensoortDao();
	
	private void encrypt(Cost cost) {
		if (cost.getAmount() != null && cost.getAmount().doubleValue() != 0) {
			cost.setAmount(decimalEncryptor.encrypt(cost.getAmount()));
		}
		if (cost.getVat() != null && cost.getVat().doubleValue() != 0) {
			cost.setVat(decimalEncryptor.encrypt(cost.getVat()));
		}
		if (cost.getDescription() != null && StringUtils.isNotEmpty(cost.getDescription().trim())) {
			cost.setDescription(textEncryptor.encrypt(cost.getDescription()));
		}
	}

	public void decrypt(Cost cost) {
		if (cost.getDescription() != null && StringUtils.isNotEmpty(cost.getDescription().trim())) {
			cost.setDescription(textEncryptor.decrypt(cost.getDescription()));
		}		
		if (cost.getAmount() != null && cost.getAmount().doubleValue() != 0) {
			try {
				cost.setAmount(decimalEncryptor.decrypt(cost.getAmount()));
			} catch (EncryptionOperationNotPossibleException e) {
				e.printStackTrace();
				System.out.println("Could not decrypt: " + cost.getDescription());
			}
		}
		if (cost.getVat() != null && cost.getVat().doubleValue() != 0) {
			try {
				cost.setVat(decimalEncryptor.decrypt(cost.getVat()));
			} catch (EncryptionOperationNotPossibleException e) {
				System.out.println("Could not decrypt: " + cost.getDescription());
			}				
		}
	}

	private void decrypt(DeductableCostGroup deductableCost) {
		if (deductableCost.getAftrekbaarBedrag() != null && deductableCost.getAftrekbaarBedrag().doubleValue() != 0) {		
			deductableCost.setAftrekbaarBedrag(decimalEncryptor.decrypt(deductableCost.getAftrekbaarBedrag()));
		}
	}

	public void insertKost(Cost kost) throws Exception {
		kost.roundValues();
		encrypt(kost);
		sqlMap.insert("insertKost", kost);
		decrypt(kost);
	}
	
	public void insertSplitCost(Cost originalCost, Cost splitCost) throws Exception {
		splitCost.setDate(originalCost.getDate());
		Kostensoort costType = kostensoortDao.getKostensoort(Long.toString(originalCost.getCostTypeId()));
		if (costType.isBalansMeetellen()) {
			splitCost.setCostTypeId(CostConstants.UITGAVE_DEZE_REKENING_FOUTIEF);
		} else {
			splitCost.setCostTypeId(CostConstants.EXPENSE_OTHER_ACCOUNT_IGNORE);
		}
		splitCost.roundValues();
		encrypt(splitCost);
		sqlMap.insert("insertKost", splitCost);
		decrypt(splitCost);
	}	

	public void insertPrivateExpense(Cost kost) throws Exception {
		kost.setAmount(BigDecimal.valueOf(kost.getAmount().doubleValue()).setScale(2));
		kost.setVat(kost.getVat().setScale(2));		
		sqlMap.insert("insertPrivateExpense", kost);
	}

	@SuppressWarnings("unchecked")
	public List<Cost> getKostLijst(String userId) throws Exception {
		List<Cost> costs = sqlMap.queryForList("getKostLijst", userId);
		for (Cost cost : costs) {
			decrypt(cost);
		}
		return costs;
	}

	@SuppressWarnings("unchecked")
	public List<Cost> getPrivateExpenses() throws Exception {
		return sqlMap.queryForList("getPrivateExpenses", null);
	}

	@SuppressWarnings("unchecked")
	public List<Cost> getKostLijst(String beginDatum, String eindDatum, String balansSoort, String userId) throws Exception {
		Map<String, String> map = createMap(beginDatum, eindDatum, userId);
		List<Cost> costs = null;
		if (balansSoort != null) {
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
				return getInvestments(beginDatum, eindDatum, userId);
			} else if (balansSoort.equals("afschrijvingen")) {
				costs = sqlMap.queryForList("getAfschrijvingenLijst", map);
			} else if (balansSoort.equals("private")) {
				return sqlMap.queryForList("getPrivateExpenses", map);
			} else if (balansSoort.equals("tax")) {
				costs = sqlMap.queryForList("getTaxList", map);
			} else if (balansSoort.equals("audit")) {
				costs = sqlMap.queryForList("getAuditList", map);
			}
		}
		if (costs != null) {
			for (Cost cost : costs) {
				decrypt(cost);
			}
		}
		return costs;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cost> getCostListCurrentAccount(String beginDatum, String eindDatum, String userId) throws Exception {
		Map<String, String> map = createMap(beginDatum, eindDatum, userId);
		List<Cost> costs = sqlMap.queryForList("getKostenBalansCurrentAccountLijst", map);
		if (costs != null) {
			for (Cost cost : costs) {
				decrypt(cost);
			}
		}
		return costs;
	}	

	@SuppressWarnings("unchecked")
	public List<DeductableCostGroup> getDeductableCosts(String beginDatum, String eindDatum, String userId) throws Exception {
		Map<String, String> map = createMap(beginDatum, eindDatum, userId);
		List<DeductableCostGroup> deductableCostList = sqlMap.queryForList("getDeductableCostList", map);
		Collections.sort(deductableCostList);
		long latestCostType = 0;
		DeductableCostGroup groupedCost = null;
		BigDecimal totalDeductableCost = new BigDecimal("0");
		List<DeductableCostGroup> groupedDeducatableCostList = new ArrayList<DeductableCostGroup>();
		for (DeductableCostGroup deductableCost : deductableCostList) {
			decrypt(deductableCost);
			if (deductableCost.getKostenSoortId() != latestCostType) {
				if (groupedCost != null) {
					groupedCost.setAftrekbaarBedrag(totalDeductableCost);
					groupedCost.setKostenSoortId(latestCostType);
					groupedDeducatableCostList.add(groupedCost);
				}
				latestCostType = deductableCost.getKostenSoortId();
				groupedCost = new DeductableCostGroup();
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
	}

	public void updateKost(Cost kost) throws Exception {
		kost.roundValues();	
		encrypt(kost);
		sqlMap.insert("updateKost", kost);
		decrypt(kost);
	}
	
	public Cost getKost(String id, long userId) throws Exception {
		KeyId key = new KeyId();
		key.setId(Long.parseLong(id));
		key.setUserId(userId);
		Cost cost = (Cost) sqlMap.queryForObject("getKost", key);
		if (cost != null) {
			decrypt(cost);
		}
		return cost;
	}

	@Deprecated
	@SuppressWarnings("unchecked")
	public List<Cost> getVatCorrectionDepreciation(String beginDatum, String eindDatum, String userId) throws Exception {
		Map<String, String> map = createMap(beginDatum, eindDatum, userId);
		List<Cost> costs = sqlMap.queryForList("getVatCorrectionDepreciation", map);
		for (Cost cost : costs) {
			decrypt(cost);
		}
		return costs;
	}

	@SuppressWarnings("unchecked")
	public List<Cost> getTaxList(String beginDatum, String eindDatum, String userId) throws Exception {
		Map<String, String> map = createMap(beginDatum, eindDatum, userId);
		List<Cost> costs = sqlMap.queryForList("getTaxList", map);
		for (Cost cost : costs) {
			decrypt(cost);
		}
		return costs;
	}

	@SuppressWarnings("unchecked")
	public List<Cost> getAuditList(String beginDatum, String eindDatum, String userId) throws Exception {
		Map<String, String> map = createMap(beginDatum, eindDatum, userId);
		List<Cost> costs = sqlMap.queryForList("getAuditList", map);
		for (Cost cost : costs) {
			decrypt(cost);
		}
		return costs;
	}

	@SuppressWarnings("unchecked")
	public List<Cost> getInvestments(String beginDatum, String eindDatum, String userId) throws Exception {
		Map<String, String> map = createMap(beginDatum, eindDatum, userId);
		List<Cost> costs = sqlMap.queryForList("getInvesteringenLijst", map);
		List<Cost> filteredCosts = new ArrayList<Cost>();
		for (Cost cost : costs) {
			decrypt(cost);
			if (cost.getAmount().compareTo(new BigDecimal(CostConstants.INVESTMENT_MINIMUM_AMOUNT)) == 1) {
				filteredCosts.add(cost);
			}
		}
		return filteredCosts;
	}

	@SuppressWarnings("unchecked")
	public List<Cost> getAllCosts() throws Exception {
		return sqlMap.queryForList("getAllCosts", null);
	}

	@SuppressWarnings("unchecked")
	public List<Cost> searchCosts(String searchTerm, String userId) throws Exception {
		List<Cost> costs = sqlMap.queryForList("getAllCostsForUser", userId);
		List<Cost> filteredCosts = new ArrayList<Cost>();
		for (Cost cost : costs) {
			decrypt(cost);
			if (cost.getDescription().toUpperCase().contains(searchTerm.toUpperCase())) {
				filteredCosts.add(cost);
			}
		}
		return filteredCosts;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cost> getRepurchases(String beginDatum, String eindDatum, String userId) throws Exception {
		Map<String, String> map = createMap(beginDatum, eindDatum, userId);
		List<Cost> costs = sqlMap.queryForList("getRepurchases", map);
		for (Cost cost : costs) {
			decrypt(cost);
		}
		return costs;
	}
	
	@SuppressWarnings("unchecked")
	public BigDecimal getInterest(String beginDatum, String eindDatum, String userId) throws Exception {
		Map<String, String> map = createMap(beginDatum, eindDatum, userId);
		List<Cost> costs = sqlMap.queryForList("getInterest", map);
		BigDecimal interest = new BigDecimal("0");
		for (Cost cost : costs) {
			decrypt(cost);
			interest = interest.add(cost.getAmount());
		}
		return interest;
	}
	
	@SuppressWarnings("unchecked")
	public  BigInteger getTotalCostForActivumThisYear(Activum activum) throws Exception {
		BigInteger totalCost = new BigInteger("0");
		List<Cost> costs = sqlMap.queryForList("getCostListForActivum", activum);
		for (Cost cost : costs) {
			decrypt(cost);
			totalCost = totalCost.add(cost.getAmount().toBigInteger());
		}
		return totalCost;
	}
	
	@SuppressWarnings("unchecked")
	public BigDecimal getInvoiceBalance(String beginDatum, String eindDatum, String userId) throws Exception {
		Map<String, String> map = createMap(beginDatum, eindDatum, userId);
		List<Cost> costs = sqlMap.queryForList("getInvoices", map);
		BigDecimal invoiceBalance = new BigDecimal("0");
		for (Cost cost : costs) {
			decrypt(cost);
			if (cost.getCostTypeId() == CostConstants.INVOICE_SENT) {
				invoiceBalance = invoiceBalance.add(cost.getAmount()).add(cost.getVat());	
			} else if (cost.getCostTypeId() == CostConstants.INVOICE_PAID) {
				invoiceBalance = invoiceBalance.subtract(cost.getAmount()).subtract(cost.getVat());
			}			
		}
		return invoiceBalance;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cost> getInvoices(String beginDatum, String eindDatum, String userId) throws Exception {
		Map<String, String> map = createMap(beginDatum, eindDatum, userId);
		List<Cost> costs = sqlMap.queryForList("getInvoices", map);
		for (Cost cost : costs) {
			decrypt(cost);
		}
		return costs;
	}	
	
	@SuppressWarnings("unchecked")
	public BigDecimal getVatDebt(String beginDatum, String eindDatum, String userId) throws Exception {
		Map<String, String> map = createMap(beginDatum, eindDatum, userId);
		List<Cost> costs = sqlMap.queryForList("getVatDebt", map);
		BigDecimal vatBalance = new BigDecimal("0");
		for (Cost cost : costs) {
			decrypt(cost);
			vatBalance = vatBalance.add(cost.getVat());	
		}
		return vatBalance;
	}
	
	@SuppressWarnings("unchecked")
	public BigDecimal getCostsWithPrivateMoney(String beginDatum, String eindDatum, String userId) throws Exception {
		Map<String, String> map = createMap(beginDatum, eindDatum, userId);
		List<Cost> costs = sqlMap.queryForList("getCostsWithPrivateMoney", map);
		BigDecimal costsWithPrivateMoney = new BigDecimal("0");
		for (Cost cost : costs) {
			decrypt(cost);
			costsWithPrivateMoney = costsWithPrivateMoney.add(cost.getAmount()).add(cost.getVat());	
		}
		return costsWithPrivateMoney;
	}
	
	public void deleteCost(Cost cost) throws Exception {
		sqlMap.delete("deleteCost", cost);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cost> getVatCostsWithPrivateMoney(String beginDatum, String eindDatum, String userId) throws Exception {
		Map<String, String> map = createMap(beginDatum, eindDatum, userId);
		List<Cost> costs = sqlMap.queryForList("getVatCostsWithPrivateMoney", map);
		for (Cost cost : costs) {
			decrypt(cost);
		}
		return costs;
	}
	
	@SuppressWarnings("unchecked")
	public BigDecimal getCostsCurrentAccountIgnore(String beginDatum, String eindDatum, String userId) throws Exception {
		Map<String, String> map = createMap(beginDatum, eindDatum, userId);
		BigDecimal costsIgnoreBalance = new BigDecimal("0");
		List<Cost> costs = sqlMap.queryForList("getCostsCurrentAccountIgnore", map);
		for (Cost cost : costs) {
			decrypt(cost);
			costsIgnoreBalance = costsIgnoreBalance.add(cost.getAmount()).add(cost.getVat());
		}
		return costsIgnoreBalance;
	}	

}
