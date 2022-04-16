package org.example.services;


import java.util.List;

public interface Cart<T> {
    void init();

    void addEntity(T obj);

    void deleteEntity(int id);

    List<T> getCart();

    String getCartList();
}
