package application.controllers.ten.k.hours.aim;

import application.entities.aim.TenThousandHoursAim;
import application.entities.time.data.TenThousandHoursAimTime;
import application.entities.user.User;
import application.managers.UserManager;
import application.repositories.ITenThousandHoursAimRepository;
import application.services.TenThousandHoursAimService;
import application.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class TenThousandHoursAimController {

    @Autowired
    private ITenThousandHoursAimRepository aimRepository;
    private UserManager userManager = new UserManager();
    @Autowired
    private TenThousandHoursAimService aimService;

    @GetMapping("/ten_thousand_hours_aim")
    public String allAims(Model model){
        User loggedInUser = userManager.getLoggedInUser();
        List<TenThousandHoursAim> userAims = aimRepository.findByUser(loggedInUser);

        model.addAttribute("all_aims", userAims);
        model.addAttribute("timeSum", getSumOfAllLoggedTime(
                new ArrayList<>(loggedInUser.getTenThousandHoursAims())));

        return "ten_thousand_hours_aim";
    }

    @GetMapping("/ten_thousand_hours_aim/add")
    public String addAim(
            @AuthenticationPrincipal User user,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String text,
            Map<String, Object> model) {

        Optional<TenThousandHoursAim> aimOptional = aimService.adaptAim(title, description, text, user);
        if (aimOptional.isPresent()){
            TenThousandHoursAim aim = aimOptional.get();
            aimRepository.save(aim);
            Iterable<TenThousandHoursAim> userAims = user.getTenThousandHoursAims();
            model.put("aims", userAims);
        }
        else {
            HashMap myMap = MapUtils.oneElementHashMap("", "");
            model.put("aims", myMap);
        }
        return "redirect:/ten_thousand_hours_aim#aimsTable";
    }

    @GetMapping("/ten_thousand_hours_aim/delete/{aim}")
    public String deleteMessage(
            @PathVariable TenThousandHoursAim aim,
            Map<String, Object> model) {

        aimService.deleteAim(aim);
        User loggedInUser = userManager.getLoggedInUser();
        Iterable<TenThousandHoursAim> userAims = aimRepository.findByUser(loggedInUser);

        model.put("aims", userAims);

        return "redirect:/ten_thousand_hours_aim#aimsTable";
    }

    @GetMapping("/ten_thousand_hours_aim/achieve/{aim}")
    public String achieve(@PathVariable TenThousandHoursAim aim, Map<String, Object> model){
        aimService.achieve(aim);

        return "redirect:/ten_thousand_hours_aim#aimsTable";
    }

    public Map<TenThousandHoursAim, Double> getSumOfAllLoggedTime(List<TenThousandHoursAim> aims){
        return aims == null ? Collections.emptyMap() :
                aims.stream()
                        .collect(Collectors.toMap(Function.identity(),
                                aim -> aim.getLoggedTime().stream()
                                .mapToDouble(TenThousandHoursAimTime::getTime)
                                        .sum()));
    }

}
