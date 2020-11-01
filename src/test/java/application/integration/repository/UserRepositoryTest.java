package application.integration.repository;

import application.entities.user.User;
import application.repositories.IUserRepository;
import application.roles.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private IUserRepository repository;
    private String userEmail = "test.email@test.test";
    private String userActivationCode = "fWrV$52e!";
    private User user;

    @BeforeEach
    void createUser(){
        this.user = new User();
        user.setEmail(userEmail);
        user.setPassword("");
        user.setRoles(Collections.singleton(Role.ADMIN));
        user.setActive(true);
        user.setActivationCode(userActivationCode);
    }

    @Test
    void shouldSaveUser() {
        User user = repository.save(this.user);

        assertNotNull(user);
        assertNotNull(user.getId());
        assertThat(user.getEmail()).isEqualTo(this.user.getEmail());
        assertThat(user.getRoles().containsAll(Collections.singleton(Role.ADMIN)));
    }

    @Test
    void shouldFindUserByEmail(){
        repository.save(this.user);

        User user = repository.findUserByEmail(this.userEmail);

        assertNotNull(user);
        assertThat(user.getEmail()).isEqualTo(this.userEmail);
    }

    @Test
    void shouldFindByActivationCode(){
        repository.save(this.user);

        User user = repository.findByActivationCode(this.userActivationCode);

        assertNotNull(user);
        assertThat(user.getActivationCode()).isEqualTo(this.userActivationCode);
    }

    @Test
    void shouldFindActiveUserWithoutActivationCode(){
        this.user.setActivationCode(null);
        repository.save(this.user);

        User user = repository.findActiveUserWithoutActivationCodeByEmail(this.userEmail);

        assertNotNull(user);
        assertNull(user.getActivationCode());
        assertTrue(user.isActive());
    }

    @Test
    void shouldNotFindNotActiveUserWithoutActivationCode(){
        this.user.setActivationCode(null);
        this.user.setActive(false);
        repository.save(this.user);

        User user = repository.findActiveUserWithoutActivationCodeByEmail(this.userEmail);

        assertNull(user);
    }

    @Test
    void shouldNotFindUserWithActivationCode(){
        this.user.setActivationCode(null);
        repository.save(this.user);

        User user = repository.findActiveUserWithoutActivationCodeByEmail(this.userEmail);

        assertNull(user);
    }
}