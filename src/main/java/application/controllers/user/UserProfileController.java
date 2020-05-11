package application.controllers.user;

import application.entities.user.User;
import application.managers.UserManager;
import application.menu.MenuTabs;
import application.repositories.IUserRepository;
import application.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserProfileController {

    @Autowired
    private IUserRepository iUserRepository;
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/userProfile")
    public String getUserProfile(Model model){
        model.addAttribute("user", getLoggedInUser());
        addMenuElements(model);
        return "userProfile";
    }

    @PostMapping("/userProfile/save")
    public String saveUserProfileChanges(@RequestParam(name = "avatar", required = false, defaultValue = "") MultipartFile avatar,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String username,
            @RequestParam("userId") User user,
            Model model){

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        addUserAvatar(user, avatar);

        iUserRepository.save(user);
        model.addAttribute("user", user);
        addMenuElements(model);
        return "userProfile";
    }

    private User getLoggedInUser(){
        return iUserRepository.findById(new UserManager().getLoggedInUser().getId()).get();
    }

    private void addMenuElements(Model model){
        model.addAttribute("menuElements", new MenuTabs().defaultMenu());
        model.addAttribute("slideMenuElements", new MenuTabs().defaultSlideMenu());
    }

    private void addUserAvatar(User user, MultipartFile avatar){
        if(!avatar.getOriginalFilename().isEmpty()){
            FileService fileService = new FileService();
            fileService.uploadFile(avatar);
            user.setAvatar(fileService.getCreatedFileName());
        }
    }
}
