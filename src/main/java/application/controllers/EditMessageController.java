package application.controllers;

import application.entities.Message;
import application.enums.State;
import application.repositories.IMessageRepository;
import application.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/editMessage")
public class EditMessageController {

    @Autowired
    private IMessageRepository messageRepository;

    @GetMapping("{message}")
    @PreAuthorize("hasAuthority('USER')")
    public String getEditForm(@PathVariable Message message, Model model){
        model.addAttribute("message", message);
        return "editMessage";
    }

    @PostMapping
    public String saveEditedMessage(
            @RequestParam String text,
            @RequestParam String tag,
            @RequestParam("file") MultipartFile file,
            @RequestParam("messageId") Message message) {

        FileService fileService = new FileService();
        fileService.uploadFile(file);

        message.setText(text);
        message.setTag(tag);
        message.setFilename(fileService.getCreatedFileName());
        message.setMessageState(State.MessageState.EDITED.toString());

        messageRepository.save(message);
        return "redirect:/main#message_" + message.getId();
    }


    @GetMapping("/cancel")
    public String cancel(){
        return  "redirect:/main#messagesTable";
    }

/*    @GetMapping("delete")
    @PreAuthorize("hasAuthority('USER')")
    public String getDeleteForm(@PathVariable Message message, Model model){

        message.setText("deleted");
        message.setTag("deleted");
        message.setMessageState(State.MessageState.DELETED.toString());

        messageRepository.save(message);

        model.addAttribute("message", message);
        return "redirect:/greeting";
    }*/

}
