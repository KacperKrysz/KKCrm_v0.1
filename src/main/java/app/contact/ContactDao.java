package app.contact;

import app.contact.Contact;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public class ContactDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void saveContact(Contact contact) {
        entityManager.persist(contact);
    }
    public Contact findById(long id) {
        return entityManager.find(Contact.class, id);
    }
    public void update(Contact contact) {
        entityManager.merge(contact);
    }
    public void delete(Contact contact) {
        entityManager.remove(entityManager.contains(contact) ? contact : entityManager.merge(contact));
    }

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
