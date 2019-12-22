package application.managers;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;

public class AimManager{

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    /*public List<Aim> getAimsByUserId(Long userId){
        List<Aim> userAims = new ArrayList<>();
        userAims = getDayPrice();
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
    }*/
}
