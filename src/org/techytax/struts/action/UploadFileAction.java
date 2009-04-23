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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.tomcat.util.http.fileupload.DiskFileUpload;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.techytax.dao.KostensoortDao;
import org.techytax.domain.Kost;
import org.techytax.domain.Kostensoort;
import org.techytax.helper.RekeningFileHelper;

public class UploadFileAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			final HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = "success";
		try {
			List<Kost> result = uploadData(request);
			request.getSession().setAttribute("kostLijst", result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward(forward);
	}

	@SuppressWarnings("unchecked")
	public List<Kost> uploadData(HttpServletRequest request) {
		DiskFileUpload upload = new DiskFileUpload();
		// Set maximum size;
		upload.setSizeMax(-1);
		// parse this request by the handler
		// this gives us a list of items from the request
		try {
			List<Kost> items = upload.parseRequest(request);
			return getUploadedFile(items, request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		return null;

	}

	private List<Kost> getUploadedFile(List<Kost> items, HttpServletRequest request) {

		Iterator<Kost> itr = items.iterator();
		List<Kost> result = null;

		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();

			// check if the current item is a form field or an uploaded file
			if (item.isFormField()) {

				// get the name of the field
				String fieldName = item.getFieldName();
				System.out.println("Fieldname: " + fieldName + ": "
						+ item.getString());

			} else {
				System.out.println("Dit is de file zelf: "
						+ item.getFieldName());
				try {
					KostensoortDao dao = new KostensoortDao();
					List<Kostensoort> kostensoortLijst = dao.getKostensoortLijst();
					InputStream is = item.getInputStream();
					result = RekeningFileHelper.readFile(new BufferedReader(
							new InputStreamReader(is)), kostensoortLijst);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
		return result;
	}

}
