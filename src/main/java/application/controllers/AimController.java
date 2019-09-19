package application.controllers;

import application.entities.Aim;
import application.entities.Message;
import application.entities.User;
import application.repositories.IAimRepository;
import application.services.AimService;
import application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class AimController {

    @Autowired
    private AimService aimService;
    @Autowired
    private IAimRepository aimRepository;

    @GetMapping("/main_aim")
    public String allAims(Model model){
        Iterable<Aim> aims = aimRepository.findAll();
        model.addAttribute("all_aims", aims);
        return "main_aim";
    }

    @PostMapping("/main_aim")
    public String addAim(
            @AuthenticationPrincipal User user,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String text,
            Map<String, Object> model) {

        Optional<Aim> aimOptional = aimService.adaptAim(title, description, text, user);
        if (aimOptional.isPresent()){
            try{
                Aim aim = aimOptional.get();
                aimRepository.save(aim);

                Iterable<Aim> aims = aimRepository.findAll();
                model.put("aims", aims);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "main_aim";
    }

}
