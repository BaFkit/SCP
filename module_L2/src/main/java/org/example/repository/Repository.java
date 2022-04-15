package org.example.repository;

import java.util.List;

public interface Repository<T> {

    void init();

    T getById(int id);

    List<T> getAll();

    String getListAll();

}
