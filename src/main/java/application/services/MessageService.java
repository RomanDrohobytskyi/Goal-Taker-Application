package application.services;

import application.entities.message.Message;
import application.entities.user.User;
import application.enums.State;
import application.managers.UserManager;
import application.repositories.IMessageRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private IMessageRepository messageRepository;
    private UserManager userManager = new UserManager();

    public Optional<Message> adaptMessage(String text, String tag, User user){
        if(Strings.isNotEmpty(text) && Strings.isNotEmpty(tag) && user != null){
            Message message = new Message();
            message.setUser(user);
            message.setText(text);
            message.setTag(tag);
            return Optional.of(message);
        }
        return Optional.empty();
    }

    public void delete(List<Message> messages){
        for (Message message : messages){
            deleteMessage(message);
        }
    }

    public Message deleteMessage(Message message){
        message.setState(State.MessageState.DELETED.toString());

        return messageRepository.save(message);
    }

    public Message achieve(Message message){
        message.setState(State.AimState.ACHIEVED.toString());
        return messageRepository.save(message);
    }

    public Message adaptEditedMessage(Message message, String text, String tag, MultipartFile file) {
        FileService fileService = new FileService();
        fileService.uploadFile(file);
        message.setText(text);
        message.setTag(tag);
        message.setFilename(fileService.getCreatedFileName());
        message.setState(State.MessageState.EDITED.toString());
        message.setFilename(fileService.getCreatedFileName());

        messageRepository.save(message);
        return  message;
    }

    public List<Message> filterMessages(String filter) {
        User loggedInUser = userManager.getLoggedInUser();
        if (StringUtils.isEmpty(filter)) {
            return messageRepository.findByUser(loggedInUser);
        } else {
            return messageRepository.findByTagAndAndUser(filter, loggedInUser);
        }
    }
}
