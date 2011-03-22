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
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>

<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">

<link type="text/css" href="../css/overcast/jquery-ui-1.8.6.custom.css" rel="stylesheet" />	
<script type="text/javascript" src="../js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui-1.8.6.custom.min.js"></script>
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
			<style type="text/css">
			/*demo page css*/
			body{ font: 62.5% "Trebuchet MS", sans-serif; margin: 50px;}
			.demoHeaders { margin-top: 2em; }
			#dialog_link {padding: .4em 1em .4em 20px;text-decoration: none;position: relative;}
			#dialog_link span.ui-icon {margin: 0 5px 0 0;position: absolute;left: .2em;top: 50%;margin-top: -8px;}
			ul#icons {margin: 0; padding: 0;}
			ul#icons li {margin: 2px; position: relative; padding: 4px 0; cursor: pointer; float: left;  list-style: none;}
			ul#icons span.ui-icon {float: left; margin: 0 4px;}
			
			@font-face { font-family: tomhand;	src: url('../ttf/tomhand.ttf');}
			p.custom_font{	font-family: tomhand;	font-size: 30px; margin: 0 0 0 0 }
		</style>	
<title><decorator:title default="TechyTax"/></title>
</head>

<body
	onload="scrollToItem(<%=request.getSession().getAttribute("id")%>)">
<table cellspacing="0" border="0" cellpadding="20" id="contentTable">
	<tr>
		<td class="noPrint" id="navigationCell" valign="top">
		<page:applyDecorator page="/WEB-INF/jsp/menu.jsp" name="panel" /></td>
		<td id="contentCell" valign="top"><table class="headerTable"><tr><td></td><td></td><td></td></tr></table> 
			<decorator:body /></td>
		</tr>
</table>
</body>
</html>
