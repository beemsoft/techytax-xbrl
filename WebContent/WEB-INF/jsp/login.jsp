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





