package app.model.client;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Data Access Object (DAO) for handling client-related database operations.
 */
@Repository
@Transactional
public class ClientDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Saves a client to the database.
     *
     * @param client The client to be saved.
     */
    public void saveClient(Client client) {
        entityManager.persist(client);
    }

    /**
     * Retrieves a client by its ID.
     *
     * @param id The ID of the client to retrieve.
     * @return The retrieved client, or null if not found.
     */
    public Client findById(long id) {
        return entityManager.find(Client.class, id);
    }

    /**
     * Updates an existing client in the database.
     *
     * @param client The client to be updated.
     */
    public void update(Client client) {
        entityManager.merge(client);
    }

    /**
     * Deletes a client from the database.
     *
     * @param client The client to be deleted.
     */
    public void delete(Client client) {
        entityManager.remove(entityManager.contains(client) ? client : entityManager.merge(client));
    }

    /**
     * Retrieves a list of all clients from the database.
     *
     * @return A list of all clients in the database.
     */
    public List<Client> findAll() {
        return entityManager.createQuery("SELECT a FROM Client a", Client.class).getResultList();
    }

    /**
     * Retrieves a client by its full name.
     *
     * @param fullName The full name of the client to retrieve.
     * @return The retrieved client, or null if not found.
     */
    public Client findClientByFullName(String fullName) {
        try {
            return entityManager.createQuery("SELECT DISTINCT a FROM Client a WHERE a.fullName = :fullName", Client.class)
                    .setParameter("fullName", fullName)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
}
