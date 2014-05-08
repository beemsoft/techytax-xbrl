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

import static org.techytax.domain.BalanceType.MACHINERY;
import static org.techytax.domain.BalanceType.NON_CURRENT_ASSETS;
import static org.techytax.domain.BalanceType.PENSION;
import static org.techytax.domain.BalanceType.VAT_TO_BE_PAID;
import static org.techytax.domain.CostConstants.FOR_PERCENTAGE;
import static org.techytax.domain.CostConstants.MAXIMALE_FOR;
import static org.techytax.helper.AmountHelper.roundToInteger;
import static org.techytax.helper.BalanceCalculator.getAfschrijvingAuto;
import static org.techytax.helper.BalanceCalculator.getAlgemeneKosten;
import static org.techytax.helper.BalanceCalculator.getDepreciationSettlement;
import static org.techytax.helper.BalanceCalculator.getFiscaleBijtelling;
import static org.techytax.helper.BalanceCalculator.getFoodCosts;
import static org.techytax.helper.BalanceCalculator.getKostenVoorAuto;
import static org.techytax.helper.BalanceCalculator.getReiskosten;
import static org.techytax.helper.BalanceCalculator.getSettlementCosts;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import org.techytax.domain.FiscalBalance;
import org.techytax.domain.FiscalOverview;
import org.techytax.domain.Periode;
import org.techytax.domain.PrepaidTax;
import org.techytax.domain.PrivatWithdrawal;
import org.techytax.domain.User;
import org.techytax.domain.VatBalanceWithinEu;
import org.techytax.jpa.dao.GenericDao;
import org.techytax.props.PropsFactory;
import org.techytax.util.DateHelper;
import org.techytax.zk.login.UserCredentialManager;

public class FiscalOverviewHelper {

	private User user = UserCredentialManager.getUser();
	private FiscalDao fiscalDao = new FiscalDao();
	private GenericDao<BookValue> bookValueGenericDao = new GenericDao<>(BookValue.class);
	private BookValueDao bookValueDao = new BookValueDao();
	private CostCache costCache = new CostCache();
	private CostDao costDao = new CostDao();
	private FiscalOverview overview = new FiscalOverview();
	private InvestmentDeductionHelper investmentDeductionHelper = new InvestmentDeductionHelper();
	private int bookYear;
	private Map<BalanceType, FiscalBalance> passivaMap = new HashMap<>();

	public FiscalOverviewHelper() throws Exception {
		bookYear = DateHelper.getFiscalYear();
	}

	public FiscalOverview createFiscalOverview(Date beginDatum, Date eindDatum) throws Exception {

		// Load properties
		Properties props = PropsFactory.loadProperties();
		costCache.setBeginDatum(beginDatum);
		costCache.setEindDatum(eindDatum);
		PrivatWithdrawal privatWithdrawal = new PrivatWithdrawal();

		VatBalanceWithinEu vatBalanceWithinEu = BalanceCalculator.calculateBtwBalance(costCache.getCosts(), false);
		List<DeductableCostGroup> deductableCosts = costCache.getDeductableCosts();
		overview.setJaar(bookYear);

		handleProfitAndLoss(privatWithdrawal, vatBalanceWithinEu, deductableCosts);

		ActivaHelper activaHelper = new ActivaHelper(overview, costCache);
		Map<BalanceType, FiscalBalance> activaMap = activaHelper.handleActiva(props, deductableCosts, costCache.getBusinessAccountCosts());
		overview.setActivaMap(activaMap);

		List<BookValue> activumListThisYear = fiscalDao.getActivaList(bookYear);
		List<BookValue> activumListPreviousYear = fiscalDao.getActivaList(bookYear - 1);
		setBalanceTotals(activumListThisYear, activumListPreviousYear);

		handlePassiva();
		overview.setPassivaMap(passivaMap);

		List<BookValue> passivaList = fiscalDao.getPassivaList(bookYear);

		BigInteger enterpriseCapital = getEnterpriseCapital(passivaList);
		overview.setEnterpriseCapital(enterpriseCapital);

		BigDecimal privateDeposit = handlePrivateDeposits();

		handlePrivateWithdrawals(privatWithdrawal, passivaList, enterpriseCapital, privateDeposit);

		handlePrepaidTaxes();

		return overview;
	}

