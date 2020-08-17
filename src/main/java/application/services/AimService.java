package application.services;

import application.entities.aim.Aim;
import application.entities.user.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AimService {
    Aim achieve(Aim aim);
    Aim delete(Aim aim);
    Aim edit(String title, String text, String description, String specific, String measurable, String attainable,
             String relevant,String timeBased,Aim aim);
    Optional<Aim> adapt(String title, String description, String text, String specific, String measurable,
             String attainable, String relevant, Date timeBased, User user);
    List<Aim> getAchievedUserAims(User user);
    List<Aim> getNotDeletedUserAims(User user);
    Optional<Date> parseDate(String timeBased);
    void delete(List<Aim> aims);
}
