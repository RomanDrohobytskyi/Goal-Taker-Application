package application.services;

import application.entities.aim.Aim;
import application.entities.time.data.Time;
import application.enums.State;
import application.repositories.IAimRepository;
import application.repositories.ITenThousandHoursAimRepository;
import application.repositories.ITimeRepository;
import application.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class TimeService {

    @Autowired
    private ITimeRepository iTimeRepository;
    @Autowired
    private IAimRepository aimRepository;
    @Autowired
    private ITenThousandHoursAimRepository tenThousandHoursAimRepository;
    @Autowired
    private ITimeRepository timeRepository;

    public Time adaptTime(Double loggedTime, Date date, String description, State.DateState state, Aim aim){
        Time time = new Time();
        time.setTime(loggedTime);
        time.setDate(date);
        time.setDescription(description);
        time.setState(state.toString());
        time.setAim(aim);

        return time;
    }

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

    public Optional<Time> saveTimeForAim(Number time, String description, String date, Aim aim) {
        Optional<Time> newTime = Optional.empty();
        try {
            Date convertedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            newTime = Optional.of(adaptTime(time.doubleValue(), convertedDate, description, State.DateState.NEW, aim));
            newTime.get().setCreationDate(new Date());
            timeRepository.save(newTime.get());
            aim.setLoggedTime(ListUtils.oneElementArrayList(newTime.get()));
            aimRepository.save(aim);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newTime;
    }

}
