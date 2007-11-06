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
			    <td>
			      <a href="<@spring.url '/editVet.htm?vetId=${vet.id}'/>">
			        ${vet.firstName} ${vet.lastName}
			      </a>
			    </td>
			    <td>
			      <#list vet.specialtiesReadOnly as specialty>
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
