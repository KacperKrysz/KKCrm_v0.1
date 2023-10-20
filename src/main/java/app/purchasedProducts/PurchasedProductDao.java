package app.purchasedProducts;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class PurchasedProductDao {


    @PersistenceContext
    private EntityManager entityManager;

    public void savePurchasedProduct(PurchasedProduct purchasedProduct) {
        entityManager.persist(purchasedProduct);
    }
    public PurchasedProduct findById(long id) {
        return entityManager.find(PurchasedProduct.class, id);
    }
    public void update(PurchasedProduct purchasedProduct) {
        entityManager.merge(purchasedProduct);
    }
    public void delete(PurchasedProduct purchasedProduct) {
        entityManager.remove(entityManager.contains(purchasedProduct) ? purchasedProduct : entityManager.merge(purchasedProduct));
    }

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
