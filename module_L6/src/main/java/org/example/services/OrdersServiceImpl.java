package org.example.services;

import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.entity.Product;
import org.example.repository.CustomerRepository;
import org.example.repository.OrderRepository;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService<Customer, Product> {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository<Customer, Product> orderRepositoryImpl;

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getListCustomers(Long id) {
        Product product = productRepository.findById(id).get();
        return product.getCustomers();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getListProducts(Long id) {
        Customer customer = customerRepository.findById(id).get();
        return customer.getProducts();
    }

    @Override
    @Transactional
    public void addProductToCustomer(Customer customer, Product product) {
        product.getCustomers().add(customer);
        productRepository.save(product);
        addOrder(customer, product);
    }

    @Override
    @Transactional
    public void delProductToCustomer(Customer customer, Product product) {
        product.getCustomers().remove(customer);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void addOrder(Customer customer, Product product) {
        orderRepositoryImpl.add(customer, product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepositoryImpl.getAll();
    }

}
