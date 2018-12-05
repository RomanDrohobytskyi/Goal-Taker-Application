package application.service;

import application.user.IUserRepository;
import application.user.Role;
import application.user.User;
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
    private MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return iUserRepository.findUserByEmail(email);
    }

    public boolean addUser(User user, String passwordConfirm){
        //Find user by email from DB using IUserRepository
        User userFromDb = iUserRepository.findUserByEmail(user.getEmail());

        //If User not exist, return false.
        if (userFromDb != null){
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        //user.setRoles(Collections.singleton(Role.ADMIN));

        iUserRepository.save(user);
        //If User has email and confirm his password get and send on his
        // email activation code.
        if (!org.springframework.util.StringUtils.isEmpty(user.getEmail())
                & user.getPassword().equals(passwordConfirm)){

            String message = String.format(
              "Hello, %s!\n" + "Welcome to my page!\n" +
                      "To activate your Email please, click on a link below.\n" +
                      "http://localhost:8080/activate/%s",
                    user.getFirstName() + " " + user.getLastName(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation code", message);
        }
        return true;
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
