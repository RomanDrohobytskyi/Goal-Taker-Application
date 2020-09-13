package application.controllers.smart.aim;

import application.entities.aim.Aim;
import application.entities.user.User;
import application.managers.UserManager;
import application.menu.MenuTabs;
import application.repositories.IAimRepository;
import application.services.AimService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Smart aim Controller
 */
@Controller
@RequiredArgsConstructor
public class AimController {

    private final AimService aimService;
    private final IAimRepository aimRepository;
    private final UserManager userManager = new UserManager();

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

            aimService.addAndSaveAim(user, description, text, specific, measurable, attainable, relevant,
                    timeBased, title);
            model.put("aims", user.getAims());
        return "redirect:/main_aim#aimsTable";
    }

    @GetMapping("/main_aim/delete/{aim}")
    public String delete(
            @PathVariable Aim aim,
            Map<String, Object> model) {

        aimService.delete(aim);
        User loggedInUser = userManager.getLoggedInUser();
        Iterable<Aim> userAims = aimRepository.findByUser(loggedInUser);
        model.put("aims", userAims);

        return "redirect:/main_aim#aimsTable";
    }

    @GetMapping("/main_aim/achieve/{aim}")
    public String achieve(@PathVariable Aim aim){
        aimService.achieve(aim);
        return "redirect:/main_aim#aimsTable";
    }

}
