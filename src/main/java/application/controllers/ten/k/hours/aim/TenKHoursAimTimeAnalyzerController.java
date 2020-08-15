package application.controllers.ten.k.hours.aim;

import application.entities.aim.TenThousandHoursAim;
import application.entities.time.data.TenThousandHoursAimTime;
import application.menu.MenuTabs;
import application.repositories.ITenThousandHoursAimRepository;
import application.services.TenThousandHoursAimTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/tenKAimTimeAnalyzer")
@PreAuthorize("hasAuthority('USER')")
@RequiredArgsConstructor
public class TenKHoursAimTimeAnalyzerController {

    private final TenThousandHoursAimTimeService timeService;
    private final ITenThousandHoursAimRepository aimRepository;

    @GetMapping
    @RequestMapping("/{aim}")
    public String getEditForm(@PathVariable TenThousandHoursAim aim, Model model) {
        List<TenThousandHoursAimTime> lastSevenDaysTime = timeService.getLastWeekTime(aim.getId());
        Set<TenThousandHoursAimTime> loggedTime = aim.getLoggedTime();
        model.addAttribute("aim", aim);
        model.addAttribute("loggedTime", loggedTime);
        model.addAttribute("lessProductive", timeService.getLessActiveTime(loggedTime));
        model.addAttribute("mostProductive", timeService.getMostActiveTime(loggedTime));
        model.addAttribute("lastSevenDaysTime", lastSevenDaysTime);
        model.addAttribute("loggedTimeSum", timeService.getAimLoggedTimeSum(loggedTime));
        model.addAttribute("menuElements", new MenuTabs().timeAnalyzerMenu());
        model.addAttribute("slideMenuElements", new MenuTabs().defaultSlideMenu());
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
