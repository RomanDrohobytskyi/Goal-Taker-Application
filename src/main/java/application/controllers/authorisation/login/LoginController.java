package application.controllers.authorisation.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/loginError")
    public String loginError(Map<String, Object> model) {
        model.put("validatorError", "Wrong email or password, please try again.");
        return "login";
    }
}
