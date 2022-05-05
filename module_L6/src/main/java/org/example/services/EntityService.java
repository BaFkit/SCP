package org.example.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface EntityService<T> {

    T getById(Long id);

    void save(T t);

    List<T> getEntityAll();

    void remove(Long id);

    T getByName(String name);

    List<T> getByThroughFilter(Optional<BigDecimal> param1, Optional<BigDecimal> param2);
}