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

		<P>
		<H2>Veterinarians:</H2>
		<TABLE border="1">
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
		  
		</TABLE>
		<P>
		<BR>

		<hr>
		<table style="width:100%"><tr>
			<td><A href="<@spring.url '/welcome.htm'/>">Home</A></td>
			<td style="text-align:right;color:silver">PetClinic :: a Spring Framework demonstration</td>
		</tr></table>
	
	</body>
</html>
