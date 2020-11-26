package application.services.time;

import application.entities.aim.Aim;
import application.entities.time.data.Time;
import application.enums.State;
import application.repositories.TimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class SmartAimTimeService {

    private final TimeRepository timeRepository;

    public Optional<Time> adaptAndSaveAimDetails(Number loggedTime, String date, String description, Aim aim) {
        Optional<Time> time = adaptTimeWithStringDate(loggedTime, date, description, State.Date.NEW, aim);
        time.ifPresent(this::save);
        return time;
    }

    public Optional<Time> adaptTimeWithStringDate(Number loggedTime, String date, String description, State.Date state, Aim aim){
        try {
            Date convertedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            return Optional.of(adaptTime(loggedTime.doubleValue(), convertedDate, description, state, aim));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Time adaptTime(Double loggedTime, Date date, String description, State.Date state, Aim aim) {
        return Time.builder()
                .time(loggedTime)
                .date(date)
                .creationDate(date)
                .description(description)
                .state(state.toString())
                .aim(aim)
                .build();
    }

    public Time save(Time time) {
        return timeRepository.save(time);
    }

    public List<Time> getLastWeekTime(Long aimId) {
        List<Time> aimTime = getLoggedTimeForAim(aimId);
        List<Time> lastWeekendTime = aimTime;
        if (!CollectionUtils.isEmpty(aimTime) && aimTime.size() >= 7) {
            lastWeekendTime = getTimeForDateRange(aimTime, 7);
        }

        return lastWeekendTime;
    }

    public List<Time> getLoggedTimeForAim(Long aimId){
        return timeRepository.findByAim_Id(aimId);
    }

    public List<Time> getTimeForDateRange(List<Time> time, int dayRange){
        return time.stream()
                .sorted(Comparator.comparing(Time::getDate).reversed())
                .limit(dayRange)
                .collect(toList());
    }

    public Time deleteTime(Time time) {
        time.setModificationDate(new Date());
        time.setState(State.Date.DELETED.toString());
        timeRepository.save(time);
        return time;
    }

    public Time getMostActiveTime(Set<Time> times) {
        return times.stream()
                .max(Comparator.comparing(Time::getTime))
                .orElseThrow(IllegalArgumentException :: new);
    }

    public Time getLessActiveTime(Set<Time> times){
        Optional<Time> time = times.stream()
                .min(Comparator.comparing(Time::getTime));
        return time.get();
    }

    public Set<Time> getAllLoggedTimeForUserAims(List<Aim> userAims){
        Set<Time> allAimsLoggedTime = new HashSet<>();
        for (Aim aim : userAims){
            allAimsLoggedTime.addAll(aim.getLoggedTime());
        }
        return allAimsLoggedTime;
    }

    public Double getAimLoggedTimeSum(Set<Time> loggedTime) {
        return loggedTime
                .stream()
                .mapToDouble(Time::getTime)
                .sum();
    }

}
