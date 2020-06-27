package application.services;

import application.entities.user.User;
import application.repositories.IUserRepository;
import application.roles.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

import static application.logger.LoggerJ.logError;
import static application.logger.LoggerJ.logInfo;

@Service
public class UserService implements UserDetailsService{
    
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private MailSenderService mailSenderService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return iUserRepository.findUserByEmail(s);
    }

    public User findUserByEmail(String email) throws UsernameNotFoundException {
        return iUserRepository.findUserByEmail(email);
    }

    public boolean isUserExist(User user){
        return iUserRepository.findUserByEmail(user.getEmail()) != null;
    }

    public boolean isUserEmailEmpty(String email){
        return !StringUtils.isEmpty(email);
    }

    public boolean isPasswordsMatch(String password, String confirmedPassword){
        return password.equals(confirmedPassword);
    }

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
            e.printStackTrace();
        }
        return false;
    }

    public void setUserData(User user){
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        //user.setRoles(Collections.singleton(Role.ADMIN));
    }

    public void saveUser(User user){
        try {
            iUserRepository.save(user);
            logInfo(getClass(), "User " + user.getFirstName() + " " + user.getLastName()
                    + " " + user.getEmail() + " has been successfully saved!");
        } catch (Exception e) {
            logError(getClass(), "");
            e.printStackTrace();
        }
    }

    public boolean activateUser(String code) {
        User user = iUserRepository.findByActivationCode(code);

        if (user == null){
            return false;
        }
        user.setActivationCode(null);
        iUserRepository.save(user);
        return true;
    }

    public User delete(User user){
        if (user != null){
            user.setActive(false);
            iUserRepository.save(user);
        }
        return user;
    }
}
