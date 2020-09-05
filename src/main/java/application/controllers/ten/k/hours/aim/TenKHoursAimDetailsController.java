package application.controllers.ten.k.hours.aim;

import application.entities.aim.TenThousandHoursAim;
import application.menu.MenuTabs;
import application.services.TenThousandHoursAimService;
import application.services.TenThousandHoursAimTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('USER')")
@RequiredArgsConstructor
public class TenKHoursAimDetailsController {

    private final TenThousandHoursAimService aimService;
    private final TenThousandHoursAimTimeService timeService;

    @GetMapping("tenKHoursAimDetails/{aim}")
    public String aimDetails(@PathVariable TenThousandHoursAim aim, Model model){
        model.addAttribute("aim", aim);
        model.addAttribute("lastWeekLoggedTime",  timeService.getLastWeekTime(aim.getId()));
        model.addAttribute("menuElements", new MenuTabs().defaultMenu());
        model.addAttribute("slideMenuElements", new MenuTabs().defaultSlideMenu());
        return "tenKHoursAimDetails";
    }

    @GetMapping("tenKHoursAimDetails/saveDetails")
    public String saveAimDetails(
            @RequestParam Number time,
            @RequestParam String description,
            @RequestParam String date,
            @RequestParam("aimId") TenThousandHoursAim aim,
            Map<String, Object> model) {

        timeService.saveTimeForTenKHoursAim(time, description, date, aim);
        model.put("aims", aimService.getAllLoggedUserAims());
        return "redirect:/tenKHoursAimDetails/" + aim.getId() + "#details";
    }

    @GetMapping("tenKHoursAimDetails/saveDetailsAndContinue/{aim}")
    public String saveAimAndContinue(
            @RequestParam Number time,
            @RequestParam String description,
            @RequestParam String date,
            @PathVariable TenThousandHoursAim aim,
            Map<String, Object> model) {

        timeService.saveTimeForTenKHoursAim(time, description, date, aim);
        model.put("aim", aim);
        return "redirect:/tenKHoursAimDetails#details" + aim.getId();
    }

    @GetMapping("tenKHoursAimDetails/cancel")
    public String cancel(){
        return  "redirect:/ten_thousand_hours_aim#aimsTable";
    }
}
