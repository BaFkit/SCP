package org.example.controllers;


import org.example.services.security.CustomerAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Autowired
    private CustomerAuthService customerAuthService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", customerAuthService.getCustomer().getName());
        return "index";
    }
}
