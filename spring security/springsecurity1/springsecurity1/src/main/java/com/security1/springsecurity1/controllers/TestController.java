package com.security1.springsecurity1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    
    @GetMapping("/get")
    public String getA(){
        return "value";
    }
}
