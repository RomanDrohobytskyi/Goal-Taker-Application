package application.controllers.smart.aim;

import application.entities.aim.Aim;
import application.entities.time.data.Time;
import application.menu.MenuTabs;
import application.services.aim.SmartAimService;
import application.services.time.SmartAimTimeService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class AimDetailsController {

    private final SmartAimService aimService;
    private final SmartAimTimeService timeService;

    @GetMapping("aim_details/{aim}")
    public String aimDetails(@PathVariable Aim aim, Model model){
        List<Time> lastWeekLoggedTime = timeService.getLastWeekTime(aim.getId());
        model.addAttribute("aim", aim);
        model.addAttribute("lastWeekLoggedTime", lastWeekLoggedTime);
        model.addAttribute("menuElements", MenuTabs.defaultMenu());
        model.addAttribute("slideMenuElements", MenuTabs.defaultSlideMenu());
        return "aim_details";
    }

    @GetMapping("aim_details/saveDetails")
    public String saveAimDetails(
            @RequestParam Number time,
            @RequestParam String description,
            @RequestParam String date,
            @RequestParam("aimId") Aim aim,
            Map<String, Object> model) {
        timeService.adaptAndSaveAimDetails(time, date, description, aim);
        model.put("aims", aimService.getLoggedInUserAims());
        return "redirect:/aim_details/" + aim.getId() + "#details";
    }

    @GetMapping("aim_details/saveDetailsAndContinue/{aim}")
    public String saveAimAndContinue(
            @RequestParam Number time,
            @RequestParam String description,
            @RequestParam String date,
            @PathVariable Aim aim,
            Map<String, Object> model) {
        timeService.adaptAndSaveAimDetails(time, date, description, aim);
        model.put("aim", aim);
        return "redirect:/aim_details#details" + aim.getId();
    }

    @GetMapping("/cancel")
    public String cancel(){
        return  "redirect:/main_aim#aimsTable";
    }

}
