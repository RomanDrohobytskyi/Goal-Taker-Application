package application.services;

import application.entities.message.Message;
import application.repositories.IUserRepository;
import application.roles.Role;
import application.entities.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import static application.logger.LoggerJ.*;

@Service
public class UserService implements UserDetailsService{
    
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private MailSenderService mailSenderService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return iUserRepository.findUserByEmail(email);
    }

    /**
     * Method to check is User exist by Email
     * @param user - user to check is exist
     * @return boolean
     */
    public boolean isUserExist(User user){
        return iUserRepository.findUserByEmail(user.getEmail()) != null;
    }

    //Check and return User email is empty.
    public boolean isUserEmailEmpty(String email){
        return !org.springframework.util.StringUtils.isEmpty(email);
    }

    //Return true if passwords match.
    public boolean isPasswordsMatch(String password, String confirmedPassword){
        return password.equals(confirmedPassword);
    }

    /**
     * Method to check is user has searched message
     * @param user - user which should has a searched message
     * @param searchMessage - searched message
     * @return true if has.
     */
    public boolean isUserHasMessage(User user, Message searchMessage){
        return searchMessage.getUser().getId().equals( user.getId());
    }

    public boolean isUserHasAuthority(User user, String authority){
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            return role.getAuthority().equals(authority);
        }
        return false;
    }

    public boolean sendActivationCode(User user){
        try{
            String message = String.format(
                    "Hello, %s!\n" + "Welcome to my page!\n" +
                            "To activate your Email please, click on a link below.\n" +
                            "http://localhost:8080/activate/%s",
                    user.getFirstName() + " " + user.getLastName(),
                    user.getActivationCode()
            );
            mailSenderService.send(user.getEmail(), "Activation code", message);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Setting Active, Role, Activation code for New User
     * @param user - New User.
     */
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

    /**
     * Method to check if User has been activate his email by activation code
     * or not.
     * @param code - user activation code
     * @return - true if has
     */
    public boolean activateUser(String code) {
        User user = iUserRepository.findByActivationCode(code);

        if (user == null){
            return false;
        }
        //If User activated his mail, set his activation code NULL.
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
