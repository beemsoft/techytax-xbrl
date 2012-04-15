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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.techytax.dao.BoekDao;
import org.techytax.domain.Cost;
import org.techytax.domain.User;
import org.techytax.helper.DepreciationHelper;
import org.techytax.struts.form.DepreciationForm;

public class AfschrijvenKostAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, final HttpServletRequest request, HttpServletResponse response) throws Exception {

		String forward = "success";
		DepreciationForm depreciationForm = (DepreciationForm) form;
		DepreciationHelper helper = new DepreciationHelper();
		long id = depreciationForm.getId();
		BoekDao dao = new BoekDao();
		User user = (User) request.getSession().getAttribute("user");
		Cost kost = dao.getKost(Long.toString(id), user.getId());
		helper.splitCost(kost, depreciationForm.isCar(), depreciationForm.getNofYears(), user.getId());
		return mapping.findForward(forward);
	}

}
