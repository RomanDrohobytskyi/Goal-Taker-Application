package application.controllers.ten.k.hours.aim;

import application.entities.aim.TenThousandHoursAim;
import application.entities.user.User;
import application.menu.MenuTabs;
import application.services.TenThousandHoursAimService;
import application.services.TenThousandHoursAimTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class TenKHoursAimController {

    @Autowired
    private TenThousandHoursAimService aimService;
    @Autowired
    private TenThousandHoursAimTimeService aimTimeService;

    @GetMapping("/ten_thousand_hours_aim")
    public String allAims(Model model){
        model.addAttribute("all_aims", aimService.getAllLoggedUserAims());
        model.addAttribute("timeSum", aimTimeService.getAimsLoggedTimeSum((aimService.getAllLoggedUserAims())));
        model.addAttribute("allAimsTimeSum", aimTimeService.getAimsLoggedTimeSum((aimService.getAllLoggedUserAims()))); //TODO
        model.addAttribute("menuElements", new MenuTabs().smartGoalsMainMenu());
        model.addAttribute("slideMenuElements", new MenuTabs().defaultSlideMenu());
        return "ten_thousand_hours_aim";
    }

    @GetMapping("/ten_thousand_hours_aim/add")
    public String addAim(
            @AuthenticationPrincipal User user,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String text,
            Map<String, Object> model) {

        aimService.createAim(title, description, text, user);
        model.put("aims",  user.getTenThousandHoursAims());
        return "redirect:/ten_thousand_hours_aim#aimsTable";
    }

    @GetMapping("/ten_thousand_hours_aim/delete/{aim}")
    public String deleteMessage(
            @PathVariable TenThousandHoursAim aim,
            Map<String, Object> model) {

        aimService.deleteAim(aim);
        model.put("aims",  aimService.getAllLoggedUserAims());
        return "redirect:/ten_thousand_hours_aim#aimsTable";
    }

    @GetMapping("/ten_thousand_hours_aim/achieve/{aim}")
    public String achieve(@PathVariable TenThousandHoursAim aim, Map<String, Object> model){
        aimService.achieve(aim);
        return "redirect:/ten_thousand_hours_aim#aimsTable";
    }
}
