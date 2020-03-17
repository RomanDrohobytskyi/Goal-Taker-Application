package application.services;

import application.entities.aim.Aim;
import application.entities.user.User;
import application.enums.State;
import application.repositories.IAimRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
     * @param specific
     * @param measurable
     * @param attainable
     * @param relevant
     * @param timeBased
     * @param user - aim creator
     * @return optional of Aim
     */
    public Optional<Aim> adaptAim(String title, String description, String text, String specific, String measurable,
                                  String attainable, String relevant, Date timeBased, User user){

        if (Strings.isNotEmpty(title) && Strings.isNotEmpty(description) && user != null){
            if (userService.isUserExist(user)){
                Aim aim = new Aim();
                aim.setTitle(title);
                aim.setDescription(description);
                aim.setText(text);
                aim.setSpecify(specific);
                aim.setMeasurable(measurable);
                aim.setAttainable(attainable);
                aim.setRelevant(relevant);
                aim.setTimeBased(timeBased);
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
        aim.setDeletionDate(new Date());
        aim.setAimState(State.AimState.DELETED.toString());

        return aimRepository.save(aim);
    }

    public void delete(List<Aim> aims) {
        for (Aim aim : aims){
            if (!aim.getAimState().equals(State.AimState.DELETED.toString())) {
                deleteAim(aim);
            }
        }
    }

    public Aim achieve(Aim aim){
        aim.setAimState(State.AimState.ACHIEVED.toString());
        aim.setModificationDate(new Date());
        aim.setAchievedDate(new Date());

        return aimRepository.save(aim);
    }

    /**
     * Update edited Aim data
     */
    public Aim editSmartAim(String title, String text, String description, String specific,
                            String measurable, String attainable, String relevant,String timeBased,Aim aim){
        try {
            aim.setText(text);
            aim.setDescription(description);
            aim.setTitle(title);
            aim.setAimState(State.AimState.EDITED.toString());
            aim.setSpecify(specific);
            aim.setMeasurable(measurable);
            aim.setAttainable(attainable);
            aim.setRelevant(relevant);
            aim.setTimeBased(new SimpleDateFormat("yyyy-MM-dd").parse(timeBased));
            aim.setModificationDate(new Date());
            aim.setAimState(State.AimState.EDITED.toString());
        } catch (Exception e){
            e.printStackTrace();
        }

        return aim;
    }
}
