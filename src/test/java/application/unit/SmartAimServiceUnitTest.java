package application.unit;

import application.entities.aim.Aim;
import application.entities.time.data.Time;
import application.enums.State;
import application.services.aim.SmartAimService;
import application.services.time.SmartAimTimeService;
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
public class SmartAimServiceUnitTest {

    @Autowired
    private SmartAimService aimService;
    @Autowired
    private SmartAimTimeService timeService;
    private List<Aim> aims;

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

    @Test
    void mostActiveAim() {
        Aim mostActiveAim = aimService.getMostActiveAim(aims);
        Aim expectedAim = aims.get(1);

        assertThat(mostActiveAim).isEqualTo(expectedAim);
    }

    @Test
    void builderTest(){
        String title = "Test title";
        String description =  "Description";
        String text = "Text";

        Aim aim = new Aim.AimBuilder(title, description, text).build();

        assertAll(
                () -> assertNotNull(aim),
                () -> assertNull(aim.getId()),
                () -> assertThat(aim.getTitle()).isEqualTo(title),
                () -> assertThat(aim.getDescription()).isEqualTo(description),
                () -> assertThat(aim.getText()).isEqualTo(text)
        );
    }
}
