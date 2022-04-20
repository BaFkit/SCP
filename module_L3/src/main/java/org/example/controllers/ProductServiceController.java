package org.example.controllers;

import org.example.entity.Product;
import org.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/index")
public class ProductServiceController {

    @Autowired
    private ProductService productService;

    @GetMapping("/form_product")
    public String formProduct() {
        return "form_product";
    }

    @GetMapping("/add_product")
    public String addProduct(HttpServletRequest request) {
        int id = productService.getEntityAll().size() + 1;
        String name = request.getParameter("product_name");
        String cost = request.getParameter("product_cost");
        productService.add(new Product(id, name, Integer.parseInt(cost)));
        return "redirect:product_list";
    }

    @GetMapping("/product_list")
    public String showProductList(Model model) {
        model.addAttribute("products", productService.getEntityAll());
        return "product_list";
    }
}
