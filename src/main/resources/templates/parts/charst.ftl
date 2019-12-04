<#macro barChart aim>
    <style>
        .bar {
            fill: #777; /* changes the background */
            height: 21px;
            transition: fill .3s ease;
            cursor: pointer;
            font-family: Helvetica, sans-serif;
        }

        .bar text {
            color: black;
        }

        .bar:hover,
        .bar:focus {
            fill: #f1f1f1;
        }

        .bar:hover text,
        .bar:focus text {
            fill: #777;
        }
    </style>

    <#if aim?has_content && aim.loggedTime?has_content>
        <svg class="chart" width="420" height="150" aria-labelledby="title desc" role="img">
            <#assign y = 0>
            <#list aim.loggedTime as loggedTime>
                <#assign timeInProcent=(loggedTime.time * 4.1)>
                Date: ${loggedTime.date}
                <text x="85" y="28" dy=".35em">8 bananas</text>
                <g class="bar">
                    <rect width="${timeInProcent}" height="19" y="${y}"></rect>
                </g>
                <#assign y = y + 20>
            </#list>
        </svg>
    </#if>
</#macro>

<#macro largeBarChart loggedTime>
    <#if loggedTime?has_content>
        <#list loggedTime as loggedTime>
            <#assign timeInProcent=(loggedTime.time * 4.1)>
            ${loggedTime.description}<#sep>, ${loggedTime.date}
            <div class="w3-light-grey">
                <div class="w3-container w3-padding-small w3-dark-grey w3-center"
                     style="width:${timeInProcent}%">${loggedTime.time}h
                </div>
            </div>
        </#list>
    </#if>
</#macro>