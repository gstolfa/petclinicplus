<#import "spring.ftl" as spring />


<html>
	<head>
	</head>

	<body>
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
			<@spring.formSingleSelect 'pet.type', types, ''/> 
		    <@spring.showErrors '<br>', 'fieldError' />
		
		<p>
		<#if pet.new><input type="submit" value="Add Pet" /></#if>
		<#if !pet.new><input type="submit" value="Update Pet" /></#if>
		
		</form>
	</body>
</html>

