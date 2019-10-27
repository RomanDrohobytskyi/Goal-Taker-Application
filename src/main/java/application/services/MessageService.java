package application.services;

import application.entities.Message;
import application.entities.User;
import application.enums.State;
import application.repositories.IMessageRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private IMessageRepository messageRepository;

    private Iterable<Message> allMessages;
    private Iterable<Message> actualMessages;
    private Iterable<Message> filteredMessages;
    private Iterable<Message> deletedMessages;

    public Iterable<Message> getAllMessages() {
        if(this.allMessages == null){
            allMessages = messageRepository.findAll();
        }
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

    public Message deleteMessage(Message message){
        message.setMessageState(State.MessageState.DELETED.toString());

        return messageRepository.save(message);
    }

}
