package com.spring.springvalidation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.springvalidation.entities.LoginData;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {

    @GetMapping("/formulaire")
    public String form(Model model) {
        model.addAttribute("loginData", new LoginData());
        return "formulaire"; 
    }

    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute("loginData") LoginData logindata, BindingResult bindresult) {
        System.out.println("loginData: " + logindata);
        if (bindresult.hasErrors()){
            System.out.println("Error: " + bindresult);
            return "formulaire";
        }
        return "suc";
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String requestMethodName(@RequestParam("username") String nom,@RequestParam("email") String email) {
        return "{\"status\":\"success\",\"email\":\""+email+"\"}";
    }
    
}
