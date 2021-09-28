package ru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import ru.domain.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.errores.ProductError;
import ru.repositiry.ProductRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {


    private final ProductRepository productService;

    @Autowired
    public ProductController(ProductRepository productService) {
        this.productService = productService;
    }


    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable long id) {
        Optional<Product> maybeProduct = productService.findById(id);
        if (maybeProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(maybeProduct.get());
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        Product newlyCreated = productService.save(product);
        return ResponseEntity.created(URI.create("/products/" + newlyCreated.getId())).body(newlyCreated);
    }

    @ExceptionHandler
    public ResponseEntity<ProductError> handleException(RuntimeException ex) {
        return ResponseEntity.internalServerError()
                .body(new ProductError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }


    @GetMapping("/min")
    public ResponseEntity<Iterable<Product>> filterByPriceMin() {
        return ResponseEntity.ok(productService.filterByPriceMin());
    }


    @GetMapping("/max")
    public ResponseEntity<Iterable<Product>> filterByPriceMax() {
        return ResponseEntity.ok(productService.filterByPriceMax());
    }

    @GetMapping("/maxmin")
    public ResponseEntity<Iterable<Product>> filterByPriceMinMax() {
        return ResponseEntity.ok(productService.filterByPriceMinMax());
    }

    @DeleteMapping(value = "/delete/{id}")
    public int delete(@PathVariable(name = "id") long id) {
        productService.deleteById(id);
        return HttpStatus.OK.value();
    }
}
