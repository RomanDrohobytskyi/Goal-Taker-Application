package application.services.impl;

import application.entities.user.User;
import application.repositories.IUserRepository;
import application.roles.Role;
import application.services.MailSenderService;
import application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static application.logger.LoggerJ.logError;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final IUserRepository iUserRepository;
    private final MailSenderService mailSenderService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return iUserRepository.findUserByEmail(s);
    }

    @Override
    public User findUserByEmail(String email) throws UsernameNotFoundException {
        return iUserRepository.findUserByEmail(email);
    }

    @Override
    public boolean isUserExist(User user){
        return iUserRepository.findUserByEmail(user.getEmail()) != null;
    }

    @Override
    public boolean isUserEmailEmpty(String email){
        return !StringUtils.isEmpty(email);
    }

    @Override
    public boolean isPasswordsMatch(String password, String confirmedPassword){
        return password.equals(confirmedPassword);
    }

    @Override
    public boolean sendActivationCode(User user){
        try{
            String message = String.format(
                    "Hello, %s!\n" + "Welcome to the Aim Taker!\n" +
                        "To activate your account, please, click on a link below.\n" +
                        "http://localhost:8080/activate/" + user.getActivationCode() + "\n" +
                         "Thank You " +  user.getFirstName() + " " + user.getLastName(),
                    user.getFirstName() + " " + user.getLastName()
            );
            mailSenderService.send(user.getEmail(), "Aim Taker. Activation code", message);
            return true;
        }catch (Exception e){
            logError(getClass(), e.getMessage());
        }
        return false;
    }

    @Override
    public void setUserData(User user){
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
    }

    @Override
    public void saveUser(User user){
            iUserRepository.save(user);
    }

    @Override
    public void activateUser(String code) {
        Optional<User> user = Optional.of(iUserRepository.findByActivationCode(code));
        user.orElseThrow(IllegalArgumentException::new).setActivationCode(null);
        iUserRepository.save(user.get());
    }

    @Override
    public User delete(User user){
        user.setActive(false);
        iUserRepository.save(user);
        return user;
    }
}
