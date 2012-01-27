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
<%@ page import="java.util.List"%>
<%@ page import="org.techytax.domain.Activum"%>
<%@ page import="org.techytax.domain.Passivum"%>
<%@ page import="org.techytax.report.domain.ReportBalance"%>

<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-bean" prefix="bean"%>

<jsp:useBean id="overview" class="org.techytax.domain.FiscalOverview"
	scope="session" />
<jsp:useBean id="user" class="org.techytax.domain.User"
	scope="session" />	
	
<%
	int boekjaar = overview.getJaar();
%>
<h4 class="section"><bean:message key="overview.fiscal.title"/> <%=boekjaar%> - <%=user.getCompanyName() %></h4>
<h4><bean:message key="overview.fiscal.statement"/></h4>
<table>
<tr>
<td>
<table class="overviewTable">
	<tr>
		<td><bean:message key="overview.turnover.net"/>:</td>
		<td></td>		
		<td align="right"><%=overview.getNettoOmzet()%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.interest"/>:</td>
		<td></td>		
		<td align="right">+<%=overview.getInterestFromBusinessSavings()%></td>
	</tr>	
	<tr>
		<td><bean:message key="overview.fiscal.repurchase"/>:</td>
		<td></td>
		<td align="right">-<%=overview.getRepurchase()%></td>
	</tr>	
	<tr>
		<td><bean:message key="overview.fiscal.depreciation.car"/>:</td>
		<td align="right">-<%=overview.getAfschrijvingAuto()%></td>
		<td></td>		
	</tr>
<% if (overview.getAfschrijvingAutoCorrectie() > 0) {
%>	
	<tr>
		<td><bean:message key="overview.fiscal.depreciation.car.correction"/>:</td>
		<td align="right">+<%=overview.getAfschrijvingAutoCorrectie()%></td>
		<td></td>		
	</tr>
<% } %>	
	<tr>
		<td><bean:message key="overview.fiscal.depreciation.other"/>:</td>
		<td align="right">-<%=overview.getAfschrijvingOverig()%></td>
		<td></td>		
	</tr>
<% if (overview.getAfschrijvingOverigCorrectie() > 0) {
%>	
	<tr>
		<td><bean:message key="overview.fiscal.depreciation.other.correction"/>:</td>
		<td align="right">+<%=overview.getAfschrijvingOverigCorrectie()%></td>
		<td></td>		
	</tr>
<% } %>
	<tr>
		<td><bean:message key="overview.fiscal.depreciation.total"/></td>
		<td></td>		
		<td align="right">-<%=overview.getAfschrijvingTotaal()%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.income.car"/>:</td>
		<td align="right">+<%=overview.getBijtellingAuto()%></td>
		<td></td>		
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.depreciation.car"/>:</td>
		<td align="right">-<%=overview.getAfschrijvingAuto()%></td>
		<td></td>		
	</tr>
<% if (overview.getAfschrijvingAutoCorrectie() > 0) {
%>	
	<tr>
		<td><bean:message key="overview.fiscal.depreciation.car.correction"/>:</td>
		<td align="right">+<%=overview.getAfschrijvingAutoCorrectie()%></td>
		<td></td>		
	</tr>
<% } %>	
	<tr>
		<td><bean:message key="overview.fiscal.cost.car"/>:</td>
		<td align="right">-<%=overview.getKostenAuto()%></td>
		<td></td>		
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.cost.car.deductable"/></td>
		<td></td>		
		<td align="right"><%=overview.getKostenAutoAftrekbaar()%></td>
	</tr>	
	<tr>
		<td><bean:message key="overview.fiscal.cost.transport"/>:</td>
		<td></td>		
		<td align="right">-<%=overview.getKostenOverigTransport()%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.cost.other"/>:</td>
		<td></td>		
		<td align="right">-<%=overview.getKostenOverig()%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.profit"/>:</td>
		<td></td>		
		<td align="right"><b><%=overview.getWinst()%></b></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.pension"/>:</td>
		<td></td>		
		<td align="right">(<%=overview.getOudedagsReserveMaximaal()%>)</td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.investment.deduction"/>:</td>
		<td></td>		
		<td align="right">(<%=overview.getInvestmentDeduction()%>)</td>
	</tr>	
</table>
</td>
<td>
<html:img page="/chart?chartType=profitAndLoss" />
</td>
</tr>
</table>

