<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>N o t e s</title>
    <link rel="shortcut icon" type="image/png" href="https://cdn0.iconfinder.com/data/icons/basic-outline/64/icon-basic-set_12-camera-512.png">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/css/buttons.css">
    <link rel="stylesheet" href="/static/css/text.css">
    <script type="text/javascript" src="/static/javascript/js.js"></script>
    <script type="text/javascript" src="/static/javascript/checkbox.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/static/css/menu.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <#import "parts/menu.ftlh" as menu>
    <#import "parts/footer.ftlh" as footer>
    <#import "parts/elements.ftlh" as elements>
    <#import "parts/aims.ftlh" as aims>

</head>
<body>

    <@menu.customMenu menuElements slideMenuElements/>

    <div class="parallax big-img-messages w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Notes</span>
        </div>
    </div>

    <!-- Add new note Container -->
    <div class="w3-content w3-container w3-padding-32" id="add-message">
        <div class="w3-center w3-row">
            <h3 class="w3-center">Create note</h3>
            <form action="/main/add" method="post" enctype="multipart/form-data">
                <p class="w3-center">Enter note title: </p>
                <@elements.input id="title" name="text" type="text" placeholder="t e x t . . ."
                onfocus="this.placeholder = ''"  onblur="this.placeholder = 't e x t . . .'"/>

                <p class="w3-center">Tag: </p>
                 <@elements.input id="tag" name="tag" type="text" placeholder="t a g . . ."
                 onfocus="this.placeholder = ''"  onblur="this.placeholder = 't a g . . .'"/>

                <p class="w3-center">Choose file to add: </p>
                <input type="file" name="file" placeholder="file . . ." />

                <br>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit" class="btn btn1 w3-button w3-padding-large">Add</button>
            </form>
        </div>
    </div>

    <#--Second Parallax IMG-->
    <div class="parallax big-img-messages w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Notes</span>
        </div>
    </div>

    <!-- Messages Container -->
    <div class="w3-content w3-container w3-padding-32" id="messages">
        <div id="messages" class="w3-center w3-row">
            <h3 class="w3-center">All notes</h3>
            <div id="menu">
                <div class="form-item form-type-textfield form-item-count-checked-checkboxes">
                    <label for="edit-count-checked-checkboxes" class="default-margin">
                        <span class="form-required">Selected messages</span>
                    </label>
                    <br>
                    <form method="post" action="/main/deleteMessages">
                        <div class="count-checkboxes-wrapper">
                            <span id="count-checked-checkboxes" title="Selected notes count">0</span>
                            <button type="submit" name="deleteMessage"  title="Delete selected notes" style="border: none;" class="fa fa-trash-o">
                            </button>
                            <br>
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        </div>
                    </form>
                </div>
            </div>

            <form method="get" action="/main">
                <p class="w3-center">
                    Find note by filter
                </p>
                <@elements.input id="filter" value="${filter!}" name="filter" type="text" placeholder="f i l t e r . . ."
                onfocus="this.placeholder = ''"  onblur="this.placeholder = 't i t l e . . .'"/>

                <p class="fa fa-times-circle" onclick="document.getElementById('filter').value = ''"> </p>
                <br>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit" class="btn btn1 w3-button w3-padding-large" style="margin-bottom: 30px;">Find</button>
                <br>
            </form>
            <div>
                <@aims.notes messages/>
            </div>
        </div>
    </div>

    <div class="parallax big-img-messages-small w3-display-container w3-opacity-min" id="home">
    </div>

    <@footer.footer/>
</body>
</html>
