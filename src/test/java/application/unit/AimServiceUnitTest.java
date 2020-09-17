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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AimServiceUnitTest {

    @Autowired
    private AimService aimService;
    @Autowired
    private TimeService timeService;
    private List<Aim> aims;


    private List<Time> time() {
        ArrayList<Time> aimTime = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            Time time = timeService.adaptTime((double) i, new Date(),
                    "Test time " + i, State.Date.NEW, null);
            aimTime.add(time);
            time.setId((long) i);
        }
        return aimTime;
    }
    @BeforeEach
    void createAimsWithTimes(){
        Aim aim1 = new Aim();
        Aim aim2 = new Aim();
        List<Time> timesForAim = time();

        aim1.setLoggedTime(new HashSet<>(Arrays.asList(timesForAim.get(0), timesForAim.get(1))));
        aim2.setLoggedTime(new HashSet<>(Arrays.asList(timesForAim.get(2), timesForAim.get(3))));

        timesForAim.get(0).setAim(aim1);
        timesForAim.get(1).setAim(aim1);

        timesForAim.get(2).setAim(aim2);
        timesForAim.get(3).setAim(aim2);

        this.aims = new ArrayList<>(Arrays.asList(aim1, aim2));
    }

    @Test
    void mostActiveAim() {
        Aim mostActiveAim = aimService.getMostActiveAim(aims);

        assertEquals(mostActiveAim, aims.get(1));
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
        Optional<Date> shouldBeEmptyParsedDate = aimService.parseDate("213");
        assertThat(Optional.empty()).isEqualTo(shouldBeEmptyParsedDate);
    }

    @Test
    void builderTest(){
        Aim aim = new Aim.AimBuilder("Test title", "Description", "Text")
                .build();
        assertNotNull(aim);
    }
}
