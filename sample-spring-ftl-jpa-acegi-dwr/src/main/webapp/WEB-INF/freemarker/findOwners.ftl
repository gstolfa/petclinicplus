<#import "spring.ftl" as spring />

<html>
	<head>
	</head>

	<body>

	

		<h2>Find Owners (with autocomplete):</h2>
        <@spring.bind 'owner.*' />
        <font color="red">
		     <#list spring.status.errorMessages as error> <b>${error}</b> <br> </#list>
		</font>
		
		
		<p>
		<form method="POST" name="DwrForm" AUTOCOMPLETE="off" ID="DwrForm">
		  

		    <p>
		    <b>Last Name:</b>
			<BR>
		    <@spring.formInput 'owner.userData.lastName', 'maxlength="30" size="30"' />
		    <@spring.showErrors '<br>', 'fieldError' />
		    <input type = "submit" value="Find Owners"  />
			
			
			<#--
				The below snippet triggers the auto suggest.
				Note that the passed element name corresponds to the bind path - without owner portion.
				The functions findOwnerNames and stringValueSelector are located in custom.js.
			-->
			<div id="ownerNameList" class="auto_complete"></div>
			<script type="text/javascript">
			// <![CDATA[
   				new Autocompleter.DWR('userData.lastName', 'ownerNameList', 
   					findOwnerNames,{ valueSelector: stringValueSelector, partialChars: 0 });
            // ]]>
			</script>


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
