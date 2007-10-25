<#import "spring.ftl" as spring />
<#assign authz=JspTaglibs["/WEB-INF/tlds/authz.tld"]>

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
    <P>
    <H2>Owner Information</H2>

    <TABLE border="0">
      <TR><TD>Name</TD><TD><b>${owner.firstName} ${owner.lastName}</b></TD></TR>
      <TR><TD>Address</TD><TD>${owner.address}</TD></TR>
      <TR><TD>City</TD><TD>${owner.city}</TD></tr>
      <TR><TD>Telephone</TD><TD>${owner.telephone}</TD></TR>
      <TR>
        <TD></TD>
        <TD>
            <FORM method=GET action="<@spring.url '/editOwner.htm'/>">
                <@spring.formHiddenInput 'owner.id'/>
                <input type="submit" value="Edit Owner"/>
            </FORM>
        </TD>
      </TR>
    </TABLE>

    <P>
    <BR>
    <H3>Pets and Visits</H3>

    <c:forEach var="pet" items="${owner.pets}">
    <TABLE border="0">
      <TR><TD valign="top">
        <TABLE border="0">
            <TR><TD>Name</TD><TD><b><c:out value="${pet.name}"/></b></TD></TR>
            <TR><TD>Birth Date</TD><TD><fmt:formatDate value="${pet.birthDate}" pattern="yyyy-MM-dd"/></TD></TR>
            <TR><TD>Type</TD><TD><c:out value="${pet.type.name}"/></TD></tr>
            <TR>
                <TD></TD>
                <TD>
                    <FORM method=GET action="<c:url value="/editPet.htm"/>" name="formEditPet<c:out value="${pet.id}"/>">
                        <INPUT type="hidden" name="petId" value="<c:out value="${pet.id}"/>"/>
                        <INPUT type="submit" value="Edit Pet"/>
                    </FORM>
<authz:authorize ifAllGranted="ROLE_SUPERVISOR">
  <form method=GET action="<c:url value="/addVisit.htm"/>" name="formVisitPet<c:out value="${pet.id}"/>">
    <input type="hidden" name="petId" value="<c:out value="${pet.id}"/>"/>
      <input type="submit" value="Add Visit"/>
  </form>
</authz:authorize>
                </TD>
            </TR>
        </TABLE>
      </TD><TD valign="top">
        <TABLE border="1">
            <TH>Visit Date</TH><TH>Description</TH>
            <c:forEach var="visit" items="${pet.visits}">
            <TR>
                <TD><fmt:formatDate value="${visit.date}" pattern="yyyy-MM-dd"/></TD>
                <TD><c:out value="${visit.description}"/></TD>
            </TR>
            </c:forEach>
        </TABLE>
        </TD></TR>
    </TABLE>
    <P>
    <BR>
    </c:forEach>

    <FORM method=GET action="<c:url value="/addPet.htm"/>" name="formAddPet">
        <INPUT type="hidden" name="ownerId" value="<c:out value="${owner.id}"/>"/>
        <INPUT type="submit" value="Add New Pet"/>
    </FORM>
    <BR>
  




		<p>
		<hr>
		<table style="width:100%"><tr>
			<td><A href="<@spring.url '/welcome.htm'/>">Home</A></td>
			<td style="text-align:right;color:silver">PetClinic :: a Spring Framework demonstration</td>
		</tr></table>
	
	</body>
</html>
