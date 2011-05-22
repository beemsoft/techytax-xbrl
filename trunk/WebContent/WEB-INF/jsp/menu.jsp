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
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-bean" prefix="bean"%>

<table cellspacing="0" cellpadding="20" id="contentTable">
	<tr>
		<td id="navigationCell" valign="top">

		<div class="margins">
		<h4><bean:message key="program.title" /></h4>
		<p>
		Version: @version@
		</p>	
		</td>		
		<div id="links">
		<html:link href="index.jsp">
			<bean:message key="menu.welcome" />
			<span><bean:message key="menu.welcome.descr" /></span>
		</html:link> 
		<html:link action="newKost.do">
			<bean:message key="menu.new.cost" />
		</html:link>
		<html:link action="getKostensoortLijst.do">
			<bean:message key="menu.cost.types" />
		</html:link> 
		<html:link action="laadKostLijst.do">
			<bean:message key="menu.load.data" />
		</html:link> 
		<html:link action="getKostLijst.do">
			<bean:message key="menu.cost.list" />
			<span> <bean:message key="menu.cost.list.descr" /></span>
		</html:link> 
		<html:link action="getKostLijstWithForm">
			<html:param name="action" value="Fiscaal overzicht"></html:param>
			<bean:message key="menu.fiscal.overview" />
			<span> <bean:message key="menu.fiscal.overview.descr" /></span>
		</html:link> 
		<html:link action="getAccounts.do">
			<bean:message key="menu.accounts" />
			<span> <bean:message key="menu.accounts.descr" /></span>
		</html:link> <%
 	if (request.getUserPrincipal() != null) {
 %> <a href="<%=request.getContextPath()%>/index.jsp?logoff=true"><bean:message
			key="menu.log.off" /></a> <%
 	}
 %>
		</div>
		</div>
		</td>
	</tr>
</table>
