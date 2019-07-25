<html>
<head>
    <title>W e l c o m e</title>
    <link rel="shortcut icon" type="image/png" href="https://cdn0.iconfinder.com/data/icons/basic-outline/64/icon-basic-set_12-camera-512.png">

    <link rel="shortcut icon" type="image/png" href="https://cdn0.iconfinder.com/data/icons/basic-outline/64/icon-basic-set_12-camera-512.png">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/css/buttons.css">
    <link rel="stylesheet" href="/static/css/photos.css">
    <link rel="stylesheet" href="/static/css/text.css">
    <script type="text/javascript" src="/static/javascript/js.js"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

<!-- NavBar (sit on top) -->
<div class="w3-top">
    <div class="w3-bar" id="myNavBar">
        <a class="w3-bar-item w3-button w3-hover-black w3-hide-medium w3-hide-large w3-right" href="javascript:void(0);" onclick="toggleFunction()" title="Toggle Navigation Menu">
            <i class="fa fa-bars"></i>
        </a>
        <a href="/" class="w3-bar-item w3-button"><i class="fa fa-home"></i>HOME</a>
        <a href="#home" class="w3-bar-item w3-button"><i class="fa fa-chevron-up"></i>Up</a>
        <a href="#about" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-user"></i> ABOUT</a>
        <a href="#portfolio" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-th"></i> PORTFOLIO</a>
        <a href="#contact" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-envelope"></i> CONTACT</a>
        <a href="/main" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-envelope"></i> MESSAGES</a>
        <a href="/main_aim" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-envelope"></i> AIMS</a>
        <a href="/user" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-envelope"></i> AIMS</a>
        <a href="/login" class="w3-bar-item w3-button w3-hide-small w3-right w3-hover-red"><i class="fa fa-sign-in"></i></a>
        <a href="#" class="w3-bar-item w3-button w3-hide-small w3-right w3-hover-red">
        <i class="fa fa-search"></i>
        </a>
    </div>

    <!-- NavBar on small screens -->
    <div id="navDemo" class="w3-bar-block w3-white w3-hide w3-hide-large w3-hide-medium">
        <a href="#about" class="w3-bar-item w3-button" onclick="toggleFunction()">ABOUT</a>
        <a href="#portfolio" class="w3-bar-item w3-button" onclick="toggleFunction()">PORTFOLIO</a>
        <a href="#contact" class="w3-bar-item w3-button" onclick="toggleFunction()">CONTACT</a>
        <a href="main.ftl" class="w3-bar-item w3-button"><i class="fa fa-envelope"></i> MESSAGES</a>
        <a href="/login" class="w3-bar-item w3-button w3-right w3-hover-red"><i class="fa fa-sign-in"></i></a>

        <a href="#" class="w3-bar-item w3-button">SEARCH</a>
    </div>
</div>

<!-- First Parallax Image with Text -->
<div class="parallax big-img-greeting-1 w3-display-container w3-opacity-min" id="home">
    <div class="w3-display-middle" style="white-space:nowrap;">
        <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Roman Drohobytskyi</span>
    </div>
</div>

