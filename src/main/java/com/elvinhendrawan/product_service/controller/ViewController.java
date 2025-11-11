package com.elvinhendrawan.product_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        return "register";
    }

    @GetMapping("/")
    public String homePage() {
        return "redirect:/login";
    }

    // Product UI
    @GetMapping("/products-ui")
    public String productsPage() {
        return "products";
    }

}