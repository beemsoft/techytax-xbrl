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
<%@ page import="org.techytax.domain.Kostmatch"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<h4><bean:message key="costtype.edit" /></h4>
<div class="margins">
<html:form action="/updateKostensoort.do">
	<html:hidden property="kostenSoortId" />
	<jsp:include page="/WEB-INF/jsp/boek/kostensoortDetails.jsp" />
	<html:submit>
		<bean:message key="button.update" />
	</html:submit>
</html:form>
</div>
<logic:equal value="true" property="balansMeetellen"
	name="kostensoortForm">
	<%
		String id = (String) request.getParameter("kostenSoortId");
	%>
<h4><bean:message key="costtype.match" /></h4>
<div class="margins">
	<html:link action="/newKostmatch.do">
		<html:param name="kostenSoortId"><%=id%></html:param>
		<bean:message key="costtype.match.new" />
	</html:link>
	<table cellspacing="0" border="1" class="overviewTable">
		<tr>
			<th><bean:message key="label.match"/></th>
			<logic:equal value="true" property="btwVerrekenbaar"
				name="kostensoortForm">
				<th><bean:message key="label.vat"/></th>
			</logic:equal>
		</tr>

		<%
			List<Kostmatch> res = (List<Kostmatch>)request.getAttribute("kostmatchLijst");
				if (res != null) {
					for (int i = 0; i < res.size(); i++) {

						Kostmatch obj = null;
						obj = (Kostmatch) res.get(i);
						if (obj != null) {
		%>

		<tr valign="top">
			<td><a href="editKostmatch.do?id=<%=obj.getId()%>"><%=obj.getMatchText()%></a></td>
			<logic:equal value="true" property="btwVerrekenbaar"
				name="kostensoortForm">
				<td><a href="editBtwmatch.do?id=<%=obj.getId()%>"><%=obj.getBtwType()%></a></td>
			</logic:equal>
		</tr>
		<%
			}

					}
				}
		%>
	</table>
</div>	
</logic:equal>



