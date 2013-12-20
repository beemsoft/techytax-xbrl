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
package org.techytax.helper;

import static org.techytax.helper.ActivaHelper.handleActiva;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.techytax.cache.CostCache;
import org.techytax.dao.BoekDao;
import org.techytax.dao.BookValueDao;
import org.techytax.dao.FiscalDao;
import org.techytax.domain.Activum;
import org.techytax.domain.BalanceType;
import org.techytax.domain.Balans;
import org.techytax.domain.BookValue;
import org.techytax.domain.Cost;
import org.techytax.domain.CostConstants;
import org.techytax.domain.DeductableCostGroup;
import org.techytax.domain.FiscalOverview;
import org.techytax.domain.KeyYear;
import org.techytax.domain.Passivum;
import org.techytax.domain.Periode;
import org.techytax.domain.PrepaidTax;
import org.techytax.domain.PrivatWithdrawal;
import org.techytax.props.PropsFactory;
import org.techytax.util.DateHelper;

public class FiscalOverviewHelper {

	private FiscalDao fiscaalDao = new FiscalDao();
	private BookValueDao boekwaardeDao = new BookValueDao();
	private CostCache costCache = new CostCache();

	public FiscalOverview createFiscalOverview(String beginDatum, String eindDatum, long userId, Locale locale) throws Exception {

		// Load properties
		Properties props = PropsFactory.loadProperties();
		costCache.setBeginDatum(beginDatum);
		costCache.setEindDatum(eindDatum);
		FiscalOverview overview = new FiscalOverview();
		PrivatWithdrawal privatWithdrawal = new PrivatWithdrawal();

		Date datum = DateHelper.stringToDate(beginDatum);
		int bookYear = DateHelper.getYear(datum);
		int currentBookYear = DateHelper.getYear(new Date()) - 1;

		Balans btwBalans = BalanceCalculator.calculateBtwBalance(costCache.getCosts(), false);
		List<DeductableCostGroup> deductableCosts = costCache.getDeductableCosts();
		overview.setJaar(bookYear);

		handleProfitAndLoss(userId, bookYear, overview, privatWithdrawal, btwBalans, deductableCosts);

		List<Activum> activaLijst = handleActiva(userId, locale, props, overview, bookYear, currentBookYear, deductableCosts,
				costCache.getBusinessAccountCosts());

		List<Passivum> passivaLijst = handlePassiva(userId, locale, overview, bookYear, currentBookYear, activaLijst);

		BigInteger enterpriseCapital = getEnterpriseCapital(passivaLijst, bookYear);
		overview.setEnterpriseCapital(enterpriseCapital);

		BigDecimal privateDeposit = handlePrivateDeposits(overview);

		handlePrivateWithdrawals(overview, privatWithdrawal, bookYear, passivaLijst, enterpriseCapital, privateDeposit);

		handlePrepaidTaxes(userId, overview, bookYear);
		return overview;
	}

	private void handleProfitAndLoss(long userId, int bookYear, FiscalOverview overview, PrivatWithdrawal privatWithdrawal, Balans btwBalans,
			List<DeductableCostGroup> deductableCosts) throws Exception {
		handleTurnOver(overview, btwBalans);

		handleRepurchase(overview);

		handleInterest(overview);

		handleCar(userId, bookYear, overview, privatWithdrawal, deductableCosts);

		handleDepreciations(overview, deductableCosts);

		handleCosts(overview, deductableCosts);

		calculateProfit(overview);

		handleFiscalPension(overview);

		handleInvestments(userId, overview);
	}

	private void handleInvestments(long userId, FiscalOverview overview) throws Exception {
		List<Cost> investmentKostList = costCache.getInvestments();
		overview.setInvestmentDeduction(InvestmentDeductionHelper.getInvestmentDeduction(investmentKostList, userId));
	}

	private void handleFiscalPension(FiscalOverview overview) {
		int maximaleFor = (int) (overview.getWinst() * CostConstants.FOR_PERCENTAGE);
		if (maximaleFor > CostConstants.MAXIMALE_FOR) {
			maximaleFor = CostConstants.MAXIMALE_FOR;
		}
		if (maximaleFor < 0) {
			maximaleFor = 0;
		}
		overview.setOudedagsReserveMaximaal(maximaleFor);
	}

