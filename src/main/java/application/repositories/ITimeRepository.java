package application.repositories;

import application.entities.time.data.Time;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITimeRepository extends CrudRepository<Time, Long>{

    List<Time> findByAim_Id(Long aimId);
}
