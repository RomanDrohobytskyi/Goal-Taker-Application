<html>
    <head>
        <title>Projects</title>
        <link rel="shortcut icon" type="image/png" href="http://cdn.onlinewebfonts.com/svg/img_272979.png">
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
                <a href="#hibernate" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-th"></i> HIBERNATE</a>
                <a href="#jdbc" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-th"></i> JDBC</a>
                <a href="#jsoup" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-th"></i> JSOUP</a>
                <a href="#spring" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-th"></i> SPRING</a>
                <a href="/logout" class="w3-bar-item w3-button w3-hide-small w3-right w3-hover-red"><i class="fa fa-sign-out"></i></a>
                <a href="#" class="w3-bar-item w3-button w3-hide-small w3-right w3-hover-red">
                    <i class="fa fa-search"></i>
                </a>
            </div>

            <!-- NavBar on small screens -->
            <div id="navDemo" class="w3-bar-block w3-white w3-hide w3-hide-large w3-hide-medium">
                <a href="#hibernate" class="w3-bar-item w3-button" onclick="toggleFunction()"> HIBERNATE</a>
                <a href="#jdbc" class="w3-bar-item w3-button" onclick="toggleFunction()"> JDBC</a>
                <a href="#jsoup" class="w3-bar-item w3-button" onclick="toggleFunction()"> JSOUP</a>
                <a href="#spring" class="w3-bar-item w3-button" onclick="toggleFunction()"> SPRING</a>
                <a href="/login" class="w3-bar-item w3-button w3-hide-small w3-right w3-hover-red"><i class="fa fa-sign-in"></i></a>

                <a href="#" class="w3-bar-item w3-button">SEARCH</a>
            </div>
        </div>

        <!-- FIRST Parallax Image with Portfolio Text -->
        <div class="parallax big-img-greeting-2 w3-display-container w3-opacity-min" id = "home">
            <div class="w3-display-middle">
                <span class="w3-xxlarge w3-text-white w3-wide">HIBERNATE</span>
            </div>
        </div>

        <#--Hibernate project DIV-->
        <div class="w3-content w3-container w3-padding-64" id="hibernate">
            <h3 class="w3-center">ABOUT ME</h3>
            <p class="w3-center"><em>I love photography</em></p>
            <p>First article.</p>
            <div class="w3-row">
                <div class="w3-col m6 w3-center w3-padding-large">
                    <p><b><i class="fa fa-user w3-margin-right"></i>Roman Drohobytskyi.</b></p><br>
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

        <!-- Second Parallax Image with Portfolio Text -->
        <div class="parallax big-img-greeting-2 w3-display-container w3-opacity-min">
            <div class="w3-display-middle">
                <span class="w3-xxlarge w3-text-white w3-wide">JDBC</span>
            </div>
        </div>


        <#--JDBC project DIV-->
        <div class="w3-content w3-container w3-padding-64" id="jdbc">
            <h3 class="w3-center">ABOUT ME</h3>
            <p class="w3-center"><em>I love photography</em></p>
            <p>First article.</p>
            <div class="w3-row">
                <div class="w3-col m6 w3-center w3-padding-large">
                    <p><b><i class="fa fa-user w3-margin-right"></i>Roman Drohobytskyi.</b></p><br>
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

        <!-- Second Parallax Image with Portfolio Text -->
        <div class="parallax big-img-greeting-2 w3-display-container w3-opacity-min">
            <div class="w3-display-middle">
                <span class="w3-xxlarge w3-text-white w3-wide">JSOUP</span>
            </div>
        </div>

        <#--JSOUP project DIV-->
        <div class="w3-content w3-container w3-padding-64" id="jsoup">
            <h3 class="w3-center">ABOUT ME</h3>
            <p class="w3-center"><em>I love photography</em></p>
            <p>First article.</p>
            <div class="w3-row">
                <div class="w3-col m6 w3-center w3-padding-large">
                    <p><b><i class="fa fa-user w3-margin-right"></i>Roman Drohobytskyi.</b></p><br>
                    <img src="https://pp.userapi.com/c849432/v849432316/b6f85/N5R2bjbNqJE.jpg" class="w3-round w3-image w3-opacity w3-hover-opacity-off" alt="Photo of Me" width="500" height="333">
                </div>

                <!-- Hide this text on small devices -->
                <div class="w3-col m6 w3-hide-small w3-padding-large">
                    <p>Second article.</p>
                </div>
            </div>
            <p class="w3-large w3-center w3-padding-16">Technologies:</p>
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

        <!-- Second Parallax Image with Portfolio Text -->
        <div class="parallax big-img-greeting-2 w3-display-container w3-opacity-min">
            <div class="w3-display-middle">
                <span class="w3-xxlarge w3-text-white w3-wide">SPRING</span>
            </div>
        </div>

        <#-- SPRING project DIV-->
        <div class="w3-content w3-container w3-padding-64" id="spring">
            <h3 class="w3-center">ABOUT ME</h3>
            <p class="w3-center"><em>I love photography</em></p>
            <p>First article.</p>
            <div class="w3-row">
                <div class="w3-col m6 w3-center w3-padding-large">
                    <p><b><i class="fa fa-user w3-margin-right"></i>Roman Drohobytskyi.</b></p><br>
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

        <!-- Modal for full size images on click-->
        <div id="modal01" class="w3-modal w3-black" onclick="this.style.display='none'">
            <span class="w3-button w3-large w3-black w3-display-topright" title="Close Modal Image"><i class="fa fa-remove"></i></span>
            <div class="w3-modal-content w3-animate-zoom w3-center w3-transparent w3-padding-64">
                <img id="img01" class="w3-image">
                <p id="caption" class="w3-opacity w3-large"></p>
            </div>
        </div>

        <!-- Footer -->
        <@footer.footer>
        </@footer.footer>

    </body>
</html>