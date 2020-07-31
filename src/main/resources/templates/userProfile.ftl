<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>P r o f i l e</title>
    <link rel="shortcut icon" type="image/png" href="https://i.ya-webdesign.com/images/google-png-icons.png">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/css/buttons.css">
    <link rel="stylesheet" href="/static/css/login.css">
    <link rel="stylesheet" href="/static/css/text.css">
    <script type="text/javascript" src="/static/javascript/js.js"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/static/css/menu.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <#import "parts/menu.ftl" as menu>
    <#import "parts/footer.ftl" as footer>
    <#import "parts/elements.ftl" as elements>
</head>
<body>

    <style>
        img {
            border-radius: 10px;
        }
    </style>
    <@menu.customMenu menuElements slideMenuElements/>

    <div class="parallax big-img-login-1 w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">
                ${user.firstName + ' ' + user.lastName}
            </span>
        </div>
    </div>

    <div class="w3-content w3-container w3-padding-32" id="userProfile">
        <div class="w3-center w3-row">
            <form action="/userProfile/save" method="post" enctype="multipart/form-data">
                <p class="w3-center">Email: ${user.email}</p>
                <div class="w3-center" style="">
                    <#if user.avatar?has_content>
                        <img src="/img/${user.avatar}" style="width: 300px;height: 337px;">
                    <#else>
                        <p style="color: gray;">
                            <strong>No image</strong>
                        </p>
                    </#if>
                </div>
                <p class="w3-center">Change avatar: </p>
                <input type="file" name="avatar" placeholder="avatar . . ." />

                <p class="w3-center">First name</p>
                <@elements.input id="firstname" name="firstName" type="text" value="${user.firstName}"/>
                <p class="w3-center">Last name</p>
                <@elements.input id="lastname" name="lastName" type="text" value="${user.lastName}"/>
                <p class="w3-center">Username</p>
                <@elements.input id="username" name="username" type="text" value="${user.username}"/>
                <#if user.isActive()>
                    <p class="w3-center"><b>Account is active</b></p>
                <#else>
                    <p class="w3-center" style="color: red;">Account is not active</p>
                </#if>
                <input type="hidden" value="${user.id}" name="userId">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit" class="btn btn1 w3-button w3-padding-large">Save changes</button>
            </form>
        </div>
    </div>

    <div class="parallax big-img-login-1 w3-display-container w3-opacity-min" id="footer">
    </div>

    <@footer.footer/>
</body>
</html>