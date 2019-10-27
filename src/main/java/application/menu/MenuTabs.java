package application.menu;

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
        List<MenuElement> menuElements = new ArrayList<MenuElement>();

        MenuElement main = new MenuElement("/main", "fa fa-envelope", "MESSAGES", "All messages");
        MenuElement mainAim = new MenuElement("/main_aim", "fa fa-envelope", "MESSAGES", "All messages");

        menuElements.add(main);
        menuElements.add(mainAim);
        return menuElements;
    }
}
