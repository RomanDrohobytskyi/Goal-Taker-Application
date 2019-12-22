package application.controllers;

import application.entities.aim.Aim;
import application.entities.time.data.Time;
import application.managers.UserManager;
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
    private UserManager userManager = new UserManager();
    private List<Time> lastSevenDaysTime;

    @GetMapping
    @RequestMapping("/{aim}")
    public String getEditForm(@PathVariable Aim aim, Model model) {
        lastSevenDaysTime = timeService.getLastWeekTime(aim.getId());
        model.addAttribute("aim", aim);
        model.addAttribute("logged_time", aim.getLoggedTime());
        model.addAttribute("lastSevenDaysTime", lastSevenDaysTime);
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
