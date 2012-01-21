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
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<script language="JavaScript">
var cal13 = new CalendarPopup();
</script>
<table border="0">
	<tr>
		<td><bean:message key="label.descr"/>:</td>
		<td><html:text property="description" size="100" /></td>
	</tr>
	<tr>
	<td><bean:message key="label.number"/>:</td>
		<td><html:text property="number" size="20" /></td>
	</tr>
	<tr>
		<td><bean:message key="label.name"/>:</td>
		<td><html:text property="name" size="100" /></td>
	</tr>
	<tr>
		<td><bean:message key="label.date.opened"/>:</td>
		<td><html:text property="dateOpened" /><a href="#"
			onclick="cal13.select(document.forms[0].dateOpened,'dateOpened','yyyy-MM-dd'); return false;"
			title="<bean:message key="label.date.select"/>" name="dateOpened" id="dateOpened">select</a></td>
	</tr>
	<tr>
		<td><bean:message key="label.date.closed"/>:</td>
		<td><html:text property="dateClosed" /><a href="#"
			onclick="cal13.select(document.forms[0].dateClosed,'dateClosed','yyyy-MM-dd'); return false;"
			title="<bean:message key="label.date.select"/>" name="dateClosed" id="dateClosed">select</a></td>
	</tr>
	<tr>
		<td><bean:message key="label.type"/>:</td>
		<td><html:select property="type">
				<html:option value="0"><bean:message key="account.type.private"/></html:option>
				<html:option value="1"><bean:message key="account.type.business"/></html:option>
				<html:option value="2"><bean:message key="account.type.savings"/></html:option>
				<html:option value="3"><bean:message key="account.type.mortgage.loan"/></html:option>
				<html:option value="4"><bean:message key="account.type.mortgage.savings"/></html:option>
				<html:option value="5"><bean:message key="account.type.private.savings"/></html:option>				
			</html:select>
		</td>
	</tr>		
</table>