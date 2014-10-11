/**
 * Copyright 2014 Hans Beemsterboer
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
package org.techytax.zk.login;

import java.util.List;

import org.apache.cxf.common.util.CollectionUtils;
import org.techytax.domain.User;
import org.techytax.jpa.dao.GenericDao;
import org.techytax.jpa.entities.VatDeclaration;
import org.techytax.log.AuditLogger;
import org.techytax.log.AuditType;
import org.techytax.security.AuthenticationException;
import org.techytax.security.SecurityService;
import org.techytax.security.SecurityServiceImpl;
import org.techytax.util.VersionHelper;
import org.zkoss.bind.annotation.Command;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class LoginModel {

	private String username = "";

	private String password = "";
	
	public boolean isSaasVersion() {
		return VersionHelper.isSaasVersion();
	}
	
	public String getVersion() {
		return VersionHelper.getVersion();
	}

	@Command
	public void login() throws Exception {
		SecurityService securityService = new SecurityServiceImpl();
		try {
			User user = securityService.authenticate(username, Sha.SHA1(password));
			UserCredentialManager.setUser(user);
			Executions.sendRedirect("/zul/zk_calendar.zul");
		} catch (AuthenticationException e) {
			Messagebox.show(e.getMessage(), null, new Messagebox.Button[] { Messagebox.Button.OK }, Messagebox.EXCLAMATION, null);
		}
	}

	@Command
	public void register() {
		String template = "~./saas/register.zul";
		Window window = (Window) Executions.createComponents(template, null, null);
		window.doModal();
	}

	@Command
	public void forgotPassword() {
		String template = "~./saas/forgot-password.zul";
		Window window = (Window) Executions.createComponents(template, null, null);
		window.doModal();
	}

	@Command
	public void editUser() {
		if (!isGuestUser()) {
			String template = "~./saas/edit-user.zul";
			Window window = (Window) Executions.createComponents(template, null, null);
			window.doModal();
		}
	}

	private boolean isGuestUser() {
		User user = UserCredentialManager.getUser();
		if (user != null && "guest".equals(user.getUsername())) {
			return true;
		}
		return false;
	}

	@Command
	public void termsAndConditions() {
		String template = "~./saas/av.zul";
		Window window = (Window) Executions.createComponents(template, null, null);
		window.doPopup();
	}
	
	@Command
	public void question() {
		String template = "~./saas/question.zul";
		Window window = (Window) Executions.createComponents(template, null, null);
		window.doModal();
	}	

	public boolean getLoggedOn() {
		return UserCredentialManager.getUser() != null;
	}

	public boolean getDisplayVatWarning() throws Exception {
		User user = UserCredentialManager.getUser();
		if (user != null) {
			GenericDao<VatDeclaration> vatDeclarationDao = new GenericDao<>(VatDeclaration.class);
			List<VatDeclaration> vatDeclarationsUnpaid = vatDeclarationDao.findByNamedQuery("VatDeclaration.findUnpaid", user);
			if (!CollectionUtils.isEmpty(vatDeclarationsUnpaid)) {
				return true;
			}
		}
		return false;
	}

	public String getLoggedOnText() {
		String text = Labels.getLabel("logon.please");
		User user = UserCredentialManager.getUser();
		if (user != null) {
			text = user.getCompanyName();
		}
		return text;
	}

	public String getUser() {
		User user = UserCredentialManager.getUser();
		if (user != null) {
			return user.getFullName();
		} else {
			return "";
		}
	}

	@Command
	public void logout() {
		User user = UserCredentialManager.getUser();
		AuditLogger.log(AuditType.LOGOFF, user);
		UserCredentialManager.setUser(null);
		Executions.sendRedirect("login.zul");
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
