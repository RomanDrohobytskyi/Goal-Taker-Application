package application.controllers.smart.aim;

import application.entities.aim.Aim;
import application.entities.user.User;
import application.managers.UserManager;
import application.menu.MenuTabs;
import application.repositories.IAimRepository;
import application.services.AimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import static application.logger.LoggerJ.logError;

/**
 * Smart aim Controller
 */
@Controller
public class AimController {

    @Autowired
    private AimService aimService;
    @Autowired
    private IAimRepository aimRepository;
    private UserManager userManager = new UserManager();

    @GetMapping("/main_aim")
    public String allAims(Model model){
        User loggedInUser = userManager.getLoggedInUser();
        Iterable<Aim> userAims = aimRepository.findByUser(loggedInUser);
        model.addAttribute("all_aims", userAims);
        model.addAttribute("menuElements", new MenuTabs().smartGoalsMainMenu());
        model.addAttribute("slideMenuElements", new MenuTabs().defaultSlideMenu());
        return "main_aim";
    }

    @GetMapping("/main_aim/add")
    public String addAim(
            @AuthenticationPrincipal User user,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String text,
            @RequestParam String specific,
            @RequestParam String measurable,
            @RequestParam String attainable,
            @RequestParam String relevant,
            @RequestParam String timeBased,
            Map<String, Object> model) {

        Date convertedDate;
        Optional<Aim> aimOptional = Optional.empty();

        try{
            convertedDate = new SimpleDateFormat("yyyy-MM-dd").parse(timeBased);
            aimOptional = aimService.adaptAim(title, description, text, specific, measurable, attainable, relevant, convertedDate, user);
            if (aimOptional.isPresent()){
                Aim aim = aimOptional.get();
                aimRepository.save(aim);
                Iterable<Aim> userAims = user.getAims();
                model.put("aims", userAims);
            }
            else {
                Map myMap = Collections.EMPTY_MAP;
                model.put("aims", myMap);
            }
        } catch (Exception e){
            logError(getClass(), "Could not save aim: " + aimOptional.get());
            e.printStackTrace();
        }
        return "redirect:/main_aim#aimsTable";
    }

    @GetMapping("/main_aim/delete/{aim}")
    public String delete(
            @PathVariable Aim aim,
            Map<String, Object> model) {

        aimService.deleteAim(aim);
        User loggedInUser = userManager.getLoggedInUser();
        Iterable<Aim> userAims = aimRepository.findByUser(loggedInUser);
        model.put("aims", userAims);

        return "redirect:/main_aim#aimsTable";
    }

    @GetMapping("/main_aim/achieve/{aim}")
    public String achieve(@PathVariable Aim aim, Map<String, Object> model){
        aimService.achieve(aim);
        return "redirect:/main_aim#aimsTable";
    }

}
