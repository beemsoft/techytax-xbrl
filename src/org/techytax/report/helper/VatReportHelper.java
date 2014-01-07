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
package org.techytax.report.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.techytax.cache.CostTypeCache;
import org.techytax.dao.CostDao;
import org.techytax.domain.Cost;
import org.techytax.domain.CostConstants;
import org.techytax.domain.CostType;
import org.techytax.domain.Periode;
import org.techytax.report.domain.VatJournal;
import org.techytax.report.domain.VatJournals;
import org.techytax.report.domain.VatReportData;
import org.techytax.util.DateHelper;

public class VatReportHelper {

	public static VatReportData createReportData(List<Cost> vatCosts) throws Exception {

		VatReportData vatReportData = new VatReportData();
		VatJournals vatJournalsIn = new VatJournals();
		VatJournals vatJournalsOut = new VatJournals();
		Map<String, List<Cost>> journalMapIn = new java.util.HashMap<String, List<Cost>>();
		Map<String, List<Cost>> journalMapOut = new java.util.HashMap<String, List<Cost>>();

		groupCosts(vatCosts, journalMapIn, journalMapOut);

		convertGroupsToVatJournals(vatJournalsIn, journalMapIn);
		convertGroupsToVatJournals(vatJournalsOut, journalMapOut);

		vatReportData.setVatJournalsIn(vatJournalsIn);
		vatReportData.setVatJournalsOut(vatJournalsOut);
		return vatReportData;
	}

	private static void convertGroupsToVatJournals(VatJournals vatJournals, Map<String, List<Cost>> journalMap) {
		Iterator<Entry<String, List<Cost>>> iterator = journalMap.entrySet().iterator();
		BigDecimal totalJournalsAmount = new BigDecimal("0");
		while (iterator.hasNext()) {
			Entry<String, List<Cost>> entry = iterator.next();
			VatJournal vatJournal = createVatJournal(entry);
			vatJournals.addVatJournal(vatJournal);
			totalJournalsAmount = totalJournalsAmount.add(vatJournal.getTotalAmount());
		}
		vatJournals.setVatJournalsTotal(totalJournalsAmount);
	}

	private static VatJournal createVatJournal(Entry<String, List<Cost>> entry) {
		VatJournal vatJournal = new VatJournal();
		Iterator<Cost> costIterator = entry.getValue().iterator();
		BigDecimal totalJournalAmount = calculateTotalJournalAmount(costIterator);
		vatJournal.setTotalAmount(totalJournalAmount);
		vatJournal.setJournalCosts(entry.getValue());
		vatJournal.setCostType(entry.getKey());
		return vatJournal;
	}

	private static BigDecimal calculateTotalJournalAmount(Iterator<Cost> costIterator) {
		BigDecimal totalJournalAmount = new BigDecimal("0");
		while (costIterator.hasNext()) {
			Cost cost = costIterator.next();
			totalJournalAmount = totalJournalAmount.add(cost.getVat());
		}
		return totalJournalAmount;
	}

	private static void groupCosts(List<Cost> vatCosts, Map<String, List<Cost>> journalMapIn, Map<String, List<Cost>> journalMapOut) throws Exception {
		List<Cost> journalCosts;
		for (Cost cost : vatCosts) {
			CostType costType = CostTypeCache.getCostType(cost.getCostTypeId());
			if (costType.isBijschrijving() || costType.getKostenSoortId() == CostConstants.INVOICE_SENT) {
				if (journalMapIn.containsKey(cost.getKostenSoortOmschrijving())) {
					journalCosts = journalMapIn.get(cost.getKostenSoortOmschrijving());
				} else {
					journalCosts = new ArrayList<Cost>();
				}
				journalCosts.add(cost);
				journalMapIn.put(cost.getKostenSoortOmschrijving(), journalCosts);

			} else {

				if (journalMapOut.containsKey(cost.getKostenSoortOmschrijving())) {
					journalCosts = journalMapOut.get(cost.getKostenSoortOmschrijving());
				} else {
					journalCosts = new ArrayList<Cost>();
				}
				journalCosts.add(cost);
				journalMapOut.put(cost.getKostenSoortOmschrijving(), journalCosts);

			}

		}
	}

	public static void main(String[] args) throws Exception {
		Periode period = DateHelper.getLatestVatPeriod();
		CostDao costDao = new CostDao();
		List<Cost> vatCosts = costDao.getKostLijst(DateHelper.getDate(period.getBeginDatum()), DateHelper.getDate(period.getEindDatum()),
				"btwBalans", "");
		createReportData(vatCosts);
	}
}
