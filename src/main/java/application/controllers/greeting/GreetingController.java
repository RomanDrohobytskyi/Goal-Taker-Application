package application.controllers.greeting;

import application.menu.MenuTabs;
import application.services.MailSenderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class GreetingController {

    private String userName;
    private String userEmail;
    private String message;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Greeting page
     * @param model
     * @return 'greeting'
     */
    @GetMapping("/")
    public String greeting(Model model) {
        MenuTabs menuTabs = new MenuTabs();
        model.addAttribute("menuElements", menuTabs.defaultMenu());
        return "greeting";
    }

    @GetMapping("/send/note")
    public String sendNote( Map<String, Object> model) {

        new MailSenderService().send(userEmail, "Note", userName + "/n" + message);

        return "greeting";
    }
}
