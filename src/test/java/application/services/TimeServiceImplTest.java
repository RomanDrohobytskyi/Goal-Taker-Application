package application.services;

import application.entities.time.data.Time;
import application.enums.State;
import application.services.impl.TimeServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TimeServiceTest")
class TimeServiceImplTest {

    @Autowired
    private static TimeServiceImpl timeService;
    private static List<Time> times;

    @BeforeAll
    static void initialize(){
        times = new ArrayList<>(Arrays.asList(
                timeService.adaptTime(new Double("2"), new Date(), "Test Time object 1", State.DateState.NEW, null),
                timeService.adaptTime(new Double("3.5"), new Date(), "Test Time object 2", State.DateState.NEW, null)
        ));
        times.get(0).setId(1L);
        times.get(1).setId(2L);
    }

    @Test
    void adaptTime() {
        Time time = timeService.adaptTime(new Double("2.5"), new Date(), "Test Time object", State.DateState.NEW, null);
        assertNotNull(time, "Object Time is NULL in adaptTime() test.");
        assertAll("time",
                () -> assertEquals(new Double("2.5"), time.getTime()),
                () -> assertEquals("Test Time object", time.getDescription()),
                () -> assertEquals(State.DateState.NEW.toString(), time.getState()),
                () -> assertNull(time.getAim())
        );
    }

    @Test
    void getAimLoggedTimeSum(){
        assertEquals(new Double("5.5"), timeService.getAimLoggedTimeSum(new HashSet<>(times)));
    }

    @Test
    void getMostActiveTime(){
        Time mostActive = timeService.getMostActiveTime(new HashSet<>(times));
        assertEquals(mostActive.getId(), Long.valueOf(2));
    }

    @Test
    void getLessActiveTime(){
        Time lessActive = timeService.getLessActiveTime(new HashSet<>(times));
        assertEquals(lessActive.getId(), Long.valueOf(1));
    }
}