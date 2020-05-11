package application.controllers.authorisation.registration;


import application.entities.user.User;
import application.logger.LoggerJ;
import application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model,
                          @RequestParam String passwordConfirm) {

        if (!userService.isUserExist(user)){
            if (userService.isUserEmailEmpty(user.getEmail())){
                if (userService.isPasswordsMatch(user.getPassword(), passwordConfirm)){
                    userService.setUserData(user);
                    if (userService.sendActivationCode(user)){
                        userService.saveUser(user);
                        return "redirect:/login";
                    } else {
                        model.put("message", "We can`t send to You activation code, sorry!");
                    }
                } else {
                    model.put("passwordNotMach", "Sorry, but Your passwords do not match, check it again!");
                }
            }  else {
                model.put("emailIsEmpty", "Sorry, Your email is empty, please check it again");
            }
        } else {
            model.put("userExist", "Sorry, user with email: " + user.getEmail() + ", already exist!");
        }
        model.put("user", user);
        return "registration";
    }


    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);
        if (isActivated){
            model.addAttribute("message", "User successfully activated.");
            LoggerJ.logInfo(getClass(), "User successfully activated.");
        }else {
            model.addAttribute("message", "Activation code is not found!");
        }
        return "login";
    }
}
