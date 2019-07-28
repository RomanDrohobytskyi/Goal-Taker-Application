package application.controllers;

import application.logger.LoggerJ;
import application.services.UserService;
import application.entities.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    private Logger logger = Logger.getLogger(this.getClass());


    @GetMapping("/login")
    public String login() {
        LoggerJ.logInfo(this.getClass(), "@GetMapping /login");
        return "login";
    }

    @PostMapping("/login")
    public String loginError(User user, Map<String, Object> model) {
        LoggerJ.logInfo(this.getClass(), "@PostMapping /login");
        if (!userService.isUserExist(user)) {
            model.put("validatorError", "No user with email: " + user.getEmail());
            LoggerJ.logInfo(this.getClass(), "@PostMapping / No user with email: " + user.getEmail());
        }else
            model.put("validatorError", "Wrong Email or Password!");
            LoggerJ.logInfo(this.getClass(), "@PostMapping / Wrong Email or Password!" );

        return "login";
    }
}
