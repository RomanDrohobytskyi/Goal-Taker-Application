<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>U s e r s </title>
    <link rel="shortcut icon" type="image/png" href="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Group_font_awesome.svg/1024px-Group_font_awesome.svg.png">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/css/buttons.css">
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
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">List of Users</span>
        </div>
    </div>

    <!-- User ListContainer -->
    <div class="w3-content w3-container w3-padding-64" id="add-message">

        <h3 class="w3-center lucida-console" style="font-size:40px;">Users</h3>

        <div class="w3-center w3-row">
            <table align="center" width="100%" style="margin-top:30px">
                    <tr>
                        <th>Email</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Role</th>
                        <th>Edit User</th>
                    </tr>
                    <tbody>
                    <#list users as user>
                        <tr style="height: 120px">
                            <td>
                                ${user.email}
                            </td>
                            <td>
                                ${user.firstName}
                            </td>
                            <td>
                                ${user.lastName}
                            </td>
                            <td><#list user.roles as role>
                                ${role}<#sep>,
                            </#list>
                            </td>
                            <td>
                                <a href="/user/${user.id}"> <button class="small-btn btn2 w3-button w3-padding-large">Edit</button></a>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
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
