<#import "spring.ftl" as spring />
<#assign xhtmlCompliant = true in spring>


<html>
<head>
	<title><@spring.message "search.results.title"/></title>
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
				<td>
					Search Results
				</td>
			</tr>
			<tr>
				<td>
					<hr/>
				</td>
			</tr>
			<tr>
				<td>
					<table border="1">
						<tr>
							<th>First Name</th>
							<th>Last Name</th>
							<th>User Id</th>
							<th>Phone</th>
						</tr>
						<#list results as person>
							<tr>
								<td>${person.firstName}</td>
								<td>${person.lastName}</td>
								<td>
									<a href="phonebook.htm?_flowExecutionKey=${flowExecutionKey}&_eventId=select&id=${person.id}">
										${person.userId}
									</a>
								</td>
								<td>${person.phone}</td>
							</tr>
						</#list>
					</table>
				</td>
			</tr>
			<tr>
				<td class="buttonBar">
					<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">
					<input type="submit" class="button" name="_eventId_newSearch" value="New Search">
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