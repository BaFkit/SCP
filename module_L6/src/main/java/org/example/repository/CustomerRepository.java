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
public class CustomerRepository implements EntityRepository<Customer, Product> {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void add(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    @Transactional
    public void update(Customer customer) {
        entityManager.merge(customer);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        entityManager.remove(getById(id));
    }

    @Override
    public Customer getById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public List<Customer> getAll() {
        return entityManager.createQuery("select a from Customer a", Customer.class).getResultList();
    }

    @Override
    public Customer getByName(String name) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("select a from Customer a where a.name = :name", Customer.class);
        query.setParameter("name", name);
        return (Customer) query.getSingleResult();
    }

    @Override
    public List<Product> getListItem(Long id) {
        Customer customer = getById(id);
        return customer.getProducts();
    }

    @Override
    @Transactional
    public void addItemInList(Customer customer, Product product) {
        customer.getProducts().add(product);
        entityManager.merge(customer);
    }

    @Override
    @Transactional
    public void delItemFromList(Customer customer, Product product) {
        customer.getProducts().remove(product);
        entityManager.merge(customer);
    }
}