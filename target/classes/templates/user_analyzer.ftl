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
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript"></script>
</head>
<body>

    <@menu.customMenu menuElements slideMenuElements/>

    <div class="parallax big-img-users-1 w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Aim</span>
        </div>
    </div>

    <#--Firts analizer container-->
    <div class="w3-content w3-container w3-padding-64" id="details">

        <h3 class="w3-center w3-black" style="background-color: #616161!important;">amdklawd</h3>
        <p class="w3-center article"><em>!!!!!!!!!!!!!!!!!!!!!!!!!!!!</em></p>

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
            </div>

        </div>
    </div>

    <div class="parallax big-img-users-1 w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Aim</span>
        </div>
    </div>

    <#--Second char div-->
    <div class="w3-content w3-container w3-padding-64" id="details">
        <div class="w3-col m6 w3-hide-small w3-center">
            <div class="w3-center w3-row">
                <div id="chartContainer2"></div>

            </div>
        </div>
    </div>

    <div class="parallax big-img-users-1 w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
        </div>
    </div>

    <@footer.footer/>

</body>
</html>
