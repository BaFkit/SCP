package org.example.services;

import java.util.List;

public interface CustomerService<T> {

    T getById(Long id);

    void save(T t);

    List<T> getEntityAll();

    void remove(Long id);

    T getByName(String name);

    T getByLogin(String login);
}
