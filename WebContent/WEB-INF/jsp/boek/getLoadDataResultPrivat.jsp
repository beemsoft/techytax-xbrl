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
<%@ taglib uri="struts-html" prefix="html" %>
<%@ taglib uri="struts-bean" prefix="bean"%>

<%
	List<Cost> res = (List<Cost>) session.getAttribute("kostLijst");
%>

<h4><bean:message key="load.data"/></h4>
<a href="<%=request.getContextPath()%>/insertPrivateExpenses.do"><bean:message key="load.data.process"/></a>

<table cellspacing="0" border="1" class="overviewTable">
<tr>
<th width="10%"><bean:message key="label.date"/></th>
<th width="10%"><bean:message key="label.amount"/></th>
<th>In</th>
<th><bean:message key="label.descr"/></th>
</tr>

<%
	if (res != null) {
	for (int i=0; i<res.size(); i++) { 

		Cost obj = null;	
		obj = (Cost) res.get(i);
		if (obj != null) {
	String incoming = "-";
	if (obj.isIncoming()) {
		incoming = "+";
	}
%>

<tr valign="top">
<td><%=obj.getDate()%></td>
<td align="right"><%=obj.getAmount()%></td>
<td><%=incoming%></td>
<td><%=obj.getDescription()%></td>
</tr>
<%
		}

	}
}
%>
</table>


