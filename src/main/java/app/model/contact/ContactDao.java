package app.model.contact;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Data Access Object (DAO) class for managing contact entities in the database.
 */
@Transactional
@Repository
public class ContactDao {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Saves a contact entity in the database.
     *
     * @param contact The contact entity to be saved.
     */
    public void saveContact(Contact contact) {
        entityManager.persist(contact);
    }

    /**
     * Retrieves a contact entity by its unique identifier (ID).
     *
     * @param id The unique identifier of the contact.
     * @return The contact entity if found, otherwise null.
     */
    public Contact findById(long id) {
        return entityManager.find(Contact.class, id);
    }

    /**
     * Updates a contact entity in the database.
     *
     * @param contact The contact entity to be updated.
     */
    public void update(Contact contact) {
        entityManager.merge(contact);
    }

    /**
     * Deletes a contact entity from the database.
     *
     * @param contact The contact entity to be deleted.
     */
    public void delete(Contact contact) {
        entityManager.remove(entityManager.contains(contact) ? contact : entityManager.merge(contact));
    }

    /**
     * Retrieves a list of contacts associated with a specific client.
     *
     * @param clientId The unique identifier of the client.
     * @return A list of contact entities associated with the client if found, otherwise an empty list or null.
     */
    public List<Contact> findContactsByClientId(Long clientId) {
        try {
            return entityManager
                    .createQuery("SELECT a FROM Contact a WHERE a.client.id = :clientId", Contact.class)
                    .setParameter("clientId", clientId)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
