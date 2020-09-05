package application.services;

import application.entities.message.Message;
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

    public Optional<Message> adaptMessage(String text, String tag, User user){
        if(Strings.isNotEmpty(text) && Strings.isNotEmpty(tag) && user != null){
            Message message = new Message();
            message.setState(State.MessageState.NEW.toString());
            message.setUser(user);
            message.setText(text);
            message.setTag(tag);
            return Optional.of(message);
        }
        return Optional.empty();
    }

    public void delete(List<Message> messages){
        for (Message message : messages)
            delete(message);
    }

    public Message delete(Message message){
        message.setState(State.MessageState.DELETED.toString());
        return messageRepository.save(message);
    }

    public Message achieve(Message message){
        message.setState(State.AimState.ACHIEVED.toString());
        return messageRepository.save(message);
    }

    public Message adaptEditedMessage(Message message, String text, String tag, MultipartFile file) {
        fileService.uploadFile(file);
        message.setText(text);
        message.setTag(tag);
        message.setFilename(fileService.getCreatedFileName());
        message.setState(State.MessageState.EDITED.toString());
        message.setFilename(fileService.getCreatedFileName());

        messageRepository.save(message);
        return  message;
    }

    public List<Message> filter(String filter) {
        User loggedInUser = userManager.getLoggedInUser();
        if (StringUtils.isEmpty(filter))
            return messageRepository.findByUser(loggedInUser);
        else
            return messageRepository.findByTagAndAndUser(filter, loggedInUser);
    }

    public Message save(Message message){
        return messageRepository.save(message);
    }

    public List<Message> findByUser(User user){
        return messageRepository.findByUser(user);
    }

    public Message adaptMessageAsNote(String message, String userName,  String userEmail) {
        String messageText = "User " + userEmail + ", " + userName + ", left note: " + message;
        String tag = "User Note";
        return adaptMessage(messageText, tag, getMessageAsNoteReceiver())
                .orElseThrow(IllegalArgumentException::new);
    }

    public User getMessageAsNoteReceiver() {
        return Optional.of(userService.findUserByEmail("romabikebmx@gmail.com"))
                .orElseThrow(IllegalArgumentException::new);
    }

    public Message sendMessageAsNote(Message message) {
        return save(message);
    }
}
