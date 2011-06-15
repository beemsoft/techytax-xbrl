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
package org.techytax.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailHelper {

	private static Properties props;

	public static void loadProperties() throws Exception {
		if (props != null)
			return;

		props = new Properties();
		try {
			File file = new File("test");
			System.out.println("Test: " + file.getAbsolutePath());
			props.load(new FileInputStream("mail.properties"));
			props.put("mail.transport.protocol", "smtps");
			props.put("mail.smtps.starttls.enable", "true");
			props.put("mail.smtps.auth", "true");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");

		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static void sendDutchVatDeclaration(String message) throws Exception {
		loadProperties();
		String to = props.getProperty("message.to");
		System.out.println("Test: " + to);
		String subj = "OB aangifte";
		Session session = Session.getDefaultInstance(props);
//		session.setDebug(true);
		Message msg = new MimeMessage(session);
		InternetAddress[] toAddrs = null;

		if (to != null) {
			toAddrs = InternetAddress.parse(to, false);
			msg.setRecipients(Message.RecipientType.TO, toAddrs);
		} else
			throw new MessagingException("No \"To\" address specified");

		if (subj != null)
			msg.setSubject(subj);

		msg.setFrom(new InternetAddress(props.getProperty("mail.from")));
		MimeMultipart multipart = new MimeMultipart("related");
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(message, "text/html");
		multipart.addBodyPart(messageBodyPart);
		msg.setContent(multipart);
		Transport tr = session.getTransport("smtp");
		tr.connect(props.getProperty("mail.smtp.host"), 465, props.getProperty("mail.smtp.user"), props.getProperty("mail.smtp.password"));
		msg.saveChanges();
		tr.sendMessage(msg, msg.getAllRecipients());
		tr.close();
	}

}
