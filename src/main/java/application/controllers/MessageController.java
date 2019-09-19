package application.controllers;

import application.entities.Message;
import application.enums.State;
import application.repositories.IMessageRepository;
import application.entities.User;
import application.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
public class MessageController {

    @Autowired
    private IMessageRepository messageRepo;
    @Autowired
    private MessageService messageService;
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/main")
    public String filter(@RequestParam(required = false, defaultValue = "")
                               String filter, Model model) {
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String addMessage(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model,
            @RequestParam("file") MultipartFile file) {

        Optional<Message> message = messageService.adaptMessage(text, tag, user);

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

                message.get().setMessageState(State.MessageState.NEW);

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
        return "main";
    }

    @PostMapping("/mainDelete")
    public String deleteMessage(@AuthenticationPrincipal User user,
                                Map<String, Object> model) {

        for (int i = 0; i < 100; i++) {
            System.out.println("******************************");
        }


        return "mainDelete";
    }

}
