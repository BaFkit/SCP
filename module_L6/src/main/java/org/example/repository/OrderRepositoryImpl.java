package org.example.repository;

import lombok.AllArgsConstructor;
import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@AllArgsConstructor
public class OrderRepositoryImpl implements OrderRepository<Customer, Product> {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void add(Customer customer, Product product) {
        Order order = new Order(customer.getId(), customer.getName(), product.getId(), product.getTitle(), product.getCost());
        entityManager.persist(order);
    }

    @Override
    public List<Order> getAll() {
        return entityManager.createQuery("select a from Order a", Order.class).getResultList();
    }
}
