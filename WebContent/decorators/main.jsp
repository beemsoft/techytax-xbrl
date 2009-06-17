<!--
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
-->
<%@ taglib uri="sitemesh-decorator" prefix="decorator"%>
<%@ taglib uri="sitemesh-page" prefix="page"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-bean" prefix="bean"%>

<html>
<head>

<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">

<SCRIPT LANGUAGE="JavaScript" SRC="script/CalendarPopup.js"></SCRIPT>
<script language="JavaScript">

function scrollToItem(id) {
	if (id != null) {
		location.href="#"+id;
	}
}

</script>
<style type="text/css" media="screen, print">
<!--
@import url(css/default.css);
-->
</style>
<title><decorator:title default="TechyTax"/></title>
</head>

<body
	onload="scrollToItem(<%=request.getSession().getAttribute("id")%>)">
<table cellspacing="0" border="0" cellpadding="20" id="contentTable">
	<tr>
		<td class="noPrint" id="navigationCell" valign="top">
		<page:applyDecorator page="/WEB-INF/jsp/menu.jsp" name="panel" /></td>
		<td id="contentCell" valign="top"><img
			src="images/techytax_logo.png" /><a href="http://sourceforge.net/projects/techytax"><img src="http://sflogo.sourceforge.net/sflogo.php?group_id=260219&amp;type=14" width="150" height="40" alt="Get TechyTax at SourceForge.net. Fast, secure and Free Open Source software downloads" /></a> 
			<decorator:body /></td>
		</tr>
</table>
</body>
</html>
