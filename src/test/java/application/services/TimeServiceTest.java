package application.services;

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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TimeServiceTest {

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
                timeService.adaptTime(new Double("2"), new Date(), "Test Time object 1", State.Date.NEW, null),
                timeService.adaptTime(new Double("3.5"), new Date(), "Test Time object 2", State.Date.NEW, null)
        );
        times.get(0).setId(1L);
        times.get(1).setId(2L);
    }

    @Test
    void isNotEmpty(){
        assertNotNull(times);
        assertAll("Assert ids not null",
                () -> assertTrue(times.size() == 2),
                () -> assertNotNull(times.get(0)),
                () -> assertNotNull(times.get(1)),
                () -> assertNotNull(times.get(0).getId()),
                () -> assertNotNull(times.get(0).getId()));
    }


}
