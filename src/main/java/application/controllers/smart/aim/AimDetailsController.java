package application.controllers.smart.aim;

import application.entities.aim.Aim;
import application.entities.time.data.Time;
import application.entities.user.User;
import application.enums.State;
import application.managers.UserManager;
import application.repositories.IAimRepository;
import application.repositories.ITimeRepository;
import application.services.TimeService;
import application.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('USER')")
public class AimDetailsController {

    @Autowired
    private IAimRepository aimRepository;
    @Autowired
    private ITimeRepository timeRepository;
    @Autowired
    private TimeService timeService;
    private UserManager userManager = new UserManager();
    private List<Time> lastWeekLoggedTime = new ArrayList<>();

    @GetMapping("aim_details/{aim}")
    public String aimDetails(@PathVariable Aim aim, Model model){
        lastWeekLoggedTime = timeService.getLastWeekTime(aim.getId());
        model.addAttribute("aim", aim);
        model.addAttribute("lastWeekLoggedTime", lastWeekLoggedTime);
        return "aim_details";
    }

    @GetMapping("aim_details/saveDetails")
    public String saveAimDetails(
            @RequestParam Number time,
            @RequestParam String description,
            @RequestParam String date,
            @RequestParam("aimId") Aim aim,
            Map<String, Object> model) {

        saveTimeForAim(time, description, date, aim);

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

        saveTimeForAim(time, description, date, aim);

        model.put("aim", aim);

        return "redirect:/aim_details#details" + aim.getId();
    }

    @GetMapping("/cancel")
    public String cancel(){
        return  "redirect:/main_aim#aimsTable";
    }

    private void saveTimeForAim(Number time, String description, String date, Aim aim){
        try {
            Date convertedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            Time newTime = timeService.adaptTime(time.doubleValue(),convertedDate, description, State.DateState.NEW, aim);
            newTime.setCreationDate(new Date());
            timeRepository.save(newTime);
            aim.setLoggedTime(ListUtils.oneElementArrayList(newTime));
            aimRepository.save(aim);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
