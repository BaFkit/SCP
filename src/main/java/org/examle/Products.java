package org.examle;

import java.util.ArrayList;
import java.util.List;

public class Products {

    private final List<Product> products;

    public Products() {
        products = new ArrayList<>();
        products.add(new Product(1, "Orange", 10.5f));
        products.add(new Product(1, "Banana", 8.2f));
        products.add(new Product(1, "Milk", 7.6f));
        products.add(new Product(1, "Butter", 12.5f));
        products.add(new Product(1, "Pear", 11.3f));
        products.add(new Product(1, "Eggs", 10.1f));
        products.add(new Product(1, "Strawberry", 14.8f));
        products.add(new Product(1, "Bread", 5.5f));
        products.add(new Product(1, "Salad", 7.9f));
        products.add(new Product(1, "Papaya", 10.2f));
    }

    public List<Product> selectAll(){
        return products;
    }
}
