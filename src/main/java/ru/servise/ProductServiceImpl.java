package ru.servise;

import org.springframework.stereotype.Service;
import ru.dao.ProductDao;
import ru.domain.Product;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @EJB
    private ProductDao productDao;

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Product getById(Long id) {
        Product product = productDao.getById(id);
        if (product != null) {
            return product;
        }
        return null;
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(Product product) {

        productDao.saveOrUpdate(product);
    }


    @Override
    public void deleteById(Long id) {
        productDao.deleteById(id);
    }

    @Override
    public Product update(Long id, Product product) {
        return productDao.update(id,product);
    }

    @Override
    public List<Product> filterByPriceMin() {
        return productDao.filterByPriceMin();
    }

    @Override
    public List<Product> filterByPriceMax() {
        return productDao.filterByPriceMax();
    }

    @Override
    public List<Product> filterByPriceMinMax() {
        return productDao.filterByPriceMinMax();
    }
}