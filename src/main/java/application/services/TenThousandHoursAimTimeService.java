package application.services;

import application.entities.aim.TenThousandHoursAim;
import application.entities.time.data.TenThousandHoursAimTime;
import application.enums.State;
import application.repositories.ITenThousandHoursAimRepository;
import application.repositories.ITenThousandHoursAimTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
@RequiredArgsConstructor
public class TenThousandHoursAimTimeService {

    private final ITenThousandHoursAimRepository tenThousandHoursAimRepository;
    private final ITenThousandHoursAimTimeRepository iTenThousandHoursAimTimeRepository;

    public TenThousandHoursAimTime adaptTime(Double loggedTime, Date date, String description, State.Date state, TenThousandHoursAim aim){
        TenThousandHoursAimTime time = new TenThousandHoursAimTime();
        time.setTime(loggedTime);
        time.setDate(date);
        time.setDescription(description);
        time.setState(state.toString());
        time.setAim(aim);
        return time;
    }

    public List<TenThousandHoursAimTime> getLastWeekTime(Long aimId) {
        List<TenThousandHoursAimTime> aimTime = getLoggedTimeForAim(aimId);
        List<TenThousandHoursAimTime> lastWeekendTime = aimTime;

        if (!CollectionUtils.isEmpty(aimTime) && aimTime.size() >= 7){
            lastWeekendTime = getTimeForDateRange(aimTime, 7L, false);
        }
        return lastWeekendTime;
    }

    public List<TenThousandHoursAimTime> getLoggedTimeForAim(Long aimId){
        return iTenThousandHoursAimTimeRepository.findByAim_Id(aimId);
    }

    public List<TenThousandHoursAimTime> getTimeForDateRange(List<TenThousandHoursAimTime> time, Long dayRange, boolean asc){
        if (asc){
            return time.stream()
                    .sorted(Comparator.comparing(TenThousandHoursAimTime::getDate))
                    .limit(dayRange)
                    .collect(toList());
        }
        else return time.stream()
                .sorted(Comparator.comparing(TenThousandHoursAimTime::getDate).reversed())
                .limit(dayRange)
                .collect(toList());
    }

    public TenThousandHoursAimTime deleteTime(TenThousandHoursAimTime time) {
        time.setModificationDate(new Date());
        time.setState(State.Date.DELETED.toString());
        iTenThousandHoursAimTimeRepository.save(time);
        return time;
    }

    public TenThousandHoursAimTime getMostActiveTime(Set<TenThousandHoursAimTime> times){
        Optional<TenThousandHoursAimTime> time = times.stream().max(Comparator.comparing(TenThousandHoursAimTime::getTime));
        return time.get();
    }

    public TenThousandHoursAimTime getLessActiveTime(Set<TenThousandHoursAimTime> times){
        Optional<TenThousandHoursAimTime> time = times.stream().min(Comparator.comparing(TenThousandHoursAimTime::getTime));
        return time.get();
    }

    public TenThousandHoursAimTime saveTimeForTenKHoursAim(Number time, String description, String date, TenThousandHoursAim aim) {
        TenThousandHoursAimTime newTime = null;
        try {
            Date convertedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            newTime = adaptTime(time.doubleValue(), convertedDate, description, State.Date.NEW, aim);
            newTime.setCreationDate(new Date());
            iTenThousandHoursAimTimeRepository.save(newTime);
            aim.setLoggedTime(Collections.singleton(newTime));
            tenThousandHoursAimRepository.save(aim);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newTime;
    }

    public Set<TenThousandHoursAimTime> getAllLoggedTimeForUserTenThousandHoursAims(List<TenThousandHoursAim> userAims){
        Set<TenThousandHoursAimTime> allAimsLoggedTime = new HashSet<>();
        for (TenThousandHoursAim aim : userAims){
            allAimsLoggedTime.addAll(aim.getLoggedTime());
        }
        return allAimsLoggedTime;
    }

    public Map<Long, Double> getAimsLoggedTimeSum(List<TenThousandHoursAim> aims){
        return aims
            .stream()
                .collect(toMap(TenThousandHoursAim::getId,
                    aim -> aim.getLoggedTime()
                        .stream()
                        .mapToDouble(TenThousandHoursAimTime::getTime)
                        .sum())
                );
    }

    public Double getAimLoggedTimeSum(Set<TenThousandHoursAimTime> time){
        return time
            .stream()
            .mapToDouble(TenThousandHoursAimTime::getTime)
            .sum();
    }

    public TenThousandHoursAim getMostActiveAim(List<TenThousandHoursAim> userAims) {
        TenThousandHoursAim maxTimeAim = null;
        Double maxTime = 0D;
        for (TenThousandHoursAim aim : userAims){
            Double sum = aim.getLoggedTime().stream()
                    .mapToDouble(TenThousandHoursAimTime::getTime)
                    .sum();
            if (sum > maxTime){
                maxTime = sum;
                maxTimeAim = aim;
            }
        }
        return maxTimeAim;
    }
}