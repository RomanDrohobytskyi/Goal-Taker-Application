package application.services;

import application.entities.aim.Aim;
import application.entities.user.User;
import application.enums.State;
import application.repositories.IAimRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AimService {

    @Autowired
    private UserService userService;
    @Autowired
    private IAimRepository aimRepository;

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

    /**
     * Method to setup deleted aim data and then save
     * @param aim - aim to delete
     * @return - saved/updated Aim
     */
    public Aim deleteAim(Aim aim) {
        aim.setModificationDate(new Date());
        aim.setAimState(State.AimState.DELETED.toString());

        return aimRepository.save(aim);
    }
}
