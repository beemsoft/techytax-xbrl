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
package org.techytax.zk.fiscal;

import static org.techytax.helper.AmountHelper.format;

import java.util.ArrayList;
import java.util.List;

import org.techytax.domain.FiscalOverview;
import org.techytax.domain.Periode;
import org.techytax.domain.User;
import org.techytax.helper.AmountHelper;
import org.techytax.helper.FiscalOverviewHelper;
import org.techytax.log.AuditLogger;
import org.techytax.log.AuditType;
import org.techytax.report.domain.BalanceReport;
import org.techytax.report.domain.ReportBalance;
import org.techytax.util.DateHelper;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

public class TaxVM {

	private User user = UserCredentialManager.getUser();
	private FiscalOverview overview;
	private int fiscalYear;

	public TaxVM() throws Exception {
		if (user != null && overview == null) {
			Periode previousFiscalPeriod = DateHelper.getPeriodPreviousYear();
			fiscalYear = DateHelper.getYear(previousFiscalPeriod.getBeginDatum());
			try {
				FiscalOverviewHelper fiscalOverviewHelper = new FiscalOverviewHelper();
				overview = fiscalOverviewHelper.createFiscalOverview(DateHelper.getDate(previousFiscalPeriod.getBeginDatum()), DateHelper.getDate(previousFiscalPeriod.getEindDatum()));
				AuditLogger.log(AuditType.TAX_OVERVIEW, user);
			} catch (Exception e) {
				e.printStackTrace();
				Messagebox.show(e.getMessage(), null, 0, Messagebox.ERROR);
			}
		} else {
			Executions.sendRedirect("login.zul");
		}
	}

	public int getFiscalYear() {
		return fiscalYear;
	}

	public ListModelList<ReportTableItem> getProfitAndLossTableItems() {
		List<ReportTableItem> items = new ArrayList<ReportTableItem>();
		if (overview != null) {
			items.add(new ReportTableItem("Netto omzet", "", "", "", format(overview.getNettoOmzet())));
			items.add(new ReportTableItem("Renteopbrengsten van banktegoeden", "", "", "", "+" + format(overview.getInterestFromBusinessSavings())));
			items.add(new ReportTableItem("Inkooppkosten", "", "", "", "-" + format(overview.getRepurchase())));
			items.add(new ReportTableItem("Afschrijving auto", "", "", "-" + format(overview.getAfschrijvingAuto()), ""));
			items.add(new ReportTableItem("Afschrijvingen apparaten", "", "", "-" + format(overview.getAfschrijvingOverig()), ""));
			items.add(new ReportTableItem("Afschrijving bedrijfsruimte", "", "", "-" + format(overview.getSettlementDepreciation()), ""));
			items.add(new ReportTableItem("Totale afschrijving", "", "", "", "-" + format(overview.getAfschrijvingTotaal())));
			items.add(new ReportTableItem("Bijtelling privégebruik", "+" + format(overview.getBijtellingAuto()), "", "", ""));
			items.add(new ReportTableItem("Afschrijving auto", "-" + format(overview.getAfschrijvingAuto()), "", "", ""));
			items.add(new ReportTableItem("Autokosten", "-" + format(overview.getKostenAuto()), "", "", ""));
			items.add(new ReportTableItem("Aftrekbare autokosten", "", format(overview.getKostenAutoAftrekbaar()), "", ""));
			items.add(new ReportTableItem("Overige transportkosten", "", "-" + format(overview.getKostenOverigTransport()), "", ""));
			items.add(new ReportTableItem("Auto- en transportkosten", "", "", format(overview.getCarAndTransportCosts()), ""));
			items.add(new ReportTableItem("Andere kosten", "", "", "-" + format(overview.getKostenOverig()), ""));
			items.add(new ReportTableItem("Huisvestingskosten", "", "", "-" + format(overview.getSettlementCosts()), ""));
			items.add(new ReportTableItem("Totaal overige bedrijfskosten", "", "", "", format(overview.getOtherCostsTotal())));
			items.add(new ReportTableItem("Winst", "", "", "", format(overview.getProfit())));
			items.add(new ReportTableItem("Wijzigingen in toelaatbare reserves, toevoeging oudedagsreserve", "", "", "", "(" + format(overview.getOudedagsReserveMaximaal()) + ")"));
			items.add(new ReportTableItem("Kleinschaligheidsinvesteringsaftrek", "", "", "", "(" + format(overview.getInvestmentDeduction()) + ")"));
		}
		return new ListModelList<ReportTableItem>(items);
	}

