<%--
Copyright 2012 Hans Beemsterboer

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
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@ page import="org.techytax.domain.AccountBalance"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%
	List<AccountBalance> res = (List<AccountBalance>) request
			.getAttribute("accountBalance");
	String accountId = (String)request.getAttribute("accountId");
%>

<h4><bean:message key="label.account" /></h4>
<div class="margins"><html:link action="/newAccountBalance.do">
	<html:param name="accountId"><%=accountId%></html:param>
	<bean:message key="account.balance.new" />
</html:link>

<table cellspacing="0" border="1" class="overviewTable">
	<tr>
		<th><bean:message key="label.date" /></th>
		<th><bean:message key="label.saldo" /></th>
	</tr>

	<%
		if (res != null) {
			for (int i = 0; i < res.size(); i++) {

				AccountBalance obj = null;
				obj = (AccountBalance) res.get(i);
				if (obj != null) {
	%>

	<tr valign="top">
		<td><%=obj.getDatum()%></td>
		<td><%=obj.getBalance()%></td>
	</tr>
	<%
		}
			}
		}
	%>
</table>
</div>

