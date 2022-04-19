package org.example.services;

import lombok.AllArgsConstructor;
import org.example.entity.Product;
import org.example.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductService implements EntityService<Product> {

    private Repository<Product> productRepository;

    @Override
    public void add(Product product) {
        productRepository.add(product);
    }

    @Override
    public Product getById(int id) {
        return productRepository.getById(id);
    }

    @Override
    public List<Product> getEntityAll() {
        return productRepository.getAll();
    }

}
