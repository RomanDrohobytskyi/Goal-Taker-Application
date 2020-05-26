<#macro barChart aim>
    <style>
        .bar {
            fill: #777;
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
            <#assign timeInProcent=(loggedTime.time * (4.16666666667))>
            ${loggedTime.description}, ${loggedTime.date}
            <div class="w3-light-grey" title="Creation date: ${loggedTime.creationDate}">
                <div class="w3-container w3-padding-small w3-dark-grey w3-center"
                     style="width:${timeInProcent}%">
                    <a style="text-decoration: none;" class="time" title="More details">
                        ${loggedTime.time}h
                    </a>
                </div>
            </div>
        </#list>
    <#else>
        No time logged yet!
    </#if>
</#macro>


<#macro all loggedTime lastSevenDaysTime mostProductive lessProductive loggedTime_2 lastSevenDaysTime_2 mostProductive_2 lessProductive_2>
<script type="text/javascript">
    window.onload = function () {
        var chart = new CanvasJS.Chart("chartContainer1",
                {
                    animationEnabled: true,
                    title: {
                        text: "Spline Area Chart"
                    },
                    axisX: {
                        interval: 10
                    },
                    data: [

                        {
                            type: "splineArea",
                            color: "rgba(255,12,32,.3)",
                            dataPoints: [
                                 <#list loggedTime as time>
                                     <#assign day = time.getConvertedDate(time).day>
                                     <#assign month = time.getConvertedDate(time).month>
                                     <#assign year = time.getConvertedDate(time).year>
                                    {label: '${time.getConvertedDate(time).toString()}', y: ${time.time?string}},
                                 </#list>
                            ]
                        }
                    ]
                });
        chart.render();

        var chart = new CanvasJS.Chart("chartContainer2",
                {
                    animationEnabled: true,
                    title: {
                        text: "Last 7 days"
                    },
                    data: [
                        {
                            type: "pie",
                            showInLegend: true,
                            dataPoints: [
                                <#if lastSevenDaysTime?has_content>
                                    <#list lastSevenDaysTime as time>
                                    {
                                        y: ${time.time?string},
                                        legendText: '${time.description + '/' + time.time + 'h'}',
                                        indexLabel: '${time.getConvertedDate(time).toString()}'
                                    },
                                    </#list>
                                </#if>
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
                    data: [
                        {
                            type: "line",
                            dataPoints: [
                                 <#list loggedTime as time>
                                     <#assign day = time.getConvertedDate(time).day>
                                     <#assign month = time.getConvertedDate(time).month>
                                     <#assign year = time.getConvertedDate(time).year>

                                     <#if time.state != "DELETED">
                                         <#if mostProductive.id = time.id>
                                              {
                                                  label: '${time.getConvertedDate(time).toString()}',
                                                  y: ${time.time?string},
                                                  indexLabel: "highest", markerColor: "red",
                                                  markerType: "triangle"
                                              },
                                         <#elseif lessProductive.id = time.id>
                                              {
                                                  label: '${time.getConvertedDate(time).toString()}',
                                                  y: ${time.time?string},
                                                  indexLabel: "lowest",
                                                  markerColor: "DarkSlateGrey",
                                                  markerType: "cross"
                                              },
                                         <#else>
                                             {
                                                 label: '${time.getConvertedDate(time).toString()}',
                                                 y: ${time.time?string}
                                             },
                                         </#if>
                                     </#if>
                                 </#list>
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
                        interval: 5
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
                                 <#list loggedTime as time>
                                    {
                                        label: '${time.getConvertedDate(time).toString()}',
                                        y: ${time.time?string}
                                    },
                                 </#list>
                            ]
                        }
                    ]
                });
        chart.render();

        <#if loggedTime_2?has_content && lastSevenDaysTime_2?has_content && mostProductive_2?has_content && lessProductive_2?has_content>
            var chart = new CanvasJS.Chart("chartContainer5",
                    {
                        animationEnabled: true,
                        title: {
                            text: "Last 7 days"
                        },
                        data: [
                            {
                                type: "pie",
                                showInLegend: true,
                                dataPoints: [
                                    <#if lastSevenDaysTime_2?has_content>
                                        <#list lastSevenDaysTime_2 as time>
                                        {
                                            y: ${time.time?string},
                                            legendText: '${time.description + '/' + time.time + 'h'}',
                                            indexLabel: '${time.getConvertedDate(time).toString()}'
                                        },
                                        </#list>
                                    </#if>
                                ]
                            }
                        ]
                    });
            chart.render();

            var chart = new CanvasJS.Chart("chartContainer6",
                    {
                        animationEnabled: true,
                        title: {
                            text: "Line Chart"
                        },
                        data: [
                            {
                                type: "line",
                                dataPoints: [
                                     <#list loggedTime_2 as time>
                                         <#assign day = time.getConvertedDate(time).day>
                                         <#assign month = time.getConvertedDate(time).month>
                                         <#assign year = time.getConvertedDate(time).year>

                                         <#if time.state != "DELETED">
                                             <#if mostProductive_2.id = time.id>
                                                  {
                                                      label: '${time.getConvertedDate(time).toString()}',
                                                      y: ${time.time?string},
                                                      indexLabel: "highest", markerColor: "red",
                                                      markerType: "triangle"
                                                  },
                                             <#elseif lessProductive_2.id = time.id>
                                                  {
                                                      label: '${time.getConvertedDate(time).toString()}',
                                                      y: ${time.time?string},
                                                      indexLabel: "lowest",
                                                      markerColor: "DarkSlateGrey",
                                                      markerType: "cross"
                                                  },
                                             <#else>
                                                 {
                                                     label: '${time.getConvertedDate(time).toString()}',
                                                     y: ${time.time?string}
                                                 },
                                             </#if>
                                         </#if>
                                     </#list>
                                ]
                            }
                        ]
                    });
            chart.render();
        </#if>
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

<#macro pieChart_two>
    <div id="chartContainer5" style="width: 100%; height: 300px;display: inline-block;"></div>
</#macro>

<#macro lineChart_two>
    <div id="chartContainer6" style="width: 100%; height: 300px;display: inline-block;"></div>
</#macro>

