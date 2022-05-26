package org.example.services;

import org.example.components.Cart;
import org.example.entity.Product;
import org.example.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    private ProductService<Product> productService;
    @Autowired
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void add(Long id) {
        Product product = productService.getById(id);
        cart.add(productMapper.productToProductDTO(product));
    }

    public void remove(Long id) {
        Product product = productService.getById(id);
        cart.remove(productMapper.productToProductDTO(product));
    }
}
