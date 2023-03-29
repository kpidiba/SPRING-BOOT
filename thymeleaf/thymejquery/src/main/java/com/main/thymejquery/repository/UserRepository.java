package com.main.thymejquery.repository;

import org.springframework.data.repository.CrudRepository;

import com.main.thymejquery.models.Student;

public interface UserRepository extends CrudRepository<Student,Integer> {
    
}
