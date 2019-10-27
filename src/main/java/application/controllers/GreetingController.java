package application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

    /**
     * Greeting page
     * @param model
     * @return 'greeting'
     */
    @GetMapping("/")
    public String greeting(Model model) {
        /*MenuTabs menuTabs = new MenuTabs();
        model.addAttribute("menuElements", menuTabs.defaultMenu());*/
        return "greeting";
    }
}
