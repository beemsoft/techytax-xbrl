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
package org.techytax.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.techytax.dao.UserDao;
import org.techytax.domain.User;

public class UserFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			UserDao userDao = new UserDao();
			try {
				user = userDao.getUser(user.getUsername());
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (user == null) {
				session.invalidate();
//				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.user.removed"));
			} else if (user.isBlocked()) {
				session.invalidate();
//				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.user.blocked"));
			}
		}
//		if (errors.isEmpty()) {
			chain.doFilter(request, response);
//		} else {
//			req.setAttribute(Globals.ERROR_KEY, errors);
//			req.getRequestDispatcher("/index.jsp").forward(req, res);
//		}
	}

	public void destroy() {
	}
}
