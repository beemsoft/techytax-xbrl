<!--
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
-->
<%@ taglib uri="sitemesh-decorator" prefix="decorator"%>
<%@ taglib uri="sitemesh-page" prefix="page"%>
<%@ taglib uri="struts-html" prefix="html" %>
<%@ taglib uri="struts-bean" prefix="bean"%>

<html>
<head>
<style type="text/css" media="screen, print">
<!--
@import url(css/default.css);
-->
</style>
<title>TechyTax</title>
</head>

<body>
<table cellspacing="0" border="0" cellpadding="20" id="contentTable">
	<tr>
		<td id="navigationCell" valign="top" width="20%">
		<div class="margins">
		<h4><bean:message key="program.title" /></h4>	
		</div>	
		</td>

		<td id="contentCell" valign="top" width="80%">
		<img src="images/techytax_logo.png" />

<form method="post" action="<%=request.getContextPath()%>/j_security_check" id="form1">

<h4 class="section"><bean:message key="logon.title" /></h4>
<p>
<bean:message key="logon.descr"/>
</p>
<table cellspacing="0" class="loginForm">
	<tr>
		<th id="hdr1"><label for="input1"><bean:message key="logon.username"/></label></th>
		<td headers="hdr1"><input type="text" name="j_username" size="10"
			class="inputTextSmall" /></td>
	</tr>
	<tr>
		<th id="hdr2"><label for="input2"><bean:message key="logon.password"/></label></th>
		<td headers="hdr2"><input type="password" name="j_password" size="10"
			class="inputTextSmall" /></td>
	</tr>
</table>


<div class="margins"><html:submit><bean:message key="button.ok" /></html:submit>
	 <br class="spacer" />
</div>
</form>
		</td>
	</tr>
</table>

</body>
</html>


