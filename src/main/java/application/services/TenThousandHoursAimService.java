package application.services;

import application.entities.aim.TenThousandHoursAim;
import application.entities.user.User;
import application.enums.State;
import application.repositories.ITenThousandHoursAimRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TenThousandHoursAimService {

    @Autowired
    private ITenThousandHoursAimRepository aimRepository;

    public Optional<TenThousandHoursAim> adaptAim(String title, String description, String text, User user) {

        if (Strings.isNotEmpty(title) && Strings.isNotEmpty(description) && user != null){
            TenThousandHoursAim aim = new TenThousandHoursAim();
            aim.setTitle(title);
            aim.setDescription(description);
            aim.setText(text);
            aim.setCreationDate(new Date());
            aim.setUser(user);
            return Optional.of(aim);
        }
        return Optional.empty();
    }

    public TenThousandHoursAim deleteAim(TenThousandHoursAim aim) {
        aim.setModificationDate(new Date());
        aim.setDeletionDate(new Date());
        aim.setAimState(State.AimState.DELETED.toString());

        return aimRepository.save(aim);
    }

    public void delete(List<TenThousandHoursAim> aims){
        for (TenThousandHoursAim aim : aims){
            if (!aim.getAimState().equals(State.AimState.DELETED.toString())){
                deleteAim(aim);
            }
        }
    }

    public TenThousandHoursAim achieve(TenThousandHoursAim aim){
        aim.setAimState(State.AimState.ACHIEVED.toString());
        aim.setModificationDate(new Date());
        aim.setAchievedDate(new Date());
        return aimRepository.save(aim);
    }

    public TenThousandHoursAim adaptEditedAim(TenThousandHoursAim aim, String title, String text, String description) {
        aim.setTitle(title);
        aim.setDescription(description);
        aim.setText(text);
        aim.setModificationDate(new Date());
        aim.setAimState(State.AimState.EDITED.toString());
        aimRepository.save(aim);
        return aim;
    }
}
