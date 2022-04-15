package org.example.repository;

import lombok.AllArgsConstructor;
import org.example.entity.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ProductRepository implements Repository<Product>{

    private List<Product> products;

    @PostConstruct
    public void init(){
        products = new ArrayList<>();
        products.add(new Product(1, "Milk", 10));
        products.add(new Product(2, "Bread", 5));
        products.add(new Product(3, "Orange", 7));
        products.add(new Product(4, "Butter", 11));
        products.add(new Product(5, "Banana", 12));
    }

    public Product getById(int id) {
       return products.stream().
               filter(e -> e.getId() == id).
               findFirst().
               get();
    }

    public List<Product> getAll() {
        return products;
    }

    public String getListAll() {
        StringBuilder sb = new StringBuilder();
        for (Product product: products) {
            sb.append("№: ")
                    .append(product.getId())
                    .append("; title: ")
                    .append(product.getTitle())
                    .append("; cost - ")
                    .append(product.getCost())
                    .append("\n");
        }
        return sb.toString();
    }
}
