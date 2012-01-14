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
import org.techytax.dao.BoekwaardeDao;
import org.techytax.dao.FiscaalDao;
import org.techytax.domain.Activum;
import org.techytax.domain.Aftrekpost;
import org.techytax.domain.BalanceType;
import org.techytax.domain.Balans;
import org.techytax.domain.Boekwaarde;
import org.techytax.domain.FiscalOverview;
import org.techytax.domain.KeyYear;
import org.techytax.domain.Kost;
import org.techytax.domain.KostConstanten;
import org.techytax.domain.Liquiditeit;
import org.techytax.domain.Passivum;
import org.techytax.domain.PrepaidTax;
import org.techytax.domain.PrivatWithdrawal;
import org.techytax.props.PropsFactory;
import org.techytax.util.DateHelper;

public class FiscalOverviewHelper {

	public static FiscalOverview createFiscalOverview(String beginDatum, String eindDatum, List<Kost> boekingen, long userId) throws Exception {

		// Load properties
		Properties props = PropsFactory.loadProperties();

		FiscalOverview overview = new FiscalOverview();
		PrivatWithdrawal privatWithdrawal = new PrivatWithdrawal();
		Liquiditeit liquiditeit = null;

		Date datum = DateHelper.stringToDate(beginDatum);
		int bookYear = DateHelper.getYear(datum);
		int currentBookYear = DateHelper.getYear(new Date());

		// Maak winst-en-verlies rekening op
		Balans btwBalans = BalanceCalculator.calculateBtwBalance(boekingen, false);

		FiscaalDao fiscaalDao = new FiscaalDao();
		BoekwaardeDao boekwaardeDao = new BoekwaardeDao();
		BoekDao boekDao = new BoekDao();
		List<Aftrekpost> aftrekpostenLijst = boekDao.getDeductableCosts(beginDatum, eindDatum, Long.toString(userId));
		overview.setJaar(bookYear);
		overview.setNettoOmzet(btwBalans.getNettoOmzet().intValue());

		// Repurchase
		BigInteger repurchase = BalanceCalculator.getRepurchase(aftrekpostenLijst);
		overview.setRepurchase(repurchase);

		// Business car costs
		BigDecimal afschrijvingAuto = BalanceCalculator.getAfschrijvingAuto(aftrekpostenLijst);
		if (afschrijvingAuto != null) {
			overview.setAfschrijvingAuto(afschrijvingAuto.intValue());
			overview.setBijtellingAuto(BalanceCalculator.getFiscaleBijtelling(aftrekpostenLijst).intValue());
			overview.setKostenAuto(BalanceCalculator.getKostenVoorAuto(aftrekpostenLijst).intValue());
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
		BigDecimal depreciationOther = BalanceCalculator.getOverigeAfschrijvingen(aftrekpostenLijst);
		if (depreciationOther != null) {
			overview.setAfschrijvingOverig(BalanceCalculator.getOverigeAfschrijvingen(aftrekpostenLijst).intValue());
		}
		overview.setAfschrijvingTotaal(overview.getAfschrijvingAuto() - overview.getAfschrijvingAutoCorrectie() + overview.getAfschrijvingOverig() - overview.getAfschrijvingOverigCorrectie());

		overview.setKostenOverigTransport(BalanceCalculator.getReiskosten(aftrekpostenLijst).intValue());
		overview.setKostenOverig(BalanceCalculator.getEtenskosten(aftrekpostenLijst).add(BalanceCalculator.getAlgemeneKosten(aftrekpostenLijst)).intValue());

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
		Boekwaarde boekwaarde = null;
		if (bookYear == currentBookYear) {

			// Current assets
			boekwaarde = new Boekwaarde();
			boekwaarde.setBalanceType(BalanceType.CURRENT_ASSETS);
			boekwaarde.setJaar(bookYear);
			boekwaarde.setUserId(userId);
			boekwaarde = boekwaardeDao.getBoekwaardeDitJaar(boekwaarde);

			if (boekwaarde == null) {
				String startDate = props.getProperty("start.date");
				List<Kost> rekeningLijst = boekDao.getKostLijst(startDate, eindDatum, "rekeningBalans", Long.toString(userId));
				liquiditeit = BalanceCalculator.calculateAccountBalance(rekeningLijst);

				BigInteger saldo = liquiditeit.getRekeningBalans().toBigInteger();
				saldo = saldo.add(liquiditeit.getSpaarBalans().toBigInteger());
				boekwaarde = new Boekwaarde();
				boekwaarde.setJaar(bookYear);
				boekwaarde.setBalanceType(BalanceType.CURRENT_ASSETS);
				boekwaarde.setSaldo(saldo);
				boekwaarde.setUserId(userId);
				boekwaardeDao.insertBoekwaarde(boekwaarde);
			} else {
				Boekwaarde vorigeBoekwaarde = boekwaardeDao.getVorigeBoekwaarde(boekwaarde);
				BigInteger saldo = new BigInteger("0");
				if (vorigeBoekwaarde != null) {
					saldo = vorigeBoekwaarde.getSaldo();
				}
				List<Kost> rekeningLijst = boekDao.getKostLijst(beginDatum, eindDatum, "rekeningBalans", Long.toString(userId));
				liquiditeit = BalanceCalculator.calculateAccountBalance(rekeningLijst);
				saldo = saldo.add(liquiditeit.getRekeningBalans().toBigInteger());
				saldo = saldo.add(liquiditeit.getSpaarBalans().toBigInteger());
				boekwaarde.setSaldo(saldo);
				boekwaardeDao.updateBoekwaarde(boekwaarde);
			}

			// Machinery

			// Add or update boekwaarde
			BigDecimal totaalAfschrijvingenOverig = new BigDecimal("0");
			// List<Aftrekpost> aftrekpostenLijst =
			// boekDao.getDeductableCosts(DateHelper.getDate(periode.getBeginDatum()),
			// DateHelper.getDate(periode.getEindDatum()),
			// Long.toString(userId));
			totaalAfschrijvingenOverig = BalanceCalculator.getOverigeAfschrijvingen(aftrekpostenLijst);
			Boekwaarde activumValue = new Boekwaarde();
			activumValue.setJaar(bookYear);
			activumValue.setBalanceType(BalanceType.MACHINERY);
			activumValue.setUserId(userId);
			activumValue = boekwaardeDao.getVorigeBoekwaarde(activumValue);
			if (activumValue != null) {
				activumValue.setSaldo(activumValue.getSaldo().subtract(totaalAfschrijvingenOverig.toBigInteger()));
				// boekwaardeDao.insertBoekwaarde(activumValue);
			} else {
				// TODO
			}

			// Stock
			if (overview.getRepurchase() != null && overview.getRepurchase().intValue() > 0) {
				activumValue = new Boekwaarde();
				activumValue.setJaar(bookYear);
				activumValue.setUserId(userId);
				activumValue.setBalanceType(BalanceType.STOCK);
				activumValue = boekwaardeDao.getBoekwaardeDitJaar(activumValue);
				if (activumValue == null) {
					activumValue = new Boekwaarde();
					activumValue.setJaar(bookYear);
					activumValue.setUserId(userId);
					activumValue.setBalanceType(BalanceType.STOCK);
					activumValue = boekwaardeDao.getVorigeBoekwaarde(activumValue);
					if (activumValue == null) {
						activumValue = new Boekwaarde();
						activumValue.setBalanceType(BalanceType.STOCK);
						activumValue.setJaar(bookYear);
						activumValue.setUserId(userId);
						activumValue.setSaldo(overview.getRepurchase());
						boekwaardeDao.insertBoekwaarde(activumValue);
					} else {
						activumValue.setJaar(bookYear);
						activumValue.setSaldo(activumValue.getSaldo().add(overview.getRepurchase()));
						boekwaardeDao.insertBoekwaarde(activumValue);
					}
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
		if (bookYear == currentBookYear) {
			// Voer dezelfde FOR op.
			boekwaarde = new Boekwaarde();
			boekwaarde.setBalanceType(BalanceType.PENSION);
			boekwaarde.setJaar(bookYear);
			boekwaarde.setUserId(userId);
			boekwaarde = boekwaardeDao.getBoekwaardeDitJaar(boekwaarde);

			if (boekwaarde == null) {
				boekwaarde = new Boekwaarde();
				boekwaarde.setBalanceType(BalanceType.PENSION);
				boekwaarde.setJaar(bookYear);
				boekwaarde.setUserId(userId);
				boekwaarde = boekwaardeDao.getVorigeBoekwaarde(boekwaarde);

				if (boekwaarde != null) {
					FOR = boekwaarde.getSaldo().intValue();
					boekwaarde.setId(0);
					boekwaarde.setJaar(bookYear);
					boekwaarde.setUserId(userId);
					boekwaardeDao.insertBoekwaarde(boekwaarde);
				}
			} else {
				FOR = boekwaarde.getSaldo().intValue();
			}
		}

		// Get balance totals
		int bookTotalBegin = getBalansTotaal(activaLijst, bookYear - 1);
		int bookTotalEnd = getBalansTotaal(activaLijst, bookYear);
		overview.setBookTotalBegin(bookTotalBegin);
		overview.setBookTotalEnd(bookTotalEnd);

		if (bookYear == currentBookYear) {
			// Voer eigen vermogen op.
			boekwaarde = new Boekwaarde();
			boekwaarde.setBalanceType(BalanceType.NON_CURRENT_ASSETS);
			boekwaarde.setJaar(bookYear);
			boekwaarde.setUserId(userId);
			boekwaarde = boekwaardeDao.getBoekwaardeDitJaar(boekwaarde);
			if (boekwaarde == null) {
				boekwaarde = new Boekwaarde();
				boekwaarde.setBalanceType(BalanceType.NON_CURRENT_ASSETS);
				boekwaarde.setJaar(bookYear);
				boekwaarde.setSaldo(BigInteger.valueOf(bookTotalEnd - FOR));
				boekwaarde.setUserId(userId);
				boekwaardeDao.insertBoekwaarde(boekwaarde);
			} else {
				boekwaarde.setSaldo(BigInteger.valueOf(bookTotalEnd - FOR));
				boekwaardeDao.updateBoekwaarde(boekwaarde);
			}
		}

		KeyYear key = new KeyYear();
		key.setUserId(userId);
		key.setYear(bookYear);
		List<Passivum> passivaLijst = fiscaalDao.getPassivaLijst(key);
		overview.setPassiva(passivaLijst);

		// Vul prive onttrekking in
		int totalWithdrawal = profit - (bookTotalEnd - bookTotalBegin);
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
		while (iterator.hasNext()) {
			Activum activa = iterator.next();
			if (activa.getBoekjaar() == fiscaalJaar) {
				totaal += activa.getSaldo().intValue();
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
