<#import "spring.ftl" as spring />


<html>
	<head>
	</head>

	<body>
		<H2>Veterinarians:</H2>
		<table border="1">
		  <TH>Name</TH><TH>Specialties</TH>
		  
		  <#list vets as vet>
			  <tr>
			    <td>${vet.firstName} ${vet.lastName}</td>
			    <td>
			      <#list vet.specialties as specialty>
			        ${specialty.name}
			      </#list> 
			      <#if vet.nrOfSpecialties == 0>none</#if>
			    </td>
			  </tr>
		  </#list>
		  
		</table>
		<p><small>(<a href="?printable=true">printable version</a>)</small></p>
		
	</body>
</html>
