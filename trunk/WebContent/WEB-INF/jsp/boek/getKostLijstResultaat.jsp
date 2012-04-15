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
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-bean" prefix="bean"%>

<script language="JavaScript">
var cal13 = new CalendarPopup();
</script>

<jsp:useBean id="balansForm" scope="session" type="org.techytax.struts.form.BalansForm" />

<%
	List<Cost> res = (List<Cost>) request.getAttribute("kostLijst");
%>

<h4><bean:message key="cost.list"/></h4>
<div class="margins">
<html:form action="/getKostLijstWithForm">
	<table>
		<tr>
			<td>
				<table>
					<tr>
						<td><bean:message key="select.from"/>:</td>
						<td><html:text property="beginDatum" /><a href="#"
						onclick="cal13.select(document.forms[0].beginDatum,'beginDatum','yyyy-MM-dd'); return false;"
						title="Kies begindatum" name="beginDatum" id="beginDatum">select</a></td>
					</tr>
					<tr>
						<td><bean:message key="select.to"/>:</td>
				<td><html:text property="eindDatum" /><a href="#"
				onclick="cal13.select(document.forms[0].eindDatum,'eindDatum','yyyy-MM-dd',(document.forms[0].eindDatum.value=='')?document.forms[0].beginDatum.value:null); return false;"
				title="Kies einddatum" name="eindDatum" id="eindDatum">select</a></td>
					</tr>
				</table>
			</td>
			<td><bean:message key="select.type"/>:</td>
			<td>
				<table>
					<tr>
						<td><html:radio property="balansSoort" value="alles"><bean:message key="overview.all"/></html:radio></td>
						<td><html:radio	property="balansSoort" value="rekeningBalans"><bean:message key="overview.account"/></html:radio></td>
						<td><html:radio property="balansSoort" value="btwBalans"><bean:message key="overview.vat"/></html:radio></td>
						<td><html:radio property="balansSoort" value="kostenBalans"><bean:message key="overview.cost"/></html:radio></td>
						<td><html:radio property="balansSoort" value="reiskostenBalans"><bean:message key="overview.travel"/></html:radio></td>
					</tr>
					<tr>						
						<td><html:radio property="balansSoort" value="investeringen"><bean:message key="overview.invest"/></html:radio></td>
						<td><html:radio property="balansSoort" value="afschrijvingen"><bean:message key="overview.depreciation"/></html:radio></td>
						<td><html:radio property="balansSoort" value="private"><bean:message key="overview.private"/></html:radio></td>
						<td><html:radio property="balansSoort" value="tax"><bean:message key="overview.tax"/></html:radio></td>
						<td><html:radio property="balansSoort" value="audit"><bean:message key="overview.audit"/></html:radio></td>
					</tr>
				</table>
					
			</td>
		</tr>
		<tr>
			<td><bean:message key="search.term"/>:<html:text property="searchTerm"/></td>
			<td/>
			<td/>
		</tr>		
		<tr>
			<td colspan="3"><html:submit><bean:message key="button.show"/></html:submit>
			</td>
		</tr>
	</table>
</html:form>
<html:errors/>


<jsp:include page="report.jsp"></jsp:include>

<%
	if (balansForm != null && balansForm.getBalansSoort() != null && balansForm.getBalansSoort().equals("private")) 
	{
%>
<table cellspacing="0" border="1" class="overviewTable">
	<tr>
		<th><bean:message key="label.id"/></th>
		<th width="10%"><bean:message key="label.date"/></th>
		<th width="10%"><bean:message key="label.amount"/></th>
		<th>In</th>
		<th><bean:message key="label.descr"/></th>
	</tr>

	<%
		if (res != null)
			{
				for (int i = 0; i < res.size(); i++) 
				{
					Cost obj = null;
					obj = (Cost) res.get(i);
					if (obj != null)
					{
						String incoming = "-";
						if (obj.isIncoming()) 
						{
							incoming = "+";
						}
	%>

	<tr valign="top">
		<td><a href="editKost.do?id=<%=obj.getId()%>"><%=obj.getId()%></a></td>
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
<%
	} else 
	{
%>
<table cellspacing="0" border="1" class="overviewTable">
	<tr>
		<th><bean:message key="label.id"/></th>
		<th width="5%"><bean:message key="label.date"/></th>
		<th width="5%"><bean:message key="label.amount"/></th>
		<th width="5%"><bean:message key="label.vat"/></th>
		<th><bean:message key="label.descr"/></th>
		<th width="25%"><bean:message key="label.costtype"/></th>
	</tr>

	<%
		if (res != null) 
			{
		for (int i = 0; i < res.size(); i++) 
		{
			Cost obj = null;
			obj = (Cost) res.get(i);
			if (obj != null) 
			{
	%>

	<tr valign="top">
		<td><a href="editKost.do?id=<%=obj.getId()%>"><%=obj.getId()%></a></td>
		<td><%=obj.getDate()%></td>
		<td align="right"><%=obj.getAmount()%></td>
		<td align="right"><%=obj.getVat()%></td>
		<td><%=obj.getDescription()%></td>
		<td><bean:message key="<%=obj.getKostenSoortOmschrijving()%>"/></td>
	</tr>
	<%
						}

					}
				}
	%>
</table>
<%
			}
%>

</div>
