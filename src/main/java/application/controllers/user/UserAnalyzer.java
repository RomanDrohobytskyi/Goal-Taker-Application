package application.controllers.user;

import application.services.UserAnalyzerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("userAnalyzer")
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
@RequiredArgsConstructor
public class UserAnalyzer {

    private final UserAnalyzerService userAnalyzerService;

    @GetMapping
    @RequestMapping("/activity")
    public String getUserAnalyzer(Model model){
        userAnalyzerService.addAnalyzerData(model);
        return "user_analyzer";
    }
}