	private void handleTurnOver(FiscalOverview overview, Balans btwBalans) throws Exception {
		overview.setNettoOmzet(btwBalans.getNettoOmzet().intValue());
	}

	private void handleRepurchase(FiscalOverview overview) {
		BigInteger repurchase = new BigInteger("0");
		overview.setRepurchase(repurchase);
	}

	private List<Passivum> handlePassiva(long userId, Locale locale, FiscalOverview overview, int bookYear, int currentBookYear,
			List<Activum> activaLijst) throws Exception {
		BookValue boekwaarde;
		int fiscalPension = 0;
		BigDecimal vatDebt = new BigDecimal("0");
		if (bookYear == currentBookYear) {
			// Voer dezelfde FOR op.
			boekwaarde = new BookValue();
			boekwaarde.setBalanceType(BalanceType.PENSION);
			boekwaarde.setJaar(bookYear);
			boekwaarde.setUserId(userId);
			boekwaarde = boekwaardeDao.getBookValueThisYear(boekwaarde);

			if (boekwaarde == null) {
				boekwaarde = new BookValue();
				boekwaarde.setBalanceType(BalanceType.PENSION);
				boekwaarde.setJaar(bookYear);
				boekwaarde.setUserId(userId);
				boekwaarde = boekwaardeDao.getPreviousBookValue(boekwaarde);

				if (boekwaarde != null) {
					fiscalPension = boekwaarde.getSaldo().intValue();
					boekwaarde.setId(0);
					boekwaarde.setJaar(bookYear);
					boekwaarde.setUserId(userId);
					boekwaardeDao.insertBookValue(boekwaarde);
				}
			} else {
				fiscalPension = boekwaarde.getSaldo().intValue();
			}

			Periode lastVatPeriod = DateHelper.getLastVatPeriodPreviousYear();
			BoekDao boekDao = new BoekDao();
			vatDebt = boekDao.getVatDebtFromPreviousYear(DateHelper.getDate(lastVatPeriod.getBeginDatum()),
					DateHelper.getDate(lastVatPeriod.getEindDatum()), Long.toString(userId));

			if (vatDebt != null && vatDebt.compareTo(new BigDecimal("0")) == 1) {
				boekwaarde = new BookValue();
				boekwaarde.setJaar(bookYear);
				boekwaarde.setBalanceType(BalanceType.VAT_TO_BE_PAID);
				boekwaarde.setUserId(userId);
				boekwaarde.setSaldo(vatDebt.toBigInteger());

				BookValue currentBookValue = boekwaardeDao.getBookValueThisYear(boekwaarde);

				if (currentBookValue == null) {
					boekwaardeDao.insertBookValue(boekwaarde);
				} else {
					boekwaarde.setId(currentBookValue.getId());
					boekwaardeDao.updateBookValue(boekwaarde);
				}
			}
		}

		// Get balance totals
		int bookTotalBegin = getBalansTotaal(activaLijst, bookYear - 1);
		int bookTotalEnd = getBalansTotaal(activaLijst, bookYear);
		overview.setBookTotalBegin(bookTotalBegin);
		overview.setBookTotalEnd(bookTotalEnd);

		if (bookYear == currentBookYear) {
			// Non current assets
			boekwaarde = new BookValue();
			boekwaarde.setBalanceType(BalanceType.NON_CURRENT_ASSETS);
			boekwaarde.setJaar(bookYear);
			boekwaarde.setUserId(userId);
			boekwaarde = boekwaardeDao.getBookValueThisYear(boekwaarde);
			BigInteger bookTotalNonCurrentAssets = BigInteger.valueOf(bookTotalEnd - fiscalPension - vatDebt.intValue());
			if (boekwaarde == null) {
				boekwaarde = new BookValue();
				boekwaarde.setBalanceType(BalanceType.NON_CURRENT_ASSETS);
				boekwaarde.setJaar(bookYear);
				boekwaarde.setSaldo(bookTotalNonCurrentAssets);
				boekwaarde.setUserId(userId);
				boekwaardeDao.insertBookValue(boekwaarde);
			} else {
				boekwaarde.setSaldo(bookTotalNonCurrentAssets);
				boekwaardeDao.updateBookValue(boekwaarde);
			}
		}

		KeyYear key = new KeyYear(userId, bookYear);
		List<Passivum> passivaLijst = fiscaalDao.getPassivaLijst(key);
		for (Passivum passivum : passivaLijst) {
			passivum.setOmschrijving(Translator.translateKey(passivum.getOmschrijving(), locale));
		}

		overview.setPassiva(passivaLijst);
		return passivaLijst;
	}

