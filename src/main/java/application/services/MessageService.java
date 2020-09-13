package application.services;

import application.entities.user.User;
import application.enums.State;
import application.managers.UserManager;
import application.repositories.IMessageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final UserManager userManager = new UserManager();
    private final IMessageRepository messageRepository;
    private final FileService fileService;
    private final UserService userService;

    public Optional<application.entities.message.Message> adaptMessage(String text, String tag, User user){
        if(Strings.isNotEmpty(text) && Strings.isNotEmpty(tag) && user != null){
            application.entities.message.Message message = new application.entities.message.Message();
            message.setState(State.Message.NEW.toString());
            message.setUser(user);
            message.setText(text);
            message.setTag(tag);
            return Optional.of(message);
        }
        return Optional.empty();
    }

    public void delete(List<application.entities.message.Message> messages){
        for (application.entities.message.Message message : messages)
            delete(message);
    }

    public application.entities.message.Message delete(application.entities.message.Message message){
        message.setState(State.Message.DELETED.toString());
        return messageRepository.save(message);
    }

    public application.entities.message.Message achieve(application.entities.message.Message message){
        message.setState(State.Aim.ACHIEVED.toString());
        return messageRepository.save(message);
    }

    public application.entities.message.Message adaptEditedMessage(application.entities.message.Message message, String text, String tag, MultipartFile file) {
        fileService.uploadFile(file);
        message.setText(text);
        message.setTag(tag);
        message.setFilename(fileService.getCreatedFileName());
        message.setState(State.Message.EDITED.toString());
        message.setFilename(fileService.getCreatedFileName());

        messageRepository.save(message);
        return  message;
    }

    public List<application.entities.message.Message> filter(String filter) {
        User loggedInUser = userManager.getLoggedInUser();
        if (StringUtils.isEmpty(filter))
            return messageRepository.findByUser(loggedInUser);
        else
            return messageRepository.findByTagAndAndUser(filter, loggedInUser);
    }

    public application.entities.message.Message save(application.entities.message.Message message){
        return messageRepository.save(message);
    }

    public List<application.entities.message.Message> findByUser(User user){
        return messageRepository.findByUser(user);
    }

    public application.entities.message.Message adaptMessageAsNote(String message, String userName, String userEmail) {
        String messageText = "User " + userEmail + ", " + userName + ", left note: " + message;
        String tag = "User Note";
        return adaptMessage(messageText, tag, getMessageAsNoteReceiver())
                .orElseThrow(IllegalArgumentException::new);
    }

    public User getMessageAsNoteReceiver() {
        return Optional.of(userService.findUserByEmail("romabikebmx@gmail.com"))
                .orElseThrow(IllegalArgumentException::new);
    }

    public application.entities.message.Message sendMessageAsNote(application.entities.message.Message message) {
        return save(message);
    }
}
