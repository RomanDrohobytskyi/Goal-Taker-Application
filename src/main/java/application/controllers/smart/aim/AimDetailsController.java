package application.controllers.smart.aim;

import application.entities.aim.Aim;
import application.entities.time.data.Time;
import application.entities.user.User;
import application.managers.UserManager;
import application.menu.MenuTabs;
import application.repositories.IAimRepository;
import application.services.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class AimDetailsController {

    @Autowired
    private IAimRepository aimRepository;
    @Autowired
    private TimeService timeService;
    private UserManager userManager = new UserManager();

    @GetMapping("aim_details/{aim}")
    public String aimDetails(@PathVariable Aim aim, Model model){
        List<Time> lastWeekLoggedTime = timeService.getLastWeekTime(aim.getId());
        model.addAttribute("aim", aim);
        model.addAttribute("lastWeekLoggedTime", lastWeekLoggedTime);
        model.addAttribute("menuElements", new MenuTabs().defaultMenu());
        model.addAttribute("slideMenuElements", new MenuTabs().defaultSlideMenu());
        return "aim_details";
    }

    @GetMapping("aim_details/saveDetails")
    public String saveAimDetails(
            @RequestParam Number time,
            @RequestParam String description,
            @RequestParam String date,
            @RequestParam("aimId") Aim aim,
            Map<String, Object> model) {

        timeService.saveTimeForAim(time, description, date, aim);
        User loggedInUser = userManager.getLoggedInUser();
        Iterable<Aim> userAims = aimRepository.findByUser(loggedInUser);
        model.put("aims", userAims);
        return "redirect:/aim_details/" + aim.getId() + "#details";
    }

    @GetMapping("aim_details/saveDetailsAndContinue/{aim}")
    public String saveAimAndContinue(
            @RequestParam Number time,
            @RequestParam String description,
            @RequestParam String date,
            @PathVariable Aim aim,
            Map<String, Object> model) {

        timeService.saveTimeForAim(time, description, date, aim);
        model.put("aim", aim);
        return "redirect:/aim_details#details" + aim.getId();
    }

    @GetMapping("/cancel")
    public String cancel(){
        return  "redirect:/main_aim#aimsTable";
    }

}
