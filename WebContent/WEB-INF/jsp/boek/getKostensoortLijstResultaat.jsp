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
<%@ page import="org.techytax.domain.Kostensoort"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-bean" prefix="bean"%>

<%
	List<Kostensoort> res = (List<Kostensoort>) request.getAttribute("kostensoortLijst");
%>

<h4><bean:message key="costtype.list.title"/></h4>
<div class="margins">
<a href="newKostensoort.do"><bean:message key="costtype.new"/></a>

<table cellspacing="0" border="1" class="overviewTable">
	<tr>
		<th><bean:message key="label.id"/></th>
		<th><bean:message key="label.descr"/></th>
		<th><bean:message key="label.incoming.payment"/></th>
		<th><bean:message key="label.vat.appl"/></th>
		<th><bean:message key="label.current.account"/></th>
		<th><bean:message key="label.tax.deductible"/></th>
		<th><bean:message key="label.investment"/></th>		
	</tr>

	<%
			if (res != null) {
			for (int i = 0; i < res.size(); i++) {

				Kostensoort obj = null;
				obj = (Kostensoort) res.get(i);
				if (obj != null) {
	%>

	<tr valign="top">
		<td><a href="editKostensoort.do?kostenSoortId=<%=obj.getKostenSoortId()%>"><%=obj.getKostenSoortId()%></a></td>
		<td><bean:message key="<%=obj.getOmschrijving()%>"/></td>
		<td><% if (obj.isBijschrijving()) {%>x<% } %></td>
		<td><% if (obj.isBtwVerrekenbaar()) {%>x<% } %></td>
		<td><% if (obj.isBalansMeetellen()) {%>x<% } %></td>
		<td><% if (obj.isAftrekbaar()) {%>x<%} %></td>		
		<td><%
				if (obj.isInvestering()) {
				%>x<%} %></td>				
	</tr>
	<%
			}

			}
		}
	%>
</table>
</div>

