package application.controllers.photography;

import application.menu.MenuTabs;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PhotosController {
    @GetMapping("/photos")
    public String photos(Model model)
    {
        model.addAttribute("menuElements", new MenuTabs().defaultMenu());
        model.addAttribute("slideMenuElements", new MenuTabs().defaultSlideMenu());
        return "photos";
    }
}
