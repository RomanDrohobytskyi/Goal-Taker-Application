package application.controllers.ten.k.hours.aim;

import application.entities.aim.TenThousandHoursAim;
import application.entities.time.data.TenThousandHoursAimTime;
import application.repositories.ITenThousandHoursAimRepository;
import application.services.TenThousandHoursAimTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tenKAimTimeAnalyzer")
@PreAuthorize("hasAuthority('USER')")
public class TenKHoursAimTimeAnalyzerController {

    @Autowired
    private TenThousandHoursAimTimeService timeService;
    @Autowired
    private ITenThousandHoursAimRepository aimRepository;

    @GetMapping
    @RequestMapping("/{aim}")
    public String getEditForm(@PathVariable TenThousandHoursAim aim, Model model) {
        List<TenThousandHoursAimTime> lastSevenDaysTime = timeService.getLastWeekTime(aim.getId());
        List<TenThousandHoursAimTime> loggedTime = timeService.getLoggedTimeForAim(aim.getId());
        model.addAttribute("aim", aim);
        model.addAttribute("loggedTime", loggedTime);
        model.addAttribute("lessProductive", timeService.getLessActiveTime(loggedTime));
        model.addAttribute("mostProductive", timeService.getMostActiveTime(loggedTime));
        model.addAttribute("lastSevenDaysTime", lastSevenDaysTime);
        return "time_analyzer";
    }

    @GetMapping("/{aim}/delete/{time}")
    public String deleteMessage(
            @PathVariable TenThousandHoursAimTime time,
            @PathVariable TenThousandHoursAim aim,
            Map<String, Object> model) {

        timeService.deleteTime(time);
        TenThousandHoursAim reloadedAim = (aimRepository.findById(aim.getId())).get();
        model.put("aim", reloadedAim);
        model.put("logged_time", reloadedAim.getLoggedTime());

        return "redirect:/analyzer/" + aim.getId()+ "#timeTable";
    }
}
