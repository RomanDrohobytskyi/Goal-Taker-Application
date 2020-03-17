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

    <#import "parts/footer.ftl" as footer>
    <#import "parts/elements.ftl" as elements>
    <#import "parts/charst.ftl" as charts>
    <#import "parts/details.ftl" as details>
    <#import "parts/popups.ftl" as popups>
    <#import "parts/menu.ftl" as menu>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript"></script>
</head>
<body>

<!-- NavBar (sit on top) -->
<div class="w3-top">
    <div class="w3-bar" id="myNavBar">
        <@menu.slideMenu/>

        <a class="w3-bar-item w3-button w3-hover-black w3-hide-medium w3-hide-large w3-right" href="javascript:void(0);"
           onclick="toggleFunction()" title="Toggle Navigation Menu">
            <i class="fa fa-bars"></i>
        </a>
        <a href="/" class="w3-bar-item w3-button"><i class="fa fa-home"></i>HOME</a>
        <a href="#home" class="w3-bar-item w3-button"><i class="fa fa-chevron-up"></i>UP</a>
        <a href="/login" class="w3-bar-item w3-button w3-hide-small w3-right w3-hover-red"><i class="fa fa-sign-in"></i>
            <form action="/logout" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </form>
        </a>

        <a href="#" class="w3-bar-item w3-button w3-hide-small w3-right w3-hover-red">
            <i class="fa fa-search"></i>
        </a>
    </div>

    <!-- NavBar on small screens -->
    <div id="navDemo" class="w3-bar-block w3-white w3-hide w3-hide-large w3-hide-medium">
        <a href="greeting.ftl" class="w3-bar-item w3-button" onclick="toggleFunction()">HOME</a>
        <a href="#home" class="w3-bar-item w3-button" onclick="toggleFunction()">UP</a>
        <a href="#" class="w3-bar-item w3-button">SEARCH</a>
    </div>
</div>

<!-- First Parallax Image -->
<div class="parallax big-img-users-1 w3-display-container w3-opacity-min" id="home">
    <div class="w3-display-middle" style="white-space:nowrap;">
        <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Details</span>
    </div>
</div>

<#--Firts analizer container-->
<div class="w3-content w3-container w3-padding-64" id="details">

    <h3 class="w3-center w3-black" style="background-color: #616161!important;">${aim.title!''}</h3>
    <p class="w3-center article"><em>${aim.description!''}</em></p>

<#--Aim main details-->
    <div class="w3-row" id="main-details" style="padding: 12px 24px!important">
        <div class="w3-col m6 w3-center w3-padding-large">
            awd
        </div>
        <div class="w3-col m6 w3-hide-small w3-center">
            dd
        </div>
    </div>

    <div class="w3-row">
        <#--All logged time-->
        <div class="w3-center">
            <h3 class="w3-center">Logged time</h3>
            <#-- Table of a aim and logged time -->
            <p class="w3-center article"><em>Only not deleted time</em></p>
            <@details.loggedTimeDetail logged_time=loggedTime aim=aim/>
        </div>
    </div>
</div>

<!-- First Parallax Image -->
<div class="parallax big-img-users-1 w3-display-container w3-opacity-min" id="home">
    <div class="w3-display-middle" style="white-space:nowrap;">
        <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Charts</span>
    </div>
</div>

<#--Firts analizer container-->
<div class="w3-content w3-container w3-padding-64" id="details">

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

        <@charts.all loggedTime lastSevenDaysTime mostProductive lessProductive/>

    </div>
</div>

<#--Second char div-->s
<div class="w3-content w3-container w3-padding-64" id="details">
    <div class="w3-col m6 w3-hide-small w3-center">
        <div class="w3-center w3-row">
            <div id="chartContainer2"></div>

        </div>
    </div>
</div>

<#--Second Paralax IMG-->
<div class="parallax big-img-users-1 w3-display-container w3-opacity-min" id="home">
    <div class="w3-display-middle" style="white-space:nowrap;">
    </div>
</div>

<#--Footer-->
<@footer.footer/>

</body>
</html>
