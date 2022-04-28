package org.example.repository;

import java.util.List;

public interface EntityRepository<T, E> {

    void add(T t);

    void update(T t);

    void remove(Long id);

    T getById(Long id);

    List<T> getAll();

    T getByName(String name);

    List<E> getListItem(Long id);

    void addItemInList(T t, E e);

    void delItemFromList(T t, E e);
}
