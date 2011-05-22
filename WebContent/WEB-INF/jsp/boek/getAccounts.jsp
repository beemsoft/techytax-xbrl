<%--
Copyright 2011 Hans Beemsterboer

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
<%@ page import="org.techytax.domain.Account"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-bean" prefix="bean"%>

<%
	List<Account> res = (List<Account>) request.getAttribute("accounts");
%>

<h4><bean:message key="accounts.title"/></h4>
<div class="margins">
<a href="newAccount.do"><bean:message key="account.new"/></a>

<table cellspacing="0" border="1" class="overviewTable">
	<tr>
		<th><bean:message key="label.id"/></th>
		<th><bean:message key="label.descr"/></th>
		<th><bean:message key="label.number"/></th>
		<th><bean:message key="label.name"/></th>
	</tr>

	<%
			if (res != null) {
			for (int i = 0; i < res.size(); i++) {

				Account obj = null;
				obj = (Account) res.get(i);
				if (obj != null) {
	%>

	<tr valign="top">
		<td><a href="editAccount.do?id=<%=obj.getId()%>"><%=obj.getId()%></a></td>
		<td><a href="getAccountBalance.do?id=<%=obj.getId()%>"><%=obj.getDescription()%></a></td>
		<td><%=obj.getNumber()%></td>
		<td><%=obj.getName()%></td>
	</tr>
	<%
				}
			}
		}
	%>
</table>
</div>

