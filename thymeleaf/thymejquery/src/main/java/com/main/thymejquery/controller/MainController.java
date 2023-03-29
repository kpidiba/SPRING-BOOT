package com.main.thymejquery.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.main.thymejquery.models.Student;
import com.main.thymejquery.services.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/students")
public class MainController {
    @Autowired
    private StudentService service;

    @GetMapping(value="/home")
    public String home( ) {
        return "index";
    }

    @GetMapping("/getAll")
    public String getAll(Model model){
        List<Student> students = service.getAll();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping(value="/getOne")
    @ResponseBody
    public Optional<Student> getOne( Integer id) {
        return service.getOne(id);
    }

    @PostMapping(value="/addStudent")
    public String addStudent( Student student) {
        this.service.addStudent(student);
        return "redirect:/students/getAll";
    }
    
    
    
}
