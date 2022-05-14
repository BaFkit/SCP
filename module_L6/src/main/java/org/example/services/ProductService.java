package org.example.services;

import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService<T> {

    T getById(Long id);

    void save(T t);

    List<T> getEntityAll();

    void remove(Long id);

    T getByName(String name);

    void setAmountProduct(int amount);

    Page<T> getByThroughFilter(Optional<BigDecimal> min, Optional<BigDecimal> max, Optional<Integer> page, Optional<Integer> size);
}