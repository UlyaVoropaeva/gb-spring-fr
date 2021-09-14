package ru.dao;


import org.springframework.stereotype.Repository;
import ru.domain.Product;
import ru.domain.User;
import ru.domain.UserProduct;

import javax.persistence.EntityManager;
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

    private List<Product> products = new ArrayList<>();

    public Product getById(Long id) {
        if (id == null) {
            throw new NullPointerException();
        }

        return entityManager.createQuery("from Product where id = :id", Product.class)
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


    public Product update(Long id, Product product) {
        Product original = getById(id);
        if (original != null) {
            original.setName(product.getName());
            product.setPrice(product.getPrice());
            entityManager.merge(original);
        }
        return original;
    }


    public List<Product> findAll() {
        List<Product> resultList = entityManager
                .createQuery("select p from Product as p", Product.class)
                .getResultList();
        return resultList;
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


    public List<User> getUsersByProductId(long id) {
        Optional<Product> pro = findById(id);
        if (pro.isPresent()) {
            return getUsers(pro.get());
        }
        return new ArrayList<>();
    }


    public List<User> getUsers(Product product) {
        ArrayList<User> users = new ArrayList<>();
        for (UserProduct userProduct : product.getUserProducts()) {
            users.add(userProduct.getUser());
        }
        return users.stream().distinct().collect(Collectors.toList());
    }
}
