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

<%@ page import="org.techytax.domain.Cost"%>
<%@ page import="java.util.List"%>
<%@ page import="java.math.BigDecimal"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<jsp:useBean id="balansForm" scope="session"
	type="org.techytax.struts.form.BalansForm" />

<%
	List<Cost> res = (List<Cost>) request.getAttribute("kostLijst");
	if (balansForm != null && balansForm.getBalansSoort() != null) {
		if (balansForm.getBalansSoort().equals("btwBalans")) {
%>
<table>
	<tr>
		<td><b><bean:message key="overview.vat.return" /></b></td>
		<td align="right"><%=request.getAttribute("balans")%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.vat.in" /></td>
		<td align="right"><%=request.getAttribute("btwIn")%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.vat.out" /></td>
		<td align="right"><%=request.getAttribute("btwOut")%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.vat.correction" /></td>
		<td align="right"><%=request.getAttribute("btwCorrection")%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.turnover.gross" /></td>
		<td align="right"><%=request.getAttribute("brutoOmzet")%></td>
	</tr>
	<tr>
		<td><b><bean:message key="overview.turnover.net" /></b></td>
		<td align="right"><%=request.getAttribute("nettoOmzet")%></td>
	</tr>
</table>

<%
	} else if (balansForm.getBalansSoort().equals("audit")) {
%>
<p>Audit</p>
<html:form action="/sendAuditReport.do">
<html:hidden property="beginDatum" value="<%=balansForm.getBeginDatum()%>"/>
<html:hidden property="eindDatum" value="<%=balansForm.getEindDatum()%>"/>
<html:submit/>
</html:form>
<%		
	} else if (balansForm.getBalansSoort().equals("rekeningBalans")) {
%>
<table>
	<tr>
		<td><bean:message key="overview.balance.account" /></td>
		<td align="right"><%=request.getAttribute("balans")%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.balance.account.actual" /></td>
		<td align="right"><%=request.getAttribute("actualBalance")%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.balance.double.check" /></td>
		<td align="right"><%=request.getAttribute("doubleCheck")%></td>
	</tr>	
	<tr>
		<td><bean:message key="overview.turnover.gross" /></td>
		<td align="right"><%=request.getAttribute("brutoOmzet")%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.balance.tax" /></td>
		<td align="right"><%=request.getAttribute("taxBalans")%></td>
	</tr>	
	<tr>
		<td><bean:message key="overview.balance.savings" /></td>
		<td align="right"><%=request.getAttribute("sparen")%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.balance.private" /></td>
		<td align="right"><%=request.getAttribute("private")%></td>
	</tr>
	<tr>
		<td>cost balance</td>
		<td align="right"><%=request.getAttribute("costBalance")%></td>
	</tr>	
</table>
<%
	} else if (balansForm.getBalansSoort().equals("kostenBalans")) {
%>
<table>
	<tr>
		<td><bean:message key="overview.cost.total.out" /></td>
		<td align="right"><%=request.getAttribute("kosten")%></td>
	</tr>
	<tr>
		<td>Current account costs</td>
		<td align="right"><%=request.getAttribute("costCurrentAccount")%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.cost.total.in" /></td>
		<td align="right"><%=request.getAttribute("baten")%></td>
	</tr>
</table>
<%
	} else if (balansForm.getBalansSoort().equals(
				"reiskostenBalans")) {
%>
<table>
	<tr>
		<td><bean:message key="overview.travel.public" /></td>
		<td align="right"><%=request.getAttribute("kostenOv")%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.travel.car.net" /></td>
		<td align="right"><%=request.getAttribute("kostenAutoZonderBtw")%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.travel.car.gross" /></td>
		<td align="right"><%=request.getAttribute("kostenAutoMetBtw")%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.vat.correction" /></td>
		<td align="right"><%=request.getAttribute("vatCorrection")%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.travel.car.vat" /></td>
		<td align="right"><%=request.getAttribute("verschil")%></td>
	</tr>
</table>

<%
	} else if (balansForm.getBalansSoort().equals("private")) {
		%>
		<table>
			<tr>
				<td><bean:message key="overview.private.monthly" /></td>
				<td align="right"><%=request.getAttribute("monthlyExpenses")%></td>
			</tr>
		</table>
<%
	}
	}
%>