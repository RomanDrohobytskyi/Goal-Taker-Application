package application.controllers.user;

import application.entities.aim.Aim;
import application.entities.aim.TenThousandHoursAim;
import application.entities.message.Message;
import application.entities.user.User;
import application.repositories.IAimRepository;
import application.repositories.IMessageRepository;
import application.repositories.ITenThousandHoursAimRepository;
import application.repositories.IUserRepository;
import application.roles.Role;
import application.services.AimService;
import application.services.MessageService;
import application.services.TenThousandHoursAimService;
import application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private IMessageRepository messageRepo;
    @Autowired
    private IAimRepository aimRepository;
    @Autowired
    private ITenThousandHoursAimRepository tenThousandHoursAimRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private AimService aimService;
    @Autowired
    private TenThousandHoursAimService thousandHoursAimService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", iUserRepository.findAll());
        return "userList";
    }

    /**
     * Getting form for edit user
     * "{user}" - id from url like - "http://localhost:8080/user/10"
     * @PathVariable User user - checked user.
     * @param user
     * @param model
     * @return
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userEditedSave(
            @RequestParam String username,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam Map <String, String> form,
            @RequestParam("userId") User user) {

        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()){
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        iUserRepository.save(user);
        return "redirect:/user";
    }

    @GetMapping("/delete/{user}")
    public String delete(@PathVariable User user,  Map<String, Object> model){
        List<Message> notes = messageRepo.findByUser(user);
        List<Aim> aims =aimRepository.findByUser(user);
        List<TenThousandHoursAim> thousandHoursAims =
                tenThousandHoursAimRepository.findByUser(user);

        messageService.delete(notes);
        aimService.delete(aims);
        thousandHoursAimService.delete(thousandHoursAims);
        userService.delete(user);

        model.put("users", iUserRepository.findAll());

        return "redirect:/user";
    }
}
