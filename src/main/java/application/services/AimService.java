package application.services;

import application.entities.Aim;
import application.entities.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AimService {

    @Autowired
    private UserService userService;

    /**
     * This method create new Aim based on parameters.
     * @param title - aim title
     * @param description - aim description
     * @param user - aim creator
     * @return optional of Aim
     */
    public Optional<Aim> adaptAim(String title, String description, String text, User user){
        if (Strings.isNotEmpty(title) && Strings.isNotEmpty(description) && user != null){
            if (userService.isUserExist(user)){
                Aim aim = new Aim();
                aim.setTitle(title);
                aim.setDescription(description);
                aim.setText(text);
                aim.setCreationDate(new Date());

                aim.setUser(user);
                return Optional.of(aim);
            }
        }
        return Optional.empty();
    }

}
