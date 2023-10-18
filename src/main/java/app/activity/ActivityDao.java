package app.activity;

import app.activity.Activity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class ActivityDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveActivity(Activity activity) {
        entityManager.persist(activity);
    }
    public Activity findById(long id) {
        return entityManager.find(Activity.class, id);
    }
    public void update(Activity activity) {
        entityManager.merge(activity);
    }
    public void delete(Activity activity) {
        entityManager.remove(entityManager.contains(activity) ? activity : entityManager.merge(activity));
    }

    public List<Activity> findActivitiesByClientId(Long clientId) {
        try {
            return entityManager
                    .createQuery("SELECT a FROM Activity a WHERE a.client.id = :clientId", Activity.class)
                    .setParameter("clientId", clientId)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }




}
