package application.services;

import application.repositories.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

    @Mock
    private IUserRepository iUserRepository;
    @Mock
    private MailSenderService mailSenderService;
    @InjectMocks
    private UserService userService;

    @Test
    void userServiceIsNotNull(){
        assertNotNull(userService);
    }
}
