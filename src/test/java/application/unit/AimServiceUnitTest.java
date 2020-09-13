package application.unit;

import application.entities.aim.Aim;
import application.entities.time.data.Time;
import application.enums.State;
import application.services.AimService;
import application.services.TimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AimServiceUnitTest {

    @Autowired
    private AimService aimService;
    @Autowired
    private TimeService timeService;
    private List<application.entities.aim.Aim> aims;


    @BeforeEach
    void createAimsWithTimes(){
        application.entities.aim.Aim aim1 = new application.entities.aim.Aim();
        ArrayList<Time> times1 = new ArrayList<>(Arrays.asList(
                timeService.adaptTime(new Double("2"), new Date(), "Test Time object 1",
                        State.Date.NEW, aim1),
                timeService.adaptTime(new Double("3.5"), new Date(), "Test Time object 2",
                        State.Date.NEW, aim1)
        ));
        times1.get(0).setId(1L);
        times1.get(1).setId(2L);
        aim1.setLoggedTime(new HashSet<>(times1));

        application.entities.aim.Aim aim2 = new application.entities.aim.Aim();
        ArrayList<Time> times2 = new ArrayList<>(Arrays.asList(
                timeService.adaptTime(new Double("1"), new Date(), "Test Time object 3",
                        State.Date.NEW, aim2),
                timeService.adaptTime(new Double("1.5"), new Date(), "Test Time object 4",
                        State.Date.NEW, aim2)
        ));
        aim2.setLoggedTime(new HashSet<>(times2));
        times2.get(0).setId(3L);
        times2.get(1).setId(4L);

        this.aims = new ArrayList<>(Arrays.asList(aim1, aim2));
    }

    @Test
    void mostActiveAim() {
        application.entities.aim.Aim mostActiveAim = aimService.getMostActiveAim(aims);

        assertEquals(mostActiveAim, aims.get(0));
    }

    @Test
    void parsedDatePresentAndCorrect(){
        String date = "2020-02-20";
        Optional<Date> parsedDate = aimService.parseDate(date);
        assertTrue(parsedDate.isPresent());

        assertEquals("Thu Feb 20 00:00:00 CET 2020", parsedDate.get().toString());
    }

    @Test
    void parseWrongDate(){
        assertEquals(Optional.empty(), aimService.parseDate("213"));
    }

    @Test
    void builderTest(){
        Aim aim = new Aim.AimBuilder("Test title", "Description", "Text")
                .build();

    }
}
