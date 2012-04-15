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
<jsp:useBean id="kostmatchForm" scope="request"
	type="org.techytax.struts.form.KostmatchForm" />
<h4><bean:message key="costtype.match.vat.edit" /></h4>
<html:form action="/updateBtwmatch.do">
	<html:hidden property="id" />
	<html:hidden property="kostenSoortId" />
	<table>
		<tr>
			<td><bean:message key="label.match" />:</td>
			<td><%=kostmatchForm.getMatchText()%></td>
		</tr>
		<tr>
			<td><bean:message key="label.vat" />:</td>
			<td><html:radio property="btwType" value="0">
				<bean:message key="vat.none" />
			</html:radio> <html:radio property="btwType" value="1">
				<bean:message key="vat.low" />
			</html:radio> <html:radio property="btwType" value="2">
				<bean:message key="vat.high" />
			</html:radio></td>
		</tr>
	</table>
	<html:submit>
		<bean:message key="button.update" />
	</html:submit>
</html:form>

