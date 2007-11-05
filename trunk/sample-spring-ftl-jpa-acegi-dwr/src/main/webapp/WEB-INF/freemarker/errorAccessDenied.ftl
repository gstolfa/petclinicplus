<#import "spring.ftl" as spring />

<p><@spring.message 'error.accessdenied'/></p>

<p/><#if auth?exists>

<p>Name: ${auth.name}</p>

<ul>
	<#list auth.authorities as authority>
		<li>${authority}</li>
	</#list>
</ul>


<#else>No authentication object available.</#if>



