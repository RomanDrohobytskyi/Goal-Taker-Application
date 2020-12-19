package application.services;

import application.entities.user.User;
import application.enums.UserRegisterValidationState;
import application.roles.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {

    private final UserService userService;

    //TODO refactor : return ENUM with message inside
    public Map<String, Object> validateUser(User user, String passwordConfirm) {
        Map<String, Object> emailAndPasswordValidationResult = validateUserEmailAndPasswords(user, passwordConfirm);
        if (emailAndPasswordValidationResult.isEmpty()) {
            setNewUserData(user);
            return sendActivationCode(user);
        } else {
            return emailAndPasswordValidationResult;
        }
    }

    private void setNewUserData(User user){
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(generateVerificationToken());
        userService.encodeUserPassword(user);
    }

    private String generateVerificationToken() {
        return UUID.randomUUID().toString();
    }

    private Map<String, Object> validateUserEmailAndPasswords(User user, String passwordConfirm){
        if (userService.isUserEmailEmpty(user)) {
            return Map.of(UserRegisterValidationState.EMPTY_EMAIL.state, UserRegisterValidationState.EMPTY_EMAIL.description);
        } else if (userService.isUserExist(user)) {
            return Map.of(UserRegisterValidationState.USER_EXIST.state, UserRegisterValidationState.USER_EXIST.description);
        } else if (!userService.isPasswordsMatch(user.getPassword(), passwordConfirm)) {
            return Map.of(UserRegisterValidationState.PASSWORDS_NOT_MATCHING.state,
                    UserRegisterValidationState.PASSWORDS_NOT_MATCHING.description);
        } else {
            return Collections.emptyMap();
        }
    }

    private Map<String, Object> sendActivationCode(User user){
        if (userService.sendActivationCode(user)) {
            userService.saveUser(user);
            return Map.of(UserRegisterValidationState.SUCCESS.state, UserRegisterValidationState.SUCCESS.description);
        } else {
            return Map.of(UserRegisterValidationState.CODE_SENDING_FAILED.state, UserRegisterValidationState.CODE_SENDING_FAILED.description);
        }
    }

    public void activateUserByActivationCode(String code) {
        User user = userService.findByActivationCode(code).orElseThrow(IllegalArgumentException::new);
        user.setActivationCode(null);
        user.setActive(true);
        userService.saveUser(user);
    }

    public boolean resendVerificationToken(String email) {
        User user = userService.findUserByEmail(email);
        generateNewVerificationTokenForUser(user);
        return userService.sendActivationCode(user);
    }

    public void generateNewVerificationTokenForUser(User user) {
        user.setActivationCode(generateVerificationToken());
    }

}
