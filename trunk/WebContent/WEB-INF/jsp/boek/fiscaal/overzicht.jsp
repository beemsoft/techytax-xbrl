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
<%@ page import="java.util.List"%>
<%@ page import="org.techytax.domain.Activa"%>
<%@ page import="org.techytax.domain.Passiva"%>

<%@ taglib uri="struts-bean" prefix="bean"%>

<jsp:useBean id="overzicht" class="org.techytax.domain.FiscaalOverzicht"
	scope="request" />
	
<%
	int boekjaar = overzicht.getJaar();
	int balansSaldo = 0;
	int totaalOnttrekking = 0; 
%>
<h2><bean:message key="overview.fiscal.title"/> <%=boekjaar%></h2>
<h4><bean:message key="overview.fiscal.statement"/></h4>
<table class="overviewTable">
	<tr>
		<td><bean:message key="overview.turnover.net"/>:</td>
		<td align="right"><%=overzicht.getNettoOmzet()%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.depreciation.car"/>:</td>
		<td align="right"><%=overzicht.getAfschrijvingAuto()%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.depreciation.other"/>:</td>
		<td align="right"><%=overzicht.getAfschrijvingOverig()%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.cost.car"/>:</td>
		<td align="right"><%=overzicht.getKostenAuto()%> - <%=overzicht.getBijtellingAuto()%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.cost.transport"/>:</td>
		<td align="right"><%=overzicht.getKostenOverigTransport()%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.cost.other"/>:</td>
		<td align="right"><%=overzicht.getKostenOverig()%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.profit"/>:</td>
		<td align="right"><b><%=overzicht.getWinst()%></b></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.pension"/>:</td>
		<td align="right"><%=overzicht.getOudedagsReserveMaximaal()%></td>
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
					balansSaldo = totaalEind - totaalBegin;
					totaalOnttrekking = overzicht.getWinst() - balansSaldo;
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
		<td><bean:message key="overview.balance.account"/>:</td>
		<td align="right"><%=balansSaldo%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.withdrawal.total"/>:</td>
		<td align="right"><b><%=totaalOnttrekking%></b></td>
	</tr>
	<tr>
		<td><bean:message key="overview.balance.savings"/>:</td>
		<td align="right"><%=overzicht.getOnttrekking().getOpnameSaldo()%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.tax.prepaid"/>:</td>
		<td align="right"><%=overzicht.getOnttrekking().getVoorlopigeAanslag()%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.activa.car"/>:</td>
		<td align="right"><%=overzicht.getKostenAuto()%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.tax.return"/>:</td>
		<td align="right"><%=overzicht.getOnttrekking().getTeruggave()%></td>
	</tr>
	<tr>
		<td><bean:message key="overview.fiscal.withdrawal.blackhole"/>:</td>
		<td align="right"><%=totaalOnttrekking+overzicht.getOnttrekking().getOpnameSaldo()-overzicht.getOnttrekking().getVoorlopigeAanslag()-overzicht.getKostenAuto()+overzicht.getOnttrekking().getTeruggave()%></td>
	</tr>
</table>
