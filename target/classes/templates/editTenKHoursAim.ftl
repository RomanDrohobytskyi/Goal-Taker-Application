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
    <#import "parts/menu.ftl" as menu>
</head>
<body>

    <@menu.customMenu menuElements slideMenuElements/>

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
                    <i class="w3-center">Title</i>
                    <br>
                    <input id="aim_title${aim.id}" class="w3-center" type="text" name="title" value="${aim.title}" style="margin: 20px;">
                    <br>
                    <i class="w3-center">Description</i>
                    <br>
                    <input id="aim_description${aim.id}" class="w3-center" type="text" name="description" value="${aim.description}"
                           style="margin: 20px;">
                    <br>
                    <i class="w3-center">Text</i>
                    <br>
                    <textarea id="aim_text${aim.id}" name="text" rows="2" cols="21"
                              style="margin: 20px; text-align: center; width: 250px;">
                        ${aim.text}
                    </textarea>
                </label>
                <br>

                <input type="hidden" value="${aim.id}" name="aimId">
                <input type="hidden" value="${_csrf.token}" name="_csrf">
                <br>
                <button type="submit" onclick="return validateLength('aim_title${aim.id}', 3, 32)"
                        class="small-btn btn2 w3-button w3-padding-large">
                    Save
                </button>

                <form action="/editAim/cancel" method="post" style="margin: 30px 10px 10px">
                    <button type="submit" class="small-btn btn1 w3-button w3-padding-large">
                        Cancel
                    </button>
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
