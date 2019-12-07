<html>
<head>
    <title>A L L   A I M S</title>
    <link rel="shortcut icon" type="image/png" href="https://cdn0.iconfinder.com/data/icons/tiny-icons-1/100/tiny-15-512.png">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/css/buttons.css">
    <link rel="stylesheet" href="/static/css/photos.css">
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
<style>

</style>

<!-- NavBar (sit on top) -->
<div class="w3-top">
    <div class="w3-bar" id="myNavBar">
        <a class="w3-bar-item w3-button w3-hover-black w3-hide-medium w3-hide-large w3-right" href="javascript:void(0);" onclick="toggleFunction()" title="Toggle Navigation Menu">
            <i class="fa fa-bars"></i>
            <a href="/" class="w3-bar-item w3-button"><i class="fa fa-home"></i>HOME</a>
            <a href="#home" class="w3-bar-item w3-button"><i class="fa fa-chevron-up"></i>Up</a>
            <a href="#about" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-user"></i> ABOUT</a>
            <a href="/login" class="w3-bar-item w3-button w3-hide-small w3-right w3-hover-red"><i class="fa fa-sign-out"></i>
                <form action="/logout" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                </form>
            </a>
            <a href="#" class="w3-bar-item w3-button w3-hide-small w3-right w3-hover-red">
                <i class="fa fa-search"></i>
            </a>
        </a>

    </div>

    <!-- NavBar on small screens -->
    <div id="navDemo" class="w3-bar-block w3-white w3-hide w3-hide-large w3-hide-medium">
        <a href="#about" class="w3-bar-item w3-button" onclick="toggleFunction()">ABOUT</a>
        <a href="main.ftl" class="w3-bar-item w3-button"><i class="fa fa-envelope"></i> MESSAGES</a>
        <a href="/login" class="w3-bar-item w3-button w3-right w3-hover-red"><i class="fa fa-sign-in"></i></a>
        <a href="#" class="w3-bar-item w3-button">SEARCH</a>
    </div>
</div>

<!-- First Parallax Image with Text -->
<div class="parallax big-img-smart w3-display-container w3-opacity-min" id="home">
    <div class="w3-display-middle" style="white-space:nowrap;">
        <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">WHAT IS AIM</span>
    </div>
</div>

<!-- Definition of AIM container-->
<div class="w3-content w3-container w3-padding-64" id="about">
    <h3 class="w3-center w3-black ">AIM IS</h3>
    <p class="w3-center article"><em>Dream</em></p>
    <p class="article-text">
        How to achieve your dreams?
    </p>
    <div class="w3-row">
        <div class="w3-col m6 w3-center w3-padding-large">
            <img src=https://3.bp.blogspot.com/-o6v3A_fmflA/WAoc16_1klI/AAAAAAAAAE4/InAvp8WNspIhIhd8-Jw5SEDiL83zSQPBQCLcB/s1600/aim%2B1.png"
                 class="big-img-small-smart w3-round w3-image w3-opacity w3-hover-opacity-off"
                 onclick="onClick(this)" alt="Photo of Me" width="100%">
            <p>
                <b>Achieve your dream</b>
            </p>
            <br>
        </div>

        <!-- Hide this text on small devices -->
        <div class="w3-col m6 w3-hide-small w3-padding-large">
            <p class="article-text">
                Why do we use it?
            </p>
        </div>
    </div>
</div>

<!-- Second Parallax Image with Portfolio Text -->
<div class="parallax big-img-smart w3-display-container w3-opacity-min" id="home">
    <div class="w3-display-middle" style="white-space:nowrap;">
        <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">10K HOURS</span>
    </div>
</div>

<!-- Definition of AIM container-->
<div class="w3-content w3-container w3-padding-64" id="definition">
    <h3 class="w3-center w3-black ">S.M.A.R.T</h3>
    <p class="w3-center article"><em>It`s easier than You think</em></p>
    <p class="article-text">
        What is Lorem Ipsum?
    </p>
    <div class="w3-row">
        <div class="w3-col m6 w3-center w3-padding-large">
            <img src="https://www.professionalacademy.com/media/images-news/smarter-objectives.jpg" class="big-img-small-smart w3-round w3-image w3-opacity w3-hover-opacity-off" onclick="onClick(this)" alt="Photo of Me" width="100%">
            <p>
                <b>S.M.A.R.T</b>
            </p>
            <br>
        </div>

        <!-- Hide this text on small devices -->
        <div class="w3-col m6 w3-hide-small w3-padding-large">
            <p class="article-text">
                Why do we use it?
                <a href="/main_aim"> <button class="btn btn1 w3-button w3-padding-large" style="margin: 20px 0px;" >Create Aim</button></a>
            </p>
        </div>
    </div>
</div>

<!-- First Parallax Image with Text -->
<div class="parallax big-img-smart w3-display-container w3-opacity-min" id="first">
    <div class="w3-display-middle" style="white-space:nowrap;">
        <span class="w3-center w3-padding-large w3-black w3-xlarge w3-wide w3-animate-opacity">S.M.A.R.T</span>
    </div>
</div>

<div class="w3-content w3-container w3-padding-64" id="about">
    <h3 class="w3-center w3-black ">10k </h3>
    <p class="w3-center article"><em>Hours</em></p>
    <p class="article-text">
        Work hard !
    </p>
    <div class="w3-row">
        <div class="w3-col m6 w3-center w3-padding-large">
            <img src=https://3.bp.blogspot.com/-o6v3A_fmflA/WAoc16_1klI/AAAAAAAAAE4/InAvp8WNspIhIhd8-Jw5SEDiL83zSQPBQCLcB/s1600/aim%2B1.png"
                 class="big-img-small-smart w3-round w3-image w3-opacity w3-hover-opacity-off"
                 onclick="onClick(this)" alt="Photo of Me" width="100%">
            <p>
                <b></b>
            </p>
            <br>
        </div>

        <!-- Hide this text on small devices -->
        <div class="w3-col m6 w3-hide-small w3-padding-large">
            <p class="article-text">
                Why do we use it?
            </p>
        </div>
    </div>
</div>

<div class="parallax big-img-smart w3-display-container w3-opacity-min" id="first">
</div>

<!-- Footer -->
<@footer.footer>
</@footer.footer>

</body>
</html>