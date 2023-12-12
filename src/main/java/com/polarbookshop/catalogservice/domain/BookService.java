package com.polarbookshop.catalogservice.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	
	private final BookRepository bookRepository;
	
	private Map<String, Book> map = new HashMap<String, Book>(); 
	
	public BookService(BookRepository bookRepository) {
		
		this.bookRepository = bookRepository;
		
	}
	
	//get all the books in the catalog : 
	public Iterable<Book> viewBookList(){
		
		return bookRepository.findAll();
		
	}
	
	//get the book with the given isbn
	public Book viewBookDetails(String isbn) {
		
		return bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
		
	}
	
	//add book to the catalog
	public Book addBookToCatalog(Book book) {
		if(bookRepository.existsByIsbn(book.isbn())) {
			throw new BookAlreadyExistsException(book.isbn());
		}
		
		return bookRepository.save(book); 
		
	}
	
	//remove book from the catalog
	public void removeBookFromCatalog(String isbn) {
		
		bookRepository.deleteByIsbn(isbn);
		
		
	}
	
	
	 public Book editBookDetails(String isbn, Book book) {
		
		 Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
		 
		 if(optionalBook.isPresent()) {
			 
			 Book existingBook = optionalBook.get();
			 Book updatedBook = new Book(existingBook.isbn(),book.title(),book.author(),book.price());
			 
			 return updatedBook;
		 }
		 
		 else {
			 
			 //create a new book and save it to the repository
			 return addBookToCatalog(book);
			 
		 }
	
				
	}
	
	
	//edit the book
		
}
