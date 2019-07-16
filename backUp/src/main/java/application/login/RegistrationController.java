package application.login;


import application.user.UserService;
import application.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;
    @PersistenceContext
    EntityManager entityManager;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model,
                          @RequestParam String passwordConfirm) {

<<<<<<< HEAD
        //If user not exist.
        if (userService.isUserExist(user.getEmail())){
            //If user email is not empty.
=======
        //If user not exist
        if (userService.isUserExist(user)){
            //If user email is not empty
>>>>>>> 4421ffe9e6d2d0f718a777353acacdcaecf48c85
            if (userService.isUserEmailEmpty(user.getEmail())){
                //If passwords match - save user and user data
                if (userService.isPasswordsMatch(user.getPassword(), passwordConfirm)){
                    userService.setUserData(user);
                    userService.saveUser(user);
                    if (userService.sendActivationCode(user)){
                        return "redirect:/login";
                    } else {
                        model.put("message", "We can`t send to You activation code, sorry!");
                    }
                } else {
                    model.put("passwordNotMach", "Sorry, but Your passwords do not match, check it again!");
                }
            } else {
                model.put("emailIsEmpty", "Sorry, Your email is empty, please check it again");
            }
        } else {
            model.put("userExist", "Sorry, user with email: " + user.getEmail() + ", already exist!");
        }
//        userService.deleteUser(user);
        return "registration";
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