<!-- ABOUT ME Section -->
<div class="w3-content w3-container w3-padding-64" id="about">
    <h3 class="w3-center w3-black ">ABOUT ME</h3>
    <p class="w3-center article"><em>I love photography</em></p>
    <p class="article-text">
        What is Lorem Ipsum?
        Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's
        standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make
        a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting,
        remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing
        Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions
        of Lorem Ipsum
    </p>
    <div class="w3-row">
        <div class="w3-col m6 w3-center w3-padding-large">
            <img id="myselfPhoto" src="https://pp.userapi.com/c849432/v849432316/b6f85/N5R2bjbNqJE.jpg" class="w3-round w3-image w3-opacity w3-hover-opacity-off" onclick="onClick(this); setMaxHeightAndWight('myselfPhoto');" alt="Photo of Me" width="100%">
            <p><b><i class="fa fa-user w3-margin-right"></i>Roman Drohobytskyi</b></p><br>

        </div>

        <!-- Hide this text on small devices -->
        <div class="w3-col m6 w3-hide-small w3-padding-large">
            <p class="article-text">
                Why do we use it?
                It is a long established fact that a reader will be distracted by the readable content of a page when
                looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution
                of letters, as opposed to using 'Content here, content here', making it look like readable English.
                Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text,
                and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have
                evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).
            </p>
        </div>
    </div>
    <p class="w3-large w3-center w3-padding-16">Im really good at:</p>
    <p class="w3-wide"><i class="fa fa-camera"></i>Photography</p>
    <div class="w3-light-grey">
        <div class="w3-container w3-padding-small w3-dark-grey w3-center" style="width:90%">90%</div>
    </div>
    <p class="w3-wide"><i class="fa fa-laptop"></i>Web Design</p>
    <div class="w3-light-grey">
        <div class="w3-container w3-padding-small w3-dark-grey w3-center" style="width:85%">85%</div>
    </div>
    <p class="w3-wide"><i class="fa fa-photo"></i>PhotoShop</p>
    <div class="w3-light-grey">
        <div class="w3-container w3-padding-small w3-dark-grey w3-center" style="width:75%">75%</div>
    </div>
</div>

<div class="w3-row w3-center w3-dark-grey w3-padding-16">
    <div class="w3-quarter w3-section">
        <span class="w3-xlarge">14+</span><br>
        Text
    </div>
    <div class="w3-quarter w3-section">
        <span class="w3-xlarge">55+</span><br>
        Text
    </div>
    <div class="w3-quarter w3-section">
        <span class="w3-xlarge">89+</span><br>
        Text
    </div>
    <div class="w3-quarter w3-section">
        <span class="w3-xlarge">150+</span><br>
        Text
    </div>
</div>

<!-- Second Parallax Image with Portfolio Text -->
<div class="parallax big-img-greeting-2 w3-display-container w3-opacity-min">
    <div class="w3-display-middle">
        <span class="w3-xxlarge w3-text-white w3-wide">PORTFOLIO</span>
    </div>
</div>

