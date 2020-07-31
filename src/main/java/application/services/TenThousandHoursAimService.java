package application.services;

import application.entities.aim.TenThousandHoursAim;
import application.entities.user.User;

import java.util.List;
import java.util.Optional;

public interface TenThousandHoursAimService {
    Optional<TenThousandHoursAim> createAim(String title, String description, String text, User user);
    Optional<TenThousandHoursAim> adaptAim(String title, String description, String text, User user);
    TenThousandHoursAim adaptEditedAim(TenThousandHoursAim aim, String title, String text, String description);
    TenThousandHoursAim achieve(TenThousandHoursAim aim);
    TenThousandHoursAim deleteAim(TenThousandHoursAim aim);
    void delete(List<TenThousandHoursAim> aims);
    List<TenThousandHoursAim> getAllLoggedUserAims();
    List<TenThousandHoursAim> getAchievedUserAims(User user);
    List<TenThousandHoursAim> getNotDeletedUserAims(User user);
}
