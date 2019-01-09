package application.message;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IMessageRepository extends CrudRepository<Message, Integer> {
    List<Message> findByTag(String tag);
}
