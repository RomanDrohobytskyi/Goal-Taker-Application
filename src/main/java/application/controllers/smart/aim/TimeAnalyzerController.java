package application.controllers.smart.aim;

import application.entities.aim.Aim;
import application.entities.time.data.Time;
import application.menu.MenuTabs;
import application.repositories.IAimRepository;
import application.services.TimeService;
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
@RequestMapping("/analyzer")
@PreAuthorize("hasAuthority('USER')")
public class TimeAnalyzerController {

    @Autowired
    private TimeService timeService;
    @Autowired
    private IAimRepository aimRepository;

    @GetMapping
    @RequestMapping("/{aim}")
    public String getEditForm(@PathVariable Aim aim, Model model) {
        List<Time> lastSevenDaysTime = timeService.getLastWeekTime(aim.getId());
        List<Time> loggedTime = timeService.getLoggedTimeForAim(aim.getId());
        model.addAttribute("aim", aim);
        model.addAttribute("loggedTime", loggedTime);
        model.addAttribute("mostProductive", timeService.getMostActiveTime(loggedTime));
        model.addAttribute("lessProductive", timeService.getLessActiveTime(loggedTime));
        model.addAttribute("lastSevenDaysTime", lastSevenDaysTime);
        model.addAttribute("loggedTimeSum", timeService.getAimLoggedTimeSum(loggedTime));
        model.addAttribute("menuElements", new MenuTabs().timeAnalyzerMenu());
        model.addAttribute("slideMenuElements", new MenuTabs().defaultSlideMenu());
        return "time_analyzer";
    }

    @GetMapping("/{aim}/delete/{time}")
    public String deleteMessage(
            @PathVariable Time time,
            @PathVariable Aim aim,
            Map<String, Object> model) {

        timeService.deleteTime(time);
        Aim reloadedAim = (aimRepository.findById(aim.getId())).get();
        model.put("aim", reloadedAim);
        model.put("logged_time", reloadedAim.getLoggedTime());

        return "redirect:/analyzer/" + aim.getId()+ "#timeTable";
    }
}
