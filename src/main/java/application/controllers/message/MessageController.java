package application.controllers.message;

import application.entities.message.Message;
import application.entities.user.User;
import application.enums.State;
import application.managers.UserManager;
import application.menu.MenuTabs;
import application.repositories.IMessageRepository;
import application.services.FileService;
import application.services.MessageService;
import application.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Optional;

@Controller
public class MessageController {

    @Autowired
    private IMessageRepository messageRepo;
    @Autowired
    private MessageService messageService;
    @Value("${upload.path}")
    private String uploadPath;
    private UserManager userManager = new UserManager();

    @GetMapping("/main")
    public String filter(@RequestParam(required = false, defaultValue = "")
                               String filter, Model model) {
        Iterable<Message> messages = messageService.filterMessages(filter);
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

        Optional<Message> optionalMessage = messageService.adaptMessage(text, tag, user);

        if (optionalMessage.isPresent()){

            Message message = optionalMessage.get();
            FileService fileService = new FileService();
            fileService.uploadFile(file);
            message.setFilename(fileService.getCreatedFileName());
            message.setState(State.MessageState.NEW.toString());

            messageRepo.save(message);

            User loggedInUser = userManager.getLoggedInUser();
            Iterable<Message> userMessages = messageRepo.findByUser(loggedInUser);

            model.put("messages", userMessages);
        }else {
            model.put("messages", MapUtils.oneElementHashMap("",""));
        }
        return "redirect:/main#messagesTable";
    }

    @GetMapping("/main/delete/{message}")
    public String deleteMessage(
            @PathVariable Message message,
            Map<String, Object> model) {

        messageService.deleteMessage(message);

        User loggedInUser = userManager.getLoggedInUser();
        Iterable<Message> userMessages = messageRepo.findByUser(loggedInUser);
        model.put("messages", userMessages);

        return "redirect:/main#messagesTable";
    }

    @GetMapping("/main/achieve/{message}")
    public String achieve(@PathVariable Message message){
        messageService.achieve(message);
        return "redirect:/main#messagesTable";
    }

}
