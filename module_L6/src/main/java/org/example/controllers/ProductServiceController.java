package org.example.controllers;

import org.example.entity.Customer;
import org.example.entity.Product;
import org.example.services.*;
import org.example.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/index")
public class ProductServiceController {

    @Autowired
    private ProductService<Product> productServiceImpl;
    @Autowired
    private CustomerService<Customer> customerServiceImpl;
    @Autowired
    private OrdersService<Customer, Product> ordersServiceImpl;

    private Customer customer;

    @GetMapping("/index")
    public String auth(HttpServletRequest request) {
        customer = customerServiceImpl.getByName(request.getParameter("name"));
        return "redirect:product_list";
    }

    @PostMapping("/form_product")
    public String formUpdateProduct(Product product) {
        productServiceImpl.save(product);
        return "redirect:product_list";
    }

    @GetMapping("/product_list")
    public String showProductList(Model model,
                                  @RequestParam(name = "min", required = false)Optional<BigDecimal> min,
                                  @RequestParam(name = "max", required = false)Optional<BigDecimal> max,
                                  @RequestParam(name = "page", required = false)Optional<Integer> page,
                                  @RequestParam(name = "size", required = false)Optional<Integer> size) {
        try {
            model.addAttribute("products", productServiceImpl.getByThroughFilter(min, max, page, size));
            model.addAttribute("name", customer.getName());
            model.addAttribute("customer_products", ordersServiceImpl.getListProducts(customer.getId()));
        }catch (RuntimeException e){
            throw new NotFoundException();
        }
        return "product_list";
    }

    @GetMapping("/add_product")
    public String addProduct(Model model) {
        model.addAttribute(new Product());
        return "form_product";
    }

    @GetMapping("{id}")
    public String updateProduct(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("product", productServiceImpl.getById(id));
        return "form_product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        productServiceImpl.remove(id);
        return "redirect:/index/product_list";
    }

    @GetMapping("/buy/{id}")
    public String buyProduct(@PathVariable(value = "id") Long id) {
        ordersServiceImpl.addProductToCustomer(customer, productServiceImpl.getById(id));
        return "redirect:/index/product_list";
    }

    @GetMapping("/delete_purcha/{id}")
    public String deletePurcha(@PathVariable(value = "id") Long id) {
        ordersServiceImpl.delProductToCustomer(customer, productServiceImpl.getById(id));
        return "redirect:/index/product_list";
    }

    @GetMapping("/buyer_list/{id}")
    public String showBuyerList(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("name_product", productServiceImpl.getById(id).getTitle());
        model.addAttribute("customers", ordersServiceImpl.getListCustomers(id));
        return "buyer_list";
    }

    @GetMapping("/orders_list")
    public String showOrderList(Model model) {
        model.addAttribute("orders", ordersServiceImpl.getAllOrders());
        return "orders_list";
    }

    @GetMapping("/product_list/{amount}")
    public String showByAmount(@PathVariable(value = "amount") int amount) {
        productServiceImpl.setAmountProduct(amount);
        return "redirect:/index/product_list";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("/404");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
