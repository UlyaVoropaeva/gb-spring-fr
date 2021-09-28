package ru.repositiry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.domain.Product;

import java.awt.print.Pageable;
import java.util.List;


@Service
public interface ProductRepository extends CrudRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {

   // @Query("select p from Product p where p.name = :name")
    //Iterable<Product> findByName(@Param("name") String name);

   // @Query(value = "SELECT * FROM products as p WHERE p.name = :name", nativeQuery = true)
   // Iterable<Product> findByNameNativeSQL(@Param("name") String name);

    @Query("select min (p.price) from Product as p")
    Iterable<Product> filterByPriceMin();

    @Query("select min (p.price)  as min, max (p.price) as max from Product as p")
    Iterable<Product> filterByPriceMinMax();

    @Query("select max (p.price) from Product as p")
    Iterable<Product> filterByPriceMax();

   // @Query("SELECT p FROM Product  as p WHERE p.id = :id")
    //Product getById(@Param("id") long id);

    @Query("select p from Product as p")
    List<Product> findAll();

    //@Modifying
  //  @Query("UPDATE Product as p SET p.name='$name', p.price='$price' where p.id = :id")
   // void update(long id);

   // Page<Product> findAllPage(PageRequest pageable);

   // Page<Product> findByName(String name, Pageable pageable);

  //  Slice<Product> findByNameAndPrice(String name, int price, Pageable pageable);

}
