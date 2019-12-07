<#macro userDetails user>
    <#if user?has_content>
     <p class="w3-center article"> <em>User details</em></p> <br>
        ${user.firstName!''} <br>
        ${user.lastName!''} <br>
        ${user.email!''} <br>
    </#if>
</#macro>

<#macro aimDetails aim>
    <#if aim?has_content>
        <p class="w3-center article"> <em>Aim details</em> <br> </p>
        ${aim.title!''} <br>
        ${aim.description!''} <br>
        ${aim.aimState!''} <br>
        ${aim.creationDate!''} <br>
    </#if>
</#macro>