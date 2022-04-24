package org.example.repository;

import lombok.AllArgsConstructor;
import org.example.entity.Product;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Component
@AllArgsConstructor
public class ProductRepository implements Repository<Product>{

    private final EntityManager entityManager;

    @Override
    @Transactional
    public void add(Product product) {
        entityManager.persist(product);
    }

    @Override
    @Transactional
    public void update(Product product) {
        entityManager.merge(product);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        entityManager.remove(getById(id));
    }

    @Override
    public Product getById(Long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = entityManager
                .createQuery("select a from Product a", Product.class).getResultList();
        return products;
    }
}
