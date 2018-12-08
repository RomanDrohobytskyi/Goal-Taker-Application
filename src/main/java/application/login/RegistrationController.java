package application.login;


import application.service.UserService;
import application.user.IUserRepository;
import application.user.User;
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

//        if (!userService.addUser(user, passwordConfirm)){
//            model.put("message", "User with Email: " + user.getEmail() +
//                    " already exists!");
//            return "registration";
//        }
//        User userFromDb = iUserRepository.findUserByEmail(user.getEmail());
//
//
//        if (userFromDb != null) {
//            model.put("message", "User with Email: " + user.getEmail() +
//                    " already exists!");
//            return "registration";
//        }
//
//        if (user.getPassword().equals(passwordConfirm)) {
//            user.setActive(true);
//            user.setRoles(Collections.singleton(Role.USER));
////            user.setRoles(Collections.singleton(Role.ADMIN));
//
//            iUserRepository.save(user);
//            return "redirect:/login";
//        }
//        return "registration";
        //TODO: If everything is goes => save user data, send code, else show error and return.

        //If user already exist.
        if (!userService.isUserExist(user)){
            model.put("userExist", "Sorry, user with email: " + user.getEmail() + ", already exist!");
            return "registration";
        }

        //If user email is empty.
        if (!userService.isUserEmailEmpty(user.getEmail())){
            model.put("emailIsEmpty", "Sorry, Your email is empty, please check it again");
            return "registration";
        }

        //If passwords do not match.
        if (!userService.isPasswordsMatch(user.getPassword(), passwordConfirm)){
            model.put("passwordNotMach", "Sorry, but Your passwords do not match, check it again!");
            return "registration";
        }

        userService.setUserData(user);
        userService.saveUser(user);
        userService.sendActivationCode(user);

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);
        if (isActivated){
            model.addAttribute("message", "User successfully activated.");
        }else {
            model.addAttribute("message", "Activation code is not found!");
        }
        return "login";
    }
}
