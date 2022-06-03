package org.example.controllers;

import org.example.services.CartService;
import org.example.services.security.CustomerAuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index/cart")
public class CartController {

    private CartService cartService;
    private CustomerAuthService customerAuthService;

    public CartController(CartService cartService, CustomerAuthService customerAuthService) {
        this.cartService = cartService;
        this.customerAuthService = customerAuthService;
    }

    @GetMapping
    public String showListCart(Model model) {
        model.addAttribute("customer", customerAuthService.getCustomer());
        model.addAttribute("products", cartService.getCart().getProductDTOList());
        model.addAttribute("total", cartService.getCart().getTotal());
        return "/cart";
    }

    @GetMapping("/{id}")
    public String addToCart(@PathVariable(value = "id") Long id) {
        cartService.add(id);
        return "redirect:/index/product_list";
    }

    @GetMapping("/clean/{id}")
    public String clean(@PathVariable(value = "id") Long id) {
        cartService.remove(id);
        return "redirect:/index/cart";
    }

}
