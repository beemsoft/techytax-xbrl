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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.techytax.dao.KostensoortDao;
import org.techytax.domain.AccountType;
import org.techytax.domain.Kost;
import org.techytax.domain.Kostensoort;
import org.techytax.domain.User;
import org.techytax.helper.RekeningFileHelper;
import org.techytax.struts.form.UploadForm;

public class UploadFileAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, final HttpServletRequest request, HttpServletResponse response) throws Exception {

		UploadForm uploadForm = (UploadForm) form;
		User user = (User) request.getSession().getAttribute("user");
		
		FormFile myFile = uploadForm.getTheFile();

		String forward = "business";
		try {
			
			String fileName = myFile.getFileName();
			AccountType accountType = RekeningFileHelper.getAccountType(fileName, user.getId());
			switch (accountType) {
				case PRIVATE : forward = "private"; break;
			}
			
			KostensoortDao dao = new KostensoortDao();
			List<Kostensoort> kostensoortLijst = dao.getKostensoortLijst();
			InputStream is = myFile.getInputStream();

			List<Kost> result = RekeningFileHelper.readFile(new BufferedReader(new InputStreamReader(is)), kostensoortLijst, Long.toString(user.getId()));
			request.getSession().setAttribute("kostLijst", result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward(forward);
	}

}
