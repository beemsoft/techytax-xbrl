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
<%@ page import="java.util.List"%>
<%@ page import="org.techytax.domain.Activa"%>
<%@ page import="org.techytax.domain.Passiva"%>

<%@ taglib uri="struts-bean" prefix="bean"%>

<jsp:useBean id="overzicht" class="org.techytax.domain.FiscalOverview"
	scope="request" />
	
<%
	int boekjaar = overzicht.getJaar();
%>
<h4 class="section"><bean:message key="overview.fiscal.title"/> <%=boekjaar%></h4>
<h4><bean:message key="overview.fiscal.statement"/></h4>
<table class="overviewTable">
	<tr>
		<td><bean:message key="overview.turnover.net"/>:</td>
		<td></td>		
		<td align="right"><%=overzicht.getNettoOmzet()%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.depreciation.car"/>:</td>
		<td align="right">-<%=overzicht.getAfschrijvingAuto()%></td>
		<td></td>		
	</tr>
<% if (overzicht.getAfschrijvingAutoCorrectie() > 0) {
%>	
	<tr>
		<td><bean:message key="overview.fiscal.depreciation.car.correction"/>:</td>
		<td align="right">+<%=overzicht.getAfschrijvingAutoCorrectie()%></td>
		<td></td>		
	</tr>
<% } %>
	<tr>
		<td><bean:message key="overview.fiscal.depreciation.other"/>:</td>
		<td align="right">-<%=overzicht.getAfschrijvingOverig()%></td>
		<td></td>		
	</tr>
<% if (overzicht.getAfschrijvingOverigCorrectie() > 0) {
%>	
	<tr>
		<td><bean:message key="overview.fiscal.depreciation.other.correction"/>:</td>
		<td align="right">+<%=overzicht.getAfschrijvingOverigCorrectie()%></td>
		<td></td>		
	</tr>
<% } %>	
	<tr>
		<td><bean:message key="overview.fiscal.depreciation.total"/></td>
		<td></td>		
		<td align="right"><%=overzicht.getAfschrijvingTotaal()%></td>
	</tr>	
	<tr>
		<td><bean:message key="overview.fiscal.income.car"/>:</td>
		<td align="right">+<%=overzicht.getBijtellingAuto()%></td>
		<td></td>		
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.cost.car"/>:</td>
		<td align="right">-<%=overzicht.getKostenAuto()%></td>
		<td></td>		
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.cost.car.deductable"/></td>
		<td></td>		
		<td align="right"><%=overzicht.getKostenAutoAftrekbaar()%></td>
	</tr>	
	<tr>
		<td><bean:message key="overview.fiscal.cost.transport"/>:</td>
		<td></td>		
		<td align="right"><%=overzicht.getKostenOverigTransport()%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.cost.other"/>:</td>
		<td></td>		
		<td align="right">-<%=overzicht.getKostenOverig()%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.profit"/>:</td>
		<td></td>		
		<td align="right"><b><%=overzicht.getWinst()%></b></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.pension"/>:</td>
		<td></td>		
		<td align="right">(<%=overzicht.getOudedagsReserveMaximaal()%>)</td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.investment.deduction"/>:</td>
		<td></td>		
		<td align="right">(<%=overzicht.getInvestmentDeduction()%>)</td>
	</tr>	
</table>

<h4><bean:message key="overview.fiscal.activa"/></h4>
<table border="1" class="overviewTable">
	<tr>
		<th><bean:message key="overview.fiscal.activa.type"/></th>
		<th><bean:message key="overview.fiscal.initial.cost"/></th>
		<th><bean:message key="overview.fiscal.value.begin"/></th>
		<th><bean:message key="overview.fiscal.value.end"/></th>
		<th><bean:message key="overview.fiscal.value.rest"/></th>
	</tr>
	<%
		int totaalBegin = 0;
		int totaalEind = 0;
		List<Activa> res = overzicht.getActiva();
		if (res != null) {
			for (int i = 0; i < res.size(); i++) {

				Activa obj = null;
				obj = res.get(i);
				if (obj != null) {
					int boekwaardeBegin = 0;
					if (obj.getBoekjaar() == boekjaar - 1) {
						boekwaardeBegin = obj.getSaldo().intValue();
						totaalBegin += boekwaardeBegin;						
						i++;
						if (i < res.size()) {
							obj = res.get(i);
						}							
					}
					totaalEind += obj.getSaldo().intValue();
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
		<td align="right"><%=boekwaardeBegin %></td>
		<td align="right"><%=obj.getSaldo() %></td>
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
		<td align="right"><%=totaalBegin %></td>
		<td align="right"><b><%=totaalEind %></b></td>
		<td></td>
	</tr>
</table>
<h4><bean:message key="overview.fiscal.passiva"/></h4>
<table border="1" class="overviewTable">
	<tr>
		<th><bean:message key="overview.fiscal.passiva.type"/></th>
		<th><bean:message key="overview.fiscal.value.begin"/></th>
		<th><bean:message key="overview.fiscal.value.end"/></th>
	</tr>
		<%
		totaalBegin = 0;
		totaalEind = 0;
		List<Passiva> passivaList = overzicht.getPassiva();
		if (res != null) {
			for (int i = 0; i < passivaList.size(); i++) {

				Passiva obj = null;
				obj = passivaList.get(i);
				if (obj != null) {
					int boekwaardeBegin = 0;
					if (obj.getBoekjaar() == boekjaar - 1) {
						boekwaardeBegin = obj.getSaldo().intValue();
						totaalBegin += boekwaardeBegin;							
						i++;
						if (i < res.size()) {
							obj = passivaList.get(i);
						}					
					}
					totaalEind += obj.getSaldo().intValue();
	%>
	<tr>
		<td><%=obj.getOmschrijving() %></td>
		<td align="right"><%=boekwaardeBegin %></td>
		<td align="right"><%=obj.getSaldo() %></td>
	</tr>
	<%
			}
			}
		}
	%>
	<tr>
		<td><bean:message key="label.total"/></td>
		<td align="right"><%=totaalBegin %></td>
		<td align="right"><b><%=totaalEind %></b></td>
	</tr>
</table>
<h4><bean:message key="overview.fiscal.withdrawal"/></h4>
<table class="overviewTable">
	<tr>
		<td><bean:message key="overview.fiscal.withdrawal.total"/>:</td>
		<td align="right"><b><%=overzicht.getOnttrekking().getTotaleOnttrekking()%></b></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.withdrawal.cash"/>:</td>
		<td align="right"><b><%=overzicht.getOnttrekking().getWithdrawalCash()%></b></td>
	</tr>	
</table>
<h4><bean:message key="overview.fiscal.tax.prepaid"/></h4>
<table class="overviewTable">
	<tr>
		<td><bean:message key="overview.fiscal.tax.prepaid.income"/>:</td>
		<td align="right"><b><%=overzicht.getPrepaidTax().getPrepaidIncome()%></b></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.tax.prepaid.health"/>:</td>
		<td align="right"><b><%=overzicht.getPrepaidTax().getPrepaidHealth()%></b></td>
	</tr>	
</table>