	private void setBalanceTotals(List<BookValue> activumListThisYear, List<BookValue> activumListPreviousYear) {
		BigInteger bookTotalBegin = getBalansTotaal(activumListPreviousYear);
		BigInteger bookTotalEnd = getBalansTotaal(activumListThisYear);
		overview.setBookTotalBegin(bookTotalBegin);
		overview.setBookTotalEnd(bookTotalEnd);

	}

	private void handleProfitAndLoss(PrivatWithdrawal privatWithdrawal, VatBalanceWithinEu vatBalanceWithinEu, List<DeductableCostGroup> deductableCosts) throws Exception {
		handleTurnOver(vatBalanceWithinEu);

		handleRepurchase();

		handleInterest();

		handleCar(privatWithdrawal, deductableCosts);

		handleDepreciations(deductableCosts);

		handleCosts(deductableCosts);

		calculateProfit();

		setMaximalFiscalPension();

		handleInvestments();
	}

	private void handleInvestments() throws Exception {
		List<Cost> investmentKostList = costCache.getInvestments();
		overview.setInvestmentDeduction(investmentDeductionHelper.getInvestmentDeduction(investmentKostList));
	}

	private void setMaximalFiscalPension() {
		BigInteger maximaleFor = new BigDecimal(overview.getWinst()).multiply(FOR_PERCENTAGE).toBigInteger();
		if (maximaleFor.compareTo(MAXIMALE_FOR) == 1) {
			maximaleFor = MAXIMALE_FOR;
		}
		if (maximaleFor.compareTo(BigInteger.ZERO) == -1) {
			maximaleFor = BigInteger.ZERO;
		}
		overview.setOudedagsReserveMaximaal(maximaleFor);
	}

	private void handleTurnOver(VatBalanceWithinEu vatBalanceWithinEu) throws Exception {
		if (vatBalanceWithinEu.getBrutoOmzet().compareTo(BigDecimal.ZERO) == 0) {
			List<Cost> balanceCosts = costCache.getBusinessAccountCosts();
			BigDecimal turnover = BalanceCalculator.calculateTotalPaidInvoices(balanceCosts);
			vatBalanceWithinEu.setNettoOmzet(turnover);
		}
		overview.setNettoOmzet(AmountHelper.roundDownToInteger(vatBalanceWithinEu.getNettoOmzet()));
	}

	private void handleRepurchase() throws Exception {
		BigInteger repurchase = AmountHelper.roundToInteger(costCache.getRepurchases());
		overview.setRepurchase(repurchase);
	}

	private void handlePassiva() throws Exception {

		BigInteger fiscalPension = handleFiscalPension();

		BigDecimal vatDebt = handleVatToBePaid();

		handleNonCurrentAssets(fiscalPension, vatDebt);
	}

	private void handleNonCurrentAssets(BigInteger fiscalPension, BigDecimal vatDebt) throws Exception {
		BookValue currentBookValue = bookValueDao.getBookValue(NON_CURRENT_ASSETS, bookYear);
		BookValue previousBookValue = bookValueDao.getBookValue(NON_CURRENT_ASSETS, bookYear - 1);
		BigInteger bookTotalNonCurrentAssets = overview.getBookTotalEnd();
		bookTotalNonCurrentAssets = bookTotalNonCurrentAssets.subtract(fiscalPension);
		bookTotalNonCurrentAssets = bookTotalNonCurrentAssets.subtract(roundToInteger(vatDebt));
		if (currentBookValue == null) {
			BookValue newValue = new BookValue(NON_CURRENT_ASSETS, bookYear, bookTotalNonCurrentAssets);
			newValue.setUser(user);
			bookValueGenericDao.persistEntity(newValue);
		} else {
			currentBookValue.setSaldo(bookTotalNonCurrentAssets);
		}
		if (previousBookValue != null || currentBookValue != null) {
			FiscalBalance fiscalBalance = new FiscalBalance();
			if (previousBookValue != null) {
				fiscalBalance.setBeginSaldo(previousBookValue.getSaldo());
			}
			fiscalBalance.setEndSaldo(currentBookValue.getSaldo());
			passivaMap.put(NON_CURRENT_ASSETS, fiscalBalance);
		}
	}

