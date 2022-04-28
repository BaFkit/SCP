package org.example.repository;

import lombok.AllArgsConstructor;
import org.example.entity.Customer;
import org.example.entity.Product;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Component
@AllArgsConstructor
public class ProductRepository implements EntityRepository<Product, Customer> {

    @PersistenceContext
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
        return entityManager.createQuery("select a from Product a", Product.class).getResultList();
    }

    @Override
    public Product getByName(String title) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("select a from Product a where a.title = :title", Product.class);
        query.setParameter("title", title);
        return (Product) query.getSingleResult();
    }

    @Override
    public List<Customer> getListItem(Long id) {
        Product product = getById(id);
        return product.getCustomers();
    }

    @Override
    @Transactional
    public void addItemInList(Product product, Customer customer) {
        product.getCustomers().add(customer);
        entityManager.merge(product);
    }

    @Override
    @Transactional
    public void delItemFromList(Product product, Customer customer) {
        product.getCustomers().add(customer);
        entityManager.merge(product);
    }
}
