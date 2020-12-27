package application.controllers.greeting;

import application.entities.message.Message;
import application.menu.MenuTabs;
import application.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class GreetingController {

    private final MessageService messageService;

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("menuElements", MenuTabs.defaultMenu());
        model.addAttribute("slideMenuElements", MenuTabs.defaultSlideMenu());
        return "greeting";
    }

    @PostMapping("/send/note")
    public String sendNote(@RequestParam String userName,
                           @RequestParam String userEmail,
                           @RequestParam String message) {
        Message messageAsNote = messageService.adaptMessageAsNote(message, userName, userEmail);
        messageService.sendMessageAsNote(messageAsNote);
        return "redirect:/#contact";
    }
}
