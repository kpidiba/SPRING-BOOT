package com.jpa.crud.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jpa.crud.dao.BookRepository;
import com.jpa.crud.entities.Book;

@Component
public class BookService {
	
	// private static List<Book> list = new ArrayList<>();
	
	// static {
	// 	list.add(new Book(11,"Java completed ","manuelle"));
	// 	list.add(new Book(12,"Java completed ","manuelle"));
	// 	list.add(new Book(13,"Java completed ","manuelle"));
	// }

	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> getBooks(){
			return  (List<Book>) this.bookRepository.findAll();
	}
	
	
	public Book addBook(Book book) {
		return bookRepository.save(book);
	}

	public void deleteBook(long id){
		// list =list.stream().filter(book ->{
		// 	if(book.getId() != id){
		// 		return true;
		// 	}else{
		// 		return false;	
		// 	}
		// }).collect(Collectors.toList());
		this.bookRepository.deleteById((int)id);
	}

	public Book updateBook(Book book,long id){
		// list.stream().map(b ->{
		// 	if(b.getId() == id){
		// 		b.setId(id);
		// 		b.setTitle(book.getTitle());
		// 		b.setAuthor(book.getAuthor());
		// 	}
		// 	return b;
		// }).collect(Collectors.toList());
		book.setId(id);
		bookRepository.save(book);
		return book;
	}
}
