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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.techytax.dao.BoekDao;
import org.techytax.domain.Kost;
import org.techytax.domain.User;

public class InsertPrivateExpensesAction extends Action {

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BoekDao boekDao = new BoekDao();
		List<Kost> kostLijst = (List<Kost>) request.getSession().getAttribute("kostLijst");
		Kost kost = null;
		User user = (User) request.getSession().getAttribute("user");
		for (int i = 0; i < kostLijst.size(); i++) {
			kost = (Kost) kostLijst.get(i);
			kost.setUserId(user.getId());
			boekDao.insertPrivateExpense(kost);
		}

		return mapping.findForward("success");

	}
}