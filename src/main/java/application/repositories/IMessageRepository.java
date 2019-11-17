package application.repositories;

import application.entities.message.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IMessageRepository extends CrudRepository<Message, Long> {

    List<Message> findByTag(String tag);
 //find by user V
}
