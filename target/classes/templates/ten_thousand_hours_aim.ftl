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
    <#import "parts/aims.ftl" as aims>
    <#import "parts/charst.ftl" as charts>

</head>
<body>

    <@menu.customMenu menuElements slideMenuElements/>

    <div class="parallax big-img-old_clock w3-display-container w3-opacity-min" id="home">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">JUST CREATE</span>
        </div>
    </div>

    <div class="w3-content w3-container w3-padding-32" id="createAim">
        <h3 class="w3-center w3-black ">Create Your A I M</h3>
        <p class="w3-center article">
            <em>It`s easier than You think</em>
        </p>
        <div class="w3-center w3-row">
            <h3 class="w3-center">Create 10 000 hours AIM</h3>
            <form action="/ten_thousand_hours_aim/add" method="get" enctype="multipart/form-data">
                <div class="w3-center">
                    <div>
                        <@elements.input id="aim_title" name="title" type="text" placeholder="t i t l e . . ."
                            onfocus="this.placeholder = ''"  onblur="this.placeholder = 't i t l e . . .'"/>
                        <br><br>
                        <@elements.input id="aim_description" name="description" type="text"
                            placeholder="d e s c r i p t i o n  . . ." onfocus="this.placeholder = ''"
                        onblur="this.placeholder = 'd e s c r i p t i o n  . . .'"/>
                        <br><br>
                        <textarea id="aim_text" name="text" placeholder="t e x t  . . ." rows="2" cols="21"
                              style="text-align: center; width: 250px;"></textarea>
                        <br>
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

    <div class="parallax big-img-old_clock w3-display-container w3-opacity-min" id="third">
        <div class="w3-display-middle" style="white-space:nowrap;">
            <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">AIMS</span>
        </div>
    </div>

    <div class="w3-content w3-container w3-padding-32" id="aimsList">
        <div id="aims" class="w3-center w3-row">
            <h3 class="w3-center">All aims</h3>
            <@aims.tenThousandAim all_aims/>
        </div>
    </div>

    <div class="parallax big-img-old_clock w3-display-container w3-opacity-min" id="fourth"></div>

    <@footer.footer/>

</body>
</html>