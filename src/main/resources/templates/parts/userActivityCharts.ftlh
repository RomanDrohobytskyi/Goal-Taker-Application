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
    <style>
        .time:hover{
            color: #000 !important;
        }
    </style>

    <#if loggedTime?has_content>
        <#list loggedTime as loggedTime>
            <#assign timeInProcent=(loggedTime.time * 4.1)>
            ${loggedTime.description}<#sep>, ${loggedTime.date}
            <div class="w3-light-grey">
                <div class="w3-container w3-padding-small w3-dark-grey w3-center"
                     style="width:${timeInProcent}%">
                    <a id="myBtn" style="text-decoration: none;" class="time" title="More details">
                        ${loggedTime.time}h
                    </a>
                </div>
            </div>
        </#list>
    <#else>
        No time logged yet!
    </#if>
</#macro>


<#macro columnChart time>
     <script type="text/javascript">
         window.onload = function () {

             var chart1 = new CanvasJS.Chart("chartContainer1", {
                 theme: "light1", // "light2", "dark1", "dark2"
                 animationEnabled: false, // change to true
                 title:{
                     text: "Column chart"
                 },
                 data: [
                     {
                         // Change type to "bar", "area", "spline", "pie",etc.
                         type: "column",
                         dataPoints: [
                                <#list time as time>
                                    {label: '${time.getConvertedDate(time).toString()}', y: ${time.time?string}},
                                </#list>
                         ]
                     }
                 ]
             });
             chart1.render();

         }
     </script>
            <div id="chartContainer1" style="height: 370px; width: 100%;"></div>
            <script src="https://canvasjs.com/assets/script/canvasjs.min.js"> </script>
</#macro>


<#macro lineChart time>
     <script type="text/javascript">
         window.onload = function () {

             var chart2 = new CanvasJS.Chart("chartContainer2", {
                 theme: "light1", // "light2", "dark1", "dark2"
                 animationEnabled: false, // change to true
                 title:{
                     text: "Line chart"
                 },
                 data: [
                     {
                         // Change type to "bar", "area", "spline", "pie",etc.
                         type: "line",
                         dataPoints: [
                                <#list time as time>
                                    <#assign day = time.getConvertedDate(time).day>
                                    <#assign month = time.getConvertedDate(time).month>
                                    <#assign year = time.getConvertedDate(time).year>
                                    {label: '${time.getConvertedDate(time).toString()}', y: ${time.time?string}},
                                </#list>
                         ]
                     }
                 ]
             });
             chart2.render();

         }
     </script>
            <div id="chartContainer2" style="height: 370px; width: 100%;"></div>
            <script src="https://canvasjs.com/assets/script/canvasjs.min.js"> </script>
</#macro>

<#--
<#macro oneElementDetails loggedTime>
     <style>
         .time:hover{
             color: #000 !important;
         }
     </style>

    <#if loggedTime?has_content>
        <#assign timeInProcent=(loggedTime.time * 4.1)>
        ${loggedTime.description}, ${loggedTime.date}
        <div class="w3-light-grey">
            <div class="w3-container w3-padding-small w3-dark-grey w3-center"
                 style="width:${timeInProcent}%">
                <a id="myBtn" style="text-decoration: none;" class="time" title="More details">
                    ${loggedTime.time}h
                </a>
            </div>
        </div>
    </#if>
</#macro>-->

<#macro all aim lastSevenDaysTime>

