<#import "spring.ftl" as spring />


<html>
	<head>
	
	    <#--
	    	JSCalendar setup. See http://www.dynarch.com/demos/jscalendar/doc/reference.pdf
	    	for further details.
	    --> 	
		<link rel="stylesheet" type="text/css" media="all" href="<@spring.url '/styles/calendar.css'/>" title="win2k-cold-1" />
  		<script type="text/javascript" src="<@spring.url '/scripts/jscalendar/calendar_stripped.js'/>"></script>
  		<script type="text/javascript" src="<@spring.url '/scripts/jscalendar/lang/calendar-de.js'/>"></script>
  		<script type="text/javascript" src="<@spring.url '/scripts/jscalendar/calendar-setup_stripped.js'/>"></script>

	</head>

	<body>
        <h2><#if pet.new>New</#if></h2>
        <@spring.bind 'pet.*' />
        <font color="red">
		     <#list spring.status.errorMessages as error> <b>${error}</b> <br> </#list>
		</font>
		
		<p>
		<b>Owner:</b> ${pet.owner.userData.firstName} ${pet.owner.userData.lastName}
		<p>
		<form method="POST">
			
			<b>Name:</b>
			<BR>
		    <@spring.formInput 'pet.name', 'maxlength="30" size="30"' />
		    <@spring.showErrors '<br>', 'fieldError' />
		
		    <p>
			<b>Birth Date:</b>
			<BR>
		    <@spring.formInput 'pet.birthDate', 'maxlength="10" size="10"' /><button type="reset" id="btnDate">...</button> (yyyy-mm-dd)
		    <@spring.showErrors '<br>', 'fieldError' />
		
		
			<#--
				Input field with a trigger button. Clicking the button activates
				the calendar.  Note that this one needs double-click (singleClick parameter
				is explicitly set to false).  Also demonstrates the "step" parameter
				introduced in 0.9.6 (show all years in drop-down boxes, instead of every
				other year as default).
			-->
	
			<script type="text/javascript">
	    		Calendar.setup({
	        		inputField     :    "birthDate",      			// id of the input field
	        		ifFormat       :    "%Y-%m-%d",       	        // format of the input field (With time: %d.%m.%Y %I:%M %p)
	        		showsTime      :    false,            			// will display a time selector
	        		button         :    "btnDate",   				// trigger for the calendar (button ID)
	        		singleClick    :    true,           			// double-click mode
	        		step           :    1               			// show all years in drop-down boxes (instead of every other year as default)
	    		});
			</script>
		
		
		
		    <p>
		    <b>Type:</b>
			<BR>
			<@spring.formSingleSelect 'pet.type', types, ''/> 
		    <@spring.showErrors '<br>', 'fieldError' />
		
		<p>
		<#if pet.new><input type="submit" value="Add Pet" /></#if>
		<#if !pet.new><input type="submit" value="Update Pet" /></#if>
		
		</form>
	</body>
</html>


