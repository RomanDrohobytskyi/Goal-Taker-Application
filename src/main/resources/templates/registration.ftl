<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="shortcut icon" type="image/png" href="https://cdn0.iconfinder.com/data/icons/basic-outline/64/icon-basic-set_12-camera-512.png">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/css/buttons.css">
    <link rel="stylesheet" href="/static/css/registration.css">
    <link rel="stylesheet" href="/static/css/text.css">
    <script type="text/javascript" src="/static/javascript/js.js"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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

<!-- REGISTRATION Parallax Image -->
<div class="parallax big-img-login-1 w3-display-container w3-opacity-min" id="home">
    <div class="w3-display-middle" style="white-space:nowrap;">
        <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Registration</span>
    </div>
</div>

<!-- REGISTRATION Container -->
<div class="w3-content w3-container w3-padding-64" id="registration">

    <div class="w3-center w3-row">
        <h3 class="w3-center">Registration</h3>
        <p>Create Your new account!
            <p style="color: red">
                ${message!}
            </p>

        <form action="/registration" method="post">
            <p class="w3-center lucida-console">Email: </p>
            <input class="registration-input" type="email" name="email" placeholder="email"/>
            <p style="color: red">
            ${userExist!}
            </p>
            <p style="color: red">
            ${emailIsEmpty!}
            </p>
            <p class="w3-center lucida-console">Create User Name: </p>
            <input class="registration-input" type="text" name="username" placeholder="username"/>

            <p class="w3-center lucida-console">First Name: </p>
            <input class="registration-input" type="text" name="firstName" placeholder="first name"/>

            <p class="w3-center lucida-console">Last Name: </p>
            <input class="registration-input" type="text" name="lastName" placeholder="last name"/>

            <p class="w3-center lucida-console">Enter password: </p>
            <input class="registration-input" type="password" name="password" placeholder="password"/>
            <p class="w3-center lucida-console">Confirm password: </p>
            <input class="registration-input" type="password" name="passwordConfirm" placeholder="confirm password"/>
            <p style="color: red">
            ${passwordNotMach!}
            </p>
            <br>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit" class="btn btn1 w3-button w3-padding-large">Create</button>
            <h5>Already registered?<a href="/login"><b>Sing In!</b></a>!</h5>
        </form>

    </div>
</div>
<#--Footer-->
<#import "parts/footer.ftl" as footer>
<@footer.footer>
</@footer.footer>

</body>
</html>
