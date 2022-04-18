package org.example.controllers;

import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class ProductRepositoryController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/add_product")
    public String addProduct() {
        //productRepository.add(product);
        return "/add_product";
    }

    @GetMapping("/product_list")
    public String showProductList(Model model) {
        model.addAttribute("products", productRepository.getAll());
        return "product_list";


    }






}
