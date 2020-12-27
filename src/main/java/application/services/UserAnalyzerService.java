package application.services;

import application.entities.aim.Aim;
import application.entities.aim.TenThousandHoursAim;
import application.entities.time.data.TenThousandHoursAimTime;
import application.entities.time.data.Time;
import application.entities.user.User;
import application.managers.UserManager;
import application.menu.MenuTabs;
import application.services.aim.SmartAimService;
import application.services.aim.TenThousandHoursAimService;
import application.services.time.SmartAimTimeService;
import application.services.time.TenThousandHoursAimTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserAnalyzerService {

    private final SmartAimTimeService timeService;
    private final TenThousandHoursAimTimeService aimTimeService;
    private final TenThousandHoursAimService tenThousandHoursAimService;
    private final SmartAimService aimService;
    private final UserManager userManager = new UserManager();

    public void addAnalyzerData(Model model) {
        User loggedInUser = userManager.getLoggedInUser();
        addSmartAimDetails(model, loggedInUser);
        addTenThousandHoursRuleAimDetails(model, loggedInUser);
        model.addAttribute("user", loggedInUser);
        model.addAttribute("menuElements", MenuTabs.userAnalyzerMenuItems());
        model.addAttribute("slideMenuElements", MenuTabs.defaultSlideMenu());
    }

    private void addSmartAimDetails(Model model, User user){
        List<Aim> userSmartAims = new ArrayList<>(user.getAims());
        if (!CollectionUtils.isEmpty(userSmartAims)) {
            Set<Time> smartAimsTime = timeService.getAllLoggedTimeForUserAims(userSmartAims);
            Aim mostActiveSmartAim = aimService.getMostActiveAim(userSmartAims);

            model.addAttribute("sum", timeService.getAimLoggedTimeSum(smartAimsTime));
            model.addAttribute("mostProductive", timeService.getMostActiveTime(smartAimsTime));
            model.addAttribute("lessProductive", timeService.getLessActiveTime(smartAimsTime));
            model.addAttribute("lastSevenDaysTime", timeService.getLastWeekTime(mostActiveSmartAim.getId()));
            model.addAttribute("mostActiveSmartAim", mostActiveSmartAim);
            model.addAttribute("loggedTime", mostActiveSmartAim.getLoggedTime());
            model.addAttribute("achievedAims", aimService.getAchievedUserAims(user));
            model.addAttribute("notDeletedAims", aimService.getNotDeletedUserAims(user));
        }
    }

    private void addTenThousandHoursRuleAimDetails(Model model, User user) {
        List<TenThousandHoursAim> userAims = new ArrayList<>(user.getTenThousandHoursAims());
        if (!CollectionUtils.isEmpty(userAims)) {
            Set<TenThousandHoursAimTime> aimsTime = aimTimeService
                    .getAllLoggedTimeForUserTenThousandHoursAims(userAims);
            TenThousandHoursAim mostActiveTenThousandAim = aimTimeService.getMostActiveAim(userAims);

            model.addAttribute("tenThousandHoursAimSum", aimTimeService.getAimLoggedTimeSum(aimsTime));
            model.addAttribute("tenThousandHoursAimMostProductive", aimTimeService.getMostActiveTime(aimsTime));
            model.addAttribute("tenThousandHoursAimLessProductive", aimTimeService.getLessActiveTime(aimsTime));
            model.addAttribute("mostActiveTenThousandAim", mostActiveTenThousandAim);
            model.addAttribute("tenKLoggedTime", mostActiveTenThousandAim.getLoggedTime());
            model.addAttribute("tenKLastSevenDays", aimTimeService.getLastSevenLoggedTimesForAim(mostActiveTenThousandAim.getId()));
            model.addAttribute("achieveTenKdAims", tenThousandHoursAimService.getAchievedUserAims(user));
            model.addAttribute("notDeletedTenKdAims", tenThousandHoursAimService.getNotDeletedUserAims(user));
        }
    }
}
