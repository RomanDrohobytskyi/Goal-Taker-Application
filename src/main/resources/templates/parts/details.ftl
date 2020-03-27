<#macro userDetails user>
    <#if user?has_content>
     <p class="w3-center article"> <em>User details</em></p> <br>
        ${user.firstName!} <br>
        ${user.lastName!} <br>
        ${user.email!} <br>
    </#if>
</#macro>

<#macro aimDetails aim>
    <#if aim?has_content>
        <p class="w3-center article"> <em>Aim details</em> <br> </p>
        ${aim.title!} <br>
        ${aim.description!} <br>
        ${aim.aimState!} <br>
        ${aim.creationDate!} <br>
    </#if>
</#macro>

<#macro loggedTimeDetail logged_time aim>
    <table id="timeTable" align="center" width="100%"
           style="padding: 10px;">
    <#-- Table header -->
        <tr>
            <th>ID</th>
            <th>Description</th>
            <th>Creation date</th>
            <th>Time</th>
            <th>Delete</th>
        </tr>
        <#if logged_time?has_content>
            <#list logged_time as time>
                <#if time.state!= "DELETED">
                    <tr id="time_${time.id}" style="text-align:center; height: 100px">
                        <td>
                            <b>${time.id}</b>
                        </td>
                        <td style="word-wrap: break-word"><i>${time.description}</i></td>
                        <td><i>${time.creationDate}</i></td>
                        <td><i>${time.time}h.</i></td>
                        <td>
                            <a href="/analyzer/${aim.id}/delete/${time.id}">
                                <i class="fa fa-trash-o" aria-hidden="true"
                                   title="Delete logged time"></i>
                            </a>
                            <input type="hidden" value="${time}" name="time">
                            <input type="hidden" value="${aim}" name="aim">
                        </td>
                    </tr>
                </#if>
            </#list>
        <#else>
             <h4 class="w3-center" style="font-weight: bold;">No logged time for
                 aim ${aim.title!'No title'} yet</h4>
        </#if>
    </table>
</#macro>