package application.services.impl;

import application.entities.aim.TenThousandHoursAim;
import application.entities.time.data.TenThousandHoursAimTime;
import application.enums.State;
import application.repositories.ITenThousandHoursAimRepository;
import application.repositories.ITenThousandHoursAimTimeRepository;
import application.services.TenThousandHoursAimTimeService;
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
public class TenThousandHoursAimTimeServiceImpl implements TenThousandHoursAimTimeService {

    private final ITenThousandHoursAimRepository tenThousandHoursAimRepository;
    private final ITenThousandHoursAimTimeRepository iTenThousandHoursAimTimeRepository;

    @Override
    public TenThousandHoursAimTime adaptTime(Double loggedTime, Date date, String description, State.DateState state, TenThousandHoursAim aim){
        TenThousandHoursAimTime time = new TenThousandHoursAimTime();
        time.setTime(loggedTime);
        time.setDate(date);
        time.setDescription(description);
        time.setState(state.toString());
        time.setAim(aim);
        return time;
    }

    @Override
    public List<TenThousandHoursAimTime> getLastWeekTime(Long aimId) {
        List<TenThousandHoursAimTime> aimTime = getLoggedTimeForAim(aimId);
        List<TenThousandHoursAimTime> lastWeekendTime = aimTime;

        if (!CollectionUtils.isEmpty(aimTime) && aimTime.size() >= 7){
            lastWeekendTime = getTimeForDateRange(aimTime, 7L, false);
        }
        return lastWeekendTime;
    }

    @Override
    public List<TenThousandHoursAimTime> getLoggedTimeForAim(Long aimId){
        return iTenThousandHoursAimTimeRepository.findByAim_Id(aimId);
    }

    @Override
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

    @Override
    public TenThousandHoursAimTime deleteTime(TenThousandHoursAimTime time) {
        time.setModificationDate(new Date());
        time.setState(State.DateState.DELETED.toString());
        iTenThousandHoursAimTimeRepository.save(time);
        return time;
    }

    @Override
    public TenThousandHoursAimTime getMostActiveTime(Set<TenThousandHoursAimTime> times){
        Optional<TenThousandHoursAimTime> time = times.stream().max(Comparator.comparing(TenThousandHoursAimTime::getTime));
        return time.get();
    }

    @Override
    public TenThousandHoursAimTime getLessActiveTime(Set<TenThousandHoursAimTime> times){
        Optional<TenThousandHoursAimTime> time = times.stream().min(Comparator.comparing(TenThousandHoursAimTime::getTime));
        return time.get();
    }

    @Override
    public TenThousandHoursAimTime saveTimeForTenKHoursAim(Number time, String description, String date, TenThousandHoursAim aim) {
        TenThousandHoursAimTime newTime = null;
        try {
            Date convertedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            newTime = adaptTime(time.doubleValue(), convertedDate, description, State.DateState.NEW, aim);
            newTime.setCreationDate(new Date());
            iTenThousandHoursAimTimeRepository.save(newTime);
            aim.setLoggedTime(Collections.singleton(newTime));
            tenThousandHoursAimRepository.save(aim);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newTime;
    }

    @Override
    public Set<TenThousandHoursAimTime> getAllLoggedTimeForUserTenThousandHoursAims(List<TenThousandHoursAim> userAims){
        Set<TenThousandHoursAimTime> allAimsLoggedTime = new HashSet<>();
        for (TenThousandHoursAim aim : userAims){
            allAimsLoggedTime.addAll(aim.getLoggedTime());
        }
        return allAimsLoggedTime;
    }

    @Override
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

    @Override
    public Double getAimLoggedTimeSum(Set<TenThousandHoursAimTime> time){
        return time
            .stream()
            .mapToDouble(TenThousandHoursAimTime::getTime)
            .sum();
    }

    @Override
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