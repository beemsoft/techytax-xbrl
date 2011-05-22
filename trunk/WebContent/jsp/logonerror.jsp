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
<%@ page language="java"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-html" prefix="html"%>

<head>
<style type="text/css" media="screen, print">
<!--
@import url(css/default.css);
-->
</style>
<title>TechyTax</title>
</head>

<body>
<div class="margins">
<table cellspacing="0" border="0" cellpadding="20" id="contentTable">
	<tr>
		<td id="navigationCell" valign="top">
		<div class="margins">
		<ul>
			<li><a
				href="<%=request.getContextPath()%>/index.jsp?logoff=true"><bean:message
				key="logon.retry" /></a></li>
		</ul>
		</div>
		</td>
		<td id="contentCell" valign="top"><img
			src="images/techytax_logo.png"/>
		<h4 class="section"><bean:message key="logon.title"/></h4>
		<p><bean:message key="logon.failed"/></p>
		</td>
	</tr>
</table>
</div>

</body>

