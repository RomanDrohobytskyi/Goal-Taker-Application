package application.repositories;

import application.entities.time.data.TenThousandHoursAimTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenThousandHoursAimTimeRepository extends CrudRepository<TenThousandHoursAimTime, Long> {
    List<TenThousandHoursAimTime> findByAim_Id(Long aimId);
}
