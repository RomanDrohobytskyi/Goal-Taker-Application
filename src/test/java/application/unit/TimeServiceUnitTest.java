package application.unit;

import application.entities.time.data.Time;
import application.enums.State;
import application.repositories.AimRepository;
import application.repositories.TimeRepository;
import application.services.time.SmartAimTimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TimeServiceUnitTest {

    @Mock
    private AimRepository aimRepository;
    @Mock
    private TimeRepository timeRepository;
    @InjectMocks
    private SmartAimTimeService timeService;
    private List<Time> times;

    @BeforeEach
    void init(){
        times = List.of(
                timeService.adaptTime(Double.parseDouble("2"), new Date(), "Test Time object 1", State.Date.NEW, null),
                timeService.adaptTime(Double.parseDouble("3.5"), new Date(), "Test Time object 2", State.Date.NEW, null)
        );
        times.get(0).setId(1L);
        times.get(1).setId(2L);
    }

    @Test
    void adaptTime() {
        Time time = timeService.adaptTime(Double.parseDouble("2.5"), new Date(), "Test Time object", State.Date.NEW, null);

        assertAll("time",
                () -> assertNotNull(time),
                () -> assertEquals(Double.parseDouble("2.5"), time.getTime()),
                () -> assertEquals("Test Time object", time.getDescription()),
                () -> assertEquals(State.Date.NEW.toString(), time.getState()),
                () -> assertNull(time.getAim())
        );
    }

    @Test
    void getAimLoggedTimeSum(){
        assertEquals(Double.parseDouble("5.5"), timeService.getAimLoggedTimeSum(new HashSet<>(times)));
    }

    @Test
    void getMostActiveTime(){
        Time mostActive = timeService.getMostActiveTime(new HashSet<>(times));

        assertThrows(NullPointerException.class, () -> timeService.getMostActiveTime(null));
        assertThrows(IllegalArgumentException.class, () -> timeService.getMostActiveTime(new HashSet<>()));
        assertEquals(mostActive.getId(), Long.valueOf(2));
    }

    @Test
    void getLessActiveTime(){
        Time lessActive = timeService.getLessActiveTime(new HashSet<>(times));
        assertEquals(lessActive.getId(), Long.valueOf(1));
    }

}
