<#macro mostProductive mostProductive>
    <strong>Aim:</strong> ${mostProductive.aim.title}, ${mostProductive.aim.description}
    <br>
    <strong>State:</strong> ${mostProductive.aim.aimState}
    <br>
    <strong>Time:</strong> ${mostProductive.time!0}h
    <br>
    <strong>Description:</strong> ${mostProductive.description}
    <br>
    <strong>Date:</strong> ${mostProductive.date}
    <br>
    <strong>Creation date:</strong> ${mostProductive.creationDate}
    <br>
    <strong>State:</strong> ${mostProductive.state}
</#macro>

<#macro lessProductive lessProductive>
    <strong>Aim:</strong> ${lessProductive.aim.title}, ${lessProductive.aim.description}
     <br>
     <strong>State:</strong> ${lessProductive.aim.aimState}
     <br>
     <strong>Time:</strong> ${lessProductive.time!0}h
     <br>
     <strong>Description:</strong> ${lessProductive.description}
     <br>
     <strong>Date:</strong> ${lessProductive.date}
     <br>
     <strong>Creation date:</strong> ${lessProductive.creationDate}
     <br>
     <strong>State:</strong> ${lessProductive.state}
</#macro>