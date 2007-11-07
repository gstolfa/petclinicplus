<#import "spring.ftl" as spring />
<#assign authz=JspTaglibs["/WEB-INF/tlds/authz.tld"]>

<html>
	<head>
	</head>

	<body>
	
		<H2><@spring.message "welcome"/></H2>
		
		<@authz.authorize ifAllGranted='ROLE_USER'>
			<P>
			<A href="<@spring.url '/findOwners.htm'/>">Find owner</A>
        </@authz.authorize>
		
		<@authz.authorize ifAllGranted='ROLE_USER'>
			<P>
			<A href="<@spring.url '/vets.htm'/>">Display all veterinarians</A>
        </@authz.authorize>
		
		<@authz.authorize ifAllGranted='ROLE_SUPERVISOR'>
		     <P>
	         <a href="<@spring.url '/administration.htm'/>">Administration</a>
        </@authz.authorize>
	</body>
	
</html>