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
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-bean" prefix="bean"%>

<table border="0">
	<tr>
		<td><bean:message key="label.saldo" />:</td>
		<td><html:text property="saldo" size="20" /></td>
	</tr>
	<tr>
		<td><bean:message key="label.year" />:</td>
		<td><html:select property="jaar">
			<html:option value="2012">2012</html:option>
			<html:option value="2011">2011</html:option>
			<html:option value="2010">2010</html:option>
			<html:option value="2009">2009</html:option>
			<html:option value="2008">2008</html:option>
			<html:option value="2007">2007</html:option>
		</html:select></td>
	</tr>
	<tr>
		<td><bean:message key="label.type" />:</td>
		<td><html:select property="balanceType">
			<html:option value="MACHINERY">Apparaten</html:option>
			<html:option value="CAR">Auto</html:option>
			<html:option value="CURRENT_ASSETS">Liquide middelen</html:option>
			<html:option value="NON_CURRENT_ASSETS">Eigen vermogen</html:option>
			<html:option value="PENSION">Oudedagsreserve</html:option>
			<html:option value="STOCK">Voorraad</html:option>			
		</html:select></td>
	</tr>
</table>