<#import "spring.ftl" as spring />
<#assign authz=JspTaglibs["/WEB-INF/tlds/authz.tld"]>

<html>
	<head>
	</head>

	<body>
	
	    <P>
	    <H2>Owner Information</H2>
	
	    <TABLE border="0">
	      <TR><TD>Name</TD><TD><b>${owner.firstName} ${owner.lastName}</b></TD></TR>
	      <TR><TD>Address</TD><TD>${owner.address}</TD></TR>
	      <TR><TD>City</TD><TD>${owner.city}</TD></tr>
	      <TR><TD>Telephone</TD><TD>${owner.telephone}</TD></TR>
	      <TR>
	        <TD>
	            <FORM method=GET action="<@spring.url '/editOwner.htm'/>">
	                <input type="hidden" name="ownerId" value="${owner.id}"/>
	                <input type="submit" value="Edit Owner"/>
	            </FORM>
	        </TD>
	      </TR>
	    </TABLE>
	
	    <P>
	    <BR>
	    <H3>Pets and Visits</H3>

		<#list owner.pets as pet>
			<table border="0">
				<tr>
					<td valign="top">
						<table border="0">
							<tr><td>Name</td><td>${pet.name}</td></tr>
							<tr><td>Birth Date</td><td>${pet.birthDate}</td></tr>
							<tr><td>Type</td><td>${pet.type.name}</td></tr>
							<tr>
								<td>
								    <a href="<@spring.url '/editPet.htm?petId=${pet.id}'/>">Edit Pet</a>
								</td>
							</tr>
						</table>
					</td>
					
					<td valign="top">
        				<table border="1">
            				<th>Visit Date</th>
            				<th>Description</th>
            				<#list pet.visits as visit>
            					<tr>
                					<td>${visit.date}</td>
                					<td>${visit.description}</td>
            					</tr>
            				</#list>
	       				</table>
                    	<@authz.authorize ifAllGranted='ROLE_SUPERVISOR'>
	                    	<a href="<@spring.url '/addVisit.htm?petId=${pet.id}'/>">Add Visit</a>
                    	</@authz.authorize>
        			</td>				
				</tr>
			</table>
		</#list>

		<p>
    	<br>
    	<form method="GET" action="<@spring.url '/addPet.htm'/>" name="formAddPet">
        	<input type="hidden" name="ownerId" value="${owner.id}" />
        	<input type="submit" value="Add New Pet"/>
    	</form>
	
	</body>
</html>
