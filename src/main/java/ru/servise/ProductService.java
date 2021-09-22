package ru.servise;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.domain.Product;
import java.util.List;


public interface ProductService  {

    List<Product> findAll();
    Product getById(Long id);
    void saveOrUpdate(Product product);
    void deleteById(Long id);
    Product update(Long id, Product product);
    List<Product> filterByPriceMin();
    List<Product> filterByPriceMax();
    List<Product> filterByPriceMinMax();
}