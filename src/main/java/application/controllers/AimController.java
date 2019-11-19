package application.controllers;

import application.entities.aim.Aim;
import application.entities.user.User;
import application.managers.AimManager;
import application.managers.UserManager;
import application.repositories.IAimRepository;
import application.repositories.IUserRepository;
import application.services.AimService;
import application.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static application.logger.LoggerJ.logError;

@Controller
public class AimController {

    @Autowired
    private AimService aimService;
    @Autowired
    private IAimRepository aimRepository;
    @Autowired
    private IUserRepository userRepository;

    private UserManager userManager = new UserManager();
    private AimManager aimManager = new AimManager();

    @GetMapping("/main_aim")
    public String allAims(Model model){
        User loggedInUser = userManager.getLoggedInUser();
        Iterable<Aim> userAims = loggedInUser.getAims();
        
        Iterable<Aim> aims = aimRepository.findAll();
        model.addAttribute("all_aims", userAims);
        return "main_aim";
    }

    @GetMapping("/main_aim/add")
    public String addAim(
            @AuthenticationPrincipal User user,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String text,
            Map<String, Object> model) {

        Optional<Aim> aimOptional = aimService.adaptAim(title, description, text, user);
        if (aimOptional.isPresent()){
            try{
                Aim aim = aimOptional.get();
                aimRepository.save(aim);

                Iterable<Aim> userAims = user.getAims();

                //Iterable<Aim> aims = aimRepository.findAll();
                model.put("aims", userAims);
            } catch (Exception e) {
                logError(getClass(), "Could not save aim.");
                e.printStackTrace();
            }
        }
        else {
            HashMap myMap = MapUtils.oneElementHashMap("","");
            model.put("messages", myMap);
        }
            return "redirect:/main_aim#aimsTable";
    }

    @GetMapping("/main_aim/delete/{aim}")
    public String deleteMessage(
            @PathVariable Aim aim,
            Map<String, Object> model) {

        aimService.deleteAim(aim);
        User loggedInUser = userManager.getLoggedInUser();
        Iterable<Aim> userAims = loggedInUser.getAims();

        model.put("aims", userAims);

        return "redirect:/main_aim#aimsTable";
    }

}
