package application.services;

import application.entities.aim.Aim;
import application.entities.time.data.Time;
import application.enums.State;
import application.repositories.ITimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TimeService {

    @Autowired
    private ITimeRepository iTimeRepository;

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
        List<Time> lastWeekendTime =aimTime;
        if (!CollectionUtils.isEmpty(aimTime) && aimTime.size() >= 7){
            lastWeekendTime = getTimeForDateRange(aimTime, 7L, false);
        } else if (!CollectionUtils.isEmpty(aimTime)){
            lastWeekendTime = sortTimeByDateDescending(aimTime);
        }

        return lastWeekendTime;
    }

    public List<Time> getLoggedTimeForAim(Long aimId){
        return iTimeRepository.findByAim_Id(aimId);
    }

    public List<Time> getTimeForDateRange(List<Time> time, Long dayRange, boolean asc){
        if (asc){
            List<Time> sorted = sortTimeByDateAscending(time).stream().limit(dayRange).collect(Collectors.toList());
            return sorted;
        }
        else {
            List<Time> sorted = sortTimeByDateDescending(time).stream().limit(dayRange).collect(Collectors.toList());
            return sorted;
        }
    }

    public List<Time> sortTimeByDateAscending(List<Time> time){

       Collections.sort(time, new Comparator<Time>() {
            @Override
            public int compare(Time t1, Time t2) {
                return t1.getDate().compareTo(t2.getDate());
            }
        });
        return time;
    }


    public List<Time> sortTimeByDateDescending(List<Time> time){

        //users.sort(Comparator.comparing(User::getCreatedOn).reversed());
        Collections.sort(time, new Comparator<Time>() {
            @Override
            public int compare(Time t1, Time t2) {
                return t2.getDate().compareTo(t1.getDate());
            }
        });
        return time;
    }
}
