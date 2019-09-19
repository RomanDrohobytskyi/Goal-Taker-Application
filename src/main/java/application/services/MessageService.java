package application.services;

import application.entities.Message;
import application.entities.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {

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

    public void print(){
        System.out.println("Here we go!");
    }
}
