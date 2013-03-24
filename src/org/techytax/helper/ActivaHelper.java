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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.techytax.dao.BoekDao;
import org.techytax.dao.BookValueDao;
import org.techytax.dao.FiscalDao;
import org.techytax.domain.Activum;
import org.techytax.domain.BalanceType;
import org.techytax.domain.BookValue;
import org.techytax.domain.Cost;
import org.techytax.domain.DeductableCostGroup;
import org.techytax.domain.FiscalOverview;
import org.techytax.domain.KeyId;
import org.techytax.domain.KeyYear;
import org.techytax.domain.Liquiditeit;
import org.techytax.domain.RemainingValue;

public class ActivaHelper {
	
	private static FiscalDao fiscaalDao = new FiscalDao();
	private static BookValueDao bookValueDao = new BookValueDao();
	private static BoekDao boekDao = new BoekDao();
	
	public static List<Activum> handleActiva(String beginDatum, String eindDatum, long userId, Locale locale, Properties props,
			FiscalOverview overview, int bookYear, int currentBookYear, List<DeductableCostGroup> deductableCosts) throws Exception {
		if (bookYear == currentBookYear) {

			KeyYear keyYear = new KeyYear(userId, bookYear);
			handleCurrentAssets(beginDatum, eindDatum, userId, props, bookYear, keyYear);

			handleMachinery(beginDatum, userId, bookYear, deductableCosts);
			
			handleSettlement(beginDatum, userId, bookYear, deductableCosts);

			handleBusinessCar(userId, overview, bookYear);

			handleStock(userId, overview, bookYear);

			handleInvoicedToBePaid(userId, overview, bookYear);

		}

		KeyYear keyYear = new KeyYear(userId, bookYear);
		List<Activum> activaLijst = getAndTranslate(locale, keyYear);

		if (!checkActivaOpgegeven(activaLijst, bookYear)) {
			throw new Exception("errors.fiscal.activa");
		}
		overview.setActiva(activaLijst);
		return activaLijst;
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
	
	private static List<Activum> getAndTranslate(Locale locale, KeyYear keyYear) throws Exception {
		List<Activum> activaLijst = fiscaalDao.getActivaLijst(keyYear);
		for (Activum activum : activaLijst) {
			activum.setOmschrijving(Translator.translateKey(activum.getOmschrijving(), locale));
		}
		return activaLijst;
	}

	private static void handleInvoicedToBePaid(long userId, FiscalOverview overview, int bookYear) throws Exception {
		BookValue activumValue;
		BookValue currentBookValue;
		if (overview.getTurnOverUnpaid() != null && overview.getTurnOverUnpaid().compareTo(new BigDecimal("0")) != 0) {
			// Invoices yet to be paid
			activumValue = new BookValue();
			activumValue.setJaar(bookYear);
			activumValue.setBalanceType(BalanceType.INVOICES_TO_BE_PAID);
			activumValue.setUserId(userId);
			activumValue.setSaldo(overview.getTurnOverUnpaid().toBigInteger());

			currentBookValue = bookValueDao.getBookValueThisYear(activumValue);

			if (currentBookValue == null) {
				bookValueDao.insertBookValue(activumValue);
			} else {
				activumValue.setId(currentBookValue.getId());
				bookValueDao.updateBookValue(activumValue);
			}
		}
	}

	private static void handleStock(long userId, FiscalOverview overview, int bookYear) throws Exception {
		BookValue activumValue;
		// Stock
		if (overview.getRepurchase() != null && overview.getRepurchase().intValue() > 0) {
			activumValue = new BookValue();
			activumValue.setJaar(bookYear);
			activumValue.setUserId(userId);
			activumValue.setBalanceType(BalanceType.STOCK);
			activumValue = bookValueDao.getBookValueThisYear(activumValue);
			if (activumValue == null) {
				activumValue = new BookValue();
				activumValue.setJaar(bookYear);
				activumValue.setUserId(userId);
				activumValue.setBalanceType(BalanceType.STOCK);
				activumValue = bookValueDao.getPreviousBookValue(activumValue);
				if (activumValue == null) {
					activumValue = new BookValue();
					activumValue.setBalanceType(BalanceType.STOCK);
					activumValue.setJaar(bookYear);
					activumValue.setUserId(userId);
					activumValue.setSaldo(overview.getRepurchase());
					bookValueDao.insertBookValue(activumValue);
				} else {
					activumValue.setJaar(bookYear);
					activumValue.setSaldo(activumValue.getSaldo().add(overview.getRepurchase()));
					bookValueDao.insertBookValue(activumValue);
				}
			}
		}
	}

	private static void handleBusinessCar(long userId, FiscalOverview overview, int bookYear) throws Exception {
		BookValue activumValue;
		// Car
		BigInteger carDepreciation = BigInteger.valueOf(overview.getAfschrijvingAuto());
		activumValue = new BookValue();
		activumValue.setJaar(bookYear);
		activumValue.setBalanceType(BalanceType.CAR);
		activumValue.setUserId(userId);
		activumValue = bookValueDao.getPreviousBookValue(activumValue);
		if (activumValue != null) {
			BigInteger carBookValue = activumValue.getSaldo();
			activumValue.setJaar(bookYear);
			activumValue = bookValueDao.getBookValueThisYear(activumValue);
			if (activumValue == null) {
				activumValue = new BookValue();
				activumValue.setBalanceType(BalanceType.CAR);
				activumValue.setUserId(userId);
				activumValue.setJaar(bookYear);
				activumValue.setSaldo(carBookValue.subtract(carDepreciation));
				bookValueDao.insertBookValue(activumValue);
			} else {
				activumValue.setBalanceType(BalanceType.CAR);
				activumValue.setUserId(userId);
				activumValue.setJaar(bookYear);
				activumValue.setSaldo(carBookValue.subtract(carDepreciation));
				bookValueDao.updateBookValue(activumValue);
			}
		} else {
		}
	}

	private static void handleMachinery(String beginDatum, long userId, int bookYear, List<DeductableCostGroup> deductableCosts) throws Exception {
		BookValue activumValue = new BookValue();
		activumValue.setJaar(bookYear);
		activumValue.setBalanceType(BalanceType.MACHINERY);
		activumValue.setUserId(userId);

		BookValue previousBookValue = bookValueDao.getPreviousBookValue(activumValue);
		BookValue currentBookValue = activumValue = bookValueDao.getBookValueThisYear(activumValue);

		if (activumValue == null) {
			throw new RuntimeException("Add machinery activa");
		}

		Activum activum = new Activum();
		activum.setUserId(userId);
		activum.setBalanceType(BalanceType.MACHINERY);
		activum.setEndDate(beginDatum);
		BigInteger totalCost = boekDao.getTotalCostForActivumThisYear(activum);

		if (previousBookValue != null) {
			BigDecimal totaalAfschrijvingenOverig = BalanceCalculator.getOverigeAfschrijvingen(deductableCosts);
			BigInteger newSaldo = previousBookValue.getSaldo().add(totalCost).subtract(totaalAfschrijvingenOverig.toBigInteger());
			KeyId key = new KeyId();
			key.setUserId(userId);
			List<RemainingValue> remainingValues = bookValueDao.getRemainingValueForMachines(key);
			
			BigInteger totalRemainingValue = new BigInteger("0");
			for (RemainingValue remainingValue : remainingValues) {
				totalRemainingValue = totalRemainingValue.add(remainingValue.getRestwaarde());
			}
			if (currentBookValue == null) {
				previousBookValue.setId(0);
				previousBookValue.setSaldo(newSaldo);
				bookValueDao.insertBookValue(activumValue);
			} else {
				if (totalRemainingValue.compareTo(newSaldo) == -1) {
					activumValue.setSaldo(newSaldo);
				} else {
					activumValue.setSaldo(totalRemainingValue);
				}
				bookValueDao.updateBookValue(activumValue);
			}

		} else {
			if (totalCost.compareTo(new BigInteger("0")) == 1) {
				if (currentBookValue == null) {
					activumValue = new BookValue();
					activumValue.setBalanceType(BalanceType.MACHINERY);
					activumValue.setJaar(bookYear);
					activumValue.setUserId(userId);
					activumValue.setSaldo(totalCost);
					bookValueDao.insertBookValue(activumValue);
				} else {
					currentBookValue.setSaldo(totalCost);
					bookValueDao.updateBookValue(currentBookValue);
				}
			}
		}
	}
	
	private static void handleSettlement(String beginDatum, long userId, int bookYear, List<DeductableCostGroup> deductableCosts) throws Exception {
		BookValue activumValue = new BookValue();
		activumValue.setJaar(bookYear);
		activumValue.setBalanceType(BalanceType.OFFICE);
		activumValue.setUserId(userId);

		BookValue previousBookValue = bookValueDao.getPreviousBookValue(activumValue);
		BookValue currentBookValue = activumValue = bookValueDao.getBookValueThisYear(activumValue);

		Activum activum = new Activum();
		activum.setUserId(userId);
		activum.setBalanceType(BalanceType.OFFICE);
		activum.setEndDate(beginDatum);
		BigInteger totalCost = boekDao.getTotalCostForActivumThisYear(activum);

		if (previousBookValue != null) {
			BigDecimal depreciationSettlement = BalanceCalculator.getDepreciationSettlement(deductableCosts);
			BigInteger newSaldo = previousBookValue.getSaldo().add(totalCost).subtract(depreciationSettlement.toBigInteger());
			if (currentBookValue == null) {
				previousBookValue.setId(0);
				previousBookValue.setSaldo(newSaldo);
				bookValueDao.insertBookValue(activumValue);
			} else {
				activumValue.setSaldo(newSaldo);
				bookValueDao.updateBookValue(activumValue);
			}

		} else {
			if (totalCost.compareTo(new BigInteger("0")) == 1) {
				if (currentBookValue == null) {
					activumValue = new BookValue();
					activumValue.setBalanceType(BalanceType.OFFICE);
					activumValue.setJaar(bookYear);
					activumValue.setUserId(userId);
					activumValue.setSaldo(totalCost);
					bookValueDao.insertBookValue(activumValue);
				} else {
					currentBookValue.setSaldo(totalCost);
					bookValueDao.updateBookValue(currentBookValue);
				}
			}
		}
	}	

	private static void handleCurrentAssets(String beginDatum, String eindDatum, long userId, Properties props, int bookYear, KeyYear keyYear)
			throws Exception {
		Liquiditeit liquiditeit;
		BookValue boekwaarde;
		boekwaarde = BookValueHelper.getCurrentBookValue(BalanceType.CURRENT_ASSETS, keyYear);
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
			bookValueDao.insertBookValue(boekwaarde);
		} else {
			BookValue vorigeBoekwaarde = bookValueDao.getPreviousBookValue(boekwaarde);
			BigInteger saldo = new BigInteger("0");
			if (vorigeBoekwaarde != null) {
				saldo = vorigeBoekwaarde.getSaldo();
			}
			List<Cost> rekeningLijst = boekDao.getKostLijst(beginDatum, eindDatum, "rekeningBalans", Long.toString(userId));
			liquiditeit = BalanceCalculator.calculateAccountBalance(rekeningLijst);
			saldo = saldo.add(liquiditeit.getRekeningBalans().toBigInteger());
			saldo = saldo.add(liquiditeit.getSpaarBalans().toBigInteger());
			boekwaarde.setSaldo(saldo);
			bookValueDao.updateBookValue(boekwaarde);
		}
	}
	
}