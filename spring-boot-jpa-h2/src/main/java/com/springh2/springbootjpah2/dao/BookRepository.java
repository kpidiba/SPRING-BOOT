package com.springh2.springbootjpah2.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.springh2.springbootjpah2.model.Book;

public interface BookRepository extends JpaRepository<Book,Long> {
    
}
