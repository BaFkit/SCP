package org.example.services;

import lombok.AllArgsConstructor;
import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements EntityService<Product>{

    private ProductRepository productRepository;

    @Override
    public Product getById(Long id) {
        List<Product> list = (List<Product>) productRepository.findAll();
        return list.stream().filter(e -> e.getId().equals(id)).findFirst().get();
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> getEntityAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getByName(String title) {
       return productRepository.findByTitle(title);
    }

}
