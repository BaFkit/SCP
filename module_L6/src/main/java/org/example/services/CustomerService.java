package org.example.services;

import org.example.entity.Customer;

import java.util.List;

public interface CustomerService<T> {

    Customer getById(Long id);

    void save(T t);

    List<Customer> getEntityAll();

    void remove(Long id);

    Customer getByName(String name);
}
