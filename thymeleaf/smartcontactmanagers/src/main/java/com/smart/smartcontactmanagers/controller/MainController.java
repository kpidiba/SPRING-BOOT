package com.smart.smartcontactmanagers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.smart.smartcontactmanagers.dao.UserRepository;

@Controller
public class MainController {
    
    @Autowired
    UserRepository userRepository;
    @GetMapping("/")
    // @ResponseBody
    public String index(){
        // User user  = new User();
        // user.setName("david");
        // user.setEmail("dj@gmail.com");
        // userRepository.save(user);
        return "home";
    }
}
