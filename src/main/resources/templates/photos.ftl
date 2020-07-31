<html>
<head>
    <title>W e l c o m e</title>
    <link rel="shortcut icon" type="image/png" href="https://cdn0.iconfinder.com/data/icons/basic-outline/64/icon-basic-set_12-camera-512.png">

    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/css/buttons.css">
    <link rel="stylesheet" href="/static/css/photos.css">
    <script type="text/javascript" src="/static/javascript/js.js"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/static/css/menu.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <#import "parts/menu.ftl" as menu>
    <#import "parts/footer.ftl" as footer>
    <#import "parts/image.ftl" as paralax>
</head>
<body>

    <@menu.customMenu menuElements slideMenuElements/>

    <@paralax.paralax id="home" image="" title="See mountains photos" text="MOUNTAINS"
    cssClass="gradient-white parallax w3-display-container w3-opacity-min gradient-blue">
    </@paralax.paralax>

    <!-- MY PHOTOS (Portfolio Section) -->
    <div class="w3-content w3-container w3-padding-32" id="portfolio">

        <div class="w3-center w3-row slider-show middle">

                <div class="slides">
                    <input type="radio" name="r" id="r1" checked>
                    <input type="radio" name="r" id="r2">
                    <input type="radio" name="r" id="r3">
                    <input type="radio" name="r" id="r4">
                    <input type="radio" name="r" id="r5">

                    <div class="slide s1">
                        <img src="https://pp.userapi.com/c845417/v845417470/1854e9/iCB1Vo-s2AQ.jpg" alt="">
                    </div>
                    <div class="slide">
                        <img src="https://pp.userapi.com/c847221/v847221064/126611/7O_a9n533XI.jpg" alt="">
                    </div>
                    <div class="slide">
                        <img src="https://pp.userapi.com/c845417/v845417470/1854b0/Wo38FNMoI08.jpg" alt="">
                    </div>
                    <div class="slide">
                        <img src="https://pp.userapi.com/c845417/v845417470/1854ba/_QV6tLFCm2Q.jpg" alt="">
                    </div>
                    <div class="slide">
                        <img src="https://pp.userapi.com/c845417/v845417470/1854c4/BF8cyTXuF5w.jpg" alt="">
                    </div>

                    <div class="navigation">
                        <label for="r1" class="bar"></label>
                        <label for="r2" class="bar"></label>
                        <label for="r3" class="bar"></label>
                        <label for="r4" class="bar"></label>
                        <label for="r5" class="bar"></label>
                    </div>
                </div>
        </div>
    </div>

    <@paralax.paralax id="secondParalax" image="" title="See mountains photos" text="MOUNTAINS"
        cssClass="gradient-white parallax w3-display-container w3-opacity-min gradient-blue">
    </@paralax.paralax>

    <!-- Modal for full size images on click-->
    <div id="modal01" class="w3-modal w3-black" onclick="this.style.display='none'">
        <span class="w3-button w3-large w3-black w3-display-topright" title="Close Modal Image"><i class="fa fa-remove"></i></span>
        <div class="w3-modal-content w3-animate-zoom w3-center w3-transparent w3-padding-32">
            <img id="img01" class="w3-image">
            <p id="caption" class="w3-opacity w3-large"></p>
        </div>
    </div>

    <@footer.footer/>
</body>
</html>