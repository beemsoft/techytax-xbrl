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
package org.techytax.helper;

import static org.techytax.domain.BalanceType.CAR;
import static org.techytax.domain.BalanceType.CURRENT_ASSETS;
import static org.techytax.domain.BalanceType.INVOICES_TO_BE_PAID;
import static org.techytax.domain.BalanceType.MACHINERY;
import static org.techytax.domain.BalanceType.OFFICE;
import static org.techytax.domain.BalanceType.STOCK;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.techytax.cache.CostCache;
import org.techytax.dao.BookValueDao;
import org.techytax.dao.CostDao;
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
import org.techytax.domain.User;
import org.techytax.jpa.dao.GenericDao;
import org.techytax.util.DateHelper;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.util.Locales;

public class ActivaHelper {

	private FiscalDao fiscalDao = new FiscalDao();
	private BookValueDao bookValueDao = new BookValueDao();
	private CostDao costDao = new CostDao();
	private User user = UserCredentialManager.getUser();
	private BookValueHelper bookValueHelper = new BookValueHelper();
	private GenericDao<BookValue> bookValueGenericDao = new GenericDao<BookValue>(BookValue.class, user);
	private FiscalOverview fiscalOverview;
	private CostCache costCache;
	private int bookYear;
	
	public ActivaHelper(FiscalOverview fiscalOverview, CostCache costCache) {
		this.fiscalOverview = fiscalOverview;
		this.costCache = costCache;
		bookYear = DateHelper.getYear(new Date()) - 1;
	}
	
	public List<Activum> handleActiva(Properties props, List<DeductableCostGroup> deductableCosts, List<Cost> rekeningLijst) throws Exception {

		KeyYear keyYear = new KeyYear(user.getId(), bookYear);
		handleCurrentAssets(props, keyYear, rekeningLijst);

		handleMachinery(deductableCosts);

		handleSettlement(deductableCosts);

		handleBusinessCar();

		handleStock();

		handleInvoicesToBePaid();

		List<Activum> activaLijst = getAndTranslate(Locales.getCurrent(), keyYear);

		if (!checkActivaOpgegeven(activaLijst, bookYear)) {
			throw new Exception("errors.fiscal.activa");
		}
		fiscalOverview.setActiva(activaLijst);
		return activaLijst;
	}

	private boolean checkActivaOpgegeven(List<Activum> activaLijst, int fiscaalJaar) {
		Iterator<Activum> iterator = activaLijst.iterator();
		while (iterator.hasNext()) {
			Activum activa = iterator.next();
			if (activa.getBoekjaar() == fiscaalJaar) {
				return true;
			}
		}
		return false;
	}

	public List<Activum> getAndTranslate(Locale locale, KeyYear keyYear) throws Exception {
		List<Activum> activaLijst = fiscalDao.getActivaLijst(keyYear);
		for (Activum activum : activaLijst) {
			activum.setOmschrijving(Translator.translateKey(activum.getOmschrijving(), locale));
		}
		return activaLijst;
	}

	private void handleInvoicesToBePaid() throws Exception {
		BookValue activumValue;
		BookValue currentBookValue;
		if (fiscalOverview.getTurnOverUnpaid() != null && fiscalOverview.getTurnOverUnpaid().compareTo(BigDecimal.ZERO) != 0) {
			// Invoices yet to be paid
			activumValue = new BookValue();
			activumValue.setJaar(bookYear);
			activumValue.setBalanceType(INVOICES_TO_BE_PAID);
			activumValue.setUser(user);
			activumValue.setSaldo(fiscalOverview.getTurnOverUnpaid().toBigInteger());

			currentBookValue = bookValueDao.getBookValue(BalanceType.INVOICES_TO_BE_PAID, bookYear);

			if (currentBookValue == null) {
				bookValueGenericDao.persistEntity(activumValue);
			} else {
				activumValue.setId(currentBookValue.getId());
				// bookValueDao.updateBookValue(activumValue);
			}
		}
	}

	private void handleStock() throws Exception {
		BookValue activumValue;
		if (fiscalOverview.getRepurchase() != null && fiscalOverview.getRepurchase().intValue() > 0) {
			activumValue = bookValueDao.getBookValue(BalanceType.STOCK, bookYear);
			if (activumValue == null) {
				activumValue = bookValueDao.getBookValue(BalanceType.STOCK, bookYear - 1);
				if (activumValue == null) {
					activumValue = new BookValue();
					activumValue.setBalanceType(STOCK);
					activumValue.setJaar(bookYear);
					activumValue.setUser(user);
					activumValue.setSaldo(fiscalOverview.getRepurchase());
					bookValueGenericDao.persistEntity(activumValue);
				} else {
					activumValue.setJaar(bookYear);
					activumValue.setSaldo(activumValue.getSaldo().add(fiscalOverview.getRepurchase()));
					bookValueGenericDao.persistEntity(activumValue);
				}
			}
		}
	}

	private void handleBusinessCar() throws Exception {
		BigInteger carDepreciation = BigInteger.valueOf(fiscalOverview.getAfschrijvingAuto());
		BookValue previousValue = bookValueDao.getBookValue(CAR, bookYear - 1);
		if (previousValue != null) {
			BigInteger carBookValue = previousValue.getSaldo();
			BookValue currentValue = bookValueDao.getBookValue(CAR, bookYear);
			if (currentValue == null) {
				BookValue newValue = new BookValue();
				newValue.setBalanceType(CAR);
				newValue.setUser(user);
				newValue.setJaar(bookYear);
				newValue.setSaldo(carBookValue.subtract(carDepreciation));
				bookValueGenericDao.persistEntity(newValue);
			} else {
				currentValue.setSaldo(carBookValue.subtract(carDepreciation));
			}
		} 
	}

