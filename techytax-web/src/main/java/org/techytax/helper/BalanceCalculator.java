/**
 * Copyright 2016 Hans Beemsterboer
 * <p/>
 * This file is part of the TechyTax program.
 * <p/>
 * TechyTax is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * TechyTax is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with TechyTax; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.techytax.helper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.techytax.dao.AccountBalanceDao;
import org.techytax.dao.AccountDao;

import org.techytax.domain.*;
import org.techytax.util.DateHelper;

import static java.math.BigDecimal.ZERO;

import static org.techytax.domain.CostConstants.ADVERTORIAL;
import static org.techytax.domain.CostConstants.ADVERTORIAL_NO_VAT;
import static org.techytax.domain.CostConstants.BUSINESS_CAR;
import static org.techytax.domain.CostConstants.BUSINESS_CAR_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.BUSINESS_FOOD;
import static org.techytax.domain.CostConstants.BUSINESS_FOOD_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.DEPOSIT;
import static org.techytax.domain.CostConstants.DEPRECIATION_CAR;
import static org.techytax.domain.CostConstants.EXPENSE_CREDIT_CARD;
import static org.techytax.domain.CostConstants.EXPENSE_CURRENT_ACCOUNT;
import static org.techytax.domain.CostConstants.EXPENSE_INSIDE_EU;
import static org.techytax.domain.CostConstants.EXPENSE_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.FISCAL_TAX_BUSINESS_CAR_PRIVATE_USAGE;
import static org.techytax.domain.CostConstants.FOOD_TAXFREE_PERCENTAGE;
import static org.techytax.domain.CostConstants.FROM_PRIVATE_ACCOUNT;
import static org.techytax.domain.CostConstants.FROM_SAVINGS_ACCOUNT;
import static org.techytax.domain.CostConstants.INCOME_CURRENT_ACCOUNT_IGNORE;
import static org.techytax.domain.CostConstants.INCOME_TAX_PAID_BACK;
import static org.techytax.domain.CostConstants.INVOICE_PAID;
import static org.techytax.domain.CostConstants.INVOICE_SENT;
import static org.techytax.domain.CostConstants.REPURCHASES;
import static org.techytax.domain.CostConstants.ROAD_TAX;
import static org.techytax.domain.CostConstants.SETTLEMENT;
import static org.techytax.domain.CostConstants.SETTLEMENT_DISCOUNT;
import static org.techytax.domain.CostConstants.SETTLEMENT_INTEREST;
import static org.techytax.domain.CostConstants.SETTLEMENT_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.TO_PRIVATE_ACCOUNT;
import static org.techytax.domain.CostConstants.TO_SAVINGS_ACCOUNT;
import static org.techytax.domain.CostConstants.TRAVEL_WITH_PUBLIC_TRANSPORT;
import static org.techytax.domain.CostConstants.TRAVEL_WITH_PUBLIC_TRANSPORT_OTHER_ACCOUNT;
import static org.techytax.domain.CostConstants.VAT_CORRECTION_CAR_PRIVATE;
import static org.techytax.domain.CostConstants.WITHDRAWAL;


@Component
public class BalanceCalculator {

    @Autowired
    AccountDao accountDao;

    @Autowired
    AccountBalanceDao accountBalanceDao;

    public VatBalanceWithinEu calculateVatBalance(List<Cost> res, boolean isForAccountBalance) throws Exception {

        BigDecimal totalVatOut = ZERO;
        BigDecimal totalVatIn = ZERO;
        BigDecimal totalVatCorrection = ZERO;
        BigDecimal turnoverGross = ZERO;
        BigDecimal turnoverNet = ZERO;
        BigDecimal turnoverNetEu = ZERO;
        BigInteger vatOutEu = BigInteger.ZERO;
        if (res != null) {
            for (int i = 0; i < res.size(); i++) {
                Cost obj;
                obj = res.get(i);
                if (obj != null) {

                    CostType costType = obj.getCostType();
                    if (costType.equals(INVOICE_SENT) && isForAccountBalance) {
                        // skip
                    } else {
                        if (costType.isVatDeclarable()) {
                            if (costType.isBijschrijving() || costType.equals(INVOICE_SENT)) {
                                totalVatIn = totalVatIn.add(obj.getVat());
                                turnoverGross = turnoverGross.add(obj.getAmount());
                                turnoverNet = turnoverNet.add(obj.getAmount());
                                turnoverGross = turnoverGross.add(obj.getVat());
                            } else {
                                if (costType.equals(VAT_CORRECTION_CAR_PRIVATE)) {
                                    totalVatCorrection = totalVatCorrection.add(obj.getVat());
                                } else if (costType.equals(EXPENSE_INSIDE_EU)) {
                                    turnoverNetEu = turnoverNetEu.add(obj.getAmount());
                                } else {
                                    totalVatOut = totalVatOut.add(obj.getVat());
                                }
                            }
                        }
                    }
                }
            }
            turnoverNetEu = AmountHelper.roundDown(turnoverNetEu);
            vatOutEu = AmountHelper.roundDownToInteger(turnoverNetEu.multiply(BigDecimal.valueOf(VatType.HIGH.getValue(new Date()))));
        }
        VatBalanceWithinEu balanceWithinEu = new VatBalanceWithinEu();
        balanceWithinEu.setTotaleBaten(totalVatIn);
        balanceWithinEu.setTotaleKosten(totalVatOut);
        balanceWithinEu.setBrutoOmzet(turnoverGross);
        balanceWithinEu.setNettoOmzet(AmountHelper.roundDownToInteger(turnoverNet));
        balanceWithinEu.setCorrection(totalVatCorrection);
        balanceWithinEu.setTurnoverNetEu(AmountHelper.roundToInteger(turnoverNetEu));
        balanceWithinEu.setVatOutEu(vatOutEu);
        return balanceWithinEu;
    }

    public BigDecimal getActualAccountBalance(String beginDatum, String eindDatum) throws Exception {
        Account businessAccount = accountDao.getBusinessAccount();
        if (businessAccount != null) {
            List<AccountBalance> accountBalances = accountBalanceDao.getAccountBalances(businessAccount);
            Collections.sort(accountBalances);
            BigDecimal beginAmount = null;
            BigDecimal endAmount = null;
            for (AccountBalance accountBalance : accountBalances) {
                if (DateHelper.hasOneDayDifference(accountBalance.getDatum(), beginDatum)) {
                    beginAmount = accountBalance.getBalance();
                }
                if (beginAmount != null) {
                    if (DateHelper.hasOneDayDifference(accountBalance.getDatum(), eindDatum)) {
                        endAmount = accountBalance.getBalance();
                    }
                    if (endAmount != null) {
                        return endAmount.subtract(beginAmount);
                    }
                }
            }
        }
        return null;
    }

    public Liquidity calculateAccountBalance(List<Cost> res) throws Exception {
        BigDecimal totalKost = ZERO;
        BigDecimal totalInleg = ZERO;
        BigDecimal totalOpname = ZERO;
        BigDecimal totalSparen = ZERO;
        Liquidity liquidity = new Liquidity();
        if (res != null) {
            for (int i = 0; i < res.size(); i++) {
                Cost obj;
                obj = res.get(i);
                if (obj != null) {
                    CostType costType = obj.getCostType();
                    if (costType.isBalansMeetellen()) {
                        if (costType.isBijschrijving()) {
                            if (!costType.equals(INCOME_CURRENT_ACCOUNT_IGNORE)) {
                                totalKost = totalKost.add(obj.getAmount());
                                totalKost = totalKost.add(obj.getVat());
                                if (costType.equals(FROM_PRIVATE_ACCOUNT) || costType.equals(DEPOSIT)) {
                                    totalInleg = totalInleg.add(obj.getAmount());
                                }
                                if (costType.equals(FROM_SAVINGS_ACCOUNT)) {
                                    totalSparen = totalSparen.subtract(obj.getAmount());
                                }
                            }
                        } else {
                            totalKost = totalKost.subtract(obj.getAmount());
                            totalKost = totalKost.subtract(obj.getVat());
                            if (costType.equals(TO_SAVINGS_ACCOUNT)) {
                                totalSparen = totalSparen.add(obj.getAmount());
                            } else if (costType.equals(TO_PRIVATE_ACCOUNT) || costType.equals(WITHDRAWAL)) {
                                totalOpname = totalOpname.add(obj.getAmount());
                            }
                        }
                    }
                }
            }
        }
        // Not used for fiscal balance
        liquidity.setPriveBalans(totalInleg.subtract(totalOpname));

        liquidity.setRekeningBalans(totalKost);
        liquidity.setSpaarBalans(totalSparen);
        return liquidity;
    }

    public Balance calculateCostBalanceCurrentAccount(List<Cost> res, boolean isIncludingVat) {

        BigDecimal totalKost = ZERO;
        if (res != null) {
            for (int i = 0; i < res.size(); i++) {
                Cost obj;
                obj = res.get(i);
                if (obj != null) {
                    if (obj.isIncoming()) {
                        totalKost = totalKost.subtract(obj.getAmount());
                        if (isIncludingVat) {
                            totalKost = totalKost.subtract(obj.getVat());
                        }
                    } else {
                        totalKost = totalKost.add(obj.getAmount());
                        if (isIncludingVat) {
                            totalKost = totalKost.add(obj.getVat());
                        }
                    }
                }
            }
        }
        Balance balans = new Balance();
        balans.setTotaleKosten(totalKost);
        return balans;
    }

    public BigDecimal calculateTotalPaidInvoices(List<Cost> res) {
        BigDecimal total = ZERO;
        if (res != null) {
            for (int i = 0; i < res.size(); i++) {
                Cost obj;
                obj = res.get(i);
                if (obj != null) {
                    CostType costType = obj.getCostType();
                    if (costType.equals(INVOICE_PAID)) {
                        total = total.add(obj.getAmount()).add(obj.getVat());
                    }
                }
            }
        }
        return total;
    }

    public TravelCosts calculatTravelCostBalance(List<Cost> res) {
        TravelCosts reiskosten = new TravelCosts();
        BigDecimal totalKostOV = ZERO;
        BigDecimal totalKostAuto = ZERO;
        BigDecimal totalKostAutoMetBtw = ZERO;
        BigDecimal totalVatCorrection = ZERO;
        if (res != null) {
            for (int i = 0; i < res.size(); i++) {
                Cost obj;
                obj = res.get(i);
                if (obj != null) {
                    CostType costType = obj.getCostType();
                    if (costType.equals(TRAVEL_WITH_PUBLIC_TRANSPORT) || costType.equals(TRAVEL_WITH_PUBLIC_TRANSPORT_OTHER_ACCOUNT)) {
                        totalKostOV = totalKostOV.add(obj.getAmount());
                    } else if (costType.equals(BUSINESS_CAR) || costType.equals(BUSINESS_CAR_OTHER_ACCOUNT) || costType.equals(ROAD_TAX)) {
                        totalKostAuto = totalKostAuto.add(obj.getAmount());
                        totalKostAutoMetBtw = totalKostAutoMetBtw.add(obj.getAmount());
                        totalKostAutoMetBtw = totalKostAutoMetBtw.add(obj.getVat());
                    } else if (costType.equals(VAT_CORRECTION_CAR_PRIVATE)) {
                        totalVatCorrection = totalVatCorrection.add(obj.getVat());
                    }
                }
            }
        }
        reiskosten.setOvKosten(totalKostOV);
        reiskosten.setAutoKostenZonderBtw(totalKostAuto);
        reiskosten.setAutoKostenMetBtw(totalKostAutoMetBtw);
        reiskosten.setVatCorrection(totalVatCorrection);
        return reiskosten;
    }

    public Balance calculateTaxBalance(List<Cost> res) throws Exception {
        BigDecimal total = ZERO;
        if (res != null) {
            for (int i = 0; i < res.size(); i++) {
                Cost obj;
                obj = res.get(i);
                if (obj != null) {
                    if (obj.getCostType().equals(INCOME_TAX_PAID_BACK)) {
                        total = total.subtract(obj.getAmount());
                    } else {
                        total = total.add(obj.getAmount());
                    }
                }
            }
        }
        Balance balans = new Balance();
        balans.setTotaleKosten(total);
        return balans;
    }

    public BigDecimal getAfschrijvingAuto(List<DeductableCostGroup> aftrekpostenLijst) {
        Iterator<DeductableCostGroup> iterator = aftrekpostenLijst.iterator();
        while (iterator.hasNext()) {
            DeductableCostGroup aftrekpost = iterator.next();
            if (aftrekpost.getKostenSoort().equals(DEPRECIATION_CAR)) {
                return aftrekpost.getAftrekbaarBedrag();
            }
        }
        return null;
    }

    public BigInteger getRepurchase(List<DeductableCostGroup> aftrekpostenLijst) {
        Iterator<DeductableCostGroup> iterator = aftrekpostenLijst.iterator();
        BigDecimal repurchases = ZERO;
        while (iterator.hasNext()) {
            DeductableCostGroup aftrekpost = iterator.next();
            if (aftrekpost.getKostenSoort().equals(REPURCHASES)) {
                repurchases = repurchases.add(aftrekpost.getAftrekbaarBedrag());
            }
        }
        return repurchases.toBigInteger();
    }

    public BigDecimal getFiscaleBijtelling(List<DeductableCostGroup> aftrekpostenLijst) throws Exception {
        Iterator<DeductableCostGroup> iterator = aftrekpostenLijst.iterator();
        while (iterator.hasNext()) {
            DeductableCostGroup aftrekpost = iterator.next();
            if (aftrekpost.getKostenSoort().equals(FISCAL_TAX_BUSINESS_CAR_PRIVATE_USAGE)) {
                return aftrekpost.getAftrekbaarBedrag();
            }
        }
        return ZERO;
    }

    public BigDecimal getKostenVoorAuto(List<DeductableCostGroup> aftrekpostenLijst) {
        Iterator<DeductableCostGroup> iterator = aftrekpostenLijst.iterator();
        BigDecimal kosten = ZERO;
        List<CostType> costTypes = Arrays.asList(BUSINESS_CAR, BUSINESS_CAR_OTHER_ACCOUNT, ROAD_TAX);
        while (iterator.hasNext()) {
            DeductableCostGroup aftrekpost = iterator.next();
            if (costTypes.contains(aftrekpost.getKostenSoort())) {
                kosten = kosten.add(aftrekpost.getAftrekbaarBedrag());
            }
        }
        return kosten;
    }

    public BigDecimal getReiskosten(List<DeductableCostGroup> aftrekpostenLijst) {
        Iterator<DeductableCostGroup> iterator = aftrekpostenLijst.iterator();
        BigDecimal reiskosten = ZERO;
        List<CostType> costTypes = Arrays.asList(TRAVEL_WITH_PUBLIC_TRANSPORT, TRAVEL_WITH_PUBLIC_TRANSPORT_OTHER_ACCOUNT);
        while (iterator.hasNext()) {
            DeductableCostGroup aftrekpost = iterator.next();
            if (costTypes.contains(aftrekpost.getKostenSoort())) {
                reiskosten = reiskosten.add(aftrekpost.getAftrekbaarBedrag());
            }
        }
        return reiskosten;
    }

    public BigDecimal getAlgemeneKosten(List<DeductableCostGroup> aftrekpostenLijst) {
        Iterator<DeductableCostGroup> iterator = aftrekpostenLijst.iterator();
        BigDecimal kosten = ZERO;
        List<CostType> costTypes = Arrays.asList(EXPENSE_CURRENT_ACCOUNT, EXPENSE_OTHER_ACCOUNT, EXPENSE_CREDIT_CARD, ADVERTORIAL, ADVERTORIAL_NO_VAT);
        while (iterator.hasNext()) {
            DeductableCostGroup aftrekpost = iterator.next();
            if (costTypes.contains(aftrekpost.getKostenSoort())) {
                kosten = kosten.add(aftrekpost.getAftrekbaarBedrag());
            }
        }
        return kosten;
    }

    public BigDecimal getFoodCosts(List<DeductableCostGroup> aftrekpostenLijst) {
        Iterator<DeductableCostGroup> iterator = aftrekpostenLijst.iterator();
        BigDecimal kosten = ZERO;
        List<CostType> costTypes = Arrays.asList(BUSINESS_FOOD, BUSINESS_FOOD_OTHER_ACCOUNT);
        while (iterator.hasNext()) {
            DeductableCostGroup aftrekpost = iterator.next();
            if (costTypes.contains(aftrekpost.getKostenSoort())) {
                kosten = kosten.add(aftrekpost.getAftrekbaarBedrag());
            }
        }
        return kosten.multiply(BigDecimal.valueOf(FOOD_TAXFREE_PERCENTAGE));
    }

    public BigDecimal getSettlementCosts(List<DeductableCostGroup> aftrekpostenLijst) {
        Iterator<DeductableCostGroup> iterator = aftrekpostenLijst.iterator();
        BigDecimal kosten = ZERO;
        List<CostType> costTypes = Arrays.asList(SETTLEMENT, SETTLEMENT_INTEREST, SETTLEMENT_OTHER_ACCOUNT);
        while (iterator.hasNext()) {
            DeductableCostGroup aftrekpost = iterator.next();
            if (costTypes.contains(aftrekpost.getKostenSoort())) {
                kosten = kosten.add(aftrekpost.getAftrekbaarBedrag());
            } else if (aftrekpost.getKostenSoort().equals(SETTLEMENT_DISCOUNT)) {
                kosten = kosten.subtract(aftrekpost.getAftrekbaarBedrag());
            }
        }
        return kosten;
    }

    public BigDecimal calculatMonthlyPrivateExpenses(List<Cost> res) throws Exception {
        BigDecimal monthlyExpenses = ZERO;
        int nofMonths = 0;
        int lastMonth = -1;
        if (res != null) {
            for (int i = 0; i < res.size(); i++) {
                Cost obj;
                obj = res.get(i);
                if (obj != null) {
                    if (!obj.isIncoming()) {
                        if (!obj.getDescription().contains("spaarrekening") && !obj.getDescription().contains("inleg")) {
                            Date datum = obj.getDate();
                            int month = DateHelper.getMonth(datum);
                            if (month != lastMonth) {
                                lastMonth = month;
                                nofMonths++;
                            }
                            monthlyExpenses = monthlyExpenses.add(obj.getAmount());
                        }
                    }
                }
            }
        }
        if (nofMonths == 0) {
            return monthlyExpenses;
        } else {
            return monthlyExpenses.divide(new BigDecimal(nofMonths), RoundingMode.HALF_UP);
        }
    }

}
