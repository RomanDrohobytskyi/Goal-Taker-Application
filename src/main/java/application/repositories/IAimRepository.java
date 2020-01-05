package application.repositories;

import application.entities.aim.Aim;
import application.entities.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IAimRepository extends CrudRepository<Aim, Long> {
    List<Aim> findByUser(User user);
}
