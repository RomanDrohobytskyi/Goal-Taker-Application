<#macro userDetails user>
    <#if user?has_content>
     <p class="w3-center article"> <em>User details</em></p> <br>
        First name: ${user.firstName!''} <br>
        Last name: ${user.lastName!''} <br>
        Email: ${user.email!''} <br>
    </#if>
</#macro>

<#macro aimDetails aim>
    <#if aim?has_content>
        <p class="w3-center article"> <em>Aim details</em> <br> </p>
        Title: ${aim.title!''} <br>
        Description: ${aim.description!''} <br>
        State: ${aim.aimState!''} <br>
        Creation date: ${aim.creationDate!''} <br>
    </#if>
</#macro>