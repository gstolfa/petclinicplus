<#import "spring.ftl" as spring />


<html>
	<head>
	</head>

	<body>
	
		<p>
		<h2><#if owner.new>New </#if>Owner:</h2>
	
	    <@spring.bind 'owner.*'/>
	    <font color="red">
		     <#list spring.status.errorMessages as error> <b>${error}</b> <br> </#list>
		</font>
		
		<p>
		<form method="POST">
		
		
			<b>First Name:</b>
			<BR>
		    <@spring.formInput 'owner.firstName', 'maxlength="30" size="30"' />
		    <@spring.showErrors '<br>', 'fieldError' />
		    
		    <p>
		    <b>Last Name:</b>
			<BR>
		    <@spring.formInput 'owner.lastName', 'maxlength="30" size="30"' />
		    <@spring.showErrors '<br>', 'fieldError' />
		    
		    <p>
		    <b>Address:</b>
			<BR>
		    <@spring.formInput 'owner.address', 'maxlength="255" size="30"' />
		    <@spring.showErrors '<br>', 'fieldError' />

    		<p>
		    <b>City:</b>
			<BR>
		    <@spring.formInput 'owner.city', 'maxlength="80" size="30"' />
		    <@spring.showErrors '<br>', 'fieldError' />
    
		    <p>
		    <b>Telephone:</b>
			<BR>
		    <@spring.formInput 'owner.telephone', 'maxlength="20" size="20"' />
		    <@spring.showErrors '<br>', 'fieldError' />
    
			<p>
			<#if owner.new><input type = "submit" value="Add Owner"/>
			<#else><input type = "submit" value="Update Owner"/>
			</#if> 
		
		</form>
	</body>
</html>
