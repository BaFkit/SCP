package org.example.services;

import lombok.AllArgsConstructor;
import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements EntityService<Product> {

    private ProductRepository productRepository;

    @Override
    public Product getById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public void add(Product product) {
        productRepository.add(product);
    }

    @Override
    public List<Product> getEntityAll() {
        return productRepository.getAll();
    }

    @Override
    public void update(Product product) {
        productRepository.update(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.remove(id);
    }

    @Override
    public Product getByName(String name) {
       return productRepository.getByName(name);
    }

}
