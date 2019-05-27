package application.login;

import application.user.UserService;
import application.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/loginSentCode")
    public String sentCodeInformation(Map<String, Object> model){
        model.put("activated", "We sent a activation code on your email, check it out!");
        return "/loginSentCode";
    }

    @GetMapping("/login?error")
    public String validator(User user, Map<String, Object> model,
                            @RequestParam String email) {
        if (userService.isUserExist(user.getEmail())) {
            model.put("validatorError", "No user with email: " + email);
        }else
            model.put("validatorError", "Wrong Email or Password!");

        return "login?error";
    }
}
