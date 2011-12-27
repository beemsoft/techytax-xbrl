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

<%
	String investment = (String)request.getAttribute("investment");
	String depreciation = (String)request.getAttribute("depreciation");
%>

<h4><bean:message key="cost.edit.title"/></h4>
<div class="margins">
<html:form action="/updateKost.do">
<html:hidden property="id"/>
<jsp:include page="/WEB-INF/jsp/boek/kostDetails.jsp"/>
<%
	if (!"true".equals(depreciation)) {
%>
<html:submit><bean:message key="button.update"/></html:submit>
<%
	}
%>
</html:form>
<html:errors/>
<%
	if ("true".equals(investment)) {
		String id = (String)request.getParameter("id");
%>
</div>
<h4><bean:message key="cost.depreciate"/></h4>
<div class="margins">
<p>
<bean:message key="cost.depreciate.descr"/>
</p>

<html:form action="/afschrijvenKost.do">
<html:hidden property="id" value="<%=id%>"/>
<table>
<tr>
<td><bean:message key="cost.depreciate.years"/></td>
<td>
<html:select property="nofYears">
<html:option value="2">2</html:option>
<html:option value="3">3</html:option>
<html:option value="4">4</html:option>
<html:option value="5">5</html:option>
</html:select>
</td>
</tr>
<tr>
<td colspan="2">
<html:checkbox property="car"><bean:message key="cost.depreciate.car"/></html:checkbox>
</td>
</tr>
</table>
<html:submit><bean:message key="cost.depreciate"/></html:submit>
</html:form>
</div>
<%
	} else if ("true".equals(depreciation)) {
%>
<div class="margins">
<p>
<bean:message key="cost.depreciated.descr"/>
</p>
</div>
<%
	}
%>

