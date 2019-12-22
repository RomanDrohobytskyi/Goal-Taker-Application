package application.controllers;

import application.entities.aim.Aim;
import application.enums.State;
import application.repositories.IAimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/editAim")
@PreAuthorize("hasAuthority('USER')")
public class EditAimController {

    @Autowired
    private IAimRepository aimRepository;

    @GetMapping("{aim}")
    public String getEditForm(@PathVariable Aim aim, Model model) {
        model.addAttribute("aim", aim);
        return "editAim";
    }

    @PostMapping
    public String saveEditedMessage(
            @RequestParam String title,
            @RequestParam String text,
            @RequestParam String description,
            @RequestParam("aimId") Aim aim) {

        aim.setText(text);
        aim.setDescription(description);
        aim.setTitle(title);
        aim.setAimState(State.AimState.EDITED.toString());

        aimRepository.save(aim);
        return "redirect:/main_aim#aim_" + aim.getId();
    }

    @GetMapping("/cancel")
    public String cancel(){
        return  "redirect:/main_aim#aimsTable";
    }

}
