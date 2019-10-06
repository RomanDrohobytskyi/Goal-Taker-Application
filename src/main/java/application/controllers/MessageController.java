package application.controllers;

import application.entities.Message;
import application.enums.State;
import application.repositories.IMessageRepository;
import application.entities.User;
import application.services.FileService;
import application.services.MessageService;
import application.utils.MapUtils;
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
import java.util.*;

@Controller
public class MessageController {

    @Autowired
    private IMessageRepository messageRepo;
    @Autowired
    private MessageService messageService;
    @Value("${upload.path}")
    private String uploadPath;

    private Iterable<Message> allMessages;
    private Iterable<Message> actualMessages;
    private Iterable<Message> filteredMessages;
    private Iterable<Message> deletedMessages;

    public Iterable<Message> getAllMessages() {
        return allMessages;
    }

    public void setAllMessages(Iterable<Message> allMessages) {
        this.allMessages = allMessages;
    }

    public Iterable<Message> getActualMessages() {
        return actualMessages;
    }

    public void setActualMessages(Iterable<Message> actualMessages) {
        this.actualMessages = actualMessages;
    }

    public Iterable<Message> getFilteredMessages() {
        return filteredMessages;
    }

    public void setFilteredMessages(Iterable<Message> filteredMessages) {
        this.filteredMessages = filteredMessages;
    }

    public Iterable<Message> getDeletedMessages() {
        return deletedMessages;
    }

    public void setDeletedMessages(Iterable<Message> deletedMessages) {
        this.deletedMessages = deletedMessages;
    }

    @GetMapping("/main")
    public String filter(@RequestParam(required = false, defaultValue = "")
                               String filter, Model model) {
        if (filter != null && !filter.isEmpty()) {
            filteredMessages = messageRepo.findByTag(filter);
        } else {
            filteredMessages = messageRepo.findAll();
        }
        model.addAttribute("messages", filteredMessages);
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

            FileService fileService = new FileService();
            fileService.uploadFile(file);
            message.get().setFilename(fileService.getCreatedFileName());

            message.get().setMessageState(State.MessageState.NEW);

            messageRepo.save(message.get());
            Iterable<Message> messages = messageRepo.findAll();
            model.put("messages", messages);
        }else {
            HashMap myMap = MapUtils.oneElementHashMap("","");
            model.put("messages", myMap);
        }
        return "main";
    }

    @PostMapping("/mainDelete")
    public String deleteMessage(@AuthenticationPrincipal User user,
                                Map<String, Object> model) {


        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);

        return "main";
    }

}
