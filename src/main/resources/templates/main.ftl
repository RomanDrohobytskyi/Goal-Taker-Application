<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>M a i n</title>
    <link rel="shortcut icon" type="image/png" href="https://cdn0.iconfinder.com/data/icons/basic-outline/64/icon-basic-set_12-camera-512.png">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/css/buttons.css">
    <script type="text/javascript" src="/static/javascript/js.js"></script>
    <script type="text/javascript" src="/static/javascript/checkbox.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

<!-- NavBar (sit on top) -->
<div class="w3-top">
    <div class="w3-bar" id="myNavBar">
        <#--<a class="w3-bar-item w3-button w3-hover-black w3-hide-medium w3-hide-large w3-right" href="javascript:void(0);" onclick="toggleFunction()" title="Toggle Navigation Menu">-->
            <#--<i class="fa fa-bars"></i>-->
        <#--</a>-->
        <a href="/" class="w3-bar-item w3-button"><i class="fa fa-home"></i>Home</a>
        <a href="#home" class="w3-bar-item w3-button"><i class="fa fa-chevron-up"></i>Up</a>
        <a href="/main_aim" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-envelope"></i> AIMS</a>
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
        <a href="greeting.ftl" class="w3-bar-item w3-button" onclick="toggleFunction()">Home</a>
        <a href="#home" class="w3-bar-item w3-button" onclick="toggleFunction()">Up</a>
        <a href="#" class="w3-bar-item w3-button">SEARCH</a>
    </div>
</div>

<!-- LOGIN Parallax Image -->
<div class="parallax big-img-login-1 w3-display-container w3-opacity-min" id="home">
    <div class="w3-display-middle" style="white-space:nowrap;">
        <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Main</span>
    </div>
</div>

<!-- Add messages Container -->
<div class="w3-content w3-container w3-padding-64" id="add-message">

    <div class="w3-center w3-row">
        <h3 class="w3-center">Create message</h3>
        <div>
            <span>Click on a link to see <a href="/user" style="color:cornflowerblue"> list of Users</a>!</span>
        </div>
        <form method="post" enctype="multipart/form-data">
            <p class="w3-center">Enter message: </p>
            <input type="text" name="text" placeholder="text . . ."/>
            <p class="w3-center">Tag: </p>
            <input type="text" name="tag" placeholder="tag  . . ."/>

            <p class="w3-center">Choose file to add: </p>
            <input type="file" name="file" placeholder="file . . ." />

            <br>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit" class="btn btn1 w3-button w3-padding-large">Add</button>
        </form>
    </div>
</div>
<#--Second Parallax IMG-->
<div class="parallax big-img-login-1 w3-display-container w3-opacity-min" id="home">
    <div class="w3-display-middle" style="white-space:nowrap;">
        <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Messages</span>
    </div>
</div>

<!-- Messages Container -->
<div class="w3-content w3-container w3-padding-64" id="messages">
    <div class="w3-center w3-row">
        <h3 class="w3-center">All messages</h3>

        <div id="menu">
            <i id="trash" class="fa fa-trash-o" aria-hidden="true"></i>
        </div>

        <form method="get" action="/main">
            <p class="w3-center">
                Filter:
            </p>
            <input id="filter" type="text" name="filter" placeholder="filter . . ." value="${filter!}"  />


            <p class="fa fa-times-circle" onclick="document.getElementById('filter').value = ''"> </p>
            <br>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit" class="btn btn1 w3-button w3-padding-large">Find</button>

        </form>
        <table id="messagesTable" align="center" width="100%">
            <tr>
                <th>ID</th>
                <th>Message</th>
                <th>Filter</th>
                <th>Email</th>
                <th>Img</th>
                <th>
                    <input type="checkbox" title="Check All Messages" onclick="selectDeselectAll('#messageCheckbox')"/>
                </th>
            </tr>
        <#list messages as message>
            <tr style="height: 100px">
                <td><b>${message.id}</b></td>
                <td><span>${message.text}</span></td>
                <td><i>${message.tag}</i></td>
                <td><strong>${message.getAuthorEmail()}</strong></td>
                <td>
                    <div >
                        <#if message.filename?has_content>
                            <img src="/img/${message.filename}" style="width:130px;height:100px;">
                        <#else >
                            <p style="color: red">
                                <strong>No messages!</strong>
                            </p>
                        </#if>
                    </div>
                </td>
                <td>
                    <div>
                        <input id="messageCheckbox" class="checkbox" type="checkbox" title="Check"/>
                    </div>
                </td>
            </tr>
        </#list>
        </table>
    </div>
</div>

<#--Footer-->
<#import "parts/footer.ftl" as footer>
<@footer.footer>
</@footer.footer>

</body>
</html>
