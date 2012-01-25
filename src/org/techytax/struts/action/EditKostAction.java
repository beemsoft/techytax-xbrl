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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.techytax.dao.BoekDao;
import org.techytax.dao.FiscalDao;
import org.techytax.dao.KostensoortDao;
import org.techytax.domain.Activum;
import org.techytax.domain.Kost;
import org.techytax.domain.Kostensoort;
import org.techytax.domain.User;
import org.techytax.struts.form.DepreciationForm;
import org.techytax.struts.form.KostForm;

public class EditKostAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, final HttpServletRequest request, HttpServletResponse response) throws Exception {

		String forward = "failure";
		String id = (String) request.getParameter("id");
		Kost cost = null;
		KostForm objForm = (KostForm) form;
		User user = (User) request.getSession().getAttribute("user");

		if (StringUtils.isNotEmpty(id)) {
			BoekDao boekDao = new BoekDao();
			KostensoortDao kostensoortDao = new KostensoortDao();

			List<Kostensoort> kostenSoortLijst = kostensoortDao.getKostensoortLijst();
			request.setAttribute("kostenSoortLijst", kostenSoortLijst);

			cost = boekDao.getKost(id, user.getId());

			if (cost != null) {

				BeanUtils.copyProperties(objForm, cost);

				long kostensoortId = cost.getKostenSoortId();
				Kostensoort kostensoort = kostensoortDao.getKostensoort(Long.toString(kostensoortId));
				if (kostensoort.isInvestering()) {
					Activum activum = new Activum();
					activum.setCostId(cost.getId());
					activum.setUserId(user.getId());
					FiscalDao fiscaalDao = new FiscalDao();
					activum = fiscaalDao.getActivumByCostId(activum);
					if (activum == null) {
						request.setAttribute("investment", "true");
					} else {
						DepreciationForm depreciationForm = new DepreciationForm();
						depreciationForm.setRemainingValue(activum.getRestwaarde());
						depreciationForm.setEndDate(activum.getEndDate());
						request.setAttribute("depreciation", "true");
						request.setAttribute("depreciationForm", depreciationForm);						
					}
				}
			}
			forward = "success";
		}
		return mapping.findForward(forward);
	}

}
