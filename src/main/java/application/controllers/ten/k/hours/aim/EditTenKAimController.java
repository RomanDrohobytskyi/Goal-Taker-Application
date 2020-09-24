package application.controllers.ten.k.hours.aim;

import application.entities.aim.TenThousandHoursAim;
import application.menu.MenuTabs;
import application.services.TenThousandHoursAimService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/editTenKHoursAim")
@PreAuthorize("hasAuthority('USER')")
@RequiredArgsConstructor
public class EditTenKAimController {

    private final TenThousandHoursAimService aimService;

    @GetMapping("{aim}")
    public String getEditForm(@PathVariable TenThousandHoursAim aim, Model model) {
        model.addAttribute("aim", aim);
        model.addAttribute("menuElements", new MenuTabs().defaultMenu());
        model.addAttribute("slideMenuElements", new MenuTabs().defaultSlideMenu());
        return "editTenKHoursAim";
    }

    @PostMapping
    public String saveEditedAim(
            @RequestParam String title,
            @RequestParam String text,
            @RequestParam String description,
            @RequestParam("aimId") TenThousandHoursAim aim) {
        aimService.adaptEditedAim(aim, title, text, description);
        return "redirect:/ten_thousand_hours_aim";
    }

    @GetMapping("/cancel")
    public String cancel(){
        return  "redirect:/ten_thousand_hours_aim#aimsTable";
    }

}
