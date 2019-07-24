package application.repositories;

import application.entities.Aim;
import org.springframework.data.repository.CrudRepository;

public interface IAimRepository extends CrudRepository<Aim, Integer> {
}
