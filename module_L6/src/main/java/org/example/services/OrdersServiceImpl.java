package org.example.services;

import lombok.AllArgsConstructor;
import org.example.entity.Customer;
import org.example.entity.Order;
import org.example.entity.Product;
import org.example.repository.OrderRepository;
import org.example.repository.EntityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrdersServiceImpl implements OrdersService<Customer, Product> {

    private EntityRepository<Product, Customer> productEntityRepository;
    private EntityRepository<Customer, Product> customerEntityRepository;
    private OrderRepository<Customer, Product> orderRepositoryImpl;

    @Override
    public List<Customer> getListCustomers(Long id) {
        return productEntityRepository.getListItem(id);
    }

    @Override
    public List<Product> getListProducts(Long id) {
        return customerEntityRepository.getListItem(id);
    }

    @Override
    public void addProductToCustomer(Customer customer, Product product) {
        customerEntityRepository.addItemInList(customer, product);
        addOrder(customer, product);
    }

    @Override
    public void delProductToCustomer(Customer customer, Product product) {
        customerEntityRepository.delItemFromList(customer, product);
    }

    @Override
    public void addOrder(Customer customer, Product product) {
        orderRepositoryImpl.add(customer, product);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepositoryImpl.getAll();
    }

}
