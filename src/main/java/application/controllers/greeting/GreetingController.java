package application.controllers.greeting;

import application.entities.message.Message;
import application.enums.State;
import application.menu.MenuTabs;
import application.services.MessageService;
import application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class GreetingController {

    private final UserService userService;
    private final MessageService messageService;

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("menuElements", new MenuTabs().defaultMenu());
        model.addAttribute("slideMenuElements", new MenuTabs().defaultSlideMenu());
        return "greeting";
    }

    @PostMapping("/send/note")
    public String sendNote(@RequestParam String userName,
                           @RequestParam String userEmail,
                           @RequestParam String message) {
        Message userMessage = new Message();
        userMessage.setState(State.MessageState.NEW.toString());
        userMessage.setTag("User Note");
        userMessage.setUser(userService.findUserByEmail("romabikebmx@gmail.com"));
        userMessage.setText("User " + userEmail + ", " + userName + ", left note: " + message);
        messageService.save(userMessage);
        return "redirect:/#contact";
    }
}
