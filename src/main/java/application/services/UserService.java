package application.services;

import application.entities.aim.Aim;
import application.entities.aim.TenThousandHoursAim;
import application.entities.message.Message;
import application.entities.user.User;
import application.repositories.UserRepository;
import application.roles.Role;
import application.services.aim.SmartAimService;
import application.services.aim.TenThousandHoursAimService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

import static application.logger.LoggerJ.logError;
import static java.util.stream.Collectors.toSet;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MessageService messageService;
    @Autowired
    private SmartAimService aimService;
    private final UserRepository userRepository;
    private final MailSenderService mailSenderService;
    private final TenThousandHoursAimService tenThousandHoursAimService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUserByEmail(username);
    }

    public User findUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    }

    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    public boolean isUserExist(User user){
        return userRepository.findUserByEmail(user.getEmail()).isPresent();
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

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void adaptEditedUserAndSave(String username, String firstName, String lastName, Map<String, String> form, User user) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(toSet());

        user.getRoles().clear();

        roles.stream()
                .filter(form::containsKey)
                .forEach(role -> user.getRoles().add(Role.valueOf(form.get(role))));

        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        userRepository.save(user);
    }

    @Transactional
    public void deleteUserWithAllNotesAndAims(User user) {
        List<Message> notes = messageService.findByUser(user);
        List<Aim> aims = aimService.findByUser(user);
        List<TenThousandHoursAim> thousandHoursAims =
                tenThousandHoursAimService.findByUser(user);

        messageService.delete(notes);
        aimService.delete(aims);
        tenThousandHoursAimService.delete(thousandHoursAims);
        delete(user);
    }

    private User delete(User user){
        user.setActive(false);
        userRepository.save(user);
        return user;
    }

    public Optional<User> findByActivationCode(String code) {
        return userRepository.findByActivationCode(code);
    }

    public void encodeUserPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

}
