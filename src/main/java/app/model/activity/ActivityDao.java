package app.model.activity;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Data Access Object (DAO) class for managing activities in the application.
 */
@Repository
@Transactional
public class ActivityDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Saves a new activity to the database.
     *
     * @param activity The activity to be saved.
     */
    public void saveActivity(Activity activity) {
        entityManager.persist(activity);
    }

    /**
     * Retrieves an activity by its unique identifier.
     *
     * @param id The unique identifier of the activity.
     * @return The activity with the specified ID, or null if not found.
     */
    public Activity findById(long id) {
        return entityManager.find(Activity.class, id);
    }

    /**
     * Updates an existing activity in the database.
     *
     * @param activity The activity to be updated.
     */
    public void update(Activity activity) {
        entityManager.merge(activity);
    }

    /**
     * Deletes an activity from the database.
     *
     * @param activity The activity to be deleted.
     */
    public void delete(Activity activity) {
        entityManager.remove(entityManager.contains(activity) ? activity : entityManager.merge(activity));
    }

    /**
     * Retrieves a list of activities associated with a specific client.
     *
     * @param clientId The unique identifier of the client.
     * @return A list of activities associated with the specified client, or an empty list if none are found.
     */
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
