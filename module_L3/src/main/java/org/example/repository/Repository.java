package org.example.repository;

import org.example.entity.Product;

import java.util.List;

public interface Repository<T> {

    void init();

    void add(T t);

    T getById(int id);

    List<T> getAll();

}
