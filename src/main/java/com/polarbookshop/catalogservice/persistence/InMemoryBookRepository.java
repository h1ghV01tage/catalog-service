package com.polarbookshop.catalogservice.persistence;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;

@Repository
public class InMemoryBookRepository implements BookRepository{
	
	private static final Map<String, Book> books = new ConcurrentHashMap<String, Book>();
	
	@Override
	public Iterable<Book> findAll() {
		// TODO Auto-generated method stub
		return books.values();
	}

	@Override
	public Optional<Book> findByIsbn(String isbn) {
		// TODO Auto-generated method stub
		return existsByIsbn(isbn) ? Optional.of(books.get(isbn)) :
			Optional.empty();
	}
	
	//checks if the book exists in the first place
	public boolean existsByIsbn(String isbn) {
		// TODO Auto-generated method stub
		return books.get(isbn) != null;
	}

	@Override
	public Book save(Book book) {
		// TODO Auto-generated method stub
		books.put(book.isbn(), book);
		return book;
	}

	@Override
	public void deleteByIsbn(String isbn) {
		// TODO Auto-generated method stub
		books.remove(isbn);
		
	}

	
}
