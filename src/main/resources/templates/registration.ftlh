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

    <#import "parts/menu.ftlh" as menu>
    <#import "parts/footer.ftlh" as footer>
    <#import "parts/elements.ftlh" as elements>
</head>
<body>

    <@menu.loginMenu/>

    <div class="parallax big-img-login-1 w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Registration</span>
        </div>
    </div>

    <!-- REGISTRATION Container -->
    <div class="w3-content w3-container w3-padding-32" id="registration">
        <div class="w3-center w3-row">
            <h3 class="w3-center">Registration</h3>
            Create Your new account!
                <p style="color: red">
                    ${message!}
                </p>

            <form action="/registration" method="post">
                <#assign isUserPresent = user?has_content>
                <p class="w3-center">Email: </p>
                <input class="registration-input" type="email" name="email" value="${isUserPresent?then('${user.email}', '')}"/>
                <p style="color: red">
                    ${userExist!}
                </p>
                <p style="color: red">
                    ${emailIsEmpty!}
                </p>

                <p class="w3-center">User Name: </p>
                <input class="registration-input" type="text" name="username" value="${isUserPresent?then('${user.username}', '')}"/>

                <p class="w3-center">First Name: </p>
                <input class="registration-input" type="text" name="firstName"  value="${isUserPresent?then('${user.firstName}', '')}"/>

                <p class="w3-center">Last Name: </p>
                <input class="registration-input" type="text" name="lastName"  value="${isUserPresent?then('${user.lastName}', '')}"/>

                <p class="w3-center">Enter password: </p>
                <input class="registration-input" type="password" name="password"/>

                <p class="w3-center">Confirm password: </p>
                <input class="registration-input" type="password" name="passwordConfirm"/>

                <p style="color: red">
                    ${passwordNotMach!}
                </p>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit" class="btn btn1 w3-button">Create</button>
                <h5>Already registered? <a href="/login"><b>Sing In!</b></a></h5>
            </form>
            <br>
            <#if user?has_content && user.email?has_content>
                <div>
                    <form action="/registration/resendVerificationCode">
                        <#--<a href="/registration/resendVerificationCode/${user.email}">
                            <h5>Resend activation code.</h5>
                        </a>-->
                        <button type="submit" class="btn btn1 w3-button">Resend</button>
                        <input type="hidden" name="email" value="${user.email}" />
                        <input type="hidden" value="${_csrf.token}" name="_csrf">
                    </form>
                </div>
            </#if>
        </div>
    </div>
    <div class="parallax big-img-login-1 w3-display-container w3-opacity-min"></div>
    <@footer.footer/>
</body>
</html>