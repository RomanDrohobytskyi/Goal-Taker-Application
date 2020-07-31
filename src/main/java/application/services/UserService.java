package application.services;

import application.entities.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    User findUserByEmail(String email) throws UsernameNotFoundException;
    User delete(User user);
    boolean isUserExist(User user);
    boolean isUserEmailEmpty(String email);
    boolean isPasswordsMatch(String password, String confirmedPassword);
    boolean sendActivationCode(User user);
    boolean activateUser(String code);
    void setUserData(User user);
    void saveUser(User user);
}
