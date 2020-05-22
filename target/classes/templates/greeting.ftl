<html>
<head>
    <title>W e l c o m e</title>

    <link rel="shortcut icon" type="image/png" href="https://cdn.onlinewebfonts.com/svg/img_51582.png">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/css/buttons.css">
    <link rel="stylesheet" href="/static/css/photos.css">
    <link rel="stylesheet" href="/static/css/text.css">
    <link rel="stylesheet" href="/static/css/menu.css">

    <script type="text/javascript" src="/static/javascript/js.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <#import "parts/footer.ftl" as footer>
    <#import "parts/image.ftl" as paralax>
    <#import "parts/menu.ftl" as menu>
</head>
<body>

    <@menu.customMenu menuElements slideMenuElements/>

    <@paralax.paralax id="home" image="" title="" text="Goals setter"
        cssClass="parallax big-img-greeting-1 w3-display-container w3-opacity-min">
    </@paralax.paralax>

    <div class="w3-content w3-container w3-padding-32" id="about">
        <h3 class="w3-center w3-black ">PERSONAL GOAL SETTING</h3>
        <p class="w3-center article"><em>Planning to Live Your Life Your Way</em></p>
        <p class="article-text">
            Many people feel as if they're adrift in the world.
            They work hard, but they don't seem to get anywhere worthwhile.
            A key reason that they feel this way is that they haven't spent enough time
            thinking about what they want from life, and haven't set themselves
            formal goals. After all, would you set out on a major journey with no
            real idea of your destination? Probably not!
        </p>

        <div class="w3-row">
            <div class="w3-col m6 w3-center w3-padding-large">
                <img id="myselfPhoto" src="/static/images/planingSticks.jpg"
                     class="w3-round w3-image w3-opacity w3-hover-opacity-off"
                     onclick="onClick(this); setMaxHeightAndWight('myselfPhoto');" alt="Planing stickers" width="100%">
                <p>
                    <b>
                        <i class="fa fa-dot-circle-o w3-margin-right"></i> Goals
                    </b>
                </p>
                <br>
                <p class="article-text">
                    You will also raise your
                    self-confidence, as you recognize your own ability and competence in achieving
                    the goals that you've set.
                </p>
            </div>

            <div class="w3-col m6 w3-hide-small w3-padding-large">
                <h3 class="w3-center article">Why Set Goals?</h3>

                <p class="article-text">
                    Top-level athletes, successful businesspeople and achievers in all fields all set goals.
                    Setting goals gives you long-term vision and short-term motivation.
                    It focuses your acquisition of knowledge, and helps you to organize your time
                    and your resources so that you can make the most of your life.
                    By setting sharp, clearly defined goals, you can measure and take pride in the
                    achievement of those goals, and you'll see forward progress in what might
                    previously have seemed a long pointless grind.
                </p>
            </div>
        </div>
       <#-- <p class="w3-large w3-center w3-padding-16">Im really good at:</p>
        <p class="w3-wide"><i class="fa fa-camera"></i>Programming</p>
        <div class="w3-light-grey">
            <div class="w3-container w3-padding-small w3-dark-grey w3-center" style="width:90%">90%</div>
        </div>
        <p class="w3-wide"><i class="fa fa-laptop"></i>Photography</p>
        <div class="w3-light-grey">
            <div class="w3-container w3-padding-small w3-dark-grey w3-center" style="width:85%">80%</div>
        </div>
        <p class="w3-wide"><i class="fa fa-photo"></i>Film making</p>
        <div class="w3-light-grey">
            <div class="w3-container w3-padding-small w3-dark-grey w3-center" style="width:75%">75%</div>
        </div>-->
    </div>

   <div class="w3-row w3-center w3-dark-grey w3-padding-16">
        <div class="w3-quarter w3-section">
            S.M.A.R.T goal
        </div>
        <div class="w3-quarter w3-section">
            Ten thousand hours rule
        </div>
        <div class="w3-quarter w3-section">
            Time management
        </div>
        <div class="w3-quarter w3-section">
            Time analyzer
        </div>
    </div>

    <@paralax.paralax id="second" image="" title="Contact me" text="CONTACT"
        cssClass="parallax big-img-greeting-2 w3-display-container w3-opacity-min">
    </@paralax.paralax>
