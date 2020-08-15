package application.test;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AimRepositoryTest extends CrudRepository<SmartAim, Long> {

}