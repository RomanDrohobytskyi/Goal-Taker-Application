package application.services.impl;

import application.entities.aim.Aim;
import application.entities.time.data.Time;
import application.enums.State;
import application.repositories.IAimRepository;
import application.repositories.ITimeRepository;
import application.services.TimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class TimeServiceImpl implements TimeService {

    private final ITimeRepository iTimeRepository;
    private final IAimRepository aimRepository;
    private final ITimeRepository timeRepository;

    @Override
    public Time adaptTime(Double loggedTime, Date date, String description, State.DateState state, Aim aim){
        Time time = new Time();
        time.setTime(loggedTime);
        time.setDate(date);
        time.setDescription(description);
        time.setState(state.toString());
        time.setAim(aim);

        return time;
    }

    @Override
    public List<Time> getLastWeekTime(Long aimId) {
        List<Time> aimTime = getLoggedTimeForAim(aimId);
        List<Time> lastWeekendTime = aimTime;
        if (!CollectionUtils.isEmpty(aimTime) && aimTime.size() >= 7)
            lastWeekendTime = getTimeForDateRange(aimTime, 7L, false);

        return lastWeekendTime;
    }

    @Override
    public List<Time> getLoggedTimeForAim(Long aimId){
        return iTimeRepository.findByAim_Id(aimId);
    }

    @Override
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

    @Override
    public Time deleteTime(Time time) {
        time.setModificationDate(new Date());
        time.setState(State.DateState.DELETED.toString());
        iTimeRepository.save(time);
        return time;
    }

    @Override
    public Time getMostActiveTime(Set<Time> times){
        Optional<Time> time = times.stream().max(Comparator.comparing(Time::getTime));
        return time.get();
    }

    @Override
    public Time getLessActiveTime(Set<Time> times){
        Optional<Time> time = times.stream().min(Comparator.comparing(Time::getTime));
        return time.get();
    }

    @Override
    public Set<Time> getAllLoggedTimeForUserAims(List<Aim> userAims){
        Set<Time> allAimsLoggedTime = new HashSet<>();
        for (Aim aim : userAims){
            allAimsLoggedTime.addAll(aim.getLoggedTime());
        }
        return allAimsLoggedTime;
    }

    @Override
    public Aim getMostActiveAim(List<Aim> userAims){
        Aim maxTimeAim = null;
        Double maxTime = 0D;
        for (Aim aim : userAims){
            Double sum = aim.getLoggedTime().stream()
                    .mapToDouble(Time::getTime)
                    .sum();
            if (sum > maxTime){
                maxTime = sum;
                maxTimeAim = aim;
            }
        }
        return maxTimeAim;
    }

    @Override
    public Double getAimLoggedTimeSum(Set<Time> loggedTime) {
        return loggedTime
                .stream()
                .mapToDouble(Time::getTime)
                .sum();
    }

    @Override
    public Optional<Time> saveTimeForAim(Number time, String description, String date, Aim aim) {
        Optional<Time> newTime = Optional.empty();
        try {
            Date convertedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            newTime = Optional.of(adaptTime(time.doubleValue(), convertedDate, description, State.DateState.NEW, aim));
            newTime.get().setCreationDate(new Date());
            timeRepository.save(newTime.get());
            aim.setLoggedTime(Collections.singleton(newTime.get()));
            aimRepository.save(aim);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newTime;
    }
}
