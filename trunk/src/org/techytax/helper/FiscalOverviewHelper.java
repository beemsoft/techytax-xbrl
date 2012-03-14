/**
 * Copyright 2012 Hans Beemsterboer
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
import java.util.Properties;

import org.techytax.dao.BoekDao;
import org.techytax.dao.BookValueDao;
import org.techytax.dao.FiscalDao;
import org.techytax.domain.Activum;
import org.techytax.domain.Aftrekpost;
import org.techytax.domain.BalanceType;
import org.techytax.domain.Balans;
import org.techytax.domain.BookValue;
import org.techytax.domain.FiscalOverview;
import org.techytax.domain.KeyYear;
import org.techytax.domain.Kost;
import org.techytax.domain.KostConstanten;
import org.techytax.domain.Liquiditeit;
import org.techytax.domain.Passivum;
import org.techytax.domain.Periode;
import org.techytax.domain.PrepaidTax;
import org.techytax.domain.PrivatWithdrawal;
import org.techytax.props.PropsFactory;
import org.techytax.util.DateHelper;

public class FiscalOverviewHelper {

	public static FiscalOverview createFiscalOverview(String beginDatum, String eindDatum, List<Kost> costList, long userId) throws Exception {

		// Load properties
		Properties props = PropsFactory.loadProperties();

		FiscalOverview overview = new FiscalOverview();
		PrivatWithdrawal privatWithdrawal = new PrivatWithdrawal();
		Liquiditeit liquiditeit = null;

		Date datum = DateHelper.stringToDate(beginDatum);
		int bookYear = DateHelper.getYear(datum);
		int currentBookYear = DateHelper.getYear(new Date()) - 1;

		// Calculate Profit & Loss
		Balans btwBalans = BalanceCalculator.calculateBtwBalance(costList, false);

		FiscalDao fiscaalDao = new FiscalDao();
		BookValueDao boekwaardeDao = new BookValueDao();
		BoekDao boekDao = new BoekDao();
		List<Aftrekpost> deductableCosts = boekDao.getDeductableCosts(beginDatum, eindDatum, Long.toString(userId));
		overview.setJaar(bookYear);
		overview.setNettoOmzet(btwBalans.getNettoOmzet().intValue());

		// Turnover net unpaid
		// TODO: start with bookvalue from previous year
		BigDecimal turnoverUnpaid = boekDao.getInvoiceBalance(beginDatum, DateHelper.getFinalInvoiceDate(eindDatum), Long.toString(userId));
		BigDecimal turnoverUnpaidNet = new BigDecimal(turnoverUnpaid.doubleValue() / 1.19d);
		turnoverUnpaidNet = turnoverUnpaidNet.setScale(2, BigDecimal.ROUND_HALF_UP);
		overview.setNetTurnOverNotYetPaid(turnoverUnpaidNet.toBigInteger());

		// Repurchase
		BigInteger repurchase = BalanceCalculator.getRepurchase(deductableCosts);
		overview.setRepurchase(repurchase);

		// Interest
		BigInteger interest = boekDao.getInterest(beginDatum, eindDatum, Long.toString(userId));
		overview.setInterestFromBusinessSavings(interest);

		// Business car costs
		BigDecimal afschrijvingAuto = BalanceCalculator.getAfschrijvingAuto(deductableCosts);
		if (afschrijvingAuto != null) {
			overview.setAfschrijvingAuto(afschrijvingAuto.intValue());
			overview.setBijtellingAuto(BalanceCalculator.getFiscaleBijtelling(deductableCosts).intValue());
			overview.setKostenAuto(BalanceCalculator.getKostenVoorAuto(deductableCosts).intValue());
			List<Kost> corrections = boekDao.getVatCorrectionDepreciation(beginDatum, eindDatum, Long.toString(userId));
			Iterator<Kost> iterator = corrections.iterator();
			int depreciationCorrection = 0;
			while (iterator.hasNext()) {
				Kost correctionKost = iterator.next();
				if (correctionKost.getOmschrijving().contains("auto")) {
					overview.setAfschrijvingAutoCorrectie(correctionKost.getBedrag().intValue());
				} else {
					depreciationCorrection += correctionKost.getBedrag().intValue();
				}
			}
			overview.setAfschrijvingOverigCorrectie(depreciationCorrection);
			int kostenAutoAftrekbaar = 0;
			kostenAutoAftrekbaar = overview.getBijtellingAuto() - overview.getKostenAuto() - afschrijvingAuto.intValue();
			if (kostenAutoAftrekbaar > 0) {
				kostenAutoAftrekbaar = 0;
			}
			if (kostenAutoAftrekbaar < 0) {
				privatWithdrawal.setWithdrawalPrivateUsageBusinessCar(overview.getBijtellingAuto());
			} else {
				privatWithdrawal.setWithdrawalPrivateUsageBusinessCar(overview.getKostenAuto() + afschrijvingAuto.intValue());
			}
			overview.setKostenAutoAftrekbaar(kostenAutoAftrekbaar);
		}
		BigDecimal depreciationOther = BalanceCalculator.getOverigeAfschrijvingen(deductableCosts);
		if (depreciationOther != null) {
			overview.setAfschrijvingOverig(BalanceCalculator.getOverigeAfschrijvingen(deductableCosts).intValue());
		}
		overview.setAfschrijvingTotaal(overview.getAfschrijvingAuto() - overview.getAfschrijvingAutoCorrectie() + overview.getAfschrijvingOverig() - overview.getAfschrijvingOverigCorrectie());

		overview.setKostenOverigTransport(BalanceCalculator.getReiskosten(deductableCosts).intValue());
		overview.setKostenOverig(BalanceCalculator.getEtenskosten(deductableCosts).add(BalanceCalculator.getAlgemeneKosten(deductableCosts)).intValue());

		int profit = calculateProfit(overview);
		overview.setProfit(profit);
		int maximaleFor = (int) (overview.getWinst() * KostConstanten.FOR_PERCENTAGE);
		if (maximaleFor > KostConstanten.MAXIMALE_FOR) {
			maximaleFor = KostConstanten.MAXIMALE_FOR;
		}
		if (maximaleFor < 0) {
			maximaleFor = 0;
		}
		overview.setOudedagsReserveMaximaal(maximaleFor);

		List<Kost> investmentKostList = boekDao.getInvestments(beginDatum, eindDatum, Long.toString(userId));
		overview.setInvestmentDeduction(InvestmentDeductionHelper.getInvestmentDeduction(investmentKostList, userId));

		// Create/update activa
		BookValue boekwaarde = null;
		if (bookYear == currentBookYear) {

			// Current assets
			boekwaarde = new BookValue();
			boekwaarde.setBalanceType(BalanceType.CURRENT_ASSETS);
			boekwaarde.setJaar(bookYear);
			boekwaarde.setUserId(userId);
			boekwaarde = boekwaardeDao.getBookValueThisYear(boekwaarde);

			if (boekwaarde == null) {
				String startDate = props.getProperty("start.date");
				List<Kost> rekeningLijst = boekDao.getKostLijst(startDate, eindDatum, "rekeningBalans", Long.toString(userId));
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
				List<Kost> rekeningLijst = boekDao.getKostLijst(beginDatum, eindDatum, "rekeningBalans", Long.toString(userId));
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
				if (overview.getRepurchase().compareTo(new BigInteger("0")) == 1) {
					activumValue = new BookValue();
					activumValue.setBalanceType(BalanceType.MACHINERY);
					activumValue.setJaar(bookYear);
					activumValue.setUserId(userId);
					activumValue.setSaldo(overview.getRepurchase());
					boekwaardeDao.insertBookValue(activumValue);
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

			if (turnoverUnpaid != null && turnoverUnpaid.compareTo(new BigDecimal("0")) != 0) {
				// Invoices yet to be paid
				activumValue = new BookValue();
				activumValue.setJaar(bookYear);
				activumValue.setBalanceType(BalanceType.INVOICES_TO_BE_PAID);
				activumValue.setUserId(userId);
				activumValue.setSaldo(turnoverUnpaid.toBigInteger());

				currentBookValue = boekwaardeDao.getBookValueThisYear(activumValue);

				if (currentBookValue == null) {
					boekwaardeDao.insertBookValue(activumValue);
				} else {
					activumValue.setId(currentBookValue.getId());
					boekwaardeDao.updateBookValue(activumValue);
				}
			}

		}

		KeyYear keyYear = new KeyYear();
		keyYear.setYear(bookYear);
		keyYear.setUserId(userId);
		List<Activum> activaLijst = fiscaalDao.getActivaLijst(keyYear);

		if (!checkActivaOpgegeven(activaLijst, bookYear)) {
			throw new Exception("errors.fiscal.activa");
		}
		overview.setActiva(activaLijst);

		// Maak passiva balans op.
		int FOR = 0;
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
					FOR = boekwaarde.getSaldo().intValue();
					boekwaarde.setId(0);
					boekwaarde.setJaar(bookYear);
					boekwaarde.setUserId(userId);
					boekwaardeDao.insertBookValue(boekwaarde);
				}
			} else {
				FOR = boekwaarde.getSaldo().intValue();
			}

			Periode lastVatPeriod = DateHelper.getLastVatPeriodPreviousYear();
			vatDebt = boekDao.getVatDebt(DateHelper.getDate(lastVatPeriod.getBeginDatum()), DateHelper.getDate(lastVatPeriod.getEindDatum()), Long.toString(userId));

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
			BigInteger bookTotalNonCurrentAssets = BigInteger.valueOf(bookTotalEnd - FOR - vatDebt.intValue()); 
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

		KeyYear key = new KeyYear();
		key.setUserId(userId);
		key.setYear(bookYear);
		List<Passivum> passivaLijst = fiscaalDao.getPassivaLijst(key);
		overview.setPassiva(passivaLijst);

		BigDecimal privateDeposit = boekDao.getCostsWithPrivateMoney(beginDatum, eindDatum, Long.toString(userId));
		overview.setPrivateDeposit(privateDeposit.toBigInteger());
		
		// Private withdrawals
		int totalWithdrawal = profit - (bookTotalEnd - bookTotalBegin) + privateDeposit.intValue();
		privatWithdrawal.setTotaleOnttrekking(totalWithdrawal);
		int withdrawalCash = totalWithdrawal - privatWithdrawal.getWithdrawalPrivateUsageBusinessCar();
		privatWithdrawal.setWithdrawalCash(withdrawalCash);
		overview.setOnttrekking(privatWithdrawal);

		// Prepaid taxes
		PrepaidTax prepaidTax = TaxCodeHelper.findPrepaidTax(bookYear, Long.toString(userId));
		overview.setPrepaidTax(prepaidTax);
		return overview;
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

	public static int calculateProfit(FiscalOverview overview) {
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
		return nettoOmzet;
	}
}
