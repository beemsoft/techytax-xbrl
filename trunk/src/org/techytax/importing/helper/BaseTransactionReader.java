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
import org.techytax.domain.AccountType;
import org.techytax.domain.Cost;
import org.techytax.domain.CostConstants;
import org.techytax.domain.CostMatchParent;
import org.techytax.domain.CostType;
import org.techytax.domain.Kostmatch;
import org.techytax.domain.PrivateCostMatch;
import org.techytax.domain.SplitMatch;
import org.techytax.domain.VatMatchParent;
import org.techytax.domain.VatType;
import org.techytax.helper.CostSplitter;

public abstract class BaseTransactionReader implements TransactionReader {

	protected static Vector<String[]> regels = null;
	protected CostTypeDao costTypeDao = new CostTypeDao();
	protected SettlementDao settlementDao = new SettlementDao();
	protected List<Cost> kostLijst = new ArrayList<>();

	public AccountType getAccountType(String fileName) throws Exception {
		int index = fileName.indexOf("_");
		String accountNumber = fileName.substring(0, index);
		AccountDao accountDao = new AccountDao();
		return accountDao.getAccountType(accountNumber);
	}

	protected CostMatchParent findCostMatch(String omschrijving) throws Exception {
		KostmatchDao kostmatchDao = new KostmatchDao();
		List<PrivateCostMatch> privateCostMatchList = kostmatchDao.getCostMatchPrivateList();
		Iterator<PrivateCostMatch> iterator = privateCostMatchList.iterator();
		while (iterator.hasNext()) {
			PrivateCostMatch kostmatch = iterator.next();
			if (omschrijving.toUpperCase().contains(kostmatch.getMatchText().toUpperCase())) {
				return kostmatch;
			}
		}
		List<Kostmatch> kostmatchList = kostmatchDao.getKostmatchLijst();
		Iterator<Kostmatch> iterator2 = kostmatchList.iterator();
		while (iterator2.hasNext()) {
			Kostmatch kostmatch = iterator2.next();
			if (omschrijving.toUpperCase().contains(kostmatch.getMatchText().toUpperCase())) {
				return kostmatch;
			}
		}
		return null;
	}

	protected CostMatchParent matchKost(Cost kost) throws Exception {
		CostType kostensoort = CostConstants.UNDETERMINED;
		kost.setVat(BigDecimal.ZERO);
		CostMatchParent costMatch = findCostMatch(kost.getDescription());
		if (costMatch != null) {
			kostensoort = costMatch.getKostenSoort();
			handleVat(kost, costMatch, kostensoort);
		}
		kost.setCostType(kostensoort);
		return costMatch;
	}

	private void handleVat(Cost kost, CostMatchParent costMatch, CostType costType) throws Exception {
		if (costType.isVatDeclarable()) {
			VatMatchParent vatMatch = costMatch.getVatMatch();
			if (vatMatch == null && costMatch instanceof PrivateCostMatch) {
				PrivateCostMatch privateCostMatch = (PrivateCostMatch)costMatch;
				vatMatch = privateCostMatch.getVatMatchPrivate();
			}
			if (vatMatch != null) {
				if (vatMatch.getVatType() == VatType.HIGH) {
					CostSplitter.splitPercentagFromAmount(kost, VatType.HIGH.getValueAsInteger(kost.getDate()));
				}
				if (vatMatch.getVatType() == VatType.LOW) {
					CostSplitter.splitPercentagFromAmount(kost, VatType.LOW.getValueAsInteger(kost.getDate()));
				}
			}
		}
	}

	protected void addCostOrHandleAdminstrativeSplitting(Cost cost, CostMatchParent costMatch) throws Exception {
		if (cost.getCostType().equals(SETTLEMENT) || cost.getCostType().equals(SETTLEMENT_DISCOUNT)) {
			doAdministrativeSplitForSettlement(cost);
		} else if (cost.getCostType().equals(EXPENSE_CURRENT_ACCOUNT)) {
			SplitMatch splitMatch = null;
			if (costMatch instanceof PrivateCostMatch) {
				PrivateCostMatch privateCostMatch = (PrivateCostMatch)costMatch;
				splitMatch = privateCostMatch.getSplitMatch();
			}
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

	private void doAdministrativeSplitForSettlement(Cost cost) throws Exception {
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
