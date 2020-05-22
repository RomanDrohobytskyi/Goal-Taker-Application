<#macro tenThousandAim aims>
    <i class="fa fa-hand-pointer-o" aria-hidden="true"><em style="margin-left: 5px;">Click on aim id to check details</em></i>

    <#-- Table of a messages -->
    <table id="aimsTable" align="center" width="100%" style="padding: 10px;/*table-layout: fixed;*/">
    <#-- Table header -->
        <tr>
            <th>ID</th>
            <th>State</th>
            <th>Title</th>
            <th>Description</th>
            <th>Text</th>
            <th>Edit</th>
            <th>Delete</th>
            <th>Achieve</th>
        </tr>

    <#-- All Aims -->
        <#if aims?has_content>
            <#list aims as aim>
                <#if aim.aimState!= "DELETED">
                    <#assign style = ""/>
                    <#if aim.aimState == "ACHIEVED">
                        <#assign style = "text-decoration: line-through; color:#2E9267;"/>
                    </#if>
                    <tr id="aim_${aim.id}" style="text-align:center; height: 100px; ${style}">
                        <td><b>
                            <a href="/tenKHoursAimDetails/${aim.id}" style="text-decoration:none" title="Go to details: ${aim.title}">
                                ${aim.id}
                            </a>
                        </b></td>
                        <td><span style="${style}">${aim.aimState}</span></td>
                        <td><span style="${style}">${aim.title}</span></td>
                        <td style="word-wrap: break-word"><i style="${style}">${aim.description}</i></td>
                        <td style="word-wrap: break-word"><i style="${style}">${aim.text}</i></td>
                        <td>
                            <div>
                                <a href="/editTenKHoursAim/${aim.id}" >
                                    <i style="${style}" class="fa fa-pencil" aria-hidden="true"></i>
                                </a>
                            </div>
                        </td>

                        <td>
                            <div>
                                <a href="/ten_thousand_hours_aim/delete/${aim.id}">
                                    <i style="${style}" class="fa fa-trash-o" aria-hidden="true" title="Delete aim"></i>
                                </a>
                                <input type="hidden" value="${aim}" name="aim">
                            </div>
                        </td>
                        <td>
                            <div>
                                <#if aim.aimState == "ACHIEVED">
                                    <i class="fa fa-check" style="${style}" aria-hidden="true" title="Aim '${aim.title}' - already achieved"></i>
                                <#else>
                                    <a href="/ten_thousand_hours_aim/achieve/${aim.id}">
                                        <i class="fa fa-check" style="${style}" aria-hidden="true" title="Achieve aim '${aim.title}'"></i>
                                    </a>
                                </#if>
                                <input type="hidden" value="${aim}" name="aim">
                            </div>
                        </td>
                    </tr>
                   <#-- <#if timeSum?has_content && timeSum.get(aim.id)?has_content>
                        <tr>
                            <td>
                                <div class="w3-col m6 w3-center w3-padding-large">
                                    <div class="w3-light-grey">
                                        <div class="w3-container w3-padding-small w3-dark-grey w3-center"
                                             style="width:${timeSum.get(aim.id)}%">
                                            <a style="text-decoration: none;" class="time" title="More details">
                                                ${timeSum.get(aim.id)}h
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </#if>-->
                </#if>
            </#list>
        <#else>
             <h4 class="w3-center" style="font-weight: bold;">No aims yet</h4>
        </#if>
    </table>
</#macro>

<#macro notes notes>
    <div style="height:500px; overflow:auto;">
        <#-- Table of a messages table-layout: fixed; -->
        <table id="messagesTable" align="center" width="100%" style="">
            <#-- Table header -->
            <tr>
                <th>ID</th>
                <th>State</th>
                <th>Text</th>
                <th>Tag</th>
                <th>Email</th>
                <th>Img</th>
                <th>Edit</th>
                <th>
                    <input id="selectAll_checkbox" type="checkbox" title="Check All Messages"
                       onclick="selectDeselectCheckbox('.messageCheckbox');
                       showSelectedCheckboxesCount('.messageCheckbox', '#count-checked-checkboxes');"/>
                </th>
                <th>Delete</th>
                <th>Achieve</th>
            </tr>

            <#-- All messages -->
            <#list notes as message>
                <#if message.state != "DELETED">
                    <#assign style = ""/>
                    <#if message.state == "ACHIEVED">
                        <#assign style = "text-decoration: line-through; color:#2E9267;"/>
                    </#if>
                     <tr id="message_${message.id}" style="height: 100px">
                         <td><b style="${style}">${message.id}</b></td>
                         <td><b style="${style}">${message.state}</b></td>
                         <td style="word-break:break-all;"><span style="${style}">${message.text}</span></td>
                         <td style="word-break:break-all;"><i style="${style}">${message.tag}</i></td>
                         <td><strong style="${style}">${message.getAuthorEmail()}</strong></td>
                         <td>
                             <div >
                                <#if message.filename?has_content>
                                    <img src="/img/${message.filename}" style="width:130px;height:100px;">
                                <#else >
                                    <p style="color: gray()">
                                        <strong style="${style}">No image</strong>
                                    </p>
                                </#if>
                             </div>
                         </td>
                         <td>
                             <div>
                                 <a href="/editMessage/${message.id}" ><i class="fa fa-pencil" aria-hidden="true"></i></a>
                             </div>
                         </td>
                         <td>
                             <div>
                                 <input id="${message.id}" type="checkbox" name="selected${message.id}" class="messageCheckbox"
                                        title="Check" onclick="showSelectedCheckboxesCount('.messageCheckbox', '#count-checked-checkboxes');"/>
                             </div>
                         </td>
                         <td>
                             <div>
                                 <a href="/main/delete/${message.id}">
                                     <i class="fa fa-trash-o" aria-hidden="true" title="Delete note"></i>
                                 </a>
                                 <input type="hidden" value="${message}" name="message">
                             </div>
                         </td>
                         <td>
                             <div>
                                 <#if message.state == "ACHIEVED">
                                     <i class="fa fa-check" style="${style}" aria-hidden="true" title="Note '${message.text}' - already achieved"></i>
                                 <#else>
                                     <a href="/main/achieve/${message.id}">
                                         <i class="fa fa-check" style="${style}" aria-hidden="true" title="Achieve note '${message.text}'"></i>
                                     </a>
                                 </#if>
                                 <input type="hidden" value="${message}" name="message">
                             </div>
                         </td>
                     </tr>
                </#if>
            </#list>
        </table>
    </div>
</#macro>