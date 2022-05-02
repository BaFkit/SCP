package org.example.services;

import org.example.entity.Order;

import java.util.List;

public interface OrdersService<T, E> {

    List<T> getListCustomers(Long id);

    List<E> getListProducts(Long id);

    void addProductToCustomer(T t, E e);

    void delProductToCustomer(T t, E e);

    void addOrder(T t, E e);

    List<Order> getAllOrders();

}
