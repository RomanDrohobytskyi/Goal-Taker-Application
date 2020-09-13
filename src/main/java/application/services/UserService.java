package application.services;

import application.entities.user.User;
import application.repositories.IUserRepository;
import application.roles.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

import static application.logger.LoggerJ.logError;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;
    private final MailSenderService mailSenderService;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(s);
    }

    public User findUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email);
    }

    public Map<String, Object> validateUserRegistrationData(User user, String passwordConfirm) {
        Map<String, Object> model = new HashMap<>();

        if (isUserExist(user))
            model.put("userExist", "Sorry, user with email: " + user.getEmail() + ", already exist!");
        if (isUserEmailEmpty(user))
            model.put("emailIsEmpty", "Sorry, Your email is empty, please check it again");
        if (!isPasswordsMatch(user.getPassword(), passwordConfirm))
            model.put("passwordNotMach", "Sorry, but Your passwords do not match, check it again!");
        else {
            setNewUserData(user);
            if (sendActivationCode(user)){
                saveUser(user);
            } else {
                model.put("message", "We can`t send to You activation code, sorry!");
            }
            model.put("success", "Success");
        }
        model.put("user", user);
        return model;
    }

    public boolean isUserExist(User user){
        return userRepository.findUserByEmail(user.getEmail()) != null;
    }

    public boolean isUserEmailEmpty(User user){
        return StringUtils.isEmpty(user.getEmail());
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
            logError(getClass(), e.getMessage());
        }
        return false;
    }

    public void setNewUserData(User user){
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(generateVerificationToken());
        encodeUserPassword(user);
    }

    public String generateVerificationToken() {
        return UUID.randomUUID().toString();
    }

    public void generateNewVerificationTokenForUser(User user) {
        user.setActivationCode(generateVerificationToken());
    }

    public boolean resendVerificationToken(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findUserByEmail(email));
        generateNewVerificationTokenForUser(user.orElseThrow(IllegalArgumentException::new));
        return sendActivationCode(user.get());
    }

    public void saveUser(User user){
            userRepository.save(user);
    }

    public void activateUser(String code) {
        Optional<User> user = Optional.of(userRepository.findByActivationCode(code));
        user.orElseThrow(IllegalArgumentException::new).setActivationCode(null);
        user.orElseThrow(IllegalArgumentException::new).setActive(true);
        userRepository.save(user.get());
    }

    public User delete(User user){
        user.setActive(false);
        userRepository.save(user);
        return user;
    }

    public void encodeUserPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
