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
package org.techytax.struts.action;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.techytax.dao.BoekDao;
import org.techytax.dao.KostensoortDao;
import org.techytax.domain.Cost;
import org.techytax.domain.Kostensoort;
import org.techytax.domain.User;
import org.techytax.struts.form.CostForm;
import org.techytax.util.DateHelper;

public class UpdateKostAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		final ActionErrors errors = new ActionErrors();
		CostForm costForm = (CostForm) form;
		User user = (User) request.getSession().getAttribute("user");
		Cost cost = new Cost();
		cost.setAmount(costForm.getAmount());
		cost.setCostTypeId(costForm.getCostTypeId());
		cost.setDate(DateHelper.stringToDate(costForm.getDate()));
		cost.setDescription(costForm.getDescription());
		cost.setVat(costForm.getVat());
		cost.setId(costForm.getId());
		cost.setUserId(user.getId());
		BoekDao boekDao = new BoekDao();

		boekDao.updateKost(cost);

		// Administrative split cost
		if (costForm.getSplitAmount().compareTo(new BigDecimal("0")) != 0 || costForm.getSplitVat().compareTo(new BigDecimal("0")) != 0) {
			Cost splitCost = new Cost();
			splitCost.setAmount(costForm.getSplitAmount());
			splitCost.setCostTypeId(costForm.getSplitCostTypeId());
			splitCost.setDate(DateHelper.stringToDate(costForm.getDate()));
			splitCost.setDescription(costForm.getSplitDescription());
			splitCost.setVat(costForm.getSplitVat());
			splitCost.setId(0);
			splitCost.setUserId(user.getId());
			boekDao.insertKost(splitCost);

		}

		request.getSession().removeAttribute("overview");

		KostensoortDao kostensoortDao = new KostensoortDao();

		List<Kostensoort> kostenSoortLijst = kostensoortDao.getKostensoortLijst();
		request.setAttribute("kostenSoortLijst", kostenSoortLijst);
		ActionMessage message = new ActionMessage("messages.confirm");
		errors.add(ActionErrors.GLOBAL_MESSAGE, message);
		addErrors(request, errors);
		saveErrors(request, errors);
		return mapping.findForward("success");

	}
}