	public ListModelList<ReportTableItem> getActivaTableItems() {
		List<ReportTableItem> items = new ArrayList<ReportTableItem>();
		if (overview != null) {
			BalanceReport report = overview.getActivaReport();
			List<ReportBalance> balanceList = report.getBalanceList();
			for (ReportBalance balance : balanceList) {
				items.add(new ReportTableItem(balance.getOmschrijving(), AmountHelper.formatDecimal(balance.getAanschafKosten()), format(balance.getBookValueBegin()),
						format(balance.getBookValueEnd()), format(balance.getRestwaarde())));
			}
			items.add(new ReportTableItem("Totaal", "", format(report.getTotalBeginValue()), format(report.getTotalEndValue()), ""));
		}
		return new ListModelList<ReportTableItem>(items);
	}

	public ListModelList<ReportTableItem> getPassivaTableItems() {
		List<ReportTableItem> items = new ArrayList<ReportTableItem>();
		if (overview != null) {
			BalanceReport report = overview.getPassivaReport();
			List<ReportBalance> balanceList = report.getBalanceList();
			for (ReportBalance balance : balanceList) {
				items.add(new ReportTableItem(balance.getOmschrijving(), "", format(balance.getBookValueBegin()), format(balance.getBookValueEnd()), ""));
			}
			items.add(new ReportTableItem("Totaal", "", format(report.getTotalBeginValue()), format(report.getTotalEndValue()), ""));
			items.add(new ReportTableItem("Ondernemingsvermogen", "", "", format(overview.getEnterpriseCapital()), ""));
		}
		return new ListModelList<ReportTableItem>(items);
	}

	public ListModelList<ReportTableItem> getPrivatWithDrawalTableItems() {
		List<ReportTableItem> items = new ArrayList<ReportTableItem>();
		if (overview != null) {
			items.add(new ReportTableItem("Onttrekking in contanten", format(overview.getOnttrekking().getWithdrawalCash())));
			items.add(new ReportTableItem("Onttrekking voor privégebruik zakelijke auto", format(overview.getOnttrekking().getWithdrawalPrivateUsageBusinessCar())));
			items.add(new ReportTableItem("Totale onttrekking", format(overview.getOnttrekking().getTotaleOnttrekking())));
		}
		return new ListModelList<ReportTableItem>(items);
	}

	public ListModelList<ReportTableItem> getPrivatDepositTableItems() {
		List<ReportTableItem> items = new ArrayList<ReportTableItem>();
		if (overview != null) {
			items.add(new ReportTableItem("Privéstortingen", format(overview.getPrivateDeposit())));
		}
		return new ListModelList<ReportTableItem>(items);
	}

	public ListModelList<ReportTableItem> getPrepaidTaxTableItems() {
		List<ReportTableItem> items = new ArrayList<ReportTableItem>();
		if (overview != null) {
			items.add(new ReportTableItem("Voorlopige aanslag inkomstenbelasting en premie volksverzekeringen", format(overview.getPrepaidTax().getPrepaidIncome())));
			items.add(new ReportTableItem("Inkomensafhankelijke bijdrage Zorgverzekeringswet", format(overview.getPrepaidTax().getPrepaidHealth())));
		}
		return new ListModelList<ReportTableItem>(items);
	}

}
