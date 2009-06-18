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
<%@ page import="org.techytax.domain.Kost"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<jsp:useBean id="balansForm" scope="session" type="org.techytax.struts.form.BalansForm" />

<%
		List<Kost> res = (List<Kost>) request.getAttribute("kostLijst");
		if (balansForm != null && balansForm.getBalansSoort() != null) {
		if (balansForm.getBalansSoort().equals("btwBalans")) {
%>
<table>
<tr>
<td>
<b><bean:message key="overview.vat.return"/></b></td><td align="right"><%=request.getAttribute("balans")%></td>
</tr>
<tr>
<td>
<bean:message key="overview.vat.in"/></td><td align="right">
<%=request.getAttribute("btwIn")%>
</td>
</tr>
<tr>
<td>
<bean:message key="overview.vat.out"/></td><td align="right">
<%=request.getAttribute("btwOut")%>
</td>
</tr>
<tr>
<td>
<bean:message key="overview.vat.correction"/></td><td align="right">
<%=request.getAttribute("btwCorrection")%>
</td>
</tr>
<tr>
<td>
<bean:message key="overview.turnover.gross"/></td><td align="right">
<%=request.getAttribute("brutoOmzet")%>
</td>
</tr>
<tr>
<td>
<b><bean:message key="overview.turnover.net"/></b></td><td align="right">
<%=request.getAttribute("nettoOmzet")%>
</td>
</tr>
</table>
<%
} else if (balansForm.getBalansSoort().equals("rekeningBalans")) {
%>
<p><b><bean:message key="overview.balance.account"/>: <%=request.getAttribute("balans")%> </b></p>
<p><b><bean:message key="overview.balance.savings"/>: <%=request.getAttribute("sparen")%> </b></p>
<p><b><bean:message key="overview.balance.private"/>: <%=request.getAttribute("private")%> </b></p>
<%
} else if (balansForm.getBalansSoort().equals("kostenBalans")) {
%>
<p><b><bean:message key="overview.cost.total.out"/>: <%=request.getAttribute("kosten")%> </b>
 <b><bean:message key="overview.cost.total.in"/>: <%=request.getAttribute("baten")%> </b></p>
<%
		} else if (balansForm.getBalansSoort().equals(
		"reiskostenBalans")) {
%>

<p><b><bean:message key="overview.travel.public"/>: <%=request.getAttribute("kostenOv")%>
</b></p>
<p><b><bean:message key="overview.travel.car.net"/>: <%=request.getAttribute("kostenAutoZonderBtw")%>
</b></p>
<p><b><bean:message key="overview.travel.car.gross"/>: <%=request.getAttribute("kostenAutoMetBtw")%>
</b></p>
<p><b><bean:message key="overview.travel.car.vat"/>: <%=request.getAttribute("verschil")%> </b></p>
<%
	} else if (res != null && res.size() > 0 && balansForm.getBalansSoort().equals("afschrijvingen")) {
%>
<a href="<%=request.getContextPath()%>/insertActiva.do"><bean:message key="cost.depreciate.process"/></a>
<%
	}
	}
%>