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
public class UserDao {


    @PersistenceContext
    private EntityManager entityManager;

    public Optional<User> findById(long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }


    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public void deleteById(long id) {
        entityManager.createQuery("DELETE FROM User WHERE id = :id", User.class);
    }


    public User saveOrUpdate(User user) {
        boolean present = findById(user.getId()).isPresent();

        if (present) {
            return entityManager.merge(user);
        }

        entityManager.persist(user);
        entityManager.flush();
        return user;
    }


    public List<UserProduct> getUserProductsByUserId(long id) {
        Optional<User> o = findById(id);
        if (o.isPresent()) {
            return getUserProducts(o.get());
        }

        return new ArrayList<>();
    }

    public List<UserProduct> getUserProducts(User user) {
        return user.getUserProducts();
    }

    public List<Product> getProductsByUserId(long id) {
        Optional<User> o = findById(id);
        if (o.isPresent()) {
            return getProducts(o.get());
        }

        return new ArrayList<>();
    }

    public List<Product> getProducts(User user) {
        ArrayList<Product> products = new ArrayList<>();
        for (UserProduct usersProduct : user.getUserProducts()) {
            products.add(usersProduct.getProduct());
        }
        return products.stream()
                .distinct()
                .collect(Collectors.toList());
    }
}