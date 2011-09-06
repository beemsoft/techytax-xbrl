/**
 * Copyright 2009 Hans Beemsterboer
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
import org.techytax.domain.User;
import org.techytax.helper.DepreciationHelper;

public class InsertActivaAction extends Action {

	/**
	 * Werk de Activa balans bij met afschrijvingen.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			final HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = "success";
		DepreciationHelper helper = new DepreciationHelper();
		try {
			User user = (User) request.getSession().getAttribute("user");
			helper.toevoegenAfschrijvingenAanActiva(Long.toString(user.getId()));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward(forward);
	}

}
