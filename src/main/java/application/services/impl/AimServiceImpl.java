package application.services.impl;

import application.entities.aim.Aim;
import application.entities.user.User;
import application.enums.State;
import application.repositories.IAimRepository;
import application.services.AimService;
import application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AimServiceImpl implements AimService {

    private final UserService userService;
    private final IAimRepository aimRepository;

    @Override
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
        return Optional.empty();
    }

    @Override
    public Aim delete(Aim aim) {
        aim.setModificationDate(new Date());
        aim.setDeletionDate(new Date());
        aim.setAimState(State.AimState.DELETED.toString());
        return aimRepository.save(aim);
    }

    @Override
    public void delete(List<Aim> aims) {
        for (Aim aim : aims){
            if (!aim.getAimState().equals(State.AimState.DELETED.toString())) {
                delete(aim);
            }
        }
    }

    public Aim achieve(Aim aim){
        aim.setAimState(State.AimState.ACHIEVED.toString());
        aim.setModificationDate(new Date());
        aim.setAchievedDate(new Date());
        return aimRepository.save(aim);
    }

    @Override
    public Aim edit(String title, String text, String description, String specific,
                    String measurable, String attainable, String relevant, String timeBased, Aim aim){

        aim.setText(text);
        aim.setDescription(description);
        aim.setTitle(title);
        aim.setAimState(State.AimState.EDITED.toString());
        aim.setSpecify(specific);
        aim.setMeasurable(measurable);
        aim.setAttainable(attainable);
        aim.setRelevant(relevant);
        aim.setTimeBased(parseDate(timeBased).orElseThrow(IllegalArgumentException::new));
        aim.setModificationDate(new Date());
        aim.setAimState(State.AimState.EDITED.toString());
        return aim;
    }

    @Override
    public Optional<Date> parseDate(String timeBased){
        try {
            Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(timeBased);
            return Optional.of(parsedDate);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Aim> getAchievedUserAims(User user) {
        return aimRepository.findAimsByAimStateAndUser(State.AimState.ACHIEVED.toString(), user);
    }

    public List<Aim> getNotDeletedUserAims(User user) {
        return aimRepository.findAimsByAimStateIsNotLikeAndUser(State.AimState.DELETED.toString(), user);
    }
}
