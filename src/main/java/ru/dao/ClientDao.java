package ru.dao;

import org.springframework.stereotype.Repository;
import ru.domain.Product;
import ru.domain.Client;
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
public class ClientDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Client> findById(long id) {
        return Optional.ofNullable(entityManager.find(Client.class, id));
    }

    public List<Client> findAll() {
        return entityManager.createQuery("SELECT u FROM Client u", Client.class).getResultList();
    }

    public void deleteById(long id) {
        entityManager.createQuery("DELETE FROM Client WHERE id = :id", Client.class);
    }


    public Client saveOrUpdate(Client client) {
        boolean present = findById(client.getId()).isPresent();

        if (present) {
            return entityManager.merge(client);
        }

        entityManager.persist(client);
        entityManager.flush();
        return client;
    }


    public List<UserProduct> getUserProductsByUserId(long id) {
        Optional<Client> o = findById(id);
        if (o.isPresent()) {
            return getUserProducts(o.get());
        }

        return new ArrayList<>();
    }

    public List<UserProduct> getUserProducts(Client client) {
        return client.getUserProducts();
    }

    public List<Product> getProductsByUserId(long id) {
        Optional<Client> o = findById(id);
        if (o.isPresent()) {
            return getProducts(o.get());
        }

        return new ArrayList<>();
    }

    public List<Product> getProducts(Client client) {
        ArrayList<Product> products = new ArrayList<>();
        for (UserProduct usersProduct : client.getUserProducts()) {
            products.add(usersProduct.getProduct());
        }
        return products.stream()
                .distinct()
                .collect(Collectors.toList());
    }
}