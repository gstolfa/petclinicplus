<#import "spring.ftl" as spring />
<#assign authz=JspTaglibs["/WEB-INF/tlds/authz.tld"]>


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
			    
			       <#-- 
			            Attribute hasPermission takes a comma separated list of integers:
						ADMINISTRATION	1
						READ			2
						WRITE			4
						CREATE			8
						DELETE			16
			       -->
			       <@authz.acl domainObject=vet hasPermission='1,4'>
			      		<a href="<@spring.url '/editVet.htm?vetId=${vet.id}'/>">
			       </@authz.acl>
			        	${vet.firstName} ${vet.lastName}
			       <@authz.acl domainObject=vet hasPermission='1,4'>
			      		</a>
			       </@authz.acl>

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