<#--

    <div class="w3-content w3-container w3-padding-64" id="portfolio">

        <h3 class="w3-center">MY PROJECTS</h3>
        <p class="w3-center"><em>This`s my programing projects.<br> Click on the images to make them bigger</em></p><br>

        <div class="w3-row-padding w3-center">
            <div class="w3-col m3">
                <img src="http://198.211.104.161/wp-content/uploads/2014/01/Hibernate-logo.png" style="width:100%"
                     onclick="onClick(this)" class="w3-hover-opacity" alt="Hibernate project" title = "Hibernate project">
            </div>

            <div class="w3-col m3">
                <img src="https://www.openhab.org/logos/jdbc.png" style="width:100%" onclick="onClick(this)"
                     class="w3-hover-opacity" alt="JDBC project" title = "JDBC project">
            </div>

            <div class="w3-col m3">
                <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTB_yCK06ouPnYKZg92oUPTBZtw80NTQ2K_VVisGQaM6BGCqrMg"
                     style="width:100%" onclick="onClick(this)" class="w3-hover-opacity"
                     alt="University project" title = "University project">
            </div>

            <div class="w3-col m3">
                <img src="https://icons-for-free.com/iconfiles/png/512/android+logo+mobile+mobile+phone+icon-1320196075444927847.png"
                     style="width:100%" onclick="onClick(this)" class="w3-hover-opacity"
                     alt="Android project" title="Android project">
            </div>
            <a href="/projects"> <button class="btn btn1 w3-button w3-padding-large">See more projects</button></a>
        </div>

        <div class="w3-row-padding w3-center w3-section">
            <h3 class="w3-center">MY PHOTOS</h3>
            <a href="/photos"> <button class="btn btn2 w3-button w3-padding-large" >See more photos</button></a>
        </div>
    </div>
-->

    <div class="w3-content w3-container w3-padding-64" id="contact">
        <h3 class="w3-center">Contacts</h3>
        <div class="w3-row w3-padding-32 w3-section">
            <div class="w3-col m4 w3-container">
                <img src="https://upload.wikimedia.org/wikipedia/commons/f/f2/Poland_and_Ukraine.png" class="w3-image w3-round" style="width:100%">
            </div>
            <div class="w3-col m8 w3-panel">
                <div class="w3-large w3-margin-bottom">
                    <i class="fa fa-map-marker fa-fw w3-hover-text-black w3-xlarge w3-margin-right"></i> Lublin/PL, Lviv/UA<br>
                    <i class="fa fa-phone fa-fw w3-hover-text-black w3-xlarge w3-margin-right"></i> Phone: +570000000<br>
                    <i class="fa fa-envelope fa-fw w3-hover-text-black w3-xlarge w3-margin-right"></i> Email: roman.roman@mail.com<br>
                </div>
                <p>Leave me a note:</p>
                <form action="/send/note">
                    <div class="w3-row-padding" style="margin:0 -16px 8px -16px">
                        <div class="w3-half">
                            <input class="w3-input w3-border" type="text" placeholder="Name" required name="userName">
                        </div>
                        <div class="w3-half">
                            <input class="w3-input w3-border" type="text" placeholder="Email" required name="userEmail">
                        </div>
                    </div>
                    <input class="w3-input w3-border" type="text" placeholder="Message" required name="message">

                    <button class="w3-right w3-section btn btn1" type="submit">
                        <i class="fa fa-paper-plane"></i>
                            SEND MESSAGE
                    </button>
                </form>
            </div>
        </div>
    </div>

    <@footer.footer/>
</body>
</html>