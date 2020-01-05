package application.controllers;

import application.logger.LoggerJ;
import application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        LoggerJ.logInfo(this.getClass(), "@GetMapping /login");
        return "login";
    }

    @GetMapping("/loginError")
    public String loginError(Map<String, Object> model) {
        model.put("validatorError", "Wrong email or password, please try again.");

        return "login";
    }
}
