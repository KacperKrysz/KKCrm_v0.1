package app.model.user;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveUser(User user) {
        entityManager.persist(user);
    }
    public User findById(long id) {
        return entityManager.find(User.class, id);
    }
    public void update(User user) {
        entityManager.merge(user);
    }
    public void delete(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    public List<User> findAll() {
        return entityManager.createQuery("SELECT a FROM User a", User.class).getResultList();
    }

    public User findByLogin(String login) {
        try {
            return entityManager.createQuery("SELECT DISTINCT a FROM User a WHERE a.login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User findByFullName(String fullName) {
        try {
            return entityManager.createQuery("SELECT DISTINCT a FROM User a WHERE a.fullName = :fullName", User.class)
                    .setParameter("fullName", fullName)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }



}
