package org.example.services;

import lombok.AllArgsConstructor;
import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@Scope("prototype")
public class Cart {

    private List<Product> productsCart;
    private ProductRepository productRepository;

    @PostConstruct
    public void init() {
        productsCart = new ArrayList<>();
    }

    public void addProduct(Product product) {
        productsCart.add(product);
    }

    public void deleteProduct(int id) {
        productsCart.remove(productRepository.getProductById(id));
    }

    public List<Product> getCart() {
        return productsCart;
    }

    public String getProductsCartList() {
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
