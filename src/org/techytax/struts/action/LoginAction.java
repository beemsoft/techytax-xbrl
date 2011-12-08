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
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.techytax.domain.User;
import org.techytax.security.AuthenticationException;
import org.techytax.security.SecurityService;
import org.techytax.security.SecurityServiceImpl;

public final class LoginAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String username = (String) PropertyUtils.getSimpleProperty(form, "username");
		String password = (String) PropertyUtils.getSimpleProperty(form, "password");
		SecurityService service = new SecurityServiceImpl();
		ActionErrors errors = new ActionErrors();
		try {
			User user = service.authenticate(username, password);
			session.setAttribute("user", user);
		} catch (AuthenticationException e) {
			if (e.getMessage().equals("Unknown user")) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.authentication.user"));
			} else if (e.getMessage().equals("Invalid password")) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.authentication.password"));
			} else if (e.getMessage().equals("User blocked")) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.user.blocked"));
			} 
			addErrors(request, errors);
			saveErrors(request, errors);
			return mapping.findForward("error");
		}
		return mapping.findForward("success");
	}
}
