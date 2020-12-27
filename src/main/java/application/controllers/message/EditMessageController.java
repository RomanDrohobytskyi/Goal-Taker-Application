package application.controllers.message;

import application.entities.message.Message;
import application.menu.MenuTabs;
import application.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/editMessage")
@RequiredArgsConstructor
public class EditMessageController {

    private final MessageService messageService;

    @GetMapping("{message}")
    @PreAuthorize("hasAuthority('USER')")
    public String getEditForm(@PathVariable Message message, Model model){
        model.addAttribute("message", message);
        model.addAttribute("menuElements", MenuTabs.defaultMenu());
        model.addAttribute("slideMenuElements", MenuTabs.defaultSlideMenu());
        return "editMessage";
    }

    @PostMapping
    public String saveEditedMessage(
            @RequestParam String text,
            @RequestParam String tag,
            @RequestParam("file") MultipartFile file,
            @RequestParam("messageId") Message message) {
        messageService.adaptEditedMessage(message, text, tag, file);
        return "redirect:/main#message_" + message.getId();
    }

    @GetMapping("/cancel")
    public String cancel(){
        return  "redirect:/main#messagesTable";
    }

}
