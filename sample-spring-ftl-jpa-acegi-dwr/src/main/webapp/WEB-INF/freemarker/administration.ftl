<#import "spring.ftl" as spring />
<#assign authz=JspTaglibs["/WEB-INF/tlds/authz.tld"]>

<html>
	<head>
	</head>

	<body>
	
		<H2>Administration</H2>
		<P>
		<h3>Authorization Data</h3>
		<#if auth?exists>
			<p>Name: ${auth.name}</p>
			<p>Roles:</p>
			<ul>
				<#list auth.authorities as authority>
				<li>${authority.name}</li>
				</#list>
			</ul>
		<#else>No authentication object available.</#if>
		
		<h3>Direct Web Remoting (DWR)</h3>
		<a href="<@spring.url '/dwr'/>">Test</a>
		<p>Set the DWR controller's debug attribute to <i>false</i> before releasing your application!
		</p>
		
	</body>
	
</html>