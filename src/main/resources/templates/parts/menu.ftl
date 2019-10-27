<#macro navBar withSlideMenu=true>

    <!-- NavBar (sit on top) -->
    <div class="w3-top">
        <div class="w3-bar" id="myNavBar">

            <#if withSlideMenu>
                <@slideMenu/>
            </#if>

        <#-- <div class="wrapper">
             <div class = "right-menu">
                 <i class="fa fa-bars w3-bar-item w3-button menu-button"></i>
                 <div class="dropdown-menu" >
                     <a href="/main" ><i class="fa fa-envelope"></i> MESSAGES</a>
                     <a href="/main_aim" ><i class="fa fa-envelope"></i> AIMS</a>
                     <a href="/user" ><i class="fa fa-envelope"></i> USERS</a>
                 </div >
             </div >
         </div >-->

            <a href="/" class="w3-bar-item w3-button"><i class="fa fa-home"></i>HOME</a>
            <a href="#" class="w3-bar-item w3-button"><i class="fa fa-chevron-up"></i>Up</a>
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
            <a href="/main" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-envelope"></i> MESSAGES</a>
            <a href="/main_aim" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-dot-circle-o"></i> AIMS</a>
            <a href="/user" class="w3-bar-item w3-button w3-hide-small"><i class="fa fa-user-circle"></i> USERS</a>
            <a href="/login" class="w3-bar-item w3-button w3-right w3-hover-red"><i class="fa fa-sign-in"></i></a>

            <a href="#" class="w3-bar-item w3-button">SEARCH</a>
        </div>
    </div>

</#macro>

<#--
    TODO: List of url, fa-fa-icon, text
-->
<#macro slideMenu >

        <div class="middle w3-bar-item">
            <div class="sm-container">
                <i class="show-btn fa fa-bars  w3-button"></i>
                <div class="sm-menu">
                    <a href="/main" ><i class="fa fa-envelope"></i> MESSAGES</a>
                    <a href="/main_aim" ><i class="fa fa-dot-circle-o"></i> AIMS</a>
                    <a href="/user" ><i class="fa fa-user-circle"></i> USERS</a>
                </div>
            </div>
        </div>

        <script>
            $(".show-btn").click(function(){
                $(".sm-menu").fadeToggle("fast");
            });
        </script>

</#macro>