	private BigDecimal handlePrivateDeposits(FiscalOverview overview) throws Exception {
		BigDecimal privateDeposit = costCache.getCostsWithPrivateMoney();
		overview.setPrivateDeposit(privateDeposit.toBigInteger());
		return privateDeposit;
	}

	private void handlePrepaidTaxes(long userId, FiscalOverview overview, int bookYear) {
		PrepaidTax prepaidTax = DutchTaxCodeHelper.findPrepaidTax(bookYear, Long.toString(userId));
		overview.setPrepaidTax(prepaidTax);
	}

	private void handlePrivateWithdrawals(FiscalOverview overview, PrivatWithdrawal privatWithdrawal, int bookYear, List<Passivum> passivaLijst,
			BigInteger enterpriseCapital, BigDecimal privateDeposit) {
		BigInteger enterpriseCapitalPreviousYear = getEnterpriseCapital(passivaLijst, bookYear - 1);
		int totalWithdrawal = overview.getProfit() - (enterpriseCapital.intValue() - enterpriseCapitalPreviousYear.intValue())
				+ privateDeposit.intValue();
		privatWithdrawal.setTotaleOnttrekking(totalWithdrawal);
		int withdrawalCash = totalWithdrawal - privatWithdrawal.getWithdrawalPrivateUsageBusinessCar();
		privatWithdrawal.setWithdrawalCash(withdrawalCash);
		overview.setOnttrekking(privatWithdrawal);
	}

	private void handleInterest(FiscalOverview overview) throws Exception {
		BigInteger interest = costCache.getInterest().toBigInteger();
		overview.setInterestFromBusinessSavings(interest);
	}

	private void handleDepreciations(FiscalOverview overview, List<DeductableCostGroup> deductableCosts) {
		BigDecimal depreciationMachines = BalanceCalculator.getOverigeAfschrijvingen(deductableCosts);
		if (depreciationMachines != null) {
			overview.setAfschrijvingOverig(depreciationMachines.intValue());
		}
		BigDecimal depreciationSettlement = BalanceCalculator.getDepreciationSettlement(deductableCosts);
		if (depreciationSettlement != null) {
			overview.setSettlementDepreciation(depreciationSettlement.intValue());
		}
		overview.setAfschrijvingTotaal(overview.getAfschrijvingAuto() - overview.getAfschrijvingAutoCorrectie() + overview.getAfschrijvingOverig()
				- overview.getAfschrijvingOverigCorrectie() + overview.getSettlementDepreciation());
	}

	private void handleCosts(FiscalOverview overview, List<DeductableCostGroup> deductableCosts) {
		overview.setKostenOverigTransport(BalanceCalculator.getReiskosten(deductableCosts).intValue());
		overview.setKostenOverig(BalanceCalculator.getFoodCosts(deductableCosts).add(BalanceCalculator.getAlgemeneKosten(deductableCosts)).intValue());
		overview.setSettlementCosts(BalanceCalculator.getSettlementCosts(deductableCosts).intValue());
	}

	private void handleCar(long userId, int bookYear, FiscalOverview overview, PrivatWithdrawal privatWithdrawal,
			List<DeductableCostGroup> deductableCosts) throws Exception {
		BigDecimal afschrijvingAuto = BalanceCalculator.getAfschrijvingAuto(deductableCosts);
		BookValue carActivum = BookValueHelper.getCurrentBookValue(BalanceType.CAR, new KeyYear(userId, bookYear));
		if (carActivum != null) {
			handleBusinessCar(overview, privatWithdrawal, deductableCosts, afschrijvingAuto);
		} else {
			handlePrivateCar(overview, deductableCosts);
		}
	}

