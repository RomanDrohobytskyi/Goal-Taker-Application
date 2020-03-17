<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>D e t a i l s</title>
    <link rel="shortcut icon" type="image/png" href="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Group_font_awesome.svg/1024px-Group_font_awesome.svg.png">
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

</head>
<body>

<style>
    .fa{margin-right: 5px;}
</style>

<!-- NavBar (sit on top) -->
<div class="w3-top">
    <div class="w3-bar" id="myNavBar">
        <@menu.slideMenu>

        </@menu.slideMenu>

        <a class="w3-bar-item w3-button w3-hover-black w3-hide-medium w3-hide-large w3-right" href="javascript:void(0);" onclick="toggleFunction()" title="Toggle Navigation Menu">
            <i class="fa fa-bars"></i>
        </a>
        <a href="/" class="w3-bar-item w3-button"><i class="fa fa-home"></i>HOME</a>
        <a href="#home" class="w3-bar-item w3-button"><i class="fa fa-chevron-up"></i>UP</a>
        <a href="/login" class="w3-bar-item w3-button w3-hide-small w3-right w3-hover-red"><i class="fa fa-sign-in"></i>
            <form action="/logout" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
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
        <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Aim</span>
    </div>
</div>

<div class="w3-content w3-container w3-padding-64" id="details">

    <#if aim.aimState == "ACHIEVED">
        <h3 class="w3-center w3-black" style="background-color: #616161!important;">${aim.title!''}
            <i class="fa fa-check" style="color: #2E9267;" title="Achieved!"></i>
        </h3>
    <#else>
        <h3 class="w3-center w3-black" style="background-color: #616161!important;">${aim.title!''}</h3>
    </#if>
    <p class="w3-center article"><em>${aim.description!''}</em></p>

    <#--Aim main details-->
    <div class="w3-row" id="main-details" style="padding: 12px 24px!important">
        <div class="w3-col m6 w3-center w3-padding-large">
            <@details.userDetails user=aim.user/>
        </div>
        <div class="w3-col m6 w3-hide-small w3-center">
            <@details.aimDetails aim=aim/>
        </div>
    </div>

    <div class="w3-center">
        <h3 class="w3-center" style="color: #777!important;">Logged time</h3>
    </div>

    <div class="w3-row">
        <#--Left side of dib-->
        <div class="w3-col m6 w3-center w3-padding-large">
            <@charts.largeBarChart loggedTime=lastWeekLoggedTime/>
            <@popups.details></@popups.details>
            <br>
            <a href="/analyzer/${aim.id}"> <button class="btn btn1 w3-button w3-padding-large">Analyzer</button></a>
        </div>

        <!-- Hide this text on small devices -->
        <div class="w3-col m6 w3-hide-small w3-center">
            <form action="/aim_details/saveDetails" method="get" enctype="multipart/form-data">
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
            </form>
        </div>
    </div>
</div>

<#--Second Paralax IMG-->
<div class="parallax big-img-users-1 w3-display-container w3-opacity-min" id="home">
    <div class="w3-display-middle" style="white-space:nowrap;">
    </div>
</div>

<#--Footer-->
<@footer.footer>
</@footer.footer>

</body>
</html>
