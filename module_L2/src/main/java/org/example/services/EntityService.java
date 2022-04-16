package org.example.services;

import java.util.List;

public interface EntityService<T> {

    T getById(int id);

    List<T> getEntityAll();

    String getListAll();
}
