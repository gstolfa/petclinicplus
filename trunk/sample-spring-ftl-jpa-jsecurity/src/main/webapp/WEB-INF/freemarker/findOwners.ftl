<#import "spring.ftl" as spring />

<html>
	<head>
	</head>

	<body>
	

		<h2>Find Owners:</h2>
        <@spring.bind 'owner.*' />
        <font color="red">
		     <#list spring.status.errorMessages as error> <b>${error}</b> <br> </#list>
		</font>
		
		
		<p>
		<form method="POST">
		  
		    <p>
		    <b>Last Name:</b>
			<BR>
		    <@spring.formInput 'owner.lastName', 'maxlength="30" size="30"' />
		    <@spring.showErrors '<br>', 'fieldError' />
		    
		    <input type = "submit" value="Find Owners"  />
		</form>
		
		
		<p>Recently requested owners (as logged via AspectJ):</p>
		<ul>
		<#list requestedNames as o>
			<li>${o}</li>
		</#list>
		</ul>
		
		
		<p>
		<br>
		<a href="<@spring.url '/addOwner.htm'/>">Add Owner</a>
	
	</body>
</html>
