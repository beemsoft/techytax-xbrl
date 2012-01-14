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
<%@ page import="org.techytax.domain.Boekwaarde"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-bean" prefix="bean"%>

<%
	List<Boekwaarde> res = (List<Boekwaarde>) request.getAttribute("bookValues");
%>

<h4><bean:message key="bookvalues.title"/></h4>
<div class="margins">
<table>
<tr>
<td valign="top">
<table cellspacing="0" border="1" class="overviewTable">
	<tr>
		<th><bean:message key="label.id"/></th>
		<th><bean:message key="label.descr"/></th>
		<th><bean:message key="label.year"/></th>
		<th><bean:message key="label.saldo"/></th>
	</tr>

	<%
			if (res != null) {
			for (int i = 0; i < res.size(); i++) {

				Boekwaarde obj = null;
				obj = (Boekwaarde) res.get(i);
				if (obj != null) {
	%>

	<tr valign="top">
		<td><a href="editBookValue.do?id=<%=obj.getId()%>"><%=obj.getId()%></a></td>
		<td><%=obj.getDescription()%></td>
		<td><%=obj.getJaar()%></td>
		<td><%=obj.getSaldo()%></td>
	</tr>
	<%
				}
			}
		}
	%>
</table>
</td>
<td>
<html:img page="/chart?chartType=bookValues" />
</td>
</tr>
</table>
</div>

