<#import "spring.ftl" as spring />

<html>
	<head>
	</head>

	<body>
	
		<H2><@spring.message "welcome"/></H2>
		<P>
		<A href="<@spring.url '/findOwners.htm'/>">Find owner</A>
		<P>
		<A href="<@spring.url '/vets.htm'/>">Display all veterinarians</A>
	</body>
	
</html>