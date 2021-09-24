package ru.dao;

import org.springframework.stereotype.Repository;
import ru.domain.Product;
import ru.domain.Client;
import ru.domain.UserProduct;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Transactional
public class ProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    private final EntityManagerFactory managerFactory;

    public ProductDao(EntityManagerFactory managerFactory) {
        this.managerFactory = managerFactory;
    }

    private List<Product> products = new ArrayList<>();


    public List<Product> filterByPriceMin() {
        return entityManager
                .createQuery("select min (p.price) from Product as p", Product.class)
                .getResultList();
    }

    public List<Product> filterByPriceMinMax() {
        return entityManager
                .createQuery("select min (p.price)  as min, max (p.price) as max from Product as p", Product.class)
                .getResultList();

    }

    public List<Product> filterByPriceMax() {
        return entityManager
                .createQuery("select max (p.price) from Product as p", Product.class)
                .getResultList();

    }


    public Product getById(Long id) {
        if (id == null) {
            throw new NullPointerException();
        }

        return entityManager.createQuery("SELECT p FROM Product  as p WHERE p.id = :id", Product.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Product.class, id));
    }

    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);
        }
        entityManager.merge(product);
    }

    public void update(Product product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(product);
    }


    public List<Product> findAll(int page) {
        return entityManager
                .createQuery("select p from Product as p", Product.class)
                .setFirstResult(10 * (page - 1))
                .setMaxResults(10)
                .getResultList();
    }

    public void deleteById(Long id) {
        Product product = entityManager.find(Product.class, id);
        if (product != null) {
            entityManager.remove(product);
        }
    }


    public List<UserProduct> getUserProductsByProductId(Long id) {
        Optional<Product> pro = findById(id);
        if (pro.isPresent()) {
            return getUserProducts(pro.get());
        }
        return new ArrayList<>();
    }


    public List<UserProduct> getUserProducts(Product product) {
        return product.getUserProducts();
    }


    public List<Client> getUsersByProductId(long id) {
        Optional<Product> pro = findById(id);
        if (pro.isPresent()) {
            return getUsers(pro.get());
        }
        return new ArrayList<>();
    }


    public List<Client> getUsers(Product product) {
        ArrayList<Client> clients = new ArrayList<>();
        for (UserProduct userProduct : product.getUserProducts()) {
            clients.add(userProduct.getUser());
        }
        return clients.stream().distinct().collect(Collectors.toList());
    }
}
