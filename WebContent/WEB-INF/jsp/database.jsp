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
<%@ taglib uri="struts-html" prefix="html" %>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ page import="org.techytax.util.IbatisUtil"%>
<%@ page import="org.techytax.util.ConnectionInfo"%>

<logic:present name="user" scope="session">
<logic:equal name="user" property="administrator" value="true">
<h4 class="section"><bean:message key="db.info"/></h4>
<html:form action="/changeDatabase.do">
<table class="formTable">
	<tr>
		<td><bean:message key="db.username"/></td>
		<td><html:text property="username"/></td>
	</tr>
	<tr>
		<td><bean:message key="db.password"/></td>
		<td><html:password property="password"/></td>
	</tr>	
	<tr>
		<td><bean:message key="db.host"/></td>
		<td><html:text property="host"/></td>
	</tr>
	<tr>
		<td><bean:message key="db.catalog"/></td>
		<td><html:text property="catalog"/></td>
	</tr>	
	<tr>
		<td colspan="2">
		<html:submit/>
		</td>
	</tr>
</table>
</html:form>
<html:errors/>
</logic:equal>
</logic:present>