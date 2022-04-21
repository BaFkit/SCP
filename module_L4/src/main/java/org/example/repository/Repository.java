package org.example.repository;

import java.util.List;

public interface Repository<T> {

    void init();

    void add(T t);

    void update(T t);

    void remove(int id);

    T getById(int id);

    List<T> getAll();

}