	private BigDecimal handleVatToBePaid() throws Exception {
		BookValue currentBookValue = bookValueDao.getBookValue(VAT_TO_BE_PAID, bookYear);
		BookValue previousBookValue = bookValueDao.getBookValue(VAT_TO_BE_PAID, bookYear - 1);
		Periode lastVatPeriod = DateHelper.getLastVatPeriodPreviousYear();
		BigDecimal vatDebt = costDao.getVatDebtFromPreviousYear(lastVatPeriod.getBeginDatum(), lastVatPeriod.getEindDatum());

		if (vatDebt != null && vatDebt.compareTo(BigDecimal.ZERO) == 1) {

			if (currentBookValue == null) {
				BookValue newValue = new BookValue(VAT_TO_BE_PAID, bookYear, vatDebt.toBigInteger());
				newValue.setUser(user);
				bookValueGenericDao.persistEntity(newValue);
			} else {
				currentBookValue.setSaldo(vatDebt.toBigInteger());
			}
		}
		if (previousBookValue != null || currentBookValue != null) {
			FiscalBalance fiscalBalance = new FiscalBalance();
			fiscalBalance.setBeginSaldo(previousBookValue.getSaldo());
			fiscalBalance.setEndSaldo(currentBookValue.getSaldo());
			passivaMap.put(VAT_TO_BE_PAID, fiscalBalance);
		}
		return vatDebt;
	}

	private BigInteger handleFiscalPension() throws Exception {
		BigInteger fiscalPension = BigInteger.ZERO;
		BookValue currentBookValue = bookValueDao.getBookValue(PENSION, bookYear);
		BookValue previousBookValue = bookValueDao.getBookValue(PENSION, bookYear - 1);
		if (currentBookValue == null) {
			if (previousBookValue != null) {
				fiscalPension = previousBookValue.getSaldo();
				BookValue newValue = new BookValue(PENSION, bookYear, fiscalPension);
				newValue.setUser(user);
				bookValueGenericDao.persistEntity(newValue);
			}
		} else {
			fiscalPension = currentBookValue.getSaldo();
		}
		if (previousBookValue != null || currentBookValue != null) {
			FiscalBalance fiscalBalance = new FiscalBalance();
			fiscalBalance.setBeginSaldo(previousBookValue.getSaldo());
			fiscalBalance.setEndSaldo(currentBookValue.getSaldo());
			passivaMap.put(PENSION, fiscalBalance);
		}
		return fiscalPension;
	}

	private BigDecimal handlePrivateDeposits() throws Exception {
		BigDecimal privateDeposit = costCache.getCostsWithPrivateMoney();
		overview.setPrivateDeposit(privateDeposit.toBigInteger());
		return privateDeposit;
	}

	private void handlePrepaidTaxes() throws Exception {
		PrepaidTax prepaidTax = costCache.getPrepaidTax();
		overview.setPrepaidTax(prepaidTax);
	}

