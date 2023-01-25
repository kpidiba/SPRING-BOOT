package com.jpa.crud.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.crud.entities.Book;
import com.jpa.crud.services.BookService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class BookController {
	
	@Autowired
	private BookService service;
	
	@GetMapping("/books")
	public Book getBooks() {
		Book b1 = new Book(1,"Durgash","David");
		return b1;
	}
	
	@GetMapping("/books-all")
	public ResponseEntity<List<Book>> getAllBooks(){
		List<Book> list = service.getBooks();
		if(list.size() <= 0){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(list));
	}
	
	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book b) {
		try {
			this.service.addBook(b);
			return ResponseEntity.status(HttpStatus.CREATED).body(b);
		} catch (Exception e) {
			// TODO: handle exception7
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/books/{id}")
	public void deleteBook(@PathVariable("id") long id){
		//NOTE:delete BOOK
		 this.service.deleteBook(id);
	}

	@PutMapping(value="/books/{id}")
	public Book putBook(@PathVariable("id") long id, @RequestBody Book book) {
		//TODO: process PUT request
		return this.service.updateBook(book,id);
	}
}
