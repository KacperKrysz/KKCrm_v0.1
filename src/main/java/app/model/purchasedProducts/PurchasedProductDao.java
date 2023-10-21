package app.model.purchasedProducts;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Repository class for managing PurchasedProduct entities in the app.
 */
@Repository
@Transactional
public class PurchasedProductDao {


    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Saves a new PurchasedProduct entity to the database.
     *
     * @param purchasedProduct The PurchasedProduct entity to be saved.
     */
    public void savePurchasedProduct(PurchasedProduct purchasedProduct) {
        entityManager.persist(purchasedProduct);
    }

    /**
     * Retrieves a PurchasedProduct entity by its unique identifier.
     *
     * @param id The unique identifier of the PurchasedProduct entity.
     * @return The PurchasedProduct entity if found, or null if not found.
     */
    public PurchasedProduct findById(long id) {
        return entityManager.find(PurchasedProduct.class, id);
    }


    /**
     * Updates an existing PurchasedProduct entity in the database.
     *
     * @param purchasedProduct The PurchasedProduct entity to be updated.
     */
    public void update(PurchasedProduct purchasedProduct) {
        entityManager.merge(purchasedProduct);
    }

    /**
     * Deletes a PurchasedProduct entity from the database.
     *
     * @param purchasedProduct The PurchasedProduct entity to be deleted.
     */
    public void delete(PurchasedProduct purchasedProduct) {
        entityManager.remove(entityManager.contains(purchasedProduct) ? purchasedProduct : entityManager.merge(purchasedProduct));
    }

    /**
     * Retrieves a list of PurchasedProduct entities associated with a specific client.
     *
     * @param clientId The unique identifier of the client.
     * @return A list of PurchasedProduct entities associated with the client, or null if no products are found.
     */
    public List<PurchasedProduct> findPurchasedProductsByClientId(Long clientId) {
        try {
            return entityManager
                    .createQuery("SELECT a FROM PurchasedProduct a WHERE a.client.id = :clientId", PurchasedProduct.class)
                    .setParameter("clientId", clientId)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }



}
