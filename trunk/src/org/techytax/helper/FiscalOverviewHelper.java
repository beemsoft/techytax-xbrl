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
import org.techytax.domain.Balans;
import org.techytax.domain.BookValue;
import org.techytax.domain.Cost;
import org.techytax.domain.DeductableCostGroup;
import org.techytax.domain.FiscalBalance;
import org.techytax.domain.FiscalOverview;
import org.techytax.domain.Periode;
import org.techytax.domain.PrepaidTax;
import org.techytax.domain.PrivatWithdrawal;
import org.techytax.domain.User;
import org.techytax.jpa.dao.GenericDao;
import org.techytax.props.PropsFactory;
import org.techytax.util.DateHelper;
import org.techytax.zk.login.UserCredentialManager;

public class FiscalOverviewHelper {

	private User user = UserCredentialManager.getUser();
	private FiscalDao fiscalDao = new FiscalDao();
	private GenericDao<BookValue> bookValueGenericDao = new GenericDao<BookValue>(BookValue.class, user);
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

		Balans btwBalans = BalanceCalculator.calculateBtwBalance(costCache.getCosts(), false);
		List<DeductableCostGroup> deductableCosts = costCache.getDeductableCosts();
		overview.setJaar(bookYear);

		handleProfitAndLoss(privatWithdrawal, btwBalans, deductableCosts);

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
		int bookTotalBegin = getBalansTotaal(activumListPreviousYear);
		int bookTotalEnd = getBalansTotaal(activumListThisYear);
		overview.setBookTotalBegin(bookTotalBegin);
		overview.setBookTotalEnd(bookTotalEnd);

	}

	private void handleProfitAndLoss(PrivatWithdrawal privatWithdrawal, Balans btwBalans, List<DeductableCostGroup> deductableCosts) throws Exception {
		handleTurnOver(btwBalans);

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
		int maximaleFor = (int) (overview.getWinst() * FOR_PERCENTAGE);
		if (maximaleFor > MAXIMALE_FOR) {
			maximaleFor = MAXIMALE_FOR;
		}
		if (maximaleFor < 0) {
			maximaleFor = 0;
		}
		overview.setOudedagsReserveMaximaal(maximaleFor);
	}

	private void handleTurnOver(Balans vatBalance) throws Exception {
		if (vatBalance.getBrutoOmzet().compareTo(BigDecimal.ZERO) == 0) {
			List<Cost> balanceCosts = costCache.getBusinessAccountCosts();
			BigDecimal turnover = BalanceCalculator.calculateTotalPaidInvoices(balanceCosts);
			vatBalance.setNettoOmzet(turnover);
		}
		overview.setNettoOmzet(vatBalance.getNettoOmzet().intValue());
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
		BigInteger bookTotalNonCurrentAssets = BigInteger.valueOf(overview.getBookTotalEnd() - fiscalPension.intValue() - vatDebt.intValue());
		if (currentBookValue == null) {
			BookValue newValue = new BookValue(0, NON_CURRENT_ASSETS, bookYear, bookTotalNonCurrentAssets);
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
		BigDecimal vatDebt = costDao.getVatDebtFromPreviousYear(DateHelper.getDate(lastVatPeriod.getBeginDatum()), DateHelper.getDate(lastVatPeriod.getEindDatum()), Long.toString(user.getId()));

		if (vatDebt != null && vatDebt.compareTo(BigDecimal.ZERO) == 1) {

			if (currentBookValue == null) {
				BookValue newValue = new BookValue(0, VAT_TO_BE_PAID, bookYear, vatDebt.toBigInteger());
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
				BookValue newValue = new BookValue(0, PENSION, bookYear, fiscalPension);
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
		int totalWithdrawal = overview.getProfit() - (enterpriseCapital.intValue() - enterpriseCapitalPreviousYear.intValue()) + privateDeposit.intValue();
		privatWithdrawal.setTotaleOnttrekking(totalWithdrawal);
		int withdrawalCash = totalWithdrawal - privatWithdrawal.getWithdrawalPrivateUsageBusinessCar();
		privatWithdrawal.setWithdrawalCash(withdrawalCash);
		overview.setOnttrekking(privatWithdrawal);
	}

	private void handleInterest() throws Exception {
		BigInteger interest = costCache.getInterest().toBigInteger();
		overview.setInterestFromBusinessSavings(interest);
	}

	private void handleDepreciations(List<DeductableCostGroup> deductableCosts) throws Exception {
		List<Activum> allActiva = fiscalDao.getActiveActiva(MACHINERY);
		BigInteger totalDeprecation = BigInteger.ZERO;
		for (Activum activum : allActiva) {
			totalDeprecation = totalDeprecation.add(activum.getDepreciation());
		}
		overview.setAfschrijvingOverig(totalDeprecation.intValue());
		
		BigDecimal depreciationSettlement = BalanceCalculator.getDepreciationSettlement(deductableCosts);
		if (depreciationSettlement != null) {
			overview.setSettlementDepreciation(depreciationSettlement.intValue());
		}
		overview.setAfschrijvingTotaal(overview.getAfschrijvingAuto() - overview.getAfschrijvingAutoCorrectie() + overview.getAfschrijvingOverig() - overview.getAfschrijvingOverigCorrectie()
				+ overview.getSettlementDepreciation());
	}

	private void handleCosts(List<DeductableCostGroup> deductableCosts) {
		overview.setKostenOverigTransport(BalanceCalculator.getReiskosten(deductableCosts).intValue());
		overview.setKostenOverig(BalanceCalculator.getFoodCosts(deductableCosts).add(BalanceCalculator.getAlgemeneKosten(deductableCosts)).intValue());
		overview.setSettlementCosts(BalanceCalculator.getSettlementCosts(deductableCosts).intValue());
	}

	private void handleCar(PrivatWithdrawal privatWithdrawal, List<DeductableCostGroup> deductableCosts) throws Exception {
		BigDecimal afschrijvingAuto = BalanceCalculator.getAfschrijvingAuto(deductableCosts);
		BookValue carActivum = bookValueDao.getBookValue(BalanceType.CURRENT_ASSETS, bookYear);
		if (carActivum != null) {
			handleBusinessCar(privatWithdrawal, deductableCosts, afschrijvingAuto);
		} else {
			handlePrivateCar(deductableCosts);
		}
	}

	private void handleBusinessCar(PrivatWithdrawal privatWithdrawal, List<DeductableCostGroup> deductableCosts, BigDecimal afschrijvingAuto) throws Exception {
		if (afschrijvingAuto != null) {
			overview.setAfschrijvingAuto(afschrijvingAuto.intValue());
		}
		// TODO: fiscale bijtelling laten invoeren
		overview.setBijtellingAuto(BalanceCalculator.getFiscaleBijtelling(deductableCosts).intValue());
		overview.setKostenAuto(BalanceCalculator.getKostenVoorAuto(deductableCosts).intValue());
		handleDepreciationCorrections();
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
	private void handleDepreciationCorrections() throws Exception {
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

	private void handlePrivateCar(List<DeductableCostGroup> deductableCosts) {
		overview.setKostenAuto(BalanceCalculator.getKostenVoorAuto(deductableCosts).intValue());
		int kostenAutoAftrekbaar = 0;
		kostenAutoAftrekbaar = -overview.getKostenAuto();
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

	private int getBalansTotaal(List<BookValue> activaLijst) {
		Iterator<BookValue> iterator = activaLijst.iterator();
		int totaal = 0;
		while (iterator.hasNext()) {
			BookValue activa = iterator.next();
			totaal += activa.getSaldo().intValue();
		}
		return totaal;
	}

	public void calculateProfit() {
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
