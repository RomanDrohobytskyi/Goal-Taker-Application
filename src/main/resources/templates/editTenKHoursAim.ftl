<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>E d i t</title>
    <link rel="shortcut icon" type="image/png" href="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Group_font_awesome.svg/1024px-Group_font_awesome.svg.png">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/css/buttons.css">
    <script type="text/javascript" src="/static/javascript/js.js"></script>
    <script type="text/javascript" src="/static/javascript/validators.js"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <#import "parts/footer.ftl" as footer>
    <#import "parts/elements.ftl" as elements>
</head>
<body>

<!-- NavBar (sit on top) -->
<div class="w3-top">
    <div class="w3-bar" id="myNavBar">
        <a class="w3-bar-item w3-button w3-hover-black w3-hide-medium w3-hide-large w3-right" href="javascript:void(0);" onclick="toggleFunction()" title="Toggle Navigation Menu">
            <i class="fa fa-bars"></i>
        </a>
        <a href="/" class="w3-bar-item w3-button"><i class="fa fa-home"></i>Home</a>
        <a href="#home" class="w3-bar-item w3-button"><i class="fa fa-chevron-up"></i>Up</a>
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
        <a href="greeting.ftl" class="w3-bar-item w3-button" onclick="toggleFunction()">Home</a>
        <a href="#home" class="w3-bar-item w3-button" onclick="toggleFunction()">Up</a>
        <a href="#" class="w3-bar-item w3-button">SEARCH</a>
    </div>
</div>

<!-- First Parallax Image -->
<div class="parallax big-img-users-1 w3-display-container w3-opacity-min" id="home">
    <div class="w3-display-middle" style="white-space:nowrap;">
        <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Aim</span>
    </div>
</div>

<!-- Aim edit -->
<div class="w3-content w3-container w3-padding-64" id="edit-aim">

    <h3 class="w3-center">E D I T</h3>

    <div class="w3-center w3-row">

        <form action="/editTenKHoursAim" method="post" style="margin: 30px 10px 10px">

            <label>
                <i class="w3-center">t i t l e</i>
                <br>
                <input id="aim_title${aim.id}" type="text" name="title" value="${aim.title}" style="margin: 20px;">
                <br>
                <i class="w3-center">t e x t</i>
                <br>
                <input id="aim_text${aim.id}" type="text" name="text" value="${aim.text}" style="margin: 20px;">
                <br>
                <i class="w3-center">d e s c r i p t i o n</i>
                <br>
                <input id="aim_description${aim.id}" type="text" name="description" value="${aim.description}"
                       style="margin: 20px;">
            </label>
            <br>

            <label>
                <i class="w3-center">specify</i>
                <br>
                <input id="aim_s_${aim.id}" type="text" name="specific" value="${aim.specify}" style="margin: 20px;"><br>
                <i class="w3-center">measurable</i>
                <br>
                <input id="aim_m_${aim.id}" type="text" name="measurable" value="${aim.measurable}" style="margin: 20px;"><br>
                <i class="w3-center">attainable</i>
                <br>
                <input id="aim_a_${aim.id}" type="text" name="attainable" value="${aim.attainable}"
                       style="margin: 20px;"><br>
                <i class="w3-center">relevant</i>
                <br>
                <input id="aim_r_${aim.id}" type="text" name="relevant" value="${aim.relevant}" style="margin: 20px;"><br>
                <i class="w3-center">timeBased</i>
                <br>
                <input id="aim_t_${aim.id}" type="date" name="timeBased" value="${aim.timeBased}" style="margin: 20px;">

            </label>

            <input type="hidden" value="${aim.id}" name="aimId">
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <br>
            <button type="submit" onclick="return validateLength('aim_title${aim.id}', 3, 32)"
                    class="small-btn btn2 w3-button w3-padding-large">
                Save
            </button>

            <form action="/editAim/cancel" method="post" style="margin: 30px 10px 10px">
                <button type="submit" class="small-btn btn2 w3-button w3-padding-large">
                    Cancel
                </button>
            </form>
        </form>
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
