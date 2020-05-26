<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>A c t i v i t y</title>
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

    <#import "parts/footer.ftl" as footer>
    <#import "parts/elements.ftl" as elements>
    <#import "parts/charst.ftl" as charts>
    <#import "parts/details.ftl" as details>
    <#import "parts/popups.ftl" as popups>
    <#import "parts/menu.ftl" as menu>
    <#import "parts/smartAimMarcos.ftl" as smart>
    <#import "parts/userAnalyzerMacros.ftl" as userAnalyzer>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript"></script>
</head>
<body>

    <@menu.customMenu menuElements slideMenuElements/>

    <div class="parallax big-img-analytics-hologram-big w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">${user.getNames()}</span>
        </div>
    </div>

    <div class="w3-content w3-container w3-padding-32" id="analyzer">
        <h3 class="w3-center w3-black" style="background-color: #616161!important;">Your profile analyzer</h3>
        <p class="w3-center article"><em>${user.email}</em></p>
        <div class="w3-row" id="main-details" style="padding: 12px 24px!important">
            <div class="w3-col m6 w3-center" style="">
                <h3 class="w3-center w3-black" style="margin-right: 5px; background-color: #616161!important;">S.M.A.R.T</h3>
                <#if mostProductive?has_content>
                    <p style="w3-center"><strong>Most productive</strong></p>
                    <br>
                    <div style="text-align: left;">
                       <@userAnalyzer.mostProductive mostProductive/>
                    </div>
                </#if>
                <#if lessProductive?has_content>
                     <p style="w3-center"><strong>Less productive</strong></p>
                     <br>
                     <div style="text-align: left;">
                         <@userAnalyzer.lessProductive lessProductive/>
                     </div>
                </#if>
            </div>
            <div class="w3-col m6 w3-hide-small w3-center">
                <h3 class="w3-center w3-black" style="margin-left: 5px; background-color: #616161!important;">10,000 Hours Rule</h3>
                 <#if tenThousandHoursAimMostProductive?has_content>
                    <p style="w3-center"><strong>Most productive</strong></p>
                    <br>
                    <div style="text-align: right;">
                       <@userAnalyzer.mostProductive tenThousandHoursAimMostProductive/>
                    </div>
                 </#if>
                <#if tenThousandHoursAimLessProductive?has_content>
                     <p style="w3-center"><strong>Less productive</strong></p>
                     <br>
                     <div style="text-align: right;">
                         <@userAnalyzer.lessProductive tenThousandHoursAimLessProductive/>
                     </div>
                </#if>
            </div>
        </div>
    </div>

    <div class="parallax big-img-analytics-hologram w3-display-container w3-opacity-min" id="">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">More data</span>
        </div>
    </div>

    <div class="w3-content w3-container w3-padding-32" id="moreData">
        <div class="w3-row" id="main-details" style="padding: 12px 24px!important">
            <div class="w3-col m6 w3-center">
                <h3 class="w3-center w3-black" style="margin-right: 5px; background-color: #616161!important;">S.M.A.R.T</h3>
                All S.M.A.R.T aims logged time sum:
                <br>
                <b>${sum!0}h</b>
                <br>
                <div style="text-align: left;">
                    <#if mostActiveSmartAim.aimState == "ACHIEVED">
                        <h3 class="w3-center w3-black" style="margin-right: 5px; background-color: #616161!important;">${mostActiveSmartAim.title!}
                            <i class="fa fa-check" style="color: #2E9267;" title="Achieved!"></i>
                        </h3>
                    <#else>
                       <h3 class="w3-center w3-black" style="margin-right: 5px; background-color: #616161!important;">${mostActiveSmartAim.title!}</h3>
                    </#if>
                    <p class="w3-center article"><em>${mostActiveSmartAim.description!}</em></p>
                    <@details.aimDetails mostActiveSmartAim/>
                    <@smart.smartAttributes mostActiveSmartAim/>
                    <br>
                </div>
            </div>

            <div class="w3-col m6 w3-hide-small w3-center">
                <h3 class="w3-center w3-black" style="margin-left: 5px; background-color: #616161!important;">10,000 Hours Rule</h3>
                All Ten Thousand Hours Rule aims logged time sum:
                <br>
                <b>${tenThousandHoursAimSum!0}h</b>

                <div style="text-align: left;">
                    <#if mostActiveTenThousandAim.aimState == "ACHIEVED">
                        <h3 class="w3-center w3-black" style="margin-left: 5px; background-color: #616161!important;">${mostActiveTenThousandAim.title!}
                            <i class="fa fa-check" style="color: #2E9267;" title="Achieved!"></i>
                        </h3>
                    <#else>
                       <h3 class="w3-center w3-black" style="margin-left: 5px; background-color: #616161!important;">${mostActiveTenThousandAim.title!}</h3>
                    </#if>
                    <p class="w3-center article"><em>${mostActiveTenThousandAim.description!}</em></p>
                    <@details.aimDetails mostActiveTenThousandAim/>
                </div>

                <div class="w3-light-grey">
                    <div class="w3-container w3-padding-small w3-dark-grey w3-center"
                         style="width:${(tenThousandHoursAimSum / 100)}%">
                    ${tenThousandHoursAimSum}h
                    </div>
                </div>
                of 10 000h
            </div>
        </div>
    </div>

    <div class="parallax big-img-analytics-hologram w3-display-container w3-opacity-min" id="">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Smart Aim Charts</span>
        </div>
    </div>

    <div class="w3-content w3-container w3-padding-32" id="smartCharts">
        <div class="w3-row">
            <h3 class="w3-center w3-black" style="background-color: #616161!important;">S.M.A.R.T - ${mostActiveSmartAim.title}</h3>
            <div class="w3-col m6 w3-center w3-padding-large">
                 <@charts.pieChart/>
            </div>

            <div class="w3-col m6 w3-center w3-padding-large">
                 <@charts.lineChart/>
            </div>
            <div style="display: none;">
                <div class="w3-col m6 w3-center w3-padding-large">
                     <@charts.splineAreaChart/>
                </div>

                <div class="w3-col m6 w3-center w3-padding-large">
                     <@charts.columnChart/>
                </div>
            </div>
            <@charts.all loggedTime lastSevenDaysTime mostProductive lessProductive tenKLoggedTime tenKLastSevenDays mostActiveTenThousandAim tenThousandHoursAimLessProductive/>
        </div>
    </div>

    <div class="parallax big-img-analytics-hologram w3-display-container w3-opacity-min" id="">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Ten Thousand Hours Rule Aim Charts</span>
        </div>
    </div>

    <div class="w3-content w3-container w3-padding-32" id="tenKCharts">
        <div class="w3-row">
            <h3 class="w3-center w3-black" style="background-color: #616161!important;">10,000 Hours Rule Aim - ${mostActiveTenThousandAim.title}</h3>

            <div class="w3-col m6 w3-center w3-padding-large">
                 <@charts.pieChart_two/>
            </div>

            <div class="w3-col m6 w3-center w3-padding-large">
                 <@charts.lineChart_two/>
            </div>
        </div>
    </div>

    <div class="parallax big-img-analytics-hologram w3-display-container w3-opacity-min">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Total Data</span>
        </div>
    </div>

    <div class="w3-content w3-container w3-padding-32" id="totalData">
        <div class="w3-row" id="main-details" style="padding: 12px 24px!important">
            <div class="w3-col m6 w3-center">
                <h3 class="w3-center w3-black" style="margin-right: 5px; background-color: #616161!important;">S.M.A.R.T</h3>
                All goals count: ${notDeletedAims?size}
                <br>
                Achieved goals count: ${achievedAims?size}
                <#assign leftToAchieve = notDeletedAims?size - achievedAims?size/>
                <#if leftToAchieve gt 0>
                    <br>
                    Goals left to achieve: ${leftToAchieve}
                </#if>
            </div>

            <div class="w3-col m6 w3-hide-small w3-center">
                <h3 class="w3-center w3-black" style="margin-left: 5px; background-color: #616161!important;">10,000 Hours Rule</h3>
                All goals count: ${notDeletedTenKdAims?size}
                <br>
                Achieved goals count: ${achieveTenKdAims?size}
                <#assign leftToAchieve = notDeletedTenKdAims?size - achieveTenKdAims?size/>
                <#if leftToAchieve gt 0>
                    <br>
                    Goals left to achieve: ${leftToAchieve}
                </#if>

            </div>
        </div>
    </div>

    <div class="parallax big-img-analytics-hologram w3-display-container w3-opacity-min" id="footer-img"></div>

    <@footer.footer/>

</body>
</html>
