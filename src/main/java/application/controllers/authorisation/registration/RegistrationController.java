package application.controllers.authorisation.registration;


import application.entities.user.User;
import application.enums.UserRegisterValidationState;
import application.services.UserRegistrationService;
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

    private final UserRegistrationService userRegistrationService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model,
                          @RequestParam String passwordConfirm) {
        Map<String, Object> validationResult = userRegistrationService.validateUser(user, passwordConfirm);
        model.putAll(validationResult);
        return validationResult.containsKey(UserRegisterValidationState.SUCCESS.state)
                ? "redirect:/login" : "registration";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        try{
            userRegistrationService.activateUserByActivationCode(code);
            model.addAttribute("message", "User successfully activated.");
        } catch (IllegalArgumentException e){
            model.addAttribute("message", "Activation code is not found!");
        }
        return "login";
    }

    @GetMapping("/registration/resendVerificationCode")
    public String resendVerificationCode(@RequestParam String email, Model model) {
        boolean resend = userRegistrationService.resendVerificationToken(email);
        if (resend) {
            model.addAttribute("message", "Activation code successfully resend!");
        } else {
            model.addAttribute("message", "Activation code was not resend!");
        }
        return "registration";
    }
}
