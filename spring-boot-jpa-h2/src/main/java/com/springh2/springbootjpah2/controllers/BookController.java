package com.springh2.springbootjpah2.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springh2.springbootjpah2.dao.BookRepository;
import com.springh2.springbootjpah2.model.Book;

@RestController
public class BookController {
    @Autowired
    BookRepository bookrepo;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            List<Book> bookList = new ArrayList<>();
            bookrepo.findAll().forEach(bookList::add);

            if (bookList.isEmpty()) {
                return new ResponseEntity<>(bookList, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(bookList,HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> bookdata = bookrepo.findById(id);
        if (bookdata.isPresent()) {
            return new ResponseEntity<>(bookdata.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/book")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        try {
            book = bookrepo.save(book);
            return new ResponseEntity<>(book,HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id ,@RequestBody Book book) {
        Optional<Book> oldbook = bookrepo.findById(id);
        if(oldbook.isPresent()){
            Book updatedBook = oldbook.get();
            updatedBook.setTitle(book.getTitle());
            updatedBook.setAuthor(book.getAuthor());
            Book obj = bookrepo.save(updatedBook);
            return new ResponseEntity<>(obj,HttpStatus.OK);
        }
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBookById(@PathVariable Long id) {
        bookrepo.deleteById(id);
    }
}
