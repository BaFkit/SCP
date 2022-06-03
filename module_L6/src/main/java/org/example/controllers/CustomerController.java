package org.example.controllers;

import org.example.entity.Customer;
import org.example.services.CustomerService;
import org.example.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService<Customer> customerService;
    private RoleService roleService;

    @Autowired
    public void setCustomerService(CustomerService<Customer> customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public String indexCustomerPage(Model model) {
        model.addAttribute("customers", customerService.getEntityAll());
        return "customer";
    }

    @GetMapping("/{id}")
    public String editCustomer(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("customer", customerService.getById(id));
        model.addAttribute("roles", roleService.findAll());
        return "customer_form";
    }

    @GetMapping("/new")
    public String newCustomer(Model model) {
        model.addAttribute(new Customer());
        return "customer_form";
    }

    @PostMapping("/update")
    public String updateCustomer(Customer customer) {
        customerService.save(customer);
        return "redirect:/customer";
    }

    @GetMapping("/delete/{id}")
    public String removeCustomer(@PathVariable(value = "id") Long id) {
        customerService.remove(id);
        return "redirect:/customer";
    }
}
