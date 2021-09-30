package ru.repositiry;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.domain.Cart;
import ru.domain.Client;
import ru.domain.Product;

import javax.transaction.Transactional;
import java.util.List;


public interface CartRepository extends CrudRepository<Client, Long> {

    Product product = new Product();

    @Query(value = "select c from USERS_PRODUCT as c WHERE c.USER_ID = : userId", nativeQuery = true)
    List<Cart> findAll(long userId);

    @Query(value = "UPDATE USERS_PRODUCT set price = price + productPrice, count = count + countNew where USERS_PRODUCT.USER_ID = : userId and USERS_PRODUCT.product_id =: productId", nativeQuery = true)
    Cart updateToCart(long userId, long productId, int productPrice, int countNew);


    @Modifying
    @Query(value = "insert into  USERS_PRODUCT (product_id, price, count) VALUES (productId, productPrice, count) where USERS_PRODUCT.USER_ID = : userId ", nativeQuery = true)
    @Transactional
    Cart addToCart(long userId, long productId, int productPrice, int count);

}
