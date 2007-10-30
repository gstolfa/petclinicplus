<#import "spring.ftl" as spring />


<html>
	<head>
		<title>PetClinic :: a Spring Framework demonstration</title>
		<style type="text/css">
		<!--
			td { padding:3px; }
			div#top {position:absolute; top: 0px; left: 0px; background-color: #E4EFF3;background-image: url(logo.gif);background-position: 100px 1px;background-repeat: no-repeat; height: 50px; width:100%; padding:0px; border: none;margin: 0;}
		// -->
		</style>	
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
					<td>${owner.address}</td>
					<td>${owner.city}</td>
					<td>${owner.telephone}</td>
					<td>
						<#list owner.pets as pet>
							${pet.name} <br>
						</#list>
					</td>
				</tr>
			</#list>
		</table>
		<p>
		<br>

		<hr>
		<table style="width:100%"><tr>
			<td><A href="<@spring.url '/welcome.htm'/>">Home</A></td>
			<td style="text-align:right;color:silver">PetClinic :: a Spring Framework demonstration</td>
		</tr></table>
	
	</body>
</html>
