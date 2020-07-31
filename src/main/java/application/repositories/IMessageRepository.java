package application.repositories;

import application.entities.message.Message;
import application.entities.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMessageRepository extends CrudRepository<Message, Long> {
    List<Message> findByTagAndAndUser(String tag, User user);
    List<Message> findByUser(User user);
}
