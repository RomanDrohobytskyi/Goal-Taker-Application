package application.services;

import application.entities.user.User;
import application.enums.UserRegisterValidationState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRegistrationServiceTest {

    @Mock
    private UserService userService;
    @InjectMocks
    private UserRegistrationService userRegistrationService;


    //something wrong with userService, doesnt invoke methods



    @BeforeEach
    void createUser(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void validateRegistrationEmptyEmail(){
        User user = User.builder()
                .email("")
                .build();

        Map<String, Object> validationResult = userRegistrationService.validateUser(user, user.getPassword());

        assertTrue(validationResult.containsKey(UserRegisterValidationState.EMPTY_EMAIL.toString()));
    }

    @Test
    void validateRegistrationPasswordNotMatch(){
        String confirmationPassword = "222";
        User user = User.builder()
                .email("someRandomUniqueEmailAddress@test.test")
                .password("111")
                .build();

        Map<String, Object> validationResult = userRegistrationService.validateUser(user, confirmationPassword);

        assertTrue(validationResult.containsKey(UserRegisterValidationState.PASSWORDS_NOT_MATCHING.toString()));
    }

    @Test
    void validateRegistrationPasswordMatch (){
        User user = User.builder()
                .email("someRandomUniqueEmailAddress@test.test")
                .password("111")
                .build();

        Map<String, Object> validationResult = userRegistrationService.validateUser(user, "111");

        assertFalse(validationResult.containsKey(UserRegisterValidationState.PASSWORDS_NOT_MATCHING.toString()));
    }
}