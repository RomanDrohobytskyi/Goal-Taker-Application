<html>
<head>
    <title>W e l c o m e</title>
    <link rel="shortcut icon" type="image/png" href="https://cdn0.iconfinder.com/data/icons/basic-outline/64/icon-basic-set_12-camera-512.png">

    <link rel="shortcut icon" type="image/png" href="https://cdn0.iconfinder.com/data/icons/basic-outline/64/icon-basic-set_12-camera-512.png">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/css/buttons.css">
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
        <a href="#home" class="w3-bar-item w3-button"><i class="fa fa-home"></i>HOME</a>
        <a href="#about" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-user"></i> ABOUT</a>
        <a href="#portfolio" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-th"></i> PORTFOLIO</a>
        <a href="#contact" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-envelope"></i> CONTACT</a>
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
        <a href="/login" class="w3-bar-item w3-button w3-hide-small w3-right w3-hover-red"><i class="fa fa-sign-in"></i></a>

        <a href="#" class="w3-bar-item w3-button">SEARCH</a>
    </div>
</div>

<!-- First Parallax Image with Text -->
<div class="big-img-index-1 w3-display-container w3-opacity-min" id="home">
    <div class="w3-display-middle" style="white-space:nowrap;">
        <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">Roman Drohobytskyi</span>
    </div>
</div>

<!-- ABOUT ME Section -->
<div class="w3-content w3-container w3-padding-64" id="about">
    <h3 class="w3-center">ABOUT ME</h3>
    <p class="w3-center"><em>I love photography</em></p>
    <p>First article.</p>
    <div class="w3-row">
        <div class="w3-col m6 w3-center w3-padding-large">
            <p><b><i class="fa fa-user w3-margin-right"></i>My Name</b></p><br>
            <img src="https://pp.userapi.com/c849432/v849432316/b6f85/N5R2bjbNqJE.jpg" class="w3-round w3-image w3-opacity w3-hover-opacity-off" alt="Photo of Me" width="500" height="333">
        </div>

        <!-- Hide this text on small devices -->
        <div class="w3-col m6 w3-hide-small w3-padding-large">
            <p>Second article.</p>
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
<div class="big-img-index-2 w3-display-container w3-opacity-min">
    <div class="w3-display-middle">
        <span class="w3-xxlarge w3-text-white w3-wide">PORTFOLIO</span>
    </div>
</div>

<!-- MY PROJECTS (Portfolio Section) -->
<div class="w3-content w3-container w3-padding-64" id="portfolio">

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
        <a href="/main"> <button class="btn btn1" style="margin-top:64px">See more projects</button></a>
    </div>

    <div class="w3-row-padding w3-center w3-section">
        <h3 class="w3-center">MY PHOTOS</h3>
        <div class="w3-col m3">
            <img src="https://pp.userapi.com/c637122/v637122807/2cd71/fOOd2agzTSs.jpg" style="width:100%" onclick="onClick(this)" class="w3-hover-opacity" alt="The mist">
        </div>

        <div class="w3-col m3">
            <img src="https://pp.userapi.com/c637122/v637122807/2cd71/fOOd2agzTSs.jpg" style="width:100%" onclick="onClick(this)" class="w3-hover-opacity" alt="My beloved typewriter">
        </div>

        <div class="w3-col m3">
            <img src="https://pp.userapi.com/c637122/v637122807/2cd71/fOOd2agzTSs.jpg" style="width:100%" onclick="onClick(this)" class="w3-hover-opacity" alt="Empty ghost train">
        </div>

        <div class="w3-col m3">
            <img src="https://pp.userapi.com/c637122/v637122807/2cd71/fOOd2agzTSs.jpg" style="width:100%" onclick="onClick(this)" class="w3-hover-opacity" alt="Sailing">
        </div>
        <a href="/main"> <button class="btn btn1" style="margin-top:64px">See more photos</button></a>
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
<div class="big-img-index-3 w3-display-container w3-opacity-min">
    <div class="w3-display-middle">
        <span class="w3-xxlarge w3-text-white w3-wide">CONTACT</span>
    </div>
</div>

<!-- Contact (Contact Section) -->
<div class="w3-content w3-container w3-padding-64" id="contact">
    <h3 class="w3-center">WHERE I LIVE</h3>
    <p class="w3-center"><em>I'd love your feedback!</em></p>

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
                <button class="w3-button w3-black w3-right w3-section btn btn1" type="submit">
                    <i class="fa fa-paper-plane"></i> SEND MESSAGE
                </button>
            </form>
        </div>
    </div>
</div>

<!-- Footer -->
<footer class="w3-center w3-black w3-padding-64 w3-opacity w3-hover-opacity-off">
    <a href="#home" class="w3-button w3-light-grey"><i class="fa fa-arrow-up w3-margin-right"></i>To the top</a>
    <div class="w3-xlarge w3-section">
        <a href = "https://www.facebook.com/roman.drohobytskyi" target="_blank" class = "fa fa-facebook-official w3-hover-opacity"></a>
        <a href = "https://www.instagram.com/roma_drohobytskiy/" target="_blank" class = "fa fa-instagram w3-hover-opacity"> </a>
        <a href = "https://github.com/RomanDrohobytskyi" target="_blank" class = "fa fa-git w3-hover-opacity"></a>
        <a href = "https://www.linkedin.com/in/roman-drohobytskyi-4b8515163/" target="_blank" class = "fa fa-linkedin w3-hover-opacity"></a>
    </div>
    <p><i class="fa fa-user-o" aria-hidden="true"> Roman Drohobytskyi</i></p>
</footer>

</body>
</html>






<#--<!DOCTYPE html>-->
<#--<html xmlns="http://www.w3.org/1999/xhtml">-->
<#--<head>-->
    <#--<title>Spring Security Example </title>-->
    <#--<link rel="stylesheet" href="/static/css/style.css">-->
    <#--<link rel="stylesheet" href="/static/css/buttons.css">-->
    <#--<script type="text/javascript" src="/static/javascript/js.js"></script>-->
<#--</head>-->
<#--<body>-->
<#--<div>Hello, user</div>-->
<#--<a href="/main">Main page</a>-->
<#--</body>-->
<#--</html>-->


