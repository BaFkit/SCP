package org.example.rest;

import org.example.entity.Product;
import org.example.services.ProductService;
import org.example.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {

    @Autowired
    private ProductService<Product> productService;

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.getEntityAll();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        productService.save(product);
        return product;
    }

    @PutMapping(consumes = "application/json")
    public Product updateProduct(@RequestBody Product product) {
        if (productService.existById(product.getId())) {
            productService.save(product);
            return product;
        }
        throw new NotFoundException();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.remove(id);
    }
}
