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

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.techytax.dao.BtwmatchDao;
import org.techytax.dao.KostmatchDao;
import org.techytax.domain.KeyId;
import org.techytax.domain.Kostmatch;
import org.techytax.domain.User;

public class DeleteCostMatchPrivateAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String id = (String) request.getParameter("id");
		String kostenSoortId = (String) request.getParameter("kostenSoortId");
		if (StringUtils.isNotEmpty(id)) {

			KostmatchDao dao = new KostmatchDao();
			User user = (User) request.getSession().getAttribute("user");
			KeyId key = new KeyId();
			key.setId(Long.parseLong(id));
			key.setUserId(user.getId());
			Kostmatch costMatch = dao.getCostMatchPrivate(key);
			BtwmatchDao btwMatchDao = new BtwmatchDao();
			btwMatchDao.deleteBtwMatchPrivate(Long.toString(costMatch.getId()));
			costMatch.setUserId(user.getId());
			dao.deleteCostMatchPrivate(costMatch);
		}
		request.setAttribute("kostenSoortId", kostenSoortId);
		return mapping.findForward("success");

	}
}