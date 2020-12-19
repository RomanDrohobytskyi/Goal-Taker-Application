package application.services;

import application.entities.user.User;
import application.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private MailSenderService mailSenderService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;
    private User user;

    @BeforeEach
    void createUser(){
        this.user = new User();
    }

    @Test
    void userServiceIsNotNull(){
        assertNotNull(userService);
    }

    @Test
    void notEmptyEmailTest(){
        user.setEmail("non empty");
        assertFalse(userService.isUserEmailEmpty(user));
    }

    @Test
    void emptyEmailTest(){
        user.setEmail("");
        assertTrue(userService.isUserEmailEmpty(user));
    }

    @Test
    public void isUserExist(){
        user.setEmail("test.test@test.test");

        userRepository.save(this.user);

        assertTrue(userService.isUserExist(user));
    }

}
