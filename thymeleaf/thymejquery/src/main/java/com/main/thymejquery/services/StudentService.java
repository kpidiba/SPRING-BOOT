package com.main.thymejquery.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.thymejquery.models.Student;
import com.main.thymejquery.repository.UserRepository;

@Service
public class StudentService {
    
    @Autowired
    private UserRepository userRepository;

    public List<Student> getAll(){ 
        return (List<Student>) userRepository.findAll();
    }

    public Optional<Student> getOne(Integer id){
       return userRepository.findById(id);
    }

    public void addStudent(Student student){
        userRepository.save(student);
    }
}
