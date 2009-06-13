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
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-bean" prefix="bean"%>

<table border="0">
	<tr>
		<td><bean:message key="label.descr"/>:</td>
		<td><html:text property="omschrijving" size="80" /></td>
	</tr>
	<tr>
		<td><bean:message key="label.incoming.payment"/>:</td>
		<td><html:checkbox property="bijschrijving"></html:checkbox></td>
	</tr>
	<tr>
		<td><bean:message key="label.vat.appl"/>:</td>
		<td><html:checkbox property="btwVerrekenbaar"></html:checkbox></td>
	</tr>
	<tr>
		<td><bean:message key="label.current.account"/>:</td>
		<td><html:checkbox property="balansMeetellen"></html:checkbox></td>
	</tr>
	<tr>
		<td><bean:message key="label.tax.deductible"/>:</td>
		<td><html:checkbox property="aftrekbaar"></html:checkbox></td>
	</tr>
	<tr>
		<td><bean:message key="label.investment"/>:</td>
		<td><html:checkbox property="investering"></html:checkbox></td>
	</tr>		
</table>