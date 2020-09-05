<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>A n a l i z e r</title>
    <link rel="shortcut icon" type="image/png"
          href="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Group_font_awesome.svg/1024px-Group_font_awesome.svg.png">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/css/buttons.css">
    <script type="text/javascript" src="/static/javascript/js.js"></script>
    <script type="text/javascript" src="/static/javascript/validators.js"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="/static/css/menu.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <#import "parts/footer.ftlh" as footer>
    <#import "parts/elements.ftlh" as elements>
    <#import "parts/charst.ftlh" as charts>
    <#import "parts/details.ftlh" as details>
    <#import "parts/smartAimMarcos.ftlh" as smart>
    <#import "parts/popups.ftlh" as popups>
    <#import "parts/menu.ftlh" as menu>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript"></script>
</head>
<body>

    <@menu.customMenu menuElements slideMenuElements/>

    <div class="parallax big-img-analytics-charts-big w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Analyzer</span>
        </div>
    </div>

    <#--Firts analizer container-->
    <div class="w3-content w3-container w3-padding-32" id="loggedTimeTable">
        <h3 class="w3-center w3-black" style="background-color: #616161!important;">${aim.title!''}</h3>
        <p class="w3-center article"><em>${aim.description!''}</em></p>
        <#--Aim main details-->
        <div class="w3-row" id="main-details" style="padding: 12px 24px!important">
            <div class="w3-col m6 w3-center">
                <strong>Logged time count:</strong> ${loggedTime?size}
                <#if loggedTimeSum?has_content>
                    <br>
                    <strong>Logged time sum: </strong> ${loggedTimeSum}h
                </#if>
            </div>
            <div class="w3-col m6 w3-hide-small w3-center">
                <#if aim.specify?has_content>
                   <@smart.smartAttributes aim/>
                <#elseif loggedTimeSum?has_content>
                    <div class="w3-light-grey">
                        <div class="w3-container w3-padding-small w3-dark-grey w3-center"
                             style="width:${(loggedTimeSum / 100)}%">
                                ${loggedTimeSum}h
                        </div>
                    </div>
                    of 10 000h
                </#if>
            </div>
        </div>

        <div class="w3-row">
            <div class="w3-center">
                <h3 class="w3-center">Logged time</h3>
                <p class="w3-center article"><em>Only not deleted time</em></p>
                <@details.loggedTimeDetail loggedTime aim/>
            </div>
        </div>
    </div>

    <div class="parallax big-img-analytics-charts w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Charts</span>
        </div>
    </div>

    <#--Second analizer container-->
    <div class="w3-content w3-container w3-padding-32" id="line-chart">
        <h3 class="w3-center w3-black" style="background-color: #616161!important;">${aim.title!''}</h3>
        <p class="w3-center article"><em>${aim.description!''}</em></p>
        <div class="w3-row">
        <#--Logged time simple chart-->
            <div class="w3-col m6 w3-center w3-padding-large">
                 <@charts.pieChart/>
            </div>

            <div class="w3-col m6 w3-center w3-padding-large">
                 <@charts.lineChart/>
            </div>

            <div class="w3-col m6 w3-center w3-padding-large">
                 <@charts.splineAreaChart/>
            </div>

            <div class="w3-col m6 w3-center w3-padding-large">
                 <@charts.columnChart/>
            </div>

            <@charts.all loggedTime=loggedTime lastSevenDaysTime=lastSevenDaysTime mostProductive=mostProductive
                lessProductive=lessProductive loggedTime_2='' lastSevenDaysTime_2='' mostProductive_2='' lessProductive_2=''/>
        </div>
    </div>

    <div class="parallax big-img-analytics-charts w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">More data</span>
        </div>
    </div>

    <div class="w3-content w3-container w3-padding-32" id="loggedTimeTable">
        <div class="w3-row">
            <div class="w3-center">
                <h3 class="w3-center w3-black" style="background-color: #616161!important;">${aim.title} - ${loggedTimeSum}h</h3>
            </div>
        </div>
        <div class="w3-row" id="main-details" style="padding: 12px 24px!important">
            <div class="w3-col m6 w3-center">
            <#if mostProductive?has_content>
                <p style="w3-center"><strong>Most productive</strong></p>
                <br>
                <div style="text-align: left;">
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
                </div>
            </#if>
            </div>
            <div class="w3-col m6 w3-hide-small w3-center">
                 <#if lessProductive?has_content>
                     <p style="w3-center"><strong>Less productive</strong></p>
                     <br>
                     <div style="text-align: right;">
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
                     </div>
                 </#if>
            </div>
        </div>
    </div>

    <div class="parallax big-img-analytics-charts w3-display-container w3-opacity-min" id="home">
    </div>

    <@footer.footer/>

</body>
</html>
