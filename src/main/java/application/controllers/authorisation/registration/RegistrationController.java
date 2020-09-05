package application.controllers.authorisation.registration;


import application.entities.user.User;
import application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model,
                          @RequestParam String passwordConfirm) {
        model.putAll(userService.validateUserRegistrationData(user, passwordConfirm));
        return model.containsKey("success") ? "redirect:/login" : "registration";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        try{
            userService.activateUser(code);
            model.addAttribute("message", "User successfully activated.");
        } catch (IllegalArgumentException e){
            model.addAttribute("message", "Activation code is not found!");
        }
        return "login";
    }

    @PostMapping("/registration/resendVerificationCode")
    public String resendVerificationCode(User user, Map<String, Object> model) {
        userService.resendVerificationToken(user);
        return model.containsKey("success") ? "redirect:/login" : "registration";
    }
}
