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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.techytax.dao.KostensoortDao;
import org.techytax.dao.KostmatchDao;
import org.techytax.domain.Kostensoort;
import org.techytax.domain.Kostmatch;
import org.techytax.struts.form.KostensoortForm;

public class EditKostensoortAction extends Action {
	private static final Log log = LogFactory
			.getLog(EditKostensoortAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			final HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Kostensoort result = null;
		KostensoortForm objForm = (KostensoortForm) form;

		String forward = "failure";
		String kostenSoortId = (String) request.getParameter("kostenSoortId");
		if (StringUtils.isEmpty(kostenSoortId)) {
			kostenSoortId = (String) request.getAttribute("kostenSoortId");
		}
		if (StringUtils.isNotEmpty(kostenSoortId)) {
			try {
				KostensoortDao kostensoortDao = new KostensoortDao();

				result = kostensoortDao.getKostensoort(kostenSoortId);

				log.debug("result=" + result);

				BeanUtils.copyProperties(objForm, result);

				// Get the list of matching strings
				KostmatchDao kostmatchDao = new KostmatchDao();
				List<Kostmatch> kostmatchLijst = kostmatchDao
						.getKostmatchLijstForId(kostenSoortId);
				request.setAttribute("kostmatchLijst", kostmatchLijst);
				forward = "success";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mapping.findForward(forward);
	}

}
