package org.example.repository;

import org.example.entity.Order;

import java.util.List;

public interface OrderRepository<T, E> {

    void add(T t, E e);

    List<Order> getAll();
}
