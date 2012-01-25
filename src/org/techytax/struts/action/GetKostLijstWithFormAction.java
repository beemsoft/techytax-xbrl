/**
 * Copyright 2011 Hans Beemsterboer
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
package org.techytax.struts.action;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.techytax.dao.BoekDao;
import org.techytax.domain.Balans;
import org.techytax.domain.FiscalOverview;
import org.techytax.domain.Kost;
import org.techytax.domain.Liquiditeit;
import org.techytax.domain.Periode;
import org.techytax.domain.Reiskosten;
import org.techytax.domain.User;
import org.techytax.helper.BalanceCalculator;
import org.techytax.helper.FiscalOverviewHelper;
import org.techytax.struts.form.BalansForm;
import org.techytax.util.DateHelper;

public class GetKostLijstWithFormAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, final HttpServletRequest request, HttpServletResponse response) throws Exception {

		final ActionErrors errors = new ActionErrors();
		String forward = "failure";

		List<Kost> result = null;
		BalansForm balansForm = (BalansForm) form;
		if (balansForm.getBalansSoort() == null && StringUtils.isEmpty(balansForm.getSearchTerm())) {
			Periode periode = DateHelper.getPeriodeVorigJaar();
			balansForm.setBalansSoort("alles");
			balansForm.setBeginDatum(DateHelper.getDate(periode.getBeginDatum()));
			balansForm.setEindDatum(DateHelper.getDate(periode.getEindDatum()));
		}
		try {
			User user = (User) request.getSession().getAttribute("user");
			String userId = Long.toString(user.getId());
			BoekDao boekDao = new BoekDao();

			if (StringUtils.isNotEmpty(balansForm.getSearchTerm())) {
				result = boekDao.searchCosts(balansForm.getSearchTerm(), userId);
			} else {
				result = boekDao.getKostLijst(balansForm.getBeginDatum(), balansForm.getEindDatum(), balansForm.getBalansSoort(), userId);
			}
			request.setAttribute("kostLijst", result);

			String balansSoort = balansForm.getBalansSoort();
			if (balansSoort != null) {
				if (balansSoort.equals("btwBalans")) {
					Balans balans = BalanceCalculator.calculateBtwBalance(result, false);
					request.setAttribute("btwOut", balans.getTotaleKosten());
					request.setAttribute("btwIn", balans.getTotaleBaten());
					request.setAttribute("balans", (balans.getTotaleBaten().subtract(balans.getTotaleKosten()).add(balans.getCorrection())));
					request.setAttribute("brutoOmzet", balans.getBrutoOmzet());
					request.setAttribute("nettoOmzet", balans.getNettoOmzet());
					request.setAttribute("btwCorrection", balans.getCorrection());
				} else if (balansSoort.equals("rekeningBalans")) {
					BigDecimal actualBalance = BalanceCalculator.getActualAccountBalance(balansForm.getBeginDatum(), balansForm.getEindDatum(), user.getId());
					if (actualBalance != null) {
						request.setAttribute("actualBalance", actualBalance);
					} else {
						request.setAttribute("actualBalance", "kan nog niet berekend worden");
					}
					Liquiditeit liquiditeit = BalanceCalculator.calculateAccountBalance(result);
					request.setAttribute("balans", liquiditeit.getRekeningBalans());
					request.setAttribute("sparen", liquiditeit.getSpaarBalans());
					request.setAttribute("private", liquiditeit.getPriveBalans());
					List<Kost> result2 = boekDao.getKostLijst(balansForm.getBeginDatum(), balansForm.getEindDatum(), "btwBalans", userId);
					Balans balans = BalanceCalculator.calculateBtwBalance(result2, true);
					BigDecimal totalPaidInvoices = BalanceCalculator.calculateTotalPaidInvoices(result);
					request.setAttribute("brutoOmzet", balans.getBrutoOmzet().add(totalPaidInvoices));
					List<Kost> result3 = boekDao.getKostLijst(balansForm.getBeginDatum(), balansForm.getEindDatum(), "tax", userId);
					BigDecimal taxBalance = BalanceCalculator.calculateTaxBalance(result3).getTotaleKosten();
					request.setAttribute("taxBalans", taxBalance);
					List<Kost> result4 = boekDao.getCostListCurrentAccount(balansForm.getBeginDatum(), balansForm.getEindDatum(), userId);
					BigDecimal costBalance = BalanceCalculator.calculateCostBalanceCurrentAccount(result4, true).getTotaleKosten();
					request.setAttribute("costBalance", costBalance);
					BigDecimal doubleCheck = balans.getBrutoOmzet().add(totalPaidInvoices).subtract(taxBalance).subtract(costBalance).subtract(
							liquiditeit.getSpaarBalans().subtract(liquiditeit.getPriveBalans()));
					request.setAttribute("doubleCheck", doubleCheck);
				} else if (balansSoort.equals("kostenBalans")) {
					Balans balans = BalanceCalculator.calculatCostBalance(result);
					request.setAttribute("kosten", balans.getTotaleKosten());
					request.setAttribute("baten", balans.getTotaleBaten());
					List<Kost> result4 = boekDao.getCostListCurrentAccount(balansForm.getBeginDatum(), balansForm.getEindDatum(), userId);
					Balans balanceCurrentAccount = BalanceCalculator.calculateCostBalanceCurrentAccount(result4, false);
					request.setAttribute("costCurrentAccount", balanceCurrentAccount.getTotaleKosten());
				} else if (balansSoort.equals("reiskostenBalans")) {
					Reiskosten travelCostBalance = BalanceCalculator.calculatTravelCostBalance(result);
					request.setAttribute("kostenOv", travelCostBalance.getOvKosten());
					request.setAttribute("kostenAutoMetBtw", travelCostBalance.getAutoKostenMetBtw());
					request.setAttribute("kostenAutoZonderBtw", travelCostBalance.getAutoKostenZonderBtw());
					request.setAttribute("vatCorrection", travelCostBalance.getVatCorrection());
					BigDecimal verschil = (travelCostBalance.getAutoKostenMetBtw().subtract(travelCostBalance.getAutoKostenZonderBtw()).subtract(travelCostBalance.getVatCorrection()));
					request.setAttribute("verschil", verschil);
				} else if (balansSoort.equals("private")) {
					BigDecimal monthlyExpenses = BalanceCalculator.calculatMonthlyPrivateExpenses(result);
					request.setAttribute("monthlyExpenses", monthlyExpenses);
				}
			}

			String action = (String) request.getParameter("action");
			if (action == null) {
				forward = "success";
			} else {
				if (action.equals("Fiscaal overzicht")) {
					FiscalOverview overview = (FiscalOverview) request.getSession().getAttribute("overview"); 
					if (overview == null) {
						overview = FiscalOverviewHelper.createFiscalOverview(balansForm.getBeginDatum(), balansForm.getEindDatum(), result, user.getId());
						request.getSession().setAttribute("overview", overview);
					}
					forward = "fiscaal";
				} else {
					forward = "success";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ActionMessage message = null;
			if (e.getMessage().startsWith("error")) {
				message = new ActionMessage(e.getMessage());
			} else {
				message = new ActionMessage("errors.detail", e.getMessage());
			}
			errors.add(ActionErrors.GLOBAL_MESSAGE, message);
			addErrors(request, errors);
			saveErrors(request, errors);
			return mapping.findForward("error");
		}
		return mapping.findForward(forward);
	}

}
