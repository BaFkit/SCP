package org.example.services;

import lombok.AllArgsConstructor;
import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.repository.EntityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements EntityService<Product> {

    private EntityRepository<Product, Customer> productEntityRepository;

    @Override
    public Product getById(Long id) {
        return productEntityRepository.getById(id);
    }

    @Override
    public void add(Product product) {
        productEntityRepository.add(product);
    }

    @Override
    public List<Product> getEntityAll() {
        return productEntityRepository.getAll();
    }

    @Override
    public void update(Product product) {
        productEntityRepository.update(product);
    }

    @Override
    public void remove(Long id) {
        productEntityRepository.remove(id);
    }

    @Override
    public Product getByName(String name) {
       return productEntityRepository.getByName(name);
    }

}
