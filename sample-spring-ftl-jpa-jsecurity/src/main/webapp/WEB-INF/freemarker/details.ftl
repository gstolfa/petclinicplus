<#import "spring.ftl" as spring />
<#assign xhtmlCompliant = true in spring>


<html>
<head>
	<title><@spring.message "details.title"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
	<link rel="stylesheet" href="style.css" type="text/css"/>
</head>

<body>

<div id="logo">
	<img src="images/spring-logo.jpg" alt="Logo"/> 
</div>

<div id="content">
	<div id="insert">
		<img src="images/webflow-logo.jpg"/>
	</div>
	<form action="phonebook.htm" method="post">
	<table>
		<tr>
			<td>Person Details</td>
		</tr>
		<tr>
			<td colspan="2"><hr></td>
		</tr>
		<tr>
			<td><b>First Name</b></td>
			<td>${person.firstName}</td>
		</tr>
		<tr>
			<td><b>Last Name</b></td>
			<td>${person.lastName}</td>
		</tr>
		<tr>
			<td><b>User Id</B></td>
			<td>${person.userId}</td>
		</tr>
		<tr>
			<td><b>Phone</b></td>
			<td>${person.phone}</td>
		</tr>
		<tr>
			<td colspan="2">
				<br/>
				<b>Colleagues:</b>
				<br/>
				<#list person.colleagues as colleague>
					<a href="phonebook.htm?_flowExecutionKey=${flowExecutionKey}&_eventId=select&id=${colleague.id}">
						${colleague.firstName} ${colleague.lastName}<br>
					</a>
				</#list>				
			</td>
		</tr>
		<tr>
			<td colspan="2" class="buttonBar">
				<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
				<input type="submit" class="button" name="_eventId_back" value="Back"/>
			</td>
		</tr>
	</table>
	</form>
</div>

<div id="copyright">
	<p>&copy; Copyright 2004-2007, <a href="http://www.springframework.org">www.springframework.org</a>, under the terms of the Apache 2.0 software license.</p>
</div>

</body>
</html>