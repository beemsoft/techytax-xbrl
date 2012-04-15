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

<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<script language="JavaScript">
var cal13 = new CalendarPopup();
</script>
<table border="0">
	<tr>
	<td><bean:message key="label.saldo"/>:</td>
		<td><html:text property="balance" size="20" /></td>
	</tr>
	<tr>
		<td><bean:message key="label.date"/>:</td>
		<td><html:text property="datum" /><a href="#"
			onclick="cal13.select(document.forms[0].datum,'datum','yyyy-MM-dd'); return false;"
			title="<bean:message key="label.date.select"/>" name="datum" id="datum">select</a></td>
	</tr>
</table>