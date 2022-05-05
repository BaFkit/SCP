package org.example.services;

import lombok.AllArgsConstructor;
import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.example.repository.specifications.ProductSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService implements EntityService<Product>{

    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public Product getById(Long id) {
       return productRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getEntityAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    @Transactional
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Product getByName(String title) {
       return productRepository.findByTitle(title);
    }

    @Transactional(readOnly = true)
    public List<Product> getByThroughFilter(Optional<BigDecimal> min, Optional<BigDecimal> max) {

        Specification<Product> specification = Specification.where(null);
        if (min.isPresent()) {
            specification = specification.and(ProductSpecification.greatOrEquals(min.get()));
        }
        if (max.isPresent()) {
            specification = specification.and(ProductSpecification.lessOrEquals(max.get()));
        }

        return productRepository.findAll(specification);
    }
}