	private void handleBusinessCar(FiscalOverview overview, PrivatWithdrawal privatWithdrawal, List<DeductableCostGroup> deductableCosts,
			BigDecimal afschrijvingAuto) throws Exception {
		if (afschrijvingAuto != null) {
			overview.setAfschrijvingAuto(afschrijvingAuto.intValue());
		}
		overview.setBijtellingAuto(BalanceCalculator.getFiscaleBijtelling(deductableCosts).intValue());
		overview.setKostenAuto(BalanceCalculator.getKostenVoorAuto(deductableCosts).intValue());
		handleDepreciationCorrections(overview);
		int kostenAutoAftrekbaar = 0;
		kostenAutoAftrekbaar = overview.getBijtellingAuto() - overview.getKostenAuto() - overview.getAfschrijvingAuto();
		if (kostenAutoAftrekbaar > 0) {
			kostenAutoAftrekbaar = 0;
		}
		if (kostenAutoAftrekbaar < 0) {
			privatWithdrawal.setWithdrawalPrivateUsageBusinessCar(overview.getBijtellingAuto());
		} else {
			privatWithdrawal.setWithdrawalPrivateUsageBusinessCar(overview.getKostenAuto() + overview.getAfschrijvingAuto());
		}
		overview.setKostenAutoAftrekbaar(kostenAutoAftrekbaar);
	}

	@Deprecated
	private void handleDepreciationCorrections(FiscalOverview overview) throws Exception {
		List<Cost> corrections = costCache.getVatCorrectionDepreciation();
		Iterator<Cost> iterator = corrections.iterator();
		int depreciationCorrection = 0;
		while (iterator.hasNext()) {
			Cost correctionKost = iterator.next();
			if (correctionKost.getDescription().contains("auto")) {
				overview.setAfschrijvingAutoCorrectie(correctionKost.getAmount().intValue());
			} else {
				depreciationCorrection += correctionKost.getAmount().intValue();
			}
		}
		overview.setAfschrijvingOverigCorrectie(depreciationCorrection);
	}

	private void handlePrivateCar(FiscalOverview overview, List<DeductableCostGroup> deductableCosts) {
		overview.setKostenAuto(BalanceCalculator.getKostenVoorAuto(deductableCosts).intValue());
		int kostenAutoAftrekbaar = 0;
		kostenAutoAftrekbaar = -overview.getKostenAuto();
		overview.setKostenAutoAftrekbaar(kostenAutoAftrekbaar);
	}

	private BigInteger getEnterpriseCapital(List<Passivum> passiva, int bookYear) {
		BigInteger enterpriseCapital = new BigInteger("0");
		for (Passivum passivum : passiva) {
			if (passivum.getBoekjaar() == bookYear && passivum.getBalanceType() != BalanceType.VAT_TO_BE_PAID) {
				enterpriseCapital = enterpriseCapital.add(passivum.getSaldo());
			}
		}
		return enterpriseCapital;
	}

	private int getBalansTotaal(List<Activum> activaLijst, int fiscaalJaar) {
		Iterator<Activum> iterator = activaLijst.iterator();
		int totaal = 0;
		String previousBalance = null;
		while (iterator.hasNext()) {
			Activum activa = iterator.next();
			if (activa.getBoekjaar() == fiscaalJaar && (previousBalance == null || !activa.getOmschrijving().equals(previousBalance))) {
				totaal += activa.getSaldo().intValue();
				previousBalance = activa.getOmschrijving();
			}
		}
		return totaal;
	}

	public void calculateProfit(FiscalOverview overview) {
		int nettoOmzet = overview.getNettoOmzet();
		nettoOmzet += overview.getInterestFromBusinessSavings().intValue();
		nettoOmzet -= overview.getRepurchase().intValue();
		nettoOmzet += overview.getKostenAutoAftrekbaar();
		nettoOmzet -= overview.getKostenOverigTransport();
		nettoOmzet -= overview.getKostenOverig();
		nettoOmzet -= overview.getSettlementCosts();
		nettoOmzet -= overview.getAfschrijvingTotaal();
		overview.setProfit(nettoOmzet);
	}

}
