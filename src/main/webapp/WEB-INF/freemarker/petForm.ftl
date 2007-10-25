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
		<div id="top">&nbsp;</div><br clear="all"><p>&nbsp;</p>

        <p>
        <h2><#if pet.new>New</#if></h2>
        <@spring.bind 'pet.*' />
        <font color="red">
		     <#list spring.status.errorMessages as error> <b>${error}</b> <br> </#list>
		</font>
		
		<p>
		<b>Owner:</b> ${pet.owner.firstName} ${pet.owner.lastName}
		<p>
		<form method="POST">
			
			<b>Name:</b>
			<BR>
		    <@spring.formInput 'pet.name', 'maxlength="30" size="30"' />
		    <@spring.showErrors '<br>', 'fieldError' />
		
		    <p>
			<b>Birth Date:</b>
			<BR>
		    <@spring.formInput 'pet.birthDate', 'maxlength="10" size="10"' /> (yyyy-mm-dd)
		    <@spring.showErrors '<br>', 'fieldError' />
		
		    <p>
		    <b>Type:</b>
			<BR>
		    <@bind 'pet.type'/>
	        <select name="${spring.status.expression}">
		    	<#list types as type>
		    		<#if type.id == pet.type.id>
						<option selected="${pet.type.id}" value="${type.id}">${type.name}"</OPTION>
		    		</#if>
		    		<#if type.id != pet.type.id>
						<option value="${type.id}">${type.name}"</OPTION>
		    		</#if>
		    	</#list>	
		    </select> (yyyy-mm-dd)
		    <@spring.showErrors '<br>', 'fieldError' />
		
		<p>
		<#if pet.new><input type="submit" value="Add Pet" /></#if>
		<#if !pet.new><input type="submit" value="Update Pet" /></#if>
		
		</form>


		<P>
		<BR>


		<hr>
		<table style="width:100%"><tr>
			<td><A href="<@spring.url '/welcome.htm'/>">Home</A></td>
			<td style="text-align:right;color:silver">PetClinic :: a Spring Framework demonstration</td>
		</tr></table>
	
	</body>
</html>

