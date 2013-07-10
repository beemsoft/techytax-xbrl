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

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.techytax.dao.AccountDao;
import org.techytax.dao.KostensoortDao;
import org.techytax.dao.KostmatchDao;
import org.techytax.dao.VatMatchDao;
import org.techytax.domain.AccountType;
import org.techytax.domain.Cost;
import org.techytax.domain.CostConstants;
import org.techytax.domain.Kostensoort;
import org.techytax.domain.Kostmatch;
import org.techytax.domain.VatMatch;
import org.techytax.domain.VatType;
import org.techytax.helper.CostSplitter;

public abstract class BaseTransactionReader implements TransactionReader {

	protected static Vector<String[]> regels = null;
	protected static List<Kostensoort> kostensoortList = null;

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

	protected static String getKostOmschrijving(long kostensoortId) {
		Iterator<Kostensoort> iter = kostensoortList.iterator();
		while (iter.hasNext()) {
			Kostensoort kostensoort = iter.next();
			if (kostensoort.getKostenSoortId() == kostensoortId) {
				return kostensoort.getOmschrijving();
			}
		}
		return "costtype.none";
	}

	protected Cost matchKost(Cost kost, String userId) throws Exception {
		long kostensoortId = CostConstants.UNDETERMINED;
		kost.setVat(new BigDecimal("0"));
		Kostmatch costMatch = findCostMatch(kost.getDescription(), userId);
		if (costMatch != null) {
			kostensoortId = costMatch.getKostenSoortId();
			KostensoortDao kostensoortDao = new KostensoortDao();
			Kostensoort costType = kostensoortDao.getKostensoort(Long.toString(kostensoortId));
			if (costType.isBtwVerrekenbaar()) {
				VatMatchDao vatMatchDao = new VatMatchDao();
				VatMatch vatMatch = vatMatchDao.getVatMatch(Long.toString(costMatch.getId()));
				if (vatMatch == null) {
					vatMatch = vatMatchDao.getVatMatchPrivate(Long.toString(costMatch.getId()));
				}
				if (vatMatch != null) {
					if (vatMatch.getVatType() == VatType.HIGH) {
						CostSplitter.splitPercentagFromAmount(kost, (int) (100 * VatType.HIGH.getValue(kost.getDate())));
					}
					if (vatMatch.getVatType() == VatType.LOW) {
						CostSplitter.splitPercentagFromAmount(kost, 6);
					}
				}
			}
		}
		kost.setCostTypeId(kostensoortId);
		kost.setKostenSoortOmschrijving(getKostOmschrijving(kostensoortId));
		return kost;
	}

	protected static Vector<String[]> getRegels() {
		return regels;
	}

}
