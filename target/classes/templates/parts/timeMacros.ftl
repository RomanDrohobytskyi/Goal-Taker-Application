<#import "elements.ftl" as elements>

<#macro timeLogging aim>
    <div>
        <p class="w3-center article">Log worked time on aim: <em>${aim.title}</em></p>
        <p>Time: </p>
        <@elements.input id="time" name="time" type="number" placeholder="t i m e . . ."
            onfocus="this.placeholder = ''"  onblur="this.placeholder = 't i m e . . .'" step="0.5"
            min="0.5" max="24"/>
        <p>Description: </p>
        <@elements.input id="description" name="description" type="text" placeholder="d e s c . . ."
            onfocus="this.placeholder = ''"  onblur="this.placeholder = 'd e s c . . .'"/>
        <p>Date: </p>
        <@elements.input id="date" name="date" type="date" placeholder="d a t e . . ."
            onfocus="this.placeholder = ''"  onblur="this.placeholder = 'd a t e . . .'"/>
        <br>
        <input type="hidden" value="${aim.id}" name="aimId">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn1 w3-button w3-padding-large">L o g</button>
    </div>
</#macro>
