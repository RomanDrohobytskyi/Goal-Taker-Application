package application.services.aim;

import application.entities.aim.Aim;
import application.entities.time.data.Time;
import application.entities.user.User;
import application.enums.State;
import application.managers.UserManager;
import application.repositories.AimRepository;
import application.services.UserService;
import application.utils.date.DateParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static application.logger.LoggerJ.logError;

@Service
@RequiredArgsConstructor
public class SmartAimService implements AimService<Aim> {

    private final UserService userService;
    private final AimRepository aimRepository;
    private UserManager userManager = new UserManager();

    public Optional<Aim> adapt(String title, String description, String text, String specific, String measurable,
                               String attainable, String relevant, Date timeBased, User user) {
        if (userService.isUserExist(user)) {
            Aim aim = Aim.builder()
                    .title(title)
                    .description(description)
                    .text(text)
                    .specify(specific)
                    .measurable(measurable)
                    .attainable(attainable)
                    .relevant(relevant)
                    .timeBased(timeBased)
                    .creationDate(new Date())
                    .user(user)
                    .build();
            return Optional.of(aim);
        }
        logError(getClass(), "User " + user.getEmail() + " not exist!");
        return Optional.empty();
    }

    @Override
    public Aim save(Aim aim) {
        return aimRepository.save(aim);
    }

    @Override
    @Transactional
    public void delete(List<Aim> aims) {
        for (Aim aim : aims) {
            if (!aim.getAimState().equals(State.Aim.DELETED.toString())) {
                delete(aim);
            }
        }
    }

    @Override
    public Aim delete(Aim aim) {
        Date deletionDate = new Date();
        aim.setModificationDate(deletionDate);
        aim.setDeletionDate(deletionDate);
        aim.setAimState(State.Aim.DELETED.toString());
        return aimRepository.save(aim);
    }

    @Override
    public Aim achieve(Aim aim) {
        aim.setAimState(State.Aim.ACHIEVED.toString());
        aim.setModificationDate(new Date());
        aim.setAchievedDate(new Date());
        return aimRepository.save(aim);
    }

    public Aim addAndSaveAim(User user, String title, String description, String text, String specific,
                             String measurable, String attainable, String relevant, String timeBased) {
        Date timeBasedDate = DateParser.parseStringToDateForDefaultPattern(timeBased)
                .orElseThrow(IllegalArgumentException::new);
        Aim aim = adapt(title, description, text, specific, measurable, attainable, relevant,
                timeBasedDate, user).orElseThrow(IllegalArgumentException::new);
        return save(aim);
    }

    public Aim editAndSave(String title, String text, String description, String specific,
                           String measurable, String attainable, String relevant, String timeBased, Aim aim) {
        this.edit(title, text, description, specific, measurable, attainable, relevant, timeBased, aim);
        return this.save(aim);
    }

    public Aim edit(String title, String text, String description, String specific,
                    String measurable, String attainable, String relevant, String timeBased, Aim aim) {
        aim.setText(text);
        aim.setDescription(description);
        aim.setTitle(title);
        aim.setAimState(State.Aim.EDITED.toString());
        aim.setSpecify(specific);
        aim.setMeasurable(measurable);
        aim.setAttainable(attainable);
        aim.setRelevant(relevant);
        aim.setTimeBased(DateParser.parseStringToDateForDefaultPattern(timeBased)
                .orElseThrow(IllegalArgumentException::new));
        aim.setModificationDate(new Date());
        aim.setAimState(State.Aim.EDITED.toString());
        return aim;
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

    public List<Aim> findByUser(User user) {
        return aimRepository.findByUser(user);
    }

}
