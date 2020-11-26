package application.services;

import application.entities.aim.TenThousandHoursAim;
import application.entities.time.data.TenThousandHoursAimTime;
import application.enums.State;
import application.services.time.TenThousandHoursAimTimeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TenThousandHoursAimTimeServiceTest {

    @Autowired
    private TenThousandHoursAimTimeService tenThousandHoursAimTimeService;

    @Test
    void getMostActiveAim() {
        Set<TenThousandHoursAimTime> timeForFirstAim = new HashSet<>();
        timeForFirstAim.add(tenThousandHoursAimTimeService.adaptTime(new Double("2"), new Date(), "Test Time object 1", State.Date.NEW, null));
        timeForFirstAim.add(tenThousandHoursAimTimeService.adaptTime(new Double("3.5"), new Date(), "Test Time object 2", State.Date.NEW, null));


        Set<TenThousandHoursAimTime> timeForSecondAim  = new HashSet<>();
        timeForSecondAim.add(tenThousandHoursAimTimeService.adaptTime(new Double("4"), new Date(), "Test Time object 3", State.Date.NEW, null));
        timeForSecondAim.add(tenThousandHoursAimTimeService.adaptTime(new Double("3.5"), new Date(), "Test Time object 4", State.Date.NEW, null));

        TenThousandHoursAim lessActiveTimeAim = TenThousandHoursAim.builder()
                .id(1L)
                .loggedTime(timeForFirstAim)
                .build();
        TenThousandHoursAim moreActiveTimeAim = TenThousandHoursAim.builder()
                .id(2L)
                .loggedTime(timeForSecondAim)
                .build();

        TenThousandHoursAim shouldBeMoreActive = tenThousandHoursAimTimeService.getMostActiveAim(List.of(lessActiveTimeAim, moreActiveTimeAim));
        assertThat(shouldBeMoreActive).isEqualTo(moreActiveTimeAim);

    }

}
