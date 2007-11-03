<#import "spring.ftl" as spring />

<html>
	<head>
	</head>

	<body>

		<H2><#if visit.new>New </#if>Visit:</H2>
		
		<@spring.bind 'visit.*'/>
		
		  <FONT color="red">
		     <#list spring.status.errorMessages as error> <b>${error}</b> <br> </#list>
		  </FONT>
		
		<P>
		<B>Pet:</B>
		<table border="true">
		  <TH>Name</TH><TH>Birth Date</TH><TH>Type</TH><TH>Owner</TH>
		  <TR>
		    <TD>${visit.pet.name}</TD>
		    <TD>${visit.pet.birthDate?string("yyyy-MM-dd")}</TD>
		    <TD>${visit.pet.type.name}</TD>
		    <TD>${visit.pet.owner.firstName} ${visit.pet.owner.lastName}</TD>
		  </TR>
		</table>
		
		<P>
		<form method="POST">
		
		    <B>Date:</B>
		    <BR>
		    <@spring.formInput 'visit.date', 'maxlength="10" size="10" name="visit.description"' />
		    <@spring.showErrors '<br>', 'fieldError' />
		
		    <P>
		    <B>Description:</B>
		    <BR>
		    <@spring.formTextarea 'visit.description', 'rows="10" cols="25" name="visit.description"' />
		    <@spring.showErrors '<br>', 'fieldError' />
		    
		
		    <P>
		    <@spring.formHiddenInput  'visit.pet.id' />
		    <INPUT type = "submit" value="Add Visit"  />
		
		</form>
		
		<P>
		<BR>
		<B>Previous Visits:</B>
		<TABLE border="true">
		  <TH>Date</TH><TH>Description</TH>
		   
		  <#list visit.pet.visits as visit>
		    <#if !visit.new>
		        <TR>
		           <TD>${visit.date?string('yyyy-MM-dd')}</TD>
		           <TD>${visit.description}</TD>
		        </TR>
		    </#if>
		  </#list>
		
		</table>
	</body>
</html>
