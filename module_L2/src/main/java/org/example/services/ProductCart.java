package org.example.services;

import lombok.AllArgsConstructor;
import org.example.entity.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@Scope("prototype")
public class ProductCart implements Cart<Product>{

    private List<Product> productsCart;
    private EntityService<Product> entityService;

    @PostConstruct
    public void init() {
        productsCart = new ArrayList<>();
    }

    public void addEntity(Product product) {
        productsCart.add(product);
    }

    public void deleteEntity(int id) {
        productsCart.remove(entityService.getById(id));
    }

    public List<Product> getCart() {
        return productsCart;
    }

    public String getCartList() {
        StringBuilder sb = new StringBuilder();
        sb.append("Items in the cart:\n");
        for (Product product: productsCart) {
            sb.append("№: ")
                    .append(product.getId())
                    .append("; Title: ")
                    .append(product.getTitle())
                    .append("; cost - ")
                    .append(product.getCost())
                    .append("\n");
        }
        return sb.toString();
    }
}
