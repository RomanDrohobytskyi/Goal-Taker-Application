package application.services;

import application.entities.message.Message;
import application.entities.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    Optional<Message> adaptMessage(String text, String tag, User user);
    Message achieve(Message message);
    Message adaptEditedMessage(Message message, String text, String tag, MultipartFile file);
    Message save(Message message);
    Message delete(Message message);
    void delete(List<Message> messages);
    List<Message> filter(String filter);
    List<Message> findByUser(User user);

}
