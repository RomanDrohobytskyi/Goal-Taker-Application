<html>
<head>
    <title>A I M</title>
    <link rel="shortcut icon" type="image/png" href="https://cdn0.iconfinder.com/data/icons/tiny-icons-1/100/tiny-15-512.png">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/css/buttons.css">
    <link rel="stylesheet" href="/static/css/photos.css">
    <link rel="stylesheet" href="/static/css/text.css">
    <script type="text/javascript" src="/static/javascript/js.js"></script>
    <script type="text/javascript" src="/static/javascript/validators.js"></script>

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
    .fa{
        margin-right: 5px;
    }
</style>

<!-- NavBar (sit on top) -->
<div class="w3-top" id="home">
    <div class="w3-bar" id="myNavBar">
        <a class="w3-bar-item w3-button w3-hover-black w3-hide-medium w3-hide-large w3-right" href="javascript:void(0);" onclick="toggleFunction()" title="Toggle Navigation Menu">
            <i class="fa fa-bars"></i>
        </a>
        <a href="/" class="w3-bar-item w3-button"><i class="fa fa-home"></i>HOME</a>
        <a href="#home" class="w3-bar-item w3-button"><i class="fa fa-chevron-up"></i>UP</a>
        <a href="#create" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-plus"></i>CREATE</a>
        <a href="#aims" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-user"></i>AIMS</a>
        <a href="/login" class="w3-bar-item w3-button w3-hide-small w3-right w3-hover-red"><i class="fa fa-sign-out"></i>
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
        <a href="#create" class="w3-bar-item w3-button" onclick="toggleFunction()">CREATE</a>
        <a href="main.ftl" class="w3-bar-item w3-button"><i class="fa fa-envelope"></i> MESSAGES</a>
        <a href="/login" class="w3-bar-item w3-button w3-right w3-hover-red"><i class="fa fa-sign-in"></i></a>
        <a href="#" class="w3-bar-item w3-button">SEARCH</a>
    </div>
</div>

<!-- Second Parallax Image with Portfolio Text -->
<div class="parallax big-img-smart w3-display-container w3-opacity-min" id="second">
    <div class="w3-display-middle" style="white-space:nowrap;">
        <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">JUST CREATE</span>
    </div>
</div>

<!-- Create AIM container-->
<div class="w3-content w3-container w3-padding-64" id="create">
    <h3 class="w3-center w3-black ">Create Your A I M</h3>
    <p class="w3-center article">
        <em>It`s easier than You think</em>
    </p>
    <p class="article-text">
        What is Lorem Ipsum?
    </p>

    <div class="w3-center w3-row">
        <h3 class="w3-center">Create 10 000 hours AIM</h3>
        <form action="/ten_thousand_hours_aim/add" method="get" enctype="multipart/form-data">

            <div class="w3-center">

                <div style=" margin-right: 10px;">
                    <@elements.input id="aim_title" name="title" type="text" placeholder="t i t l e . . ."
                    onfocus="this.placeholder = ''"  onblur="this.placeholder = 't i t l e . . .'"/>
                    <br><br>
                <#--<p class="w3-center">d e s c r i p t i o n</p>-->
                    <@elements.input id="aim_description" name="description" type="text" placeholder="d e s c r i p t i o n  . . ."
                    onfocus="this.placeholder = ''"  onblur="this.placeholder = 'd e s c r i p t i o n  . . .'"/>
                    <br><br>
                    <textarea id="aim_text" name="text" placeholder="t e x t  . . ." rows="2" cols="21"
                              style="text-align: center; width: 250px;"></textarea>
                    <br><br>
                </div>

                <div class="w3-center" style="float:bottom">
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />

                    <button type="submit" class="btn btn1 w3-button w3-padding-large"
                            onclick="return validateLength('Title', 'aim_title', 3, 32)">
                        A d d
                    </button>
                </div>
            </div>

        </form>
    </div>

</div>

<#--Third paralax IMG-->
<div class="parallax big-img-smart w3-display-container w3-opacity-min" id="third">
    <div class="w3-display-middle" style="white-space:nowrap;">
        <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">YOUR AIMS</span>
    </div>
</div>

<#--All AIMs-->
<div class="w3-content w3-container w3-padding-64" id="messages">
    <div id="aims" class="w3-center w3-row">
        <h3 class="w3-center">All aims</h3>

        <i class="fa fa-hand-pointer-o" aria-hidden="true"><em style="margin-left: 5px;">Click on aim id to check details</em></i>

    <#-- Table of a messages -->
        <table id="aimsTable" align="center" width="100%" style="padding: 10px;/*table-layout: fixed;*/">
        <#-- Table header -->
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Description</th>
                <th>Text</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>

        <#-- All Aims -->
            <#if all_aims?has_content>
                <#list all_aims as aim>
                    <#if aim.aimState!= "DELETED">
                        <tr id="aim_${aim.id}" style="text-align:center; height: 100px">
                            <td><b>
                                <a href="/aim_details/${aim.id}" style="text-decoration:none" title="Go to details: ${aim.title}">
                                    ${aim.id}
                                </a>
                            </b></td>
                            <td><span>${aim.title}</span></td>
                            <td style="word-wrap: break-word"><i>${aim.description}</i></td>
                            <td style="word-wrap: break-word"><i>${aim.text}</i></td>
                            <td>
                                <div>
                                    <a href="/editTenKHoursAim/${aim.id}" ><i class="fa fa-pencil" aria-hidden="true"></i></a>
                                </div>
                            </td>

                            <td>
                                <div>
                                    <a href="/ten_thousand_hours_aim/delete/${aim.id}">
                                        <i class="fa fa-trash-o" aria-hidden="true" title="Delete aim"></i>
                                    </a>
                                    <input type="hidden" value="${aim}" name="aim">
                                </div>
                            </td>
                        </tr>
                    </#if>
                </#list>
            <#else>
                 <h4 class="w3-center" style="font-weight: bold;">No aims yet</h4>
            </#if>
        </table>
    </div>
</div>

<#--Fourth paralax IMG-->
<div class="parallax big-img-smart w3-display-container w3-opacity-min" id="fourth">
</div>

<!-- Footer -->
<@footer.footer>
</@footer.footer>

</body>
</html>