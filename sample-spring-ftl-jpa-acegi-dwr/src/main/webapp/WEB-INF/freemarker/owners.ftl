<#import "spring.ftl" as spring />
<html>
	<head>	
	</head>
	<body>
		<p>
		<h2>Owners:</h2>
  		<table border="1">
  			<th>Name</th>
			<th>Address</th>
			<th>City</th>
			<th>Telephone</th>
			<th>Pets</th>

			<#list selections as owner>
				<tr>
					<td>
						<form method="POST" action="<@spring.url '/owner.htm'/>">
							<input type="hidden" name="ownerId" value="${owner.id}"/>
							<input type="submit" value="${owner.firstName} ${owner.lastName}"/>
        				</form>
					</td>
					<td>${owner.address}&nbsp;</td>
					<td>${owner.city}&nbsp;</td>
					<td>${owner.telephone}&nbsp;</td>
					<td>
						<#list owner.pets as pet>
							${pet.name} <br>
						</#list>
						&nbsp;
					</td>
				</tr>
			</#list>
		</table>
		<p>
	</body>
</html>
