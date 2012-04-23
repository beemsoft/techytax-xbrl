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

import java.util.Locale;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.techytax.struts.form.LanguageForm;

public final class SetLanguageAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LanguageForm languageForm = (LanguageForm) form;
		if (StringUtils.isNotEmpty(languageForm.getLocale())) {
			session.setAttribute(Globals.LOCALE_KEY, getLocale(languageForm.getLocale()));
		}
		return mapping.findForward("success");
	}
	
	private Locale getLocale(String locale) {
		StringTokenizer tokenizer = new StringTokenizer(locale, "_");
		if (tokenizer.countTokens() > 1) {
			return new Locale(tokenizer.nextToken(), tokenizer.nextToken());
		} else {
			return new Locale(locale);
		}
	}
}