	private void handleMachinery(List<DeductableCostGroup> deductableCosts) throws Exception {
		BalanceType balanceType = MACHINERY;

		BookValue previousBookValue = bookValueDao.getBookValue(balanceType, bookYear - 1);
		BookValue currentBookValue = bookValueDao.getBookValue(balanceType, bookYear);

		Activum activum = createActivum(balanceType);
		activum.setEndDate(costCache.getBeginDatum());
		BigInteger totalCost = costDao.getTotalCostForActivum(activum);

		if (currentBookValue != null && previousBookValue != null) {
			BigDecimal totaalAfschrijvingenOverig = BalanceCalculator.getOverigeAfschrijvingen(deductableCosts);
			BigInteger newSaldo = previousBookValue.getSaldo().add(totalCost).subtract(totaalAfschrijvingenOverig.toBigInteger());
			BigInteger totalRemainingValue = calculateTotalRemaingValueForMachinery();
			if (totalRemainingValue.compareTo(newSaldo) == -1) {
				currentBookValue.setSaldo(newSaldo);
			} else {
				currentBookValue.setSaldo(totalRemainingValue);
			}
		} else {
			insertOrUpdateBookValue(balanceType, currentBookValue, totalCost);
		}
	}

	private void insertOrUpdateBookValue(BalanceType balanceType, BookValue currentBookValue, BigInteger totalCost) {
		if (totalCost.compareTo(BigInteger.ZERO) == 1) {
			if (currentBookValue == null) {
				BookValue bookValue = createBookValue(balanceType);
				bookValue.setSaldo(totalCost);
				bookValueGenericDao.persistEntity(bookValue);
			} else {
				currentBookValue.setSaldo(totalCost);
			}
		}
	}

	private BigInteger calculateTotalRemaingValueForMachinery() throws Exception {
		KeyId key = new KeyId();
		key.setUserId(user.getId());
		List<RemainingValue> remainingValues = bookValueDao.getRemainingValueForMachines(key);

		BigInteger totalRemainingValue = BigInteger.ZERO;
		for (RemainingValue remainingValue : remainingValues) {
			totalRemainingValue = totalRemainingValue.add(remainingValue.getRestwaarde());
		}
		return totalRemainingValue;
	}

	private Activum createActivum(BalanceType balanceType) {
		Activum activum = new Activum();
		activum.setUser(user);
		activum.setBalanceType(balanceType);
		return activum;
	}

	private BookValue createBookValue(BalanceType balanceType) {
		BookValue bookValue = new BookValue();
		bookValue.setJaar(bookYear);
		bookValue.setBalanceType(balanceType);
		bookValue.setUser(user);
		return bookValue;
	}

	private void handleSettlement(List<DeductableCostGroup> deductableCosts) throws Exception {
		BookValue activumValue = new BookValue();
		activumValue.setJaar(bookYear);
		activumValue.setBalanceType(OFFICE);
		activumValue.setUser(user);

		BookValue previousBookValue = bookValueDao.getBookValue(BalanceType.OFFICE, bookYear - 1);
		BookValue currentBookValue = activumValue = bookValueDao.getBookValue(BalanceType.OFFICE, bookYear);

		Activum activum = new Activum();
		activum.setUser(user);
		activum.setBalanceType(OFFICE);
		BigInteger totalCost = costDao.getTotalCostForActivum(activum);

		if (previousBookValue != null) {
			BigDecimal depreciationSettlement = BalanceCalculator.getDepreciationSettlement(deductableCosts);
			BigInteger newSaldo = previousBookValue.getSaldo().add(totalCost).subtract(depreciationSettlement.toBigInteger());
			if (currentBookValue == null) {
			} else {
				activumValue.setSaldo(newSaldo);
				bookValueGenericDao.merge(activumValue);
			}

		} else {
			if (totalCost.compareTo(BigInteger.ZERO) == 1) {
				if (currentBookValue == null) {
					activumValue = new BookValue();
					activumValue.setBalanceType(OFFICE);
					activumValue.setJaar(bookYear);
					activumValue.setUser(user);
					activumValue.setSaldo(totalCost);
					bookValueGenericDao.persistEntity(activumValue);
				} else {
					currentBookValue.setSaldo(totalCost);
					bookValueGenericDao.merge(currentBookValue);
				}
			}
		}
	}

	private void handleCurrentAssets(Properties props, KeyYear keyYear, List<Cost> rekeningLijst) throws Exception {
		Liquiditeit liquiditeit = BalanceCalculator.calculateAccountBalance(rekeningLijst);
		BookValue boekwaarde = bookValueHelper.getCurrentBookValue(CURRENT_ASSETS, keyYear);
		if (boekwaarde == null) {
			BigInteger saldo = liquiditeit.getRekeningBalans().toBigInteger();
			saldo = saldo.add(liquiditeit.getSpaarBalans().toBigInteger());
			boekwaarde = new BookValue();
			boekwaarde.setJaar(bookYear);
			boekwaarde.setBalanceType(CURRENT_ASSETS);
			boekwaarde.setSaldo(saldo);
			boekwaarde.setUser(user);
			bookValueGenericDao.persistEntity(boekwaarde);
		} else {
			BookValue vorigeBoekwaarde = bookValueDao.getBookValue(BalanceType.CURRENT_ASSETS, bookYear - 1);
			BigInteger saldo = BigInteger.ZERO;
			if (vorigeBoekwaarde != null) {
				saldo = vorigeBoekwaarde.getSaldo();
			}
			saldo = saldo.add(liquiditeit.getRekeningBalans().toBigInteger());
			saldo = saldo.add(liquiditeit.getSpaarBalans().toBigInteger());
			boekwaarde.setSaldo(saldo);
			bookValueGenericDao.merge(boekwaarde);
		}
	}

}