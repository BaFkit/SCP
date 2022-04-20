package org.example.services;

import java.util.List;

public interface EntityService<T> {

    void add(T t);

    T getById(int id);

    List<T> getEntityAll();

}
