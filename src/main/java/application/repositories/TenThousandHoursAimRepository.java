package application.repositories;

import application.entities.aim.TenThousandHoursAim;
import application.entities.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenThousandHoursAimRepository extends CrudRepository<TenThousandHoursAim, Long> {
    List<TenThousandHoursAim> findByUser(User user);
    List<TenThousandHoursAim> findTenThousandHoursAimsByAimStateAndUser(String aimState, User user);
    List<TenThousandHoursAim> findAimsByAimStateIsNotLikeAndUser(String aimStateNotLike, User user);
}
