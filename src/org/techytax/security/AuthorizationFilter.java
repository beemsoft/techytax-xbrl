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
package org.techytax.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.techytax.domain.User;
import org.techytax.struts.form.LanguageForm;

public class AuthorizationFilter implements Filter {
	private String onErrorUrl;

	public void init(FilterConfig filterConfig) throws ServletException {
		onErrorUrl = filterConfig.getInitParameter("onError");
		if (onErrorUrl == null || "".equals(onErrorUrl)) {
			onErrorUrl = "/index.jsp";
		}
	}

	private List<String> getGuestActions() {
		List<String> list = new ArrayList<String>();
		list.add("getKostensoortLijst");
		list.add("newKostensoort");
		list.add("editKostensoort");
		list.add("newKostmatch");
		list.add("editKostmatch");
		list.add("editBtwmatch");
		list.add("newCostMatchPrivate");
		list.add("editCostMatchPrivate");
		list.add("editBtwMatchPrivate");		
		list.add("getKostLijst");
		list.add("getKostLijstWithForm");
		list.add("newKost");
		list.add("editKost");
		list.add("laadKostLijst");
		list.add("uploadFile");
		list.add("getAccounts");
		list.add("newAccount");
		list.add("editAccount");
		list.add("getAccountBalance");
		list.add("newAccountBalance");
		list.add("sendAuditReport");
		list.add("getBookValues");
		list.add("editBookValue");
		list.add("newBookValue");
		list.add("toSettlement");
		return list;
	}

	private List<String> getUserActions() {
		List<String> list = getGuestActions();
		list.add("insertCostMatchPrivate");
		list.add("updateCostMatchPrivate");
		list.add("deleteCostMatchPrivate");
		list.add("updateBtwMatchPrivate");		
		list.add("insertKost");
		list.add("updateKost");
		list.add("afschrijvenKost");
		list.add("insertKostLijst");
		list.add("insertAccount");
		list.add("updateAccount");
		list.add("insertAccountBalance");
		list.add("updateActivum");	
		list.add("updateBookValue");
		list.add("insertBookValue");
		list.add("updateSettlement");		
		return list;
	}

	private List<String> getAdminActions() {
		List<String> list = getUserActions();
		list.add("insertKostmatch");
		list.add("updateKostmatch");
		list.add("updateBtwmatch");		
		list.add("insertKostensoort");
		list.add("insertPrivateExpenses");		
		list.add("updateKostensoort");
		list.add("getDatabaseInfo");
		list.add("changeDatabase");
		return list;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		ActionErrors errors = new ActionErrors();
		String action = req.getRequestURI().replaceAll(".*/", "").replace(".do", "");
		if (!action.equals("login") && !action.equals("toLogin") && !action.equals("logout") && !action.equals("setLanguage")) {
			if (user == null) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.authentication.required"));
			} else {
				String role = user.getRole();
				List<String> actions = null;
				if ("user".equals(role) && !user.isFrozen() && user.isPaid()) {
					actions = getUserActions();
				} else if ("admin".equals(role)) {
					actions = getAdminActions();
				} else {
					actions = getGuestActions();
				}
				boolean hasRole = false;
				for (String action2 : actions) {
					if (action.equals(action2)) {
						hasRole = true;
					}
				}
				if (!hasRole) {
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.authorization.required"));
				}
			}
		}
		// Initialize language
		if (session.getAttribute("languageForm") == null) {
			LanguageForm languageForm = new LanguageForm();
			languageForm.setLocale(request.getLocale().toString());
			session.setAttribute("languageForm", languageForm);
		}
		if (errors.isEmpty()) {
			chain.doFilter(request, response);
		} else {
			req.setAttribute(Globals.ERROR_KEY, errors);
			req.getRequestDispatcher(onErrorUrl).forward(req, res);
		}
	}

	public void destroy() {
	}
}
