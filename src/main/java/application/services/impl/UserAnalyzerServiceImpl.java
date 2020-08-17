package application.services.impl;

import application.entities.aim.Aim;
import application.entities.aim.TenThousandHoursAim;
import application.entities.time.data.TenThousandHoursAimTime;
import application.entities.time.data.Time;
import application.entities.user.User;
import application.managers.UserManager;
import application.menu.MenuTabs;
import application.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserAnalyzerServiceImpl implements UserAnalyzerService {

    private final TimeService timeService;
    private final TenThousandHoursAimTimeService aimTimeService;
    private final TenThousandHoursAimService tenThousandHoursAimService;
    private final AimService aimService;
    private final UserManager userManager = new UserManager();

    @Override
    public void addAnalyzerData(Model model) {
        User loggedInUser = userManager.getLoggedInUser();
        addSmartAimDetails(model, loggedInUser);
        addTenThousandHoursRuleAimDetails(model, loggedInUser);
        model.addAttribute("user", loggedInUser);
        model.addAttribute("menuElements", new MenuTabs().userAnalyzerMenuItems());
        model.addAttribute("slideMenuElements", new MenuTabs().defaultSlideMenu());
    }

    private void addTenThousandHoursRuleAimDetails(Model model, User user) {
        List<TenThousandHoursAim> userAims = new ArrayList<>(user.getTenThousandHoursAims());
        Set<TenThousandHoursAimTime> aimsTime = aimTimeService.getAllLoggedTimeForUserTenThousandHoursAims(userAims);
        Double sum = aimTimeService.getAimLoggedTimeSum(aimsTime);
        TenThousandHoursAimTime mostActiveTime = aimTimeService.getMostActiveTime(aimsTime);
        TenThousandHoursAimTime lessActiveTime = aimTimeService.getLessActiveTime(aimsTime);
        TenThousandHoursAim mostActiveTenThousandAim = aimTimeService.getMostActiveAim(userAims);
        List<TenThousandHoursAimTime> lastSevenDaysTime = aimTimeService.getLastWeekTime(mostActiveTenThousandAim.getId());
        List<TenThousandHoursAim> achievedAims = tenThousandHoursAimService.getAchievedUserAims(user);
        List<TenThousandHoursAim> notDeletedTenKdAims = tenThousandHoursAimService.getNotDeletedUserAims(user);

        model.addAttribute("tenThousandHoursAimSum", sum);
        model.addAttribute("tenThousandHoursAimMostProductive", mostActiveTime);
        model.addAttribute("tenThousandHoursAimLessProductive",lessActiveTime);
        model.addAttribute("mostActiveTenThousandAim", mostActiveTenThousandAim);
        model.addAttribute("tenKLoggedTime", mostActiveTenThousandAim.getLoggedTime());
        model.addAttribute("tenKLastSevenDays", lastSevenDaysTime);
        model.addAttribute("achieveTenKdAims", achievedAims);
        model.addAttribute("notDeletedTenKdAims", notDeletedTenKdAims);
    }

    private void addSmartAimDetails(Model model, User user){
        List<Aim> userSmartAims = new ArrayList<>(user.getAims());
        Set<Time> smartAimsTime = timeService.getAllLoggedTimeForUserAims(userSmartAims);
        Aim mostActiveSmartAim = timeService.getMostActiveAim(userSmartAims);
        Double sum = timeService.getAimLoggedTimeSum(smartAimsTime);
        Time mostActiveTime = timeService.getMostActiveTime(smartAimsTime);
        Time lessActiveTime = timeService.getLessActiveTime(smartAimsTime);
        List<Time> lastSevenDaysTime = timeService.getLastWeekTime(mostActiveSmartAim.getId());
        List<Aim> achievedAims = aimService.getAchievedUserAims(user);
        List<Aim> notDeletedAims = aimService.getNotDeletedUserAims(user);

        model.addAttribute("sum", sum);
        model.addAttribute("mostProductive", mostActiveTime);
        model.addAttribute("lessProductive", lessActiveTime);
        model.addAttribute("lastSevenDaysTime", lastSevenDaysTime);
        model.addAttribute("mostActiveSmartAim", mostActiveSmartAim);
        model.addAttribute("loggedTime", mostActiveSmartAim.getLoggedTime());
        model.addAttribute("achievedAims", achievedAims);
        model.addAttribute("notDeletedAims", notDeletedAims);
    }
}
