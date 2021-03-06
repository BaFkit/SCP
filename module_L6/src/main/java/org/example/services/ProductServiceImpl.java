package org.example.services;

import lombok.Setter;
import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.example.repository.specifications.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService<Product> {
    @Autowired
    private ProductRepository productRepository;

    @Setter
    @Value("10")
    private int amountProduct;

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
    public Page<Product> getByThroughFilter(Optional<BigDecimal> min, Optional<BigDecimal> max, Optional<Integer> page, Optional<Integer> size) {

        Specification<Product> specification = Specification.where(null);
        if (min.isPresent()) {
            specification = specification.and(ProductSpecification.greatOrEquals(min.get()));
        }
        if (max.isPresent()) {
            specification = specification.and(ProductSpecification.lessOrEquals(max.get()));
        }

        return productRepository.findAll(specification, PageRequest.of(page.orElse(1) - 1, size.orElse(amountProduct)));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existById(Long id) {
        return productRepository.existsById(id);
    }
}
