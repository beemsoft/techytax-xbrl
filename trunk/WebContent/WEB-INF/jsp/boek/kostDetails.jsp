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
<script>
	var origBedrag = 0.00;
	var origBtw = 0.00;
	
	function do19Percent() {
	var test = kostForm.bedrag.value;
	if (origBedrag == 0.00) origBedrag = test;
	kostForm.bedrag.value = Math.round(100 * (test / 1.19))/100;
	kostForm.btw.value = Math.round( 100* (test * (19/119)))/100;
	}
	function do6Percent() {
	var test = kostForm.bedrag.value;
	if (origBedrag == 0.00) origBedrag = test;	
	kostForm.bedrag.value = Math.round(100 * (test / 1.06))/100;
	kostForm.btw.value = Math.round(100 * (test * (6/106)))/100;
	}
</script>
<script language="JavaScript">
var cal13 = new CalendarPopup();
</script>
<table border="0">
	<tr>
		<td><bean:message key="label.date"/>:</td>
		<td><html:text property="datum" /><a href="#"
			onclick="cal13.select(document.forms[0].datum,'datum','yyyy-MM-dd'); return false;"
			title="Kies datum" name="datum" id="datum">select</a></td>
	</tr>
	<tr>
		<td><bean:message key="label.amount"/>:</td>
		<td><html:text property="bedrag"></html:text> <a href="#"
			onclick="do19Percent()">19%</a>, <a href="#" onclick="do6Percent()">6%</a>
		</td>
	</tr>
	<tr>
		<td><bean:message key="label.vat"/>:</td>
		<td><html:text property="btw">
		</html:text></td>
	</tr>
	<tr>
		<td><bean:message key="label.descr"/>:</td>
		<td><html:text property="omschrijving" size="100"/></td>
	</tr>
	<tr>
		<td><bean:message key="label.costtype"/>:</td>
		<td>
		<%
	List<Kostensoort> res = (List<Kostensoort>) request.getAttribute("kostenSoortLijst");
		%>
		<html:select property="kostenSoortId">
	<%
			if (res != null) {
			for (int i = 0; i < res.size(); i++) {

				Kostensoort obj = null;
				obj = (Kostensoort) res.get(i);
				if (obj != null) {
	%>
		<html:option value="<%=Long.toString(obj.getKostenSoortId())%>"><bean:message key="<%=obj.getOmschrijving()%>"/></html:option>
	<%
			}

			}
		}
	%>		
		</html:select></td>
	</tr>
</table>
