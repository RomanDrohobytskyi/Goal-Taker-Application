package application.services;


import application.entities.aim.TenThousandHoursAim;
import application.entities.time.data.TenThousandHoursAimTime;
import application.enums.State;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TenThousandHoursAimTimeService {
    TenThousandHoursAimTime adaptTime(Double loggedTime, Date date, String description, State.DateState state, TenThousandHoursAim aim);
    TenThousandHoursAimTime deleteTime(TenThousandHoursAimTime time);
    TenThousandHoursAimTime getMostActiveTime(Set<TenThousandHoursAimTime> times);
    TenThousandHoursAimTime getLessActiveTime(Set<TenThousandHoursAimTime> times);
    TenThousandHoursAimTime saveTimeForTenKHoursAim(Number time, String description, String date, TenThousandHoursAim aim);
    TenThousandHoursAim getMostActiveAim(List<TenThousandHoursAim> userAims);
    List<TenThousandHoursAimTime> getLastWeekTime(Long aimId);
    List<TenThousandHoursAimTime> getTimeForDateRange(List<TenThousandHoursAimTime> time, Long dayRange, boolean asc);
    List<TenThousandHoursAimTime> getLoggedTimeForAim(Long aimId);
    Set<TenThousandHoursAimTime> getAllLoggedTimeForUserTenThousandHoursAims(List<TenThousandHoursAim> userAims);
    Map<Long, Double> getAimsLoggedTimeSum(List<TenThousandHoursAim> aims);
    Double getAimLoggedTimeSum(Set<TenThousandHoursAimTime> time);
}
