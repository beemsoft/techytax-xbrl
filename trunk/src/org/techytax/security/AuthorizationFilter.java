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

public class AuthorizationFilter implements Filter {
	private String onErrorUrl;

	private static final String LOGIN = "login";
	private static final String LOGOUT = "logout";

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
		return list;
	}

	private List<String> getUserActions() {
		List<String> list = getGuestActions();
		list.add("insertKostmatch");
		list.add("updateKostmatch");
		list.add("updateBtwmatch");
		list.add("insertKost");
		list.add("updateKost");
		list.add("afschrijvenKost");
		list.add("insertKostLijst");
		list.add("insertPrivateExpenses");
		list.add("insertActiva");
		list.add("insertAccount");
		list.add("updateAccount");
		list.add("insertAccountBalance");
		list.add("sendVatReport");
		list.add("sendAuditReport");
		return list;
	}

	private List<String> getAdminActions() {
		List<String> list = getUserActions();
		list.add("insertKostensoort");
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
		if (!action.equals(LOGIN) && !action.equals(LOGOUT)) {
			if (user == null) {
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.authentication.required"));
			} else {
				String role = user.getRole();
				List<String> actions = null;
				if ("guest".equals(role)) {
					actions = getGuestActions();
				} else if ("admin".equals(role)) {
					actions = getAdminActions();
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
