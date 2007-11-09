/* 
   ===========================================================
   PetClinicPlus Javascript 
   ===========================================================
*/

/* 
    Function that retrieves owner names asynchronously from the remote server.
    Behind the scenes DWR handles the communication.
    The bean Spring bean "AjaxService" is exported to Javascript, see dispatcher-servlet.xml.  
*/
function findOwnerNames(autocompleter, token) {
    AjaxService.findOwnerNames(token, function(data) { autocompleter.setChoices(data) });
}
          
/*
    Each entry of the suggest list goes through this function and is converted to a string.
    Here we assume that the list entry is already a string, so the value is returned
    just as it came in.
*/            
function stringValueSelector(entry){
    return entry;
}