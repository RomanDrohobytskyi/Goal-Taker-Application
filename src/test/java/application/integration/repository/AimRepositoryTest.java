package application.integration.repository;

import application.enums.State;
import application.repositories.IAimRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AimRepositoryTest {

    @Autowired
    private IAimRepository aimRepository;

    @Test
    public void shouldUpdatePartially() {
        application.entities.aim.Aim aim = new application.entities.aim.Aim();
        aim.setTitle("Test title");
        aim.setText("Test text");
        aim.setDescription("Test description");
        aim.setSpecify("Test ");
        aim.setMeasurable("Test ");
        aim.setAttainable("Test ");
        aim.setRelevant("Test ");
        aim.setCreationDate(new Date());
        aim.setAimState(State.Aim.NEW.getState());

        aimRepository.save(aim);

        assertNotNull(aim.getId());
    }
}
