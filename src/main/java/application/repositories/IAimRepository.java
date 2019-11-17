package application.repositories;

import application.entities.aim.Aim;
import org.springframework.data.repository.CrudRepository;

public interface IAimRepository extends CrudRepository<Aim, Long> {

}
