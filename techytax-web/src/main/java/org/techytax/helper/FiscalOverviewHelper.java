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

import static java.math.BigDecimal.ZERO;
import static org.techytax.domain.BalanceType.MACHINERY;
import static org.techytax.domain.BalanceType.NON_CURRENT_ASSETS;
import static org.techytax.domain.BalanceType.PENSION;
import static org.techytax.domain.BalanceType.VAT_TO_BE_PAID;
import static org.techytax.domain.CostConstants.FOR_PERCENTAGE;
import static org.techytax.domain.CostConstants.MAXIMALE_FOR;
import static org.techytax.helper.AmountHelper.roundDownToInteger;
import static org.techytax.helper.AmountHelper.roundToInteger;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.techytax.cache.CostCache;
import org.techytax.dao.ActivumDao;
import org.techytax.dao.BookValueDao;
import org.techytax.domain.Activum;
import org.techytax.domain.BalanceType;
import org.techytax.domain.BookValue;
import org.techytax.domain.Cost;
import org.techytax.domain.DeductableCostGroup;
import org.techytax.domain.FiscalBalance;
import org.techytax.domain.FiscalOverview;
import org.techytax.domain.FiscalPeriod;
import org.techytax.domain.PrepaidTax;
import org.techytax.domain.PrivateWithdrawal;
import org.techytax.domain.VatBalanceWithinEu;
import org.techytax.jpa.dao.CostDao;
import org.techytax.util.DateHelper;
import org.techytax.zk.login.UserCredentialManager;

@Component
public class FiscalOverviewHelper {

	@Autowired
	private ActivumDao activumDao;
	
	@Autowired
	private BookValueDao bookValueDao;
	
	@Autowired
	private CostCache costCache;
	
	@Autowired
	private CostDao costDao;
	
	@Autowired
	private ActivaHelper activaHelper;
	
	private FiscalOverview overview = new FiscalOverview();
	
	@Autowired
	private InvestmentDeductionHelper investmentDeductionHelper;
	
	private int bookYear;
	private Map<BalanceType, FiscalBalance> passivaMap = new HashMap<>();
	
	@Autowired
	private BalanceCalculator balanceCalculator;

	public FiscalOverviewHelper() throws Exception {
		bookYear = DateHelper.getFiscalYear();
	}

	public FiscalOverview createFiscalOverview(Date beginDatum, Date eindDatum) throws Exception {

		costCache.setBeginDate(beginDatum);
		costCache.setEndDate(eindDatum);
		PrivateWithdrawal privatWithdrawal = new PrivateWithdrawal();

		VatBalanceWithinEu vatBalanceWithinEu = balanceCalculator.calculateVatBalance(costCache.getCosts(), false);
		List<DeductableCostGroup> deductableCosts = costCache.getDeductableCosts();
		overview.setJaar(bookYear);



		activaHelper.setFiscalOverview(overview);
		activaHelper.setCostCache(costCache);
		Map<BalanceType, FiscalBalance> activaMap = activaHelper.handleActiva(costCache.getBusinessAccountCosts(), overview);
		overview.setActivaMap(activaMap);

		List<BookValue> activumListThisYear = bookValueDao.getActivaList(bookYear);
		List<BookValue> activumListPreviousYear = bookValueDao.getActivaList(bookYear - 1);
		setBalanceTotals(activumListThisYear, activumListPreviousYear);

        handleProfitAndLoss(privatWithdrawal, vatBalanceWithinEu, deductableCosts);

		handlePassiva();
		overview.setPassivaMap(passivaMap);

		List<BookValue> passivaList = bookValueDao.getPassivaList(bookYear);

		BigInteger enterpriseCapital = getEnterpriseCapital(passivaList);
		overview.setEnterpriseCapital(enterpriseCapital);

		BigDecimal privateDeposit = handlePrivateDeposits();

		handlePrivateWithdrawals(privatWithdrawal, enterpriseCapital, privateDeposit);

		handlePrepaidTaxes();

		return overview;
	}

	private void setBalanceTotals(List<BookValue> activumListThisYear, List<BookValue> activumListPreviousYear) {
		BigInteger bookTotalBegin = getBalansTotaal(activumListPreviousYear);
		BigInteger bookTotalEnd = getBalansTotaal(activumListThisYear);
		overview.setBookTotalBegin(bookTotalBegin);
		overview.setBookTotalEnd(bookTotalEnd);

	}

