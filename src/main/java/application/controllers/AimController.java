package application.controllers;

import application.entities.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AimController {

    @GetMapping("/main_aim")
    public String filter(@RequestParam(required = false, defaultValue = "")String filter, Model model){

        return "main_aim";
    }

    @PostMapping("/main_aim")
    public String addMessage(
            @AuthenticationPrincipal User user,
            @RequestParam String title,
            @RequestParam String description,
            Map<String, Object> model) {

        /*
        Optional<Message> message = adaptMessage(text, tag, user);

        if (message.isPresent()){
            try {
                if (file != null && !file.getOriginalFilename().isEmpty()){
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()){
                        uploadDir.mkdir();
                    }
                    String uuidFile = UUID.randomUUID().toString();
                    String resultFileName = uuidFile + "." + file.getOriginalFilename();

                    file.transferTo(new File(uploadPath + "/" + resultFileName));

                    message.get().setFilename(resultFileName);
                }

                messageRepo.save(message.get());
                Iterable<Message> messages = messageRepo.findAll();
                model.put("messages", messages);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            HashMap myMap = new HashMap() {{
                put("", "");
            }};

            model.put("messages", myMap);
        }
        */
        return "main_aim";
    }
}
