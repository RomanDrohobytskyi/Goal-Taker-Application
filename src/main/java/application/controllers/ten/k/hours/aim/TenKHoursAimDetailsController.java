package application.controllers.ten.k.hours.aim;

import application.entities.aim.TenThousandHoursAim;
import application.entities.time.data.TenThousandHoursAimTime;
import application.entities.user.User;
import application.managers.UserManager;
import application.repositories.ITenThousandHoursAimRepository;
import application.services.TenThousandHoursAimTimeService;
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
public class TenKHoursAimDetailsController {

    @Autowired
    private ITenThousandHoursAimRepository aimRepository;
    @Autowired
    private TenThousandHoursAimTimeService timeService;
    private UserManager userManager = new UserManager();

    @GetMapping("tenKHoursAimDetails/{aim}")
    public String aimDetails(@PathVariable TenThousandHoursAim aim, Model model){
        List<TenThousandHoursAimTime> lastWeekLoggedTime = timeService.getLastWeekTime(aim.getId());
        model.addAttribute("aim", aim);
        model.addAttribute("lastWeekLoggedTime", lastWeekLoggedTime);
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
        User loggedInUser = userManager.getLoggedInUser();
        Iterable<TenThousandHoursAim> userAims = aimRepository.findByUser(loggedInUser);
        model.put("aims", userAims);
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
