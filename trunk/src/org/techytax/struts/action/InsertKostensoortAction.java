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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.techytax.dao.KostensoortDao;
import org.techytax.domain.Kostensoort;
import org.techytax.struts.form.KostensoortForm;

public class InsertKostensoortAction extends Action {

	private static final Log log = LogFactory
			.getLog(InsertKostensoortAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("Voeg kostensoort toe.");
		KostensoortForm kostensoortForm = (KostensoortForm) form;
		Kostensoort kostensoort = new Kostensoort();
		BeanUtils.copyProperties(kostensoort, kostensoortForm);

		KostensoortDao dao = new KostensoortDao();

		// Voeg de kostensoort toe
		dao.insertKostensoort(kostensoort);

		List<Kostensoort> kostensoortLijst = dao.getKostensoortLijst();

		request.setAttribute("kostensoortLijst", kostensoortLijst);

		return mapping.findForward("success");

	}
}