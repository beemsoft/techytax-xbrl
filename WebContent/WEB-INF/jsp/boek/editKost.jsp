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
<h4><bean:message key="cost.edit.title"/></h4>
<div class="margins">
<html:form action="/updateKost.do">
<html:hidden property="id"/>
<jsp:include page="/WEB-INF/jsp/boek/kostDetails.jsp"/>
<html:submit><bean:message key="button.update"/></html:submit>
</html:form>
<html:errors/>
<%
	String afschrijving = (String)request.getAttribute("investering");
	if ("true".equals(afschrijving)) {
		String id = (String)request.getParameter("id");
%>
</div>
<h4><bean:message key="cost.depreciate"/></h4>
<div class="margins">
<p>
<bean:message key="cost.depreciate.descr"/>
</p>

<html:link action="/afschrijvenKost.do"><html:param name="id"><%=id%></html:param><bean:message key="cost.depreciate"/></html:link>
<%
	}
%>
</div>
