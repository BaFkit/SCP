package org.example.controllers;

import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/index")
public class ProductServiceController {

    @Autowired
    private EntityService<Product> productService;
    @Autowired
    private EntityService<Customer> customerService;
    @Autowired
    private OrdersService<Customer, Product> ordersService;

    private Customer customer;

    @GetMapping("/index")
    public String auth(HttpServletRequest request) {
        customer = customerService.getByName(request.getParameter("name"));
        //done wrong, need fix:
        if (customer != null) {
            return "redirect:product_list";
        } else {
            return "redirect:404";
        }
    }

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
        model.addAttribute("name", customer.getName());
        model.addAttribute("products", productService.getEntityAll());
        model.addAttribute("customer_products", ordersService.getListProducts(customer.getId()));
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

    @GetMapping("/buy/{id}")
    public String buyProduct(@PathVariable(value = "id") Long id) {
        ordersService.addProductToCustomer(customer, productService.getById(id));
        return "redirect:/index/product_list";
    }

    @GetMapping("/delete_purcha/{id}")
    public String deletePurcha(@PathVariable(value = "id") Long id) {
        ordersService.delProductToCustomer(customer, productService.getById(id));
        return "redirect:/index/product_list";
    }

    @GetMapping("/buyer_list/{id}")
    public String showBuyerList(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("name_product", productService.getById(id).getTitle());
        model.addAttribute("customers", ordersService.getListCustomers(id));
        return "buyer_list";
    }

    @GetMapping("/orders_list")
    public String showOrderList(Model model) {
        model.addAttribute("orders", ordersService.getAllOrders());
        return "orders_list";
    }

    @GetMapping("/404")
    public String showErrorFindUser() {
        return "404";
    }
}
