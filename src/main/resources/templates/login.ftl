<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>L o g i n</title>
    <link rel="shortcut icon" type="image/png" href="http://cdn.onlinewebfonts.com/svg/img_299586.png">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/css/buttons.css">
    <link rel="stylesheet" href="/static/css/login.css">
    <link rel="stylesheet" href="/static/css/text.css">
    <script type="text/javascript" src="/static/javascript/js.js"></script>

    <#--<link rel="stylesheet" href="/static/css/login.css">-->
    <#--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>-->
    <#--<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css">-->

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <#import "parts/footer.ftl" as footer>
    <#import "parts/elements.ftl" as elements>

</head>
<body>

<style>
    .fa{margin-right: 1px; margin-left: 5px;}
</style>

<!-- NavBar (sit on top) -->
<div class="w3-top">
    <div class="w3-bar" id="myNavBar">
        <a class="w3-bar-item w3-button w3-hover-black w3-hide-medium w3-hide-large w3-right" href="javascript:void(0);" onclick="toggleFunction()" title="Toggle Navigation Menu">
            <i class="fa fa-bars"></i>
        </a>
        <a href="/" class="w3-bar-item w3-button"><i class="fa fa-home"></i>Home</a>
        <a href="#home" class="w3-bar-item w3-button"><i class="fa fa-chevron-up"></i>Up</a>

        <a href="#" class="w3-bar-item w3-button w3-hide-small w3-right w3-hover-red">
        <i class="fa fa-search"></i>
        </a>
    </div>

    <!-- NavBar on small screens -->
    <div id="navDemo" class="w3-bar-block w3-white w3-hide w3-hide-large w3-hide-medium">
        <a href="greeting.ftl" class="w3-bar-item w3-button" onclick="toggleFunction()">Home</a>
        <a href="#home" class="w3-bar-item w3-button" onclick="toggleFunction()">Up</a>
    </div>
</div>

<!-- LOGIN Parallax Image -->
<div class="parallax big-img-login-1 w3-display-container w3-opacity-min" id="home">
    <div class="w3-display-middle" style="white-space:nowrap;">
        <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Login</span>
    </div>
</div>

<!-- LOGIN Container -->
<div class="w3-content w3-container w3-padding-64" id="login">
    <div class="w3-center w3-row">
        <form method="get" action="/loginError">
            <p style="color: red">
            ${validatorError!}
            </p>
        </form>

        <form method="post" action="/login">
            <p class="w3-center">Email: </p>

            <@elements.input id="login" name="username" type="email" placeholder="e m a i l . . ."
                onfocus="this.placeholder = ''"  onblur="this.placeholder = 'e m a  i l . . .'" class="login-input"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />

            <p class="w3-center">Password: </p>

            <@elements.input id="password" name="password" type="password" placeholder="p a s s w o r d . . ."
                onfocus="this.placeholder = ''"  onblur="this.placeholder = 'p a s s w o r d . . .'" class="login-input"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />

            <br>

            <button type="submit" class="btn btn1 w3-button w3-padding-large">Sign In</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
        </form>
        <h5>No registered yet? Create easy new
            <a href="/registration" style="text-decoration: none;">
                <b>account</b>
                <i class="fa fa-user-plus">
                </i>
            </a>
            !
        </h5>
    </div>
</div>

<!-- LOGIN Parallax Image -->
<div class="parallax big-img-login-1 w3-display-container w3-opacity-min">

</div>

<@footer.footer>
</@footer.footer>

</body>
</html>