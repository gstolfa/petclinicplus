<#import "spring.ftl" as spring />
<#import "customMacros.ftl" as custom />
<#assign authz=JspTaglibs["/WEB-INF/tlds/authz.tld"]>


<html>
	<head>
	</head>

	<body>
        <h2><#if vet.new>New </#if>veterinarian</h2>
        <@spring.bind 'vet.*' />
        <font color="red">
		     <#list spring.status.errorMessages as error> <b>${error}</b> <br> </#list>
		</font>
		
		<form method="POST">
			
			<b>Firstname:</b>
			<BR>
		    <@spring.formInput 'vet.firstName', 'maxlength="30" size="30"' />
		    <@spring.showErrors '<br>', 'fieldError' />
		
		    <p>
			<b>Lastname:</b>
			<BR>
		    <@spring.formInput 'vet.lastName', 'maxlength="30" size="30"' />
		    <@spring.showErrors '<br>', 'fieldError' />
		
				    <p>
			<b>Login name:</b>
			<BR>
		    <@spring.formInput 'vet.loginName', 'maxlength="30" size="30"' />
		    <@spring.showErrors '<br>', 'fieldError' />
		
		
		    <p>
		    <b>Password:</b>
			<BR>
			<@spring.formInput 'vet.password', 'maxlength="30" size="30"'/> 
		    <@spring.showErrors '<br>', 'fieldError' />
		
			<p>
		    <b>Specialties:</b>
			<BR>
			<@custom.formNamedEntityCheckboxes 'vet.specialtiesInternal', specialties, ''/> 
		    <@spring.showErrors '<br>', 'fieldError' />
		
		<p>
		<#if vet.new><input type="submit" value="Add Vet" /></#if>
		<#if !vet.new><input type="submit" value="Update Vet" /></#if>
		
		</form>
	</body>
</html>

