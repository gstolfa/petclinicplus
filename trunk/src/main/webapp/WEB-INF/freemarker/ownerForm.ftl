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




		<p>
		<hr>
		<table style="width:100%"><tr>
			<td><A href="<@spring.url '/welcome.htm'/>">Home</A></td>
			<td style="text-align:right;color:silver">PetClinic :: a Spring Framework demonstration</td>
		</tr></table>
	
	</body>
</html>