<script type="text/javascript">
    window.onload = function () {
        var chart = new CanvasJS.Chart("chartContainer1",
                {
                    animationEnabled: true,
                    title: {
                        text: "Spline Area Chart"
                    },
                    axisX: {
                        interval: 10,
                    },
                    data: [
                        {
                            type: "splineArea",
                            color: "rgba(255,12,32,.3)",
                            dataPoints: [
                                 <#list aim.loggedTime as time>
                                     <#assign day = time.getConvertedDate(time).day>
                                     <#assign month = time.getConvertedDate(time).month>
                                     <#assign year = time.getConvertedDate(time).year>
                                    {label: '${time.getConvertedDate(time).toString()}', y: ${time.time?string}},
                                 </#list>
                                {x: new Date(1992, 0), y: 2506000},
                                {x: new Date(1993, 0), y: 2798000},
                                {x: new Date(1994, 0), y: 3386000},
                                {x: new Date(1995, 0), y: 6944000},
                                {x: new Date(1996, 0), y: 6026000},
                                {x: new Date(1997, 0), y: 2394000},
                                {x: new Date(1998, 0), y: 1872000},
                                {x: new Date(1999, 0), y: 2140000},
                                {x: new Date(2000, 0), y: 7289000},
                                {x: new Date(2001, 0), y: 4830000},
                                {x: new Date(2002, 0), y: 2009000},
                                {x: new Date(2003, 0), y: 2840000},
                                {x: new Date(2004, 0), y: 2396000},
                                {x: new Date(2005, 0), y: 1613000},
                                {x: new Date(2006, 0), y: 2821000}
                            ]
                        }
                    ]
                });
        chart.render();

        var chart = new CanvasJS.Chart("chartContainer2",
                {
                    animationEnabled: true,
                    title: {
                        text: "Pie Chart"
                    },
                    data: [
                        {
                            type: "pie",
                            showInLegend: true,
                            dataPoints: [
                            <#-- <#if lastSevenDaysTime?has_content>
                                 <#list lastSevenDaysTime as time>
                                 {y: ${time.time?string}, legendText: "${time.description}" indexLabel: '${time.getConvertedDate(time).toString()}'},
                                 </#list>
                             </#if>-->
                                {y: 4181563, legendText: "PS 3", indexLabel: "PlayStation 3"},
                                {y: 2175498, legendText: "Wii", indexLabel: "Wii"},
                                {y: 3125844, legendText: "360", indexLabel: "Xbox 360"},
                                {y: 1176121, legendText: "DS", indexLabel: "Nintendo DS"},
                                {y: 1727161, legendText: "PSP", indexLabel: "PSP"},
                                {y: 4303364, legendText: "3DS", indexLabel: "Nintendo 3DS"},
                                {y: 1717786, legendText: "Vita", indexLabel: "PS Vita"}
                            ]
                        }
                    ]
                });
        chart.render();





        var chart = new CanvasJS.Chart("chartContainer3",
                {
                    animationEnabled: true,
                    title: {
                        text: "Line Chart"
                    },
                    /*axisX: {
                        valueFormatString: "MMM",
                        interval: 1,
                        intervalType: "month"
                    },
                    axisY: {
                        includeZero: false
                    },*/
                    data: [
                        {
                            type: "line",
                            dataPoints: [
                                 <#list aim.loggedTime as time>
                                     <#assign day = time.getConvertedDate(time).day>
                                     <#assign month = time.getConvertedDate(time).month>
                                     <#assign year = time.getConvertedDate(time).year>
                                    {label: '${time.getConvertedDate(time).toString()}', y: ${time.time?string}},
                                 </#list>
                                /* {x: new Date(2012, 00, 1), y: 450},
                                 {x: new Date(2012, 01, 1), y: 414},
                                 {
                                     x: new Date(2012, 02, 1),
                                     y: 520,
                                     indexLabel: "highest",
                                     markerColor: "red",
                                     markerType: "triangle"
                                 },
                                 {x: new Date(2012, 03, 1), y: 460},
                                 {x: new Date(2012, 04, 1), y: 450},
                                 {x: new Date(2012, 05, 1), y: 500},
                                 {x: new Date(2012, 06, 1), y: 480},
                                 {x: new Date(2012, 07, 1), y: 480},
                                 {
                                     x: new Date(2012, 08, 1),
                                     y: 410,
                                     indexLabel: "lowest",
                                     markerColor: "DarkSlateGrey",
                                     markerType: "cross"
                                 },
                                 {x: new Date(2012, 09, 1), y: 500},
                                 {x: new Date(2012, 10, 1), y: 480},
                                 {x: new Date(2012, 11, 1), y: 510}*/
                            ]
                        }
                    ]
                });
        chart.render();

        var chart = new CanvasJS.Chart("chartContainer4",
                {
                    animationEnabled: true,
                    title: {
                        text: "Column Chart"
                    },
                    axisX: {
                        interval: 5,
                    },
                    data: [
                        {
                            type: "column",
                            legendMarkerType: "triangle",
                            legendMarkerColor: "green",
                            color: "rgba(255,12,32,.3)",
                            showInLegend: true,
                            legendText: "Country wise population",
                            dataPoints: [
                                 <#list aim.loggedTime as time>
                                    {label: '${time.getConvertedDate(time).toString()}', y: ${time.time?string}},
                                 </#list>
                            ]
                        },
                    ]
                });
        chart.render();
    }
</script>
<script type="text/javascript" src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
<div id="chartContainer" style="height: 300px; width: 100%;">
</div>
     <script type="text/javascript" src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</#macro>

<#macro splineAreaChart>
    <div id="chartContainer1" style="width: 100%; height: 300px;display: inline-block;"></div>
</#macro>

<#macro pieChart>
    <div id="chartContainer2" style="width: 100%; height: 300px;display: inline-block;"></div>
</#macro>

<#macro lineChart>
    <div id="chartContainer3" style="width: 100%; height: 300px;display: inline-block;"></div>
</#macro>

<#macro columnChart>
    <div id="chartContainer4" style="width: 100%; height: 300px;display: inline-block;"></div>
</#macro>

