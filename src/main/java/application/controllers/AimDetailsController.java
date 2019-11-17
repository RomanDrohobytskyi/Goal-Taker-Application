package application.controllers;

import application.entities.aim.Aim;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aim_details")
public class AimDetailsController {

    ///main_aim
    @GetMapping("{aim}")
    @PreAuthorize("hasAuthority('USER')")
    public String aimDetails(@PathVariable Aim aim, Model model){
        model.addAttribute("aim", aim);

        return "aim_details";
    }
}
