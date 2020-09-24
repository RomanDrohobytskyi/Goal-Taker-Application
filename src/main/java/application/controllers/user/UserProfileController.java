package application.controllers.user;

import application.entities.user.User;
import application.managers.UserManager;
import application.menu.MenuTabs;
import application.services.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;
    private UserManager userManager = new UserManager();

    @GetMapping("/userProfile")
    public String getUserProfile(Model model){
        model.addAttribute("user", userManager.getLoggedInUser());
        addMenuElements(model);
        return "userProfile";
    }

    @PostMapping("/userProfile/save")
    public String saveUserProfileChanges(@RequestParam(name = "avatar", required = false) MultipartFile avatar,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String username,
            @RequestParam("userId") User user,
            Model model){

        userProfileService.adaptAndSaveEditedUserProfile(avatar, firstName, lastName, username, user);
        model.addAttribute("user", user);
        addMenuElements(model);
        return "userProfile";
    }

    private void addMenuElements(Model model){
        model.addAttribute("menuElements", new MenuTabs().defaultMenu());
        model.addAttribute("slideMenuElements", new MenuTabs().defaultSlideMenu());
    }

}
