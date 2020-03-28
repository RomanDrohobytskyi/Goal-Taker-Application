package application.menu;

import application.entities.user.User;
import application.managers.UserManager;

import java.util.ArrayList;
import java.util.List;


public class MenuTabs {

    private List<MenuElement> menuElements = null;

    public List<MenuElement> getMenuElements() {
        return menuElements;
    }

    public void setMenuElements(List<MenuElement> menuElements) {
        this.menuElements = menuElements;
    }

    public List<MenuElement> defaultMenu(){
        return getDefaultMenuItems();
    }

    public List<MenuElement> defaultSlideMenu(){
        return getDefaultSlideMenuItems();
    }

    public  List<MenuElement> getDefaultMenuItems(){
        List<MenuElement> menuElements = new ArrayList<>();
        menuElements.add(new MenuElement("/", "w3-bar-item w3-button","fa fa-home", "HOME", "Home page"));
        menuElements.add(new MenuElement("#home", "w3-bar-item w3-button","fa fa-chevron-up", "UP", "Up to the top"));
        menuElements.add(new MenuElement("#about", "w3-bar-item w3-button w3-hide-small","fa fa-user", "ABOUT", "About me"));
        menuElements.add(new MenuElement("#portfolio", "w3-bar-item w3-button w3-hide-small","fa fa-th", "PORTFOLIO", "My portfolio"));
        menuElements.add(new MenuElement("#contact", "w3-bar-item w3-button w3-hide-small","fa fa-envelope", "CONTACT", "Contact me"));
        menuElements.add(new MenuElement("/login", "w3-bar-item w3-button w3-hide-small w3-right w3-hover-red","fa fa-sign-in", "", "Login"));
        menuElements.add(new MenuElement("/profile", "w3-bar-item w3-button w3-hide-small w3-right w3-hover-red","fa fa-user", "", "Profile"));
        return menuElements;
    }

    public  List<MenuElement> getDefaultSlideMenuItems(){
        List<MenuElement> menuElements = new ArrayList<>();
        menuElements.add(new MenuElement("/aims", "fa fa-circle-o", "ALL AIMS", ">ALL AIMS"));
        menuElements.add(new MenuElement("/main_aim", "fa fa-dot-circle-o", "S.M.A.R.T", "SMART"));
        menuElements.add(new MenuElement("/ten_thousand_hours_aim", "fa fa-dot-circle-o", "10k H", "10k H"));
        menuElements.add(new MenuElement("/main", "fa fa-book", "NOTES", "NOTES"));
        menuElements.add(new MenuElement("/userAnalyzer/activity", "fa fa-circle-o", "USER ANALYZER", "USER ANALYZER"));
        menuElements.add(new MenuElement("/photos", "fa fa-picture-o", "PHOTOS", "PHOTOS"));
        menuElements.add(new MenuElement("/projects", "fa fa-archive", "PROJECTS", "PROJECTS"));
        if (isLoggedUserAdmin()){
            menuElements.add(new MenuElement("/user", "fa fa-user-circle", "USERS", "USERS"));
        }
        return menuElements;
    }

    public List<MenuElement> smartGoalsMainMenu(){
        List<MenuElement> menuElements = new ArrayList<>();

        menuElements.add(new MenuElement("/", "w3-bar-item w3-button","fa fa-home", "HOME", "Home page"));
        menuElements.add(new MenuElement("#", "w3-bar-item w3-button","fa fa-chevron-up", "UP", "Up to the top"));
        menuElements.add(new MenuElement("/login", "w3-bar-item w3-button w3-hide-small w3-right w3-hover-red","fa fa-sign-in", "", "Login"));
        menuElements.add(new MenuElement("/profile", "w3-bar-item w3-button w3-hide-small w3-right w3-hover-red","fa fa-user", "", "Profile"));
        menuElements.add(new MenuElement("#", "w3-bar-item w3-button w3-hide-small w3-right w3-hover-red","fa fa-search", "", "Search"));

        return menuElements;
    }

    public boolean isLoggedUserAdmin(){
        User user = new UserManager().getLoggedInUser();
        return user != null && user.isAdmin();
    }
}
