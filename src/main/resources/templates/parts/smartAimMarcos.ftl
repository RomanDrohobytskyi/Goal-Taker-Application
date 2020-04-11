<#import "elements.ftl" as elements>

<#macro smartTable aims>
    <i class="fa fa-hand-pointer-o" aria-hidden="true"><em style="margin-left: 5px;">Click on aim id to check details</em></i>
<#-- Table of a aims -->
    <table id="aimsTable" align="center" width="100%" style="padding: 10px;/*table-layout: fixed;*/">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Description</th>
            <th>Text</th>
            <th>Smart</th>
            <th>Edit</th>
            <th>Delete</th>
            <th>Achieve</th>
        </tr>

    <#-- All Aims -->
        <#if aims?has_content>
            <#list aims as aim>
                <#if aim.aimState != "DELETED">
                    <#assign style = ""/>
                    <#if aim.aimState == "ACHIEVED">
                        <#assign style = "text-decoration: line-through; color:#2E9267;"/>
                    </#if>
                    <tr id="aim_${aim.id}" style="text-align:center; height: 100px">
                        <td><b>
                            <a href="/aim_details/${aim.id}" style="text-decoration:none; ${style}" title="Go to details: ${aim.title}">
                                ${aim.id}
                            </a>
                        </b></td>
                        <td><span style="${style}">${aim.title}</span></td>
                        <td style="word-wrap: break-word; ${style}"><i>${aim.description}</i></td>
                        <td style="word-wrap: break-word; ${style}"><i>${aim.text}</i></td>
                        <td style="word-wrap: break-word; ${style}">
                            <i style="${style}" title="S">${aim.specify!''}</i>
                            <i style="${style}" title="M">${aim.measurable!''}</i>
                            <i style="${style}" title="A">${aim.attainable!''}</i>
                            <i style="${style}" title="R">${aim.relevant!''}</i>
                            <i style="${style}" title="T">${aim.timeBased!''}</i>
                        </td>
                        <td>
                            <div>
                                <a href="/editAim/${aim.id}" >
                                    <i class="fa fa-pencil" aria-hidden="true" title="Edit aim ${aim.title}"></i>
                                </a>
                            </div>
                        </td>

                        <td>
                            <div>
                                <a href="/main_aim/delete/${aim.id}">
                                    <i class="fa fa-trash-o" aria-hidden="true" title="Delete aim ${aim.title}"></i>
                                </a>
                                <input type="hidden" value="${aim}" name="aim">
                            </div>
                        <td>
                            <div>
                                <#if aim.aimState == "ACHIEVED">
                                    <i class="fa fa-check" style="${style}" aria-hidden="true" title="Aim '${aim.title}' - already achieved"></i>
                                <#else>
                                    <a href="/main_aim/achieve/${aim.id}">
                                        <i class="fa fa-check" style="${style}" aria-hidden="true" title="Achieve aim '${aim.title}'"></i>
                                    </a>
                                </#if>
                                <input type="hidden" value="${aim}" name="aim">
                            </div>
                        </td>
                    </tr>
                </#if>
            </#list>
        <#else>
             <h4 class="w3-center" style="font-weight: bold;">No aims yet</h4>
        </#if>
    </table>
</#macro>


<#macro addSmartAim>
     <div style=" margin-right: 10px;">
                    <@elements.input id="aim_title" name="title" type="text" placeholder="t i t l e . . ."
                    onfocus="this.placeholder = ''"  onblur="this.placeholder = 't i t l e . . .'"/>
         <br><br>
     <#--<p class="w3-center">d e s c r i p t i o n</p>-->
                    <@elements.input id="aim_description" name="description" type="text" placeholder="d e s c r i p t i o n  . . ."
                    onfocus="this.placeholder = ''"  onblur="this.placeholder = 'd e s c r i p t i o n  . . .'"/>
         <br><br>
         <textarea id="aim_text" name="text" placeholder="t e x t  . . ." rows="2" cols="21"
                   style="text-align: center; width: 250px;"></textarea>
         <br><br>
     </div>

    <div style="">
        <@elements.input id="specific" name="specific" type="text" placeholder="s p e c i f i c"
        onfocus="this.placeholder = ''"  onblur="this.placeholder = 's p e c i f i c'"/>
        <br><br>
        <@elements.input id="measurable" name="measurable" type="text" placeholder="m e a s u r a b l e"
        onfocus="this.placeholder = ''"  onblur="this.placeholder = 'm e a s u r a b l e'"/>
        <br><br>
        <@elements.input id="attainable" name="attainable" type="text" placeholder="a t t a i n a b l e"
        onfocus="this.placeholder = ''"  onblur="this.placeholder = 'a t t a i n a b l e'"/>
        <br><br>
        <@elements.input id="relevant" name="relevant" type="text" placeholder="r e l e v a n t"
        onfocus="this.placeholder = ''"  onblur="this.placeholder = 'r e l e v a n t'"/>
        <br><br>
        <@elements.input id="timeBased" name="timeBased" type="date" placeholder="t i m e b a s e d"
        onfocus="this.placeholder = ''"  onblur="this.placeholder = 't i m e b a s e d'"/>
    </div>

    <div class="w3-center" style="float:bottom">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />

        <button type="submit" class="btn btn1 w3-button w3-padding-large"
                onclick="return validateLength('Title', 'aim_title', 3, 32)">
            A d d
        </button>
    </div>
</#macro>

<#macro smartAttributes aim>
    <strong>S.M.A.R.T</strong> <br>
    S: ${aim.specify} <br>
    M: ${aim.measurable} <br>
    A: ${aim.attainable} <br>
    R: ${aim.relevant} <br>
    T: ${aim.timeBased} <br>
</#macro>