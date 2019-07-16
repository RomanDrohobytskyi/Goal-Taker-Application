package application.message;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMessageRepository extends CrudRepository<Message, Integer> {
    List<Message> findByTag(String tag);
}
