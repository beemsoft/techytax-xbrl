package org.techytax.zk;

import org.techytax.domain.User;
import org.techytax.zk.login.UserCredentialManager;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Area;

public class DemoModel {

	private static final String searchUrl = "http://www.zkoss.org/doc/searchresult.jsp?cx=008321236477929467003%3A63kdpeqkkvw&cof=FORID%3A11&q=";

	private String search;

	@Init
	public void init() {

	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	public boolean getLoggedOn() {
		return UserCredentialManager.getUser() != null;
	}
	
	public String getLoggedOnText() {
		String text = "Inloggen a.u.b.";
		User user = UserCredentialManager.getUser();
		if (user != null) {
			text = user.getCompanyName();
		}
		return text;
	}
	
	@Command
	public void logout() {
		UserCredentialManager.setUser(null);
		Executions.sendRedirect("login.zul"); 
	}	

	@Command
	public void search() {
		Executions.getCurrent().sendRedirect(searchUrl.concat(search), "_zk");
	}

	@Command
	public void displayArea(@ContextParam(ContextType.TRIGGER_EVENT) MouseEvent event) {
		Component component = event.getAreaComponent();

		if (component instanceof Area) {
			Area area = (Area) component;
			Clients.alert(area.getAttribute("entity") + ":"
					+ area.getTooltiptext());
		}
	}

}