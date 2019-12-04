package application.managers;

import application.entities.aim.Aim;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class AimManager{

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public List<Aim> getAimsByUserId(Long userId){
        List<Aim> userAims = new ArrayList<>();
        userAims = getDayPrice();
      /*  Iterable<Aim> aims = aimRepository.findAll();
        for (Aim aim : aims){
            if (aim.getUser().getId().equals(userId)){
                userAims.add(aim);
            }
        }*/
        return userAims;
    }

    public List<Aim> getDayPrice(){
        EntityManager session = entityManagerFactory.createEntityManager();
        try {
            List<Aim> aims = (List<Aim>)session.createNativeQuery("Select AIM FROM StockPrice")
                    .getSingleResult();

            return aims;
        }
        catch (NoResultException e){
            return null;
        }
        finally {
            if(session.isOpen()) session.close();
        }
    }
}
