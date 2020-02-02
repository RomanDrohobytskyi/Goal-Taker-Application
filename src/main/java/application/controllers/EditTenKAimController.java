package application.controllers;

import application.entities.aim.TenThousandHoursAim;
import application.enums.State;
import application.repositories.ITenThousandHoursAimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/editTenKHoursAim")
@PreAuthorize("hasAuthority('USER')")
public class EditTenKAimController {

    @Autowired
    private ITenThousandHoursAimRepository aimRepository;

    @GetMapping("{aim}")
    public String getEditForm(@PathVariable TenThousandHoursAim aim, Model model) {
        model.addAttribute("aim", aim);
        return "editTenKHoursAim";
    }

    @PostMapping
    public String saveEditedAim(
            @RequestParam String title,
            @RequestParam String text,
            @RequestParam String description,
            @RequestParam("aimId") TenThousandHoursAim aim) {

        try {
            aim.setTitle(title);
            aim.setDescription(description);
            aim.setText(text);
            aim.setModificationDate(new Date());
            aim.setAimState(State.AimState.EDITED.toString());

            aimRepository.save(aim);
        } catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/ten_thousand_hours_aim#aim_" + aim.getId();
    }

    @GetMapping("/cancel")
    public String cancel(){
        return  "redirect:/ten_thousand_hours_aim#aimsTable";
    }

}
