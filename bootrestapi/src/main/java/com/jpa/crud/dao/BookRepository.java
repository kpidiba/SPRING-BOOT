package com.jpa.crud.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jpa.crud.entities.Book;

public interface BookRepository extends  CrudRepository<Book,Integer> {
    public Book findById(int id);

}
