package application.services;

import application.entities.aim.Aim;
import application.entities.time.data.Time;
import application.enums.State;
import application.repositories.IAimRepository;
import application.repositories.ITimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class TimeService {

    @Autowired
    private ITimeRepository iTimeRepository;
    @Autowired
    private IAimRepository aimRepository;

    public Time adaptTime(Double loggedTime, Date date, String description, State.DateState state, Aim aim){
        Time time = new Time();
        time.setTime(loggedTime);
        time.setDate(date);
        time.setDescription(description);
        time.setState(state.toString());
        time.setAim(aim);

        return time;
    }

    /**
     * Getting sorted logged-in Aim time for last 7 days
     * @param aimId - Aim id
     * @return - sorted aims for last 7 days
     */
    public List<Time> getLastWeekTime(Long aimId) {
        List<Time> aimTime = getLoggedTimeForAim(aimId);
        List<Time> lastWeekendTime = aimTime;

        if (!CollectionUtils.isEmpty(aimTime) && aimTime.size() >= 7){
            lastWeekendTime = getTimeForDateRange(aimTime, 7L, false);
        }
        return lastWeekendTime;
    }

    public List<Time> getLoggedTimeForAim(Long aimId){
        return iTimeRepository.findByAim_Id(aimId);
    }

    public List<Time> getTimeForDateRange(List<Time> time, Long dayRange, boolean asc){
        if (asc){
            return time.stream()
                    .sorted(Comparator.comparing(Time::getDate))
                    .limit(dayRange)
                    .collect(toList());
        }
        else return time.stream()
                .sorted(Comparator.comparing(Time::getDate).reversed())
                .limit(dayRange)
                .collect(toList());
    }

    public Time deleteTime(Time time) {
        time.setModificationDate(new Date());
        time.setState(State.DateState.DELETED.toString());
        iTimeRepository.save(time);
        return time;
    }

/*    public Time getMostActiveTime(User user){
        Set<Aim> aims  = user.getAims();
        List<Time> allLoggedTimes = getAllLoggedTimeForUserAims(new ArrayList<>(aims));
        Time time = allLoggedTimes.stream().max(Comparator.comparing(Time::getTime)).get();
        return time;
    }*/

    public Time getMostActiveTime(List<Time> times){
        Optional<Time> time = times.stream().max(Comparator.comparing(Time::getTime));
        return time.get();
    }
    public Time getLessActiveTime(List<Time> times){
        Optional<Time> time = times.stream().min(Comparator.comparing(Time::getTime));
        return time.get();
    }

    public List<Time> getAllLoggedTimeForUserAims(List<Aim> userAims){
        List<Time> allAimsLoggedTime = new ArrayList<>();
        for (Aim aim : userAims){
            allAimsLoggedTime.addAll(aim.getLoggedTime());
        }
        return allAimsLoggedTime;
    }
}
