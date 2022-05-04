package org.example.services;

import lombok.AllArgsConstructor;
import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements EntityService<Product>{

    private ProductRepository productRepository;

    @Override
    public Product getById(Long id) {
       return productRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> getEntityAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    @Transactional
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getByName(String title) {
       return productRepository.findByTitle(title);
    }

}
