package application.controllers.greeting;

import application.menu.MenuTabs;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GreetingController {

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("menuElements", new MenuTabs().defaultMenu());
        model.addAttribute("slideMenuElements", new MenuTabs().defaultSlideMenu());
        return "greeting";
    }

    @GetMapping("/send/note")
    public String sendNote(@PathVariable String userEmail, @PathVariable String userName, @PathVariable String message) {
        return "greeting";
    }
}
