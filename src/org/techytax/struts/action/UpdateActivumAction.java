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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.techytax.dao.BookValueDao;
import org.techytax.dao.FiscalDao;
import org.techytax.domain.Activum;
import org.techytax.domain.RemainingValue;
import org.techytax.domain.User;
import org.techytax.struts.form.DepreciationForm;

public class UpdateActivumAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DepreciationForm depreciationForm = (DepreciationForm) form;
		User user = (User) request.getSession().getAttribute("user");

		FiscalDao fiscaalDao = new FiscalDao();
		Activum activum = new Activum();
		activum.setUserId(user.getId());
		activum.setCostId(depreciationForm.getId());
		activum = fiscaalDao.getActivumByCostId(activum);
		if (StringUtils.isNotEmpty(depreciationForm.getEndDate())) {
			activum.setEndDate(depreciationForm.getEndDate());
		} else {
			activum.setEndDate(null);
		}
		fiscaalDao.updateActivum(activum);

		RemainingValue remainingValue = new RemainingValue();
		remainingValue.setUserId(user.getId());
		remainingValue.setActivaId(activum.getId());
		remainingValue.setRestwaarde(depreciationForm.getRemainingValue());
		BookValueDao bookValueDao = new BookValueDao();
		bookValueDao.updateRemainingValueByActivumId(remainingValue);

		return mapping.findForward("success");

	}
}