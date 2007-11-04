<#import "spring.ftl" as spring />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title><@spring.message "login"/></title>
        <link rel="stylesheet" type="text/css" href="styles/default.css" />
        <script language="JavaScript" type="text/javascript">
        function disableControls()
        {
            document.forms['login_form'].elements['login'].disabled = true;
        }
        </script>
    </head>
    <body>
 
            <form id="login_form" name="Login" method="POST" action="j_security_check" onSubmit="javascript:disableControls()">
               <h2><@spring.message "login"/></h2>
               <font color="red">
					<b><#if errmsg?exists>${errmsg}</#if></b>
				</font>
				<p/>
				
               	
               	<label for="login_form:j_username" accesskey="u"><@spring.message "login.user"/></label><br/>
               	<input id="j_username" name="j_username" type="text" />
               	<p/>
               	<label for="login_form:j_password" accesskey="u"><@spring.message "login.password"/></label><br/>
               	<input id="j_password" name="j_password" type="password" />
               	<p/> 
               	<input id="login" type="submit" value="OK" class="button" />
            </form>

        <script language="JavaScript" type="text/javascript">
        <!--
        document.forms["login_form"].elements["j_username"].focus()
        // -->
        </script>

    </body>
</html>
		