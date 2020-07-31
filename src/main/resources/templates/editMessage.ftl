<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>E d i t</title>
    <link rel="shortcut icon" type="image/png" href="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Group_font_awesome.svg/1024px-Group_font_awesome.svg.png">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/css/buttons.css">
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

    <@menu.customMenu menuElements slideMenuElements/>

    <div class="parallax big-img-users-1 w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">List of Users</span>
        </div>
    </div>

    <!-- Message edit div -->
    <div class="w3-content w3-container w3-padding-32" id="edit-message">

        <h3 class="w3-center">E D I T</h3>

        <div class="w3-center w3-row">

            <form action="/editMessage" method="post" style="margin: 30px 10px 10px"  enctype="multipart/form-data">
                <label>
                    <input type="text" name="text" value="${message.text}">
                    <input type="text" name="tag" value="${message.tag}">
                    <input type="file" name="file" placeholder="file . . ." />
                </label>
                <input type="hidden" value="${message.id}" name="messageId">
                <input type="hidden" value="${_csrf.token}" name="_csrf">
                <br>
                <button type="submit" class="small-btn btn2 w3-button w3-padding-large">Save</button>

                <form action="/editMessage/cancel" method="post" style="margin: 30px 10px 10px">
                    <button type="submit" class="small-btn btn2 w3-button w3-padding-large">Cancel</button>
                </form>
            </form>
        </div>
    </div>

    <div class="parallax big-img-users-1 w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
        </div>
    </div>

    <@footer.footer/>

</body>
</html>
