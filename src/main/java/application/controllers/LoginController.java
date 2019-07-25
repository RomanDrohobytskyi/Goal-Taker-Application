package application.controllers;

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
        logger.info("@GetMapping /login: " + this.getClass().getSimpleName());
        return "login";
    }

    @PostMapping("/login")
    public String loginError(User user, Map<String, Object> model) {
        if (!userService.isUserExist(user)) {
            model.put("validatorError", "No user with email: " + user.getEmail());
            logger.info("No user with email: " + user.getEmail() + "\n" + this.getClass().getSimpleName());
        }else
            model.put("validatorError", "Wrong Email or Password!");
            logger.info("Wrong Email or Password!" + "\n" + this.getClass().getSimpleName());
        return "login";
    }
}
