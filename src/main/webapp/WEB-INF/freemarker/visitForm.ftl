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
		<H2><#if visit.new>New </#if>Visit:</H2>
		
		<@spring.bind 'visit.*'/>
		
		  <FONT color="red">
		     <#list spring.status.errorMessages as error> <b>${error}</b> <br> </#list>
		  </FONT>
		
		<P>
		<B>Pet:</B>
		<TABLE border="true">
		  <TH>Name</TH><TH>Birth Date</TH><TH>Type</TH><TH>Owner</TH>
		  <TR>
		    <TD>${visit.pet.name}</TD>
		    <TD>${visit.pet.birthDate?string("yyyy-MM-dd")}</TD>
		    <TD>${visit.pet.type.name}</TD>
		    <TD>${visit.pet.owner.firstName} ${visit.pet.owner.lastName}</TD>
		  </TR>
		</TABLE>
		
		<P>
		<FORM method="POST">
		
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
		
		</FORM>
		
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
