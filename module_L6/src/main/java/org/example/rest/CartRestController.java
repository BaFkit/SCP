package org.example.rest;

import org.example.components.Cart;
import org.example.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
public class CartRestController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public Cart getCart() {
        return cartService.getCart();
    }

    @GetMapping("/add/{id}")
    public void addProduct(@PathVariable Long id) {
        cartService.add(id);
    }

    @GetMapping("/remove/{id}")
    public void removeProduct(@PathVariable Long id) {
        cartService.remove(id);
    }
}
