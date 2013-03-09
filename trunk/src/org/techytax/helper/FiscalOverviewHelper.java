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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.techytax.dao.BoekDao;
import org.techytax.dao.BookValueDao;
import org.techytax.dao.FiscalDao;
import org.techytax.domain.Activum;
import org.techytax.domain.Aftrekpost;
import org.techytax.domain.BalanceType;
import org.techytax.domain.Balans;
import org.techytax.domain.BookValue;
import org.techytax.domain.Cost;
import org.techytax.domain.FiscalOverview;
import org.techytax.domain.KeyYear;
import org.techytax.domain.KostConstanten;
import org.techytax.domain.Liquiditeit;
import org.techytax.domain.Passivum;
import org.techytax.domain.Periode;
import org.techytax.domain.PrepaidTax;
import org.techytax.domain.PrivatWithdrawal;
import org.techytax.domain.VatType;
import org.techytax.props.PropsFactory;
import org.techytax.util.DateHelper;

public class FiscalOverviewHelper {

	private static FiscalDao fiscaalDao = new FiscalDao();
	private static BookValueDao boekwaardeDao = new BookValueDao();
	private static BoekDao boekDao = new BoekDao();

	public static FiscalOverview createFiscalOverview(String beginDatum, String eindDatum, List<Cost> costList, long userId, Locale locale)
			throws Exception {

		// Load properties
		Properties props = PropsFactory.loadProperties();

		FiscalOverview overview = new FiscalOverview();
		PrivatWithdrawal privatWithdrawal = new PrivatWithdrawal();

		Date datum = DateHelper.stringToDate(beginDatum);
		int bookYear = DateHelper.getYear(datum);
		int currentBookYear = DateHelper.getYear(new Date()) - 1;

		Balans btwBalans = BalanceCalculator.calculateBtwBalance(costList, false);

		List<Aftrekpost> deductableCosts = boekDao.getDeductableCosts(beginDatum, eindDatum, Long.toString(userId));
		overview.setJaar(bookYear);

		handleProfitAndLoss(beginDatum, eindDatum, userId, bookYear, overview, privatWithdrawal, btwBalans, deductableCosts);

		List<Activum> activaLijst = handleActiva(beginDatum, eindDatum, userId, locale, props, overview, bookYear, currentBookYear, deductableCosts);

		List<Passivum> passivaLijst = handlePassiva(userId, locale, overview, bookYear, currentBookYear, activaLijst);

		BigInteger enterpriseCapital = getEnterpriseCapital(passivaLijst, bookYear);
		overview.setEnterpriseCapital(enterpriseCapital);

		BigDecimal privateDeposit = handlePrivateDeposits(beginDatum, eindDatum, userId, overview);

		handlePrivateWithdrawals(overview, privatWithdrawal, bookYear, passivaLijst, enterpriseCapital, privateDeposit);

		handlePrepaidTaxes(userId, overview, bookYear);
		return overview;
	}

	private static void handleProfitAndLoss(String beginDatum, String eindDatum, long userId, int bookYear, FiscalOverview overview,
			PrivatWithdrawal privatWithdrawal, Balans btwBalans, List<Aftrekpost> deductableCosts) throws Exception {
		handleTurnOver(beginDatum, eindDatum, userId, overview, btwBalans);

		handleRepurchase(overview);

		handleInterest(beginDatum, eindDatum, userId, overview);

		handleCar(beginDatum, eindDatum, userId, bookYear, overview, privatWithdrawal, deductableCosts);

		handleDepreciations(overview, deductableCosts);

		handleCosts(overview, deductableCosts);

		calculateProfit(overview);

		handleFiscalPension(overview);

		handleInvestments(beginDatum, eindDatum, userId, overview);
	}

	private static void handleInvestments(String beginDatum, String eindDatum, long userId, FiscalOverview overview) throws Exception {
		List<Cost> investmentKostList = boekDao.getInvestments(beginDatum, eindDatum, Long.toString(userId));
		overview.setInvestmentDeduction(InvestmentDeductionHelper.getInvestmentDeduction(investmentKostList, userId));
	}

	private static void handleFiscalPension(FiscalOverview overview) {
		int maximaleFor = (int) (overview.getWinst() * KostConstanten.FOR_PERCENTAGE);
		if (maximaleFor > KostConstanten.MAXIMALE_FOR) {
			maximaleFor = KostConstanten.MAXIMALE_FOR;
		}
		if (maximaleFor < 0) {
			maximaleFor = 0;
		}
		overview.setOudedagsReserveMaximaal(maximaleFor);
	}