<!-- MY PROJECTS (Portfolio Section) -->
<div class="w3-content w3-container w3-padding-64" id="portfolio">

    <#--PROJECTS div-->
    <h3 class="w3-center">MY PROJECTS</h3>
    <p class="w3-center"><em>This`s my programing projects.<br> Click on the images to make them bigger</em></p><br>

    <!-- Responsive Grid. Four columns on tablets, laptops and desktops. Will stack on mobile devices/small screens (100% width) -->
    <div class="w3-row-padding w3-center">
        <div class="w3-col m3">
            <img src="http://198.211.104.161/wp-content/uploads/2014/01/Hibernate-logo.png" style="width:100%" onclick="onClick(this)" class="w3-hover-opacity" alt="Hibernate project.">
        </div>

        <div class="w3-col m3">
            <img src="https://www.openhab.org/logos/jdbc.png" style="width:100%" onclick="onClick(this)" class="w3-hover-opacity" alt="JDBC project">
        </div>

        <div class="w3-col m3">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTB_yCK06ouPnYKZg92oUPTBZtw80NTQ2K_VVisGQaM6BGCqrMg" style="width:100%" onclick="onClick(this)" class="w3-hover-opacity" alt="University project">
        </div>

        <div class="w3-col m3">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTB_yCK06ouPnYKZg92oUPTBZtw80NTQ2K_VVisGQaM6BGCqrMg" style="width:100%" onclick="onClick(this)" class="w3-hover-opacity" alt="Android project">
        </div>
        <a href="/projects"> <button class="btn btn1 w3-button w3-padding-large">See more projects</button></a>
    </div>

    <#--PHOTOS div-->
    <div class="w3-row-padding w3-center w3-section">
        <h3 class="w3-center">MY PHOTOS</h3>


        <#--
        <div class="w3-center w3-row slider-show middle">

            <div class="slides">
                <input type="radio" name="r" id="r1" checked>
                <input type="radio" name="r" id="r2">
                <input type="radio" name="r" id="r3">
                <input type="radio" name="r" id="r4">
                <input type="radio" name="r" id="r5">

                <div class="slide s1">
                    <img src="https://pp.userapi.com/c847221/v847221064/126611/7O_a9n533XI.jpg" alt="" style="width:100%" onclick="onClick(this)" class="w3-hover-opacity">
                </div>
                <div class="slide">
                    <img src="https://pp.userapi.com/c637317/v637317807/2460a/TLFfXV15QX0.jpg" alt="" style="width:100%" onclick="onClick(this)" class="w3-hover-opacity">
                </div>
                <div class="slide">
                    <img src="https://pp.userapi.com/c637122/v637122807/2cd71/fOOd2agzTSs.jpg" alt="" style="width:100%" onclick="onClick(this)" class="w3-hover-opacity">
                </div>
                <div class="slide">
                    <img src="https://pp.userapi.com/c637122/v637122807/2cd71/fOOd2agzTSs.jpg" alt="" style="width:100%" onclick="onClick(this)" class="w3-hover-opacity">
                </div>
                <div class="slide">
                    <img src="https://pp.userapi.com/c637122/v637122807/2cd71/fOOd2agzTSs.jpg" alt="" style="width:100%" onclick="onClick(this)" class="w3-hover-opacity">
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
        -->
        <a href="/photos"> <button class="btn btn2 w3-button w3-padding-large" >See more photos</button></a>
    </div>
</div>

<!-- Modal for full size images on click-->
<div id="modal01" class="w3-modal w3-black" onclick="this.style.display='none'">
    <span class="w3-button w3-large w3-black w3-display-topright" title="Close Modal Image"><i class="fa fa-remove"></i></span>
    <div class="w3-modal-content w3-animate-zoom w3-center w3-transparent w3-padding-64">
        <img id="img01" class="w3-image">
        <p id="caption" class="w3-opacity w3-large"></p>
    </div>
</div>

<!-- Third Parallax Image with Portfolio Text -->
<div class="parallax big-img-greeting-3 w3-display-container w3-opacity-min">
    <div class="w3-display-middle">
        <span class="w3-xxlarge w3-text-white w3-wide">CONTACT</span>
    </div>
</div>

<!-- Contact (Contact Section) -->
<div class="w3-content w3-container w3-padding-64" id="contact">
    <h3 class="w3-center">Contacts</h3>
    <div class="w3-row w3-padding-32 w3-section">
        <div class="w3-col m4 w3-container">
            <img src="https://upload.wikimedia.org/wikipedia/commons/f/f2/Poland_and_Ukraine.png" class="w3-image w3-round" style="width:100%">
        </div>
        <div class="w3-col m8 w3-panel">
            <div class="w3-large w3-margin-bottom">
                <i class="fa fa-map-marker fa-fw w3-hover-text-black w3-xlarge w3-margin-right"></i> Lublin/PL, Lviv/UA<br>
                <i class="fa fa-phone fa-fw w3-hover-text-black w3-xlarge w3-margin-right"></i> Phone: +570343789<br>
                <i class="fa fa-envelope fa-fw w3-hover-text-black w3-xlarge w3-margin-right"></i> Email: roman.drohobytskyi@mail.com<br>
            </div>
            <p>Swing by for a cup of <i class="fa fa-coffee"></i>, or leave me a note:</p>
            <form action="/action_page.php" target="_blank">
                <div class="w3-row-padding" style="margin:0 -16px 8px -16px">
                    <div class="w3-half">
                        <input class="w3-input w3-border" type="text" placeholder="Name" required name="Name">
                    </div>
                    <div class="w3-half">
                        <input class="w3-input w3-border" type="text" placeholder="Email" required name="Email">
                    </div>
                </div>
                <input class="w3-input w3-border" type="text" placeholder="Message" required name="Message">
                <button class="w3-right w3-section btn btn1" type="submit">
                    <i class="fa fa-paper-plane"></i>
                        SEND MESSAGE
                </button>
            </form>
        </div>
    </div>
</div>

<!-- Footer -->
<#import "parts/footer.ftl" as footer>
<@footer.footer>
</@footer.footer>

</body>
</html>