	private void handleProfitAndLoss(PrivateWithdrawal privatWithdrawal, VatBalanceWithinEu vatBalanceWithinEu, List<DeductableCostGroup> deductableCosts) throws Exception {
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
			BigDecimal turnover = balanceCalculator.calculateTotalPaidInvoices(balanceCosts);
			vatBalanceWithinEu.setNettoOmzet(AmountHelper.roundDownToInteger(turnover));
		}
		overview.setNettoOmzet(vatBalanceWithinEu.getNettoOmzet());
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
		BigInteger newSaldo = overview.getBookTotalEnd();
		newSaldo = newSaldo.subtract(fiscalPension);
		newSaldo = newSaldo.subtract(roundDownToInteger(vatDebt));
		if (currentBookValue == null) {
			BookValue newValue = new BookValue(NON_CURRENT_ASSETS, bookYear, newSaldo);
			newValue.setUser(UserCredentialManager.getUser());
			bookValueDao.persistEntity(newValue);
		} else {
			currentBookValue.setSaldo(newSaldo);
		}
		if (previousBookValue != null || currentBookValue != null) {
			FiscalBalance fiscalBalance = new FiscalBalance();
			if (previousBookValue != null) {
				fiscalBalance.setBeginSaldo(previousBookValue.getSaldo());
			}
			fiscalBalance.setEndSaldo(newSaldo);
			passivaMap.put(NON_CURRENT_ASSETS, fiscalBalance);
		}
	}

	private BigDecimal handleVatToBePaid() throws Exception {
		BookValue currentBookValue = bookValueDao.getBookValue(VAT_TO_BE_PAID, bookYear);
		BookValue previousBookValue = bookValueDao.getBookValue(VAT_TO_BE_PAID, bookYear - 1);
        BigDecimal newSaldo = getVatDebtFromPreviousYear(DateHelper.getLastVatPeriodPreviousYear());
		if (newSaldo != null && newSaldo.compareTo(BigDecimal.ZERO) == 1) {

			if (currentBookValue == null) {
				BookValue newValue = new BookValue(VAT_TO_BE_PAID, bookYear, newSaldo.toBigInteger());
				newValue.setUser(UserCredentialManager.getUser());
				bookValueDao.persistEntity(newValue);
			} else {
				currentBookValue.setSaldo(newSaldo.toBigInteger());
			}
		}
		if (previousBookValue != null || currentBookValue != null) {
			FiscalBalance fiscalBalance = new FiscalBalance();
			fiscalBalance.setBeginSaldo(previousBookValue.getSaldo());
			fiscalBalance.setEndSaldo(newSaldo.toBigInteger());
			passivaMap.put(VAT_TO_BE_PAID, fiscalBalance);
		}
		return newSaldo;
	}
	
	private BigDecimal getVatDebtFromPreviousYear(FiscalPeriod period) {
		List<Cost> costs = costDao.getInvoicesSent(period);
		BigDecimal vatBalance = ZERO;
		for (Cost cost : costs) {
			vatBalance = vatBalance.add(cost.getVat());
		}
		return vatBalance;
	}

	private BigInteger handleFiscalPension() throws Exception {
		BookValue currentBookValue = bookValueDao.getBookValue(PENSION, bookYear);
		BookValue previousBookValue = bookValueDao.getBookValue(PENSION, bookYear - 1);

        BigInteger newSaldo = BigInteger.ZERO;
		if (currentBookValue == null) {
			if (previousBookValue != null) {
                newSaldo = previousBookValue.getSaldo();
				BookValue newValue = new BookValue(PENSION, bookYear, newSaldo);
				newValue.setUser(UserCredentialManager.getUser());
				bookValueDao.persistEntity(newValue);
			}
		} else {
            newSaldo = currentBookValue.getSaldo();
		}
		if (previousBookValue != null || currentBookValue != null) {
			FiscalBalance fiscalBalance = new FiscalBalance();
			fiscalBalance.setBeginSaldo(previousBookValue.getSaldo());
			fiscalBalance.setEndSaldo(newSaldo);
			passivaMap.put(PENSION, fiscalBalance);
		}
		return newSaldo;
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

	private void handlePrivateWithdrawals(PrivateWithdrawal privateWithdrawal, BigInteger enterpriseCapital, BigDecimal privateDeposit) throws Exception {
		List<BookValue> passivaListPreviousYear = bookValueDao.getPassivaList(bookYear - 1);
		BigInteger enterpriseCapitalPreviousYear = getEnterpriseCapital(passivaListPreviousYear);
        overview.setEnterpriseCapitalPreviousYear(enterpriseCapitalPreviousYear);
		BigInteger totalWithdrawal = overview.getProfit();
		totalWithdrawal = totalWithdrawal.subtract(enterpriseCapital.subtract(enterpriseCapitalPreviousYear));
		totalWithdrawal = totalWithdrawal.add(roundToInteger(privateDeposit));
		privateWithdrawal.setTotaleOnttrekking(totalWithdrawal);
		BigInteger withdrawalCash = totalWithdrawal;
		if (privateWithdrawal.getWithdrawalPrivateUsageBusinessCar() != null) {
			withdrawalCash = withdrawalCash.subtract(privateWithdrawal.getWithdrawalPrivateUsageBusinessCar());
		}
		privateWithdrawal.setWithdrawalCash(withdrawalCash);
		overview.setOnttrekking(privateWithdrawal);
	}

	private void handleInterest() throws Exception {
		BigInteger interest = costCache.getInterest().toBigInteger();
		overview.setInterestFromBusinessSavings(interest);
	}

	private void handleDepreciations(List<DeductableCostGroup> deductableCosts) throws Exception {
		List<Activum> allActiva = activumDao.getActiveActiva(MACHINERY);
		BigInteger totalDeprecationActiva = BigInteger.ZERO;
		for (Activum activum : allActiva) {
			totalDeprecationActiva = totalDeprecationActiva.add(activum.getDepreciation());
		}
		overview.setAfschrijvingOverig(totalDeprecationActiva);

		BigInteger totalDepreciation = BigInteger.ZERO;
	    totalDepreciation = totalDepreciation.add(overview.getAfschrijvingAuto());
		totalDepreciation = totalDepreciation.add(overview.getAfschrijvingAutoCorrectie());
		totalDepreciation = totalDepreciation.add(overview.getAfschrijvingOverig());
		totalDepreciation = totalDepreciation.add(overview.getSettlementDepreciation());
		totalDepreciation = totalDepreciation.subtract(overview.getAfschrijvingOverigCorrectie());
		overview.setAfschrijvingTotaal(totalDepreciation);
	}

	private void handleCosts(List<DeductableCostGroup> deductableCosts) {
		overview.setKostenOverigTransport(roundToInteger(balanceCalculator.getReiskosten(deductableCosts)));
		overview.setKostenOverig(roundToInteger(balanceCalculator.getFoodCosts(deductableCosts).add(balanceCalculator.getAlgemeneKosten(deductableCosts))));
		overview.setSettlementCosts(roundToInteger(balanceCalculator.getSettlementCosts(deductableCosts)));
	}

	private void handleCar(PrivateWithdrawal privatWithdrawal, List<DeductableCostGroup> deductableCosts) throws Exception {
		BigDecimal afschrijvingAuto = balanceCalculator.getAfschrijvingAuto(deductableCosts);
		BookValue carActivum = bookValueDao.getBookValue(BalanceType.CURRENT_ASSETS, bookYear);
		if (carActivum != null) {
			handleBusinessCar(privatWithdrawal, deductableCosts, afschrijvingAuto);
		} else {
			handlePrivateCar(deductableCosts);
		}
	}

	private void handleBusinessCar(PrivateWithdrawal privatWithdrawal, List<DeductableCostGroup> deductableCosts, BigDecimal afschrijvingAuto) throws Exception {
		if (afschrijvingAuto != null) {
			overview.setAfschrijvingAuto(AmountHelper.roundToInteger(afschrijvingAuto));
		}
		// TODO: fiscale bijtelling laten invoeren
		overview.setBijtellingAuto(roundToInteger(balanceCalculator.getFiscaleBijtelling(deductableCosts)));
		overview.setKostenAuto(roundToInteger(balanceCalculator.getKostenVoorAuto(deductableCosts)));
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

	private void handleBusinessCarPrivateWithDrawal(PrivateWithdrawal privatWithdrawal, BigInteger kostenAutoAftrekbaar) {
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
		overview.setKostenAuto(roundToInteger(balanceCalculator.getKostenVoorAuto(deductableCosts)));
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
