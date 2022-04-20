package org.example.repository;

import lombok.AllArgsConstructor;
import org.example.entity.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@AllArgsConstructor
public class ProductRepository implements Repository<Product>{

    private static AtomicInteger identity = new AtomicInteger(0);
    private final Map<Integer, Product> identityMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init(){
        add(new Product(0, "Milk", 10));
        add(new Product(0, "Bread", 5));
        add(new Product(0, "Orange", 7));
        add(new Product(0, "Butter", 11));
        add(new Product(0, "Banana", 12));
    }

    @Override
    public void add(Product product) {
        product.setId(identity.incrementAndGet());
        identityMap.put(product.getId(), product);
    }

    @Override
    public void update(Product product) {
        identityMap.put(product.getId(), product);
    }

    @Override
    public void remove(int id) {
       identityMap.remove(id);
    }

    @Override
    public Product getById(int id) {
        return identityMap.get(id);
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(identityMap.values());
    }
}
