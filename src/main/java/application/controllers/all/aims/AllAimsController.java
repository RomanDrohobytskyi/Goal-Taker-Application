package application.controllers.all.aims;

import application.menu.MenuTabs;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllAimsController {

    @GetMapping("/aims")
    public String allAims(Model model){
        model.addAttribute("menuElements", new MenuTabs().defaultMenu());
        model.addAttribute("slideMenuElements", new MenuTabs().defaultSlideMenu());
        return "aims";
    }
}
