package ru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import ru.domain.Product;
import org.springframework.web.bind.annotation.*;
import ru.repositiry.ProductRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private int page;

    //private final ProductService productService;
    private final ProductRepository productService;

    @Autowired
    public ProductController(ProductRepository productService) {
        this.productService = productService;
    }
   /* public ProductController( ProductService productService) {
        this.productService = productService;

    }*/

    @GetMapping()
    public String findAll( Model model) {
        List<Product> products = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> page = productService.findAll(pageable);
        productService.findAll().forEach(products::add);
        model.addAttribute("products", productService.findAll());

        return "products";
    }


    @GetMapping("/min")
    public List<Product> filterByPriceMin() {
        List<Product> products = new ArrayList<>();
      //  productService.filterByPriceMin().forEach(products::add);
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



    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("products", product);
        return "edit";
    }
    @PostMapping("/update")
    public String update(@RequestParam Long id,
                         @RequestParam(value = "/edit", required = false) boolean edit) {
        productService.update(id );
        return "redirect:/products/upd";
    }

    @PostMapping
    public String save(@Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "product-add";
        }
        productService.save(product);
        return "redirect:/products/mvc";
    }


    @GetMapping("/form")
    public String saveForm(Product product) {
        return "product-add";
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id)  {
        return productService.getById(id);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/";
    }

  /*  @PostMapping()
    public ResponseEntity<?> create(@RequestBody Product product) {
        productService.saveOrUpdate(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

   */

}