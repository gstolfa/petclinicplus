<%@ page language="java" errorPage="/WEB-INF/jsp/error.jsp" pageEncoding="ISO-8859-1" contentType="text/html;charset=ISO-8859-1" %>
<%@ page import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
    <head>
        <title>Access Denied</title>
        <link rel="stylesheet" type="text/css" href="${ctx}/styles/default.css" />
    </head>
    <body>
        <center>
            <table style="width:100%;height:90%;">
                <tr>
                    <td valign="middle" align="center">
                        <table class="controlGrid">
                            <tbody >
                                <tr>
                                    <td>
                                        <h1>Exception</h1>
                                    </td>
                                    <td>&nbsp;</td>
 

                                </tr>

                                <tr><td >
                                    <p>You are not allowed to view the requested resource. Sorry.</p>
                                    </td>
                                </tr>

    

                                <tr>
                                    <td class="controlGrid_Col" >
                                        Request-URI:
                                    </td>
                                    <td class="controlGrid_Col" >
                                        <%=request.getAttribute("javax.servlet.error.request_uri")%>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="controlGrid_Col" >
                                        Servletname:
                                    </td>
                                    <td class="controlGrid_Col" >
                                        <%=request.getAttribute("javax.servlet.error.servlet_name")%>
                                    </td>
                                </tr>

                          
                            </tbody>
                        </table>
                    </td>
                </tr
            </table>
            </form>
        </center>
    </body>
</html>

















<h:panelGrid columns="1" columnClasses="plain">
    <h:outputText value="#{globalErrorForm.summary}" styleClass="errorMsg" />
    <f:verbatim escape="false">
        <br>
    </f:verbatim>

    <h:outputText value="Details" />
    <h:panelGrid columns="2" styleClass="controlGrid" columnClasses="standardTable_Col" width="100%">

        <h:outputText value="Fehlercode:" />
        <h:outputText value="#{globalErrorForm.errorCode}" />

        <h:outputText value="Fehlertyp:" />
        <h:outputText value="#{globalErrorForm.exceptionType}" />

        <h:outputText value="URI:" />
        <h:outputText value="#{globalErrorForm.requestURI}" />

        <h:outputText value="Servlet:" />
        <h:outputText value="#{globalErrorForm.servlet}" />

    </h:panelGrid>
    <f:verbatim escape="false">
        <br>
    </f:verbatim>
    <h:outputText value="Stacktrace" />
    <h:inputTextarea readonly="true" value="#{globalErrorForm.stacktrace}" rows="10" cols="55" />

</h:panelGrid>









