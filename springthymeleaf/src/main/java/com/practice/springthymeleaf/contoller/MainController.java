package com.practice.springthymeleaf.contoller;

import java.util.List;

import org.springframework.stereotype.Controller;
//NOTE:import
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class MainController {
    
    @GetMapping("/home")
    public String index(Model model){
        model.addAttribute("hello", "hello");
        return "index";
    }

    @GetMapping("/iteration")
    public String iteratioString( Model model) {
        List<String> tab = List.of("david","helloworld","man with a mission");
        model.addAttribute("tab", tab);
        return "iterate";
    }

    @GetMapping("/handleCondition")
    public String condition(Model model){
        model.addAttribute("value", false);
        model.addAttribute("gender", "Masculin");
        List<Integer> tab = List.of(123,344,544);
        model.addAttribute("tableau",tab);
        return "condition";
    }
    
}
