package application.services;

import application.entities.message.Message;
import application.entities.user.User;
import application.enums.State;
import application.managers.UserManager;
import application.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private UserManager userManager = new UserManager();
    private final MessageRepository messageRepository;
    private final FileService fileService;
    private final UserService userService;

    public Optional<Message> buildMessage(String text, String tag, MultipartFile file, User user){
        if(Strings.isNotEmpty(text) && Strings.isNotEmpty(tag) && user != null){
            String filename = fileService.uploadFile(file);
            Message message = new Message.MessageBuilder(text, tag, user)
                    .state(State.Message.NEW.toString())
                    .filename(filename)
                    .build();
            return Optional.of(message);
        }
        return Optional.empty();
    }

    public Optional<Message> addNewMessage(String text, String tag, MultipartFile file, User user) {
        Optional<Message> message = buildMessage(text, tag, file, user);
        message.ifPresent(this::save);
        return message;
    }

    public void delete(List<Message> messages){
        for (Message message : messages)
            delete(message);
    }

    public Message delete(Message message){
        message.setState(State.Message.DELETED.toString());
        return messageRepository.save(message);
    }

    public Message achieve(Message message){
        message.setState(State.Aim.ACHIEVED.toString());
        return messageRepository.save(message);
    }

    public Message adaptEditedMessage(Message message, String text, String tag, MultipartFile file) {
        fileService.uploadFile(file);
        message.setText(text);
        message.setTag(tag);
        message.setFilename(fileService.getCreatedFileName());
        message.setState(State.Message.EDITED.toString());
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
        List<Message> messages = messageRepository.findByUser(user);
        return CollectionUtils.isEmpty(messages) ? Collections.EMPTY_LIST : messages;
    }

    public List<Message> getLoggedInUserAims() {
        User loggedInUser = userManager.getLoggedInUser();
        return findByUser(loggedInUser);
    }

    public Message adaptMessageAsNote(String message, String userName, String userEmail) {
        String messageText = "User " + userEmail + ", " + userName + ", left note: " + message;
        String tag = "User Note";
        return buildMessage(messageText, tag, null, getMessageAsNoteReceiver())
                .orElseThrow(IllegalArgumentException::new);
    }

    public User getMessageAsNoteReceiver() {
        return Optional.of(userService.findUserByEmail("roman.drohobytskyi@gmail.com"))
                .orElseThrow(IllegalArgumentException::new);
    }

    public Message sendMessageAsNote(Message message) {
        return save(message);
    }
}
