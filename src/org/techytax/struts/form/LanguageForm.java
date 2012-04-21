package org.techytax.struts.form;

import org.apache.struts.action.ActionForm;

public class LanguageForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String locale;
	
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

}
