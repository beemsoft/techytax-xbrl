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
<%@ taglib uri="struts-logic" prefix="logic"%>
<script language="JavaScript">
var cal13 = new CalendarPopup();
</script>
<h4><bean:message key="settlement.title" />: <logic:present
	name="user" scope="session">
	<bean:write name="user" property="companyAddress" />
</logic:present></h4>
<div class="margins"><html:form action="/updateSettlement.do">
	<table border="0">
		<tr>
			<td><bean:message key="label.descr" />:</td>
			<td><html:textarea property="description" cols="50" rows="10" /></td>
		</tr>
		<tr>
			<td><bean:message key="label.date.start" />:</td>
			<td><html:text property="startDate" /><a href="#"
				onclick="cal13.select(document.forms[0].startDate,'startDate','yyyy-MM-dd'); return false;"
				title="<bean:message key="label.date.select"/>" name="startDate"
				id="startDate">select</a></td>
		</tr>
		<tr>
			<td><bean:message key="settlement.purchase.price" />:</td>
			<td><html:text property="purchasePrice">
			</html:text></td>
		</tr>
		<tr>
			<td><bean:message key="settlement.startup.costs" />:</td>
			<td><html:text property="startupCosts">
			</html:text></td>
		</tr>
		<tr>
			<td><bean:message key="settlement.split.business" />:</td>
			<td><html:text property="nofSquareMetersBusiness"/></td>
		</tr>
		<tr>
			<td><bean:message key="settlement.split.private" />:</td>
			<td><html:text property="nofSquareMetersPrivate"/></td>
		</tr>		
	</table>
	<html:submit>
		<bean:message key="button.update" />
	</html:submit>
</html:form> <html:errors /></div>