	private void handlePrivateWithdrawals(PrivatWithdrawal privatWithdrawal, List<BookValue> passivaLijst, BigInteger enterpriseCapital, BigDecimal privateDeposit) throws Exception {
		List<BookValue> passivaListPreviousYear = fiscalDao.getPassivaList(bookYear - 1);
		BigInteger enterpriseCapitalPreviousYear = getEnterpriseCapital(passivaListPreviousYear);
		BigInteger totalWithdrawal = overview.getProfit();
		totalWithdrawal = totalWithdrawal.subtract(enterpriseCapital.subtract(enterpriseCapitalPreviousYear));
		totalWithdrawal = totalWithdrawal.add(roundToInteger(privateDeposit));
		privatWithdrawal.setTotaleOnttrekking(totalWithdrawal);
		BigInteger withdrawalCash = totalWithdrawal;
		withdrawalCash = withdrawalCash.subtract(privatWithdrawal.getWithdrawalPrivateUsageBusinessCar());
		privatWithdrawal.setWithdrawalCash(withdrawalCash);
		overview.setOnttrekking(privatWithdrawal);
	}

	private void handleInterest() throws Exception {
		BigInteger interest = costCache.getInterest().toBigInteger();
		overview.setInterestFromBusinessSavings(interest);
	}

	private void handleDepreciations(List<DeductableCostGroup> deductableCosts) throws Exception {
		List<Activum> allActiva = fiscalDao.getActiveActiva(MACHINERY);
		BigInteger totalDeprecationActiva = BigInteger.ZERO;
		for (Activum activum : allActiva) {
			totalDeprecationActiva = totalDeprecationActiva.add(activum.getDepreciation());
		}
		overview.setAfschrijvingOverig(totalDeprecationActiva);

		BigInteger depreciationSettlement = roundToInteger(getDepreciationSettlement(deductableCosts));
		overview.setSettlementDepreciation(depreciationSettlement);

		BigInteger totalDepreciation = BigInteger.ZERO; 
	    totalDepreciation = totalDepreciation.add(overview.getAfschrijvingAuto());
		totalDepreciation = totalDepreciation.add(overview.getAfschrijvingAutoCorrectie());
		totalDepreciation = totalDepreciation.add(overview.getAfschrijvingOverig());
		totalDepreciation = totalDepreciation.add(overview.getSettlementDepreciation());
		totalDepreciation = totalDepreciation.subtract(overview.getAfschrijvingOverigCorrectie());
		overview.setAfschrijvingTotaal(totalDepreciation);
	}

	private void handleCosts(List<DeductableCostGroup> deductableCosts) {
		overview.setKostenOverigTransport(roundToInteger(getReiskosten(deductableCosts)));
		overview.setKostenOverig(roundToInteger(getFoodCosts(deductableCosts).add(getAlgemeneKosten(deductableCosts))));
		overview.setSettlementCosts(roundToInteger(getSettlementCosts(deductableCosts)));
	}

	private void handleCar(PrivatWithdrawal privatWithdrawal, List<DeductableCostGroup> deductableCosts) throws Exception {
		BigDecimal afschrijvingAuto = getAfschrijvingAuto(deductableCosts);
		BookValue carActivum = bookValueDao.getBookValue(BalanceType.CURRENT_ASSETS, bookYear);
		if (carActivum != null) {
			handleBusinessCar(privatWithdrawal, deductableCosts, afschrijvingAuto);
		} else {
			handlePrivateCar(deductableCosts);
		}
	}

	private void handleBusinessCar(PrivatWithdrawal privatWithdrawal, List<DeductableCostGroup> deductableCosts, BigDecimal afschrijvingAuto) throws Exception {
		if (afschrijvingAuto != null) {
			overview.setAfschrijvingAuto(AmountHelper.roundToInteger(afschrijvingAuto));
		}
		// TODO: fiscale bijtelling laten invoeren
		overview.setBijtellingAuto(roundToInteger(getFiscaleBijtelling(deductableCosts)));
		overview.setKostenAuto(roundToInteger(getKostenVoorAuto(deductableCosts)));
		handleDepreciationCorrections();
		BigInteger kostenAutoAftrekbaar = overview.getBijtellingAuto();
		kostenAutoAftrekbaar = kostenAutoAftrekbaar.subtract(overview.getKostenAuto());
		if (overview.getAfschrijvingAuto() != null) {
			kostenAutoAftrekbaar = kostenAutoAftrekbaar.subtract(overview.getAfschrijvingAuto());
		}
		if (kostenAutoAftrekbaar.compareTo(BigInteger.ZERO) == 1) {
			kostenAutoAftrekbaar = BigInteger.ZERO;
		}
		handleBusinessCarPrivateWithDrawal(privatWithdrawal, kostenAutoAftrekbaar);
		overview.setKostenAutoAftrekbaar(kostenAutoAftrekbaar);
	}

