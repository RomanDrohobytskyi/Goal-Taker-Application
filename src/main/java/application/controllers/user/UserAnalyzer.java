package application.controllers.user;

import application.managers.UserManager;
import application.services.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("userAnalyzer")
@PreAuthorize("hasAuthority('USER')")
public class UserAnalyzer {

    @Autowired
    private TimeService timeService;
    private UserManager userManager = new UserManager();

    @GetMapping
    @RequestMapping("/activity")
    public String getUserAnalyzer(Model model){

        //User loggedInUser = userManager.getLoggedInUser();
        //Time time = timeService.getMostActiveTime(loggedInUser);

        return "user_analyzer";
    }
}
