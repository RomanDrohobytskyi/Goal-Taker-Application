package application.menu;

import application.managers.UserManager;

import java.util.ArrayList;
import java.util.List;


public class MenuTabs {

    private CreatedMenuElements createdMenuElements = new CreatedMenuElements();

    public List<MenuElement> defaultMenu(){
        return getDefaultMenuItems();
    }

    public List<MenuElement> defaultSlideMenu(){
        return getDefaultSlideMenuItems();
    }

    public  List<MenuElement> getDefaultMenuItems(){
        List<MenuElement> menuElements = new ArrayList<>();
        menuElements.add(createdMenuElements.homePage);
        menuElements.add(createdMenuElements.upToTheTop);
        menuElements.add(createdMenuElements.about);
        menuElements.add(createdMenuElements.contact);
        menuElements.add(createdMenuElements.login);
        menuElements.add(createdMenuElements.profile);
        return menuElements;
    }

    public  List<MenuElement> getDefaultSlideMenuItems(){
        List<MenuElement> menuElements = new ArrayList<>();
        menuElements.add(createdMenuElements.aims);
        menuElements.add(createdMenuElements.smartAim);
        menuElements.add(createdMenuElements.tenThousandHoursAim);
        menuElements.add(createdMenuElements.main);
        menuElements.add(createdMenuElements.userAnalyzer);
        menuElements.add(createdMenuElements.photos);
        menuElements.add(createdMenuElements.projects);
        if (new UserManager().isLoggedUserAdmin()){
            menuElements.add(createdMenuElements.users);
        }
        return menuElements;
    }

    public List<MenuElement> smartGoalsMainMenu(){
        List<MenuElement> menuElements = new ArrayList<>();
        menuElements.add(createdMenuElements.homePage);
        menuElements.add(createdMenuElements.upToTheTop);
        menuElements.add(createdMenuElements.aimsList);
        menuElements.add(createdMenuElements.createAim);
        menuElements.add(createdMenuElements.login);
        menuElements.add(createdMenuElements.profile);
        return menuElements;
    }

    public  List<MenuElement> timeAnalyzerMenu(){
        List<MenuElement> menuElements = new ArrayList<>();
        menuElements.add(createdMenuElements.homePage);
        menuElements.add(createdMenuElements.upToTheTop);
        menuElements.add(createdMenuElements.loggedTimeTable);
        menuElements.add(createdMenuElements.lineChart);
        menuElements.add(createdMenuElements.moreData);
        menuElements.add(createdMenuElements.login);
        menuElements.add(createdMenuElements.profile);
        return menuElements;
    }

    public  List<MenuElement> userAnalyzerMenuItems(){
        List<MenuElement> menuElements = new ArrayList<>();
        menuElements.add(createdMenuElements.homePage);
        menuElements.add(createdMenuElements.upToTheTop);
        menuElements.add(createdMenuElements.personalUserAnalyzer);
        menuElements.add(createdMenuElements.moreData);
        menuElements.add(createdMenuElements.smartAimCharts);
        menuElements.add(createdMenuElements.tenThousandHoursAimCharts);
        menuElements.add(createdMenuElements.login);
        menuElements.add(createdMenuElements.profile);
        return menuElements;
    }
}
