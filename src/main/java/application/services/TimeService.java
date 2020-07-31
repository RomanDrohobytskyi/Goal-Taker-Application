package application.services;

import application.entities.aim.Aim;
import application.entities.time.data.Time;
import application.enums.State;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TimeService {
    Optional<Time> saveTimeForAim(Number time, String description, String date, Aim aim);
    Time adaptTime(Double loggedTime, Date date, String description, State.DateState state, Aim aim);
    Time deleteTime(Time time);
    Time getMostActiveTime(Set<Time> times);
    Time getLessActiveTime(Set<Time> times);
    Aim getMostActiveAim(List<Aim> userAims);
    Set<Time> getAllLoggedTimeForUserAims(List<Aim> userAims);
    Double getAimLoggedTimeSum(Set<Time> loggedTime);
    List<Time> getLastWeekTime(Long aimId);
    List<Time> getLoggedTimeForAim(Long aimId);
    List<Time> getTimeForDateRange(List<Time> time, Long dayRange, boolean asc);
}