	private void handleBusinessCarPrivateWithDrawal(PrivatWithdrawal privatWithdrawal, BigInteger kostenAutoAftrekbaar) {
		BigInteger withDrawal = BigInteger.ZERO;
		if (kostenAutoAftrekbaar.compareTo(BigInteger.ZERO) == -1) {
			withDrawal = overview.getBijtellingAuto();
			privatWithdrawal.setWithdrawalPrivateUsageBusinessCar(withDrawal);
		} else {
			withDrawal = overview.getKostenAuto();
			withDrawal.add(overview.getAfschrijvingAuto());
			privatWithdrawal.setWithdrawalPrivateUsageBusinessCar(withDrawal);
		}
	}

	@Deprecated
	private void handleDepreciationCorrections() throws Exception {
		List<Cost> corrections = costCache.getVatCorrectionDepreciation();
		Iterator<Cost> iterator = corrections.iterator();
		BigInteger depreciationCorrection = BigInteger.ZERO;
		while (iterator.hasNext()) {
			Cost correctionKost = iterator.next();
			if (correctionKost.getDescription().contains("auto")) {
				overview.setAfschrijvingAutoCorrectie(roundToInteger(correctionKost.getAmount()));
			} else {
				depreciationCorrection = depreciationCorrection.add(roundToInteger(correctionKost.getAmount()));
			}
		}
		overview.setAfschrijvingOverigCorrectie(depreciationCorrection);
	}

	private void handlePrivateCar(List<DeductableCostGroup> deductableCosts) {
		overview.setKostenAuto(roundToInteger(getKostenVoorAuto(deductableCosts)));
		BigInteger kostenAutoAftrekbaar = BigInteger.ZERO;
		kostenAutoAftrekbaar = kostenAutoAftrekbaar.subtract(overview.getKostenAuto());
		overview.setKostenAutoAftrekbaar(kostenAutoAftrekbaar);
	}

	private BigInteger getEnterpriseCapital(List<BookValue> passiva) {
		BigInteger enterpriseCapital = BigInteger.ZERO;
		for (BookValue passivum : passiva) {
			if (passivum.getBalanceType() != VAT_TO_BE_PAID) {
				enterpriseCapital = enterpriseCapital.add(passivum.getSaldo());
			}
		}
		return enterpriseCapital;
	}

	private BigInteger getBalansTotaal(List<BookValue> activaLijst) {
		Iterator<BookValue> iterator = activaLijst.iterator();
		BigInteger totaal = BigInteger.ZERO;
		while (iterator.hasNext()) {
			BookValue activa = iterator.next();
			totaal = totaal.add(activa.getSaldo());
		}
		return totaal;
	}

	public void calculateProfit() {
		BigInteger nettoOmzet = overview.getNettoOmzet();
		nettoOmzet = nettoOmzet.add(overview.getInterestFromBusinessSavings());
		nettoOmzet = nettoOmzet.subtract(overview.getRepurchase());
		nettoOmzet = nettoOmzet.add(overview.getKostenAutoAftrekbaar());
		nettoOmzet = nettoOmzet.subtract(overview.getKostenOverigTransport());
		nettoOmzet = nettoOmzet.subtract(overview.getKostenOverig());
		nettoOmzet = nettoOmzet.subtract(overview.getSettlementCosts());
		nettoOmzet = nettoOmzet.subtract(overview.getAfschrijvingTotaal());
		overview.setProfit(nettoOmzet);
	}

}
