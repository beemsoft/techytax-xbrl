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

import static org.techytax.domain.CostConstants.EXPENSE_OTHER_ACCOUNT_IGNORE;
import static org.techytax.domain.CostConstants.INVOICE_PAID;
import static org.techytax.domain.CostConstants.INVOICE_SENT;
import static org.techytax.domain.CostConstants.UITGAVE_DEZE_REKENING_FOUTIEF;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.techytax.cache.CostCache;
import org.techytax.cache.CostTypeCache;
import org.techytax.domain.Activum;
import org.techytax.domain.BalanceType;
import org.techytax.domain.Cost;
import org.techytax.domain.CostType;
import org.techytax.domain.User;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.zkplus.jpa.JpaUtil;

public class CostDao extends BaseDao {

	private User user = UserCredentialManager.getUser();
	private CostCache costCache = new CostCache();

	public void encrypt(Cost cost) {
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
				throw e;
			}
		}
		if (cost.getVat() != null && cost.getVat().doubleValue() != 0) {
			try {
				cost.setVat(decimalEncryptor.decrypt(cost.getVat()));
			} catch (EncryptionOperationNotPossibleException e) {
				System.out.println("Could not decrypt: " + cost.getDescription());
				throw e;
			}
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
		CostType costType = CostTypeCache.getCostType(originalCost.getCostTypeId());
		if (costType.isBalansMeetellen()) {
			splitCost.setCostType(UITGAVE_DEZE_REKENING_FOUTIEF);
		} else {
			splitCost.setCostType(EXPENSE_OTHER_ACCOUNT_IGNORE);
		}
		splitCost.roundValues();
		encrypt(splitCost);
		sqlMap.insert("insertKost", splitCost);
		decrypt(splitCost);
	}

	public List<Cost> getKostLijst(Date beginDatum, Date eindDatum, String balansSoort) throws Exception {
		List<Cost> costs = null;
		if (balansSoort != null) {
			if (balansSoort.equals("alles")) {
				costs = getCostsInPeriod(beginDatum, eindDatum);
			} else if (balansSoort.equals("btwBalans")) {
				costs = getVatCostsInPeriod(beginDatum, eindDatum);
			} else if (balansSoort.equals("rekeningBalans")) {
				costs = getCostsOnBusinessAccountInPeriod(beginDatum, eindDatum);
			} else if (balansSoort.equals("kostenBalans")) {
				throw new IllegalStateException("Migrating...");
			} else if (balansSoort.equals("audit")) {
				throw new IllegalStateException("Migrating...");
			}
		}
		return costs;
	}

	private List<Cost> getCostsInPeriod(Date beginDatum, Date eindDatum) {
		List<Cost> costs;
		TypedQuery<Cost> query = JpaUtil.getEntityManager().createQuery("SELECT c FROM org.techytax.domain.Cost c WHERE c.user = :user AND c.date >= :beginDate AND c.date <= :endDate order by c.date asc", Cost.class);
		query.setParameter("user", user);
		query.setParameter("beginDate", beginDatum);
		query.setParameter("endDate", eindDatum);
		costs = query.getResultList();
		return costs;
	}

	private List<Cost> getVatCostsInPeriod(Date beginDatum, Date eindDatum) {
		List<Cost> costs = getCostsInPeriod(beginDatum, eindDatum);
		List<Cost> filteredCosts = new ArrayList<Cost>();
		for (Cost cost : costs) {
			if (cost.getCostType().isVatDeclarable()) {
				filteredCosts.add(cost);
			}
		}
		return filteredCosts;
	}

	private List<Cost> getCostsOnBusinessAccountInPeriod(Date beginDatum, Date eindDatum) {
		List<Cost> costs = getCostsInPeriod(beginDatum, eindDatum);
		List<Cost> filteredCosts = new ArrayList<Cost>();
		for (Cost cost : costs) {
			if (cost.getCostType().isBalansMeetellen()) {
				filteredCosts.add(cost);
			}
		}
		return filteredCosts;
	}

	public void updateKost(Cost kost) throws Exception {
		kost.roundValues();
		encrypt(kost);
		sqlMap.insert("updateKost", kost);
		decrypt(kost);
	}

	public Cost getKost(Cost cost) throws Exception {
		TypedQuery<Cost> query = JpaUtil.getEntityManager().createQuery("SELECT c FROM org.techytax.domain.Cost c WHERE c.id = :id AND c.user = :user", Cost.class);
		query.setParameter("user", user);
		query.setParameter("id", cost.getId());
		Cost result = null;
		try {
			result = query.getSingleResult();
		} catch (NoResultException e) {
			// ok
		}
		return result;
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

	public List<Activum> getNewActiva(BalanceType balanceType, Date beginDate, Date endDate) {
		TypedQuery<Activum> query = JpaUtil
				.getEntityManager()
				.createQuery(
						"SELECT act FROM org.techytax.domain.Activum act WHERE act.balanceType = :balanceType AND act.cost.date >= :beginDate AND act.cost.date <= :endDate AND act.endDate = null AND act.user = :user",
						Activum.class);
		query.setParameter("user", user);
		query.setParameter("beginDate", beginDate);
		query.setParameter("endDate", endDate);
		query.setParameter("balanceType", balanceType);
		List<Activum> result = query.getResultList();
		return result;
	}

	public BigDecimal getInvoiceBalance(Date beginDatum, Date eindDatum) throws Exception {
		List<Cost> costs = getCostsInPeriod(beginDatum, eindDatum);
		BigDecimal invoiceBalance = BigDecimal.ZERO;
		for (Cost cost : costs) {
			if (cost.getCostType().equals(INVOICE_SENT)) {
				invoiceBalance = invoiceBalance.add(cost.getAmount()).add(cost.getVat());
			} else if (cost.getCostType().equals(INVOICE_PAID)) {
				invoiceBalance = invoiceBalance.subtract(cost.getAmount()).subtract(cost.getVat());
			}
		}
		return invoiceBalance;
	}

	public List<Cost> getInvoices(Date beginDatum, Date eindDatum) throws Exception {
		List<Cost> costs = getCostsInPeriod(beginDatum, eindDatum);
		List<Cost> filteredCosts = new ArrayList<Cost>();
		for (Cost cost : costs) {
			if (cost.getCostType().equals(INVOICE_SENT) || cost.getCostType().equals(INVOICE_PAID)) {
				filteredCosts.add(cost);
			}
		}
		return filteredCosts;
	}

	@SuppressWarnings("unchecked")
	public BigDecimal getVatDebtFromPreviousYear(String beginDatum, String eindDatum, String userId) throws Exception {
		Map<String, String> map = createMap(beginDatum, eindDatum, userId);
		List<Cost> costs = sqlMap.queryForList("getVatDebt", map);
		BigDecimal vatBalance = BigDecimal.ZERO;
		for (Cost cost : costs) {
			decrypt(cost);
			vatBalance = vatBalance.add(cost.getVat());
		}
		return vatBalance;
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
