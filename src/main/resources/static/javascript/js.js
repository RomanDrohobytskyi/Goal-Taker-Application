// Modal Image Gallery
function onClick(element) {
    document.getElementById("img01").src = element.src;
    document.getElementById("modal01").style.display = "block";
    var captionText = document.getElementById("caption");
    captionText.innerHTML = element.alt;
}

// Change style of navBar on scroll
window.onscroll = function() {myFunction()};
function myFunction() {
    var navBar = document.getElementById("myNavBar");
    if (document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {
        navBar.className = "w3-bar" + " w3-card" + " w3-animate-top" + " w3-white";
    } else {
        navBar.className = navBar.className.replace(" w3-card w3-animate-top w3-white", "");
    }
}

// Used to toggle the menu on small screens when clicking on the menu button
function toggleFunction() {
    var x = document.getElementById("navDemo");
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
    } else {
        x.className = x.className.replace(" w3-show", "");
    }
}