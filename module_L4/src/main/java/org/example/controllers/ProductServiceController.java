package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.entity.Product;
import org.example.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/index")
public class ProductServiceController {

    private ProductService productService;

    @PostMapping("/form_product")
    public String formUpdateProduct(Product product) {
        if (product.getId() == null) {
            productService.add(product);
        } else {
            productService.update(product);
        }
        return "redirect:product_list";
    }

    @GetMapping("/product_list")
    public String showProductList(Model model) {
        model.addAttribute("products", productService.getEntityAll());
        return "product_list";
    }

    @GetMapping("/add_product")
    public String addProduct(Model model) {
        model.addAttribute(new Product());
        return "form_product";
    }

    @GetMapping("{id}")
    public String updateProduct(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "form_product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        productService.remove(id);
        return "redirect:/index/product_list";
    }
}
