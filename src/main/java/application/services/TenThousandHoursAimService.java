package application.services;

import application.entities.aim.TenThousandHoursAim;
import application.entities.user.User;
import application.enums.State;
import application.managers.UserManager;
import application.repositories.ITenThousandHoursAimRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TenThousandHoursAimService {

    private final ITenThousandHoursAimRepository aimRepository;
    private final UserManager userManager = new UserManager();

    public Optional<TenThousandHoursAim> createAim(String title, String description, String text, User user){
        Optional<TenThousandHoursAim> aimOptional = adaptAim(title, description, text, State.Aim.NEW.toString(), user);
        aimOptional.ifPresent(aimRepository::save);
        return aimOptional;
    }

    public Optional<TenThousandHoursAim> adaptAim(String title, String description, String text,
                                                  String state, User user) {
        if (Strings.isNotEmpty(title) && Strings.isNotEmpty(description) && user != null){
            TenThousandHoursAim aim = new TenThousandHoursAim();
            aim.setTitle(title);
            aim.setDescription(description);
            aim.setText(text);
            aim.setCreationDate(new Date());
            aim.setAimState(state);
            aim.setUser(user);
            return Optional.of(aim);
        }
        return Optional.empty();
    }

    public TenThousandHoursAim adaptEditedAim(TenThousandHoursAim aim, String title, String text, String description) {
        aim.setTitle(title);
        aim.setDescription(description);
        aim.setText(text);
        aim.setModificationDate(new Date());
        aim.setAimState(State.Aim.EDITED.toString());
        aimRepository.save(aim);
        return aim;
    }

    public void delete(List<TenThousandHoursAim> aims){
        for (TenThousandHoursAim aim : aims){
            if (!aim.getAimState().equals(State.Aim.DELETED.toString())){
                deleteAim(aim);
            }
        }
    }

    public TenThousandHoursAim deleteAim(TenThousandHoursAim aim) {
        aim.setModificationDate(new Date());
        aim.setDeletionDate(new Date());
        aim.setAimState(State.Aim.DELETED.toString());
        return aimRepository.save(aim);
    }

    public TenThousandHoursAim achieve(TenThousandHoursAim aim){
        aim.setAimState(State.Aim.ACHIEVED.toString());
        aim.setModificationDate(new Date());
        aim.setAchievedDate(new Date());
        return aimRepository.save(aim);
    }

    public List<TenThousandHoursAim> getAllLoggedUserAims(){
        User loggedInUser = userManager.getLoggedInUser();
        return aimRepository.findByUser(loggedInUser);
    }

    public List<TenThousandHoursAim> getAchievedUserAims(User user){
        return aimRepository.findTenThousandHoursAimsByAimStateAndUser(State.Aim.ACHIEVED.toString(), user);
    }

    public List<TenThousandHoursAim> getNotDeletedUserAims(User user) {
        return aimRepository.findAimsByAimStateIsNotLikeAndUser(State.Aim.DELETED.toString(), user);
    }
}
