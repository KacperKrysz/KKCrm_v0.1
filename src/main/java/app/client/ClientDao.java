package app.client;

import app.activity.Activity;
import app.client.Client;
import app.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ClientDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveClient(Client client) {
        entityManager.persist(client);
    }
    public Client findById(long id) {
        return entityManager.find(Client.class, id);
    }
    public void update(Client client) {
        entityManager.merge(client);
    }
    public void delete(Client client) {
        entityManager.remove(entityManager.contains(client) ? client : entityManager.merge(client));
    }

    public List<Client> findAll() {
        return entityManager.createQuery("SELECT a FROM Client a", Client.class).getResultList();
    }
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
