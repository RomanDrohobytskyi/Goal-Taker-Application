package application.integration.repository;

import application.entities.user.User;
import application.repositories.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private IUserRepository repository;

    @Test
    void shouldUpdatePartially() {
        User user = repository.findByUsername("roman.drohobytskyi@gmail.com");
    }
}
