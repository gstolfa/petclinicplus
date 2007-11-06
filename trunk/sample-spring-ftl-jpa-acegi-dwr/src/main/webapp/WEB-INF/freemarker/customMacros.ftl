<#ftl strip_whitespace=true>
<#import "spring.ftl" as spring />

<#--
 * customMacros.ftl
 *
 * This file consists of a collection of FreeMarker macros aimed at easing
 * some of the common requirements of web applications.
 
 * @author Lars Behnke

 -->


<#--
 * formNamedEntityCheckboxes
 *
 * Show checkboxes for named entities.
 *
 * @param path the name of the field to bind to
 * @param options a map (value=label) of all the available options
 * @param separator the html tag or other character list that should be used to
 *    separate each option. Typically '&nbsp;' or '<br>'
 * @param attributes any additional attributes for the element (such as class
 *    or CSS styles or size
-->
<#macro formNamedEntityCheckboxes path options separator attributes="">
    <@spring.bind path/>
    <#list options?keys as valueId>
    <#assign id="${spring.status.expression}${valueId}">
    <#assign isSelected = containsBaseEntity(spring.status.value, valueId)>
    <input type="checkbox" id="${id}" name="${spring.status.expression}" value="${valueId?html}"<#if isSelected> checked="checked"</#if> ${attributes}<@spring.closeTag/>
    <label for="${id}">${options[valueId]?html}</label>${separator}
    </#list>
    <input type="hidden" name="_${spring.status.expression}" value="on"/>
</#macro>


<#--
 * containsBaseEntity
 *
 * Macro to return true if a list od BaseEntity objects contains the passed item, false if not.
 * The result is determined by comparing the item IDs.
 *
 * @param list the list of base entitie to search for the item
 * @param item the itemId to search for in the list
 * @return true if item is found in the list, false otherwise
-->
<#function containsBaseEntity list itemId>
    <#list list as nextInList>
    <#if nextInList.id?string == itemId?string><#return true></#if>
    </#list>
    <#return false>
</#function>
