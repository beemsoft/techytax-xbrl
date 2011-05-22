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
<%@ page isErrorPage="true" %>
<%@ page language="java" %>
<%@ taglib uri="struts-html" prefix="html" %>
<%@ taglib uri="struts-bean" prefix="bean" %>
<%@ taglib uri="struts-logic" prefix="logic" %>
<%@ taglib uri="i18n" prefix="i18n" %>
<html:html>
<head>
<style type="text/css" media="screen, print">
<!--
@import url(css/default.css);
-->
</style>
<title>Foutmelding</title>
</head>

<body>
 
<!--	Hoofdscherm	-->
<div class="main">

			<p class="invoerblokken">
				<table class="invoerblokken">
					<tr>
						<td class="foutmeldingen">
							<p class="foutmeldingen">
							<i18n:formatDateTime value="<%= new java.util.Date() %>" dateStyle="long" timeStyle="medium"/> - 
							Er is een fout opgetreden in de applicatie.
							<br><html:errors/>
							</p>
						</td>
					</tr>
				</table>


</div>

</body>
</html:html>
