package ru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import ru.domain.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.servise.ProductService;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/app/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController( ProductService productService) {
        this.productService = productService;

    }

    @GetMapping("/min")
    public List<Product> filterByPriceMin() {
        List<Product> products = new ArrayList<>();
        productService.filterByPriceMin().forEach(products::add);
        return products;
    }

    @GetMapping("/max")
    public List<Product> filterByPriceMax(){
        List<Product> products = new ArrayList<>();
        productService.filterByPriceMax().forEach(products::add);
        return products;
    }

    @GetMapping("/maxmin")
    public List<Product> filterByPriceMinMax() {
        List<Product> products = new ArrayList<>();
        productService.filterByPriceMinMax().forEach(products::add);
        return products;
    }

    @GetMapping()
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        productService.findAll().forEach(products::add);
        return products;
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id)  {
        return productService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.deleteById( id);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Product product) {
        productService.saveOrUpdate(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}