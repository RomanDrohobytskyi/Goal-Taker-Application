package application.services;

import application.repositories.IUserRepository;
import application.roles.Role;
import application.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

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

    //Find user by email from DB using IUserRepository.
    //If User not exist, return false.
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

    public void setUserData(User user){
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
//        user.setRoles(Collections.singleton(Role.ADMIN));
    }

    public void saveUser(User user){
        iUserRepository.save(user);
    }

    //Method to check is User activate his email by activation code or not.
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

}
