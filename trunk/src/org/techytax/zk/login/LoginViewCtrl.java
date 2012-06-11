package org.techytax.zk.login;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.techytax.domain.User;
import org.techytax.security.AuthenticationException;
import org.techytax.security.SecurityService;
import org.techytax.security.SecurityServiceImpl;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class LoginViewCtrl extends SelectorComposer<Window> {

	private static final long serialVersionUID = 2026810060053024173L;
	@Wire
	private Textbox nameTxb, passwordTxb;

	@Listen("onClick=#confirmBtn; onOK=#loginWin")
	public void confirm() throws WrongValueException, AuthenticationException, NoSuchAlgorithmException, IOException {
		doLogin();
		navigateApp();
	}

	private void doLogin() throws WrongValueException, AuthenticationException, NoSuchAlgorithmException, UnsupportedEncodingException {
		SecurityService securityService = new SecurityServiceImpl();
		User user = securityService.authenticate(nameTxb.getValue(), Sha.SHA1(passwordTxb.getValue()));
		UserCredentialManager.setUser(user);

	}

	private void navigateApp() throws IOException {
		Executions.sendRedirect("/zul/vat.zul");
	}

	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		User user = UserCredentialManager.getUser();
		if (user != null) {
			navigateApp();
		}

	}

}
