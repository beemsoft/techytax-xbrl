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
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ page import="org.techytax.props.PropsFactory"%>
<%@ page import="java.util.Properties"%>

<%
	Properties props = PropsFactory.loadProperties();
	String htmlExtra = props.getProperty("html.extra");
	String htmlExtraLoggedOn = props.getProperty("html.extra.logged.on");
%>	

<h4 class="section"><bean:message key="welcome.title" /> <logic:present
	name="user" scope="session">
	<bean:write name="user" property="fullName" />
</logic:present></h4>
<table>
	<tr>
		<td valign="top">
		<logic:notPresent name="user" scope="session">
		<p><bean:message key="welcome.intro" /></p>
		 <p><a href="help.html" /><bean:message key="welcome.install" /></a></p>
		</logic:notPresent>
		
		<logic:present	name="user" scope="session">
		<logic:notEmpty name="user" property="latestOnlineTime">
			<p><bean:message key="welcome.latest.visit" /> <bean:write name="user" property="latestOnlineTime" format="yyyy-MM-dd HH:mm:ss"/>.</p>
		</logic:notEmpty>
		<logic:equal name="user" property="user" value="false">
		<p><a href="help.html" /><bean:message key="welcome.install" /></a></p>
		</logic:equal>

	<logic:equal name="user" property="user" value="true">
		<logic:equal name="user" property="frozen" value="true">
		<p>Uw account is bevroren. Daarom kunt u uw gegevens alleen inzien, niet wijzigen.</p>
		</logic:equal>
	</logic:equal>
		</logic:present>
		 
		<p><a href="userguide.html" /><bean:message key="welcome.guide" /></a></p>
	<%
		if (htmlExtra != null) {
	%>
	<%=htmlExtra%>
	<%
		}
	%>		
		<p><a href="releasenotes.html" /><bean:message
			key="welcome.release" /></a></p>
		<p><a href="http://www.techytax.org/forum/">Forum</a></p>
		<logic:present name="user" scope="session">
			<logic:equal name="user" property="administrator" value="true">
				<p><html:link action="/getDatabaseInfo.do">
					<bean:message key="db.info" />
				</html:link></p>
			</logic:equal>
		</logic:present></td>
	</tr>
</table>

<logic:notPresent name="user" scope="session">
	<h4 class="section"><bean:message key="logon.title" /></h4>
	<p><bean:message key="logon.descr" /></p>
	<html:form action="/login">
		<table class="formTable">
			<tr>
				<td><bean:message key="logon.username" /></td>
				<td><html:text property="username" /></td>
			</tr>
			<tr>
				<td><bean:message key="logon.password" /></td>
				<td><html:password property="password" /></td>
			</tr>
		</table>
		<div class="margins"><html:submit  onclick="encryptPassword()">
			<bean:message key="logon.title" />
		</html:submit> <br class="spacer" />
		</div>
	</html:form>
</logic:notPresent>

<html:errors />

<script type="text/javascript" src="js/jquery.sha1.js"></script>
<script type="text/javascript">
	function encryptPassword() {
		var passwordField=$('input[type=password]');
		var password=passwordField.val();
		var encryptedPassword=$.sha1(password);
		passwordField.val(encryptedPassword);
	}
</script>





