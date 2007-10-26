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
		
		<p>
		<br>
		<a href="<@spring.url '/addOwner.htm'/>">Add Owner</a>
		<p>
		<br>
		
		
		<hr>
		<table style="width:100%"><tr>
			<td><a href="<@spring.url '/welcome.htm'/>" >Home</a></td>
			<td style="text-align:right;color:silver">PetClinic :: a Spring Framework demonstration</td>
	</tr></table>
	
	</body>
</html>
