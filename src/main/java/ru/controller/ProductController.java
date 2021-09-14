package ru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dao.ProductDao;
import ru.domain.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping()
    @ResponseBody
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product findById(@PathVariable Long id) throws Exception {
        return productDao.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws Exception {
        productDao.deleteById(id);
    }


    @PostMapping()
    @ResponseBody
    public List<Product> save(@RequestBody Product product) {

        productDao.saveOrUpdate(product);
        return productDao.findAll();

    }


}
