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

<%@ page import="org.techytax.domain.Kostensoort"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<script>
	var origAmount = 0.00;
	var origVat = 0.00;
	
	function do19Percent() {
	var test = costForm.amount.value;
	if (origAmount == 0.00) origAmount = test;
	costForm.amount.value = Math.round(100 * (test / 1.19))/100;
	costForm.vat.value = Math.round( 100* (test * (19/119)))/100;
	}
	function do6Percent() {
	var test = costForm.amount.value;
	if (origAmount == 0.00) origAmount = test;	
	costForm.amount.value = Math.round(100 * (test / 1.06))/100;
	costForm.vat.value = Math.round(100 * (test * (6/106)))/100;
	}
</script>
<script language="JavaScript">
var cal13 = new CalendarPopup();
</script>
		<%
	List<Kostensoort> res = (List<Kostensoort>) request.getAttribute("kostenSoortLijst");
		%>
<div id="original" style="display:none" class="margins">
<table>
	<tr>
		<td><bean:message key="label.amount"/>:</td>
		<td><bean:write name="costForm" property="amount"/></td>
	</tr>
	<tr>
		<td><bean:message key="label.vat"/>:</td>
		<td><bean:write name="costForm" property="vat"/></td>
	</tr>
</table>
</div>
<div id="costDetail" class="margins">
<table border="0">
	<tr>
		<td><bean:message key="label.date"/>:</td>
		<td><html:text property="date" /><a href="#"
			onclick="cal13.select(document.forms[1].date,'date','yyyy-MM-dd'); return false;"
			title="Kies datum" name="date" id="date">select</a></td>
	</tr>
	<tr>
		<td><bean:message key="label.amount"/>:</td>
		<td><html:text property="amount"></html:text> <a href="#"
			onclick="do19Percent()">19%</a>, <a href="#" onclick="do6Percent()">6%</a>
		</td>
	</tr>
	<tr>
		<td><bean:message key="label.vat"/>:</td>
		<td><html:text property="vat">
		</html:text></td>
	</tr>
	<tr>
		<td><bean:message key="label.descr"/>:</td>
		<td><html:text styleId="description" property="description" size="100"/></td>
	</tr>
	<tr>
		<td><bean:message key="label.costtype"/>:</td>
		<td>
		<html:select property="costTypeId">
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
</div>
<div id="split" style="display:none" class="margins">
<h4><bean:message key="cost.edit.split"/></h4>
<table>
	<tr>
		<td><bean:message key="label.amount"/>:</td>
		<td><html:text property="splitAmount"></html:text> 
		</td>
	</tr>
	<tr>
		<td><bean:message key="label.vat"/>:</td>
		<td><html:text property="splitVat">
		</html:text></td>
	</tr>
	<tr>
		<td><bean:message key="label.descr"/>:</td>
		<td><html:text styleId="splitDescription" property="splitDescription" size="100"/></td>
	</tr>
	<tr>
		<td><bean:message key="label.costtype"/>:</td>
		<td>
		<html:select property="splitCostTypeId" value="3">
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
</div>

