package application.services;

import application.entities.aim.Aim;
import application.entities.time.data.Time;
import application.entities.user.User;
import application.enums.State;
import application.managers.UserManager;
import application.repositories.IAimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static application.logger.LoggerJ.logError;

@Service
@RequiredArgsConstructor
public class AimService {

    private final UserService userService;
    private final IAimRepository aimRepository;
    private UserManager userManager = new UserManager();

    public Optional<Aim> adapt(String title, String description, String text, String specific, String measurable,
                                                        String attainable, String relevant, Date timeBased, User user){
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
        logError(getClass(), "User " + user.getEmail() + " not exist!");
        throw new IllegalArgumentException();
    }

    public Aim save(Aim aim) {
        return aimRepository.save(aim);
    }

    public Aim delete(Aim aim) {
        Date deletionDate = new Date();
        aim.setModificationDate(deletionDate);
        aim.setDeletionDate(deletionDate);
        aim.setAimState(State.Aim.DELETED.toString());
        return aimRepository.save(aim);
    }

    public void delete(List<Aim> aims) {
        for (Aim aim : aims){
            if (!aim.getAimState().equals(State.Aim.DELETED.toString())) {
                delete(aim);
            }
        }
    }

    public Aim addAndSaveAim(User user, String title, String description, String text, String specific,
                                                      String measurable, String attainable, String relevant, String timeBased){
        Date timeBasedDate = parseDate(timeBased).orElseThrow(IllegalArgumentException::new);
        Optional<Aim> aimOptional = adapt(title, description, text, specific, measurable, attainable, relevant,
                timeBasedDate, user);
        return save(aimOptional.orElseThrow(IllegalArgumentException::new));
    }

    public Aim achieve(Aim aim){
        aim.setAimState(State.Aim.ACHIEVED.toString());
        aim.setModificationDate(new Date());
        aim.setAchievedDate(new Date());
        return aimRepository.save(aim);
    }

    public Aim editAndSave(String title, String text, String description, String specific,
                                                    String measurable, String attainable, String relevant, String timeBased, Aim aim) {
        this.edit(title,text,description,specific,measurable,attainable,relevant,timeBased,aim);
        return this.save(aim);
    }

    public Aim edit(String title, String text, String description, String specific,
                                             String measurable, String attainable, String relevant, String timeBased, Aim aim){
        aim.setText(text);
        aim.setDescription(description);
        aim.setTitle(title);
        aim.setAimState(State.Aim.EDITED.toString());
        aim.setSpecify(specific);
        aim.setMeasurable(measurable);
        aim.setAttainable(attainable);
        aim.setRelevant(relevant);
        aim.setTimeBased(parseDate(timeBased).orElseThrow(IllegalArgumentException::new));
        aim.setModificationDate(new Date());
        aim.setAimState(State.Aim.EDITED.toString());
        return aim;
    }

    public Optional<Date> parseDate(String timeBased){
        try {
            Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(timeBased);
            return Optional.of(parsedDate);
        } catch (ParseException e){
            logError(AimService.class, "AimService.parse(" + timeBased + "), error message:"
            + e.getMessage());
        }
        return Optional.empty();
    }

    public List<Aim> getAchievedUserAims(User user) {
        return aimRepository.findAimsByAimStateAndUser(State.Aim.ACHIEVED.toString(), user);
    }

    public List<Aim> getNotDeletedUserAims(User user) {
        return aimRepository.findAimsByAimStateIsNotLikeAndUser(State.Aim.DELETED.toString(), user);
    }

    public List<Aim> getLoggedInUserAims() {
        User loggedInUser = userManager.getLoggedInUser();
        return getNotDeletedUserAims(loggedInUser);
    }

    public Aim getMostActiveAim(List<Aim> userAims) {
        return Collections.max(userAims,
                Comparator.comparing(a -> a.getLoggedTime()
                        .stream()
                        .mapToDouble(Time::getTime)
                        .sum()));
    }
}
