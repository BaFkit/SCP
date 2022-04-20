package org.example.services;

import java.util.List;

public interface EntityService<T> {

    T getById(int id);

    void add(T t);

    List<T> getEntityAll();

    void update(T t);

    void remove(int id);

}
