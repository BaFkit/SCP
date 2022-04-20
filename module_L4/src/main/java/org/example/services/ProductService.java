package org.example.services;

import lombok.AllArgsConstructor;
import org.example.entity.Product;
import org.example.repository.Repository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
@AllArgsConstructor
public class ProductService implements EntityService<Product> {

    private Repository<Product> productRepository;

    @Override
    public Product getById(int id) {
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
    public void remove(int id) {
        productRepository.remove(id);
    }
}
