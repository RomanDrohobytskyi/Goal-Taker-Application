package application.services;

import application.entities.aim.TenThousandHoursAim;
import application.entities.time.data.TenThousandHoursAimTime;
import application.enums.State;
import application.repositories.ITenThousandHoursAimRepository;
import application.repositories.ITenThousandHoursAimTimeRepository;
import application.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class TenThousandHoursAimTimeService {

    @Autowired
    private ITenThousandHoursAimRepository tenThousandHoursAimRepository;
    @Autowired
    private ITenThousandHoursAimTimeRepository iTenThousandHoursAimTimeRepository;

    public TenThousandHoursAimTime adaptTime(Double loggedTime, Date date, String description, State.DateState state, TenThousandHoursAim aim){
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
        time.setState(State.DateState.DELETED.toString());
        iTenThousandHoursAimTimeRepository.save(time);
        return time;
    }

    public TenThousandHoursAimTime getMostActiveTime(List<TenThousandHoursAimTime> times){
        Optional<TenThousandHoursAimTime> time = times.stream().max(Comparator.comparing(TenThousandHoursAimTime::getTime));
        return time.get();
    }
    public TenThousandHoursAimTime getLessActiveTime(List<TenThousandHoursAimTime> times){
        Optional<TenThousandHoursAimTime> time = times.stream().min(Comparator.comparing(TenThousandHoursAimTime::getTime));
        return time.get();
    }

    public TenThousandHoursAimTime saveTimeForTenKHoursAim(Number time, String description, String date, TenThousandHoursAim aim) {
        TenThousandHoursAimTime newTime = null;
        try {
            Date convertedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            newTime = adaptTime(time.doubleValue(), convertedDate, description, State.DateState.NEW, aim);
            newTime.setCreationDate(new Date());
            iTenThousandHoursAimTimeRepository.save(newTime);
            aim.setLoggedTime(ListUtils.oneElementArrayList(newTime));
            tenThousandHoursAimRepository.save(aim);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newTime;
    }

}
