package application.controllers.message;

import application.entities.message.Message;
import application.entities.user.User;
import application.menu.MenuTabs;
import application.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MessageController {

    @Value("${upload.path}")
    private String uploadPath;
    private final MessageService messageService;

    @GetMapping("/main")
    public String filter(@RequestParam(required = false, defaultValue = "")
                               String filter, Model model) {
        Iterable<Message> messages = messageService.filter(filter);
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        model.addAttribute("menuElements", new MenuTabs().defaultMenu());
        model.addAttribute("slideMenuElements", new MenuTabs().defaultSlideMenu());
        return "main";
    }

    @PostMapping("/main/add")
    public String addMessage(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model,
            @RequestParam(name = "file", required = false, defaultValue = "") MultipartFile file) {

        messageService.addNewMessage(text, tag, file, user);
        List<Message> userMessages = messageService.getLoggedInUserAims();
        model.put("messages", userMessages);
        return "redirect:/main#messagesTable";
    }

    @GetMapping("/main/delete/{message}")
    public String deleteMessage(
            @PathVariable Message message,
            Map<String, Object> model) {
        messageService.delete(message);
        List<Message> userMessages = messageService.getLoggedInUserAims();
        model.put("messages", userMessages);
        return "redirect:/main#messagesTable";
    }

    @PostMapping("/main/deleteMessages")
    public String deleteMessages(
            Map<String, Object> model) {

        List<Message> userMessages = messageService.getLoggedInUserAims();
        model.put("messages", userMessages);
        return "redirect:/main#messagesTable";
    }

    @GetMapping("/main/achieve/{message}")
    public String achieve(@PathVariable Message message){
        messageService.achieve(message);
        return "redirect:/main#messagesTable";
    }

}
