{
"fieldErrors":[
<#assign keys = fieldErrors?keys>
<#list keys as key>
"${fieldErrors[key][0]}"<#if key_has_next>,</#if>
</#list>
],
"actionErrors":[
<#list actionErrors as actionError>
"${actionError}"<#if actionError_has_next>,</#if>
</#list>
],
"actionMessages":[
<#list actionMessages as actionMessage>
"${actionMessage}"<#if actionMessage_has_next>,</#if>
</#list>
]
}
