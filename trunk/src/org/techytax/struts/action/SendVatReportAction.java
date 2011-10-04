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

import java.io.StringWriter;
import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.techytax.domain.User;
import org.techytax.mail.MailHelper;
import org.techytax.struts.form.VatReportForm;
import org.techytax.xml.dutch.vat.AangifteOmzetbelasting;
import org.techytax.xml.dutch.vat.ObjectFactory;
import org.techytax.xml.dutch.vat.AangifteOmzetbelasting.Berekening;
import org.techytax.xml.dutch.vat.AangifteOmzetbelasting.Communicatiegegevens;
import org.techytax.xml.dutch.vat.AangifteOmzetbelasting.IdentificerendeGegevens;

public class SendVatReportAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			final HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final ActionErrors errors = new ActionErrors();
		VatReportForm vatReportForm = (VatReportForm) form;
		User user = (User) request.getSession().getAttribute("user");
		ObjectFactory objectFactory = null;
		JAXBContext jc = null;
		Marshaller m = null;
		try {
			objectFactory = new ObjectFactory();
			jc = JAXBContext.newInstance("org.techytax.xml.dutch.vat");
			m = jc.createMarshaller();
			StringWriter writer = new StringWriter();
			m.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			AangifteOmzetbelasting aangifteOmzetbelasting = objectFactory
					.createAangifteOmzetbelasting();
			Berekening berekening = objectFactory
					.createAangifteOmzetbelastingBerekening();
			Communicatiegegevens communicatiegegevens = objectFactory
					.createAangifteOmzetbelastingCommunicatiegegevens();
			aangifteOmzetbelasting.getCommunicatiegegevens().add(
					communicatiegegevens);
			berekening.setTotaalTeBetalenTerugTeVragen(new BigInteger("2"));
			berekening.setVerschuldigdeOmzetbelasting(new BigInteger("3"));
			berekening.setVoorbelasting(new BigInteger("1"));
			aangifteOmzetbelasting.setBerekening(berekening);
			communicatiegegevens.setContactpersoonID("TT");
			communicatiegegevens.setNaamContactpersoon("TechyTax");
			communicatiegegevens.setSoortContactpersoon("Web");
			communicatiegegevens.setTelefoonContactpersoon("123456789");
			IdentificerendeGegevens identificerendeGegevens = objectFactory
					.createAangifteOmzetbelastingIdentificerendeGegevens();
			identificerendeGegevens.setAangiftejaar(new BigInteger("2010"));
			identificerendeGegevens.setAangiftetijdvak("01");
			identificerendeGegevens.setLevCode("TT");
			identificerendeGegevens.setMediumsoort("Mail");
			identificerendeGegevens.setMessageID("OB01");
			identificerendeGegevens.setOBNummer("OB nummer");

			XMLGregorianCalendar calendar = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar();
			calendar.setDay(1);
			calendar.setMonth(1);
			calendar.setYear(2010);
			identificerendeGegevens.setTijdstipAanmaak(calendar);
			identificerendeGegevens.setValutacode("EUR");
			identificerendeGegevens.setVersieApplicatie("1.2.1");
			aangifteOmzetbelasting
					.setIdentificerendeGegevens(identificerendeGegevens);
			m.marshal(aangifteOmzetbelasting, writer);
			System.out.println(writer.toString());
			MailHelper.sendDutchVatDeclaration(writer.toString(), user.getEmail());

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
