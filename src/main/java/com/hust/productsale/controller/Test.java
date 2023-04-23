package com.hust.productsale.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth/test")
@RestController
public class Test {
    @GetMapping("/test")
    public String test(){
        return "Hello world";
    }
}
