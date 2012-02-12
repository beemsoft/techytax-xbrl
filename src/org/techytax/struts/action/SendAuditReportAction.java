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

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.techytax.dao.BoekDao;
import org.techytax.domain.Kost;
import org.techytax.domain.User;
import org.techytax.helper.AuditFileHelper;
import org.techytax.mail.MailHelper;
import org.techytax.struts.form.AuditReportForm;

public class SendAuditReportAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, final HttpServletRequest request, HttpServletResponse response) throws Exception {
		final ActionErrors errors = new ActionErrors();
		AuditReportForm auditReportForm = (AuditReportForm) form;

		BoekDao boekDao = new BoekDao();
		User user = (User) request.getSession().getAttribute("user");
		List<Kost> costList = boekDao.getAuditList(auditReportForm.getBeginDatum(), auditReportForm.getEindDatum(), Long.toString(user.getId()));
		try {
			String message = AuditFileHelper.createAuditFile(costList, user);
			MailHelper.sendAuditReport(message, user.getEmail());

		} catch (Exception e) {
			e.printStackTrace();
			ActionMessage message = null;
			if (e.getMessage() != null && e.getMessage().startsWith("error")) {
				message = new ActionMessage(e.getMessage());
			} else {
				message = new ActionMessage("errors.detail", e.getMessage());
			}
			errors.add(ActionErrors.GLOBAL_MESSAGE, message);
			addErrors(request, errors);
			saveErrors(request, errors);
			return mapping.findForward("success");
		}

		return mapping.findForward("success");
	}
}