	private static void handleTurnOver(String beginDatum, String eindDatum, long userId, FiscalOverview overview, Balans btwBalans) throws Exception {
		overview.setNettoOmzet(btwBalans.getNettoOmzet().intValue());

		// Turnover net unpaid
		// TODO: start with bookvalue from previous year
		BigDecimal turnoverUnpaid = boekDao.getInvoiceBalance(beginDatum, DateHelper.getFinalInvoiceDate(eindDatum), Long.toString(userId));
		BigDecimal turnoverUnpaidNet = new BigDecimal(turnoverUnpaid.doubleValue() / (1 + VatType.HIGH.getValue(null)));
		turnoverUnpaidNet = turnoverUnpaidNet.setScale(2, BigDecimal.ROUND_HALF_UP);
		overview.setNetTurnOverNotYetPaid(turnoverUnpaidNet.toBigInteger());
		overview.setTurnOverUnpaid(turnoverUnpaid);
	}

	private static void handleRepurchase(FiscalOverview overview) {
		// BigInteger repurchase =
		// BalanceCalculator.getRepurchase(deductableCosts);
		BigInteger repurchase = new BigInteger("0");
		overview.setRepurchase(repurchase);
	}

	private static List<Passivum> handlePassiva(long userId, Locale locale, FiscalOverview overview, int bookYear, int currentBookYear,
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
			vatDebt = boekDao.getVatDebt(DateHelper.getDate(lastVatPeriod.getBeginDatum()), DateHelper.getDate(lastVatPeriod.getEindDatum()),
					Long.toString(userId));

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

	private static BigDecimal handlePrivateDeposits(String beginDatum, String eindDatum, long userId, FiscalOverview overview)
			throws Exception {
		BigDecimal privateDeposit = boekDao.getCostsWithPrivateMoney(beginDatum, eindDatum, Long.toString(userId));
		overview.setPrivateDeposit(privateDeposit.toBigInteger());
		return privateDeposit;
	}

	private static void handlePrepaidTaxes(long userId, FiscalOverview overview, int bookYear) {
		PrepaidTax prepaidTax = DutchTaxCodeHelper.findPrepaidTax(bookYear, Long.toString(userId));
		overview.setPrepaidTax(prepaidTax);
	}

	private static void handlePrivateWithdrawals(FiscalOverview overview, PrivatWithdrawal privatWithdrawal, int bookYear,
			List<Passivum> passivaLijst, BigInteger enterpriseCapital, BigDecimal privateDeposit) {
		BigInteger enterpriseCapitalPreviousYear = getEnterpriseCapital(passivaLijst, bookYear - 1);
		int totalWithdrawal = overview.getProfit() - (enterpriseCapital.intValue() - enterpriseCapitalPreviousYear.intValue())
				+ privateDeposit.intValue();
		privatWithdrawal.setTotaleOnttrekking(totalWithdrawal);
		int withdrawalCash = totalWithdrawal - privatWithdrawal.getWithdrawalPrivateUsageBusinessCar();
		privatWithdrawal.setWithdrawalCash(withdrawalCash);
		overview.setOnttrekking(privatWithdrawal);
	}

	private static void handleInterest(String beginDatum, String eindDatum, long userId, FiscalOverview overview) throws Exception {
		BigInteger interest = boekDao.getInterest(beginDatum, eindDatum, Long.toString(userId)).toBigInteger();
		overview.setInterestFromBusinessSavings(interest);
	}

	private static void handleDepreciations(FiscalOverview overview, List<Aftrekpost> deductableCosts) {
		BigDecimal depreciationOther = BalanceCalculator.getOverigeAfschrijvingen(deductableCosts);
		if (depreciationOther != null) {
			overview.setAfschrijvingOverig(BalanceCalculator.getOverigeAfschrijvingen(deductableCosts).intValue());
		}
		overview.setAfschrijvingTotaal(overview.getAfschrijvingAuto() - overview.getAfschrijvingAutoCorrectie() + overview.getAfschrijvingOverig()
				- overview.getAfschrijvingOverigCorrectie());
	}

	private static void handleCosts(FiscalOverview overview, List<Aftrekpost> deductableCosts) {
		overview.setKostenOverigTransport(BalanceCalculator.getReiskosten(deductableCosts).intValue());
		overview.setKostenOverig(BalanceCalculator.getEtenskosten(deductableCosts).add(BalanceCalculator.getAlgemeneKosten(deductableCosts))
				.intValue());
	}

	private static List<Activum> handleActiva(String beginDatum, String eindDatum, long userId, Locale locale, Properties props,
			FiscalOverview overview, int bookYear, int currentBookYear, List<Aftrekpost> deductableCosts) throws Exception {
		Liquiditeit liquiditeit;
		// Create/update activa
		BookValue boekwaarde = null;
		if (bookYear == currentBookYear) {

			// Current assets
			KeyYear keyYear = new KeyYear(userId, bookYear);
			boekwaarde = getCurrentBookValue(BalanceType.CURRENT_ASSETS, keyYear);
			if (boekwaarde == null) {
				String startDate = props.getProperty("start.date");
				List<Cost> rekeningLijst = boekDao.getKostLijst(startDate, eindDatum, "rekeningBalans", Long.toString(userId));
				liquiditeit = BalanceCalculator.calculateAccountBalance(rekeningLijst);

				BigInteger saldo = liquiditeit.getRekeningBalans().toBigInteger();
				saldo = saldo.add(liquiditeit.getSpaarBalans().toBigInteger());
				boekwaarde = new BookValue();
				boekwaarde.setJaar(bookYear);
				boekwaarde.setBalanceType(BalanceType.CURRENT_ASSETS);
				boekwaarde.setSaldo(saldo);
				boekwaarde.setUserId(userId);
				boekwaardeDao.insertBookValue(boekwaarde);
			} else {
				BookValue vorigeBoekwaarde = boekwaardeDao.getPreviousBookValue(boekwaarde);
				BigInteger saldo = new BigInteger("0");
				if (vorigeBoekwaarde != null) {
					saldo = vorigeBoekwaarde.getSaldo();
				}
				List<Cost> rekeningLijst = boekDao.getKostLijst(beginDatum, eindDatum, "rekeningBalans", Long.toString(userId));
				liquiditeit = BalanceCalculator.calculateAccountBalance(rekeningLijst);
				saldo = saldo.add(liquiditeit.getRekeningBalans().toBigInteger());
				saldo = saldo.add(liquiditeit.getSpaarBalans().toBigInteger());
				boekwaarde.setSaldo(saldo);
				boekwaardeDao.updateBookValue(boekwaarde);
			}

			// Machinery
			BigDecimal totaalAfschrijvingenOverig = new BigDecimal("0");
			totaalAfschrijvingenOverig = BalanceCalculator.getOverigeAfschrijvingen(deductableCosts);

			BookValue activumValue = new BookValue();
			activumValue.setJaar(bookYear);
			activumValue.setBalanceType(BalanceType.MACHINERY);
			activumValue.setUserId(userId);

			BookValue previousBookValue = boekwaardeDao.getPreviousBookValue(activumValue);
			BookValue currentBookValue = activumValue = boekwaardeDao.getBookValueThisYear(activumValue);

			if (activumValue == null) {
				throw new RuntimeException("Add machinery activa");
			}

			Activum activum = new Activum();
			activum.setUserId(userId);
			activum.setBalanceType(BalanceType.MACHINERY);
			activum.setEndDate(beginDatum);
			BigInteger totalCost = boekDao.getTotalCostForActivumThisYear(activum);

			if (previousBookValue != null) {
				if (currentBookValue == null) {
					previousBookValue.setId(0);
					previousBookValue.setSaldo(previousBookValue.getSaldo().add(totalCost).subtract(totaalAfschrijvingenOverig.toBigInteger()));
					boekwaardeDao.insertBookValue(activumValue);
				} else {
					activumValue.setSaldo(previousBookValue.getSaldo().add(totalCost).subtract(totaalAfschrijvingenOverig.toBigInteger()));
					boekwaardeDao.updateBookValue(activumValue);
				}

			} else {
				if (totalCost.compareTo(new BigInteger("0")) == 1) {
					if (currentBookValue == null) {
						activumValue = new BookValue();
						activumValue.setBalanceType(BalanceType.MACHINERY);
						activumValue.setJaar(bookYear);
						activumValue.setUserId(userId);
						activumValue.setSaldo(totalCost);
						boekwaardeDao.insertBookValue(activumValue);
					} else {
						currentBookValue.setSaldo(totalCost);
						boekwaardeDao.updateBookValue(currentBookValue);
					}
				}
			}

			// Car
			BigInteger carDepreciation = BigInteger.valueOf(overview.getAfschrijvingAuto());
			activumValue = new BookValue();
			activumValue.setJaar(bookYear);
			activumValue.setBalanceType(BalanceType.CAR);
			activumValue.setUserId(userId);
			activumValue = boekwaardeDao.getPreviousBookValue(activumValue);
			if (activumValue != null) {
				BigInteger carBookValue = activumValue.getSaldo();
				activumValue.setJaar(bookYear);
				activumValue = boekwaardeDao.getBookValueThisYear(activumValue);
				if (activumValue == null) {
					activumValue = new BookValue();
					activumValue.setBalanceType(BalanceType.CAR);
					activumValue.setUserId(userId);
					activumValue.setJaar(bookYear);
					activumValue.setSaldo(carBookValue.subtract(carDepreciation));
					boekwaardeDao.insertBookValue(activumValue);
				} else {
					activumValue.setBalanceType(BalanceType.CAR);
					activumValue.setUserId(userId);
					activumValue.setJaar(bookYear);
					activumValue.setSaldo(carBookValue.subtract(carDepreciation));
					boekwaardeDao.updateBookValue(activumValue);
				}
			} else {
			}

			// Stock
			if (overview.getRepurchase() != null && overview.getRepurchase().intValue() > 0) {
				activumValue = new BookValue();
				activumValue.setJaar(bookYear);
				activumValue.setUserId(userId);
				activumValue.setBalanceType(BalanceType.STOCK);
				activumValue = boekwaardeDao.getBookValueThisYear(activumValue);
				if (activumValue == null) {
					activumValue = new BookValue();
					activumValue.setJaar(bookYear);
					activumValue.setUserId(userId);
					activumValue.setBalanceType(BalanceType.STOCK);
					activumValue = boekwaardeDao.getPreviousBookValue(activumValue);
					if (activumValue == null) {
						activumValue = new BookValue();
						activumValue.setBalanceType(BalanceType.STOCK);
						activumValue.setJaar(bookYear);
						activumValue.setUserId(userId);
						activumValue.setSaldo(overview.getRepurchase());
						boekwaardeDao.insertBookValue(activumValue);
					} else {
						activumValue.setJaar(bookYear);
						activumValue.setSaldo(activumValue.getSaldo().add(overview.getRepurchase()));
						boekwaardeDao.insertBookValue(activumValue);
					}
				}
			}

			if (overview.getTurnOverUnpaid() != null && overview.getTurnOverUnpaid().compareTo(new BigDecimal("0")) != 0) {
				// Invoices yet to be paid
				activumValue = new BookValue();
				activumValue.setJaar(bookYear);
				activumValue.setBalanceType(BalanceType.INVOICES_TO_BE_PAID);
				activumValue.setUserId(userId);
				activumValue.setSaldo(overview.getTurnOverUnpaid().toBigInteger());

				currentBookValue = boekwaardeDao.getBookValueThisYear(activumValue);

				if (currentBookValue == null) {
					boekwaardeDao.insertBookValue(activumValue);
				} else {
					activumValue.setId(currentBookValue.getId());
					boekwaardeDao.updateBookValue(activumValue);
				}
			}

		}

		KeyYear keyYear = new KeyYear(userId, bookYear);
		List<Activum> activaLijst = fiscaalDao.getActivaLijst(keyYear);
		for (Activum activum : activaLijst) {
			activum.setOmschrijving(Translator.translateKey(activum.getOmschrijving(), locale));
		}

		if (!checkActivaOpgegeven(activaLijst, bookYear)) {
			throw new Exception("errors.fiscal.activa");
		}
		overview.setActiva(activaLijst);
		return activaLijst;
	}
	
	private static BookValue getPreviousBookValue(BalanceType balanceType, KeyYear keyYear) throws Exception {
		BookValue bookValue = new BookValue();
		bookValue.setJaar(keyYear.getYear());
		bookValue.setBalanceType(balanceType);
		bookValue.setUserId(keyYear.getUserId());
		bookValue = boekwaardeDao.getPreviousBookValue(bookValue);
		return bookValue;
	}
	
	private static BookValue getCurrentBookValue(BalanceType balanceType, KeyYear keyYear) throws Exception {
		BookValue bookValue = new BookValue();
		bookValue.setJaar(keyYear.getYear());
		bookValue.setBalanceType(balanceType);
		bookValue.setUserId(keyYear.getUserId());
		bookValue = boekwaardeDao.getBookValueThisYear(bookValue);
		return bookValue;
	}	

	private static void handleCar(String beginDatum, String eindDatum, long userId, int bookYear, FiscalOverview overview, PrivatWithdrawal privatWithdrawal,
			List<Aftrekpost> deductableCosts) throws Exception {
		BigDecimal afschrijvingAuto = BalanceCalculator.getAfschrijvingAuto(deductableCosts);
		BookValue carActivum = getCurrentBookValue(BalanceType.CAR, new KeyYear(userId, bookYear));
		if (carActivum != null) {
			handleBusinessCar(beginDatum, eindDatum, userId, overview, privatWithdrawal, deductableCosts, afschrijvingAuto);
		} else {
			handlePrivateCar(overview, deductableCosts);
		}
	}

	private static void handleBusinessCar(String beginDatum, String eindDatum, long userId, FiscalOverview overview,
			PrivatWithdrawal privatWithdrawal, List<Aftrekpost> deductableCosts, BigDecimal afschrijvingAuto) throws Exception {
		if (afschrijvingAuto != null) {
			overview.setAfschrijvingAuto(afschrijvingAuto.intValue());
		}
		overview.setBijtellingAuto(BalanceCalculator.getFiscaleBijtelling(deductableCosts).intValue());
		overview.setKostenAuto(BalanceCalculator.getKostenVoorAuto(deductableCosts).intValue());
		handleDepreciationCorrections(beginDatum, eindDatum, userId, overview);
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
	private static void handleDepreciationCorrections(String beginDatum, String eindDatum, long userId, FiscalOverview overview)
			throws Exception {
		List<Cost> corrections = boekDao.getVatCorrectionDepreciation(beginDatum, eindDatum, Long.toString(userId));
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

	private static void handlePrivateCar(FiscalOverview overview, List<Aftrekpost> deductableCosts) {
		overview.setKostenAuto(BalanceCalculator.getKostenVoorAuto(deductableCosts).intValue());
		int kostenAutoAftrekbaar = 0;
		kostenAutoAftrekbaar = -overview.getKostenAuto();
		overview.setKostenAutoAftrekbaar(kostenAutoAftrekbaar);
	}

	private static BigInteger getEnterpriseCapital(List<Passivum> passiva, int bookYear) {
		BigInteger enterpriseCapital = new BigInteger("0");
		for (Passivum passivum : passiva) {
			if (passivum.getBoekjaar() == bookYear && passivum.getBalanceType() != BalanceType.VAT_TO_BE_PAID) {
				enterpriseCapital = enterpriseCapital.add(passivum.getSaldo());
			}
		}
		return enterpriseCapital;
	}

	private static int getBalansTotaal(List<Activum> activaLijst, int fiscaalJaar) {
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

	private static boolean checkActivaOpgegeven(List<Activum> activaLijst, int fiscaalJaar) {
		Iterator<Activum> iterator = activaLijst.iterator();
		while (iterator.hasNext()) {
			Activum activa = iterator.next();
			if (activa.getBoekjaar() == fiscaalJaar) {
				return true;
			}
		}
		return false;
	}

	public static void calculateProfit(FiscalOverview overview) {
		int nettoOmzet = overview.getNettoOmzet();
		nettoOmzet += overview.getInterestFromBusinessSavings().intValue();
		nettoOmzet += overview.getNetTurnOverNotYetPaid().intValue();
		nettoOmzet -= overview.getRepurchase().intValue();
		nettoOmzet += overview.getKostenAutoAftrekbaar();
		nettoOmzet -= overview.getKostenOverigTransport();
		nettoOmzet -= overview.getKostenOverig();
		nettoOmzet -= overview.getAfschrijvingOverig();
		nettoOmzet += overview.getAfschrijvingOverigCorrectie();
		nettoOmzet -= overview.getAfschrijvingAuto();
		nettoOmzet += overview.getAfschrijvingAutoCorrectie();
		overview.setProfit(nettoOmzet);
	}
}
