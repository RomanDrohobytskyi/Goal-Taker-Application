package application.login;


import application.service.UserService;
import application.user.IUserRepository;
import application.user.Role;
import application.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model,
                          @RequestParam String passwordConfirm) {

        if (!userService.addUser(user, passwordConfirm)){
            model.put("message", "User with Email: " + user.getEmail() +
                    " already exists!");
            return "registration";
        }
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
