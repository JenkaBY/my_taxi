package com.exposit.my_taxi.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resources")
public class AuthorizedResourceController {

    @GetMapping("/common")
    public String common() {
        return "Common info";
    }

    @GetMapping("/admin")
    public String onlyAdmin() {
//        OAuth2AuthenticationProcessingFilter
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "Only for admin";
    }

    @GetMapping("/customer_and_operator")
    public String onlyCustomerAndOperator() {
        return "Only for customer and operator";
    }
}
