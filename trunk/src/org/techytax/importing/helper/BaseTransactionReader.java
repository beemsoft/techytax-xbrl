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
package org.techytax.importing.helper;

import static org.techytax.domain.CostConstants.EXPENSE_CURRENT_ACCOUNT;
import static org.techytax.domain.CostConstants.INCOME_CURRENT_ACCOUNT_IGNORE;
import static org.techytax.domain.CostConstants.SETTLEMENT;
import static org.techytax.domain.CostConstants.SETTLEMENT_DISCOUNT;
import static org.techytax.domain.CostConstants.UITGAVE_DEZE_REKENING_FOUTIEF;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.techytax.cache.CostTypeCache;
import org.techytax.dao.AccountDao;
import org.techytax.dao.CostTypeDao;
import org.techytax.dao.KostmatchDao;
import org.techytax.dao.SettlementDao;
import org.techytax.dao.VatMatchDao;
import org.techytax.domain.AccountType;
import org.techytax.domain.Cost;
import org.techytax.domain.CostConstants;
import org.techytax.domain.CostType;
import org.techytax.domain.Kostmatch;
import org.techytax.domain.SplitMatch;
import org.techytax.domain.VatMatch;
import org.techytax.domain.VatType;
import org.techytax.helper.CostSplitter;
import org.techytax.jpa.dao.SplitMatchDao;

public abstract class BaseTransactionReader implements TransactionReader {

	protected static Vector<String[]> regels = null;
	protected CostTypeDao costTypeDao = new CostTypeDao();
	protected SettlementDao settlementDao = new SettlementDao();
	protected List<Cost> kostLijst = new ArrayList<>();

	public AccountType getAccountType(String fileName, long userId) throws Exception {
		int index = fileName.indexOf("_");
		String accountNumber = fileName.substring(0, index);
		AccountDao accountDao = new AccountDao();
		return accountDao.getAccountType(accountNumber, userId);
	}

	protected Kostmatch findCostMatch(String omschrijving, String userId) throws Exception {
		KostmatchDao kostmatchDao = new KostmatchDao();
		List<Kostmatch> kostmatchList = kostmatchDao.getCostMatchPrivateList(userId);
		Iterator<Kostmatch> iterator = kostmatchList.iterator();
		while (iterator.hasNext()) {
			Kostmatch kostmatch = iterator.next();
			if (omschrijving.toUpperCase().contains(kostmatch.getMatchText().toUpperCase())) {
				return kostmatch;
			}
		}
		kostmatchList = kostmatchDao.getKostmatchLijst();
		iterator = kostmatchList.iterator();
		while (iterator.hasNext()) {
			Kostmatch kostmatch = iterator.next();
			if (omschrijving.toUpperCase().contains(kostmatch.getMatchText().toUpperCase())) {
				return kostmatch;
			}
		}
		return null;
	}

	protected Kostmatch matchKost(Cost kost, String userId) throws Exception {
		long kostensoortId = CostConstants.UNDETERMINED.getId();
		kost.setVat(BigDecimal.ZERO);
		Kostmatch costMatch = findCostMatch(kost.getDescription(), userId);
		if (costMatch != null) {
			kostensoortId = costMatch.getKostenSoortId();
			CostType costType = CostTypeCache.getCostType(kostensoortId);
			handleVat(kost, costMatch, costType);
		}
		kost.setCostType(CostTypeCache.getCostType(kostensoortId));
		return costMatch;
	}

	private void handleVat(Cost kost, Kostmatch costMatch, CostType costType) throws Exception {
		if (costType.isVatDeclarable()) {
			VatMatchDao vatMatchDao = new VatMatchDao();
			VatMatch vatMatch = vatMatchDao.getVatMatch(Long.toString(costMatch.getId()));
			if (vatMatch == null) {
				vatMatch = vatMatchDao.getVatMatchPrivate(Long.toString(costMatch.getId()));
			}
			if (vatMatch != null) {
				if (vatMatch.getVatType() == VatType.HIGH) {
					CostSplitter.splitPercentagFromAmount(kost, VatType.HIGH.getValueAsInteger(kost.getDate()));
				}
				if (vatMatch.getVatType() == VatType.LOW) {
					CostSplitter.splitPercentagFromAmount(kost, 6);
				}
			}
		}
	}

	protected void addCostOrHandleAdminstrativeSplitting(String userId, Cost cost, Kostmatch costMatch) throws Exception {
		if (cost.getCostType().equals(SETTLEMENT) || cost.getCostType().equals(SETTLEMENT_DISCOUNT)) {
			doAdministrativeSplitForSettlement(userId, cost);
		} else if (cost.getCostType().equals(EXPENSE_CURRENT_ACCOUNT)) {
			SplitMatchDao splitMatchDao = new SplitMatchDao();
			SplitMatch splitMatch = splitMatchDao.getSplitMatch(costMatch.getId());
			if (splitMatch != null) {
				doAdministrativeSplitForExpense(cost, splitMatch.getPercentage());
			} else {
				kostLijst.add(cost);
			}
		} else {
			kostLijst.add(cost);
		}
	}

	private void doAdministrativeSplitForExpense(Cost cost, int privatePercentage) throws Exception {
		Cost splitCost = new Cost();
		splitCost.setAmount(cost.getAmount());
		splitCost.setVat(cost.getVat());
		splitCost.setCostType(CostTypeCache.getCostType(UITGAVE_DEZE_REKENING_FOUTIEF.getId()));
		splitCost.setDate(cost.getDate());
		splitCost.setDescription(cost.getDescription());
		CostSplitter.applyPercentage(splitCost, privatePercentage);
		CostSplitter.applyPercentage(cost, 100 - privatePercentage);
		kostLijst.add(cost);
		kostLijst.add(splitCost);
	}

	private void doAdministrativeSplitForSettlement(String userId, Cost cost) throws Exception {
		long percentage = settlementDao.getPercentage();
		Cost splitCost = new Cost();
		splitCost.setAmount(cost.getAmount());
		splitCost.setVat(cost.getVat());
		if (cost.getCostType().equals(SETTLEMENT)) {
			splitCost.setCostType(CostTypeCache.getCostType(UITGAVE_DEZE_REKENING_FOUTIEF.getId()));
		} else {
			splitCost.setCostType(CostTypeCache.getCostType(INCOME_CURRENT_ACCOUNT_IGNORE.getId()));
		}
		splitCost.setDate(cost.getDate());
		splitCost.setDescription(cost.getDescription());
		CostSplitter.applyPercentage(splitCost, (int) (100 - percentage));
		CostSplitter.applyPercentage(cost, (int) percentage);
		kostLijst.add(cost);
		kostLijst.add(splitCost);
	}

	protected static Vector<String[]> getRegels() {
		return regels;
	}

}
