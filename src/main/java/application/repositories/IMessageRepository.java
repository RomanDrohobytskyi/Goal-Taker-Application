package application.repositories;

import application.entities.message.Message;
import application.entities.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IMessageRepository extends CrudRepository<Message, Long> {

    List<Message> findByTagAndAndUser(String tag, User user);
    List<Message> findByUser(User user);
 //find by user V
}
