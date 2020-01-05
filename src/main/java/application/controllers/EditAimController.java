package application.controllers;

import application.entities.aim.Aim;
import application.enums.State;
import application.repositories.IAimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

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
            @RequestParam String specific,
            @RequestParam String measurable,
            @RequestParam String attainable,
            @RequestParam String relevant,
            @RequestParam String timeBased,
            @RequestParam("aimId") Aim aim) {

        try {
            aim.setText(text);
            aim.setDescription(description);
            aim.setTitle(title);
            aim.setAimState(State.AimState.EDITED.toString());
            aim.setSpecify(specific);
            aim.setMeasurable(measurable);
            aim.setAttainable(attainable);
            aim.setRelevant(relevant);
            aim.setTimeBased(new SimpleDateFormat("yyyy-MM-dd").parse(timeBased));
            aim.setModificationDate(new Date());
            aim.setAimState(State.AimState.EDITED.toString());

            aimRepository.save(aim);
        } catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/main_aim#aim_" + aim.getId();
    }

    @GetMapping("/cancel")
    public String cancel(){
        return  "redirect:/main_aim#aimsTable";
    }

}
