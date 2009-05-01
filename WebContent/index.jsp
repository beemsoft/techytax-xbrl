<%--
Copyright 2009 Hans Beemsterboer

This file is part of the TechyTax program.

TechyTax is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 3 of the License, or
(at your option) any later version.

TechyTax is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with TechyTax; if not, write to the Free Software
Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
--%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%
	if (request.getParameter("logoff") != null) {
		session.invalidate();
		response.sendRedirect("http://localhost:8080"
				+ request.getContextPath());
		return;
	}
%>
<h4 class="section"><bean:message key="welcome.title" /></h4>
<table>
	<tr>
		<td valign="top">
		<p><bean:message key="welcome.intro" /></p>
		<p><bean:message key="welcome.security"/></p>
		<p><a href="help.html"/><bean:message key="welcome.install"/></a></p>		
		<p><a href="releasenotes.html"/><bean:message key="welcome.release"/></a></p>		
		</td>
	</tr>
</table>