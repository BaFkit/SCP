package org.example.repository;

import java.util.List;

public interface Repository<T> {

    void add(T t);

    void update(T t);

    void remove(Long id);

    T getById(Long id);

    List<T> getAll();

}