<h4><bean:message key="overview.fiscal.activa"/></h4>
<table>
<tr>
<td>
<table border="1" class="overviewTable" width="300px">
	<tr>
		<th><bean:message key="overview.fiscal.activa.type"/></th>
		<th><bean:message key="overview.fiscal.initial.cost"/></th>
		<th><bean:message key="overview.fiscal.value.begin"/></th>
		<th><bean:message key="overview.fiscal.value.end"/></th>
		<th><bean:message key="overview.fiscal.value.rest"/></th>
	</tr>
	<%
		List<ReportBalance> res = overview.getActivaReport().getBalanceList();
		if (res != null) {
			for (int i = 0; i < res.size(); i++) {

				ReportBalance obj = null;
				obj = res.get(i);
				if (obj != null) {
	%>
	<tr>
		<td><%=obj.getOmschrijving() %></td>
		<% 
			String aanschafkosten = "";
			if (obj.getAanschafKosten() != null) {
				aanschafkosten = obj.getAanschafKosten().toString();
			}
		%>
		<td align="right"><%=aanschafkosten %></td>
		<td align="right"><%=obj.getBookValueBegin() %></td>
		<td align="right"><%=obj.getBookValueEnd() %></td>
		<% 
			String restwaarde = "";
			if (obj.getRestwaarde() != null) {
				restwaarde = obj.getRestwaarde().toString();
			}
		%>		
		<td align="right"><%=restwaarde %></td>
	</tr>
	<%
			}
			}
		}
	%>
	<tr>
		<td><bean:message key="label.total"/></td>
		<td></td>
		<td align="right"><%=overview.getActivaReport().getTotalBeginValue() %></td>
		<td align="right"><b><%=overview.getActivaReport().getTotalEndValue() %></b></td>
		<td></td>
	</tr>
</table>
</td>
<td>
<html:img page="/chart?chartType=activa" />
</td>
</tr>
</table>

<h4><bean:message key="overview.fiscal.passiva"/></h4>
<table>
<tr>
<td>
<table border="1" class="overviewTable" width="300px">
	<tr>
		<th><bean:message key="overview.fiscal.passiva.type"/></th>
		<th><bean:message key="overview.fiscal.value.begin"/></th>
		<th><bean:message key="overview.fiscal.value.end"/></th>
	</tr>
		<%
		res = overview.getPassivaReport().getBalanceList();	
		if (res != null) {
			for (int i = 0; i < res.size(); i++) {

				ReportBalance obj = null;
				obj = res.get(i);
				if (obj != null) {
	%>
	<tr>
		<td><%=obj.getOmschrijving() %></td>
		<td align="right"><%=obj.getBookValueBegin() %></td>
		<td align="right"><%=obj.getBookValueEnd() %></td>
	</tr>
	<%
			}
			}
		}
	%>
	<tr>
		<td><bean:message key="label.total"/></td>
		<td align="right"><%=overview.getPassivaReport().getTotalBeginValue() %></td>
		<td align="right"><b><%=overview.getPassivaReport().getTotalEndValue() %></b></td>
	</tr>
</table>
</td>
<td>
<html:img page="/chart?chartType=passiva" />
</td>
</tr>
</table>
<h4><bean:message key="overview.fiscal.withdrawal"/></h4>
<table class="overviewTable">
	<tr>
		<td><bean:message key="overview.fiscal.withdrawal.cash"/>:</td>
		<td align="right"><b><%=overview.getOnttrekking().getWithdrawalCash()%></b></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.withdrawal.car"/>:</td>
		<td align="right"><b><%=overview.getOnttrekking().getWithdrawalPrivateUsageBusinessCar()%></b></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.withdrawal.total"/>:</td>
		<td align="right"><b><%=overview.getOnttrekking().getTotaleOnttrekking()%></b></td>
	</tr>			
</table>
<h4><bean:message key="overview.fiscal.tax.prepaid"/></h4>
<table class="overviewTable">
	<tr>
		<td><bean:message key="overview.fiscal.tax.prepaid.income"/>:</td>
		<td align="right"><b><%=overview.getPrepaidTax().getPrepaidIncome()%></b></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.tax.prepaid.health"/>:</td>
		<td align="right"><b><%=overview.getPrepaidTax().getPrepaidHealth()%></b></td>
	</tr>	
</table>
