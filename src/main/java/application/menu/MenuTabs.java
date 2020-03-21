package application.menu;

import application.managers.UserManager;
import application.roles.Role;

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

    public List<MenuElement> smartGoalsMainMenu(){
        List<MenuElement> menuElements = new ArrayList<>();

        menuElements.add(new MenuElement("/", "w3-bar-item w3-button","fa fa-home", "HOME", "Home page"));
        menuElements.add(new MenuElement("#", "w3-bar-item w3-button","fa fa-chevron-up", "UP", "Up to the top"));
        menuElements.add(new MenuElement("/login", "w3-bar-item w3-button w3-hide-small w3-right w3-hover-red","fa fa-sign-in", "", "Login"));
        menuElements.add(new MenuElement("/profile", "w3-bar-item w3-button w3-hide-small w3-right w3-hover-red","fa fa-user", "", "Profile"));
        menuElements.add(new MenuElement("#", "w3-bar-item w3-button w3-hide-small w3-right w3-hover-red","fa fa-search", "", "Search"));

        return menuElements;
    }

    public boolean getIsUserAdmin(){
        return new UserManager().getLoggedInUser().getRoles().contains(Role.ADMIN);
    }
}
